package controller.validator;

public class CannotEqualDateException extends Exception
{
	private static final long serialVersionUID = 1L;

	public CannotEqualDateException(String field, String date)
	{
		super(field + " cannot equal with " + date + ".");
	}
}
