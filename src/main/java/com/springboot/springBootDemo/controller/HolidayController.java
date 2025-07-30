package com.springboot.springBootDemo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.springBootDemo.model.Holiday;
import com.springboot.springBootDemo.repository.HolidaysRepository;



@Controller
public class HolidayController {

    @Autowired
    private HolidaysRepository holidaysRepository;
    
	@GetMapping("/holidays")
    public String displayHolidays(@RequestParam(required = false) boolean festival,
                                  @RequestParam(required = false) boolean federal,Model model) {
        model.addAttribute("festival",festival);
        model.addAttribute("federal",federal);
        

        Iterable<Holiday> holidays = holidaysRepository.findAll();
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(),false).collect(Collectors.toList());
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }	
}
