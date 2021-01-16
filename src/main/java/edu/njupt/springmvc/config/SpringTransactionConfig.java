package edu.njupt.springmvc.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class SpringTransactionConfig {
	@Bean
	@Resource(name = "getDruidDataSource")
	public TransactionManager getTransactionManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}
}
