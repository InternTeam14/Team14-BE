package com.be.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.be.entities.Order;
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
	
	 @RequestMapping("list")
	 public String list(Model model, javax.servlet.http.HttpServletRequest request) {
		 String username = request.getRemoteUser();
		 model.addAttribute("orders", orderService.findByUsername(username));
		
		 return "customerUI/orderDetail";
	 }
	 
	 
	 
	  @RequestMapping("detail/{orderId}")
	    public String detail(@PathVariable("orderId") Long id, Model model) {
	        Order order = orderService.findById(id);
	        model.addAttribute("order", order);
	        model.addAttribute("orderDetails", order.getOrderDetails()); // Thêm orderDetails vào model
	        return "customerUI/orderDetails";
	    }	
}
