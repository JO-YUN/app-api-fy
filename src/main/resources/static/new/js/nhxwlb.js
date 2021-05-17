
	/* ---- */
	//新闻置顶
	function newTop(picUrl,id) {
		var html =
			'<div class="swiper-slide"><a href="query_news_nh.html?id='+id+'"><img src="' + picUrl + '"/></a></div>';
		return html;
	}
	//中间两张图片
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
	//下边列表
	function newTopThree(picUrl, title, releaseDate,id,intro) {
		/*var html =
			'<li class="bg-fl1 ">' +
			'	<div class="xwlb-zw  ">' +
			'		<div class="bbjj xiaotubiao" >' +
			'			 <i><b class="ziti1">' + daysStr + '</b><br ><u class="ziti2">'+ yearStr +'.'+ monthStr+'</u></i>' +
			'		</div>' +
			'		<a href="query_news_nh.html?id='+id+'"><p style="font-size: 14px; font-weight: bold;">' + title + '</p></a>' +
			'	</div>' +
			'	<div class="xwlb-zw line1 " style="padding-top: 10px;">' +
			'		<a href="query_news_nh.html?id='+id+'"><p style="font-size: 14px; ">' + intro + '</p></a>' +
			'	</div>' +
			'	<div class="xwlb-zw ">' +
			'		<a href="query_news_nh.html?id='+id+'"><p style="font-size: 14px; font-weight: bold;float: right;padding-top: 10px;">查看更多>></p></a>' +
			'	</div>' +
			'</li>';*/
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
  	function getNewsType(){
		$.ajax({
		type: "post", //请求的方式，也有POST请求
		url: "../newsTypeNH/queryNewsTypeList", //请求地址，
		data: {
		}, //data是传给后台的字段，支取5条数据
		dataType: "json", //json格式，后台返回的数据为json格式的。
		success: function(result) {
			for(var i=0; i<result.list.length; i++){
				var name = result.list[i].name;
				var url = result.list[i].list_file_pic[0].url;
				var id = result.list[i].id;
				/*var html = '<div class="col-xs-3 newsTypeImage">'+
				'<div class="tubiaoti">'+
				'<div class="tu">'+
					'<img src="'+url+'" class="img-responsive gray iwidth" id="img'+id+'" name ="clickMe" onclick="setNewsType(\''+id+'\')">'+
				'</div>'+
				'<div class="biaoti">'+
					name+
				'</div>'+
				'</div>'+
				'</div>';
				$(".fenlei").append(html);*/
				var html ='<li name ="clickMe" onclick="setNewsType(\''+id+'\')" ><a href="#">'+name+'</a></li>';
				$("#fenlei").append(html);
			}
			//$("#fenlei").append('<input type="hidden" id="newsTypeHidden">');
		}
		})
	}
	
    function setNewsType(id){
		$("#newsTypeHidden").val(id);
		/*$(".iwidth").each(function(){
			$(this).addClass("gray");
		});
		$("#img"+id).removeClass("gray");*/
		getNewsList('1','5',id);
		}
  //标题截取字
	 function titleSubstr (str){
		
	    	if(str != null){
	    		if(str . length > 18){
	    			str = str.substring(0,18) + '...';
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
   			str = str.substring(0,50) + '...';
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
	//AJAX方法
	function getNewsList(pageNo,pageSize,typeId){
		console.log("pageNo=="+pageNo+"==pageSize=="+pageSize+"==typeId=="+typeId);
		if(typeId == null)
		var typeId =$("#newsTypeHidden").val();
		$.ajax({
			type: "post", //请求的方式，也有POST请求
			url: "../newsInfoNH/queryNewsInfoListPage", //请求地址
			data: {
				pageNo: pageNo,
				pageSize: pageSize,
				homePages:'1',
				typeId:typeId
			}, //data是传给后台的字段，支取5条数据
			dataType: "json", //json格式，后台返回的数据为json格式的。
			success: function(result) {
				var dataObj = result.data; //返回的result为json格式的数据
				var totalPage = result.totalPage;
				
				if(pageNo == '1'){
					$("#newTopOne").show()//显示div
					  $("#newTopTow").show()//显示div
						
				}else{
					$("#newTopOne").hide();//隐藏div
					$("#newTopTow").hide();//隐藏div
				}
				//
				$("#newTop").html("");
				$("#newTopTow").html("");
				$("#newTopThree").html("");
				$("#nextPage").attr("totalPage", totalPage);
				$("#beforePage").attr("totalPage", totalPage);
				
				//置顶组
				var topList = new Array();
				//列表组
				var topTowList = new Array();
				/*console.log(topList)
				console.log(topTowList)*/
				
				//遍历
				for(var i = 0; i < dataObj.length; i++) {
					if( dataObj[i].topState == "Y"){
						topList.push(dataObj[i]);
					}else{
						topTowList.push(dataObj[i]);
					}
				}
				//置顶
				 //数组里除去第一个以外的数据
			/*	var ary=[1,2,3,4]; 
				var a=ary.shift()
				console.log(a)
				console.log(ary)*/
				
				//第一条
				if(topList.length > 0){
					//有数据,显示轮播图片
					$("#newTopOne").show()
					//数据是几条,多条取两条
					var arrPic = topList[0].list_file_pic;
					if(arrPic != null && arrPic.length > 0){
						for(var i = 0; i < arrPic.length; i++) {
							var id = topList[0].id
							var picUrl = arrPic[i].url;
							$("#newTop").append(newTop(picUrl,id));
							//轮播图
							initSwiper()
						}
					}else{
						var picUrl = '../img/news.png';
						var id = topList[0].id
						$("#newTop").append(newTop(picUrl,id));
						//轮播图
						initSwiper()
					}
					//第二条
					if(topList.length > 1){
						 $("#newTopTow").show()//显示div
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
							var title2 = titleSubstr(topList[1].title);
							var id2 = topList[1].id;
							$("#newTopTow").append(newTopTow(picUrl1, picUrl2, title2,id2));
						}
					}else{
						$("#newTopTow").hide();//隐藏div
					}
				}else{
					$("#newTopOne").hide();//隐藏div
				}
				//列表组
				if(topTowList.length > 0){
					for(var i = 0; i < topTowList.length; i++) {
						var id = topTowList[i].id;//ID
						var topState = topTowList[i].topState; //置顶状态
						var title = titleSubstr(topTowList[i].title); //标题
						var intro =introSubstr(topTowList[i].context);//简介截取
						var yearStr = yearsSubstr(topTowList[i].releaseDate);//年
						var monthStr = monthsSubstr(topTowList[i].releaseDate);//月
						var daysStr = daysSubstr(topTowList[i].releaseDate);//日
						var releaseDate = topTowList[i].releaseDate;// 年
						var pic = topTowList[i].list_file_pic;
						if(pic != null && pic.length > '0'){
							var picUrl = pic[0].url;
						}else{
							var picUrl ='../img/news.png';
						}
						$("#newTopThree").append(newTopThree(picUrl, title, releaseDate,id,intro));
					}
					
				}
				/*  -分页- */
				/* 判断 */
				 if(totalPage > pageNo){
					$("#nextPage").show();
				} else {
					$("#nextPage").hide();
				}
				//console.log(data);
				$("#nextPage").attr("nextPageNo", pageNo);
				$("#nextPage").attr("nextPageSize", pageSize);
				$("#beforePage").attr("beforePageNo", pageNo);
				$("#beforePage").attr("beforePageSize", pageSize);
			},
			error: function() {
				alert("获取服务器失败");
			}
		});
	}
	//上一页
	function beforePage(tag){
		$("#nextPage").show();
		var beforePageNo = parseInt($(tag).attr("beforePageNo"))-1;
		var beforePageSize = $(tag).attr("beforePageSize");
		if(beforePageNo == '1'){
			$("#beforePage").hide();
		}
		var typeId =$("#newsTypeHidden").val();
		getNewsList(beforePageNo,beforePageSize,typeId);
	}
	//下一页
	function nextPage(tag){
		$("#beforePage").show();
		$("#nextPage").hide();
		var nextPageNo = parseInt($(tag).attr("nextPageNo"))+1;
		var nextPageSize = $(tag).attr("nextPageSize");
		var totalPage = parseInt($(tag).attr("totalPage"));
		if(totalPage==nextPageNo){
			$("#nextPage").hide();
		}
		var typeId =$("#newsTypeHidden").val();
		getNewsList(nextPageNo,nextPageSize,typeId);
	}
	$(function(){
		getNewsType();
		getNewsList('1','5',$("#newsTypeHidden").val())
		// alert("自动加载");
	});
	/* ---- */
	function initSwiper(){
		var mySwiper = new Swiper ('.swiper-container', {
	        // 轮播图的方向，也可以是vertical方向
	        direction:'horizontal',
	        //播放速度
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
		   //播放速度
		   loop: true,
		   // 自动播放时间
		   autoplay:true,
		   // 播放的速度
		   speed:3000,
		   // 如果需要分页器，即下面的小圆点
		   pagination: {el:'.swiper-pagination1'},
		   autoplayDisableOnInteraction : false
		 });
	}

		
