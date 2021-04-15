package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

public class Admin
{	
	public static int addMovie(Vector<String> movie)
	{
		return linkDatabaseUpdate("INSERT INTO MOVIE VALUES(default,'"+movie.get(0)+"', '"+movie.get(1)+"','"+movie.get(2)+"', "+movie.get(3)+", '"+movie.get(4)+"')");
	}

	public static int addTime(String movieName, String date)
	{
		return linkDatabaseUpdate("INSERT INTO TIMETABLE VALUES('"+date+"', '"+movieName+"')");
	}
	
	public static int addUser(String name, String pass)
	{
		return linkDatabaseUpdate("INSERT INTO USER VALUES('"+name+"', '"+pass+"', 1)");
	}

	public static int deleteTimeByTime(String time)
	{
		return linkDatabaseUpdate("DELETE FROM TIMETABLE WHERE DATE = '"+time+"'");
	}
	
	public static int updateMovie(String movieName)
	{
		return linkDatabaseUpdate("UPDATE MOVIE SET ONSCREEN = false WHERE NAME = '"+movieName+"'");
	}
	
	public static int updateMovie1(String movieName)
	{
		return linkDatabaseUpdate("UPDATE MOVIE SET ONSCREEN = true WHERE NAME = '"+movieName+"'");
	}

	public static int updateUser(String userName)
	{
		return linkDatabaseUpdate("UPDATE USER SET AVAILABILITY = false WHERE USERNAME = '"+userName+"'");
	}
	
	public static int updateUser1(String userName)
	{
		return linkDatabaseUpdate("UPDATE USER SET AVAILABILITY = true WHERE USERNAME = '"+userName+"'");
	}

	public static int updatedTimeTableMovieName(String oldName, String newName)
	{
		return linkDatabaseUpdate("UPDATE TIMETABLE SET MOVIENAME = '"+newName+"' WHERE MOVIENAME = '"+oldName+"'");
	}
	
	public static int updateData(Vector<String> info, String table)
	{
		int i = 0;	
		if (table.equals("MOVIE")) {			
			return linkDatabaseUpdate("UPDATE MOVIE SET NAME = '"+info.get(++i)+"', TYPE = '"+info.get(++i)+"', PRICE = '"+info.get(++i)+"', ONSCREEN = "+info.get(++i)+", DESCRIPTION =\""+info.get(++i)+"\" WHERE ID = '"+info.get(0)+"'");
		}
		else if (table.equals("TIMETABLE"))
			return linkDatabaseUpdate("UPDATE TIMETABLE SET DATE = '"+info.get(++i)+"' WHERE MOVIENAME = '"+info.get(0)+"'");
		else if (table.equals("USER"))
			return linkDatabaseUpdate("UPDATE USER SET LEVEL = "+info.get(++i)+", AVAILABILITY = "+info.get(++i)+" WHERE USERNAME = '"+info.get(0)+"'");
		return -1;
	}

	public static CachedRowSet getTable(String tableName)
	{
		return linkDatabase("SELECT * FROM "+tableName);
	}
	
	public static CachedRowSet getFilterMovie(String column)
	{
		return linkDatabase("SELECT * FROM MOVIE WHERE ONSCREEN = TRUE"+column+"");
	}
	
	public static int countTable(String tableName)
	{
		return linkDatabaseCount("SELECT COUNT(*) FROM "+tableName); 
	}
	
	public static int countFilterMovie(String column)
	{
		return linkDatabaseCount("SELECT COUNT(*) FROM MOVIE WHERE ONSCREEN = TRUE"+column+"");
	}
	
	public static int countSeatAtDate(String movieName, String time)
	{
		return linkDatabaseCount("SELECT count(*) from seats where moviename = '"+ movieName +"' and time = '"+ time +"'");
	}
	
	public static int checkExistOfUser(String name)
	{
		return linkDatabaseCount("SELECT COUNT(*) FROM USER WHERE USERNAME = '"+name+"'");
	}
	
	public static int getUserAvailablility(String name)
	{
		return linkDatabaseCount("SELECT AVAILABILITY FROM USER WHERE USERNAME = '"+name+"'");
	}
	
	public static String getMovieDescription(String movieName)
	{
		return linkDatabaseString("SELECT DESCRIPTION FROM MOVIE WHERE NAME = '"+movieName+"'");
	}
	
	public static Vector<String> getMoviesName()
	{
		Vector<String> temp = new Vector<String>();
		ResultSet rs = linkDatabase("SELECT NAME FROM MOVIE");
		try 
		{
			while (rs.next())
				temp.add(rs.getString(1));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return temp;
	}
	
	public static Vector<Vector<String>> getMovieIDName() throws SQLException
	{
		Vector<Vector<String>> movies = new Vector<Vector<String>>();
		ResultSet rs = linkDatabase("SELECT ID, NAME FROM MOVIE");

		while (rs.next())
		{
			Vector<String> info = new Vector<String>();
			info.add(rs.getString(1));
			info.add(rs.getString(2));
			movies.add(info);
		}
		return movies;
	}

	public static Vector<String> getTimeTableOfMovie(String name)
	{
		ResultSet rs = linkDatabase("SELECT * FROM TIMETABLE WHERE MOVIENAME = '"+ name +"'");
		Vector<String> dateTime = new Vector<>();
		try 
		{
			while (rs.next())
				dateTime.add(rs.getDate(1) +" " +rs.getTime(1));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		myConnection.close();
		return dateTime;
	}

	public static int linkDatabaseUpdate(String sql)
	{
		int status = -1;
		Connection conn = null;
		PreparedStatement ps = null;

		try 
		{
			conn =  myConnection.getConnection();
			ps = conn.prepareStatement(sql);
			status = ps.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try { if (ps != null) ps.close(); } catch (SQLException e) {};
			try { if (conn != null) conn.close(); } catch (SQLException e) {};
		}

		return status;
	}

	public static CachedRowSet linkDatabase(String sql)
	{
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		CachedRowSet rowSet = null;
		
		try 
		{
			conn =  myConnection.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rowSet = new CachedRowSetImpl();
			rowSet.populate(rs);
			
			rs.next();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch (SQLException e) {};
			try { if (ps != null) ps.close(); } catch (SQLException e) {};
			try { if (conn != null) conn.close(); } catch (SQLException e) {};
		}

		return rowSet;
	}
	
	public static int linkDatabaseCount(String sql)
	{
		int count = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn =  myConnection.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			count = rs.getInt(1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch (SQLException e) {};
			try { if (ps != null) ps.close(); } catch (SQLException e) {};
			try { if (conn != null) conn.close(); } catch (SQLException e) {};
		}

		return count;
	}
	
	public static String linkDatabaseString(String sql)
	{
		String text = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try 
		{
			conn =  myConnection.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			text = rs.getString(1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch (SQLException e) {};
			try { if (ps != null) ps.close(); } catch (SQLException e) {};
			try { if (conn != null) conn.close(); } catch (SQLException e) {};
		}

		return text;
	}
}
