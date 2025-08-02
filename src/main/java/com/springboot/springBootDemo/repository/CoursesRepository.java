package com.springboot.springBootDemo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.springBootDemo.model.Courses;
@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>{

}
