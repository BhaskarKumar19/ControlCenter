package practice.spring.utils;

import java.util.List;

import com.spring.hibernate.entities.Device;
import com.spring.hibernate.entities.User;

import practice.spring.exceptions.DatabaseException;

public interface UserService {

	public void createUser(int id, Object value) throws DatabaseException; 

	public void updateUser(int id, Object value) throws DatabaseException;

	public Object getUser(int id) throws DatabaseException;
	
	public List<User> getUserByProperty(String property, Object value) throws DatabaseException;

	public Object getUserList(int id) throws DatabaseException;

	public Object deleteUser(int id) throws DatabaseException;

}
