package com.liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication  
public class ExampleSpringBoot {  
      
	
	@RequestMapping("/hello")  
    String home() {  
        return "Spring Boot 大爷！";  
    } 
	
	
	
    public static void main(String[] args) {  
        SpringApplication.run(ExampleSpringBoot.class, args);    
    }  
} 
