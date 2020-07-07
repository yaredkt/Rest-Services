package com.example.demo.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.FilterProvicder;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController 
public class FilteringController {

	/*@Autowired
	FilterProvicder filterProvider;*/
	// should return id, name and age
	@GetMapping(path = "/filter")
	public MappingJacksonValue findStudent() {
		
	Student std = new  Student("76df3", "yaried Tekie", 32, "computer Science");
	
	
	//filterProvider.filterStudent(std, new HashSet<String>(Arrays.asList("id","name","age")));
	
	
	SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "age");
	FilterProvider filters =  new SimpleFilterProvider().addFilter("BeanStudent", filter);
	
	
	MappingJacksonValue mapping = new MappingJacksonValue(std);
	
	mapping.setFilters(filters);
	
	return mapping;
	}
	
	//should return id, name and department
	@GetMapping(path = "/filter-list")
	public MappingJacksonValue findListStudent() {
		
		List<Student> students =   Arrays.asList(new Student("76df3", "yaried Tekie", 32, "computer Science"), new Student("76df3", "yaried Tekie", 32, "computer Science"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "department");
		FilterProvider filters =  new SimpleFilterProvider().addFilter("BeanStudent", filter);
		
		
		
		MappingJacksonValue mapping = new MappingJacksonValue(students);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
}
