package com.be.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.be.dto.CartResponseDto;
import com.be.dto.OrderRequestDto;
import com.be.dto.ProductDTO;
import com.be.dto.UserDTO;
import com.be.entities.Account;
import com.be.entities.Cart;
import com.be.entities.Category;
import com.be.entities.Order;
import com.be.entities.OrderDetail;
import com.be.entities.Product;
import com.be.entities.User;
import com.be.service.AccountService;
import com.be.service.CartService;
import com.be.service.CategoryService;
import com.be.service.OrderDetailService;
import com.be.service.OrderService;
import com.be.service.ProductService;
import com.be.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {
	private final OrderService orderService;
	private final OrderDetailService orderDetailService;
	private final UserService userService;
	private final AccountService accountService;
	private final CartService cartService;
	private final ProductService productService;
	private final CategoryService categoryService;

	Optional<Cart> cart;
	CartResponseDto cartResponseDto;
	Product product = null;

	@GetMapping("/checkout")
	public String createOrder(Model model, HttpSession session) {

		List<String> cartIdList = new ArrayList<>();
		cartIdList.add("01");
		cartIdList.add("02");

		List<CartResponseDto> cartResponseDtos = new ArrayList<>();

		String username = (String) session.getAttribute("username");
		Optional<Account> account = accountService.findById("trungpm");

		Optional<User> user = userService.findUserByAccount(account.get());

		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.get().getUserId());
		userDTO.setAddress(user.get().getAddress());
		userDTO.setFullName(user.get().getFullName());
		userDTO.setEmail(user.get().getEmail());
		userDTO.setPhone(user.get().getPhone());

		for (String id : cartIdList) {
			cart = cartService.getCartById(id);

			cartResponseDto = new CartResponseDto();
			product = productService.getById(cart.get().getProduct().getProductID());

			cartResponseDto.setCartId(cart.get().getCartId());
			cartResponseDto.setQuantity(cart.get().getQuantity());
			cartResponseDto.setProductDTO(productToProductResponese(product));
			cartResponseDto.setPrice(product.getPrice());
			cartResponseDto.setTotal(cart.get().getQuantity() * product.getPrice());

			cartResponseDtos.add(cartResponseDto);
		}
		System.out.println(cartResponseDtos);
		model.addAttribute("userResponseDto", userDTO);
		model.addAttribute("cartResponseDtos", cartResponseDtos);
		model.addAttribute("orderRequestDto", new OrderRequestDto());

		return "/customerUI/checkout";
	}

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<?> addOrder(@Valid @RequestBody OrderRequestDto orderRequestDto, BindingResult bindingResult,
			HttpSession session) {

		Map<String, Object> errors = new HashMap<>();

		String username = (String) session.getAttribute("username");
		Optional<Account> account = accountService.findById("trungpm");

		Optional<User> user = userService.findUserByAccount(account.get());

		if (bindingResult.hasErrors()) {
			errors.put("bindingErrors", bindingResult.getAllErrors());
			return ResponseEntity.badRequest().body(errors);
		}
		Order order = new Order();
		order.setOrderId(generateRandomOrderId());
		order.setFullName(orderRequestDto.getFullName());
		order.setAddress(orderRequestDto.getAddress());
		order.setPhone(orderRequestDto.getPhone());
		order.setNote(orderRequestDto.getNote());
		order.setUsers(user.get());
		order.setTotalAmount(0.0);
		orderService.save(order);

		// ...

		double totalPayment = 0;
		for (String id : orderRequestDto.getCartIds()) {
			Optional<Cart> cart = cartService.getCartById(id);

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setQuantity(cart.get().getQuantity());

			orderDetail.setAmount(cart.get().getQuantity() * cart.get().getProduct().getPrice());

			orderDetail.setProduct(cart.get().getProduct());
			orderDetail.setOrders(order);

			orderDetailService.save(orderDetail);

			totalPayment += (orderDetail.getAmount());
		}

		order.setTotalAmount(totalPayment);
		orderService.save(order);

		return ResponseEntity.ok().body("Order Success!");
	}

	private String generateRandomOrderId() {
		Random random = new Random();
		int randomInt = random.nextInt(1000000);
		return String.format("%06d", randomInt);
	}


	public ProductDTO productToProductResponese(Product product) {
		ProductDTO productResponse = new ProductDTO();

		productResponse.setProductID(product.getProductID());
		// set title
		productResponse.setTitle(product.getTitle());
		// set category
		// Category category =
		// categoryService.getById(product.getCategory().getCateId());
		productResponse.setCateId(product.getCategory().getCateId());
		// set price
		productResponse.setPrice(product.getPrice());

		// set quantity
		productResponse.setQuantity(product.getQuantity());

		// set discription
		productResponse.setDescription(product.getDescription());

		// set images
		productResponse.setImages(product.getImages());

		return productResponse;

	}

}
