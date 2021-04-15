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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Admin;
import controller.validator.InvalidNumberException;
import controller.validator.MaximumLengthException;
import controller.validator.MaximumNumberException;
import controller.validator.MinimumLengthException;
import controller.validator.MinimumNumberException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;

public class AddMovieDialog  extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JTextField txtName = new JTextField();
	private JTextField txtType = new JTextField(12);
	private JTextField txtPrice = new JTextField();
	private JCheckBox chkOnScreen = new JCheckBox("Yes", true);
	private JTextArea txtDescription = new JTextArea(8,1);
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");

	public AddMovieDialog(AdminMainPage dialog) 
	{
		super(dialog, "Add Movie", true);

		txtDescription.setLineWrap(true);

		JPanel pnlCenter = new JPanel(new GridLayout(4, 2, 10, 10));
		JPanel pnlBottom = new JPanel(new GridLayout(1, 1, 10, 10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		
		pnlCenter.setBackground(Color.black);
		pnlBottom.setBackground(Color.black);
		pnlSouth.setBackground(Color.black);
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlBottom.setBorder(BorderFactory.createEmptyBorder(5, 10, 20, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		
		JLabel lblMovieName = new JLabel("Movie name:", JLabel.RIGHT);
		JLabel lblMovieType = new JLabel("Movie type:", JLabel.RIGHT);
		JLabel lblMoviePrice = new JLabel("Movie price (RM):", JLabel.RIGHT);
		JLabel lblOnScreen = new JLabel("On Screen:", JLabel.RIGHT);
		JLabel lblDescripton = new JLabel("Description:", JLabel.RIGHT);
		
		lblMovieName.setForeground(new Color(222,212,87));
		lblMoviePrice.setForeground(new Color(222,212,87));
		lblMovieType.setForeground(new Color(222,212,87));
		lblOnScreen.setForeground(new Color(222,212,87));
		lblDescripton.setForeground(new Color(222,212,87));
		chkOnScreen.setForeground(new Color(222,212,87));
		
		chkOnScreen.setBackground(Color.black);
		
		pnlCenter.add(lblMovieName);
		pnlCenter.add(txtName);
		pnlCenter.add(lblMovieType);
		pnlCenter.add(txtType);
		pnlCenter.add(lblMoviePrice);
		pnlCenter.add(txtPrice);
		pnlCenter.add(lblOnScreen);
		pnlCenter.add(chkOnScreen);
		pnlBottom.add(lblDescripton);
		pnlBottom.add(txtDescription);
		pnlSouth.add(btnSubmit);
		pnlSouth.add(btnReset);
		
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

		this.add(pnlCenter, BorderLayout.NORTH);
		this.add(pnlBottom, BorderLayout.CENTER);
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
			Vector<String> movie = new Vector<String>();
			Vector<Exception> exceptions = new Vector<>();

			try 
			{
				movie.add(Validator.validate("Movie name", txtName.getText(), true, 5, 50));
			}
			catch (RequiredFieldException | MaximumLengthException | MinimumLengthException e) 
			{
				exceptions.add(e);
			} 

			try 
			{
				movie.add(Validator.validate("Movie type", txtType.getText(), true, 0, 10));
			}
			catch (RequiredFieldException | MaximumLengthException | MinimumLengthException e) 
			{
				exceptions.add(e);
			} 

			try 
			{
				movie.add(String.valueOf(Validator.validate("Movie price", txtPrice.getText(), true, true, true, 5.0, 50.0)));
			} 
			catch (RequiredFieldException | InvalidNumberException | MinimumNumberException | MaximumNumberException e) 
			{
				exceptions.add(e);
			}

			movie.add(String.valueOf(chkOnScreen.isSelected()));
			
			try 
			{
				movie.add(Validator.validate("Description", txtDescription.getText(), true, -1, 10000));
			}
			catch (RequiredFieldException | MaximumLengthException | MinimumLengthException e) 
			{
				exceptions.add(e);
			} 

			

			int size = exceptions.size();

			if (size == 0)
			{	
				if (Admin.addMovie(movie) > 0)
				{
					reset();
					JOptionPane.showMessageDialog(this, "Movie " + movie.get(0) + " has been successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
				else
					JOptionPane.showMessageDialog(this, "Unable to add new movie", "Unsuccessful", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				String message = null;

				if (size == 1)
					message = exceptions.firstElement().getMessage();
				else
				{
					message = "Please fix the following errors:";

					for (int i = 0; i < size; i++)
						message += "\n" + (i + 1) + ". " + exceptions.get(i).getMessage();
				}
				JOptionPane.showMessageDialog(this, message, "Unsuccessful", JOptionPane.WARNING_MESSAGE);
			}

		}
		else if (source == btnReset)
		{
			reset();
		}
	}

	private void reset()
	{
		txtName.setText("");
		txtType.setText("");
		txtPrice.setText("");
		chkOnScreen.setSelected(true);
		txtDescription.setText("");
		txtName.grabFocus();
	}
}
