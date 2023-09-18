package it.l_soft.contacto.dbUtils;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Customers extends DBInterface {	
	private static final long serialVersionUID = -7615245450418546503L;

	protected int idCustomer;
	protected int idSegment;
	protected String description;
	protected boolean status;

	protected boolean selected = false;
	
	private void setNames()
	{
		tableName = "Customers";
		idColName = "idCustomer";
	}

	public Customers()
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
	public static ArrayList<Customers> getAllCustomers(DBConnection conn) throws Exception {
		Logger log = Logger.getLogger(Customers.class);
		String sql = "SELECT * " +
					 "FROM Customers";
		log.trace("Querying: " + sql);
		return (ArrayList<Customers>) DBInterface.populateCollection(conn, sql, Customers.class);
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
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
