package jp.co.sss.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	//表示するURL
	@RequestMapping(path = "/")
	public String index() {
		//表示したいtemplatesフォルダ内のファイル名
		return "index"; 
	}
	@RequestMapping(path = "/before")
	public String before() {
		//表示したいtemplatesフォルダ内のファイル名
		return "before"; 
	}
	
	@RequestMapping(path = "/after")
	public String after() {
		//表示したいtemplatesフォルダ内のファイル名
		return "after"; 
	}
	
	@RequestMapping(path = "/index_f")
	public String indexForward() {
		//表示したいtemplatesフォルダ内のファイル名
		//データは保持される
		return "index"; 
	}
	
	@RequestMapping(path = "/transition")
	public String sample_Tramsition() {
		//表示したいtemplatesフォルダ内のファイル名
		return "sample_Tramsition"; 
	}
	
	@RequestMapping(path = "/index_r")
	public String indexRedirect() {
		//URL : index　にアクセスしなおす
		//　処理が終わるため、データは消える
		return "redirect:/"; 
	}
}
