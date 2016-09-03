package practice.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import practice.spring.pojo.User;

@Controller
public class HelloWorldController {

	@RequestMapping("/helloserv")
	public ModelAndView welcomeMessage() {
		// Name of your jsp file as parameter
		//ModelAndView view = new ModelAndView("hello");
		//ModelAndView view = new ModelAndView("userRegistration");
		//view.addObject("name", name);
		//return view;
		return new ModelAndView("userRegistration","registration",new User());
	}
}
