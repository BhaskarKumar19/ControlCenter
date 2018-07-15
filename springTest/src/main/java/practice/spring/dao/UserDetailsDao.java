package practice.spring.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import practice.spring.entities.UserDetails;

@Component
@Repository
public class UserDetailsDao {
	@Autowired
	private SessionFactory sessionFactory;
	int i;

	Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public long createUser(UserDetails userDetails) {
		UserDetails u=(UserDetails) getCurrentSession().get(UserDetails.class, userDetails.getUserId());
		if (u != null) {
			getCurrentSession().update(userDetails);
			return userDetails.getUserId();
		} else {
			return (Long) (getCurrentSession().save(userDetails));
		}
	}

	public void updateUser(UserDetails userDetails) {
		getCurrentSession().update(userDetails);

	}

	@SuppressWarnings("unchecked")
	public List<UserDetails> getAllUsers() {
		return (List<UserDetails>) getCurrentSession().createCriteria(UserDetails.class).list();
	}
}
