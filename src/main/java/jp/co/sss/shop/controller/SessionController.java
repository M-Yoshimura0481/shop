package jp.co.sss.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SessionController {
	@RequestMapping(path = "/login",method = RequestMethod.GET)
	public String login() {
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login"; 
	}
	
	@RequestMapping(path = "/doLogin",method = RequestMethod.GET)
	public String doLoginGet(Integer userId) {
		System.out.println("ユーザーID:" + userId);
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login"; 
	}
	
	
}
