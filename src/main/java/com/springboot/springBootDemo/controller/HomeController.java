package com.springboot.springBootDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(value={"home","/","start"})
	public String display(Model model) {
		model.addAttribute("name","parinithaaa m s");
		return "home.html";
	}
}
