package com.alex.springDemo2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alex.springDemo2.SpringDemo2Application;

@Configuration
public class DataSourceAutoConfiguration {
		private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SpringDemo2Application.class);
		
		@Bean
		@ConfigurationProperties("master.datasource")
		public DataSourceProperties masterDataSourceProperties() {
			return new DataSourceProperties();
		}
		
		@Bean("masterDataSource")
		public DataSource masterDataSource() {
			DataSourceProperties dataSourceProperties = masterDataSourceProperties();
			log.info("master datasource:{}",dataSourceProperties.getUrl());
			return dataSourceProperties.initializeDataSourceBuilder().build();
		}
		
		@Bean
		public PlatformTransactionManager masterManager(@Qualifier( "masterDataSource")DataSource masterDatasource) {
			log.info("master manager:{}",masterDatasource.toString());
			return new DataSourceTransactionManager(masterDatasource);
		}
		
		@Bean
		@ConfigurationProperties("slave.datasource")
		public DataSourceProperties slaveDataSourceProperties() {
			return new DataSourceProperties();
		}
		
		@Bean("slaveDataSource")
		public DataSource slaveDataSource() {
			DataSourceProperties dataSourceProperties = slaveDataSourceProperties();
			log.info("slave datasource:{}",dataSourceProperties.getUrl());
			return dataSourceProperties.initializeDataSourceBuilder().build();
		}
		
		@Bean
		public PlatformTransactionManager slaveManager(@Qualifier("slaveDataSource")DataSource slaveDatasource) {
			log.info("slave manager:{}", slaveDatasource.toString());
			return new DataSourceTransactionManager(slaveDatasource);
		}
}
