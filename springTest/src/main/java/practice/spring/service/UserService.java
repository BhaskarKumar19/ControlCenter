package practice.spring.service;

import practice.spring.exceptions.SystemErrorException;

public interface UserService {

	public void createUser(int id, Object value) throws SystemErrorException;

	public void updateUser(int id, Object value);

	public Object getUser(int id);

	public Object getUserList(int id) throws SystemErrorException;

	public Object deleteUser(int id);

}
