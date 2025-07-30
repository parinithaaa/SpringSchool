package com.springboot.springBootDemo.service;

import java.time.LocalDateTime;
import java.util.List;

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
	
	public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(Constants.OPEN);
        return contactMsgs;
    }
	
	public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId,Constants.CLOSE, updatedBy);
        if(result>0) {
            isUpdated = true;
        }
        return isUpdated;
    }

}
