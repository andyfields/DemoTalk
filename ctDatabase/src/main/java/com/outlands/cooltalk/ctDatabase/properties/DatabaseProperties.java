package com.outlands.cooltalk.ctDatabase.properties;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
/**
 * Service for retrieving database properties.  There is one set of properties for production and another for test.
 * Properties are refreshed every 60 seconds.
 * 
 * @author Andy Fields
 *
 */
public class DatabaseProperties {
	
	private static final long REFRESH_INTERVAL_SECS = 60;
	
	@Resource
	private String databasePropertiesFile;

	private Properties properties;
	private LocalDateTime loadTime = null;
	
	public Properties getProperties() {
		if (properties == null || 
				loadTime.plusSeconds(REFRESH_INTERVAL_SECS).isBefore(LocalDateTime.now())) {
			loadProperties();
		}
		
		return properties;
	}
	
	
	private  synchronized void loadProperties() {
		InputStream input = null;

		if (properties == null || 
				loadTime.plusSeconds(REFRESH_INTERVAL_SECS).isBefore(LocalDateTime.now())) {
	
			input = DatabaseProperties.class.getClassLoader().getResourceAsStream(databasePropertiesFile);
			if(input==null){
		            System.out.println("Sorry, unable to find " + databasePropertiesFile);
					throw new RuntimeException("Unable to load properties file " + databasePropertiesFile);
			}
			
			properties = new Properties();

			//load a properties file from class path.
			try {
				properties.load(input);
				loadTime = LocalDateTime.now();
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("IOException: Unable to load properties file " + databasePropertiesFile, e);
			}
		}
	}
	


}
