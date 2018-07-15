package practice.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import practice.spring.dao.UserDetailsDao;
import practice.spring.entities.Device;
import practice.spring.entities.UserDetails;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDetailsDao userDetailsDao;

	public long createUser(UserDetails userDetails) {
		return (Long) userDetailsDao.createUser(userDetails);
	}

	public void updateUser(UserDetails userDetails) {
		userDetailsDao.updateUser(userDetails);

	}
    
	public List<UserDetails> getAllUsers() {
		/*for (UserDetails element : userDetailsDao.getAllUsers()) {
			for (Device iterable_element : element.getDevices()) {
				System.out.println(iterable_element);
			}
		}*/
		return userDetailsDao.getAllUsers();
	}
}
