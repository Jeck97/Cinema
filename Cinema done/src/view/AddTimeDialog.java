package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import controller.Admin;
import controller.validator.CannotEqualDateException;
import controller.validator.MinDateException;
import controller.validator.Validator;

public class AddTimeDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JComboBox<String> cmbMovieName;
	private JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
	private JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");
	
	public AddTimeDialog(AdminMainPage dialog)
	{
		super(dialog, "Add Movie Showtime", true);
		
		cmbMovieName = new JComboBox<String>(Admin.getMoviesName());
		
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MMM/yyyy");
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		
		JPanel pnlCenter = new JPanel(new GridLayout(3, 2, 10, 10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		JLabel lblMovieName = new JLabel("Movie name:", JLabel.RIGHT);
		JLabel lblShowDate = new JLabel("Show date:", JLabel.RIGHT);
		JLabel lblShowTime = new JLabel("Show time:", JLabel.RIGHT);
		
		lblMovieName.setForeground(new Color(222,212,87));
		lblShowDate.setForeground(new Color(222,212,87));
		lblShowTime.setForeground(new Color(222,212,87));
		
		pnlCenter.add(lblMovieName);
		pnlCenter.add(cmbMovieName);
		pnlCenter.add(lblShowDate);
		pnlCenter.add(dateSpinner);
		pnlCenter.add(lblShowTime);
		pnlCenter.add(timeSpinner);
		pnlSouth.add(btnSubmit);
		pnlSouth.add(btnReset);
		
		pnlCenter.setBackground(Color.black);
		pnlSouth.setBackground(Color.black);
		
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnSubmit.setBackground(new Color(204,204,0));
				btnSubmit.setForeground(Color.black);
			}
			public void mouseExited(MouseEvent evt) {
				btnSubmit.setBackground(new Color(102,51,0));
				btnSubmit.setForeground(new Color(222,212,87));
			}
		});
		btnReset.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnReset.setBackground(new Color(204,204,0));
				btnReset.setForeground(Color.black);
			}
			public void mouseExited(MouseEvent evt) {
				btnReset.setBackground(new Color(102,51,0));
				btnReset.setForeground(new Color(222,212,87));
			}
		});
		
		btnSubmit.setBounds(858, 52, 93, 29);
		btnReset.setBounds(858, 52, 93, 29);

		btnSubmit.setBackground(new Color(102,51,0));
		btnReset.setBackground(new Color(102,51,0));
		
		btnSubmit.setForeground(new Color(222,212,87));
		btnReset.setForeground(new Color(222,212,87));
		
		btnSubmit.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnReset.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		
		btnSubmit.setFocusPainted(false);
		btnReset.setFocusPainted(false);
		
		
		btnSubmit.addActionListener(this);
		btnReset.addActionListener(this);
		
		dateSpinner.setEditor(dateEditor);
		dateSpinner.setValue(new Date());
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date());
		
		this.add(pnlCenter);
		this.add(pnlSouth, BorderLayout.SOUTH);
		
		this.setResizable(false);
		this.pack();
		this.getRootPane().setDefaultButton(btnSubmit);
		this.setLocationRelativeTo(dialog);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source = event.getSource();
		
		if (source == btnSubmit)
		{
			SimpleDateFormat formater = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormater = new SimpleDateFormat("HH:mm");
			SimpleDateFormat dateTimeFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			
			try 
			{
				String movieName = cmbMovieName.getItemAt(cmbMovieName.getSelectedIndex());
				Vector<String> dateExistString = Admin.getTimeTableOfMovie(movieName);
				Vector<Date> dateExist = new Vector<Date>();
				
				for (String dateString : dateExistString)
					dateExist.add(dateTimeFormater.parse(dateString));
					
				String date = dateFormater.format(formater.parse(dateSpinner.getValue().toString()));
				String time = timeFormater.format(formater.parse(timeSpinner.getValue().toString()));
				String datePicked = date + " " + time;
				
				if (Validator.validateDateAdded("Movie show time", dateTimeFormater.parse(datePicked), dateExist, 2))
				{
					if (Admin.addTime(movieName, datePicked) > 0)
					{
						JOptionPane.showMessageDialog(this, "New time has been successfully added to " + movieName + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
					}
					else
						JOptionPane.showMessageDialog(this, "Unable to add new time", "Unsuccessful", JOptionPane.WARNING_MESSAGE);
				}
			} 
			catch (ParseException | HeadlessException e) 
			{
				e.printStackTrace();
			} 
			catch (CannotEqualDateException | MinDateException e) 
			{
				JOptionPane.showMessageDialog(this, e.getMessage(), "Unsuccessful", JOptionPane.WARNING_MESSAGE);
			} 
		}
		else if (source == btnReset)
		{
			cmbMovieName.setSelectedIndex(0);
			dateSpinner.setValue(new Date());
			timeSpinner.setValue(new Date());
		}
	}
}
