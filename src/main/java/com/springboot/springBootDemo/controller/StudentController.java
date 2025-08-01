package com.springboot.springBootDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.springBootDemo.model.Person;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("student")
public class StudentController {
	 @GetMapping("/displayCourses")
	    public ModelAndView displayCourses(Model model, HttpSession session) {
	        Person person = (Person) session.getAttribute("loggedInPerson");
	        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
	        modelAndView.addObject("person",person);
	        return modelAndView;
	    }
}
