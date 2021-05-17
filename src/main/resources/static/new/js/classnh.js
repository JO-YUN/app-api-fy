var menu_list = [];
var contentTops = [];

function tabScroll() {
/*	$('a').off().on('tap', function(e) {
		var viewTitle = $(this).attr('data-title');
		var obj = {
			'html': $(this).attr('data-html'),
			'title': viewTitle,
			'id': 'yyzx-list'
		}
		//通用
		titleNViewWebview(obj);
	})*/

	$('li').each(function(){
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

	//滚动
	/*var arr = [];
	$('#segmentedControlContents').scroll(function() {
		arr.length = 0;
		$('#segmentedControlContents>div').each(function() {
			arr.push(Math.abs($(this).offset().top));
			var min = arr[0],
				index = 0;
			for(var i = 1; i < arr.length; i++) {
				if(arr[i] < min) {
					min = arr[i];
					index = i;
				}
			}
			$('#segmentedControls .mui-control-item').eq(index).addClass('mui-active').siblings().removeClass('mui-active');
			$('#segmentedControls').scrollLeft($('.mui-control-item').width() * (index - 2));
		})
	})*/

	//点击头部
	/*$('#segmentedControls .mui-control-item').on('tap', function() {
		$(this).addClass('active').siblings().removeClass('active');
		var i = $(this).index();
		var h = 0;
		for(var j = 0; j < $('#segmentedControlContents>div').children().length; j++) {
			if(j < i) {
				h += $('#segmentedControlContents>div').eq(j).height();
			}
		}
		$('#segmentedControls').scrollLeft($('.mui-control-item').width() * (i - 2));
		$('#segmentedControlContents').animate({
			'scrollTop': h
		}, 0);
	})*/
}

function getAllApp() {

	$.ajax({
		type: "get",
		url: "../app/all",
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		data: {
			'cityCode': "230281",
			 'wx': '1'
		},
		beforeSend: function() {
			//mui.showLoading("正在加载", "div");
		},
		success: function(result) {
			if(result.errorcode == 1) {
				//获取应用类别分类				
				/*for(var i in result.data) {
					if(!isDuplicate(result.data[i].appType)) {
						menu_list.push(result.data[i].appType);
					}
				}

				//添加类别分类
				$('.aui-scroll-nav').empty();
				var menu_list_html = '';
				for(var i in menu_list) {
					if(i == 0) {
						menu_list_html += '<a href="#0" class="aui-scroll-item aui-crt"><div class="aui-scroll-item-icon"></div><div class="aui-scroll-item-text">' + menu_list[i] + '</div></a>';
					}else {
						menu_list_html += '<a href="#0" class="aui-scroll-item"><div class="aui-scroll-item-icon"></div><div class="aui-scroll-item-text">' + menu_list[i] + '</div></a>';
					}
				}
				$('.aui-scroll-nav').append(menu_list_html);*/

				//添加类别分类内容外层
/*				$('#apps').empty();
				var menu_content_html = '';
				for(var i in menu_list) {
					menu_content_html += '<div class="aui-scroll-content-item">'+
					'<h2 class="aui-scroll-content-title"><b id="1">'+ menu_list[i] +'</b></h2>'+
					'<section class="aui-grid-content">'+
						'<div class="aui-grid-row aui-grid-row-clear ul-' + menu_list[i] + '">'+
						'</div>'+
					'</section>'+
					'</div>';*/
				
/*					menu_content_html += '<div id="item' + i + '" class="mui-control-content">';
					menu_content_html += '	<div class="menu-title">';
					menu_content_html += '		<span></span>';
					menu_content_html += '		<span>' + menu_list[i] + '</span>';
					menu_content_html += '	</div>';
					menu_content_html += '	<ul class="mui-table-view mui-grid-view mui-grid-9 ul-' + menu_list[i] + '">';
					menu_content_html += '	</ul>';
					menu_content_html += '</div>';
				}*/
				$(".apptype").each(function(){
					$(this).empty();
				})
				//$('#apps').append(menu_content_html);
				$('#apps').append('<div class="miit-f"><div class="miit">ICP备案编号：<a  target="_blank" href="http://www.beian.miit.gov.cn/">黑ICP备18002311号-2</a ><div> 码上城市（黑龙江）数字科技有限公司 版权所有</div></div></div>');
				$('#apps').append('');
				//添加类别分类内容内层应用
				
				var user_token = "wxNew";
				for(var i of result.data) {
					var html = '';
					//内部应用
					if((i.openType == 1 || i.openType == 8)&& i.link.indexOf("paytype=orderHistory") < 0) {
						
						html += '<li class="aui-grid-row-item" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + user_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
						if(i.urltype == 2) {
							html += '<i class="aui-icon-large "><img src="'+ i.icon +'" alt=""></i>';
						} else {
							html += '<i class="aui-icon-large "><img src="../icon/' + i.icon + '" alt=""></i>';
						}
						//**
						html +='<p class="aui-grid-row-label">' + i.name + '</p></li>';
						/*html += '<li id="pay" class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + user_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
						html += '	<a>';
						
						if(i.urltype == 2) {
							html += '		<img src="' + i.icon + '">';
						} else {
							html += '		<img src="icon/' + i.icon + '">';
						}
						  
						html += '		<div class="mui-media-body">' + i.name + '</div>';
						html += '	</a>';
						html += '</li>';*/
					}else if(i.link != null && i.link.indexOf("paytype=orderHistory") > 0){
						var u_token = "wxSearchNew";
						html += '<li class="aui-grid-row-item" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + u_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
						if(i.urltype == 2) {
							html += '<i class="aui-icon-large "><img src="'+ i.icon +'" alt=""></i>';
						} else {
							html += '<i class="aui-icon-large "><img src="../icon/' + i.icon + '" alt=""></i>';
						}
						html +='<p class="aui-grid-row-label">' + i.name + '</p></li>';
						/*html += '<li id="pay" class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + u_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
						html += '	<a>';
						
						if(i.urltype == 2) {
							html += '		<img src="' + i.icon + '">';
						} else {
							html += '		<img src="icon/' + i.icon + '">';
						}
						  
						html += '		<div class="mui-media-body">' + i.name + '</div>';
						html += '	</a>';
						html += '</li>';*/
					}else {
						//外部应用
						html += '<li class="aui-grid-row-item" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + i.link + '">';
						if(i.urltype == 2) {
							html += '<i class="aui-icon-large "><img src="'+ i.icon +'" alt=""></i>';
						} else {
							html += '<i class="aui-icon-large "><img src="../icon/' + i.icon + '" alt=""></i>';
						}
						html +='<p class="aui-grid-row-label">' + i.name + '</p></li>';
						/*html += '<li  class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + i.link + '">';
						html += '	<a>';
						if(i.urltype == 2) {
							html += '		<img src="' + i.icon + '">';
						} else {
							html += '		<img src="icon/' + i.icon + '">';
						}
						html += '		<div class="mui-media-body">' + i.name + '</div>';
						html += '	</a>';
						html += '</li>';*/
					}

					$('.ul-' + i.appType).append(html);

					/*$('#pay').off().on('tap', function(e) {
						console.log(1111);
						var viewTitle = $(this).attr('data-title');
						var obj = {
							'html': $(this).attr('data-html'),
							'title': viewTitle,
							'id': 'pay'
						}
						//通用
						titleNViewWebview(obj);
					})	*/
				}
				tabScroll();
			}
		},
		complete: function() {
			//mui.hideLoading(function() {});
		}
	});
}

$(function() {
//	if(checkReferer()){
		getAllApp();
//	}else{
//		window.location.href = "./qrcode.html";
//	}
});


//去重
function isDuplicate(id) {
	for(var index in menu_list) {
		if(menu_list[index] == id) {
			return true;
		}
	}
	return false;
}

 
