package jp.co.sss.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jp.co.sss.shop.entity.Category;
import jp.co.sss.shop.entity.ItemWithCategory;
import jp.co.sss.shop.repository.ItemWithCategoryRepository;

@Controller
public class ItemWithCategoryController {
	@Autowired
	ItemWithCategoryRepository repository;
	@Autowired
	EntityManager entityManager;
	
	@RequestMapping("items/findItemAndCategory")
	public String showItemAndCategoryList(Model model) {
		model.addAttribute("items",repository.findAll());
		return "items/item_category_list";
	}
	@RequestMapping("items/searchByCategoryId/{categoryId}")
	public String searchByCategoryId(@PathVariable Integer categoryId,Model model) {
		Category category = new Category();
		category.setId(categoryId);
		List<ItemWithCategory>items = repository.findByCategory(category);
		model.addAttribute("items",items);
		return "items/item_category_list";
	}
	@RequestMapping("items/searchWithNamedQuery/{id}")
	public String searchWithNamedQuery(@PathVariable Integer id,Model model) {
		Query query = entityManager.createNamedQuery("findByIdNamedQuery");
		query.setParameter("id", id);
		model.addAttribute("items",query.getResultList());
		return "items/item_category_list";
	}
	@RequestMapping("items/searchWithQuery/{id}")
	public String searchWithQuery(@PathVariable Integer id,Model model) {
		model.addAttribute("items",repository.findByIdQuery(id));
		return "items/item_category_list";
	}
	@RequestMapping("items/searchWithQuery")
	public String searchWithQuery(Model model) {
		model.addAttribute("items",
				repository.findByPriceGrEAVGPriceQuery());
		return "items/item_category_list";
	}
	
}
