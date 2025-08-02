package com.springboot.springBootDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springBootDemo.model.Address;
import com.springboot.springBootDemo.model.SpringClass;

public interface SpringClassRepository extends JpaRepository<SpringClass, Integer>{

}
