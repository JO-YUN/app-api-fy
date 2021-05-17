function initDownloadpages(appscheme){
	//app,Android-应用宝下载地址/IOS-appstore下载地址
	var appstoreUrl = appinfo.getAppStoreAddressByscheme(appscheme);
	var href = browser.versions.android ? appstoreUrl.qqstore : appstoreUrl.appstore;

    $(".download").attr("href",href);
    // alert($(".download").attr("href"));
    // alert(href.indexOf('.apk'));
	if(href.indexOf('.apk') > 0){
      //在android版的weixin中引导去浏览器中打开下载
      if(browser.versions.weixin && browser.versions.android){
      	$(".download").attr("href","javascript:;");
      	$(".download").click(function(){
          // alert(href);
      		showguidelayer();
      	})
      }else if(!browser.versions.weixin && browser.versions.android){
      	//android浏览器中直接跳转到下载地址
      	location.href = href;
      }
	}
};


function showguidelayer(){
          var alertHtml = new Array();
          alertHtml.push('<div class="alert_wrap">');
          alertHtml.push('<img src="../wap/images/yd@2x.png" alt=""/>');
          alertHtml.push("</div>");
          $("body").append(alertHtml.join(""));
        }