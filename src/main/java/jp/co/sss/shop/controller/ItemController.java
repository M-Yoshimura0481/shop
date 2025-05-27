package jp.co.sss.shop.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.shop.bean.ItemBean;
import jp.co.sss.shop.entity.Item;
import jp.co.sss.shop.form.ItemForm;
import jp.co.sss.shop.repository.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	ItemRepository repository;
	
	@RequestMapping("/items/findAll")
	public String showItemList(Model model) {
		model.addAttribute("items",repository.findAll());
		return "items/item_list";
	}
	
	@RequestMapping("/items/findAllByOrderByPriceDesc")
	public String showItemListByOrderByPriceDesc(Model model) {
		model.addAttribute("items",repository.findAllByOrderByPriceDesc());
		return "items/item_list";
	}
	
	@RequestMapping("/items/getReferenceById/{id}")
	public String showItem(@PathVariable Integer id, Model model) {
		Item item = repository.getReferenceById(id);
		ItemBean itemBean = new ItemBean();
		itemBean.setId(item.getId());
		itemBean.setName(item.getName());
		itemBean.setPrice(item.getPrice());
		model.addAttribute("item",itemBean);
		return "items/item";
	}
	
	
	@RequestMapping("/items/findByPrice/{price}")
	public String showItemListByPrice(@PathVariable Integer price, Model model) {
		model.addAttribute("items",repository.findByPrice(price));
		return "items/item_list";
	}
	
	@RequestMapping("/items/findByNameAndPrice/{name}/{price}")
	public String showItemListByNameAndPrice(@PathVariable String name,@PathVariable Integer price, Model model) {
		model.addAttribute("items",repository.findByNameAndPrice(name,price));
		return "items/item_list";
	}
	@RequestMapping("/items/findByNameLike/{name}")
	public String showItemListByNameLike(@PathVariable String name, Model model) {
		model.addAttribute("items",repository.findByNameContaining(name));
		return "items/item_list";
	}
	
	@RequestMapping("/items/findAllAndSetDropdown")
	public String showItemListSetDropdown(Model model) {
		model.addAttribute("items",repository.findAll());
		return "items/item_list_dropdown";
	}
	
	@RequestMapping("/items/create/input")
	public String createInput() {
		return "items/create_input";
	}
	//似てるー
	@PostMapping("/items/create/complete")
	public String createComplete (ItemForm form, Model model) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item, "id");
		item = repository.save(item);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item, itemBean);
		model.addAttribute("item",itemBean);
		return "items/item";
	}
	
	@RequestMapping("/items/update/input/{id}")
	public String updateInput (@PathVariable Integer id, Model model) {
		Item item = repository.getReferenceById(id);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item, itemBean);
		model.addAttribute("item",itemBean);
		return "items/update_input";
	}
	
	
	@RequestMapping("/items/update/complete/{id}")
	public String updateComplete (@PathVariable Integer id,ItemForm form, Model model) {
		Item item = repository.getReferenceById(id);
		BeanUtils.copyProperties(form, item, "id");
		item = repository.save(item);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item, itemBean);
		model.addAttribute("item",itemBean);
		return "items/item";
	}
	@RequestMapping("/items/delete/input")
	public String deleteInput(Model model) {
		model.addAttribute("items",repository.findAll());
		return "items/delete_input";
	}
	@PostMapping("/items/delete/complete")
	public String deleteIComplete(ItemForm form) {
		repository.deleteById(form.getId());
		return "redirect:/items/findAll";
	}
	@RequestMapping("/items/create/input/hidden")
	public String itemInputHidden() {
		return "items/create_input_hidden";
	}
	@RequestMapping("/items/create/check/hidden")
	public String itemCheckHidden(ItemForm form, Model model) {
		model.addAttribute("item",form);
		return "items/create_check_hidden";
	}
	
	@RequestMapping("/items/create/complete/hidden")
	public String itemCompleteHidden(ItemForm form, Model model) {
		//登録コマンドを入れる
		System.out.println("名前" + form.getName());
		return "items/create_complete_hidden";
	}
	
	@RequestMapping("/items/findAllJs")
	public String showItemListJs(Model model) {
	model.addAttribute("items", repository.findAll());
	// 実行時の日付を取得してリクエスト属性に保存する
	model.addAttribute("now", new Date());
	return "items/item_list_js";
	}

	
}
