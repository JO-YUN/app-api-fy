var openid = '';
var wechat = '';

$('#save').on('tap', function() {
	if($('#name input').val() != null && $('#name input').val() != '' && $('#name input').val() != '未填写') {
		var xm = namefunction();
		if(xm != false) {

				var sfzze = sfzfunction();
				if(sfzze != false) {
					modification();
				}
		}
	} else {
		mui.toast("真实姓名必须为中文,请重新输入!")
	}
});


function modification() {
	$.ajax({
		type: "get",
		url: "reg/addWx",
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		data: {
			'name': $('#name a input').val(),	
			'sfzjh': $('#sfz a input').val(),
			'userId': $('#phone a input').val(),
			'verificationCode': $('.codeyzm').val()
		},
		beforeSend: function() {
			mui.showLoading("正在加载", "div");
		},
		success: function(result) {
			if(result.errorcode == 1) {
				mui.toast("身份信息已提交！");
				window.history.go(-1);
			} else {
				mui.toast(result.errormsg);
			}
		},
		complete: function() {
			mui.hideLoading(function() {});
		}
	});
}

//图片上传
mui.init();
mui.plusReady(function() {
	
});



$(function() {
	mui.init();
	
	openid = getlocalStorage('openid');
	wechat = getlocalStorage('wechat');
	
	$('.yzm').on('tap', function() {
		if(countdown  != 60){
			mui.toast("发送成功，"+countdown+"秒后可以再次发送！");
			return ;
		}
		if($('#phone input').val() == ""){
			mui.toast("请输入电话号码!");
			return ;
		}
		$.ajax({
			type: "get",
			url: apis.daYU_sendSMSCode,
			dataType: "json",
			cache: false, //缓存
			async: true, //异步
			data: {
				'userId': $('#phone input').val(),
				'type': 5
			},
			beforeSend: function() {
				mui.showLoading("正在加载", "div");
			},
			success: function(result) {
				if(result.errorcode == 1) {
					mui.toast("发送成功，"+countdown+"秒后可以再次发送！");
					settime($('.yzm')[0]);
				} else {
					mui.toast(result.errormsg);
				}
			},
			complete: function() {
				mui.hideLoading(function() {});
			}
		});
	});
	
	$("#name input").blur(function() {
		if($('#name input').val() != null && $('#name input').val() != '') {
			namefunction();
		}
	});

	$("#sfz input").blur(function() {
		sfzfunction();
	});
	
})


function showMsg() {
	//console.log('裁剪完成跳回detail')
	personal();
}

//姓名只为中文正则
function namefunction() {
	var name = /^[\u4e00-\u9fa5\s]+$/;
	var nameinput = $('#name input').val();
	if(!name.test(nameinput)) {
		mui.toast("真实姓名必须为中文,请重新输入!")
		return false;
	}

}
//电子邮箱正则
function emailfunction() {
	var email = new RegExp("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$");
	var emailinput = $('#youx input').val();
	if(!email.test(emailinput)) {
		mui.toast("邮箱格式不正确,请重新输入!")
		$("#phone input").focus();
		return false;
	}
}
//身份证正则
function sfzfunction() {
	var sfz = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
	var sfzinput = $('#sfz input').val();
	if(!sfz.test(sfzinput)) {
		mui.toast("身份证格式不正确,请重新输入!")
		return false;
	}
}

var countdown = 60;
function settime(obj) {
	if(countdown == 0) {
		//obj.removeAttribute("disabled");
		obj.setAttribute("color", "#09B6F2");
		obj.innerText = "获取验证码";
		countdown = 60;
		return;
	} else {
		obj.setAttribute("color", "#808080");
		obj.innerText = "重发(" + countdown + ")";
		countdown--;
	}
	setTimeout(function() {
		settime(obj)
	}, 1000)
}