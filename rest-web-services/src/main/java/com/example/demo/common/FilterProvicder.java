package com.example.demo.common;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.example.demo.filter.Student;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class FilterProvicder {

	
	public MappingJacksonValue filterStudent(Student stud, HashSet<String> arr) {
		
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(arr);
		FilterProvider filters =  new SimpleFilterProvider().addFilter("BeanStudent", filter);
		
		
		MappingJacksonValue mapping = new MappingJacksonValue(stud);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
}
