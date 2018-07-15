package practice.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.hibernate.entities.User;


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
