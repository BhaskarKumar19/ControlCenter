package practice.spring.utils;

import practice.spring.exceptions.SystemErrorException;

public interface UserService {

	public void createUser(Integer id, Object value) throws SystemErrorException;

	public void updateUser(Integer id, Object value);

	public Object getUser(Integer id);

	public Object getUserList(Integer id) throws SystemErrorException;

	public Object deleteUser(Integer id);

}
