package practice.spring.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import practice.spring.dao.UserDetailsDao;
import practice.spring.entities.Address;
import practice.spring.entities.Device;
import practice.spring.entities.SharedDevice;
import practice.spring.entities.UserDetails;
import practice.spring.services.UserService;





@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/java/practice/spring/test/config/beansConfig.xml",
		"file:src/test/java/practice/spring/test/config/dataSource.xml" })
public class UserTest {

	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserService userService;
	
	private Session session;
	
	@Before
	public void wipeData()
	{
		session=sessionFactory.openSession();
		session.beginTransaction();
		
		session.createSQLQuery("delete from userDetails_device").executeUpdate();
		session.createSQLQuery("delete from device_subtopics").executeUpdate();
		session.createSQLQuery("delete from device_pubtopics").executeUpdate();
		session.createSQLQuery("delete from userDetails").executeUpdate();
		session.createSQLQuery("delete from device").executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		
		/*
		String sql = "drop database junittestdb";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();*/
	}
	
	@Test
	public void TestUser()
	{
		assertTrue("test started",true);
		assertEquals("hello", 1, 1);
		System.out.println("hello there!");

		UserDetails usr=new UserDetails("bhaskar", "bhaskar@spring.com", "2564");
		UserDetails usr2=new UserDetails("bhaskar22", "bhaskar@spring22.com", "256422");
		Address addr= new Address();
		Device dev =new Device("device11", "inactive");
		Device dev2 =new Device("device12", "inactive");
		Device dev3 =new Device("device123", "inactive");
		SharedDevice sdev=new SharedDevice("sdevdemo", "inactive");
		
		dev.getPubTopics().add("pubtopic11");
		dev.getPubTopics().add("pubtopic12");
		dev.getSubTopics().add("subtopic11");
		dev.getSubTopics().add("subtopic12");
		dev.setUserDetails(usr); 
		
		dev2.getPubTopics().add("pubtopic111");
		dev2.getPubTopics().add("pubtopic112");
		dev2.getSubTopics().add("subtopic111");
		dev2.getSubTopics().add("subtopic112");
		dev2.setUserDetails(usr); 
		
		dev3.getPubTopics().add("pubtopic1113");
		dev3.getPubTopics().add("pubtopic1123");
		dev3.getSubTopics().add("subtopic1113");
		dev3.getSubTopics().add("subtopic1123");
		dev3.setUserDetails(usr); 
		
		
		sdev.getSubTopics().add("sharedSubtopic1113");
		sdev.getPubTopics().add("sharedPubtopic11135");
		sdev.getUserDetails().add(usr);
		sdev.getUserDetails().add(usr2);
		usr2.getSharedDevices().add(sdev);
		usr.getSharedDevices().add(sdev);

		
		addr.setCity("Purnea");
		addr.setCountry("India");
		addr.setState("Bihar");
		
		
		usr.setOfficeAddress(addr);
		usr.addDevice(dev);
		usr.addDevice(dev2);
		usr.addDevice(dev3);
		dev.setUserDetails(usr);
		dev2.setUserDetails(usr);
		dev3.setUserDetails(usr);
		
		long lon=userService.createUser(usr);
		long lon2=userService.createUser(usr2);
				
		  
		
		//List<UserDetails> userList=userService.getAllUsers();
		
		// 1. mixing session with Tx annotaion and manual session manipulation
		// 2. problem with eger fetching>> mutiple rows
		// 3. no need of mapped by in one to may mapping
		// 4. id needed in entity class?? check again
		// 5. once object is populated with child results, it is availabe even after the session is closed
		
		//cascade type when many to many relationship>> created duplicate obj when cascade type was
		// defined on both side
		// mapped by aslo created issue
		//?? what happens when same object is saved twice
		
		System.out.println("session open111="+session.isOpen());
		
		session=sessionFactory.openSession();
		session.getTransaction().begin();
		session.isOpen();
		session.isDirty();
		
		UserDetails obj=(UserDetails) session.get(UserDetails.class, 1L);
		System.out.println("session open="+session.isOpen());
		//System.out.println(userList.get(0).getDevices());
		
		if (obj != null) {
			System.out.println(obj.getUserName());
			System.out.println(obj.getEmail());

			System.out.print(":::::::::::::::::" + obj.getDevices().size());
			for (Device device : obj.getDevices()) {
				System.out.print(":::::" + device.getName());
			}
		}
			
		
		//session.getTransaction().commit();
		//session.close();
		
		//System.out.print("::::usrDetail:::::"+userService.getAllUsers());
		
		
		//usr.getDevices().add(dev2);
		
		//session.save(dev);
		//session.save(dev2);
		//session.save(usr);
		
		//session.getTransaction().commit();
		//session.close(); 
		
		
	
		
		
	
	
	}

}
