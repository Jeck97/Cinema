package controller.validator;

public class TextNotSame extends Exception
{
	private static final long serialVersionUID = 1L;

	public TextNotSame(String text1, String text2)
	{
		super(text1+" does not match with "+text2);
	}
}
