package com.outlands.cooltalk.ctCommon.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.outlands.cooltalk.ctCommon.constants.OLConstants;
import com.outlands.cooltalk.utility.OLDateUtils;

/**
 * @author Andy Fields
 *
 * Base class for properties classes.
 */
public abstract class OLPropertiesServiceImpl {

	@Autowired
	OLDateUtils olDateUtils;
	
	private final Logger log = Logger.getLogger(this.getClass());
	private String propsFile;
	private Properties properties = null;
	private Date lastLoaded = null;
	
	public OLPropertiesServiceImpl(String propsFile) {
		this.propsFile = propsFile;
	}
	
	/**
	 * Load the properties.
	 * 
	 * @return Properties object with Globals.prop values.
	 */
	private synchronized void loadProperties() {
		
		if (properties == null || lastLoaded == null || 
				olDateUtils.add(lastLoaded, Calendar.SECOND, OLConstants.PROPERTY_FILE_REFRESH_SECONDS).before(olDateUtils.getNow())) {
	
			InputStream stream = null;
			stream = OLPropertiesServiceImpl.class.getResourceAsStream(propsFile);
			
			if (stream == null) {
				log.info("Null properties stream.  File = " + propsFile);
				throw new RuntimeException("");
			}
			
			properties = new Properties();
			try {
				properties.load(stream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	Properties getProperties() {
		if (properties == null || lastLoaded == null || 
				olDateUtils.add(lastLoaded, Calendar.SECOND, OLConstants.PROPERTY_FILE_REFRESH_SECONDS).before(olDateUtils.getNow())) {
			loadProperties();
		}
		
		return properties;
	}
}
