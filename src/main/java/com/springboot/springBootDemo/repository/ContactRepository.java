package com.springboot.springBootDemo.repository;


import java.sql.PreparedStatement;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.springBootDemo.rowMapper.ContactRowMapper;
import com.springboot.springBootDemo.model.Contact;
import com.springboot.springBootDemo.model.Holiday;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer>{
	List<Contact> findByStatus(String status);
	Optional<Contact> findByContactId(int contactId);
	
	@Query("SELECT c FROM Contact c WHERE c.status = :status")
    //@Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status",nativeQuery = true)
	Page<Contact> findByStatus(String status, Pageable pageable);
	
	 @Transactional
	    @Modifying
	    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
	    int updateStatusById(String status, int id);

	    Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);
	    
	    @Query(nativeQuery = true)
	    Page<Contact> findOpenMsgsNative(@Param("status") String status, Pageable pageable);

	    @Transactional
	    @Modifying
	    @Query(nativeQuery = true)
	    int updateMsgStatusNative(String status, int id);

}
