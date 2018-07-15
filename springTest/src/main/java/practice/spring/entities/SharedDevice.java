package practice.spring.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class SharedDevice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deviceId;
	private String name;
	private String status;
	@ElementCollection
	private List<String> pubTopics = new ArrayList<String>();
	@ElementCollection
	private List<String> subTopics = new ArrayList<String>();
	@ManyToMany(mappedBy="sharedDevices")
	private List<UserDetails> userDetails = new ArrayList<UserDetails>();

	public String getName() {
		return name;
	}

	public SharedDevice(String name, String status) {
		super();
		this.name = name;
		this.status = status;
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

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

}
