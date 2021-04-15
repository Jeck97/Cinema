package controller.validator;

public class MinimumNumberException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public MinimumNumberException(String field, double minimum)	
	{
		super(field + " must be less than or equals to " + minimum + ".");
	}
	
	public MinimumNumberException(String field, int minimum)
	{
		super(field + " must be less than or equals to " + minimum + ".");
	}
}
