package edu.njupt.springmvc.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

@Configuration
public class SpringTransactionConfig {
	@Bean
	@Resource(name = "getDruidDataSource")
	public TransactionManager getTransactionManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}
}
