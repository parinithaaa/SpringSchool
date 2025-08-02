package com.springboot.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.springBootDemo.model.Person;
import com.springboot.springBootDemo.repository.PersonRepository;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
	@Controller
	public class DashboardController {

	 @Autowired
	  PersonRepository personRepository;

	    @RequestMapping("/dashboard")
	    public String displayDashboard(Model model,Authentication authentication, HttpSession session) {
	        Person person = personRepository.readByName(authentication.getName());
	        model.addAttribute("username", person.getName());
	        model.addAttribute("roles", authentication.getAuthorities().toString());
	        if(null != person.getSpringClass() && null != person.getSpringClass().getName()){
	            model.addAttribute("enrolledClass", person.getSpringClass().getName());
	        }
	        session.setAttribute("loggedInPerson", person);
	        return "dashboard.html";
	    }
}


