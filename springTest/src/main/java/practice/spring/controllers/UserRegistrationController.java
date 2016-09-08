package practice.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import practice.spring.pojo.User;
import practice.spring.validator.userValidator;

@SessionAttributes("registrationForm")
@Controller
public class UserRegistrationController {
	@Autowired
	userValidator validator;

	@ModelAttribute
	public void addUserToModel(Model model) {
		if (!model.containsAttribute("registrationForm")) {
			model.addAttribute("registrationForm", new User());
		}
	}

	@RequestMapping(value = "/userRegistration", method = RequestMethod.GET)
	public String registerForm(@ModelAttribute("registrationForm") User user, ModelMap model) {

		System.out.println("userRegistration::" + user.getUserName());
		System.out.println("userRegistration::" + user.getUserEmail());
		return "userRegistration";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("registrationForm") @Valid User user, BindingResult result,
			ModelMap model) {
		System.out.println("registerUser::" + user.getUserName());
		System.out.println("registerUser::" + user.getUserEmail());
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "userRegistration";
		} else {
			return "redirect:userDetails";

		}
	}

	@RequestMapping(value = "/userDetails", method = RequestMethod.GET)
	public String userDetails(@ModelAttribute("registrationForm") User user, ModelMap model) {

		System.out.println("userDetails::" + user.getUserName());
		System.out.println("userDetails::" + user.getUserEmail());
		model.addAttribute("name", user.getUserName());
		model.addAttribute("email", user.getUserEmail());
		model.addAttribute("user", user);

		return "userDetails";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		request.getSession(false).invalidate();
		model.clear();
		return "redirect:logoutMsg";
	}

	@RequestMapping(value = "/logoutMsg", method = RequestMethod.GET)
	public String logoutPage(ModelMap model) {
		model.addAttribute("logout", "logout");
		return "userRegistration";
	}
}
