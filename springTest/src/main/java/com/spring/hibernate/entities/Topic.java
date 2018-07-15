package com.spring.hibernate.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TOPIC")
public class Topic {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="PUB_TOPIC")
	private String publishTopic;
	
	@Column(name="SUB_TOPIC")
	private String subscribeTopic;
	
	@ManyToOne
	@JoinColumn(name="device_FK")
	private Device device;
	
	public String getPublishTopic() {
		return publishTopic;
	}
	public void setPublishTopic(String publishTopic) {
		this.publishTopic = publishTopic;
	}
	public String getSubscribeTopic() {
		return subscribeTopic;
	}
	public void setSubscribeTopic(String subscribeTopic) {
		this.subscribeTopic = subscribeTopic;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
