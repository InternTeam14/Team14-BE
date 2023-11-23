package com.be.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import com.be.dto.UserDTO;
import com.be.entities.Manufacturer;
import com.be.entities.User;
import com.be.service.ManufacturerService;
import com.be.service.UserService;
import com.be.utils.RedirectHelper;


@Controller
@RequestMapping("web/admin/user/")
public class ManagementUserController {

	@Autowired
	private UserService userService;

	public void fillToTable(ModelMap model) {
		List<User> list = userService.findAll();
		model.addAttribute("users", list);
	}
	
	@GetMapping("view")
	public String viewUser(ModelMap model) {
		fillToTable(model);
		return "adminUI/managementUser";
	}
	
	public void fillUserInfo(ModelMap model, String userID) {
		try {
			Optional<User> user = userService.findById(userID);
			User getUser = user.get();
			model.addAttribute("user", getUser);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}
	
	@GetMapping("detail/{userID}")
	public String viewProductDetail(ModelMap model, @PathVariable("userID") String userID) {
		fillUserInfo(model, userID);
		
		return "adminUI/userDetail";
	}
	
	@PostMapping("update")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("user") UserDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView(viewUser(model));
		}
		User entity=new User();
		BeanUtils.copyProperties(dto, entity);
		
		userService.save(entity);
		model.addAttribute("mess", "Người dùng "+dto.getFullName()+" đã được cập nhật");
		return RedirectHelper.redirectTo("/web/admin/user/detail/"+entity.getUserId());
	}
	

	@GetMapping("lockOrUnlock/{userID}/{status}")
	public ModelAndView updateEnabled(ModelMap model,@PathVariable("userID") String userID, @PathVariable("status") Boolean enabled) {
		userService.updateEnabled(!enabled, userID);
		
		return RedirectHelper.redirectTo("/web/admin/user/detail/"+userID);
	}
}
