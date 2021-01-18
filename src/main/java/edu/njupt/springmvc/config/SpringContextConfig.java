package edu.njupt.springmvc.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Spring框架主配置文件
 * @author Administrator
 *
 */
@Configuration
@EnableAspectJAutoProxy
@Import(value = {SpringTransactionConfig.class})
@ComponentScan(
		basePackages = {
				"edu.njupt.springmvc.settings.admin",
				"edu.njupt.springmvc.settings.login",
				"edu.njupt.springmvc.settings.student"}, 
		excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class})})
public class SpringContextConfig {
	/**
	 * 配置德鲁伊连接池
	 * @return
	 */
	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource getDataSource() {
		DruidDataSource dds = new DruidDataSource();
		try {
			Properties p = Resources.getResourceAsProperties("jdbc.properties");
			dds.setUrl(p.getProperty("jdbc.url"));
			dds.setUsername(p.getProperty("jdbc.username"));
			dds.setPassword(p.getProperty("jdbc.password"));
			dds.setMaxActive(Integer.parseInt(p.getProperty("jdbc.maxActive")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dds;
	}
	/**
	 * 配置SqlSessionFactoryBean
	 * @param ds
	 * @return
	 */
	@Bean
	@Resource(name = "getDataSource")
	public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource ds) {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(ds);
		ssfb.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return ssfb;
	}
	/**
	 * 配置映射器
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer getMapperScannerConfigurer() {
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setSqlSessionFactoryBeanName("getSqlSessionFactoryBean");
		msc.setBasePackage("edu.njupt.springmvc.settings");
		return msc;
	}
}
