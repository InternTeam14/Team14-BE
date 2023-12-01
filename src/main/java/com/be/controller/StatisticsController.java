package com.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.be.entities.Order;
import com.be.entities.Product;
import com.be.repository.OrderDetailRepository;
import com.be.service.OrderDetailService;
import com.be.service.OrderService;
import com.be.service.ProductService;



@Controller
@RequestMapping("adminUI/statitics")
public class StatisticsController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService detailService;
	
	@RequestMapping("list")
	public String list(ModelMap modelMap) {
		Long countTotalUser = orderService.countTotalUser();
		modelMap.addAttribute("countTotalUser", countTotalUser);
		Long countTotalOrder = orderService.countTotalOrder();
		modelMap.addAttribute("countTotalOrder", countTotalOrder);
		
		Long countTotalQuantity = detailService.countTotatlQuantityOfProduct();
		modelMap.addAttribute("countTotalQuantity", countTotalQuantity);
		
		Long countTotalAmount = detailService.countTotatlAmountOfProduct();
		modelMap.addAttribute("countTotalAmount", countTotalAmount);
		return "adminUI/statistics";
	}
	
	@RequestMapping("")
	public String list1(ModelMap modelMap) {
		
		return "adminUI/statistics";
	}
}
