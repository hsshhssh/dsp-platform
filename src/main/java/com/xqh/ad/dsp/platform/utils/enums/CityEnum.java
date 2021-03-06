package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by samson.huang on 2019/7/27
 */
@Getter
@AllArgsConstructor
public enum CityEnum {
    _110000("110000", "北京市",""),
    _120000("120000", "天津市",""),
    _310000("310000", "上海市",""),
    _500000("500000", "重庆市",""),
    _130100("130100", "石家庄市", "130000"),
    _130200("130200", "唐山市", "130000"),
    _130300("130300", "秦皇岛市", "130000"),
    _130400("130400", "邯郸市", "130000"),
    _130500("130500", "邢台市", "130000"),
    _130600("130600", "保定市", "130000"),
    _130700("130700", "张家口市", "130000"),
    _130800("130800", "承德市", "130000"),
    _130900("130900", "沧州市", "130000"),
    _131000("131000", "廊坊市", "130000"),
    _131100("131100", "衡水市", "130000"),
    _139001("139001", "定州市", "130000"),
    _139002("139002", "辛集市", "130000"),
    _140100("140100", "太原市", "140000"),
    _140200("140200", "大同市", "140000"),
    _140300("140300", "阳泉市", "140000"),
    _140400("140400", "长治市", "140000"),
    _140500("140500", "晋城市", "140000"),
    _140600("140600", "朔州市", "140000"),
    _140700("140700", "晋中市", "140000"),
    _140800("140800", "运城市", "140000"),
    _140900("140900", "忻州市", "140000"),
    _141000("141000", "临汾市", "140000"),
    _141100("141100", "吕梁市", "140000"),
    _150100("150100", "呼和浩特市", "150000"),
    _150200("150200", "包头市", "150000"),
    _150300("150300", "乌海市", "150000"),
    _150400("150400", "赤峰市", "150000"),
    _150500("150500", "通辽市", "150000"),
    _150600("150600", "鄂尔多斯市", "150000"),
    _150700("150700", "呼伦贝尔市", "150000"),
    _150800("150800", "巴彦淖尔市", "150000"),
    _150900("150900", "乌兰察布市", "150000"),
    _152200("152200", "兴安盟", "150000"),
    _152500("152500", "锡林郭勒盟", "150000"),
    _152900("152900", "阿拉善盟", "150000"),
    _210100("210100", "沈阳市", "210000"),
    _210200("210200", "大连市", "210000"),
    _210300("210300", "鞍山市", "210000"),
    _210400("210400", "抚顺市", "210000"),
    _210500("210500", "本溪市", "210000"),
    _210600("210600", "丹东市", "210000"),
    _210700("210700", "锦州市", "210000"),
    _210800("210800", "营口市", "210000"),
    _210900("210900", "阜新市", "210000"),
    _211000("211000", "辽阳市", "210000"),
    _211100("211100", "盘锦市", "210000"),
    _211200("211200", "铁岭市", "210000"),
    _211300("211300", "朝阳市", "210000"),
    _211400("211400", "葫芦岛市", "210000"),
    _220100("220100", "长春市", "220000"),
    _220200("220200", "吉林市", "220000"),
    _220300("220300", "四平市", "220000"),
    _220400("220400", "辽源市", "220000"),
    _220500("220500", "通化市", "220000"),
    _220600("220600", "白山市", "220000"),
    _220700("220700", "松原市", "220000"),
    _220800("220800", "白城市", "220000"),
    _222400("222400", "延边朝鲜族自治州", "220000"),
    _230100("230100", "哈尔滨市", "230000"),
    _230200("230200", "齐齐哈尔市", "230000"),
    _230300("230300", "鸡西市", "230000"),
    _230400("230400", "鹤岗市", "230000"),
    _230500("230500", "双鸭山市", "230000"),
    _230600("230600", "大庆市", "230000"),
    _230700("230700", "伊春市", "230000"),
    _230800("230800", "佳木斯市", "230000"),
    _230900("230900", "七台河市", "230000"),
    _231000("231000", "牡丹江市", "230000"),
    _231100("231100", "黑河市", "230000"),
    _231200("231200", "绥化市", "230000"),
    _232700("232700", "大兴安岭地区", "230000"),
    _320100("320100", "南京市", "320000"),
    _320200("320200", "无锡市", "320000"),
    _320300("320300", "徐州市", "320000"),
    _320400("320400", "常州市", "320000"),
    _320500("320500", "苏州市", "320000"),
    _320600("320600", "南通市", "320000"),
    _320700("320700", "连云港市", "320000"),
    _320800("320800", "淮安市", "320000"),
    _320900("320900", "盐城市", "320000"),
    _321000("321000", "扬州市", "320000"),
    _321100("321100", "镇江市", "320000"),
    _321200("321200", "泰州市", "320000"),
    _321300("321300", "宿迁市", "320000"),
    _330100("330100", "杭州市", "330000"),
    _330200("330200", "宁波市", "330000"),
    _330300("330300", "温州市", "330000"),
    _330400("330400", "嘉兴市", "330000"),
    _330500("330500", "湖州市", "330000"),
    _330600("330600", "绍兴市", "330000"),
    _330700("330700", "金华市", "330000"),
    _330800("330800", "衢州市", "330000"),
    _330900("330900", "舟山市", "330000"),
    _331000("331000", "台州市", "330000"),
    _331100("331100", "丽水市", "330000"),
    _340100("340100", "合肥市", "340000"),
    _340200("340200", "芜湖市", "340000"),
    _340300("340300", "蚌埠市", "340000"),
    _340400("340400", "淮南市", "340000"),
    _340500("340500", "马鞍山市", "340000"),
    _340600("340600", "淮北市", "340000"),
    _340700("340700", "铜陵市", "340000"),
    _340800("340800", "安庆市", "340000"),
    _341000("341000", "黄山市", "340000"),
    _341100("341100", "滁州市", "340000"),
    _341200("341200", "阜阳市", "340000"),
    _341300("341300", "宿州市", "340000"),
    _341500("341500", "六安市", "340000"),
    _341600("341600", "亳州市", "340000"),
    _341700("341700", "池州市", "340000"),
    _341800("341800", "宣城市", "340000"),
    _350100("350100", "福州市", "350000"),
    _350200("350200", "厦门市", "350000"),
    _350300("350300", "莆田市", "350000"),
    _350400("350400", "三明市", "350000"),
    _350500("350500", "泉州市", "350000"),
    _350600("350600", "漳州市", "350000"),
    _350700("350700", "南平市", "350000"),
    _350800("350800", "龙岩市", "350000"),
    _350900("350900", "宁德市", "350000"),
    _360100("360100", "南昌市", "360000"),
    _360200("360200", "景德镇市", "360000"),
    _360300("360300", "萍乡市", "360000"),
    _360400("360400", "九江市", "360000"),
    _360500("360500", "新余市", "360000"),
    _360600("360600", "鹰潭市", "360000"),
    _360700("360700", "赣州市", "360000"),
    _360800("360800", "吉安市", "360000"),
    _360900("360900", "宜春市", "360000"),
    _361000("361000", "抚州市", "360000"),
    _361100("361100", "上饶市", "360000"),
    _370100("370100", "济南市", "370000"),
    _370200("370200", "青岛市", "370000"),
    _370300("370300", "淄博市", "370000"),
    _370400("370400", "枣庄市", "370000"),
    _370500("370500", "东营市", "370000"),
    _370600("370600", "烟台市", "370000"),
    _370700("370700", "潍坊市", "370000"),
    _370800("370800", "济宁市", "370000"),
    _370900("370900", "泰安市", "370000"),
    _371000("371000", "威海市", "370000"),
    _371100("371100", "日照市", "370000"),
    _371200("371200", "莱芜市", "370000"),
    _371300("371300", "临沂市", "370000"),
    _371400("371400", "德州市", "370000"),
    _371500("371500", "聊城市", "370000"),
    _371600("371600", "滨州市", "370000"),
    _371700("371700", "菏泽市", "370000"),
    _410100("410100", "郑州市", "410000"),
    _410200("410200", "开封市", "410000"),
    _410300("410300", "洛阳市", "410000"),
    _410400("410400", "平顶山市", "410000"),
    _410500("410500", "安阳市", "410000"),
    _410600("410600", "鹤壁市", "410000"),
    _410700("410700", "新乡市", "410000"),
    _410800("410800", "焦作市", "410000"),
    _410900("410900", "濮阳市", "410000"),
    _411000("411000", "许昌市", "410000"),
    _411100("411100", "漯河市", "410000"),
    _411200("411200", "三门峡市", "410000"),
    _411300("411300", "南阳市", "410000"),
    _411400("411400", "商丘市", "410000"),
    _411500("411500", "信阳市", "410000"),
    _411600("411600", "周口市", "410000"),
    _411700("411700", "驻马店市", "410000"),
    _419001("419001", "济源市", "410000"),
    _420100("420100", "武汉市", "420000"),
    _420200("420200", "黄石市", "420000"),
    _420300("420300", "十堰市", "420000"),
    _420500("420500", "宜昌市", "420000"),
    _420600("420600", "襄阳市", "420000"),
    _420700("420700", "鄂州市", "420000"),
    _420800("420800", "荆门市", "420000"),
    _420900("420900", "孝感市", "420000"),
    _421000("421000", "荆州市", "420000"),
    _421100("421100", "黄冈市", "420000"),
    _421200("421200", "咸宁市", "420000"),
    _421300("421300", "随州市", "420000"),
    _422800("422800", "恩施土家族苗族自治州", "420000"),
    _429004("429004", "仙桃市", "420000"),
    _429005("429005", "潜江市", "420000"),
    _429006("429006", "天门市", "420000"),
    _429021("429021", "神农架林区", "420000"),
    _430100("430100", "长沙市", "430000"),
    _430200("430200", "株洲市", "430000"),
    _430300("430300", "湘潭市", "430000"),
    _430400("430400", "衡阳市", "430000"),
    _430500("430500", "邵阳市", "430000"),
    _430600("430600", "岳阳市", "430000"),
    _430700("430700", "常德市", "430000"),
    _430800("430800", "张家界市", "430000"),
    _430900("430900", "益阳市", "430000"),
    _431000("431000", "郴州市", "430000"),
    _431100("431100", "永州市", "430000"),
    _431200("431200", "怀化市", "430000"),
    _431300("431300", "娄底市", "430000"),
    _433100("433100", "湘西土家族苗族自治州", "430000"),
    _440100("440100", "广州市", "440000"),
    _440200("440200", "韶关市", "440000"),
    _440300("440300", "深圳市", "440000"),
    _440400("440400", "珠海市", "440000"),
    _440500("440500", "汕头市", "440000"),
    _440600("440600", "佛山市", "440000"),
    _440700("440700", "江门市", "440000"),
    _440800("440800", "湛江市", "440000"),
    _440900("440900", "茂名市", "440000"),
    _441200("441200", "肇庆市", "440000"),
    _441300("441300", "惠州市", "440000"),
    _441400("441400", "梅州市", "440000"),
    _441500("441500", "汕尾市", "440000"),
    _441600("441600", "河源市", "440000"),
    _441700("441700", "阳江市", "440000"),
    _441800("441800", "清远市", "440000"),
    _441900("441900", "东莞市", "440000"),
    _442000("442000", "中山市", "440000"),
    _445100("445100", "潮州市", "440000"),
    _445200("445200", "揭阳市", "440000"),
    _445300("445300", "云浮市", "440000"),
    _450100("450100", "南宁市", "450000"),
    _450200("450200", "柳州市", "450000"),
    _450300("450300", "桂林市", "450000"),
    _450400("450400", "梧州市", "450000"),
    _450500("450500", "北海市", "450000"),
    _450600("450600", "防城港市", "450000"),
    _450700("450700", "钦州市", "450000"),
    _450800("450800", "贵港市", "450000"),
    _450900("450900", "玉林市", "450000"),
    _451000("451000", "百色市", "450000"),
    _451100("451100", "贺州市", "450000"),
    _451200("451200", "河池市", "450000"),
    _451300("451300", "来宾市", "450000"),
    _451400("451400", "崇左市", "450000"),
    _460100("460100", "海口市", "460000"),
    _460200("460200", "三亚市", "460000"),
    _460300("460300", "三沙市", "460000"),
    _460400("460400", "儋州市", "460000"),
    _469001("469001", "五指山市", "460000"),
    _469002("469002", "琼海市", "460000"),
    _469005("469005", "文昌市", "460000"),
    _469006("469006", "万宁市", "460000"),
    _469007("469007", "东方市", "460000"),
    _469021("469021", "定安县", "460000"),
    _469022("469022", "屯昌县", "460000"),
    _469023("469023", "澄迈县", "460000"),
    _469024("469024", "临高县", "460000"),
    _469025("469025", "白沙黎族自治县", "460000"),
    _469026("469026", "昌江黎族自治县", "460000"),
    _469027("469027", "乐东黎族自治县", "460000"),
    _469028("469028", "陵水黎族自治县", "460000"),
    _469029("469029", "保亭黎族苗族自治县", "460000"),
    _469030("469030", "琼中黎族苗族自治县", "460000"),
    _510100("510100", "成都市", "510000"),
    _510300("510300", "自贡市", "510000"),
    _510400("510400", "攀枝花市", "510000"),
    _510500("510500", "泸州市", "510000"),
    _510600("510600", "德阳市", "510000"),
    _510700("510700", "绵阳市", "510000"),
    _510800("510800", "广元市", "510000"),
    _510900("510900", "遂宁市", "510000"),
    _511000("511000", "内江市", "510000"),
    _511100("511100", "乐山市", "510000"),
    _511300("511300", "南充市", "510000"),
    _511400("511400", "眉山市", "510000"),
    _511500("511500", "宜宾市", "510000"),
    _511600("511600", "广安市", "510000"),
    _511700("511700", "达州市", "510000"),
    _511800("511800", "雅安市", "510000"),
    _511900("511900", "巴中市", "510000"),
    _512000("512000", "资阳市", "510000"),
    _513200("513200", "阿坝藏族羌族自治州", "510000"),
    _513300("513300", "甘孜藏族自治州", "510000"),
    _513400("513400", "凉山彝族自治州", "510000"),
    _520100("520100", "贵阳市", "520000"),
    _520200("520200", "六盘水市", "520000"),
    _520300("520300", "遵义市", "520000"),
    _520400("520400", "安顺市", "520000"),
    _520500("520500", "毕节市", "520000"),
    _520600("520600", "铜仁市", "520000"),
    _522300("522300", "黔西南布依族苗族自治州", "520000"),
    _522600("522600", "黔东南苗族侗族自治州", "520000"),
    _522700("522700", "黔南布依族苗族自治州", "520000"),
    _530100("530100", "昆明市", "530000"),
    _530300("530300", "曲靖市", "530000"),
    _530400("530400", "玉溪市", "530000"),
    _530500("530500", "保山市", "530000"),
    _530600("530600", "昭通市", "530000"),
    _530700("530700", "丽江市", "530000"),
    _530800("530800", "普洱市", "530000"),
    _530900("530900", "临沧市", "530000"),
    _532300("532300", "楚雄彝族自治州", "530000"),
    _532500("532500", "红河哈尼族彝族自治州", "530000"),
    _532600("532600", "文山壮族苗族自治州", "530000"),
    _532800("532800", "西双版纳傣族自治州", "530000"),
    _532900("532900", "大理白族自治州", "530000"),
    _533100("533100", "德宏傣族景颇族自治州", "530000"),
    _533300("533300", "怒江傈僳族自治州", "530000"),
    _533400("533400", "迪庆藏族自治州", "530000"),
    _540100("540100", "拉萨市", "540000"),
    _540200("540200", "日喀则市", "540000"),
    _540300("540300", "昌都市", "540000"),
    _540400("540400", "林芝市", "540000"),
    _540500("540500", "山南市", "540000"),
    _542400("542400", "那曲地区", "540000"),
    _542500("542500", "阿里地区", "540000"),
    _610100("610100", "西安市", "610000"),
    _610200("610200", "铜川市", "610000"),
    _610300("610300", "宝鸡市", "610000"),
    _610400("610400", "咸阳市", "610000"),
    _610500("610500", "渭南市", "610000"),
    _610600("610600", "延安市", "610000"),
    _610700("610700", "汉中市", "610000"),
    _610800("610800", "榆林市", "610000"),
    _610900("610900", "安康市", "610000"),
    _611000("611000", "商洛市", "610000"),
    _620100("620100", "兰州市", "620000"),
    _620200("620200", "嘉峪关市", "620000"),
    _620300("620300", "金昌市", "620000"),
    _620400("620400", "白银市", "620000"),
    _620500("620500", "天水市", "620000"),
    _620600("620600", "武威市", "620000"),
    _620700("620700", "张掖市", "620000"),
    _620800("620800", "平凉市", "620000"),
    _620900("620900", "酒泉市", "620000"),
    _621000("621000", "庆阳市", "620000"),
    _621100("621100", "定西市", "620000"),
    _621200("621200", "陇南市", "620000"),
    _622900("622900", "临夏回族自治州", "620000"),
    _623000("623000", "甘南藏族自治州", "620000"),
    _630100("630100", "西宁市", "620000"),
    _630200("630200", "海东市", "620000"),
    _632200("632200", "海北藏族自治州", "620000"),
    _632300("632300", "黄南藏族自治州", "620000"),
    _632500("632500", "海南藏族自治州", "620000"),
    _632600("632600", "果洛藏族自治州", "620000"),
    _632700("632700", "玉树藏族自治州", "620000"),
    _632800("632800", "海西蒙古族藏族自治州", "620000"),
    _640100("640100", "银川市", "640000"),
    _640200("640200", "石嘴山市", "640000"),
    _640300("640300", "吴忠市", "640000"),
    _640400("640400", "固原市", "640000"),
    _640500("640500", "中卫市", "640000"),
    _650100("650100", "乌鲁木齐市", "650000"),
    _650200("650200", "克拉玛依市", "650000"),
    _650400("650400", "吐鲁番市", "650000"),
    _650500("650500", "哈密市", "650000"),
    _652300("652300", "昌吉回族自治州", "650000"),
    _652700("652700", "博尔塔拉蒙古自治州", "650000"),
    _652800("652800", "巴音郭楞蒙古自治州", "650000"),
    _652900("652900", "阿克苏地区", "650000"),
    _653000("653000", "克孜勒苏柯尔克孜自治州", "650000"),
    _653100("653100", "喀什地区", "650000"),
    _653200("653200", "和田地区", "650000"),
    _654000("654000", "伊犁哈萨克自治州", "650000"),
    _654200("654200", "塔城地区", "650000"),
    _654300("654300", "阿勒泰地区", "650000"),
    _659001("659001", "石河子市", "650000"),
    _659002("659002", "阿拉尔市", "650000"),
    _659003("659003", "图木舒克市", "650000"),
    _659004("659004", "五家渠市", "650000"),
    _659006("659006", "铁门关市", "650000"),
    _710101("710101", "金门", "710000"),
    _710102("710102", "连江", "710000"),
    _710103("710103", "苗栗", "710000"),
    _710104("710104", "南投", "710000"),
    _710105("710105", "澎湖", "710000"),
    _710106("710106", "屏东", "710000"),
    _710107("710107", "台东", "710000"),
    _710108("710108", "台中", "710000"),
    _710109("710109", "台南", "710000"),
    _710110("710110", "台北", "710000"),
    _710111("710111", "桃园", "710000"),
    _710112("710112", "云林", "710000"),
    _710113("710113", "新北", "710000"),
    _710114("710114", "彰化", "710000"),
    _710115("710115", "嘉义", "710000"),
    _710116("710116", "新竹", "710000"),
    _710117("710117", "花莲", "710000"),
    _710118("710118", "宜兰", "710000"),
    _710119("710119", "高雄", "710000"),
    _710120("710120", "基隆", "710000"),
    _810101("810101", "中西区", "810000"),
    _810102("810102", "东区", "810000"),
    _810103("810103", "九龙城区", "810000"),
    _810104("810104", "观塘区", "810000"),
    _810105("810105", "深水埗区", "810000"),
    _810106("810106", "湾仔区", "810000"),
    _810107("810107", "黄大仙区", "810000"),
    _810108("810108", "油尖旺区", "810000"),
    _810109("810109", "离岛区", "810000"),
    _810110("810110", "葵青区", "810000"),
    _810111("810111", "北区", "810000"),
    _810112("810112", "西贡区", "810000"),
    _810113("810113", "沙田区", "810000"),
    _810114("810114", "屯门区", "810000"),
    _810115("810115", "大埔区", "810000"),
    _810116("810116", "荃湾区", "810000"),
    _810117("810117", "元朗区", "810000"),
    _810118("810118", "香港", "810000"),
    _810119("810119", "九龙", "810000"),
    _810120("810120", "新界", "810000"),
    _820101("820101", "离岛", "820000"),
    _820102("820102", "澳门半岛", "820000"),
    _820103("820103", "凼仔", "820000"),
    _820104("820104", "路凼城", "820000"),
    _820105("820105", "路环", "820000"),
    ;

    private String code;
    private String name;
    private String province;

    public static CityEnum get(String code) {
        for (CityEnum cityEnum : CityEnum.values()) {
            if (Objects.equals(code, cityEnum.getCode())) {
                return cityEnum;
            }
        }
        return null;
    }
}
