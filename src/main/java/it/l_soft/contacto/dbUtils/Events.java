package it.l_soft.contacto.dbUtils;

import java.util.ArrayList;
import java.util.Date;

public class Events extends DBInterface {	
	
	private static final long serialVersionUID = 933935830484629799L;
	
	protected int idEvent;
	protected int idOwner;
	protected int idCompany;
	protected Date date;
	protected int duration;
	protected String company;
	protected String description;
	protected ArrayList<Persons> participants;

	protected boolean status;
		
	protected boolean selected = false;

	private void setNames()
	{
		tableName = "Events";
		idColName = "idEvent";
	}

	public Events()
	{
		setNames();
	}

	public static ArrayList<Events> getFutureEventsByOwner(DBConnection conn, int idOwner, int limit) throws Exception
	{
		String sql = "SELECT a.*, b.description as company " +
					 "FROM Events a LEFT JOIN Companies b ON (" +
					 "       a.idCompany = b.idCompany " +
					 "     ) " +
					 "WHERE idOwner = " + idOwner + " AND " + 
					 "      date >= CURDATE() " + 
					 (limit > 0 ? "LIMIT " + limit : "");
		
		@SuppressWarnings("unchecked")
		ArrayList<Events> events = (ArrayList<Events>) DBInterface.populateCollection(conn, sql, Events.class);
		for(Events event : events)
		{
			event.setParticipants(EventParticipants.getPersonsParticipatingToEvent(conn, event.getIdEvent()));		
		}
		return events;
	}
	
	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Persons> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Persons> participants) {
		this.participants = participants;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
