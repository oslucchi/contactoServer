package it.l_soft.contacto.dbUtils;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Companies extends DBInterface {	
	private static final long serialVersionUID = -7615245450418546503L;

	protected int idCompany;
	protected int idSegment;
	protected String description;
	protected boolean status;

	protected boolean selected = false;
	
	private void setNames()
	{
		tableName = "Companies";
		idColName = "idCompany";
	}

	public Companies()
	{
		setNames();
	}
	
	public void getCustomer(DBConnection conn, int id) throws Exception
	{
		setNames();
		String sql = "SELECT * " +
					 "FROM " + tableName + " " +
					 "WHERE " + idColName + " = " + id;
		this.populateObject(conn, sql, this);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Companies> getAllCustomers(DBConnection conn) throws Exception {
		Logger log = Logger.getLogger(Companies.class);
		String sql = "SELECT * " +
					 "FROM Companies";
		log.trace("Querying: " + sql);
		return (ArrayList<Companies>) DBInterface.populateCollection(conn, sql, Companies.class);
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCustomer(int idCompany) {
		this.idCompany = idCompany;
	}

	public int getIdSegment() {
		return idSegment;
	}

	public void setIdSegment(int idSegment) {
		this.idSegment = idSegment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
