$(function() {
	
	$.ajax({
		type: "post",
		url: "getRegionOrgenByRegionCode",
		data: JSON.stringify({regionCode:"230281000000"}),
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		contentType: "application/json",
		beforeSend:function(){mui.showLoading("正在加载", "div");},
		complete:function(){mui.hideLoading(function() {});},
		success: function(result) {
			var html = "";
			for(var depart of result.organ){
				html += "<a href=\"./items.html?orgCode="+ depart.CODE +"\" class=\"list-group-item\">" +
						"<h4 class=\"list-group-item-heading\">" +depart.SHORT_NAME + "</h4>" +
								"<p class=\"list-group-item-text\">"+ depart.NAME+"。</p>" +
								
						"</a>";
			}
			$(".list-group").html(html);
	}});
})