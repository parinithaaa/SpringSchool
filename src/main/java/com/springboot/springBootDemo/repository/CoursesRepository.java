package com.springboot.springBootDemo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.springBootDemo.model.Courses;
@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>{
	 List<Courses> findByOrderByNameDesc();

	    /*
	    The Asc keyword is optional as OrderBy, by default,
	    sorts the results in the ascending order.
	    * */
	    List<Courses> findByOrderByName();
}
