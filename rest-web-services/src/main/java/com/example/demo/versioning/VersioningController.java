package com.example.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

	
	@GetMapping("v1/person")
	public Person1 getPerson1() {
		
		return new Person1("yaried tekie");
	}
	
	@GetMapping("v2/person")
	public Person2 getPerson2() {
		
		return new Person2(new Name("saliem", "bokre"));
	}
	
	
	//Parameter
	@GetMapping(value = "person/param", params ="version=1")
	public Person1 paramPerson1() {
		return new Person1("yaried tekie");
	}
	
	@GetMapping(value = "person/param", params ="version=2")
	public Person2 paramPerson2() {
		return new Person2(new Name("saliem", "bokre"));
	}
	
	//Header
	@GetMapping(value = "person/header", headers ="X-API-VERSION=1")
	public Person1 headerPerson1() {
		return new Person1("yaried tekie");
	}
	
	@GetMapping(value = "person/header", headers ="X-API-VERSION=2")
	public Person2  headerPerson2() {
		return new Person2(new Name("saliem", "bokre"));
	}
	
	//Produces
	@GetMapping(value = "person/produces", produces ="application/company.api-v1+json")
	public Person1 producePerson1() {
		return new Person1("yaried tekie");
	}
	
	@GetMapping(value = "person/produces", produces ="application/company.api-v2+json")
	public Person2  prodcuePerson2() {
		return new Person2(new Name("saliem", "bokre"));
	}
	
}
