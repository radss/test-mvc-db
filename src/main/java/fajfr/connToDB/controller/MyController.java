package fajfr.connToDB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

@RequestMapping("/show")
public ModelAndView showView(){
	String message = "My hello world";
	
	return new ModelAndView("view", "message", message);
}
	
}
