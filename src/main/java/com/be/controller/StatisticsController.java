package com.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.be.entities.Order;
import com.be.entities.Product;
import com.be.service.OrderService;
import com.be.service.ProductService;



@Controller
@RequestMapping("adminUI/statitics")
public class StatisticsController {
	@Autowired
	OrderService orderService;
	
	
	
	
	@RequestMapping("list")
	public String list(ModelMap modelMap) {
		List<Order> list = orderService.listOrder();
		modelMap.addAttribute("orders", list);
		return "adminUI/statitics";
	}
	
	@RequestMapping("")
	public String list1(ModelMap modelMap) {
		Long total = orderService.countTotalOrder();
		modelMap.addAttribute("countTotalOrder",total);
		Long total2 = orderService.countTotalUserByPrduct();
		modelMap.addAttribute("countTotalUserByProduct",total2);
		
		Long total3 = orderService.totalQuantitySell();
		modelMap.addAttribute("totalQuantitySell",total3);
		
		
		Long tota4 = orderService.totalOrderAmountSell();
		modelMap.addAttribute("countTotalPrice",tota4);
		return "adminUI/statitics";
	}
}
