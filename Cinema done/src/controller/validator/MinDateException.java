package controller.validator;

public class MinDateException extends Exception {

	private static final long serialVersionUID = 1L;

	public MinDateException(String field, String minHours) 
	{
			super(field + " must be after "+ minHours+" current date.");
	}
	
}
