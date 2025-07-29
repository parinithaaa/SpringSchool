package com.springboot.springBootDemo.controller;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.springBootDemo.model.Contact;
import com.springboot.springBootDemo.service.ContactService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
@Slf4j
@Controller
public class ContactController {
	@RequestMapping("/contact")
	public String display(Model model) {
		model.addAttribute("contact",new Contact());
		return "contact.html";
	}
	
	private final ContactService con;
	
	@Autowired
	public ContactController(ContactService con) {
		super();
		this.con = con;
	}


	@RequestMapping(value = "/saveMsg",method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact,Errors errors){

        if(errors.hasErrors()){
            log.error("Contact form validation failed due to : " + errors.toString());
            return "contact.html";
        }
        con.saveMsgDetails(contact);
        return "redirect:/contact";
    }

	
}
