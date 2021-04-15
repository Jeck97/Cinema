package controller.validator;

public class MinimumLengthException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public MinimumLengthException(String field, int min)
	{
		super(field + " must be less than or equals to " + min + " characters. ");
	}
}
