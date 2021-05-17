mui.init();

		//设置默认打开首页显示的子页序号；
		var Index = 0;
		//把子页的路径写在数组里面
		var subpages = ['page/home.html', 'page/app.html', 'page/my.html'];
		var subpage_style = {
			top: '44px',
			bottom: '100px'
		}

		var activeTab = subpages[Index];
		var title = document.querySelector(".mui-title");
		//选项卡点击事件
		mui('.mui-bar-tab').on('tap', 'a', function(e) {
			var targetTab = this.getAttribute('href');
			if(targetTab == "we"){
				mui.alert(" 鹤岗市公安局交警支队负责全市道路交通安全管理工作。主要职责是维护道路交通秩序，管理车辆和驾驶人，处理道路交通事故，开展交通安全宣传教育，保障全市道路交通安全、有序、畅通。","关于我们");
			}
			if(targetTab == "phone"){
				window.location.href = "tel://04683355196";
			}
			if(targetTab == "message"){
				//mui.alert("敬请期待！","系统提示");
				window.location.href = "./location.html";
			}
		});
		
		$(function() {
			var url = getUrlParam("url");
			var Tprt_Mode = getUrlParam("Tprt_Mode");
			var Tprt_Parm = getUrlParam("Tprt_Parm");
			var Bsn_Data = getUrlParam("Bsn_Data");
			if(url != null && Tprt_Mode != null && Tprt_Parm != null && Bsn_Data != null){
				var exportForm = $('<form action="'+url+'" method="post">\
	    			    <input type="hidden" name="Tprt_Mode" value="'+Tprt_Mode+'"/>\
	    			    <input type="hidden" name="Tprt_Parm" value="'+Tprt_Parm+'"/>\
	    			    <input type="hidden" name="Bsn_Data" value="'+Bsn_Data+'"/>\
	    			    </form>');
				$(document.body).append(exportForm);
				exportForm.submit();
			}else{
				getAllApp();
			}
		});
		
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
			
		},
		success: function(result) {
			if(result.errorcode == 1 && result.data.lenghth >0) {
	
				//添加类别分类内容内层应用
				
				var user_token = "wxhg";
				for(var i of result.data) {
					var html = '';
					//内部应用
					if(i.openType == 1) {
						
						
						html += '<button id="pay" class="mui-button clickin" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + user_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">点击进入';
				
						html += '</button>';
						
						html += '<button id="search" class="mui-button clickin" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + "wxSearchhg" + "&IsMobile=2" + "&cityCode=" + "230281") + '">点击查询';
						
						html += '</button>';
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

					$('#clickindiv').append(html);

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
			}else{
				var html = '';
				html += '<button id="expect" class="mui-button clickin" data-open="" data-flag="" data-id="" data-html="' + "http://"+"/" + '">点击进入';
				
				html += '</button>';
				html += '<button id="search" class="mui-button clickin" data-open="1' + '" data-flag="'  + '" data-id="' + '" data-html="' + base_api_url+"/" + ("app-api/bmjf/pay?paytype=010010" + "&token=" + "wxSearchhg" + "&IsMobile=2" + "&cityCode=" + "230281") + '">点击查询';
				
				html += '</button>';

				$('#clickindiv').append(html);
				$('#expect').off().on('tap', function(e) {
					mui.alert("系统升级中敬请期待！","公告");
				})
				tabScroll();
			}
		},
		complete: function() {
			
		}
	});
}
		
		function tabScroll() {
			
			$('#pay,#search').off().on('tap', function(e) {
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
				    			if(result.data.url == null){
				    				window.location.href = result.data;
				    			}else{
					    		    if(result.data!=null && result.data!=''){		    	
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
					    		      
					    		       
					    		    }else{
					    		       mui.toast("系统内部异常,请联系管理员！");
					    		    }
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

		

			
		}
		
		function getUrlParam(name) {
			  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			  var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			  if (r != null) return decodeURI(r[2]); return null; //返回参数值
		}



