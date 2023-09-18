package it.l_soft.contacto.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import it.l_soft.contacto.rest.ApplicationProperties;

public class Utils {
	final static Logger log = Logger.getLogger(Utils.class);
	
	public static class LanguageResources {
		final static Logger log = Logger.getLogger(LanguageResources.class);
		private static Properties[] resource = new Properties[LanguageConstants.MAX_LNG];
		private static LanguageResources singletonInstance = null;
		private static int languageCode = LanguageConstants.LNG_IT;

		private LanguageResources()
		{
	    	try 
	    	{
	        	InputStream in = LanguageResources.class.getResourceAsStream("/resources/it.properties");
	        	resource[LanguageConstants.LNG_IT] = new Properties();
	        	resource[LanguageConstants.LNG_IT].load(in);
		    	in.close();
	        	in = LanguageResources.class.getResourceAsStream("/resources/en.properties");
	        	resource[LanguageConstants.LNG_EN] = new Properties();
	        	resource[LanguageConstants.LNG_EN].load(in);
		    	in.close();
	        	in = LanguageResources.class.getResourceAsStream("/resources/de.properties");
	        	resource[LanguageConstants.LNG_DE] = new Properties();
	        	resource[LanguageConstants.LNG_DE].load(in);
		    	in.close();
	        	in = LanguageResources.class.getResourceAsStream("/resources/fr.properties");
	        	resource[LanguageConstants.LNG_FR] = new Properties();
	        	resource[LanguageConstants.LNG_FR].load(in);
		    	in.close();
	        	in = LanguageResources.class.getResourceAsStream("/resources/es.properties");
	        	resource[LanguageConstants.LNG_ES] = new Properties();
	        	resource[LanguageConstants.LNG_ES].load(in);
		    	in.close();

			}
	    	catch(IOException e) 
	    	{
	    		log.warn("Exception " + e.getMessage(), e);    		
	    		return;
			}
		}
		
		public static String getResource(String errCode)
		{
			if (singletonInstance == null)
			{
				singletonInstance = new LanguageResources();
			}
			return(resource[languageCode].getProperty(errCode, "Unknown error"));
		}

		public static String getResource(int language, String errCode)
		{
			if (singletonInstance == null)
			{
				singletonInstance = new LanguageResources();
			}
			if (resource.length >= language)
				return(resource[language].getProperty(errCode, "Unknown error"));
			else
				return(resource[1].getProperty(errCode, "Unknown error"));
		}

		public static void setLanguageCode(int languageCode) 
		{
			LanguageResources.languageCode = languageCode;
		}
	}
	
	public static class LanguageConstants {
		public static final int LNG_IT	= 1;
		public static final int LNG_EN	= 2;
		public static final int LNG_FR	= 3;
		public static final int LNG_DE	= 4;
		public static final int LNG_ES	= 5;
		public static final int MAX_LNG = 6;

		public static final String LOCALE_IT = "it_IT";
		public static final String LOCALE_EN = "en_US";
		public static final String LOCALE_FR = "fr_FR";
		public static final String LOCALE_DE = "de_DE";
		public static final String LOCALE_ES = "es_ES";
		
		private static int getLanguageCode(String language)
		{
			switch(language.substring(0,2).toUpperCase())
			{
			case "IT":
				return(LNG_IT);

			case "EN":
				return(LNG_EN);

			case "FR":
				return(LNG_FR);

			case "DE":
				return(LNG_DE);

			case "ES":
				return(LNG_ES);

			default:
				return(LNG_EN);
			}
		}
		
		public static int getAlternativeLanguage(String language)
		{
			switch(language.substring(0,2).toUpperCase())
			{
			case "IT":
				return(LNG_EN);

			case "EN":
				return(LNG_IT);

			case "FR":
				return(LNG_IT);

			case "DE":
				return(LNG_IT);

			case "ES":
				return(LNG_IT);

			default:
				return(LNG_IT);
			}
		}

		public static int getAlternativeLanguage(int language)
		{
			switch(language)
			{
			case LNG_IT:
				return(LNG_EN);

			case LNG_EN:
				return(LNG_IT);

			case LNG_FR:
				return(LNG_IT);

			case LNG_DE:
				return(LNG_IT);

			case LNG_ES:
				return(LNG_IT);

			default:
				return(LNG_IT);
			}
		}

		public static String getLocale(int languageId)
		{
			switch(languageId)
			{
			case LNG_IT:
				return(LOCALE_IT);

			case LNG_EN:
				return(LOCALE_EN);

			case LNG_FR:
				return(LOCALE_FR);

			case LNG_DE:
				return(LOCALE_DE);

			case LNG_ES:
				return(LOCALE_ES);

			default:
				return(LOCALE_EN);
			}
		}

	}
	
	static ApplicationProperties prop = ApplicationProperties.getInstance();
	HashMap<String, Object>jsonResponse = new HashMap<>();
	
	public static String printStackTrace(Exception e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return (sw.toString()); 
	}
	
	public static int getLanguageId(String language)
	{
		if (language == null)
		{
			language = prop.getDefaultLang();
		}
		int languageId = LanguageConstants.getLanguageCode(language);
		LanguageResources.setLanguageCode(languageId);
		
		return languageId;
	}

	public String jsonize()
	{
		Gson gson= new Gson();
		return gson.toJson(jsonResponse);
	}

	private static Field[] getAllFields(Class<?> cType)
	{
		List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = cType; c != null; c = c.getSuperclass()) 
        {
        	fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        Field[] fieldArr = new Field[fields.size()];
        return fields.toArray(fieldArr);
	}

	public static Response jsonizeResponse(Status status, Exception e, String language, String errResource )
	{
		return(jsonizeResponse(status, e, getLanguageId(language), errResource));
	}

	public static Response jsonizeSingleObject(Object o, int languageId)
	{
		Gson mapper = new Gson();
		String json = "";
		
		json = mapper.toJson(o);
		return Response.status(Status.OK).entity(json).build();
	}

	public static Response jsonizeSingleObject(Object o, String language)
	{
		return jsonizeSingleObject(o, getLanguageId(language));
	}

	public static Response jsonizeSingleObject(Object o)
	{
		return jsonizeSingleObject(o, prop.getLanguageId());
	}

	public static Object populateObjectFromJSON(JsonObject jsonObj, Object objInst)
	{
		Field[] clFields = getAllFields(objInst.getClass());
		for(Field field : clFields)
		{
			try
			{
				JsonElement jElement = jsonObj.get(field.getName());
				switch(field.getType().getName())
				{
				case "int":
				case "long":
					field.set(objInst, jElement.getAsInt());
					break;
	
				case "java.lang.String":
					field.set(objInst, jElement.getAsString());
					break;
				}
			}			
			catch(Exception e)
			{
				log.warn("Exception " + e.getMessage(), e);
			}
		}
		return objInst;
	}
	
	public static Response jsonizeResponse(Status status, Exception e, int languageId, String errResource )
	{
		HashMap<String, Object>jsonResponse = new HashMap<>();
		Gson gson = new Gson();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		if (e != null)
		{
			e.printStackTrace(pw);
		}
		String objAttribute = ((status.getStatusCode() >= 200) && (status.getStatusCode() < 300) ? "resource" : "message");
		jsonResponse.clear();
		jsonResponse.put(objAttribute, 
						 LanguageResources.getResource(languageId, errResource) + 
						 	(e == null ? "" : " (" + e.getMessage() + ")\n" + sw.toString()));
		return Response.status(status).entity(gson.toJson(jsonResponse)).build();
	}
	
	public static Response jsonizeResponseWithExceptionMessage(Status status, Exception e, int languageId)
	{
		HashMap<String, Object>jsonResponse = new HashMap<>();
		Gson gson = new Gson();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		if (e == null)
		{
			return jsonizeResponse(status, e, languageId, "generic.execError");
		}
		e.printStackTrace(pw);
		String objAttribute = ((status.getStatusCode() >= 200) && (status.getStatusCode() < 300) ? "resource" : "message");
		jsonResponse.clear();
		jsonResponse.put(objAttribute, e.getMessage());
		return Response.status(status).entity(gson.toJson(jsonResponse)).build();
	}

	public static String quoteSpecialChar(String input)
	{
		String out = "";
		out = input.replaceAll("&", "&amp;").replaceAll("\"", "&quot;")
				   .replaceAll("'", "&#39;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
				   .replace("\n", " ");
		return out;
	}
	
	public static String quoteSpecialCharWithBlank(String input)
	{
		String out = "";
		out = input.replaceAll("&", " ").replaceAll("\"", " ")
				   .replaceAll("'", " ").replaceAll("<", " ").replaceAll(">", " ")
				   .replace("\n", " ");
		return out;
	}
		
	public static String asciifyString(String in)
	{
		char[] inByteArray = in.toCharArray();
		
		for(int i = 0; i < inByteArray.length; i++)
		{
			if ((inByteArray[i] < 32) || (inByteArray[i] > 127))
			{
				inByteArray[i] = 32;
			}
		}
		return String.valueOf(inByteArray);
	}
}