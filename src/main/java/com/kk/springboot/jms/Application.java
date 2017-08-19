package com.kk.springboot.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * Author: PDudin
 * Created date: 20.08.2017.
 */
@SpringBootApplication
public class Application {

	public static final String JMS_CONTAINER_FACTORY_1 = "jmsListenerContainerFactory1";

	public static final String JMS_CONTAINER_FACTORY_2 = "jmsListenerContainerFactory2";

	private static final String JMS_CONNECTION_FACTORY_1 = "jmsConnectionFactory1";

	private static final String JMS_CONNECTION_FACTORY_2 = "jmsConnectionFactory2";

	/*Service 1*/
	@Bean(name = JMS_CONNECTION_FACTORY_1)
	@Primary
	public ConnectionFactory getJmsConnectionFactory1(
			@Value("${service1.spring.activemq.user}") String user,
			@Value("${service1.spring.activemq.password}") String password,
			@Value("${service1.spring.activemq.broker-url}") String brokerUrl) {
		return new ActiveMQConnectionFactory(user, password, brokerUrl);
	}

	@Bean(name = JMS_CONTAINER_FACTORY_1)
	@Primary
	public JmsListenerContainerFactory<DefaultMessageListenerContainer> getJmsListenerContainerFactory1(
			@Qualifier(JMS_CONNECTION_FACTORY_1) ConnectionFactory cf) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(cf);
		return factory;
	}

	@Bean(name = "jmsTemplate1")
	@Primary
	public JmsTemplate getJmsTemplate1(
			@Qualifier(JMS_CONNECTION_FACTORY_1) ConnectionFactory connectionFactory) {
		return new JmsTemplate(connectionFactory);
	}

	/*Service 2*/
	@Bean(name = JMS_CONNECTION_FACTORY_2)
	public ConnectionFactory getJmsConnectionFactory2(
			@Value("${service2.spring.activemq.user}") String user,
			@Value("${service2.spring.activemq.password}") String password,
			@Value("${service2.spring.activemq.broker-url}") String brokerUrl) {
		return new ActiveMQConnectionFactory(user, password, brokerUrl);
	}

	@Bean(name = JMS_CONTAINER_FACTORY_2)
	public JmsListenerContainerFactory<DefaultMessageListenerContainer> getJmsListenerContainerFactory2(
			@Qualifier(JMS_CONNECTION_FACTORY_2) ConnectionFactory cf) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(cf);
		return factory;
	}

	@Bean(name = "jmsTemplate2")
	@Primary
	public JmsTemplate getJmsTemplate2(
			@Qualifier(JMS_CONNECTION_FACTORY_2) ConnectionFactory connectionFactory) {
		return new JmsTemplate(connectionFactory);
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
