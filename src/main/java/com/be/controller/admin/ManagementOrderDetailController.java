package com.be.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.be.entities.OrderDetail;
import com.be.service.OrderDetailService;



@Controller
@RequestMapping("orderDetails")
public class ManagementOrderDetailController {

	@Autowired
	OrderDetailService detailService;
	
	@RequestMapping("list")
	public String list(ModelMap modelMap) {
		List<OrderDetail> list = detailService.findAll();
		modelMap.addAttribute("orderDetails",list);
		return "customerUI/orderDetail";
	}
}
