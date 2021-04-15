package controller.validator;

public class CannotLoginException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public CannotLoginException() 
	{
		super("Username and Password not matched");
	}
}
