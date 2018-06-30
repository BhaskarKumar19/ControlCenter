package practice.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import practice.spring.dao.DatabaseOperations;
import practice.spring.dao.impl.UserDbOperationsImpl;
import practice.spring.exceptions.SystemErrorException;
import practice.spring.pojo.User;
import practice.spring.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	//@Qualifier("userDbOperationsImpl")
	@Qualifier("userDbOperationsImMemImpl")
	DatabaseOperations UserDAO;

	public DatabaseOperations getUserDAO() {
		return UserDAO;
	}

	public void setUserDAO(UserDbOperationsImpl userDAO) {
		UserDAO = userDAO;
	}

	public void createUser(int id, Object value) throws SystemErrorException {
		// TODO Auto-generated method stub
		try {
			UserDAO.createRepo(id, value);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new SystemErrorException("Database Error::"+ex.getCause().toString());
		} catch (Exception ex) {
			// now display this message using model
			throw new SystemErrorException("Database Error::"+ex.getCause().toString());
		}

	}

	public void updateUser(int id, Object value) {
		// TODO Auto-generated method stub

	}

	public Object getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUserList(int id) throws SystemErrorException {
		try {
			return (List<User>) UserDAO.getRepoList(id);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new SystemErrorException("Database Error::"+ex.getCause().toString());
		} catch (Exception ex) {
			// now display this message using model
			throw new SystemErrorException("Database Error::"+ex.getCause().toString());
		}
	}

	public Object deleteUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}