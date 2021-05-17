$(function() {
	if($("#cx").val() != ""){
		search();
	}
	
	$(".submit").on("tap",search);
})

function search(){
	$.ajax({
		type: "post",
		url: "getBusinessList",
		data: JSON.stringify({objType:"1",listType:"0",code:$("#cx").val(),SystemCode:"91363F59F2CC4584B75EFCB63358231E"}),
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		contentType: "application/json",
		beforeSend:function(){mui.showLoading("正在加载", "div");},
		complete:function(){mui.hideLoading(function() {});},
		timeout: 4000,
		error: function(jqXHR, textStatus, errorThrown){
			mui.hideLoading(function() {});
			mui.alert("系统连接超时，请重试。");
		},
		success: function(result) {
			$(".list-group").empty();
			var html = "";
			if(result.state == "300" || result.code == "300"){
				html += "<div" +"\" class=\"list-group-item\">" +
				"<h4 class=\"list-group-item-heading\">"+result.error+"</h4>" +
				"</div>";
			}else{				
				if(result.data.length > 0){
					
					for(var data of result.data){
						var finish_time = data.FINISH_TIME == undefined? "暂无" : data.FINISH_TIME;
						html += "<div" + " class=\"list-group-item\">" +
								"<h4 class=\"list-group-item-heading\">申请人: " +data.APPLICANT + "</h4>" +
									"<p class=\"list-group-item-text\">事项名称: "+ data.ITEM_NAME+"</p>" +
									"<p class=\"list-group-item-text\">提交时间: "+ data.SUBMIT_TIME+"</p>" +
									"<p class=\"list-group-item-text\">证件号码: "+ data.CODE+"</p>" +
									"<p class=\"list-group-item-text\">办结时间: "+ finish_time+"</p>" +
								"</div>";
					}
				}else{
					html += "<div" +"\" class=\"list-group-item\">" +
					"<h4 class=\"list-group-item-heading\">暂无数据</h4>" +
					"</div>";
				}
			}
			$(".list-group").html(html);
			$(".list-group").show();
	}});
}