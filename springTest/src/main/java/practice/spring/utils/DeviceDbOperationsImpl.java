package practice.spring.utils;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practice.spring.pojo.Device;

@Repository
public class DeviceDbOperationsImpl implements DatabaseOperations {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	public void setDataSource(DataSource ds) {
		// TODO Unused method in hibernate

	}

	public void deleteRepo(int id) {
		Object persistentInstance = getCurrentSession().load(Device.class, id);
		if (persistentInstance != null) {
			getCurrentSession().delete(persistentInstance);
		}
	}

	public long createRepo(int id, Object value) {
		Serializable newId = getCurrentSession().save((Device) value);
		return ((Integer) newId).longValue();
	}

	public void updateRepo(int id, Object value) {
		getCurrentSession().update((Device) value);
	}

	public Object getRepo(int id) {
		return getCurrentSession().get(Device.class, id);
	}

	public Object getRepoList(int id) {
		return (List<Device>) getCurrentSession().createCriteria(Device.class).list();
	}

	public Object getByProperty(String property, String value) {
		return (List<Device>) getCurrentSession().createCriteria(Device.class).add(Restrictions.like(property, value))
				.list();
	}

}
