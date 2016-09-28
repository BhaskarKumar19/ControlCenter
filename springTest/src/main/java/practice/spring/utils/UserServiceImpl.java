package practice.spring.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import practice.spring.exceptions.SystemErrorException;
import practice.spring.pojo.User;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	UserDbOperationsImpl UserDAO;

	public UserDbOperationsImpl getUserDAO() {
		return UserDAO;
	}

	public void setUserDAO(UserDbOperationsImpl userDAO) {
		UserDAO = userDAO;
	}

	public void createUser(Integer id, Object value) throws SystemErrorException {
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

	public void updateUser(Integer id, Object value) {
		// TODO Auto-generated method stub

	}

	public Object getUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUserList(Integer id) throws SystemErrorException {
		try {
			return UserDAO.getRepoList(id);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new SystemErrorException("Database Error::"+ex.getCause().toString());
		} catch (Exception ex) {
			// now display this message using model
			throw new SystemErrorException("Database Error::"+ex.getCause().toString());
		}
	}

	public Object deleteUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
