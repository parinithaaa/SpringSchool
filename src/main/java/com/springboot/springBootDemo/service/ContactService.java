package com.springboot.springBootDemo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.springboot.springBootDemo.model.Contact;

@Service
@RequestScope
public class ContactService {
	int counter;
	
	public ContactService(int counter) {
		super();
		this.counter = counter;
	}

		public static boolean saveMsgDetails(Contact contact) {
//			later we'll add db 
			System.out.println(contact.getEmail()+" "+contact.getName());
			return true;
		}
}
