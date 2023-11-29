package com.be.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.be.dto.CategoryDTO;
import com.be.entities.Category;
import com.be.service.CategoryService;





@Controller
@RequestMapping("adminUI")

public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDTO());
		return "adminUI/themmoitheloai";
	}
	
	@GetMapping("edit/{cateId}")
	public ModelAndView edit(ModelMap model,@PathVariable("cateId") String id) {
		
		Optional<Category> opt = categoryService.findById(id);
		
		CategoryDTO dto = new CategoryDTO();
		
		if(opt.isPresent()) {
			
			Category enCategory = opt.get();
			
			BeanUtils.copyProperties(enCategory, dto);
			dto.setIsEdit(true);
			
			model.addAttribute("category",dto);
			return new ModelAndView("adminUI/themmoitheloai",model);
		}
		model.addAttribute("message","Category is not existed");
		return new ModelAndView( "forward:/adminUI");
	
	}
	
	@GetMapping("delete/{cateId}")
	public ModelAndView delete(@PathVariable("cateId") String id,ModelMap model) {
		categoryService.deleteById(id);
		
		model.addAttribute("message","Category is delete");
		
		return new ModelAndView("forward:/adminUI",model);
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model
			,@Validated @ModelAttribute("category") CategoryDTO categorymodel,BindingResult result) {
	
		Category entity = new Category();
		BeanUtils.copyProperties(categorymodel, entity);
		
		categoryService.save(entity);
		
		model.addAttribute("message","Category is save");
		
		return new ModelAndView("forward:/adminUI",model) ;
	}
	
	@RequestMapping("")
	public String list(ModelMap modelMap) {
		List<Category> list = categoryService.findAll();
		modelMap.addAttribute("categories",list);
		return "adminUI/qltheloai2";
	}
	
	
}
