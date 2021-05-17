var openid = null;
var country = null;
var province = null;
var city = null;
var sex = null;
var nickname = null;
var headimgurl = null;
var language = null;

$(function(){
	openid = sessionStorage.getItem("openid");
	country = sessionStorage.getItem("country");
	province = sessionStorage.getItem("province");
	city = sessionStorage.getItem("city");
	sex = sessionStorage.getItem("sex");
	nickname = sessionStorage.getItem("nickname");
	headimgurl = sessionStorage.getItem("headimgurl");
	language = sessionStorage.getItem("language");
	if(openid){
		$("#headimg").attr("src",headimgurl);
		$("#nickname").html(nickname);
	}else{
		var data = getQueryVariable("data");;
		if(!data){
			window.location.href="../wxLoginfy";
		}else{
			var datastring = decodeURIComponent(decodeURI(data));
			var dataJson = JSON.parse(datastring);
			sessionStorage.setItem("openid",dataJson.openid);
			openid = dataJson.openid;
			sessionStorage.setItem("country",dataJson.country);
			country = dataJson.country;
			sessionStorage.setItem("province",dataJson.province);
			province = dataJson.province;
			sessionStorage.setItem("city",dataJson.city);
			city = dataJson.city;
			sessionStorage.setItem("sex",dataJson.sex);
			sex = dataJson.sex;
			sessionStorage.setItem("nickname",dataJson.nickname);
			nickname = dataJson.nickname;
			sessionStorage.setItem("headimgurl",dataJson.headimgurl);
			headimgurl = dataJson.headimgurl;
			sessionStorage.setItem("language",dataJson.language);
			language = dataJson.language;
			$("#headimg").attr("src",dataJson.headimgurl);
			$("#nickname").html(dataJson.nickname);
		}
	}
	checkOpenidFy();
//	$.ajax({
//		type: "get", 
//		url: "../wxLoginfy", 
//		contentType: "application/x-www-form-urlencoded",
//		dataType: "json", //json格式，后台返回的数据为json格式的。
//		success: function(result) {
//			alert(result);
//		}
//	})
})

function checkOpenidFy(){
	$.ajax({
	type: "get", 
	url: "../realName/checkOpenidFy",
	data:{"openid":openid},
	dataType: "json", //json格式，后台返回的数据为json格式的。
	success: function(result) {
		if(result.errorcode == 1){
			$("#mh-wsgr").html('<span class="icon-comm"></span>实名认证（已认证）<em class="mh-num"></em>');
			$("#mh-wsgr").attr("disabble","disabble");
		}else{
			$("#mh-wsgr").attr("href","../fysmrz.html");
		}
	}
	})
}

function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       return query.substring(5);
}