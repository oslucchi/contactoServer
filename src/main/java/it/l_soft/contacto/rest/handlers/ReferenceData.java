package it.l_soft.contacto.rest.handlers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import it.l_soft.contacto.dbUtils.Customers;
import it.l_soft.contacto.dbUtils.DBConnection;
import it.l_soft.contacto.rest.ApplicationProperties;
import it.l_soft.contacto.utils.Utils;

@Path("/referenceData")
public class ReferenceData {
	@Context
	private ServletContext context;
	
	ApplicationProperties prop = ApplicationProperties.getInstance();
	final Logger log = Logger.getLogger(this.getClass());
	
	boolean useExtension = false;
	
	DBConnection conn = null;
	
	@GET
	@Path("/customers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCustomers(@HeaderParam("Language") String language)
	{
		prop.setLanguageId(language);
		DBConnection conn;
		ArrayList<Customers> customers = null;
		try {
			conn = new DBConnection();
			new Customers();
			customers = Customers.getAllCustomers(conn);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Utils.jsonizeSingleObject(customers);
	}

}
