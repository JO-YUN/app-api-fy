$(function() {
	
	$.ajax({
		type: "post",
		url: "getItemListByPage",
		data: JSON.stringify({page:"1",rows:"100",orgCode:getQueryVariable("orgCode")}),
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		contentType: "application/json",
		beforeSend:function(){mui.showLoading("正在加载", "div");},
		complete:function(){mui.hideLoading(function() {});},
		success: function(result) {
			
			var html = "";
			if(result.pageList.length == 0){
				html +="<h4 class=\"list-group-item-heading\">暂时没有新数据。</h4>" ;
			}
			for(var item of result.pageList){
					html += "<a href=\"./details.html?itemCode="+ item.columns.CODE +"\" class=\"list-group-item\">" +
					"<h4 class=\"list-group-item-heading\">" +item.columns.NAME + "</h4>" +
							"<p class=\"list-group-item-text\">办理机构："+ item.columns.ORG_NAME+"。</p>" +
							
					"</a>";
			}
			$(".list-group").html(html);
	}});
})


function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}