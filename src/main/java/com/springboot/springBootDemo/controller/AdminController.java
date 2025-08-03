package com.springboot.springBootDemo.controller;

import java.util.List;

import java.util.Optional;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.springBootDemo.model.Courses;
import com.springboot.springBootDemo.model.Person;
import com.springboot.springBootDemo.model.SpringClass;
import com.springboot.springBootDemo.repository.CoursesRepository;
import com.springboot.springBootDemo.repository.PersonRepository;
import com.springboot.springBootDemo.repository.SpringClassRepository;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	SpringClassRepository springClassRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	CoursesRepository coursesRepository;

	@RequestMapping("/displayClasses")
	public ModelAndView displayClasses(Model model) {
		List<SpringClass> springClasses = springClassRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("classes.html");
		modelAndView.addObject("springClasses", springClasses);
		modelAndView.addObject("springClass", new SpringClass());
		return modelAndView;
	}

	@PostMapping("/addNewClass")
	public ModelAndView addNewClass(Model model, @ModelAttribute("springClass") SpringClass springClass) {
		springClassRepository.save(springClass);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
		return modelAndView;
	}

	@RequestMapping("/deleteClass")
	public ModelAndView deleteClass(Model model, @RequestParam int id) {
		Optional<SpringClass> springClass = springClassRepository.findById(id);
		for (Person person : springClass.get().getPersons()) {
			person.setSpringClass(null);
			personRepository.save(person);
		}
		springClassRepository.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
		return modelAndView;
	}

	@GetMapping("/displayStudents")
	public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
			@RequestParam(value = "error", required = false) String error) {
		String errorMessage = null;
		ModelAndView modelAndView = new ModelAndView("students.html");
		Optional<SpringClass> springClass = springClassRepository.findById(classId);
		modelAndView.addObject("springClass", springClass.get());
		modelAndView.addObject("person", new Person());
		session.setAttribute("springClass", springClass.get());
		if (error != null) {
			errorMessage = "Invalid Email entered!!";
			modelAndView.addObject("errorMessage", errorMessage);
		}
		return modelAndView;
	}

	@PostMapping("/addStudent")
	public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		SpringClass springClass = (SpringClass) session.getAttribute("springClass");
		Person personEntity = personRepository.readByEmail(person.getEmail());
		if (personEntity == null || !(personEntity.getPersonId() > 0)) {
			modelAndView
					.setViewName("redirect:/admin/displayStudents?classId=" + springClass.getClassId() + "&error=true");
			return modelAndView;
		}
		personEntity.setSpringClass(springClass);
		personRepository.save(personEntity);
		springClass.getPersons().add(personEntity);
		springClassRepository.save(springClass);
		modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + springClass.getClassId());
		return modelAndView;
	}

	@GetMapping("/deleteStudent")
	public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
		SpringClass springClass = (SpringClass) session.getAttribute("springClass");
		Optional<Person> person = personRepository.findById(personId);
		person.get().setSpringClass(null);
		springClass.getPersons().remove(person.get());
		SpringClass springClassSaved = springClassRepository.save(springClass);
		session.setAttribute("springClass", springClassSaved);
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/admin/displayStudents?classId=" + springClass.getClassId());
		return modelAndView;
	}

	@GetMapping("/displayCourses")
	public ModelAndView displayCourses(Model model) {
		List<Courses> courses = coursesRepository.findByOrderByName();
		ModelAndView modelAndView = new ModelAndView("courses_admin.html");
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("course", new Courses());
		return modelAndView;
	}

	@PostMapping("/addNewCourse")
	public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses course) {
		ModelAndView modelAndView = new ModelAndView();
		coursesRepository.save(course);
		modelAndView.setViewName("redirect:/admin/displayCourses");
		return modelAndView;
	}

	@GetMapping("/viewStudents")
	public ModelAndView viewStudents(Model model, @RequestParam int id, HttpSession session,
			@RequestParam(required = false) String error) {
		String errorMessage = null;
		ModelAndView modelAndView = new ModelAndView("course_students.html");
		Optional<Courses> courses = coursesRepository.findById(id);
		modelAndView.addObject("courses", courses.get());
		modelAndView.addObject("person", new Person());
		session.setAttribute("courses", courses.get());
		if (error != null) {
			errorMessage = "Invalid Email entered!!";
			modelAndView.addObject("errorMessage", errorMessage);
		}
		return modelAndView;
	}

	@PostMapping("/addStudentToCourse")
	public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person person, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		Courses courses = (Courses) session.getAttribute("courses");
		Person personEntity = personRepository.readByEmail(person.getEmail());
		if (personEntity == null || !(personEntity.getPersonId() > 0)) {
			modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courses.getCourseId() + "&error=true");
			return modelAndView;
		}
		personEntity.getCourses().add(courses);
		courses.getPersons().add(personEntity);
		personRepository.save(personEntity);
		session.setAttribute("courses", courses);
		modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courses.getCourseId());
		return modelAndView;
	}

	@GetMapping("/deleteStudentFromCourse")
	public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId, HttpSession session) {
		Courses courses = (Courses) session.getAttribute("courses");
		Optional<Person> person = personRepository.findById(personId);
		person.get().getCourses().remove(courses);
		courses.getPersons().remove(person);
		personRepository.save(person.get());
		session.setAttribute("courses", courses);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/viewStudents?id=" + courses.getCourseId());
		return modelAndView;
	}

}
