package model;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

import controller.Admin;

public class TableModelAdmin extends DefaultTableModel
{
	private static final long serialVersionUID = 1L;
	private int disable;
	
	public TableModelAdmin(String currentTable)
	{
		CachedRowSet rowSet = Admin.getTable(currentTable);
		int row = Admin.countTable(currentTable);
		

		int[] index = null;
		String[] colName = null;
		String[][] data = null;
		
		if (currentTable.equals("MOVIE"))
		{
			disable=4;
			index = new int[] {1, 2, 3, 4, 5, 6};
			colName = new String[] {"ID", "Name", "Type", "Price(RM)", "On Screen", "Description"};
			data = new String[row][6];
		}
		if (currentTable.equals("TIMETABLE"))
		{
			disable=0;
			index = new int[] {2, 1};
			colName = new String[] {"Movie name", "Date and time"};
			data = new String[row][2];
		}
		if (currentTable.equals("USER"))
		{
			disable=1;
			index = new int[] {1, 3};
			colName = new String[] {"User Name", "Avaliability"};
			data = new String[row][2];
		}		
		try 
		{
			for (int i = 0; rowSet.next(); i++)
				for(int j = 0; j < data[i].length; j++)
					data[i][j] = rowSet.getString(index[j]);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		setDataVector(data, colName);
	}

	@Override
	public boolean isCellEditable(int row, int column) 
	{
		if(column == 0 || column == disable) 
			return false;
		return true;
	}
}
