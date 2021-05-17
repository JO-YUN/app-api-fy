var materialHtml = ""; 
var LAW_TIME_BASIS = "";
$(function() {
	$.ajax({
		type: "post",
		url: "getItemInfoByItemCode",
		data: JSON.stringify({itemCode:getQueryVariable("itemCode")}),
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		contentType: "application/json",
		beforeSend:function(){mui.showLoading("正在加载", "div");},
		complete:function(){mui.hideLoading(function() {});},
		success: function(result) {

			var itemInfo = result.ItemInfo[0];
			
			$("#NAME").html(itemInfo.NAME);
			$("#ORG_NAME").html("实施主体：" + itemInfo.ORG_NAME);
			
			var html = "<div class=\"xx1 row\">"+
			"<div class=\"xinxi1 col-xs-12 col-sm-12\"><span>办理时间</span></div>"+
				"<div class=\"xinxi1 col-xs-12 col-sm-12\">"+ itemInfo.LAW_TIME_UNIT_VALUE +"</div>"+
			"</div>"+
			"<div class=\"xx1 row\">"+
			"<div class=\"xinxi1 col-xs-12 col-sm-12\"><span>所属区划</span></div>"+
			"<div class=\"xinxi1 col-xs-12 col-sm-12\">"+ itemInfo.REGION_NAME +"</div>"+
			"</div>"+
			"<div class=\"xx1 row\">"+
			"<div class=\"xinxi1 col-xs-12 col-sm-12\"><span>承办机构</span></div>"+
			"<div class=\"xinxi1 col-xs-12 col-sm-12\">"+ itemInfo.AGENT_NAME +"</div>"+
			"</div>";
			
			$("#items").html(html);
			$("#aform").attr("href","./form.html?itemId="+itemInfo.ITEM_ID+"&orgCode="+itemInfo.ORG_CODE+"&itemName="+itemInfo.NAME);
			
			//设定依据
			LAW_TIME_BASIS = itemInfo.LAW_TIME_BASIS==null?"暂无":itemInfo.LAW_TIME_BASIS;
			//申请材料
			var materials = result.material;
			if(materials.length > 0){
				for (var material of materials){
					var NAME = material.NAME;
					var MUST = material.MUST==1?"是":"否";
					var PUBLISHER = material.PUBLISHER;
					var TYPE_VALUE = material.TYPE_VALUE;
					materialHtml += "<div class=\"xx1 row\">"+
					"<div class=\"xinxi1 col-xs-12 col-sm-12\"><span>材料名称："+ NAME +"</span></div>"+
						"<div class=\"xinxi1 col-xs-12 col-sm-12\">是否必须："+ MUST +"</div>"+
						"<div class=\"xinxi1 col-xs-12 col-sm-12\">发放机关："+ PUBLISHER +"</div>"+
						"<div class=\"xinxi1 col-xs-12 col-sm-12\">材料类型："+ TYPE_VALUE +"</div>"+
						
					"</div>"
				}
			}else{
				materialHtml += "<div class=\"xx1 row\">"+
				"<div class=\"xinxi1 col-xs-12 col-sm-12\"><span>材料名称："+ "暂无" +"</span></div>"+
					"<div class=\"xinxi1 col-xs-12 col-sm-12\">是否必须："+ "暂无" +"</div>"+
					"<div class=\"xinxi1 col-xs-12 col-sm-12\">发放机关："+ "暂无" +"</div>"+
					"<div class=\"xinxi1 col-xs-12 col-sm-12\">材料类型："+ "暂无" +"</div>"+
					
				"</div>"
			}
			
			sessionStorage.setItem("materials",JSON.stringify(materials));
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

function tc(){
	layer.open({
    type: 1
    ,content: '<ur>'+ materialHtml +'</ur>'
    ,anim: 'up'
    ,style: 'position:fixed; bottom:0; left:0;  height: 400px; padding:10px 10px; border:none;overflow-y: scroll'
  });
}

function law(){
	layer.open({
	    type: 1
	    ,content: '<ur>'+ LAW_TIME_BASIS +'</ur>'
	    ,anim: 'up'
	    ,style: 'position:fixed; bottom:0; left:0; width: 100%; height: 400px; padding:10px 10px; border:none;overflow-y: scroll'
	  });
}