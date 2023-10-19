package it.l_soft.contacto.dbUtils;

import java.util.ArrayList;
import java.util.Date;

public class Reports extends DBInterface {
	private static final long serialVersionUID = 8673712835807537226L;
	
	protected int idReport;
	protected int idReporter;
	protected int idEvent;
	protected int idCompany;
	protected Date date;
	protected String report;
	protected String summary;
	protected boolean archived;
	
	protected String reporter;
	protected String company;
	
	protected boolean status;
	
	protected boolean selected = false;

	public Reports()
	{
		tableName = "Events";
		idColName = "idEvent";
	}
	
	@SuppressWarnings("unchecked")
	static public ArrayList<Reports> getAllActiveReportsForCompanyId(DBConnection conn, int idCompany) 
				throws Exception
	{
		String sql = "SELECT a.*, CONCAT(b.firstName, ' ', b.familyName) as reporter " +
					 "FROM Reports a INNER JOIN Persons b ON ( " +
					 "       a.idReporter = b.idPerson " +
					 "     ) " +
					 "WHERE idCompany = " + idCompany + " AND " +
					 "      archived = 0 " +
					 "ORDER BY date DESC";
		return (ArrayList<Reports>) DBInterface.populateCollection(conn, sql, Reports.class);
	}

	public int getIdReport() {
		return idReport;
	}

	public int getIdReporter() {
		return idReporter;
	}

	public int getIdEvent() {
		return idEvent;
	}

	public int getIdCompany() {
		return idCompany;
	}

	public Date getDate() {
		return date;
	}

	public String getReport() {
		return report;
	}

	public String getSummary() {
		return summary;
	}

	public boolean isArchived() {
		return archived;
	}

	public String getReporter() {
		return reporter;
	}

	public boolean isStatus() {
		return status;
	}

	public boolean isSelected() {
		return selected;
	}
}
