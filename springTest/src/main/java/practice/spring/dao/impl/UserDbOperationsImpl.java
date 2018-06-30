package practice.spring.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import practice.spring.dao.DatabaseOperations;
import practice.spring.dao.UserMapper;
import practice.spring.pojo.User;

@Component
public class UserDbOperationsImpl implements DatabaseOperations {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void deleteRepo(int id) {
		String SQL = "delete from USER where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;

	}

	public long createRepo(int id, Object value) throws DataAccessException {

		User user = (User) value;
		String SQL = "insert into USER (NAME, EMAIL,PASSWORD,ACCESS_KEY) values (? , ? , ?, ?)";
		Integer i = jdbcTemplateObject.update(SQL, user.getUserName(), user.getUserEmail(), user.getPassword(),
				user.getAccessToken());
		System.out.println("Created Record Name = " + user.getUserName() + " Email = " + user.getUserEmail());
		return i.longValue();

	}

	public void updateRepo(int id, Object value) {
		String SQL = "update USER set NAME = ?, EMAIL = ?, PASSWORD = ?, ACCESS_KEY = ? where id = ?";
		User user = (User) value;
		jdbcTemplateObject.update(SQL, user.getUserName(), user.getUserEmail(), user.getPassword(),
				user.getAccessToken());
		System.out.println("Updated Record with ID = " + id);
		return;

	}

	public User getRepo(int id) {
		String SQL = "select * from USER where id = ?";
		User user = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new UserMapper());
		return user;

	}

	public List<User> getRepoList(int id) {
		String SQL = "select * from USER";
		List<User> students = jdbcTemplateObject.query(SQL, new UserMapper());
		return students;

	}

	public Object getByProperty(String property, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
