var openid = null;
var country = null;
var province = null;
var city = null;
var sex = null;
var nickname = null;
var headimgurl = null;
var language = null;

$('#save').on('tap', function() {
	//姓名不为空
	if($('#name input').val() != null && $('#name input').val() != '' && $('#name input').val() != '未填写') {
		//身份证
		if ($('#numberid input').val() != null && $('#numberid input').val() != '') {
			//电话号
			if ($('#phone input').val() != null && $('#phone input').val() != '') {
				//验证码
				if ($('#code input').val() != null && $('#code input').val() != '') {
					//姓名校验
					var xm = namefunction();
					if(xm != false) {
						//身份证校验
						var sfzze = sfzfunction();
						if(sfzze != false) {
							modification();
						}
					}
				}else {
					mui.toast("请输入验证码!")
				}
			}else {
				mui.toast("请输入电话号!")
			}
		}else {
			mui.toast("请输入身份证号!")
		}
	} else {
		mui.toast("请输入姓名!")
	}
});


function modification() {
	$.ajax({
		type: "get",
		url: "realName/saveRealNameNh",
		dataType: "json",
		cache: false, //缓存
		async: true, //异步
		data: {
			'name': $('#name a input').val(),	
			'numberid': $('#numberid a input').val(),
			'phone':$('#phone a input').val(),
			'openid': openid,
			'country': country,
			'city': city,
			'sex': sex,
			'nickname': nickname,
			'headimgurl': headimgurl,
			'language': language,
			'nickname': nickname,
			'verificationCode': $('.codeyzm').val()
		},
		beforeSend: function() {
			mui.showLoading("正在加载", "div");
		},
		success: function(result) {
			if(result.errorcode == 1) {
				mui.alert("个人实名认证成功","","", function(){window.location.href="new/wodenh.html";});
				//window.history.go(-1);
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
	
	openid = sessionStorage.getItem("openid");
	country = sessionStorage.getItem("country");
	province = sessionStorage.getItem("province");
	city = sessionStorage.getItem("city");
	sex = sessionStorage.getItem("sex");
	nickname = sessionStorage.getItem("nickname");
	headimgurl = sessionStorage.getItem("headimgurl");
	language = sessionStorage.getItem("language");
	
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
	//校验姓名
	$("#name input").blur(function() {
		if($('#name input').val() != null && $('#name input').val() != '') {
			namefunction();
		}
	});
	//校验身份证
	$("#numberid input").blur(function() {
		sfzfunction();
	});
	//校验手机号
	$("#phone input").blur(function() {
		phonefunction();
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
	var sfz = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
	var sfzinput = $('#numberid input').val();
	if(!sfz.test(sfzinput)) {
		mui.toast("身份证格式不正确,请重新输入!")
		return false;
	}
}
//手机号校验
function phonefunction() {
	var phoneCheck = /^1[3456789]\d{9}$/;
	var phoneInput = $('#phone input').val();
	if(!phoneCheck.test(phoneInput)) {
		mui.toast("手机号格式不正确,请重新输入!")
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