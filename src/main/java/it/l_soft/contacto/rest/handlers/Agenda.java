package it.l_soft.contacto.rest.handlers;

import java.util.ArrayList;

import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import it.l_soft.contacto.dbUtils.DBConnection;
import it.l_soft.contacto.dbUtils.Events;
import it.l_soft.contacto.dbUtils.Reports;
import it.l_soft.contacto.rest.ApplicationProperties;
import it.l_soft.contacto.utils.JavaJSONMapper;
import it.l_soft.contacto.utils.Utils;


@Path("/agenda")
public class Agenda {
	@Context
	private ServletContext context;
	
	ApplicationProperties prop = ApplicationProperties.getInstance();
	final Logger log = Logger.getLogger(this.getClass());
	
	boolean useExtension = false;
	
	DBConnection conn = null;
	
	@POST
	@Path("/schedule")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCustomers(String body, @HeaderParam("Language") String language)
	{
		prop.setLanguageId(language);
		DBConnection conn;
		ArrayList<Events> events = new ArrayList<Events>();
		JsonObject jsonIn = JavaJSONMapper.StringToJSON(body);
		int numOfFutureItems = jsonIn.getInt("numOfFutureItems", 0);
		int idOwner = jsonIn.getInt("idOwner", 0);
		
		try {
			conn = new DBConnection();
			events = Events.getFutureEventsByOwner(conn, idOwner, numOfFutureItems);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Utils.jsonizeSingleObject(events);
	}

	@POST
	@Path("/getReports")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getReports(String body, @HeaderParam("Language") String language)
	{
		prop.setLanguageId(language);
		DBConnection conn;
		ArrayList<Reports> reports= new ArrayList<Reports>();
		JsonObject jsonIn = JavaJSONMapper.StringToJSON(body);
		int idCompany = jsonIn.getInt("idCompany", 0);
		
		try {
			conn = new DBConnection();
			reports = Reports.getAllActiveReportsForCompanyId(conn, idCompany);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Utils.jsonizeSingleObject(reports);
	}
}
