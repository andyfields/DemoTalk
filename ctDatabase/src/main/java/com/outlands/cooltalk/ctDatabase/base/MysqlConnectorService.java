package com.outlands.cooltalk.ctDatabase.base;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.outlands.cooltalk.ctDatabase.properties.DatabaseProperties;

/**
 * Basic service to create a database connection.  
 * 
 * @author Andy Fields
 *
 */
@Configuration
public class MysqlConnectorService {
	
	DataSource ds = null;
	
	@Autowired
	DatabaseProperties databaseProperties;
	
	@Bean
	public DataSource dataSource() {
		if (ds == null)
			initDataSource();
		
		return ds;
	}
	
	private synchronized void initDataSource() {
		
		if (ds == null) {
			Properties prop = databaseProperties.getProperties();
			
			String jdbcDriver = prop.getProperty(Constants.JDBC_DRIVER_KEY);
		   	String dbUrl = prop.getProperty(Constants.DB_URL_KEY);
		   	String dbUserName = prop.getProperty(Constants.DB_USER_NAME_KEY);
		   	String dbPassword = prop.getProperty(Constants.DB_PASSWORD);
		   	String databaseName = prop.getProperty(Constants.DB_NAME);
		   	
		   	assert (jdbcDriver != null);
		   	assert dbUrl != null;
		   	assert dbUserName != null;
		   	assert databaseName != null;

		   	MysqlDataSource mysqlSource = new MysqlDataSource();
		   	mysqlSource.setUrl(dbUrl + databaseName);
		   	mysqlSource.setPassword(dbPassword);
		   	mysqlSource.setUser(dbUserName);
		   	
		   	ds = mysqlSource;
		}
		
	}
	
}
