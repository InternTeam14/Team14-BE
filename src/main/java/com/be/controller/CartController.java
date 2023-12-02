package com.be.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import com.be.dto.CartDTO;
import com.be.entities.Cart;
import com.be.entities.Product;
import com.be.entities.User;
import com.be.service.CartService;
import com.be.service.ProductService;
import com.be.service.UserService;
import com.be.utils.RamdomID;
import com.be.utils.RedirectHelper;

@Controller
@RequestMapping("web")
public class CartController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	

	void fillCart(ModelMap model) {
		try {
//		Tìm các sản phẩm được lưu trong giỏ hàng, tương ứng với customerID
//				Long numbersProductCart = cartService.countByUserID(session.getAttribute("customerID").toString());
//				model.addAttribute("numbersProductCart", numbersProductCart);
				
				User user= userService.findByAccount_Username(session.getAttribute("username").toString());
				List<Cart> list = cartService.findByUserID(user.getUserId());
//		Tính tổng tiền của các sản phẩm đã được lưu trong giỏ hàng
				double totalPrice = 0;
				int totalQuantity = 0;
				for (int i = 0; i < list.size(); i++) {
					totalPrice += list.get(i).getTotalAmount();
					totalQuantity += list.get(i).getQuantity();
				}

				if (list.isEmpty()) {
					model.addAttribute("cartItems", null);
				} else {
					model.addAttribute("cartItems", list);
					model.addAttribute("cartTotalPrice", totalPrice);
					model.addAttribute("cartTotalQuantity", totalQuantity);
				}

		} catch (Exception e) {
			return;
		}
	}

	
	@GetMapping("cart")
	public String viewCart(ModelMap model) {
		fillCart(model);	
		return "customerUI/cart";
	}
	
	@PostMapping("cart/addToCart")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("cart") CartDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView(viewCart(model));
		}

		Cart entity = new Cart();
		BeanUtils.copyProperties(dto, entity);
		entity.setCartId("C-"+RamdomID.generateRandomId(10));
		entity.setTotalAmount(dto.getQuantity()*dto.getPrice());
		
		entity.setProduct(new Product(dto.getProductID()));
		
//		Lưu thông tin customer vào cart
		User customer=userService.findByAccount_Username(session.getAttribute("username").toString());
		entity.setUsers(new User(customer.getUserId()));
		
		cartService.save(entity);

		return RedirectHelper.redirectTo("/web/cart");
	}
	
	@GetMapping("cart/addToCart/{dishID}")
	public ModelAndView saveOneProduct(ModelMap model, @PathVariable("dishID") String productID) {
		Optional<Product> opt = productService.findById(productID);
		
		Cart entity = new Cart();
		entity.setCartId("C-"+RamdomID.generateRandomId(10));
		entity.setQuantity(1);
		entity.setTotalAmount(opt.get().getPrice());
		
		entity.setProduct(opt.get());
		
		User user= userService.findByAccount_Username(session.getAttribute("username").toString());
				//		Lưu thông tin customer vào cart
		entity.setUsers(user);
		
		cartService.save(entity);
		return RedirectHelper.redirectTo("/web/cart");
	}
	
	@GetMapping("cart/delete/{cartID}")
	public ModelAndView delete(ModelMap model, @PathVariable("cartID") String cartID) {
		cartService.deleteById(cartID);
		
		return RedirectHelper.redirectTo("/web/cart");
	}

	@GetMapping("cart/delete-all/{customerID}")
	public ModelAndView deleteAll(ModelMap model,  @PathVariable("customerID") String customerID) {
		cartService.deleteByUserID(customerID);
		
		return RedirectHelper.redirectTo("/web/cart");
	}
	
}
