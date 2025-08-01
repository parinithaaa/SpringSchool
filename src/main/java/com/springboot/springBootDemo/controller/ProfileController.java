package com.springboot.springBootDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.springBootDemo.model.Profile;

import ch.qos.logback.core.model.Model;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {
	
	@RequestMapping("/displayProfile")
	public ModelAndView display(Model model) {
		Profile profile = new Profile();
	ModelAndView modelView = new ModelAndView("profile.html");
	 modelView.addObject("profile",profile);
	return modelView;
	}
}
