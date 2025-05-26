package jp.co.sss.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.shop.form.LoginForm;
import jp.co.sss.shop.form.LoginFormWithAnnotation;
import jp.co.sss.shop.form.LoginFormWithValidation;


@Controller
public class SessionController {
	@RequestMapping(path = "/login",method = RequestMethod.GET)
	public String login() {
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login"; 
	}
	@GetMapping("/doLogin")
//	@RequestMapping(path = "/doLogin",method = RequestMethod.GET)
	public String doLoginGet(Integer userId) {
		System.out.println("ユーザーID(GET):" + userId);
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login"; 
	}
	
	@PostMapping("/doLogin")
//	@RequestMapping(path = "/doLogin",method = RequestMethod.POST)
	public String doLoginPost(Integer userId) {
		System.out.println("ユーザーID(POST):" + userId);
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login"; 
	}
	
	@GetMapping("/loginUsingForm")
//	@RequestMapping(path = "/doLogin",method = RequestMethod.GET)
	public String loginUsingForm() {
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login_using_form"; 
	}
	
	@PostMapping("/doLoginUsingForm")
//	@RequestMapping(path = "/doLogin",method = RequestMethod.GET)
	public String dologinUsingForm(LoginForm form) {
		System.out.println("ユーザーID:" + form.getUserId());
		System.out.println("パスワード:" + form.getPassword());
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login_using_form"; 
	}
	
	@GetMapping("/loginOnRequest")
	public String loginOnRequest() {
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login_on_request"; 
	}
	
	@PostMapping("/doLoginOnRequest")
	public String doLoginOnRequest(LoginForm form,Model model) {
		//表示したいtemplatesフォルダ内のファイル名
		model.addAttribute("userId",form.getUserId());
		return "session/login_on_request"; 
	}
	
	@GetMapping("/loginOnSession")
	public String loginOnSession() {
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login_on_session"; 
	}
	
	@PostMapping("/doLoginOnSession")
	public String doLoginOnSession(LoginForm form,HttpSession session) {
		//表示したいtemplatesフォルダ内のファイル名
		if(form.getUserId() == 123) {
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		}else {
			return "session/login_on_session"; 
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//セッション破棄
		session.invalidate();
		//表示したいtemplatesフォルダ内のファイル名
		return "session/login_on_session"; 
	}

	@RequestMapping(path = "/loginWithValidation", method = RequestMethod.GET)
	public String loginWithValidation(@ModelAttribute LoginFormWithValidation form) {
		return "session/login_with_validation";
	}

	@RequestMapping(path = "/loginWithValidation", method = RequestMethod.POST)
	public String doLoginWithValidation(
			@Valid @ModelAttribute LoginFormWithValidation form,
			BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "session/login_with_validation";
		}
		if (form.getUserId() == 123) {
			//入力したユーザ ID をセッション属性 userId としてセッションスコープに保存
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/login_with_validation";
		}
	}

	@RequestMapping(path = "/loginWithAnnotation", method = RequestMethod.GET)
	public String loginWithAnnotation(@ModelAttribute LoginFormWithAnnotation form) {
		return "session/login_with_annotation";
	}

	@RequestMapping(path = "/loginWithAnnotation", method = RequestMethod.POST)
	public String doLoginWithAnnotation(
			//入力チェックの有無　入力チェック情報
			@Valid @ModelAttribute LoginFormWithAnnotation form,
			BindingResult result, HttpSession session) {
		//入力チェックエラーなら
		if (result.hasErrors()) {
			return "session/login_with_annotation";
		}
		if (form.getUserId() == 123) {
			//入力したユーザ ID をセッション属性 userId としてセッションスコープに保存
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/login_with_annotation";
		}
	}
	
}
