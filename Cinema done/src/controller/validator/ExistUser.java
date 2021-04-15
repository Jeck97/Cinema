package controller.validator;

public class ExistUser extends Exception
{
	private static final long serialVersionUID = 1L;

	public ExistUser()
	{
		super("Username exist");
	}
}
