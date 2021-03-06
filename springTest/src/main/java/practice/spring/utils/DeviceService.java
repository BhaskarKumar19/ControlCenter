package practice.spring.utils;

import java.util.List;

import com.spring.hibernate.entities.Device;

import practice.spring.exceptions.DatabaseException;

public interface DeviceService {

	public Long createDevice(int id, Device value) throws DatabaseException;

	public void updateDevice(int id, Device value) throws DatabaseException;

	public Device getDevice(int id);

	public List<Device> getDeviceByProperty(String property, String value) throws DatabaseException;

	public List<Device> getDeviceList(int id) throws DatabaseException;

	public Object deleteDevice(int id) throws DatabaseException;
}
