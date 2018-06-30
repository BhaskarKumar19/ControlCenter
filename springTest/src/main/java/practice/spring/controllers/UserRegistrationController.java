package practice.spring.controllers;

import java.util.List;

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

import practice.spring.exceptions.DatabaseException;
import practice.spring.exceptions.SystemErrorException;
import practice.spring.pojo.Device;
import practice.spring.pojo.User;
import practice.spring.service.DeviceService;
import practice.spring.service.impl.UserServiceImpl;
import practice.spring.validator.userValidator;

@SessionAttributes("registrationForm")
@Controller
public class UserRegistrationController {
	@Autowired
	userValidator validator;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	DeviceService deviceServiceImpl;

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
			try {
				userServiceImpl.createUser(0, user);
				Device device = new Device();
				device.setDeviceName(user.getUserName());
				device.setDeviceId("dummyID");
				device.setDeviceStatus("OFFLINE");
				deviceServiceImpl.createDevice(0, device);

			} catch (SystemErrorException|DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		List<User> userList = null;
		List<Device> deviceList = null;
		try {
			userList = userServiceImpl.getUserList(0);
			deviceList = deviceServiceImpl.getDeviceList(0);
			//deviceList = deviceServiceImpl.getDeviceByProperty("deviceName", "Bhaskar");

		} catch (SystemErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("userList", userList);
		model.addAttribute("deviceList", deviceList);
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
