package practice.spring.dao.inMem.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import practice.spring.dao.DatabaseOperations;
import practice.spring.pojo.Device;


@Component
public class DeviceDbOperationsInMemImpl implements DatabaseOperations {

	Map<Integer, Device> deviceDb=new HashMap<Integer, Device>(); 

	public void deleteRepo(int id) {
		deviceDb.remove(id);
		return;

	}

	public long createRepo(int id, Object value) throws DataAccessException {
		Device device = (Device) value;
		int index=deviceDb.size()+1; 
		deviceDb.put(index, device);
		return index;
	}

	public void updateRepo(int id, Object value) {
		deviceDb.put(id, (Device) value);
	}

	public Device getRepo(int id) {
		return deviceDb.get(id);

	}

	public List<Device> getRepoList(int id) {
		List<Device> users =  deviceDb.values().stream().map(device-> new Device(device)).collect(Collectors.toList());
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
