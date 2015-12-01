package model.net;

import org.springframework.beans.factory.annotation.Autowired;

import model.logic.MessageHandler;

public class Receiver {

	@Autowired
	private MessageHandler messageHandler;
	
	// ===================================================================================
	public Receiver() {		
	}

	// ===================================================================================
	public void receiveMessage(String message) {
		messageHandler.handleMessage(message);	
	}
	
}
