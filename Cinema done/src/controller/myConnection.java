package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class myConnection 
{
	private static Connection con = null;

	public static Connection getConnection() throws SQLException
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1","root","");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
