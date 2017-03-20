package com.outlands.cooltalk.ctCommon.properties;

/**
 * @author Andy Fields
 *
 * Spring class to read and hold global properties.  There may be multiple instances each with its own property 
 * file, so the bean should be specified in .xml instead of annotation.  One of the very few times we will do this.
 */
public class OLWebPropertiesServiceImpl extends OLPropertiesServiceImpl implements OLWebPropertiesService {
	
	public OLWebPropertiesServiceImpl(String propsFile) {
		super(propsFile);
	}

	@Override
	public boolean isDemoMode() {
		return "True".equalsIgnoreCase(getProperties().getProperty("DemoMode"));
	}
	
	@Override
	public String getMicroservicesServer() {
		return getProperties().getProperty("MicroservicesServer");
	}
	
}
