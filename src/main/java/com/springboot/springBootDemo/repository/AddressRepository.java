package com.springboot.springBootDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springBootDemo.model.Address;
import com.springboot.springBootDemo.model.Roles;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
