package com.spring.hibernate.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE")
public class Device {
    
	public static final String ONLINE="online";
	public static final String OFFLINE="offline";
	public static final String ERROR="error";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@Column(name = "DEVICE_ID")
	private String deviceId;

	@Column(name = "DEVICE_NAME")
	private String deviceName;

	@Column(name = "DEVICE_STATUS")
	private String deviceStatus;

	@Column(name = "DEVICE_DESCRIPTION")
	private String description;

	@Column(name = "ACCESS_KEY")
	private String accessKey;
	
	@OneToMany(cascade=CascadeType.ALL ,mappedBy="device", fetch=FetchType.EAGER)
	private Set<Topic> pubSubTopics; 

	public Set<Topic> getPubSubTopics() {
		return pubSubTopics;
	}

	public void setPubSubTopics(Set<Topic> pubSubTopics) {
		this.pubSubTopics = pubSubTopics;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		Id = id;
	}

}
