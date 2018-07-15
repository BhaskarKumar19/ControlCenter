package practice.spring.utils;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.hibernate.entities.User;


@Repository
public class UserDbOperationImpl2 implements DatabaseOperations{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

	public void deleteRepo(int id) {
		Object persistentInstance = getCurrentSession().load(User.class, id);
		if (persistentInstance != null) {
			getCurrentSession().delete(persistentInstance);
		}
		
	}

	public long createRepo(int id, Object value) {
		Serializable newId = getCurrentSession().save((User) value);
		return ((Integer) newId).longValue();
	}

	@SuppressWarnings("unchecked")
	public Object getByProperty(String property, Object value) {
		return (List<User>) getCurrentSession().createCriteria(User.class).add(Restrictions.like(property, value))
				.list();
	}

	public void updateRepo(int id, Object value) {
		getCurrentSession().update((User) value);
		
	}

	public Object getRepo(int id) {
		return getCurrentSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public Object getRepoList(int id) {
		return (List<User>) getCurrentSession().createCriteria(User.class).list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

