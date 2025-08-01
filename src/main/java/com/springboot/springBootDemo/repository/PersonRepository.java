package com.springboot.springBootDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.springBootDemo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person readByName(String id);

	

}
