package com.be.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.be.controller.admin.ManagementCategoryController;
import com.be.entities.Product;
import com.be.service.ProductService;

@Controller
@RequestMapping("web/product/")
public class ProductDetailController {

	@Autowired
	private ManagementCategoryController categoryController;
	
	@Autowired
	private HttpSession session;

	@Autowired
	private ProductService productService;

	public void fillAllProduct(ModelMap model) {
		List<Product> list = productService.findAll();
		System.out.println(list.size());
		model.addAttribute("products", list);
	}
	
	public void fillProduct(ModelMap model, String productID) {
		try {
			Optional<Product> product = productService.findById(productID);
			Product getProduct = product.get();
			model.addAttribute("product", getProduct);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}
	
	@GetMapping("detail/{productID}")
	public String viewHome(ModelMap model, @PathVariable("productID") String productID) {	
		fillProduct(model, productID);
		fillAllProduct(model);
		return "customerUI/product-detail";
	}
//	
//
//	@GetMapping("search")
//	public String searchProduct(ModelMap model, @Valid @ModelAttribute("product") DishDTO dto) {		
//		List<Dish> result=dishService.findByNameContaining(dto.getName());
//		
//		model.addAttribute("result",result);
//		model.addAttribute("numberResult", result.size());
//		return "customerUI/search";
//	}
//	
//	
//	
}
