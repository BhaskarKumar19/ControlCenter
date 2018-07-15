package practice.spring.controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.hibernate.entities.Device;
import com.spring.hibernate.entities.Topic;
import com.spring.hibernate.entities.User;
import com.spring.tools.GenericTools;

import integration.CustomGateway;
import practice.spring.exceptions.DatabaseException;
import practice.spring.utils.DeviceService;
import practice.spring.utils.UserService;
import practice.spring.validator.userValidator;

@SessionAttributes("authenticatedUserId")
@Controller
public class UserRegistrationController {
	// contants
	private static final String ADD_DEVICE = "addDevice";
	private static final String USER = "user";
	private static final String AUTHENTICATED_USER_ID = "authenticatedUserId";
	

	@Autowired
	userValidator validator;
	@Autowired
	UserService userServiceImpl;
	@Autowired
	DeviceService deviceServiceImpl;

	@Autowired
	@Qualifier("customGatewayService")
	CustomGateway gatewayService;

	@Autowired
	@Qualifier("mqttInboundAdapter")
	MqttPahoMessageDrivenChannelAdapter messageChannelAdaptor;
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		if (!model.containsAttribute("registrationForm")) {
			model.addAttribute("registrationForm", new User());
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		if (!model.containsAttribute("login")) {
			model.addAttribute("login", new User());
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("login") @Valid User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "login";
		}
		System.out.println("userRegistration::" + user.getUserName());
		System.out.println("userRegistration::" + user.getUserEmail());

		try {
			List<User> registeredUsers = userServiceImpl.getUserByProperty("userEmail", user.getUserEmail());
			for (User registeredUser : registeredUsers) {
				if (registeredUser.getPassword().equals(user.getPassword())) {
					model.addAttribute(AUTHENTICATED_USER_ID, registeredUser.getId());
					return "redirect:landing";
				}
			}

		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.rejectValue("userEmail", "validation.error.invalidUser", "user not registered");
		return "login";

	}

	@RequestMapping(value = "/landing", method = RequestMethod.GET)
	public String landing(@ModelAttribute(AUTHENTICATED_USER_ID) int userId,  ModelMap model) {

		//int userId =(Integer) model.get(AUTHENTICATED_USER_ID);
		try {
			List<User> users = userServiceImpl.getUserByProperty("id", userId);
			// for (User user : users) {
			for (Iterator<User> userItrtr = users.iterator(); userItrtr.hasNext();) {
				User usr = userItrtr.next();
				Set<Device> dev = usr.getDevices();
				model.addAttribute(USER, usr);
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		} 

		return "landing";
	}

	@RequestMapping(value = "/addDeviceForm", method = RequestMethod.GET)
	public String createDevice(ModelMap model) {
		if (!model.containsAttribute(ADD_DEVICE)) {
			model.addAttribute(ADD_DEVICE, new Device());
		}
		return "addDevice";
	}

	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	public String addDevice(@ModelAttribute(AUTHENTICATED_USER_ID) int userId,@ModelAttribute(ADD_DEVICE) Device device, BindingResult result, ModelMap model) {

		//int userId =  (Integer) model.get(AUTHENTICATED_USER_ID);
		System.out.println("addDevice::" + device.getDeviceName());
		System.out.println("addDevice::" + device.getDescription());
		System.out.println("addDevice::" + userId);
		

		// create a device and associate with the user
		// provide a topic on which the device should publish and a topic where
		// it should subscribe
		// use generated device id to detect device
		// generate a random key which should be sent with each message
		// subscribe on the topic to receive message

		// store the data generated on database

		//
		
		Device newDevice = new Device();
		newDevice.setDeviceName(device.getDeviceName());
		newDevice.setDescription(device.getDescription());
		newDevice.setDeviceStatus(Device.OFFLINE);
		newDevice.setDeviceId("DEV_ID_" + GenericTools.getSaltString());
		newDevice.setAccessKey("KEY_" + GenericTools.getSaltString());
		
		Set<Topic> topics=new HashSet();
		Topic topic=new Topic();
		topic.setPublishTopic("PubTopic_"+newDevice.getDeviceId());
		topic.setSubscribeTopic("SubTopic_"+newDevice.getDeviceId());
		topics.add(topic);
		newDevice.setPubSubTopics(topics); 
		try {
			List<User> users = userServiceImpl.getUserByProperty("id", userId);
			//for (User user : users) {
			for(Iterator<User> userItrtr=users.iterator();userItrtr.hasNext();){
				User usr=userItrtr.next();
				Set<Device> dev = usr.getDevices();
				dev.add(newDevice);
				topic.setDevice(newDevice); // important! if not set foreign key was always null, 3hr effort
				userServiceImpl.updateUser(0, usr);
				model.addAttribute(USER, usr);
			}
			
			GenericTools genericTools = new GenericTools();
			genericTools.subscribeTopic(messageChannelAdaptor, "ANY_TOPIC"+newDevice.getDeviceId());
			genericTools.publishOnTopic(gatewayService, "ANY_TOPIC"+newDevice.getDeviceId(), "::TEST-PAYLOAD:: :) ");
			
		} 
		
		catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "landing";
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
			Device device = new Device();
			device.setDeviceName("TEST_DEVICE22");
			device.setDeviceId("dummyID");
			device.setDeviceStatus("ONLINE");

			Set<Device> devices = new HashSet<Device>();
			devices.add(device);
			user.setDevices(devices);
			try {
				userServiceImpl.createUser(0, user);
			} catch (DatabaseException e) {
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
			userList = (List<User>) userServiceImpl.getUserList(0);
			// deviceList = deviceServiceImpl.getDeviceList(0);
			deviceList = deviceServiceImpl.getDeviceList(0);

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
