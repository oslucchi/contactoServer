package it.l_soft.contacto.rest;
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

@Path("/test")
public class Test {
	@Context
	private ServletContext context;
	ApplicationProperties prop = ApplicationProperties.getInstance();
	final Logger log = Logger.getLogger(this.getClass());
	
	
	@GET
	@Path("/landing")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response landing(String body, @HeaderParam("Language") String language)
	{
		log.debug("Landing page reached");
		return Response.status(Response.Status.OK).entity("Landing page reached").build();
	}
}
