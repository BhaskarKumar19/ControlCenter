package practice.spring.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deviceId;
	private String name;
	private String status;
	@ElementCollection
	private List<String> pubTopics = new ArrayList<String>();
	@ElementCollection
	private List<String> subTopics = new ArrayList<String>();
	@ManyToOne
	private UserDetails userDetails;

	public Device() {
	}

	public Device(int deviceId, String name, String status, List<String> pubTopics, List<String> subTopics) {
		super();
		this.deviceId = deviceId;
		this.name = name;
		this.status = status;
		this.pubTopics = pubTopics;
		this.subTopics = subTopics;
	}

	public Device(int deviceId, String name, String status) {
		super();
		this.deviceId = deviceId;
		this.name = name;
		this.status = status;
	}

	public Device(String name, String status) {
		super();
		this.name = name;
		this.status = status;
	}
	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getPubTopics() {
		return pubTopics;
	}

	public void setPubTopics(List<String> pubTopics) {
		this.pubTopics = pubTopics;
	}

	public List<String> getSubTopics() {
		return subTopics;
	}

	public void setSubTopics(List<String> subTopics) {
		this.subTopics = subTopics;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", name=" + name + ", status=" + status + ", pubTopics=" + pubTopics
				+ ", subTopics=" + subTopics + "]";
	}

}
