package com.be.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.be.entities.OrderDetail;
import com.be.service.OrderDetailService;
import com.be.service.OrderService;

@Controller
@RequestMapping("statistic")
public class ManagementStatisticController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService detailService;
	
	
	@RequestMapping("")
	public String list1(ModelMap modelMap) {
		//đếm số lượng đơn hàng
		Long total = orderService.countTotalOrder();
		modelMap.addAttribute("countTotalOrder",total);
		
		//đếm số lượng người mua hàng
		Long total2 = orderService.countTotalUser();
		modelMap.addAttribute("countTotalUserByProduct",total2);
		//đếm số lượng sản phẩm bán được
		Long total3 = detailService.countTotatlQuantityOfProduct();
		modelMap.addAttribute("countTotalQuantity",total2);
		
		
		//Tổng tiền sản phẩm bán được
		Long tota3 = detailService.countTotatlAmountOfProduct();
		modelMap.addAttribute("countTotalPrice",tota3);
	
		
		//danh sách sản phẩm bán được
		List<OrderDetail> list = detailService.listProductSell();
		modelMap.addAttribute("orderDetails",list);
		
		return "adminUI/statistics";
	}
}
