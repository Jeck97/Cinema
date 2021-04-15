package controller.validator;

public class DisabledUser extends Exception
{
	private static final long serialVersionUID = 1L;

	public DisabledUser()
	{
		super("Your account is deactivated");
	}
}
