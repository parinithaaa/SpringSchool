package com.springboot.springBootDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.springBootDemo.constants.Constants;
import com.springboot.springBootDemo.model.Person;
import com.springboot.springBootDemo.model.Roles;
import com.springboot.springBootDemo.repository.PersonRepository;
import com.springboot.springBootDemo.repository.RolesRepository;

@Service
public class PersonService {
	
	@Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(Constants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}
