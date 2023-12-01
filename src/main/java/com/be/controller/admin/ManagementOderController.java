package com.be.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.be.entities.Order;
import com.be.service.OrderService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ManagementOder")
public class ManagementOderController {
	private final OrderService orderService;
	
	@GetMapping()
	public String list(Model model) {
		return "adminUI/management_Oder";
	}
	
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<?> getAllAccount(Model model) {
		List<Order> orders = orderService.getAll();
		return null;
	}
}
