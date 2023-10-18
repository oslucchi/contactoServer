package it.l_soft.contacto.dbUtils;

import java.util.ArrayList;
import java.util.Date;

public class Reports extends DBInterface {
	private static final long serialVersionUID = 8673712835807537226L;
	
	protected int idReport;
	protected int idEvent;
	protected int idCompany;
	protected int idAgent;
	protected Date date;
	protected String report;
	protected boolean archived;
	
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
		String sql = "SELECT * FROM Reports " +
					 "WHERE idCompany = " + idCompany + " AND " +
					 "      archived = 0";
		return (ArrayList<Reports>) DBInterface.populateCollection(conn, sql, Reports.class);
	}
}
