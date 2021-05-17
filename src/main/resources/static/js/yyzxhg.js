var menu_list = [];
var contentTops = [];

function tabScroll() {
	/*$('li').off().on('tap', function(e) {
		var viewTitle = $(this).attr('data-title');
		var obj = {
			'html': $(this).attr('data-html'),
			'title': viewTitle,
			'id': 'yyzx-list'
		}
		//通用
		titleNViewWebview(obj);
	})*/

	$('li').off().on('tap', function(e) {
		
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
				if($(this).attr('data-open') == 1){
					
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
		    		        	console.log(result.data.tprt_Mode);
		    		        	console.log(result.data.tprt_Parm);
		    		        	console.log(result.data.bus_Data);
		    		        	var exportForm = $('<form action="'+result.data.url+'" method="post">\
		    		        			    <input type="hidden" name="Tprt_Mode" value="'+result.data.tprt_Mode+'"/>\
		    		        			    <input type="hidden" name="Tprt_Parm" value="'+result.data.tprt_Parm+'"/>\
		    		        			    <input type="hidden" name="Bsn_Data" value="'+result.data.bsn_Data+'"/>\
		    		        			    </form>');
			        			$(document.body).append(exportForm);
			        			exportForm.submit(); // 表单提交
			        			//exportForm.remove();
		    		         }
		    		       
		    		    }else{
		    		       mui.toast("系统内部异常,请联系管理员！");
		    		    }
		    		       }});
				}
			} else {
				/*mui.$.alert('该应用暂未开通', '提示', '关闭', function() {
                                              								//console.log('返回xcbd.html');
                                              							})*/
                 alert("该应用暂未开通");
			}
		
	})

	//滚动
	var arr = [];
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
	})

	//点击头部
	$('#segmentedControls .mui-control-item').on('tap', function() {
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
	})
}

function getAllApp() {

	$.ajax({
		type: "get",
		url: "app/allhg",
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		data: {
			'cityCode': "230281",
            'apple':'1'
		},
		beforeSend: function() {
			mui.showLoading("正在加载", "div");
		},
		success: function(result) {
			if(result.errorcode == 1) {
				//获取应用类别分类				
				for(var i in result.data) {
					if(!isDuplicate(result.data[i].appType)) {
						menu_list.push(result.data[i].appType);
					}
				}

				//添加类别分类
				$('#segmentedControls>div').empty();
				var menu_list_html = '';
				for(var i in menu_list) {
					if(i == 0) {
						menu_list_html += '<a class="mui-control-item mui-active"><span>' + menu_list[i] + '</span></a>';
					} else {
						menu_list_html += '<a class="mui-control-item"><span>' + menu_list[i] + '</span></a>';
					}
				}
				$('#segmentedControls>div').append(menu_list_html);

				//添加类别分类内容外层
				$('#segmentedControlContents').empty();
				var menu_content_html = '';
				for(var i in menu_list) {
					menu_content_html += '<div id="item' + i + '" class="mui-control-content">';
					menu_content_html += '	<div class="menu-title">';
					menu_content_html += '		<span></span>';
					menu_content_html += '		<span>' + menu_list[i] + '</span>';
					menu_content_html += '	</div>';
					menu_content_html += '	<ul class="mui-table-view mui-grid-view mui-grid-9 ul-' + menu_list[i] + '">';
					menu_content_html += '	</ul>';
					menu_content_html += '</div>';
				}
				$('#segmentedControlContents').append(menu_content_html);

				//添加类别分类内容内层应用
				
				var user_token = "wxhg";
				for(var i of result.data) {
					var html = '';
					//内部应用
					if(i.openType == 1) {
						
						
						html += '<li id="pay" class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + user_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
						html += '	<a>';
						
						if(i.urltype == 2) {
							html += '		<img src="' + i.icon + '">';
						} else {
							html += '		<img src="icon/' + i.icon + '">';
						}
						  
						html += '		<div class="mui-media-body">' + i.name + '</div>';
						html += '	</a>';
						html += '</li>';
					} else {
						
						//外部应用
						html += '<li  class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + i.link + '">';
						html += '	<a>';
						if(i.urltype == 2) {
							html += '		<img src="' + i.icon + '">';
						} else {
							html += '		<img src="icon/' + i.icon + '">';
						}
						html += '		<div class="mui-media-body">' + i.name + '</div>';
						html += '	</a>';
						html += '</li>';
						
						
					
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
			mui.hideLoading(function() {});
		}
	});
}

$(function() {
	getAllApp();
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


 
