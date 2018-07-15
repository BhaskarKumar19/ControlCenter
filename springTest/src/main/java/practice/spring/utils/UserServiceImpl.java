package practice.spring.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.hibernate.entities.User;

import practice.spring.exceptions.DatabaseException;

@Component
@Transactional(rollbackFor = DatabaseException.class)
public class UserServiceImpl implements UserService {
	@Qualifier("userDbOperationImpl2")
	@Autowired
	DatabaseOperations userDbOperationImpl2;

	public DatabaseOperations getUserDAO() {
		return userDbOperationImpl2;
	}

	public void setUserDAO(UserDbOperationImpl2 userDAO) {
		userDbOperationImpl2 = userDAO;
	}

	public void createUser(int id, Object value) throws DatabaseException {
		// TODO Auto-generated method stub
		try {
			userDbOperationImpl2.createRepo(id, value);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}

	}

	public void updateUser(int id, Object value) throws DatabaseException {
		try {
			userDbOperationImpl2.updateRepo(id, value);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}
	}

	public Object getUser(int id) throws DatabaseException {
		try {
			return userDbOperationImpl2.getRepo(id);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}
	}

	public List<User> getUserList(int id) throws DatabaseException {
		try {
			return (List<User>) userDbOperationImpl2.getRepoList(id);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}
	}

	public Object deleteUser(int id) throws DatabaseException {
		try {
				userDbOperationImpl2.deleteRepo(id);
				return null;
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserByProperty(String property, Object value) throws DatabaseException {
		try {
			return (List<User>) userDbOperationImpl2.getByProperty(property, value);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}
	}

}
