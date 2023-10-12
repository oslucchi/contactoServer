package it.l_soft.contacto.dbUtils;

import java.util.ArrayList;

public class EventParticipants extends DBInterface {	
	
	private static final long serialVersionUID = -741865120890620962L;
	protected int idEvent;
	protected int idPerson;
	protected ArrayList<Persons> participants;

	protected boolean status;
		
	protected boolean selected = false;

	private void setNames()
	{
		tableName = "EventParticipants";
	}

	public EventParticipants()
	{
		setNames();
	}

	@SuppressWarnings("unchecked")
	static public ArrayList<Persons> getPersonsParticipatingToEvent(DBConnection conn, int idEvent) throws Exception
	{
		String sql = "SELECT a.* " + 
					 "FROM Persons a INNER JOIN EventParticipants b ON (" +
					 "     		a.idPerson = b.idPerson " +
					 "     ) " +
					 "WHERE b.idEvent = " + idEvent;
		return (ArrayList<Persons>) DBInterface.populateCollection(conn, sql, Persons.class);
	}

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public ArrayList<Persons> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Persons> participants) {
		this.participants = participants;
	}
	
}
