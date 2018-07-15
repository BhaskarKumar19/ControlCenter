package practice.spring.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.hibernate.entities.User;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("ID"));
		user.setUserName(rs.getString("NAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setUserEmail(rs.getString("EMAIL"));
		user.setAccessToken(rs.getString("ACCESS_KEY"));
		return user;
	}

}
