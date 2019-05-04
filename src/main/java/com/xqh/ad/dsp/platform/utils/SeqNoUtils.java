package com.xqh.ad.dsp.platform.utils;

import com.xqh.ad.dsp.platform.utils.enums.SeqBizEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 前缀(两位) + 项目(3位)+ 机器码(3位) + 时间(13位) + 自增(3位)
 * 序列号组件
 */
@Slf4j
@Component
public class SeqNoUtils {

    /**
     * 项目
     */
    @Value("${seq.projectId}")
    private String projectId;

    /**
     * 机器码
     */
    @Value("${seq.workerId}")
    private String workerId;

    // 13位时间戳
    private long lastTimestamp;
    // 自增序列
    private int sequenceId;
    // 自增最大数值
    private final static int MAX_SEQUENCEID = 1000;

    @PostConstruct
    public void init(){
        this.lastTimestamp = System.currentTimeMillis();
        this.sequenceId = 0;
    }

    /**
     * 获取指定业务序列号
     */
    public String getNextSeqNo(SeqBizEnum seqBizEnum) {
        IncResult incResult = handleInc();
        return getSeqNo(seqBizEnum.getCode(), incResult.getTimestamp(), incResult.getSequenceId());
    }

    /**
     * 自增操作
     */
    private synchronized IncResult handleInc() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException("时间异常");
        }

        if (timestamp == this.lastTimestamp) {
            this.sequenceId = (this.sequenceId + 1) % MAX_SEQUENCEID;
            if (this.sequenceId == 0) {
                timestamp = nextTimestamp(this.lastTimestamp);
             }
        } else {
            this.sequenceId = 0;
        }
        this.lastTimestamp = timestamp;
        IncResult incResult = new IncResult();
        incResult.setSequenceId(this.sequenceId);
        incResult.setTimestamp(timestamp);
        return incResult;
    }

    /**
     * 获取上次取数毫秒的下一时刻
     */
    private long nextTimestamp(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    private String getSeqNo(String bizCode, long timestamp, int sequenceId) {
        StringBuilder sb =  new StringBuilder();
        return sb.append(bizCode).append(projectId).append(workerId).append(timestamp).append(String.format("%03d", sequenceId)).toString();
    }


    /**
     * 自增处理结果
     */
    @Data
    private static class IncResult {
        private long timestamp;
        private int sequenceId;
    }

}
