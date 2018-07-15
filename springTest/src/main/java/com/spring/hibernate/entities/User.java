package com.spring.hibernate.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "USER")
public class User {

	// validation used for this field in validator
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "USER_NAME")
	private String userName;
	@Email
	@Column(name = "USER_EMAIL")
	private String userEmail;
	// validation used for this field in validator
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "CONFIRM_PASSWORD")
	private String confirmPassword;
	@Column(name = "ACCESS_TOKEN")
	private String accessToken;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable( name = "USER_DEVICE_REL_TAB", joinColumns = { @JoinColumn(name = "USER_ID")}, 
	inverseJoinColumns = { @JoinColumn(name = "DEVICE_ID") })
	private Set<Device> devices;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Set<Device> getDevices() {
		return devices;
	}
	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}
}
