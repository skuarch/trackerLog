package model.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * entry point, Spring uses this class to start the application.<br/>
 * this class contains the main method.  
 * 
 * @author skuarch
 *
 */
@SpringBootApplication
@EntityScan(value={"model.bean"})
@ComponentScan({"controller","model"})
@EnableMongoRepositories("model.repository")
public class TrackerLogApplication {

	// ===================================================================================
	/**
	 * main method of the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TrackerLogApplication.class, args);
	}

}
