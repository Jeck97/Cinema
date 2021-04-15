package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import controller.Admin;
import model.TableModelAdmin;

public class AdminMainPage extends JDialog implements ActionListener, TableModelListener, WindowListener
{
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JTable table;
	private JButton btnUpdate;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnRecover;
	private JButton btnChange;

	private Vector<Vector<String>> updatedData = new Vector<Vector<String>>();
	private Vector<Vector<String>> movieNames = new Vector<Vector<String>>();

	private String currentTable;
	private Object[] tableName = { "MOVIE", "TIMETABLE", "USER" };

	public AdminMainPage(LoginPage frame)
	{
		super(frame, "Admin Main Page", true);

		JPanel panel = new JPanel();
		JPanel buttonPane = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		JLabel lblAdminMainPage = new JLabel("Admin Main Page");
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setHgap(60);
		lblAdminMainPage.setForeground(new Color(222,212,87));
		lblAdminMainPage.setFont(new Font("Kristen ITC", Font.BOLD, 24));
		lblAdminMainPage.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnAdd = new JButton("Add");
		btnAdd.setToolTipText("Add");
		btnAdd.setBounds(858, 52, 93, 29);
		btnAdd.setFocusPainted(false);
		btnAdd.setBackground(new Color(102,51,0));
		btnAdd.setForeground(new Color(222,212,87));
		btnAdd.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnAdd.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnAdd.setBackground(new Color(204,204,0));
		        btnAdd.setForeground(Color.black);
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnAdd.setBackground(new Color(102,51,0));
		        btnAdd.setForeground(new Color(222,212,87));
		    }
		});
		btnAdd.addActionListener(this);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(858, 52, 93, 29);
		btnDelete.setFocusPainted(false);
		btnDelete.setBackground(new Color(102,51,0));
		btnDelete.setForeground(new Color(222,212,87));
		btnDelete.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnDelete.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnDelete.setBackground(new Color(204,204,0));
		        btnDelete.setForeground(Color.black);
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnDelete.setBackground(new Color(102,51,0));
		        btnDelete.setForeground(new Color(222,212,87));
		    }
		});
		btnDelete.addActionListener(this);

		btnRecover = new JButton("On Screen");
		btnRecover.setBounds(858, 52, 93, 29);
		btnRecover.setFocusPainted(false);
		btnRecover.setBackground(new Color(102,51,0));
		btnRecover.setForeground(new Color(222,212,87));
		btnRecover.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnRecover.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnRecover.setBackground(new Color(204,204,0));
		        btnRecover.setForeground(Color.black);
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnRecover.setBackground(new Color(102,51,0));
		        btnRecover.setForeground(new Color(222,212,87));
		    }
		});
		btnRecover.addActionListener(this);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setToolTipText("Update");
		btnUpdate.setBounds(858, 52, 93, 29);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBackground(new Color(102,51,0));
		btnUpdate.setForeground(new Color(222,212,87));
		btnUpdate.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnUpdate.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnUpdate.setBackground(new Color(204,204,0));
		        btnUpdate.setForeground(Color.black);
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnUpdate.setBackground(new Color(102,51,0));
		        btnUpdate.setForeground(new Color(222,212,87));
		    }
		});
		btnUpdate.addActionListener(this);

		btnChange = new JButton("Change");
		btnChange.setToolTipText("Change Table");
		btnChange.setBounds(858, 52, 93, 29);
		btnChange.setFocusPainted(false);
		btnChange.setBackground(new Color(102,51,0));
		btnChange.setForeground(new Color(222,212,87));
		btnChange.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnChange.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnChange.setBackground(new Color(204,204,0));
		        btnChange.setForeground(Color.black);
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnChange.setBackground(new Color(102,51,0));
		        btnChange.setForeground(new Color(222,212,87));
		    }
		});
		btnChange.addActionListener(this);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setBackground(new Color(222,212,87));
		table.setBackground(Color.yellow);
		table.setForeground(Color.black);
		table.setRowHeight(40);
		
		currentTable = "MOVIE";
		showTable();
		
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		panel.setBackground(Color.black);
		panel.add(lblAdminMainPage);
		
		scrollPane.getViewport().setBackground(Color.black);
		scrollPane.setViewportView(table);
		buttonPane.setLayout(fl_buttonPane);
		buttonPane.setBackground(Color.black);
		buttonPane.add(btnAdd);
		buttonPane.add(btnDelete);
		buttonPane.add(btnRecover);
		buttonPane.add(btnUpdate);
		buttonPane.add(btnChange);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setBackground(new Color(222,212,87));
		contentPanel.add(panel, BorderLayout.NORTH);
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
		this.setBounds(100, 100, 850, 520);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(this);
	}

	public void showTable()
	{
		table.setModel(new TableModelAdmin(currentTable));
		table.getModel().addTableModelListener(this);

		if (currentTable.equals("MOVIE"))
		{
			btnAdd.setVisible(true);
			btnUpdate.setVisible(true);
			btnRecover.setVisible(true);
			btnRecover.setText("On Screen");
			btnDelete.setText("Off Screen");
		}
		if (currentTable.equals("TIMETABLE"))
		{
			btnAdd.setVisible(true);
			btnUpdate.setVisible(true);
			btnUpdate.setText("Update");
			btnRecover.setVisible(false);
			btnDelete.setText("Delete");
		}
		if (currentTable.equals("USER"))
		{
			btnAdd.setVisible(false);
			btnUpdate.setVisible(false);
			btnRecover.setVisible(true);
			btnRecover.setText("Enable");
			btnDelete.setText("Disable");
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		Object src = evt.getSource();

		if (src == btnAdd) 
		{
			if (!updatedData.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please update the table first.", "Unable to add", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (currentTable == "MOVIE")
				new AddMovieDialog(this);
			else if (currentTable == "TIMETABLE")
				new AddTimeDialog(this);
			showTable();
		}
		else if (src == btnDelete)
		{
			if (!updatedData.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please update the table first.", "Unable to delete", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (table.getSelectedRowCount() != 0)
			{
				int currentRow = table.getSelectedRow();
				String selectedItem = currentTable.equals("USER")? table.getValueAt(currentRow, 0).toString() : table.getValueAt(currentRow, 1).toString();
				String Message = null;
				if (currentTable.equals("MOVIE"))
					Message = "Do you sure to off screen ";
				if (currentTable.equals("TIMETABLE"))
					Message = "Do you sure to delete ";
				if (currentTable.equals("USER"))
					Message = "Do you sure to disable ";
				Message += selectedItem + ".";

				if (JOptionPane.showConfirmDialog(this, Message, "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				{
					if (currentTable.equals("MOVIE"))
						Admin.updateMovie(selectedItem);
					else if (currentTable.equals("TIMETABLE"))
						Admin.deleteTimeByTime(selectedItem);
					else if (currentTable.equals("USER"))
						Admin.updateUser(selectedItem);
					showTable();
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Please select the row first. ", "Unable to delete", JOptionPane.WARNING_MESSAGE);
		}
		else if (src == btnRecover)
		{
			if (!updatedData.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please update the table first.", "Unable to delete", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (table.getSelectedRowCount() != 0)
			{
				int currentRow = table.getSelectedRow();
				String selectedItem = currentTable.equals("USER")? table.getValueAt(currentRow, 0).toString() : table.getValueAt(currentRow, 1).toString();
				String Message = null;
				if (currentTable == "MOVIE")
					Message = "Do you sure to on screen ";
				if (currentTable == "USER")
					Message = "Do you sure to enable ";
				Message += selectedItem + ".";

				if (JOptionPane.showConfirmDialog(this, Message, "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				{
					if (currentTable.equals("MOVIE"))
						Admin.updateMovie1(selectedItem);
					else if (currentTable.equals("USER"))
						Admin.updateUser1(selectedItem);
					showTable();
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Please select the row first. ", "Unable to delete", JOptionPane.WARNING_MESSAGE);
		}
		else if (src == btnUpdate) 
		{
			for (Vector<String> data : updatedData)
				Admin.updateData(data, currentTable);

			for (Vector<String> name : movieNames)
				Admin.updatedTimeTableMovieName(name.get(0), name.get(1));

			JOptionPane.showMessageDialog(this, updatedData.size() + " rows had been updated.", "Updated successful", JOptionPane.INFORMATION_MESSAGE);
			updatedData.clear();

			showTable();
		}
		else if (src == btnChange)
		{
			if (!updatedData.isEmpty())
				if (JOptionPane.showConfirmDialog(this, "The value changed haven't been updated.\nDo you want to proceed?", "Proceed without update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
					return;

			Object selectedTable = JOptionPane.showInputDialog(this, "Please select a table", "Change Table", JOptionPane.QUESTION_MESSAGE, null, tableName, tableName[0]);
			if (selectedTable != null)
			{
				currentTable = selectedTable.toString();
				showTable();
				updatedData.clear();
			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) 
	{
		int row = e.getFirstRow();
		int column = e.getColumn();

		try 
		{
			if (currentTable == "Movie" && column == 1)
			{
				Vector<Vector<String>> movies = Admin.getMovieIDName();
				for (Vector<String> movie : movies)
				{
					String oldID = movie.get(0);
					String oldName = movie.get(column);
					String newID = table.getValueAt(row, 0).toString();
					String newName = table.getValueAt(row, column).toString();

					if (oldID.equals(newID) && !oldName.equals(newName))
					{
						Vector<String> name = new Vector<String>();
						name.add(oldName);
						name.add(newName);
						movieNames.add(name);
					}
				}
			}
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}

		boolean addInfo = true;
		if (!updatedData.isEmpty()) 
		{
			for (int i = 0; i < updatedData.size(); i++)
				if (updatedData.get(i).get(0).equals(table.getValueAt(row, 0).toString()))
				{
					updatedData.get(i).set(column, table.getValueAt(row, column).toString());
					addInfo = false;
					break;
				}			
		}
		if (addInfo)
		{
			Vector<String> updatedInfo = new Vector<String>();
			for (int i = 0; i < table.getColumnCount(); i++)
				updatedInfo.add(table.getValueAt(row, i).toString());
			updatedData.add(updatedInfo);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}
	@Override
	public void windowClosed(WindowEvent e) 
	{
		new LoginPage();
	}
	@Override
	public void windowClosing(WindowEvent e) {
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
	}
	@Override
	public void windowIconified(WindowEvent e) {
	}
	@Override
	public void windowOpened(WindowEvent e) {
	}
}
