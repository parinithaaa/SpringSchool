package com.springboot.springBootDemo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.springboot.springBootDemo.constants.Constants;
import com.springboot.springBootDemo.model.Contact;
import com.springboot.springBootDemo.repository.ContactRepository;

@Service

public class ContactService {
	
	 @Autowired
	  private ContactRepository contactRepository;

	
	
	

	public boolean saveMsgDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(Constants.OPEN);
        contact.setCreatedBy(Constants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if(result > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
