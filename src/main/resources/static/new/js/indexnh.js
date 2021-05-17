
	/* ---- */
	//新闻置顶
	function newTop(picUrl,id) {
		var html =
			'<div class="swiper-slide"><a href="query_news_nh.html?id='+id+'"><img src="' + picUrl + '"/></a></div>';
		return html;
	}
	// 中间两张图片
	function newTopTow(picUrl1, picUrl2, title2,id2) {
		var html =
			
			'<a href="query_news_nh.html?id='+id2+'">' +
			'	<div>' +
			'		<div class="col-xs-12 ">' +
			'			<div class="xwlb-zw-sanlie">' + title2 + '</div>' +
			'		</div>' +
			'		<div class="col-xs-6 xwlbfgx-sanlie" style="padding-right: 2px;padding-left: 10px;">' +
			'			<img src="' + picUrl1 + '" >' +
			'		</div>' +
			' 		<div class="col-xs-6 xwlbfgx-sanlie" style="padding-right: 10px;padding-left: 2px;">' +
			'			<img src="' + picUrl2 + '" >' +
			'		</div>' +
			'	</div>' +
			'</a>';
		return html;
	}
	// 下边列表
	function newTopThree(picUrl, title, releaseDate,id,intro) {
		var html =
			'<li>' +
			'<div>' +
			'	<div class="col-xs-5">' +
			'		<a href="query_news_nh.html?id='+id+'"><img src="' + picUrl + '" class="img-responsive"></a>' +
			'   </div>' +
			' <div class="col-xs-7 xwlbfgx">' +
			'	<div class="xwlb-zw">' +
			'		<a href="query_news_nh.html?id='+id+'">' + title + '</a>' +
			'		<a href="query_news_nh.html?id='+id+'"><p class="xwlb-db1">' + intro + '</p></a>' +
			'	</div>' +
			'	<div class="xwlb-db">' + releaseDate + '</div>' +
			' </div>' +
			'</div>' +
			'</li>';
		return html;
	}
	// 热门服务1
	function fuwu1(url,link){
		var html =
			'<div class="fuwu1">' +
			'	<a href="'+link +'"><img src="'+ url+'" class="img-responsive"></a>' +
			'</div>';
		return html;
	}
	// 热门服务2
	function fuwu2(url1,url2,link1,link2){
		var html =
		'<div class="fuwu2">' +
			'<div class="class1">' +
			'	<a href="'+link1 +'"><img src="'+ url1+'" class="img-responsive"></a>' +
			'</div>' +
			'<div class="class2" style="margin-top: 5px;">' +
			'	<a href="'+link2 +'"><img src="'+ url2+'" class="img-responsive"></a> ' +
			'</div>'+
		'</div>';
		return html;
	}
	function listApps(){
		$.ajax({
		type: "post", // 请求的方式，也有POST请求
		url: "../listNHApps", // 请求地址
		data: {
		}, 
		dataType: "json",
		success: function(result) {
			var dataObj = result.data;
			var obj = {};
			var key = "LINK";
			var value = "#"
			if(result.errorcode == 1){
				for(var i =0; i< dataObj.length; i++){
					if (!dataObj[i].hasOwnProperty('LINK')) {
						eval("dataObj[i]." + key + "='" + value + "'");
					}
					
					// AD
					if(dataObj[i].LINKTYPE == "2"){
						var ADhtml ="";
						ADhtml += '<div class="swiper-slide"><a href="'+dataObj[i].LINK +'"><img src="'+ dataObj[i].URL+'"/></a></div>';
						$("#AD").append(ADhtml);
					// top
					}else if(dataObj[i].LINKTYPE == "1"){
						var tophtml ="";
						tophtml += '<div class="swiper-slide"><a href="'+dataObj[i].LINK +'"><img src="'+ dataObj[i].URL+'"/></a></div>';
						$("#top").append(tophtml);
					}
					console.log(dataObj[i].LINK);
				}
				initSwiper();
				
				
				// 热门服务
				var rmfw = [];
				for(var i =0; i< dataObj.length; i++){
					
					if(dataObj[i].LINKTYPE == "3"){
						
						var link ="";
						/*
						 * if(){ link=dataObj[i].LINK }else{ link ="#" }
						 */
						rmfw.push(dataObj[i]);
					}
				}
				if(rmfw.length > 0){
					$("#fuwu").append(fuwu1(rmfw[0].URL,rmfw[0].LINK));
					$("#fuwu").append(fuwu2(rmfw[1].URL,rmfw[2].URL,rmfw[1].LINK,rmfw[2].LINK));
				}
			}else{
				console.log("sys error");
			}
		}
		})
	}
	//标题截取字
	 function titleSubstr (str){
	    	if(str != null){
	    		if(str . length > 20){
	    			str = str.substring(0,20) + '...';
	    		}
	    	}else{
	    		str = '';
	    	}
	    	return str;	
	    }
	// 截取字
    function introSubstr (str){
    	if(str != null){
    		if(str . length > 50){
    			str = str.substring(0,35) + '...';
    		}
    	}else{
    		str = '';
    	}
    	return str;	
    }
    // 年月
    function yearsSubstr (str){
    	if(str != null){
    		str = str.substring(0,4);
    	}else{
    		str = '';
    	}
    	
    	return str;	
    }
    function monthsSubstr (str){
    	if(str != null){
    		str = str.substring(5,7);
    	}else{
    		str = '';
    	}
    	return str;	
    }
    // 日
    function daysSubstr (str){
    	if(str != null){
    		str = str.substring(8,10);
    	}else{
    		str = '';
    	}
    	return str;	
    }
	// AJAX方法
	function ajax(pageNo,pageSize,homePages){
		$.ajax({
			type: "post", // 请求的方式，也有POST请求
			url: "../newsInfoNH/queryNewsInfoListPage", // 请求地址
			data: {
				pageNo: pageNo,
				pageSize: pageSize,
				homePages:homePages
			}, // data是传给后台的字段，支取5条数据
			dataType: "json", // json格式，后台返回的数据为json格式的。
			success: function(result) {
				var dataObj = result.data; // 返回的result为json格式的数据
				// console.log(dataObj)
				if(dataObj == ''){
					$("#newsId").hide();// 隐藏div
					$("#newTopOne").hide();// 隐藏div
					$("#newTopTow").hide();// 隐藏div
				}
				// 置顶组
				var topList = new Array();
				// 列表组
				var topTowList = new Array();
				// 遍历
				for(var i = 0; i < dataObj.length; i++) {
					if( dataObj[i].topState == "Y"){
						topList.push(dataObj[i]);
					}else{
						topTowList.push(dataObj[i]);
					}
				}
				// 置顶
				 // 数组里除去第一个以外的数据
			/*
			 * var ary=[1,2,3,4]; var a=ary.shift() console.log(a)
			 * console.log(ary)
			 */
				
				// 第一条
				if(topList.length > 0){
					// 有数据,显示轮播图片
					$("#newTopOne").show()
					// 数据是几条,多条取两条
					var arrPic = topList[0].list_file_pic;
					var title = titleSubstr(topList[0].title); // 标题
					if(arrPic != null && arrPic.length > 0){
						for(var i = 0; i < arrPic.length; i++) {
							var id = topList[0].id
							var picUrl = arrPic[i].url;
							$("#newTop").append(newTop(picUrl,id));
							//$("#topTittle").html(title);
							// 轮播图
							initSwiper()
						}
					}else{
						var picUrl = '../img/news.png';
						var id = topList[0].id
						$("#topTittle").html(titleSubstr(topList[0].title));
						$("#newTop").append(newTop(picUrl,id));
						// 轮播图
						initSwiper()
					}
					// 第二条
					if(topList.length > 1){
						 $("#newTopTow").show()// 显示div
						if(topList[1] != null){
							var arrPic = topList[1].list_file_pic;
							if(arrPic != null && arrPic.length > '1') {
								var picUrl1 = topList[1].list_file_pic[0].url;
								var picUrl2 = topList[1].list_file_pic[1].url;
							}else{
								if(arrPic != null && arrPic.length == '1') {
									var picUrl1 = topList[1].list_file_pic[0].url;
									var picUrl2 ='../img/news.png';
								}else{
									var picUrl1='../img/news.png';
									var picUrl2 ='../img/news.png';
								}
							}
							
							var title2 =titleSubstr(topList[1].title);
							var id2 = topList[1].id;
							$("#newTopTow").append(newTopTow(picUrl1, picUrl2, title2,id2));
						}
					}else{
						$("#newTopTow").hide();// 隐藏div
					}
				}else{
					$("#newTopOne").hide();// 隐藏div
				}
				// 列表组
				if(topTowList.length > 0){
					for(var i = 0; i < topTowList.length; i++) {
						var id = topTowList[i].id;// ID
						var topState = topTowList[i].topState; // 置顶状态
						var title = titleSubstr(topTowList[i].title); // 标题
						var intro =introSubstr(topTowList[i].context);// 简介截取
						var releaseDate = topTowList[i].releaseDate;// 年
						var yearStr = yearsSubstr(topTowList[i].releaseDate);// 年
						var monthStr = monthsSubstr(topTowList[i].releaseDate);// 月
						var daysStr = daysSubstr(topTowList[i].releaseDate);// 日
						var pic = topTowList[i].list_file_pic;
						if(pic != null && pic.length > '0'){
							var picUrl = pic[0].url;
						}else{
							var picUrl ='../img/news.png';
						}
						$("#newTopThree").append(newTopThree(picUrl, title, releaseDate,id,intro));
					}
					
				}

				
			},
			error: function() {
				alert("获取服务器失败");
			}
		});
	}
	
	$(function(){
		var pageNo = '1';
		var pageSize = '5';
		var homePages = '0';
		listApps();
		ajax(pageNo,pageSize,homePages);
		// alert("自动加载");
		getAllApp();
		
	});
	/* ---- */
	
	function initSwiper(){
		var mySwiper = new Swiper ('.swiper-container', {
	        // 轮播图的方向，也可以是vertical方向
	        direction:'horizontal',
	        // 播放速度
	        loop: true,
	        // 自动播放时间
	        autoplay:false,
	        // 播放的速度
	        speed:1000,
	        // 如果需要分页器，即下面的小圆点
	      pagination: {el: '.swiper-pagination'},
          autoplayDisableOnInteraction : false
	      });
		  
		  var mySwiper1 = new Swiper ('.swiper-container1', {
		          // 轮播图的方向，也可以是vertical方向
		          direction:'horizontal',
		          // 播放速度
		          loop: true,
		          // 自动播放时间
		          autoplay:false,
		          // 播放的速度
		          speed:3000,
		          // 如果需要分页器，即下面的小圆点
		        pagination: {el:'.swiper-pagination1'},
		        autoplayDisableOnInteraction : false
		        });
	}
	
	function tabScroll() {
			$('.tubiaoti').each(function(){
				$(this).off().on('click', function(e) {
					
					// mui.showLoading("正在加载", "div");
					if($(this).attr('data-html') != 'null' && $(this).attr('data-html') != '') {

						if($(this).attr('data-open') == 2)
						{
							var viewTitle = $(this).text().trim();
						    var obj = {
							'html': $(this).attr('data-html'),
							'title': viewTitle,
							'id': 'yyzx-list-' + $(this).attr('data-id')
						       }
						// 通用
						    titleNViewWebview(obj);
						}
						if($(this).attr('data-open') == 1 || $(this).attr('data-open') ==9){
							$.ajax({
				    		type: "get",
				    		// 正式环境
				    		url: $(this).attr('data-html'),
				    		// url:apis.ccBPay,
				    		dataType: "json",
				    		cache: false, // 缓存
				    		async: true, // 异步
				    		contentType: "application/json",
				    		success: function(result) {
				    		    if(result.data!=null && result.data!=''){		    	
				    		    	console.log(result.data);
				    		      // 异步
				    		         if(result!=null&&result!=''){
				    		        	 window.location.href=result.data;
				    		         }
				    		    }else{
				    		    	// mui.hideLoading(function() {});
				    		       // mui.toast("系统内部异常,请联系管理员！");
				    		    }
				    		       }});
						}else if($(this).attr('data-open') == 8){
							// mui.hideLoading(function() {});
							// mui.alert("医保缴费业务提示：由于集中缴费业务量急剧增加，引发系统响应超时，部分客户扣款成功后缴费失败费用退回。系统将于10月12日晚进行升级维护，请医保缴费客户进入缴费记录查询界面查询缴费结果，如显示“已全额退费”，请于10月13日后重新缴费，为您带来的不便敬请谅解！");
						}
					} else {
						// mui.$.alert('该应用暂未开通', '提示', '关闭', function() {
							// console.log('返回xcbd.html');
						// })
						// mui.hideLoading(function() {});
		                 alert("该应用暂未开通");
					}
			})
				
			})

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
					
					var user_token = "wxNew";
					var num=0;
					var allhtml="";
					$('#apps').empty();
					for(var i of result.data) {
						num++;
						if(num >8){
							break;
						}
						var html = '';
						// 内部应用
						
						if(num == 1 ){
							html+='<div class="fenlei row">'
						}
						
						if(num == 6){
							html+='</div><div class="fenlei1 row">'
						}
						if((i.openType == 1 || i.openType == 8) && i.link.indexOf("paytype=orderHistory") < 0 && i.id != "6511ab0b4e3347eb93ea9edf29bdd3f9" ) {
							
							html += '<div class="col-xs-3"><div class="tubiaoti" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + user_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
							if(i.urltype == 2) {
								html += '<div class="tu"><img src="'+ i.icon +'" class="img-responsive"></div>';
							} else {
								html += '<div class="tu"><img src="../icon/' + i.icon + '" class="img-responsive"></div>';
							}
							// **
							html +='<div class="biaoti"  style="width: 70px;">  ' + i.name + '</div></div></div>';
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
						}else if(i.link != null && i.link.indexOf("paytype=orderHistory") > 0){
							/*var u_token = "wxSearchNew";
							html += '<div class="col-xs-3"><div class="tubiaoti" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + u_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
							if(i.urltype == 2) {
								html += '<div class="tu"><img src="'+ i.icon +'" class="img-responsive"></div>';
							} else {
								html += '<div class="tu"><img src="../icon/' + i.icon + '" class="img-responsive"></div>';
							}
							html +='<div class="biaoti">' + i.name + '</div></div></div>';*/
							/*
							 * html += '<li id="pay" class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + base_api_url+"/" + (i.link + "&token=" + u_token + "&IsMobile=2" + "&cityCode=" + "230281") + '">';
							 * html += ' <a>';
							 * 
							 * if(i.urltype == 2) { html += ' <img src="' +
							 * i.icon + '">'; } else { html += ' <img
							 * src="icon/' + i.icon + '">'; }
							 * 
							 * html += ' <div class="mui-media-body">' + i.name + '</div>';
							 * html += ' </a>'; html += '</li>';
							 */
						}else {
							html += '<div class="col-xs-3"><div class="tubiaoti" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + i.link + '">';
							if(i.urltype == 2) {
								html += '<div class="tu"><img src="'+ i.icon +'" class="img-responsive"></div>';
							} else {
								html += '<div class="tu"><img src="../icon/' + i.icon + '" class="img-responsive"></div>';
							}
							// **
							html +='<div class="biaoti" style="width: 70px;">' + i.name + '</div></div></div>';
							/*
							 * html += '<li  class="mui-table-view-cell mui-media mui-col-xs-3" data-open="' + i.openType + '" data-flag="' + i.needLogin + '" data-id="' + i.id + '" data-html="' + i.link + '">';
							 * html += ' <a>'; if(i.urltype == 2) { html += '
							 * <img src="' + i.icon + '">'; } else { html += '
							 * <img src="icon/' + i.icon + '">'; } html += '
							 * <div class="mui-media-body">' + i.name + '</div>';
							 * html += ' </a>'; html += '</li>';
							 */
						}
						if(num == 8){
							html+='<div class="col-xs-3"><div class="tubiaoti"  style="width: 70px;"><div class="tu"><a href="classnh.html"><img src="img/mscs_58.jpg" class="img-responsive"></a></div><div class="biaoti"><a href="classnh.html">更多分类</a></div></div></div>';
						}
						if(num == 8){
							html+='</div>';
						}
						
						allhtml+=html;
						
						/*
						 * $('#pay').off().on('tap', function(e) {
						 * console.log(1111); var viewTitle =
						 * $(this).attr('data-title'); var obj = { 'html':
						 * $(this).attr('data-html'), 'title': viewTitle, 'id':
						 * 'pay' } //通用 titleNViewWebview(obj); })
						 */
					}
					$('#apps').append(allhtml);
					tabScroll();
				}
			},
			complete: function() {
				// mui.hideLoading(function() {});
			}
		});
	}
