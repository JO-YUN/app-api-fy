var format = [
//{"name":"itemName","label":"事项名称","display":"1","disabled":"1"},
//{"name":"ReceiveNum","label":"","display":"0"},//外网二次提交必填
{"name":"State","label":"","display":"0"},//"sp://外网首次提交  bqbz://外网二次提交"，	
{"name":"ItemId","label":"事项ID","display":"0"},//"事项ID"，	
{"name":"ItemCode","label":"事项CODE","display":"0"},//"事项CODE（必填）"，	
{"name":"ItemName","label":"事项名称","display":"0"},//"事项名称"，	
{"name":"OrgCode","label":"事项所属单位","display":"0"},//"事项所属单位（必填）"，	
{"name":"OrgName","label":"事项所属单位名称","display":"0"},//"事项所属单位名称"，	
{"name":"DataId","label":"表单数据ID","display":"0"},//"表单数据ID"，	
{"name":"FormId","label":"表单ID","display":"0"},//"表单ID"，	'
{"name":"ApplyFrom","label":"网上申报来源","display":"0"},//"网上申报来源"（01://网上申办；15://自助终端机）	
{"name":"IsPrejudication","label":"网上申报来源","display":"0"},//预审状态（11:待预审；12:已预审）
{"name":"zjhm","label":"证件号码","display":"1","required":"required"},
{"name": "sqrxm","label":"申请人姓名","display":"1","required":"required"}, 
{"name": "lxrxm","label":"联系人姓名","display":"1","required":"required"}, 
{"name": "lxrphone","label":"联系人电话","display":"1","required":"required"}, 
{"name": "userName","label":"用户名","display":"0"}, 
{"name": "ucid","label":"用户id","display":"0"},

{"name":"servicelist_ApplicantName","label":"申请人名称","display":"1"},// "申请人名称",
{"name":"servicelist_ApplicantID","label":"申请人身份证号","display":"1"},// "申请人身份证号",
{"name":"servicelist_ApplicantMobile","label":"申请人手机号","display":"1"},// "申请人手机号",
{"name":"servicelist_ApplicantAddress","label":"申请人地址","display":"1"},// "申请人地址",
{"name":"servicelist_SendType","label":"请选择邮寄方式","display":"1","type":"select","data":[{"key":"自取","value":"0"},{"key":"投递","value":"1"}],"event":{"name":"change","func":SendTypeCk}},// "邮寄方式0为自取，1为投递",
{"name":"servicelist_HallCode","label":"自取大厅代码","display":"1","group":"servicelist0"},// "自取大厅代码",
{"name":"servicelist_HallName","label":"自取大厅名称","display":"1","group":"servicelist0"},// "自取大厅名称",
//{"name":"servicelist_MailAddress","label":"","display":"1","group":"servicelist0"},// "邮寄地址",
{"name":"servicelist_EmsName","label":"收件人名称","display":"1","group":"servicelist1"},//"收件人名称",
{"name":"servicelist_EmsMobile","label":"收件人手机号","display":"1","group":"servicelist1"},//"收件人手机号"，
{"name":"servicelist_SendMailPostCode","label":"邮政编码（讷河市：161300）","display":"1","group":"servicelist1"},//"邮政编码",
{"name":"servicelist_SendMailAddr","label":"详细地址","display":"1","group":"servicelist1"},//"详细地址",
{"name":"servicelist_SendMailProvince","label":"地址省份","display":"1","group":"servicelist1"},//"地址省份",
{"name":"servicelist_SendMailCity","label":"地址市","display":"1","group":"servicelist1"},//"地址市",
{"name":"servicelist_SendMailDistrict","label":"地址区","display":"1","group":"servicelist1"},//"地址区",
{"name":"servicelist_SendMailBlock","label":"地址街道","display":"1","group":"servicelist1"},//"地址街道"

{"name":"ObjectType","label":"服务对象类型","display":"1","disabled":"1","type":"select","data":[{"key":"个人","value":"1"},{"key":"项目","value":"2"},{"key":"企业","value":"3"}],"event":{"name":"change","func":ObjectType}},//"服务对象类型（必填）"，（1://人员；2://项目 3://企业）	'
{"name": "info_identityType","label":"证件类型","display":"1","group":"info1","type":"select","data":[{"key":"身份证","value":"10"}]},//证件类型不能为空
{"name": "info_idcardNo","label":"证件号","display":"1","group":"info1","required":"required"},//证件号不能为空
{"name": "info_name","label":"姓名","display":"1","group":"info1","required":"required"},//姓名不能为空
{"name": "info_sex","label":"性别","display":"1","group":"info1"},//性别
{"name": "info_nation","label":"民族","display":"1","group":"info1"},//民族
{"name": "info_politicalStatus","label":"政治面貌","display":"1","group":"info1"},//政治面貌
{"name": "info_education","label":"学历","display":"1","group":"info1"},//学历
{"name": "info_nativePlace","label":"籍贯","display":"1","group":"info1"},// 籍贯
{"name": "info_birthday","label":"出生日期","display":"1","group":"info1","event":{"name":"tap","func":dateShow}},//出生日期 
{"name": "info_country","label":"国籍","display":"1","group":"info1"},//国籍
{"name": "info_homeAddress","label":"居住地址","display":"1","group":"info1"},//居住地址
{"name": "info_linkPhone","label":"联系电话不能为空","display":"1","group":"info1","required":"required"},//联系电话不能为空
{"name": "info_linkAddress","label":"联系地址不能为空","display":"1","group":"info1","required":"required"},//联系地址不能为空
{"name": "info_postCode","label":"邮政编码（讷河市：161300）","display":"1","group":"info1"},//邮政编码
{"name": "info_province","label":"省","display":"1","group":"info1","defaultValue":"黑龙江省"},//省
{"name": "info_city","label":"市","display":"1","group":"info1","defaultValue":"齐齐哈尔市"},//市
{"name": "info_county","label":"县","display":"1","group":"info1","defaultValue":"讷河市"},//县
{"name": "info_email","label":"邮箱","display":"1","group":"info1"},//邮箱
{"name": "info_projectName","label":"项目名称","display":"1","group":"info2","required":"required"},//项目名称(必填)
{"name": "info_projectCode","label":"项目编码","display":"1","group":"info2","required":"required"},//项目编码(必填)
{"name": "info_location","label":"项目地址","display":"1","group":"info2"},//项目地址
{"name": "info_linkMan","label":"联系人","display":"1","group":"info2"},//联系人
{"name": "info_linkPhone","label":"联系人电话","display":"1","group":"info2"},//联系人电话
{"name": "info_projectContent","label":"建设内容","display":"1","group":"info2"},//建设内容
{"name": "info_areaAll","label":"总用地面积","display":"1","group":"info2"},//总用地面积
{"name": "info_areaBuild","label":"建筑面积","display":"1","group":"info2"},//建筑面积
{"name": "info_investment","label":"总投资","display":"1","group":"info2"},//总投资
{"name": "info_projectAllowedNo","label":"项目审批核准备案文号","display":"1","group":"info2"},//项目审批核准备案文号
{"name": "info_orgName","label":"组织机构名称","display":"1","group":"info3","required":"required"},// 组织机构名称(必填) varchar(100) 不能为空
{"name": "info_orgCode","label":"组织机构代码","display":"1","group":"info3","required":"required"},// 组织机构代码(必填) varchar(20) 不能为空
{"name": "info_orgCodeAwardDate","label":"组织机构代码证发证日期","display":"1","group":"info3","event":{"name":"tap","func":dateShow}},//组织机构代码证发证日期 datetime 
{"name": "info_orgCodeAwardOrg","label":"组织机构代码发证机构","display":"1","group":"info3"},// 组织机构代码发证机构 varchar(50) 
{"name": "info_orgCodeValidPeriodStart","label":"组织机构代码证有效期起","display":"1","group":"info3","event":{"name":"tap","func":dateShow}},// 组织机构代码证有效期起 datetime
{"name": "info_orgCodeValidPeriodEnd_str","label":"组织机构代码证有效期止","display":"1","group":"info3","event":{"name":"tap","func":dateShow}},// 组织机构代码证有效期止 datetime 
//{"name": "info_orgCodeValidPeriodEnd","label":"","display":"1","group":"info3"},// 
{"name": "info_orgEnglishName","label":"机构英文名称","display":"1","group":"info3"},// 机构英文名称 varchar(100) 
{"name": "info_orgType","label":"组织机构类型","display":"1","group":"info3","required":"required"},// 组织机构类型 varchar(50) 不能为空
{"name": "info_enterpriseSortCode","label":"企业类别代码 ","display":"1","group":"info3"},// 企业类别代码 varchar(5) 根据国家工商总局2007年9月30日发布的《gs 1-2007企业信用分类监管数据规范》中"b.10　ca16 企业(机构)类型代码"://1000
{"name": "info_enterpriseSortName","label":"企业类别名称 ","display":"1","group":"info3"},// 企业类别名称 varchar(30) 根据国家工商总局2007年9月30日发布的《gs 1-2007企业信用分类监管数据规范》中"b.10　ca16 企业(机构)类型代码"
{"name": "info_enterpriseTypeCode","label":"企业类型代码 ","display":"1","group":"info3"},// 企业类型代码 varchar(5) 根据国家工商总局2007年9月30日发布的《gs 1-2007企业信用分类监管数据规范》中"b.10　ca16 企业(机构)类型代码"
{"name": "info_enterpriseTypeName","label":"企业类型名称","display":"1","group":"info3"},// 企业类型名称 varchar(50) 根据国家工商总局2007年9月30日发布的《gs 1-2007企业信用分类监管数据规范》中"b.10　ca16 企业(机构)类型代码"
{"name": "info_legalPerson","label":"法定代表人 ","display":"1","group":"info3","required":"required"},// 法定代表人 varchar(50) 不能为空
{"name": "info_legalPersonType","label":"法定代表人类型 ","display":"1","group":"info3","required":"required"},//法定代表人类型varchar(50)legal_person法人
{"name": "info_certificateName","label":"法人证件名称 ","display":"1","group":"info3","required":"required"},// 法人证件名称 varchar(20) 不能为空
{"name": "info_certificateNo","label":"法人证件号码 ","display":"1","group":"info3","required":"required"},// 法人证件号码 varchar(20) 不能为空
{"name": "info_responsiblePerson","label":"单位负责人  ","display":"1","group":"info3"},// 单位负责人 varchar(50) 
{"name": "info_responsiblePersonId","label":"单位负责人身份证  ","display":"1","group":"info3"},// 单位负责人身份证 varchar(20) 
{"name": "info_inAreaCode","label":"所属行政区划代码  ","display":"1","group":"info3"},// 所属行政区划代码 varchar(30) 
{"name": "info_inArea","label":"所属行政区划名称  ","display":"1","group":"info3"},// 所属行政区划名称 varchar(100)
{"name": "info_chargeDepartment","label":"上级主管单位  ","display":"1","group":"info3"},// 上级主管单位 varchar(100) 
{"name": "info_registerAddress","label":"单位注册地址  ","display":"1","group":"info3","required":"required"},//单位注册地址 varchar(200) 不能为空
{"name": "info_produceAddress","label":"单位生产地址   ","display":"1","group":"info3"},//单位生产地址 varchar(200) 
{"name": "info_mailingAddress","label":"单位通讯地址   ","display":"1","group":"info3"},//单位通讯地址 varchar(200) 
{"name": "info_postalCode","label":"邮政编码    ","display":"1","group":"info3"},//邮政编码 varchar(6) 
{"name": "info_linkMan","label":"联系人     ","display":"1","group":"info3"},// 联系人 varchar(50) 
{"name": "info_contactPhone","label":"联系电话  ","display":"1","group":"info3"},// 联系电话 varchar(20) 
{"name": "info_fax","label":"传真   ","display":"1","group":"info3"},//传真 varchar(20) 
{"name": "info_linkManEmail","label":"联系人邮箱   ","display":"1","group":"info3"},//联系人邮箱 varchar(50)
{"name": "info_bank","label":"单位开户银行   ","display":"1","group":"info3","type":"select","data":[{"key":"中国工商银行","value":"中国工商银行"},{"key":"中国农业银行","value":"中国农业银行"},{"key":"中国银行","value":"中国银行"},{"key":"中国建设银行","value":"中国建设银行"},{"key":"中国民生银行","value":"中国民生银行"},{"key":"华夏银行","value":"华夏银行"},{"key":"中国光大银行","value":"中国光大银行"},{"key":"中信实业银行","value":"中信实业银行"},{"key":"恒丰银行","value":"恒丰银行"},{"key":"上海浦东发展银行","value":"上海浦东发展银行"},{"key":"交通银行","value":"交通银行"},{"key":"兴业银行","value":"兴业银行"},{"key":"深圳发展银行","value":"深圳发展银行"},{"key":"招商银行","value":"招商银行"},{"key":"广东发展银行","value":"广东发展银行"},{"key":"中国邮政储蓄","value":"中国邮政储蓄"}]},//单位开户银行 varchar(20) 必须写入全称1、中国工商银行；2、中国农业银行；3、中国银行；4、中国建设银行；5、中国民生银行；6、华夏银行；7、中国光大银行；8、中信实业银行；9、恒丰银行；10、上海浦东发展银行；11、交通银行；12、兴业银行；13、深圳发展银行；14、招商银行；15、广东发展银行；16、中国邮政储蓄
{"name": "info_bankAccount","label":"银行账号   ","display":"1","group":"info3"},//银行账号 varchar(20) 
{"name": "info_organizationKind","label":"经济性质   ","display":"1","group":"info3"},// 经济性质 varchar(20) 
{"name": "info_employeeNum","label":"单位人数","display":"1","group":"info3"},//单位人数 varchar(6) 
{"name": "info_registerCapital","label":"注册资本(金) 单位:万元","display":"1","group":"info3"},// 注册资本(金) float 单位://万元
{"name": "info_currencyKind","label":"货币种类","display":"1","group":"info3"},// 货币种类 varchar(10) 
{"name": "info_groundArea","label":"占地面积","display":"1","group":"info3"},// 占地面积 varchar(20) 
{"name": "info_businessScope","label":"经营(生产)范围(主营）","display":"1","group":"info3"},// 经营(生产)范围(主营） varchar(1500) 不能为空
{"name": "info_businessScopePart","label":"经营范围（兼营）","display":"1","group":"info3"},// 经营范围（兼营） varchar(1500) 
{"name": "info_mainProduct","label":"主要产品","display":"1","group":"info3"},// 主要产品 varchar(1000)
{"name": "info_operatingMode","label":"经营方式【自产自销; 代购代销;加工;批发(法人);批发(非法人);零售连锁；零售单体；运输；咨询 】","display":"1","group":"info3"},// 经营方式 varchar(20) 自产自销; 代购代销;加工;批发(法人);批发(非法人);零售连锁；零售单体；运输；咨询 
{"name": "info_registerDate","label":"登记注册日期","display":"1","group":"info3","event":{"name":"tap","func":dateShow}},// 登记注册日期 date 不能为空
{"name": "info_orgFoundDate","label":"单位成立日期","display":"1","group":"info3","event":{"name":"tap","func":dateShow}},// 单位成立日期 date 
{"name": "info_businessLicense","label":"工商注册号","display":"1","group":"info3"},//工商注册号 varchar(20) 
{"name": "info_aicAwardDate","label":"工商营业执照发证日期","display":"1","group":"info3","event":{"name":"tap","func":dateShow}},//工商营业执照发证日期 datetime 
{"name": "info_aicValidPeriodStart","label":"工商营业执照有效期起 ","display":"1","group":"info3","event":{"name":"tap","func":dateShow}}, // 工商营业执照有效期起 datetime 
{"name": "info_aicValidPeriodEnd","label":"工商营业执照有效期止 ","display":"1","group":"info3","event":{"name":"tap","func":dateShow}}, //工商营业执照有效期止 datetime
{"name": "info_aicCertAwardOrg","label":"工商营业执照发证机构 ","display":"1","group":"info3"}, // 工商营业执照发证机构 varchar(50) 
{"name": "info_taxManager","label":"主管税务机关  ","display":"1","group":"info3"}, // 主管税务机关 varchar(100) 
{"name": "info_nationTaxRegisterNo","label":"国税纳税人识别号  ","display":"1","group":"info3"}, // 国税纳税人识别号 varchar(20) 税务登记证号 
{"name": "info_nationTaxAwardDate","label":"国税税务登记证发证日期  ","display":"1","group":"info3","event":{"name":"tap","func":dateShow}}, // 国税税务登记证发证日期 datetime
{"name": "info_locationTaxRegisterNo","label":"地税纳税人识别号  ","display":"1","group":"info3"}, // 地税纳税人识别号 varchar(20) 税务登记证号
{"name": "info_locationTaxAwardDate","label":"地税税务登记证发证日  ","display":"1","group":"info3","event":{"name":"tap","func":dateShow}}, // 地税税务登记证发证日期 datetime 
{"name": "info_nationInvestRate","label":"国家投资额比例  ","display":"1","group":"info3"}, // 国家投资额比例 varchar(5) 百分比
{"name": "info_corporationInvestRate","label":"企业投资额比例  ","display":"1","group":"info3"}, // 企业投资额比例 varchar(5) 百分比 
{"name": "info_foreignInvestRate","label":"外资投资额比例  ","display":"1","group":"info3"}, // 外资投资额比例 varchar(5) 百分比
{"name": "info_naturalManInvestRate","label":"自然人投资额比例  ","display":"1","group":"info3"}, // 自然人投资额比例 varchar(5) 百分比
{"name": "info_bankLoanRate","label":"银行贷款额比例 ","display":"1","group":"info3"}, // 银行贷款额比例 varchar(5) 百分比
{"name": "info_remark","label":"备注信息 ","display":"1","group":"info3"}, //备注信息 varchar(2000) 
{"name": "info_orgActuality","label":"组织机构现状 ","display":"1","group":"info3"},// 组织机构现状 varchar(10) 不能为空
];

var string ="";
var objectType = "";
var selectObjectType = "";
var receiveNum = "";
var state = "sp";//"sp://外网首次提交  bqbz://外网二次提交"，	
var itemId = getQueryVariable("itemId");
var itemCode = "";
var orgCode = getQueryVariable("orgCode");
var itemName = decodeURI(getQueryVariable("itemName"));
var dataId = "";
var formId = "";

//date window
var dtpicker = "";

var xhr;
//上传文件方法
var btnid = "";
var attid = "";
var fileName= "";
var zjhm = "";
var codetemp = "";
var nametemp = "";
var opUpWinId="";
function opUpWin(o){
	var opUpWinIdTemp = $(o).attr("id");
	attid = opUpWinIdTemp.replace("inputb","file");
	$("#"+attid).trigger("click");
	
	
}

function changeValue(o){
	attid = $(o).attr("id");
	var fileNameTemp=$(o).val();
	labelid = attid.replace("file","inputlabel");
	$("#"+labelid).html(fileNameTemp);
}

function uploadfile(o) {
	zjhm = $("#zjhm").val();
	if(zjhm == "" || zjhm == null){
		mui.alert("请先输入 证件号！");
		$("#zjhm").focus();
		return;
	}
    btnid = $(o).attr("id"); 
    attid = btnid.replace("upbt","file");
    opUpWinId = btnid.replace("upbt","inputb");
    codetemp = $(o).data("code");
    nametemp = $(o).data("name");
    var fileObj = $("#"+attid).get(0).files[0];
    if(!fileObj){
    	mui.alert("请选择文件！");
    	return;
    }
    fileName = fileObj.name;
    var url =  "../file2/upload"; // 接收上传文件的后台地址
    
    var form = new FormData(); // FormData 对象
    form.append("file", fileObj); // 文件对象
    form.append("uid", zjhm); 
    form.append("type", "doc");
    form.append("folder_name", "//");
    xhr = new XMLHttpRequest();  // XMLHttpRequest 对象
    xhr.open("post", url, true); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
    xhr.onload = uploadComplete; //请求完成
    xhr.onerror =  uploadFailed; //请求失败
    xhr.ontimeout = function (e) {
    	mui.hideLoading(function() {});
        mui.alert("超时，服务器忙请稍后尝试！");
    };
    //xhr.upload.onprogress = progressFunction;//【上传进度调用方法实现】
    xhr.upload.onloadstart = function(){//上传开始执行方法
//        ot = new Date().getTime();   //设置上传开始时间
//        oloaded = 0;//设置上传开始时，以上传的文件大小为0
    	mui.showLoading("正在加载", "div");
    };
    xhr.send(form); //开始上传，发送form数据
}
//上传成功响应

var metail = [];
function uploadComplete(evt) {
    //服务断接收完文件返回的结果
    var data = JSON.parse(evt.target.responseText);
    console.log(JSON.stringify(data));
    mui.hideLoading(function() {});
    if(data.code == "0000") {
    	$("#"+attid).attr("disabled",true);
        $("#"+btnid).attr("disabled",true);
        $("#"+opUpWinId).attr("disabled",true);
        var m = {};
        m.OPERATOR_ID = "data0452";
        m.OPERATOR_NAME = "data0452";
        m.DOCUMENT_ID = codetemp;
        m.FILE_PATH = data.docid;
        m.FILE_NAME = fileName;
        m.DOCUMENT_NAME = nametemp;
        m.TYPE= "1";
        metail.push(m);
        mui.alert("上传成功！");
    }else{
    	mui.alert("上传失败！"+data.msg);
    }
}
//上传失败
function uploadFailed(evt) {
	mui.hideLoading(function() {});
    mui.alert("上传失败！");
}


//init
$(function() {
	addAtt();
	
	$.ajax({
		type: "post",
		url: "getFormInfo",
		data: JSON.stringify({"itemId":itemId}),
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		contentType: "application/json",
		beforeSend:function(){//mui.showLoading("正在加载", "div");
			},
		complete:function(){//mui.hideLoading(function() {});
		},
		success: function(result) {
			if(result.state == "300"){
				mui.alert(result.error,"提示",function(){window.history.back()});
			}else{
				objectType = result.info.objectType;
				itemCode = result.info.itemCode;
				formId = result.info.formId;
				//mui.alert(JSON.stringify(result));
				saveData(result.info.formId);
			}
	}});
	
	dtpicker = new mui.DtPicker({
	    type: "date",//设置日历初始视图模式 
	    beginDate: new Date(1949, 10, 01),//设置开始日期 
	    endDate: new Date(),//设置结束日期 
	    labels: ['年', '月', '日'],//设置默认标签区域提示语 
	})
	$("#submitBT").on("tap",submit);
})

var materials ="";
var materialHtml = "";
function materialsInit(){
	materials = sessionStorage.getItem("materials");
	var materialsObject = JSON.parse(materials);
	for (var material of materialsObject){
		var NAME = material.NAME;
		var MUST = material.MUST==1?"是":"否";
		var PUBLISHER = material.PUBLISHER;
		var TYPE_VALUE = material.TYPE_VALUE;
		materialHtml += "<div class=\"xx1 row\">"+
		"<div class=\"xinxi1 col-xs-12 col-sm-12\"><span>材料名称："+ NAME +"</span></div>"+
		"<div class=\"xinxi1 col-xs-12 col-sm-12\">是否必须："+ MUST +"</div>"+
		(PUBLISHER?"<div class=\"xinxi1 col-xs-12 col-sm-12\">发放机关："+ PUBLISHER +"</div>":"")+
		(TYPE_VALUE?"<div class=\"xinxi1 col-xs-12 col-sm-12\">材料类型："+ TYPE_VALUE +"</div>":"")+
		"</div>"+
		"<div class=\"nr\" id=\"nr"+material.CODE+"\">"+
		   "</div>"+
		    "<input class=\"submit btn nbt\" type=\"button\" value=\"新增附件\" id=\"nbt\" data-code=\""+material.CODE+"\" data-name=\""+material.NAME+"\" style=\"width: 50%;margin-left: 25%; "+
		    "margin-right: 25%;margin-bottom:20px; background: #4381ee; color: #fff;margin-top: 20px;font-size: 18px;line-height: 20px\">"
	}
}

function addAtt(){
	materialsInit();
	$("#att").html(materialHtml);
	var attnum = 0;

	 $(".nbt").each(function(){
		 $(this).on("tap",function(){
			attnum += 1;
			var code = $(this).data("code");
			var html ='<div class="att-list">'+
				'<input  type="file" id="file'+ code + attnum + '" name="file" onchange="changeValue(this)" style="display:none"/>'+
				'<input type="button" value="点击选择附件" id="inputb'+ code + attnum + '" onclick="opUpWin(this)"/><label id="inputlabel'+ code + attnum + '"></label>'+
				'<input  type="button" id="upbt'+ code + attnum + '" data-code="'+code+'" onclick="uploadfile(this)" value="点击上传" />'+
			'</div>';
			$("#nr"+code).append(html);
			})
		 $(this).trigger("tap");
	 })
}

function saveData(formId){
	$.ajax({
		type: "post",
		url: "saveData",
		contentType:'application/json;charset=utf-8',
		data: JSON.stringify({"formId":formId,"formData":"{[]}"}),
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		contentType: "application/json",
		beforeSend:function(){//mui.showLoading("正在加载", "div");
		},
		complete:function(){//mui.hideLoading(function() {});
		},
		success: function(result) {
			//mui.alert(result);
			creatForm(format);
			//$("#ObjectType").val(objectType);
			if(result != null &&result.state == "200"){
				dataId = result.dataId;
			}else{
				dataId = "";
			}
	}});
}

function getQueryVariable(variable){
   var query = window.location.search.substring(1);
   var vars = query.split("&");
   for (var i=0;i<vars.length;i++) {
           var pair = vars[i].split("=");
           if(pair[0] == variable){return pair[1];}
   }
   return(false);
}

function creatForm(format){
	var html = "";
	for (var f of format){
		//mui.alert(f);
		if(f.display == "1"){
			if(f.type == null || f.type == "date"){
				html += '<div class="form-group" data-group="'+f.group+'">'+
			        //'<label class="control-label" id="'+f.name+'_label">'+ f.label +'</label>'+
			        '<input id="'+f.name+'" class="form-control" name="'+f.name+'" '+(f.disabled=='1'?'disabled':'')+' minlength="2" type="text" '+(f.required=='required'?' required="'+f.required+'"':"") + ' placeholder="'+f.label+ '"'+(f.defaultValue?' value="'+f.defaultValue+'"':'')+'>'+
		        '</div>'
				}else if(f.type == "select"){
					html += '<div class="form-group" data-group="'+f.group+'">'+
			        //'<label for="'+f.name+'" class="control-label" id="'+f.name+'_label">'+f.label+'</label>'+
						'<select name="'+f.name+'" id="'+f.name +'" class="form-control" minlength="2"  '+(f.disabled=='1'?'disabled':'')+'>'
							for(var d of f.data){
								html +='<option value="'+d.value+'">'+f.label+'：'+d.key+'</option>';
							}
					html +='</select>'+
				 	'</div>'
				}
		}else if(f.display == "0"){
			html += '<input id="'+f.name +'" name="' + f.name + '" type="hidden" >';
		}
	}
	
	$("#commentForm").html(html);
	
	//add event
	for (var f of format){
		//event
		if(f.event){
			var e = f.event;
			var param ;
			if(e.para){
				param = e.para;
			}else{
				param = f.name;
			}
			$("#"+f.name).on(e.name, {para:param},e.func);
		}
	}
	
	assignment();
	multipleObjectType();
	killScollBug();
}

function assignment(){
	//$("#ObjectType").val(objectType);
	$("#ItemId").val(itemId);
	$("#ItemName").val(itemName);
	$("#ItemCode").val(itemCode);
	$("#OrgCode").val(orgCode);
	$("#DataId").val(dataId);
	$("#FormId").val(formId);
	$("#IsPrejudication").val("11");
	$("#State").val("sp");
	//for test
//	$("#servicelist_ApplicantName").val("付英贺");
//	$("#servicelist_ApplicantID").val("23040419840831051X");
//	$("#servicelist_ApplicantMobile").val("15911006517");
//	$("#servicelist_ApplicantAddress").val("申请人地址");
//	$("#servicelist_HallCode").val("01");
//	$("#servicelist_HallName").val("自取大厅名称");
//	$("#servicelist_MailAddress").val("邮寄地址");
//	$("#servicelist_EmsName").val("收件人名称");
//	$("#servicelist_EmsMobile").val("15911006517");
//	$("#servicelist_SendMailPostCode").val("00000");
//	$("#servicelist_SendMailAddr").val("详细地址");
//	$("#servicelist_SendMailProvince").val("地址省份");
//	$("#servicelist_SendMailCity").val("地址市");
//	$("#servicelist_SendMailDistrict").val("地址区");
//	$("#servicelist_SendMailBlock").val("地址街道");
//	
//	$("#info_identityType").val("10");
//	$("#info_idcardNo").val("23040419840831051X");
//	 //证件类型不能为空
//	 //证件号不能为空
//	 $("#info_name").val("姓名");
//	 //姓名不能为空
//	 $("#info_sex").val("男");
//	 //性别
//	 $("#info_nation").val("");
//	 //民族
//	 $("#info_politicalStatus").val("DY");
//	 //政治面貌
//	 $("#info_education").val("BK");
//	 //学历
//	 $("#info_nativePlace").val("");
//	 //	籍贯
//	 //birthday;//出生日期	
//	 //country;//国籍
//	 //homeAddress;//居住地址
//	 $("#info_linkPhone").val("11111");
//	 //联系电话不能为空
//	 $("#info_linkAddress").val("aaaaaaa");
//	 //联系地址不能为空
//	 $("#info_postCode").val("aaaaaaa");
//	//邮政编码
//	 $("#info_province").val("省份");
//	 $("#info_city").val("市");
//	 $("#info_county").val("县");
//	 $("#info_email").val("");
//	 //$("#ucid").val("15911");
//	 $("#zjhm").val("23040419840831051X");
//	 $("#sqrxm").val("aa");
//	 $("#lxrxm").val("aaaaa");
//	 $("#lxrphone").val("aaaaa");
//	 $("#userName").val("aaaaa");
}

function SendTypeCk(){
	var sendType = $("#servicelist_SendType").val();
	$("[data-group*=servicelist]").hide();
	$("[data-group=servicelist" + sendType + "]").show();
}

function multipleObjectType(){
	$("[data-group^=info]").hide();
	if(objectType.length  == 1){
		ObjectType();
	}else{
		$("#ObjectType option").remove();
		var l = objectType.split(","); 
		for(var v of l){
			if(v == "1"){
				$("#ObjectType").append("<option value='"+v+"'>服务对象类型：个人</option>");
			}else if(v == "2"){
				$("#ObjectType").append("<option value='"+v+"'>服务对象类型：项目</option>");
			}else if(v == "3"){
				$("#ObjectType").append("<option value='"+v+"'>服务对象类型：企业</option>");
			}
			$('#ObjectType').removeAttr("disabled");
		}
		$("#ObjectType_label").html("请选择：服务对象类型");
		ObjectType();
	}
}

function killScollBug(){
	$("input").on("blur", function () {
		$("body").removeClass("mui-focusin")
	})
}

//for objectType switch
function ObjectType(){
	selectObjectType = $("#ObjectType").val();
	$("[data-group*=info]").hide();
	$("[data-group=info" + selectObjectType + "]").show();
}

//show date
function dateShow(event){
	var name = event.data.para;
	dtpicker.show(function(e) {
	    $("#"+name).val(e.text);
	})
}

function validFiles(){
	var uploadedAll = true;;
	$(".nr").each(function(){
		var uploaded = false;
		$(this).children().each(function(){
			var $file = $(this).children(":file");
			console.log($file.attr("disabled"));
			if($file.get(0).files[0] && $file.attr("disabled")){
				uploaded = true;
				return false;
			}
		})
		if(!uploaded){
	    	mui.alert("请上传必要文件！");
	    	uploadedAll = false;
	    	return false;
	    }
//    	if(!$(this).get(0).files[0]){
//    		mui.alert("请上传必要附件！",function(){
//        		$(this).focus();
//    		});
//    	}
    })
    return uploadedAll;
}

function paraFormate(){
	var valid = true;
	var data = {};
    var results = $("#form").serializeArray();
    $.each(results,function(index,item){
        //文本表单的值不为空才处理
        //if(item.value || $.trim(item.value) == ""){
    		if($("#"+item.name).attr("required") == "required" && item.value == ""){
    			mui.alert($("#"+item.name).attr("placeholder") + "不能为空。",function(){
    				$("#"+item.name).focus();
				});
    			
    			valid = false;
    			return false;
    		}else if(item.name == "info_idcardNo" && $("#info_idcardNo").val().length != 15 && $("#info_idcardNo").val().length != 18){
    			mui.alert("证件号格式错误！",function(){
    				$("#"+item.name).focus();
				});
    			valid = false;
    			return false;
    		}else if(item.name == "zjhm" && $("#zjhm").val().length != 15 && $("#zjhm").val().length != 18){
    			mui.alert("证件号码格式错误！",function(){
    				$("#"+item.name).focus();
				});
    			valid = false;
    			return false;
    		}
//    		else if(item.name == "zjhm" && $("#zjhm").val().length != 15 && $("#zjhm").val().length != 18){
//    			mui.alert("证件号码格式错误！",function(){
//    				$("#"+item.name).focus();
//				});
//    			valid = false;
//    			return false;
//    		}
    		$("#ucid").val($("#zjhm").val());
    		if(!data[item.name]){
            	if(item.name.indexOf("_") < 0){
            		data[item.name]=item.value;
            	}else{
            		//metail_DOCUMENT_NAME
        			var pnameArray = item.name.split("_");
            		if(!data[pnameArray[0]]){
            			data[pnameArray[0]] = {};
            		}
            		data[pnameArray[0]][pnameArray[1]] = item.value;
            	}
            }else{
                //name属性相同的表单，值以英文,拼接
                data[item.name]=data[item.name]+','+item.value;
            }
        //}
    });
    
    
    data.metail = metail;
    
    console.log(JSON.stringify(data));
    if(valid){
    	return JSON.stringify(data);
    }else{
    	return null;
    }
}
function submit(){
	$('[style="display: none;"]').remove();
	if(!validFiles()){
		return;
	}
	var data = paraFormate();

	if(!data){
		return ;
	}
    $.ajax({
		type: "post",
		url: "webApply",
		data: '{"postdata":"' +encodeURI(data)+'"}',
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		contentType: "application/json",
		beforeSend:function(){//mui.showLoading("正在加载", "div");
			},
		complete:function(){//mui.hideLoading(function() {});
		},
		success: function(result) {
			if(result.state == 300){
				mui.alert("填写错误："+result.error);
			}else{
				mui.alert("提交成功",function(){
					window.history.back();
				});
			}
		}
	});
}


