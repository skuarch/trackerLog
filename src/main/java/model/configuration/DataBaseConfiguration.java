package model.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration {

	private static final Logger LOGGER = Logger.getLogger(DataBaseConfiguration.class);

	@Autowired
	private Environment environment;

	@Autowired
	private DataSource dataSource;

	// ===================================================================================
	@Bean
	public synchronized LocalSessionFactoryBean sessionFactory() {

		LOGGER.info("creating LocalSessionFactoryBean");
		LocalSessionFactoryBean sessionFactoryBean = null;
		Properties hibernateProperties;

		try {
			
			sessionFactoryBean = new LocalSessionFactoryBean();
			sessionFactoryBean.setDataSource(dataSource);
			sessionFactoryBean.setPackagesToScan("model.bean");

			hibernateProperties = new Properties();
			hibernateProperties.put("hibernate.dialect", environment.getProperty("spring.jpa.database-platform"));
			hibernateProperties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
			hibernateProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));

			sessionFactoryBean.setHibernateProperties(hibernateProperties);

		} catch (Exception e) {
			LOGGER.error("LocalSessionFactoryBean.sessionFactory()", e);
		}

		return sessionFactoryBean;
	}

	// ===================================================================================
	@Bean
	public HibernateTransactionManager transactionManager() {

		LOGGER.info("creating HibernateTransactionManager");
		HibernateTransactionManager transactionManager = null;

		try {
			
			transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory().getObject());
			
		} catch (Exception e) {
			LOGGER.error("HibernateTransactionManager.transactionManager()", e);
		}

		return transactionManager;
	}

}
