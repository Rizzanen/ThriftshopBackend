package com.example.thriftshop.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ThymeleafController {

    
    	@RequestMapping(value = {"/APIDocumentation", "/"})
    public String APIDocumentation() {
        return "APIDocumentation";
    }
}
