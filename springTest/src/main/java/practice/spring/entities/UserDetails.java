package practice.spring.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String userName;
	private String email;
	private String password;
	private Address officeAddress;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Device> devices;// = new HashSet<Device>();
	@ManyToMany(cascade =CascadeType.ALL)
	private List<SharedDevice> sharedDevices=new ArrayList<SharedDevice>();

	public UserDetails() {

	}

	public UserDetails(String userName, String email, String password, Address officeAddress, Set<Device> devices) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.officeAddress = officeAddress;
		this.devices = devices;
	}

	public UserDetails(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	public void addDevice(Device device) {
		if (this.getDevices() == null) {
			this.devices = new HashSet<Device>();
			this.devices.add(device);

		} else
			this.getDevices().add(device);
	}

	
	
	@Override
	public String toString() {
		return "UserDetails [userName=" + userName + ", email=" + email + ", password=" + password + ", officeAddress="
				+ officeAddress + ", devices=" + devices + "]";
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		userId = userId;
	}

	public List<SharedDevice> getSharedDevices() {
		return sharedDevices;
	}

	public void setSharedDevices(List<SharedDevice> sharedDevices) {
		this.sharedDevices = sharedDevices;
	}

	

}
