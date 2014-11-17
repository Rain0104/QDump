package org.data.art.qdump.persistence;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan("org.data.art")
public class AppConfig {
	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) {
		return new JpaTransactionManager(
				emf);
	}
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.user"));
		dataSource.setPassword(env.getProperty("db.password"));
		dataSource.setDriverClassName(env.getProperty("db.driver"));

		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean 
		entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new 
				LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("org.hillel.it");
		bean.setDataSource(dataSource);
		Properties properties = new Properties();
		properties.put("hibernate.dialect", 
				env.getProperty("hibernate.dialect"));
		properties.put("hibernate.cache.use_second_level_cache", 
				env.getProperty("second.level.cache"));
		properties.put("hibernate.cache.provider_class", 
				env.getProperty("cache.provider.class"));
		properties.put("hibernate.hbm2ddl.auto", 
				env.getProperty("hbm2ddl.auto"));
		properties.put("hibernate.show_sql", 
				env.getProperty("show_sql"));
		properties.put("hibernate.cache.region.factory_class", 
				env.getProperty("cache.region.class"));
		
		bean.setJpaProperties(properties);
		JpaVendorAdapter adapter = new 
				HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);
		return bean;
	}
}
