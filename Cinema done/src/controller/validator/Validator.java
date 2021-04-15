package controller.validator;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

import controller.Admin;
import controller.myConnection;

public class Validator 
{
	public static double validate(String field, String value, boolean required, boolean hasMinimum, boolean hasMaximum, double minimum, double maximum) 
			throws RequiredFieldException, InvalidNumberException, MinimumNumberException, MaximumNumberException
	{
		if (required && (value == null || value.trim().isEmpty()))
			throw new RequiredFieldException(field);
		value = value.trim();

		double number = 0;

		try
		{
			number = Double.parseDouble(value);
		}
		catch(NumberFormatException e)
		{
			throw new InvalidNumberException(field);
		}
		if (hasMinimum && number <minimum)
			throw new MinimumNumberException(field, minimum);

		if (hasMaximum && number > maximum)
			throw new MaximumNumberException(field, maximum);

		return number;
	}	

	public static int validate(String field, String value, boolean required, boolean hasMinimum, boolean hasMaximum, int minimum, int maximum) 
			throws RequiredFieldException, InvalidNumberException, MinimumNumberException, MaximumNumberException
	{
		if (required && (value == null || value.trim().isEmpty()))
			throw new RequiredFieldException(field);
		value = value.trim();

		int number = 0;

		try
		{
			number = Integer.parseInt(value);
		}
		catch(NumberFormatException e)
		{
			throw new InvalidNumberException(field);
		}
		if (hasMinimum && number <minimum)
			throw new MinimumNumberException(field, minimum);

		if (hasMaximum && number > maximum)
			throw new MaximumNumberException(field, maximum);

		return number;
	}

	public static String validate(String field, String value, boolean required, int min, int max) 
			throws RequiredFieldException, MaximumLengthException, MinimumLengthException
	{
		if(required && (value == null) || value.isEmpty())
			throw new RequiredFieldException(field);

		value = value.trim();

		if(value.length() < min)
			throw new MinimumLengthException(field, min);

		if(value.length() > max)
			throw new MinimumLengthException(field, max);

		return value;
	}

	public static boolean validateUser(String value) throws SQLException, CannotFindException 
	{
		if (!Admin.linkDatabase("Select * from USER where BINARY Username = '" + value + "'").next())
			throw new CannotFindException();
		myConnection.close();
		return true;
	}

	public static boolean validateLogin(String valueUser, String valuePass) throws SQLException, CannotLoginException
	{
		if(!Admin.linkDatabase("Select * from USER where BINARY Username = '"+ valueUser +"' and BINARY Password = '"+valuePass+"'").next())
			throw new CannotLoginException();
		myConnection.close();
		return true;
	}
	
	public static boolean validateUserAvailablity(String name) throws DisabledUser
	{
		if (Admin.getUserAvailablility(name) == 0)
			throw new DisabledUser();
		return true;
	}

	public static boolean validateSelectedItem(String field, int rowSelected) throws RequiredSelectedItem
	{
		if(rowSelected < 0)
			throw new RequiredSelectedItem(field);
		return true;
	}

	public static boolean validateSelDate(Date selDt) throws DateException, MinDateException
	{
		if(selDt == null)
			throw new DateException();

		int intSelDt = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(selDt));
		int intToday = Integer.parseInt(String.valueOf(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now())));
		
		if(intSelDt < intToday)
			throw new MinDateException("Date selected", "");	
		return true;
	}

	public static boolean validateDateAdded(String field, Date date, Vector<Date> dateExist, int minHours) throws CannotEqualDateException, MinDateException
	{
		String message = minHours > 0? minHours+"hours of " : "";
		
		if (new Date(date.getTime() - minHours*3600*1000).before(new Date()))
			throw new MinDateException(field, message);
		for (Date dateTime : dateExist)
			if (date.equals(dateTime))
				throw new CannotEqualDateException(field, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dateTime));
		return true;
	}
	
	public static boolean validateUsername(String name) throws ExistUser
	{
		if (Admin.checkExistOfUser(name) != 0)
			throw new ExistUser();
		return true;
	}
	
	public static boolean validateTextMatch(String field1, String field2, String text1, String text2) throws TextNotSame
	{
		if (!text1.equals(text2))
			throw new TextNotSame(field1, field2);
		return true;
	}
}
