
mui.init(
	
)

$(".img2").on("tap",function(){
	  $(this).hide();
});
	
$(".tabs").on("tap",".tabdiv",function(event){
	var targetTab = this.getAttribute('id');
	if("instruction" == targetTab){
		$(".img2").show();
	}
})

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
				$("#pay").attr("data-open","1");
				$("#pay").attr("data-flag","1");
				$("#pay").attr("data-html",base_api_url+"/app-api/bmjf/pay?paytype=010010&token=wxhc&IsMobile=2&cityCode=230281");
				$("#search").attr("data-open","1");
				$("#search").attr("data-flag","1");
				$("#search").attr("data-html",base_api_url+"/app-api/bmjf/pay?paytype=orderHistory&token=wxSearchhc&IsMobile=2&cityCode=230281");
			}
});

$('#pay,#search').off().on('tap', function(e) {
	if($(this).attr('data-html') != 'null' && $(this).attr('data-html') != '') {

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
    		        	console.log(result.data.bsn_Data);
    		        	console.log(result.data.url);
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

function getUrlParam(name) {
			  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			  var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			  if (r != null) return decodeURI(r[2]); return null; //返回参数值
		}
