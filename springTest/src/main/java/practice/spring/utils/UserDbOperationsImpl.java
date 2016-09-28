package practice.spring.utils;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import practice.spring.pojo.User;

public class UserDbOperationsImpl implements DatabaseOperations {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void deleteRepo(Integer id) {
		String SQL = "delete from USER where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;

	}

	public void createRepo(Integer id, Object value)throws DataAccessException {
		
		User user=(User)value;
		String SQL = "insert into USER (NAME, EMAIL,PASSWORD,ACCESS_KEY) values (? , ? , ?, ?)";
		jdbcTemplateObject.update(SQL, user.getUserName(), user.getUserEmail(), user.getPassword(), user.getAccessToken());
		System.out.println("Created Record Name = " + user.getUserName() + " Email = " + user.getUserEmail());
		return;

	}

	public void updateRepo(Integer id, Object value) {
		String SQL = "update USER set NAME = ?, EMAIL = ?, PASSWORD = ?, ACCESS_KEY = ? where id = ?";
		User user=(User)value;
		jdbcTemplateObject.update(SQL, user.getUserName(), user.getUserEmail(), user.getPassword(), user.getAccessToken());
		System.out.println("Updated Record with ID = " + id);
		return;

	}

	public User getRepo(Integer id) {
		String SQL = "select * from USER where id = ?";
		User user = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new UserMapper());
		return user;

	}

	public List<User> getRepoList(Integer id) {
		String SQL = "select * from USER";
		List<User> students = jdbcTemplateObject.query(SQL, new UserMapper());
		return students;

	}

}
