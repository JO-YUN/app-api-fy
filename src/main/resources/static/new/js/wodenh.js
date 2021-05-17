var openid = null;
var country = null;
var province = null;
var city = null;
var sex = null;
var nickname = null;
var headimgurl = null;
var language = null;

$(function(){
	openid = sessionStorage.getItem("openid");
	country = sessionStorage.getItem("country");
	province = sessionStorage.getItem("province");
	city = sessionStorage.getItem("city");
	sex = sessionStorage.getItem("sex");
	nickname = sessionStorage.getItem("nickname");
	headimgurl = sessionStorage.getItem("headimgurl");
	language = sessionStorage.getItem("language");
	if(openid){
		$("#headimg").attr("src",headimgurl);
		$("#nickname").html(nickname);
		$("#mh-name").html('<span class="icon-comm"></span>国家<em class="mh-num">'+country+'</em>');
		$("#mh-adress").html('<span class="icon-comm"></span>省份<em class="mh-num">'+province+'</em>');
		$("#mh-dh").html('<span class="icon-comm"></span>城市<em class="mh-num">'+city+'</em>');
		$("#mh-yx").html('<span class="icon-comm"></span>语言<em class="mh-num">'+language+'</em>');
	}else{
		var data = getQueryVariable("data");;
		if(!data){
			window.location.href="../wxLoginnh";
		}else{
			var datastring = decodeURIComponent(decodeURI(data));
			var dataJson = JSON.parse(datastring);
			sessionStorage.setItem("openid",dataJson.openid);
			openid = dataJson.openid;
			sessionStorage.setItem("country",dataJson.country);
			country = dataJson.country;
			sessionStorage.setItem("province",dataJson.province);
			province = dataJson.province;
			sessionStorage.setItem("city",dataJson.city);
			city = dataJson.city;
			sessionStorage.setItem("sex",dataJson.sex);
			sex = dataJson.sex;
			sessionStorage.setItem("nickname",dataJson.nickname);
			nickname = dataJson.nickname;
			sessionStorage.setItem("headimgurl",dataJson.headimgurl);
			headimgurl = dataJson.headimgurl;
			sessionStorage.setItem("language",dataJson.language);
			language = dataJson.language;
			$("#headimg").attr("src",dataJson.headimgurl);
			$("#nickname").html(dataJson.nickname);
			$("#mh-name").html('<span class="icon-comm"></span>国家<em class="mh-num">'+country+'</em>');
			$("#mh-adress").html('<span class="icon-comm"></span>省份<em class="mh-num">'+province+'</em>');
			$("#mh-dh").html('<span class="icon-comm"></span>城市<em class="mh-num">'+city+'</em>');
			$("#mh-yx").html('<span class="icon-comm"></span>语言<em class="mh-num">'+language+'</em>');
		}
	}
	checkOpenidFy();
	getAllApp();
})

function checkOpenidFy(){
	$.ajax({
	type: "get", 
	url: "../realName/checkOpenidNh",
	data:{"openid":openid},
	dataType: "json", //json格式，后台返回的数据为json格式的。
	success: function(result) {
		if(result.errorcode == 1){
			$("#mh-rzxx").html('已认证');
			$("#mh-wsgr").attr("href","../nhsmrzUpdate.html");
		}else{
			//$("#mh-wsgr").attr("href","../nhsmrz.html");
			window.location.href="../nhsmrz.html";
		}
	}
	})
}

function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       return query.substring(5);
}

function getAllApp() {

	$.ajax({
		type: "get",
		url: "../app/all",
		dataType: "json",
		cache: false, // 缓存
		async: true, // 异步
		data: {
			'cityCode': "230281",
			 'wx': '1'
		},
		beforeSend: function() {
			// mui.showLoading("正在加载", "div");
		},
		success: function(result) {
			if(result.errorcode == 1) {
				
				var u_token = "wxSearchNew";
				$('#history').empty();
				var allhtml = '';
				for(var i of result.data) {
					var html = '';
					// 内部应用
					if(i.id == "6511ab0b4e3347eb93ea9edf29bdd3f9") {
						/*html += '<div class="col-xs-3"><div class="tubiaoti" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + user_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
						
						if(i.urltype == 2) {
							html += '<div class="tu"><img src="'+ i.icon +'" class="img-responsive"></div>';
						} else {
							html += '<div class="tu"><img src="../icon/' + i.icon + '" class="img-responsive"></div>';
						}
						// **
						html +='<div class="biaoti">' + i.name + '</div></div></div>';*/
						
						html+='<a id="mh-wsgr1" class=" g-next"  data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + u_token + "&IsMobile=2" + "&cityCode=" + "230281") + '"><span class="icon-comm"></span>缴费查询<em class="mh-num">查看</em></a>';
						/*
						 * html += '<li id="pay" class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + user_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
						 * html += ' <a>';
						 * 
						 * if(i.urltype == 2) { html += ' <img src="' +
						 * i.icon + '">'; } else { html += ' <img
						 * src="icon/' + i.icon + '">'; }
						 * 
						 * html += ' <div class="mui-media-body">' + i.name + '</div>';
						 * html += ' </a>'; html += '</li>';
						 */
					}
					/*else if(i.link != null && i.link.indexOf("paytype=orderHistory") > 0){
						var u_token = "wxSearchNew";
						html += '<div class="col-xs-3"><div class="tubiaoti" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + u_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
						if(i.urltype == 2) {
							html += '<div class="tu"><img src="'+ i.icon +'" class="img-responsive"></div>';
						} else {
							html += '<div class="tu"><img src="../icon/' + i.icon + '" class="img-responsive"></div>';
						}
						// **
						html +='<div class="biaoti">' + i.name + '</div></div></div>';
						
						 * html += '<li id="pay" class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + u_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
						 * html += ' <a>';
						 * 
						 * if(i.urltype == 2) { html += ' <img src="' +
						 * i.icon + '">'; } else { html += ' <img
						 * src="icon/' + i.icon + '">'; }
						 * 
						 * html += ' <div class="mui-media-body">' + i.name + '</div>';
						 * html += ' </a>'; html += '</li>';
						 
					}else {
						html += '<div class="col-xs-3"><div class="tubiaoti" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + i.link + '">';
						if(i.urltype == 2) {
							html += '<div class="tu"><img src="'+ i.icon +'" class="img-responsive"></div>';
						} else {
							html += '<div class="tu"><img src="../icon/' + i.icon + '" class="img-responsive"></div>';
						}
						// **
						html +='<div class="biaoti">' + i.name + '</div></div></div>';
						
						 * html += '<li  class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + i.link + '">';
						 * html += ' <a>'; if(i.urltype == 2) { html += '
						 * <img src="' + i.icon + '">'; } else { html += '
						 * <img src="icon/' + i.icon + '">'; } html += '
						 * <div class="mui-media-body">' + i.name + '</div>';
						 * html += ' </a>'; html += '</li>';
						 
					}*/
					allhtml += html;
				}
				$('#history').append(allhtml);
				tabScroll();
			}
		},
		complete: function() {
			// mui.hideLoading(function() {});
		}
	});
}

	function tabScroll() {
		$('a').each(function(){
			$(this).off().on('click', function(e) {
				//mui.showLoading("正在加载", "div");
				if($(this).attr('data-html') != 'null' && $(this).attr('data-html') != '') {
					if($(this).attr('data-open') == 2)
					{
						var viewTitle = $(this).text().trim();
					    var obj = {
						'html': $(this).attr('data-html'),
						'title': viewTitle,
						'id': 'yyzx-list-' + $(this).attr('data-id')
					       }
					//通用
					    titleNViewWebview(obj);
					}
					if($(this).attr('data-open') == 1 || $(this).attr('data-open') ==9){
						$.ajax({
			    		type: "get",
			    		//正式环境
			    		url: $(this).attr('data-html'),
			    		//url:apis.ccBPay,
			    		dataType: "json",
			    		cache: false, //缓存
			    		async: true, //异步
			    		contentType: "application/json",
			    		success: function(result) {
			    		    if(result.data!=null && result.data!=''){		    	
			    		    	console.log(result.data);
			    		      //异步
			    		         if(result!=null&&result!=''){
			    		        	 window.location.href=result.data;
			    		         }
			    		    }else{
			    		    	//mui.hideLoading(function() {});
			    		       //mui.toast("系统内部异常,请联系管理员！");
			    		    }
			    		       }});
					}else if($(this).attr('data-open') == 8){
						//mui.hideLoading(function() {});
						//mui.alert("医保缴费业务提示：由于集中缴费业务量急剧增加，引发系统响应超时，部分客户扣款成功后缴费失败费用退回。系统将于10月12日晚进行升级维护，请医保缴费客户进入缴费记录查询界面查询缴费结果，如显示“已全额退费”，请于10月13日后重新缴费，为您带来的不便敬请谅解！");
					}
				} else {
					//mui.$.alert('该应用暂未开通', '提示', '关闭', function() {
						//console.log('返回xcbd.html');
					//})
					//mui.hideLoading(function() {});
	                 alert("该应用暂未开通");
				}
		})
		})
	}