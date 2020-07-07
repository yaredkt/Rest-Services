package com.example.demo.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	
	public static final Contact DEFAULT_CONTACT = new Contact("Yaried Tekie", "https://www.hani2016", "hani@gmail.com");
	@SuppressWarnings("rawtypes")
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("User Info Documentation", "Awesome Documentation", "1.0", 
			"urn:tos", DEFAULT_CONTACT, "Apache 2.0", 
			"http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
	private static final Set<String> DEFAULT_CONSUME_AND_PRODUCES = new HashSet<>(Arrays.asList("application/json", "application/xml"));

	@Bean
	@Primary
	public Docket  api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_CONSUME_AND_PRODUCES)
				.consumes(DEFAULT_CONSUME_AND_PRODUCES);
			
	}
	
	/*@Bean
	public LinkDiscoverers discovers() {    
	    List<LinkDiscoverer> plugins = new ArrayList<>();
	    plugins.add(new CollectionJsonLinkDiscoverer());
	    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));  
	} 
	*/

}
