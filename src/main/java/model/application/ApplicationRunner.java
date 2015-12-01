package model.application;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import model.bean.Syslog;
import model.net.Receiver;

/**
 * simulator of Syslogs<br/>
 * this class sends Syslogs to the queue simulating a real environment.<br/>
 * Spring component
 * 
 * @author skuarch
 *
 */
@Component
public class ApplicationRunner implements CommandLineRunner {

	private static final Logger LOGGER = Logger.getLogger(ApplicationRunner.class);
	private AnnotationConfigApplicationContext annotationConfigApplicationContext = null;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Syslog syslog;

	@Autowired
	private Environment environment;

	// ===================================================================================
	/**
	 * Constructor.
	 * 
	 */
	public ApplicationRunner() {
		this.annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
	}

	// ===================================================================================
	/**
	 * this method is called when the applications start.
	 * 
	 * @param arg0
	 * @throws Exception
	 */
	@Override
	public void run(String... arg0) throws Exception {

		String message;

		try {
			
			// populate document
			if (arg0.length > 0 && arg0[0].equals("populate")) {
				LOGGER.info("populating document");

				while (true) {

					Thread.sleep(5); // avoid overloads and break everything

					short random1To10 = (short) (1 + (Math.random() * 10));
					short random0To23 = (short) (1 + (Math.random() * 23));
					byte random0To7 = (byte) (1 + (Math.random() * 7));

					// severity 7 is for debug
					if (random0To7 == 7) {
						message = "debug message";
					} else {
						message = "other message";
					}

					syslog.setAppName("app name " + random1To10);
					syslog.setFacility(random0To23);
					syslog.setHostName("the hostname");
					syslog.setMessage(message);
					syslog.setTimestamp(System.currentTimeMillis() + "");
					syslog.setSeverity(random0To7);

					rabbitTemplate.convertAndSend(environment.getProperty("rabbitmq.queue.log.name"),
							new Gson().toJson(syslog));
					annotationConfigApplicationContext.close();

				}

			}

		} catch (Exception e) {
			LOGGER.error("ApplicationRunner.run()", e);
		} finally {
			
		}

	}

	// ===================================================================================
	/**
	 * in order to use @Autowired
	 * 
	 * @param connectionFactory
	 *            ConnectionFactory
	 * @param listenerAdapter
	 *            MessageListenerAdapter
	 * @return
	 */
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(environment.getProperty("rabbitmq.queue.log.name"));
		container.setMessageListener(listenerAdapter);
		return container;
	}

	// ===================================================================================
	/**
	 * in order to use @Autowired
	 * 
	 * @param receiver
	 * @return
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

}
