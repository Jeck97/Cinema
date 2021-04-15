package controller.validator;

public class DateException extends Exception {

	private static final long serialVersionUID = 1L;

	public DateException() {

		super("Please select a date");
	}
	
}
