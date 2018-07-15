package practice.spring.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.hibernate.entities.Device;

import practice.spring.exceptions.DatabaseException;

@Component
@Transactional(rollbackFor = DatabaseException.class)
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DatabaseOperations deviceDbOperationsImpl;

	public DatabaseOperations getDeviceDbOperationsImpl() {
		return deviceDbOperationsImpl;
	}

	public void setDeviceDbOperationsImpl(DatabaseOperations deviceDbOperationsImpl) {
		this.deviceDbOperationsImpl = deviceDbOperationsImpl;
	}

	public Long createDevice(int id, Device value) throws DatabaseException {
		try {
			return getDeviceDbOperationsImpl().createRepo(id, value);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}

	}

	public void updateDevice(int id, Device value) throws DatabaseException {
		try {
			getDeviceDbOperationsImpl().updateRepo(id, value);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}

	}

	public Device getDevice(int id) {
		return (Device) getDeviceDbOperationsImpl().getRepo(id);
	}

	public List<Device> getDeviceList(int id) throws DatabaseException {
		try {
			return (List<Device>) getDeviceDbOperationsImpl().getRepoList(id);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}
	}

	public List<Device> getDeviceByProperty(String property, String value) throws DatabaseException {
		try {
			return (List<Device>) getDeviceDbOperationsImpl().getByProperty(property, value);
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}

	}

	public Object deleteDevice(int id) throws DatabaseException {
		try {
			getDeviceDbOperationsImpl().deleteRepo(id);
			return null;
		} catch (DataAccessException ex) {
			// now display this message using model
			throw new DatabaseException("Database Error::" + ex.getCause().toString());
		}
	}
}
