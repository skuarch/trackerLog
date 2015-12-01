package model.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import model.bean.Syslog;
import model.repository.SyslogRepository;

@Component
public class MessageHandler {
	
	private static final Logger LOGGER = Logger.getLogger(MessageHandler.class);
	
	@Autowired
	private SyslogRepository syslogRepository;

	// ===================================================================================
	public MessageHandler() {		
	}

	// ===================================================================================
	public void handleMessage(String message) {

		if (message == null || message.equals("")) {
			return;
		}
		
		try{
			
			Syslog syslog = new Gson().fromJson(message, Syslog.class);
			syslogRepository.save(syslog);			
			
		}catch(Exception e){
			LOGGER.error("MessageHandler.handleMessage()",e);
		}	
		
	}

}
