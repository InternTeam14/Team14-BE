package com.be.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.be.dto.LoginDTO;
import com.be.entities.Account;
import com.be.entities.User;
import com.be.service.AccountService;
import com.be.service.UserService;
import com.be.utils.RamdomID;
import com.be.utils.RedirectHelper;

@Controller
@RequestMapping("web/account")
public class LoginController {
	
	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("login")
	public String viewLogin(ModelMap model) {
		
		return "customerUI/login";
	}
	
	@GetMapping("logout")
	public ModelAndView logout(ModelMap model) {
		session.setAttribute("username", null);
		session.setAttribute("role", "guest");
		
		 return RedirectHelper.redirectTo("/web/index");
	}

	@PostMapping("pLogin")
	public ModelAndView login(ModelMap model,
							  @Valid @ModelAttribute("account") LoginDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("messL", "Sai dữ liệu, mời nhập lại");
			return new ModelAndView("customerUI/login", model);
		}

		Account account=accountService.login(dto.getUsername(), dto.getPassword());
		if (account==null) {
			model.addAttribute("messL", "Không tìm thấy thông tin đăng nhập");
			return new ModelAndView("customerUI/login", model);
		}

		session.setAttribute("username", account.getUsername());
		session.setAttribute("role", account.getRole());


		if (account.getRole().equals("user"))  return RedirectHelper.redirectTo("/web/index");

		return RedirectHelper.redirectTo("/web/admin/manufacturer/view");
	}

	@PostMapping("/pRegister")
	public ModelAndView saveorUpdate(ModelMap model, 
			@Valid @ModelAttribute("register") LoginDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("messR", "Sai dữ liệu đầu vào");
			return new ModelAndView("customerUI/login");
		}
		
		if (!dto.getConfirmPassword().equals(dto.getPassword())) {
			model.addAttribute("messR", "Đăng kí thất bại! Mật khẩu không trùng khớp");
			return new ModelAndView("customerUI/register");
		}
		
		if (accountService.existsById(dto.getUsername())) {
			model.addAttribute("messR", "Tên đăng nhập đã tồn tại");
			return new ModelAndView("customerUI/register");
		}
		Account entity = new Account();
		
		BeanUtils.copyProperties(dto, entity);
		
//		Khi người dùng đăng kí ở form của người dùng, thì mặc định set role cho người dùng là 'user'
		entity.setRole("user");
		
		accountService.save(entity);
		
		/*Khi người dùng đăng kí thành công thì tự động set userID ngẫu nhiên vào bảng User,
		 sau đó cũng lưu username vừa được đăng kí vào bảng User*/
		User user=new User();
		user.setUserId("U-"+RamdomID.generateRandomId(10));
		user.setAccount(new Account(entity.getUsername()));
		user.setEnabled(true);
		userService.save(user);
		
		model.addAttribute("messL", "Đăng kí thành công");
		 return RedirectHelper.redirectTo("/web/account/login");
	}

}
