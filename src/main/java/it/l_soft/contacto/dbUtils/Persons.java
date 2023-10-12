package it.l_soft.contacto.dbUtils;

public class Persons extends DBInterface {
	private static final long serialVersionUID = 6799339848094284973L;
	protected int idPersons;
	protected int idCustomer;
	protected String familyName;
	protected String firstName;
	protected String role;
	protected String mobile;
	protected String office;
	protected String email;
	protected String street;
	protected String zip;
	protected String city;
	protected String region;
	protected String country;
	
	protected boolean status;
	
	protected boolean selected = false;

	private void setNames()
	{
		tableName = "Persons";
		idColName = "idPerson";
	}

	public Persons()
	{
		setNames();
	}
	
	public int getIdPersons() {
		return idPersons;
	}

	public void setIdPersons(int idPersons) {
		this.idPersons = idPersons;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
