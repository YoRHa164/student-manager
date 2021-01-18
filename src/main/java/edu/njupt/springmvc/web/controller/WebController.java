package edu.njupt.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	@RequestMapping("/")
	public String indexPage() {
		return "redirect:/pages/index.html";
	}
}
