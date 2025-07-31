package com.springboot.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.springBootDemo.model.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {
	

//	PersonService personService;
	
	@RequestMapping(value="/register",method= {RequestMethod.GET})
	public String displayRegisterPage(Model model) {
		model.addAttribute("person",new Person());
		return "register.html";
	}
	
}
