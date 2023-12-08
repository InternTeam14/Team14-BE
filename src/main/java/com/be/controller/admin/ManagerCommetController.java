package com.be.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.be.entities.Comment;
import com.be.repository.CommentRESP;

@Controller
@RequestMapping("web/admin/conmmet")
public class ManagerCommetController {
	@Autowired
	CommentRESP commentRESP;
	
	@GetMapping()
	public String viewUser(Model model) {
		List<Comment> comments = commentRESP.findAll();
		System.out.println(comments);
		model.addAttribute("comments", comments);
		return "adminUI/binhLuan";
	}
	
	
	@PostMapping("/update")
	public String update(@ModelAttribute Comment comment) {
	Comment old = 	commentRESP.findById(comment.getComId()).get();
	old.setStatus(comment.getStatus());
	commentRESP.saveAndFlush(old);
		return"redirect:/web/admin/conmmet";
	}
}
