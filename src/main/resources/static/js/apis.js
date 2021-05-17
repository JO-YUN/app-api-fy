//app当前版本
var app_version = '2.0.1.007';
//var base_api_url = '../';
//var base_api_url = 'http://192.168.21.68:8080/';
//var base_api_url ='http://192.168.0.168/'
//var base_api_url = 'http://192.168.11.24/';
//var base_api_url = 'http://www.data0452.com/';
var base_api_url = 'http://'+window.location.host+'/';
var affairs_api_url = 'http://60.14.32.151/root_service/';
var affairs_web_url ='http://60.14.32.151/hz_root/';
//var base_api_url = 'http://39.105.55.139:9090/';
var apis = {};
var url_obj = {};
var my_img_url = '';
$(function() {
	my_img_url = base_api_url;

	apis = {
	    //iOS客户端appkey：
	    'affairs_ios_appkey':'77C77D41-8254-4E5A-9510-E5EAD934F507',
	    //Android客户端appkey：93523B8D-64D9-4962-B138-37C8477FA0EC
        'affairs_andr_appkey':'93523B8D-64D9-4962-B138-37C8477FA0EC',
	    //热门审批
	    'affairs_hot': affairs_api_url+'v2/projects/hot_items',
        //登录
        'affairs_login': affairs_api_url+'v2/account/login',
        //审批事项
        'affairs_items': affairs_web_url+'basicform/phonebasic_declaration.html',
        //附件列表
        'affairs_attlist':  affairs_api_url+'v2/approvals/attachs',
        //上传附件
        'affairs_attup' : affairs_api_url+'v2/approvals/upload_attach',
        //首页
        'affairs_index' : 'http://60.14.32.151/view/login/index_1.html',
        //短信
        'affairs_sms' : affairs_api_url+'v2/extra/authenticode',
        //注册
        'affairs_register' :  affairs_api_url+'v2/account/register',
        //省
        'affairs_province' : affairs_api_url + 'v2/reg_areas/provinces',
        //城市
        'affairs_city' : affairs_api_url + 'v2/reg_areas/cities',
        //县
        'affairs_district' : affairs_api_url + 'v2/reg_areas/districts',
        //厅局
        'affairs_depart' : affairs_api_url + 'v2/departments/depart_special',
        'affairs_departN' : affairs_api_url + 'v2/departments/departments',
        //审批事项列表
        'affairs_list': affairs_api_url + 'v2/projects/items',
        //个人中心
        'affairs_personal': affairs_api_url + 'v2/personal/my_profile',
        //查看获取方式
        'affairs_gain' :  affairs_api_url + 'v2/approvals/gain_type',
        //提交获取方式
        'affairs_save' :  affairs_api_url + 'v2/approvals/save_gain',
        //提交审批
        'affairs_submit' :  affairs_api_url + 'v2/approvals/submit_item',
        //area_id
        'affairs_area_id' : "70074949-F522-46DB-B4C6-97D46F5DC518",
        //我的申请
        'affairs_personal_items' :  affairs_api_url + 'v2/personal/items',
        //我的咨询
        'affairs_consults' :  affairs_api_url + 'v2/personal/consults',
        //删除申请
        'affairs_remove' :  affairs_api_url + 'v2/approvals/remove_item',
        //撤销
        'affairs_revoke' :  affairs_api_url + 'v2/approvals/revoke_item',
        //指南
        'affairs_guide' : affairs_web_url +  'basicform/formoneoff.htm',
        //找回方式
        'affairs_protect' : affairs_api_url + 'v2/account/password_protect',
        //手机验证
        'affairs_phone' : affairs_api_url + 'v2/account/password_phone',
        //密保问题
        'affairs_qu' : affairs_api_url + 'v2/account/password_question',
        //答案
        'affairs_an' : affairs_api_url + 'v2/account/forget_answer',
        //新密码
        'affairs_repwd' : affairs_api_url + 'v2/account/reset_password',

		//轮播图
		'img': base_api_url + 'app-api/img',
		//TOP广告
		'third': base_api_url + 'app-api/app/third',
		//广告
		'advertising': base_api_url + 'app-api/app/advertising',
		//头部系统应用
		'app_first': base_api_url + 'app-api/app/first',
		//中间应用
		'app_sys': base_api_url + 'app-api/app/sys',
		//获取所有App
		'app_all': base_api_url + 'app-api/app/all',
		//登陆状态下所有app
		'app': base_api_url + 'app-api/app',
		//浏览记录
		'applog_add': base_api_url + 'app-api/applog/add',
		//缴费
		'pay': base_api_url + 'app-api/bmjf/pay',

		//登录
		'login': base_api_url + 'app-api/login',
		//登出
		'logout': base_api_url + 'app-api/logout',
		//版本
		'ver': base_api_url + 'app-api/ver/list',
		//意见反馈
		'fankui': base_api_url + 'app-api/advice/add',
		//修改密码
		'updatePwd': base_api_url + 'app-api/password/updatePassword.do',
		//验证码
		'code': base_api_url + 'app-api/code/get',
		//我的-个人信息
		'infor_fetchInfor': base_api_url + 'app-api/infor/fetchInfor',
		//我的-个人信息修改
		'infor_updateInfor': base_api_url + 'app-api/infor/updateInfor',
		//检验手机号是否注册过
		'reg_verificationPhone': base_api_url + 'app-api/reg/verificationPhone',

		//通知公告(返回全部的通知公告)
		'zxtzgg_queryAllTzgg': base_api_url + 'app-api/zxtzgg/queryAllTzgg',
		//通知公告(返回最新的通知公告)
		'zxtzgg_getzxtzgg': base_api_url + 'app-api/zxtzgg/getzxtzgg',
		//通知公告详情
		'zxtzgg_queryTzggById': base_api_url + 'app-api/zxtzgg/queryTzggById',
		//我的
//		'newwdxx': base_api_url + 'app-api/newwdxx/newwdxxall.do',

		//发送短信验证码
		'daYU_sendSMSCode': base_api_url + 'app-api/daYU/sendSMSCode.do',
		//注册
		'reg_add': base_api_url + 'app-api/reg/add',
		//重置密码
		'reg_resetPwd': base_api_url + 'app-api/reg/resetPwd',
		//上传图片
		'upload': base_api_url + 'app-api/FileService/upload',
		//预览图片
		'showImg': base_api_url + 'app-api/FileService/showImg',
		//上传图片
		'updatePic': base_api_url + 'app-api/infor/updatePic',
		//APP上传图片
		'uploadPic': base_api_url + 'app-api/FileService/uploadPic',

		//省、市、区（三级联动）手动
		'infor_fetchSiteInfor': base_api_url + 'app-api/infor/fetchSiteInfor',
		//省、市、区（三级联动）自动
		'getAddresses': base_api_url + 'app-api/autocity/getAddresses',

		//微信是否经过授权
		'checkOpenId': base_api_url + 'app-api/OauthAppweixin/checkOpenId',
		//App授权后，登录并绑定接口
		'bindPhone': base_api_url + 'app-api/OauthAppweixin/bindPhone',
		//支付宝授权
		'getUrlForSJ': base_api_url + 'app-api/alipay/getUrlForSJ',
		//支付宝授权信息
		'getAlipayInfo': base_api_url + 'app-api/alipay/getAlipayInfo',
		//支付宝手机号绑定
		'addAlipayUser': base_api_url + 'app-api/reg/addAlipayUser',

		 //调用sdk
		 'ccBPay':base_api_url + 'app-api/ccb/pay?paytype=01006&IsMobile=1'
	};


})

function findAtttype(key){
    for(var t in  atttype){
        if(key == atttype[t].key){
            return  atttype[t].value;
        }
    }
    return '';
}

function getlocalStorage(_key) {
	return window.localStorage.getItem(_key);
}

function setlocalStorage(_key, _val) {
	return window.localStorage.setItem(_key, _val);
}

// 带标题栏控件的Webview窗口
var webview = null;


function titleNViewWebview(obj) {
	//创建窗口
	//在窗口中增加scalable:true,属性可支持缩放
	/*webview = plus.webview.create(obj.html, obj.id, {
		scalable: true,
		'titleNView': {
			'backgroundColor': '#1f7ebe',
			'titleText': obj.title,
			'titleColor': '#FFFFFF',
			//'autoBackButton': true,
			'buttons': [{
				text: '返回',
				float: 'left',
				fontSize: '14px',
				onclick: function() {
					plus.webview.getWebviewById(obj.id).close();
				}
			}]
		}
	},obj.extras );
	//解决缩放问题

	//webview.show('slide-in-right');
	webview.addEventListener('titleUpdate', function(e) {
		//console.log('页面跳转');
		if(window.plus) {
			onNetChange();
		}
	});
	
	return webview;*/
	window.location.href=obj.html;
	
}

//获取当前设备的网络类型
function plusReady() {
	var types = {};
	types[plus.networkinfo.CONNECTION_UNKNOW] = "Unknown connection";
	types[plus.networkinfo.CONNECTION_NONE] = "None connection";
	types[plus.networkinfo.CONNECTION_ETHERNET] = "Ethernet connection";
	types[plus.networkinfo.CONNECTION_WIFI] = "WiFi connection";
	types[plus.networkinfo.CONNECTION_CELL2G] = "Cellular 2G connection";
	types[plus.networkinfo.CONNECTION_CELL3G] = "Cellular 3G connection";
	types[plus.networkinfo.CONNECTION_CELL4G] = "Cellular 4G connection";
	//console.log("Network: " + types[plus.networkinfo.getCurrentType()]);
	webview.show('slide-in-right');
}

function onNetChange() {
	var nt = plus.networkinfo.getCurrentType();
	switch(nt) {
		case plus.networkinfo.CONNECTION_ETHERNET:
		case plus.networkinfo.CONNECTION_WIFI:
			//console.log("当前网络为WiFi");
			webview.show('slide-in-right');
			break;
		case plus.networkinfo.CONNECTION_CELL2G:
		case plus.networkinfo.CONNECTION_CELL3G:
		case plus.networkinfo.CONNECTION_CELL4G:
			//console.log("当前网络非WiFi");
			webview.show('slide-in-right');
			break;
		default:
			webview.show('slide-in-right');
			//console.log("当前没有网络");
			mui.alert('请检查网络连接！！！', '提示', function() {

			});
			break;
	}
}

function guid() {
	function S4() {
		return(((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	}
	return(S4() + "-" + S4() + "-" + S4() + "-" + S4());
}