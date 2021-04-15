package model;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

import controller.Admin;

public class TableModelCustomer extends DefaultTableModel
{
	private static final long serialVersionUID = 1L;

	public TableModelCustomer(boolean filter, String category, String text)
	{
		CachedRowSet rowSet;
		int row;
		
		if (filter)
		{
			if (category.equals("PRICE"))
			{
				rowSet = Admin.getFilterMovie(" AND "+category+" <= '"+text+"'");
				row = Admin.countFilterMovie(" AND "+category+" <= '"+text+"'");
			}
			else
			{
				rowSet = Admin.getFilterMovie(" AND "+category+" LIKE '%"+text+"%'");
				row = Admin.countFilterMovie(" AND "+category+" LIKE '%"+text+"%'");
			}
		}
		else
		{
			 rowSet = Admin.getFilterMovie("");
			 row = Admin.countFilterMovie("");
		}
		
		String[][] data = new String[row][3];
		try 
		{
			for (int i = 0; rowSet.next(); i++)
			{
				data[i][0] = rowSet.getString(2);
				data[i][1] = rowSet.getString(3);
				data[i][2] = rowSet.getString(4);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		setDataVector(data, new String[] {"Name", "Type", "Price(RM)"});
	}

	@Override
	public boolean isCellEditable(int row, int col) 
	{
		return false;
	}
}
