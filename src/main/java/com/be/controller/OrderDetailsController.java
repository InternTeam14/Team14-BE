package com.be.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.be.entities.OrderDetail;

import com.be.service.OrderDetailService;
import com.be.service.OrderService;
import com.be.service.ProductService;





@Controller
@RequestMapping("user/OrderDetails")
public class OrderDetailsController {
	@Autowired
	OrderDetailService detailService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;
	
	@RequestMapping("")
	public String list(ModelMap modelMap) {
		List<OrderDetail> list = detailService.findAll();
		modelMap.addAttribute("orderDetails",list);
		return "customerUI/orderDetails";
	}
	
	@GetMapping("orderDetails/{order_detail_id}")
	public ModelAndView view(ModelMap model,@PathVariable("order_detail_id") Long orderDetailId) {
		Optional<OrderDetail> opt = detailService.findById(orderDetailId);
		
			
		
			OrderDetail enCategory = opt.get();
							
			model.addAttribute("orderDetail",enCategory);
			return new ModelAndView("customerUI/orderDetails",model);
	
	}	
}
