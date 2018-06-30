package practice.spring.dao.inMem.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import practice.spring.dao.DatabaseOperations;
import practice.spring.pojo.User;

@Component
public class UserDbOperationsImMemImpl implements DatabaseOperations {

	Map<Integer, User> usersDb=new HashMap<Integer, User>();

	public void deleteRepo(int id) {
		usersDb.remove(id);
		return;

	}

	public long createRepo(int id, Object value) throws DataAccessException {
		User user = (User) value;
		int index=usersDb.size()+1;
		usersDb.put(index, user);
		return index;
	}

	public void updateRepo(int id, Object value) {
		usersDb.put(id, (User) value);
	}

	public User getRepo(int id) {
		return usersDb.get(id);

	}

	public List<User> getRepoList(int id) {
		List<User> users =  usersDb.values().stream().map(user-> new User(user)).collect(Collectors.toList());
		return users;
	}

	public Object getByProperty(String property, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

}
