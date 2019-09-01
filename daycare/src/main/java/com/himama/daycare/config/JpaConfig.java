package com.himama.daycare.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author nchopra
 *
 */
@Configuration
@EnableJpaRepositories("com.himama.daycare.repository.jpa")
@EnableTransactionManagement
public class JpaConfig {

	public static final String DATASOURCE_HIMAMA = "dsHimama";
	public static final String JDBCTEMPLATE_HIMAMA = "jdbcHimama";

	@Bean(name = DATASOURCE_HIMAMA)
	@Primary
	@ConfigurationProperties(prefix = "spring.jwt.security")
	public DataSource securityDataSource() {
		return dataSourceBuilder().build();
	}

	private DataSourceBuilder dataSourceBuilder() {
		return DataSourceBuilder.create().type(HikariDataSource.class);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.himama.daycare.domain.jpa");
		factory.setDataSource(securityDataSource());
		return factory;
	}

	@Bean
	public TransactionTemplate transactionTemplate(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager(entityManagerFactory);
		return new TransactionTemplate(txManager);
	}

}
