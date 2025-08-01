package com.springboot.springBootDemo.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

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
        Contact contactt = contactRepository.save(contact);
        if(null != contactt && contactt.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }
	
	public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findByStatus(Constants.OPEN);
        return contactMsgs;
    }
	
	public boolean updateMsgStatus(int contactId){
	        boolean isUpdated = false;
	        Optional<Contact> contact = contactRepository.findByContactId(contactId);
	        contact.ifPresent(contact1 -> {
	            contact1.setStatus(Constants.CLOSE);
	        });
	        Contact updatedContact = contactRepository.save(contact.get());
	        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
	            isUpdated = true;
	        }
	        return isUpdated;
	    }


}
