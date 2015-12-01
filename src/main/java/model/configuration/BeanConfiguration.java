package model.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.bean.GenericMessage;
import model.bean.Syslog;

@Configuration
public class BeanConfiguration {

	// ===================================================================================
	@Bean
	public GenericMessage genericMessage() {
		return new GenericMessage();
	}

	// ===================================================================================
	@Bean
	public Syslog syslog() {
		return new Syslog();
	}	

}
