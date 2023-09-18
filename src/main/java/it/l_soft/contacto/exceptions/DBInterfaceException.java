package it.l_soft.contacto.exceptions;

public class DBInterfaceException extends Exception {

	private static final long serialVersionUID = 431436018857288692L;

	public DBInterfaceException (String errorMessage)
	{
		super(errorMessage);
	}
}
