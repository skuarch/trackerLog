package model.configuration;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.net.Receiver;

@Configuration
@EnableAutoConfiguration
public class MessageQueueConfiguration {	
	
	private static final Logger LOGGER = Logger.getLogger(MessageQueueConfiguration.class);
	private String queueName = null;
	private String topicExchangeName = null;

	// =========================================================================
	public MessageQueueConfiguration(String queueName, String topicExchangeName) {
		this.queueName = queueName;
		this.topicExchangeName = topicExchangeName;
	}

	// =========================================================================
	public MessageQueueConfiguration() {
		this.queueName = "logQueue"; // default value
		this.topicExchangeName = "logTopicExchange"; // default value
	}

	// =========================================================================
	@Bean
	public Queue queue() {
		LOGGER.info("Setting Queue");
		return new Queue(queueName, false);
	}

	// =========================================================================
	@Bean
	public TopicExchange exchange() {
		LOGGER.info("Setting TopicExchange");
		return new TopicExchange(topicExchangeName);
	}

	// =========================================================================
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		LOGGER.info("Binding queue with exchange");
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}

	// =========================================================================
	@Bean
	public Receiver receiver() {
		return new Receiver();
	}
	
}
