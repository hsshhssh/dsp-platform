package com.xqh.ad.dsp.platform.controller;

import com.xqh.ad.dsp.platform.utils.zkconf.UploadConfig;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * Created by samson.huang on 2019/5/23
 */
@Controller
@RequestMapping("dsp/upload")
public class FileUploadController {

    @Resource
    private UploadConfig uploadConfig;

    @PostMapping
    @ResponseBody
    public Response upload(@RequestParam("file")MultipartFile file) {
        if (!file.isEmpty()) {
            String fileNameStr = System.currentTimeMillis()  + "-" + file.getOriginalFilename();
            String saveFileStr = uploadConfig.getSavePath() + "/" + fileNameStr;
            File saveFile = new File(saveFileStr);

            try {
                file.transferTo(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
                return new Response("上传失败");
            }
            return new Response(fileNameStr, uploadConfig.getFileUrlPrefix() + fileNameStr);
        }
        return new Response("上传失败");
    }


    @Data
    public static class Response {
        private String code;
        private String msg;
        private String fileName;
        private String fileUrl;

        public Response(String msg) {
            this.code = "100";
            this.msg = msg;
        }

        public Response(String fileName, String fileUrl) {
            this.code = "200";
            this.msg = "成功";
            this.fileName = fileName;
            this.fileUrl = fileUrl;
        }

        public Response() {
        }
    }

}
