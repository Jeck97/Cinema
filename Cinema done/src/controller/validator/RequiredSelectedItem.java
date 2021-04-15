package controller.validator;

public class RequiredSelectedItem extends Exception
{
		private static final long serialVersionUID = 1L;
		
		public RequiredSelectedItem(String field) 
		{
			super(field + " is empty");
		}
	
}
