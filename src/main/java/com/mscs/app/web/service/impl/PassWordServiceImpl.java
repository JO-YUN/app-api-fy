package com.mscs.app.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.util.MD5Utils;
import com.mscs.app.web.dao.UserMapper;
import com.mscs.app.web.service.PassWordService;

@Service("PassWordServiceImpl")
public class PassWordServiceImpl implements PassWordService {
	public static final Logger logger = LoggerFactory
			.getLogger(PassWordServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public ResponseDto updatePassword(HashMap<String, Object> resmap){
		ResponseDto result = new ResponseDto();
		String token = resmap.get("token")+"";
		String oldpwd = resmap.get("oldpwd")+"";
		String newpwd = resmap.get("newpwd")+"";
		String newpwd2 = resmap.get("newpwd2")+"";

		//判断参数完整性
		if(token==null || "".equals(token) 
				|| oldpwd==null || "".equals(oldpwd) 
				|| newpwd==null || "".equals(newpwd)
				|| newpwd2==null && "".equals(newpwd2)) {
			result.setErrorcode("500");
			result.setErrormsg("参数不完整，无法进行密码变更操作！");
			return result;
		}
		//判断2次输入密码是否相同
		if(!newpwd.equals(newpwd2)) {
			result.setErrorcode("500");
			result.setErrormsg("新密码两次输入不同，请重新输入！");
			return result;
		}
		//判断用户token是否过期
		String userId  = userMapper.UserIdByToken(token);
		if(userId==null || "".equals(userId)) {
			result.setErrorcode("500");
			result.setErrormsg("用户token令牌过期！");
			return result;
		}
		//判断用户密码是否若密码
		/*if(!isStrongPwd(newpwd)) {
			result.setErrorcode("500");
			result.setErrormsg("您输入的密码过于简单！");
			return result;
		}*/
		//判断原始密码与新密码是否相同
		if(newpwd.equals(oldpwd)) {
			result.setErrorcode("500");
			result.setErrormsg("原始密码与新密码不可相同！");
			return result;
		}
		String pass = MD5Utils.getMD5Token32(oldpwd);
		String realPass = userMapper.VerifitPass(userId);
		//判断原始密码是否正确
		if(!pass.equals(realPass)) {
			result.setErrorcode("500");
			result.setErrormsg("原始密码错误！");
			return result;
		}
		//修改密码
		try {
			String updatepass= MD5Utils.getMD5Token32(newpwd);
			int a = userMapper.UpdatePassWord(userId, updatepass);
			if(a==1) {
				result.setErrorcode("1");
				result.setErrormsg("密码修改成功,下次登录请使用新密码！");
				return result;
			}else {
				result.setErrorcode("500");
				result.setErrormsg("密码修改失败！");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorcode("500");
			result.setErrormsg("密码修改失败Exception！");
			return result;
		}
	}
	
	
	/**
	 * 若密码检查
	 * @param password
	 * @return
	 */
	public boolean isStrongPwd(String password) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < password.length(); i++) {
			int A = password.charAt(i);
			if (A >= 48 && A <= 57) {// 数字
				map.put("数字", "数字");
			} else if (A >= 65 && A <= 90) {// 大写
				map.put("大写", "大写");
			} else if (A >= 97 && A <= 122) {// 小写
				map.put("小写", "小写");
			} else {
				map.put("特殊", "特殊");
			}
		}
		Set<String> sets = map.keySet();
		int pwdSize = sets.size();// 密码字符种类数
		int pwdLength = password.length();// 密码长度
		if (pwdSize >= 3 && pwdLength >= 8) {
			return true;// 强密码
		} else {
			return false;// 弱密码
		}
	}

}
