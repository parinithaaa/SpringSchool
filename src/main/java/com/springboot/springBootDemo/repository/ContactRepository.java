package com.springboot.springBootDemo.repository;


import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import com.springboot.springBootDemo.rowMapper.ContactRowMapper;
import com.springboot.springBootDemo.model.Contact;
import com.springboot.springBootDemo.model.Holiday;

@Repository
public interface ContactRepository extends CrudRepository<Contact,String>{
	List<Contact> findByStatus(String status);
	Optional<Contact> findByContactId(int contactId);
}
