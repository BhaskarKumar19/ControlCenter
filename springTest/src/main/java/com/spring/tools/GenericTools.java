package com.spring.tools;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import integration.CustomGateway;

public class GenericTools {

	public static String getSaltString() {
		String SALTCHARS = "ABCDEFGUVWXYZ237890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public void subscribeTopic(MqttPahoMessageDrivenChannelAdapter messageChannelAdaptor, String subTopic) {
		// inbound- subscribing to the new topic for new device
		// the message received on this topic will be logged on the console(or
		// provide topic corresponding to
		// sensor or actuator to process it accordingly)
		messageChannelAdaptor.addTopic(subTopic);
	}

	public void publishOnTopic(CustomGateway gatewayService, String pubTopic, String payload) {

		Message m = MessageBuilder.withPayload(payload).setHeader(MqttHeaders.TOPIC, pubTopic).build();
		Future resp = gatewayService.sendMqttMessage(m);
		/*
		 * while (!resp.isDone()) {
		 * System.out.println("Task is not completed yet...."); try {
		 * Thread.sleep(100); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 * System.out.println("Task is completed !!."); try {
		 * System.out.println(resp.get()); } catch (InterruptedException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (ExecutionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}
}
