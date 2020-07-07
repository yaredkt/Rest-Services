package com.example.demo.hello;

import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RestController
public class HelloController {

	@Autowired
	private MessageSource messageSource;
	//@GetMapping(path= "hello-world")
	@RequestMapping(method = RequestMethod.GET, path= "hello")
	public @ResponseBody String  helloWorld() {
		return "Hello-World rest services";
	}
	
	//returning a bean
	@GetMapping(path="/hello-world-bean")
	public  HelloWorldBean  helloWorldBean() {
		return new HelloWorldBean("Hello-World-Bean");
	}
	
	//PathVariable 
	@GetMapping(path="/hello-world-bean/path-variable/{name}/age/{id}")
	public  HelloWorldBean  helloWorldPathVariable(@PathVariable String name, @PathVariable String id) {
		return new HelloWorldBean(String.format("Hello-World-Bean  %s", name + id));
	}
	
	//QueryParameter
	@GetMapping(path= "/hello/query-param")
	public  String  helloWorldQueryParam(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "name", required = true) String name) {
		return "Hello-World message from " + " "  + name  + " " + id;
	}
	
	@GetMapping(path="/hello-world-internalization")
	public  String  helloWorldInternalization(@RequestHeader(name = "Accept-Language", required=false) Locale locale) {
		
		return messageSource.getMessage("good.morning.message", null, locale);
		
		//return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
