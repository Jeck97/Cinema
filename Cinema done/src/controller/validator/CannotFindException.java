package controller.validator;

public class CannotFindException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public CannotFindException() 
	{
		super("Username can not be found");
	}
}
