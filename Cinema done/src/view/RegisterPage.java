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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Admin;
import controller.validator.ExistUser;
import controller.validator.MaximumLengthException;
import controller.validator.MinimumLengthException;
import controller.validator.RequiredFieldException;
import controller.validator.TextNotSame;
import controller.validator.Validator;

public class RegisterPage  extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JTextField txtName = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();
	private JPasswordField txtRetype = new JPasswordField();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");

	public RegisterPage(LoginPage frame) 
	{
		super(frame, "Registeration", true);

		JPanel pnlCenter = new JPanel(new GridLayout(3, 2, 10, 10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		
		pnlCenter.setBackground(Color.black);
		pnlSouth.setBackground(Color.black);
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

		JLabel lblUsername = new JLabel("Username:", JLabel.RIGHT);
		JLabel lblPassword = new JLabel("Password:", JLabel.RIGHT);
		JLabel lblRetypePassword = new JLabel("Retype Password:", JLabel.RIGHT);
		
		lblUsername.setForeground(new Color(222,212,87));
		lblPassword.setForeground(new Color(222,212,87));
		lblRetypePassword.setForeground(new Color(222,212,87));
		
		lblUsername.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblRetypePassword.setFont(new Font("Century Gothic", Font.BOLD, 12));
		
		pnlCenter.add(lblUsername);
		pnlCenter.add(txtName);
		pnlCenter.add(lblPassword);
		pnlCenter.add(txtPassword);
		pnlCenter.add(lblRetypePassword);
		pnlCenter.add(txtRetype);
		pnlSouth.add(btnSubmit);
		pnlSouth.add(btnReset);

		btnSubmit.addActionListener(this);
		btnReset.addActionListener(this);
		
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
		
		this.add(pnlCenter, BorderLayout.NORTH);
		this.add(pnlSouth, BorderLayout.SOUTH);

		this.setResizable(false);
		this.pack();
		this.getRootPane().setDefaultButton(btnSubmit);
		this.setLocationRelativeTo(frame);
		this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source = event.getSource();

		if (source == btnSubmit)
		{
			String name = null;
			String pass = null;
			Vector<Exception> exceptions = new Vector<>();

			try 
			{
				if(Validator.validateUsername(txtName.getText()))
					name = Validator.validate("Username", txtName.getText(), true, 8, 30);
			}
			catch (RequiredFieldException | MaximumLengthException | MinimumLengthException | ExistUser e) 
			{
				exceptions.add(e);
			} 

			try 
			{
				pass = Validator.validate("Password", txtPassword.getText(), true, 8, 20);
				Validator.validateTextMatch("Retype password", "Password", Validator.validate("Retype password", txtRetype.getText(), true, 8, 20), pass);
			}
			catch (RequiredFieldException | MaximumLengthException | MinimumLengthException | TextNotSame e) 
			{
				exceptions.add(e);
			} 

			try 
			{
				Validator.validateTextMatch("Retype password", "Password", Validator.validate("Retype password", txtRetype.getText(), true, 8, 20), pass);
			}
			catch (TextNotSame | RequiredFieldException | MaximumLengthException | MinimumLengthException e) 
			{
				exceptions.add(e);
			}
			
			int size = exceptions.size();

			if (size == 0)
			{	
				if (Admin.addUser(name, pass) > 0)
				{
					reset();
					JOptionPane.showMessageDialog(this, name + " has been successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
				else
					JOptionPane.showMessageDialog(this, "Unable to add new user", "Unsuccessful", JOptionPane.WARNING_MESSAGE);
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
		txtPassword.setText("");
		txtRetype.setText("");
		txtName.grabFocus();
	}
}
