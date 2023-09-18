package it.l_soft.contacto.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

public class ApplicationProperties {
	// parameters from the call
	private int languageId = 1;
	
	// package properties
	private int sessionExpireTime = 0;
	private String defaultLang = "";
	private boolean useCoars = false;

	// env specific
	private String mailSmtpHost = "";
	private String mailUser = "";
	private String mailPassword = "";
	
	private ServletContext context;
	
	private static ApplicationProperties instance = null;
	
	final Logger log = Logger.getLogger(this.getClass());
	
	public static ApplicationProperties getInstance()
	{
		if (instance == null)
		{
			instance = new ApplicationProperties();
		}
		return(instance);
	}
	
	private ApplicationProperties()
	{
		String variable = "";
		log.trace("ApplicationProperties start");
		Properties properties = new Properties();
		log.debug("path of abs / '" + ApplicationProperties.class.getResource("/").getPath() + "'");
		
    	try 
    	{
    		log.debug("path of abs package.properties '" + 
    				  ApplicationProperties.class.getResource("/resources/package.properties").getPath() + "'");
        	InputStream in = ApplicationProperties.class.getResourceAsStream("/resources/package.properties");
        	if (in == null)
        	{
        		log.error("resource path not found");
        		return;
        	}
        	properties.load(in);
	    	in.close();
		}
    	catch(IOException e) 
    	{
			log.warn("Exception " + e.getMessage(), e);
    		return;
		}
    	
    	defaultLang = properties.getProperty("defaultLang");
		useCoars = Boolean.parseBoolean(properties.getProperty("useCoars"));
		try
    	{
    		variable = "sessionExpireTime";
    		sessionExpireTime = Integer.parseInt(properties.getProperty("sessionExpireTime"));
    	}
    	catch(NumberFormatException e)
    	{
    		log.error("The format for the variable '" + variable + "' is incorrect (" +
    					 properties.getProperty("sessionExpireTime") + ")", e);
    	}

    	String envConf = System.getProperty("envConf");
    	try 
    	{
    		properties = new Properties();
    		String siteProps = "/resources/env." + (envConf == null ? "dev" : envConf) + ".properties";
    		log.debug("using '" + siteProps + "' at '" + ApplicationProperties.class.getResource(siteProps).getPath() + "'");
        	InputStream in = ApplicationProperties.class.getResourceAsStream(siteProps);        	
			properties.load(in);
	    	in.close();
		}
    	catch(IOException e) 
    	{
			log.error("Exception " + e.getMessage(), e);
    		return;
		}
    	mailSmtpHost = properties.getProperty("mailSmtpHost");
    	mailUser = properties.getProperty("mailUser");
    	mailPassword = properties.getProperty("mailPassword");
	}

	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public String getMailUser() {
		return mailUser;
	}

	public String getMailPassword() {
		return mailPassword;
	}
	
	public int getSessionExpireTime() {
		return sessionExpireTime;
	}

	public String getDefaultLang() {
		return defaultLang;
	}

	public boolean isUseCoars() {
		return useCoars;
	}

	public ServletContext getContext() {
		return context;
	}		

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
//		this.languageId = languageId;
	}

	
}