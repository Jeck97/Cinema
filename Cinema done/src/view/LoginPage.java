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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.validator.CannotFindException;
import controller.validator.CannotLoginException;
import controller.validator.DisabledUser;
import controller.validator.MaximumLengthException;
import controller.validator.MinimumLengthException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;

public class LoginPage extends JFrame implements ActionListener, WindowListener 
{
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPasswordField tfPassword;
	private JTextField tfUsername;
	private JButton btnLogin;
	private JButton btnRegister;

	public static void main(String[] args) 
	{
		new LoginPage();
	}

	public LoginPage()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1, 0, 0));
		JPanel pnlHeader = new JPanel();
		JPanel pnlButton = new JPanel();
		JPanel pnlUsername = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
		JPanel pnlPassword = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
		
		FlowLayout flowLayout = (FlowLayout) pnlButton.getLayout();
		flowLayout.setHgap(40);

		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblLoginPage.setForeground(new Color(222,212,87));
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblUsername.setForeground(new Color(222,212,87));
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblPassword.setForeground(new Color(222,212,87));
		
		tfUsername = new JTextField("Tan Zhi Zhong");
		tfUsername.setToolTipText("Please insert your username");
		tfUsername.setColumns(20);

		tfPassword = new JPasswordField("hahahehe");
		tfPassword.setToolTipText("Please insert your password");
		tfPassword.setColumns(20);

		btnLogin = new JButton("Log in");
		btnLogin.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		btnLogin.setBackground(new Color(102,51,0));
		btnLogin.setForeground(new Color(222,212,87));
		btnLogin.setFocusPainted(false);
		btnLogin.addActionListener(this);
		btnLogin.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnLogin.setBackground(new Color(204,204,0));
		        btnLogin.setForeground(Color.black);
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnLogin.setBackground(new Color(102,51,0));
		        btnLogin.setForeground(new Color(222,212,87));
		    }
		});
		
		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRegister.setBackground(new Color(102,51,0));
		btnRegister.setForeground(new Color(222,212,87));
		btnRegister.addActionListener(this);
		btnRegister.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnRegister.setBackground(new Color(204,204,0));
		        btnRegister.setForeground(Color.black);
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnRegister.setBackground(new Color(102,51,0));
		        btnRegister.setForeground(new Color(222,212,87));
		    }
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(222,212,87));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnlHeader, BorderLayout.NORTH);
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(pnlButton, BorderLayout.SOUTH);

		pnlHeader.setBackground(Color.black);
		pnlHeader.add(lblLoginPage);
		
		pnlUsername.setBackground(Color.black);
		pnlUsername.add(lblUsername);
		pnlUsername.add(tfUsername);
		
		pnlPassword.setBackground(Color.black);
		pnlPassword.add(lblPassword);
		pnlPassword.add(tfPassword);
		
		pnlButton.setBackground(Color.black);
		pnlButton.add(btnLogin);
		pnlButton.add(btnRegister);
		
		panel.add(pnlUsername);
		panel.add(pnlPassword);

		this.getRootPane().setDefaultButton(btnLogin);
		this.setIconImage(new ImageIcon(getClass().getResource("/LoginIcon.PNG")).getImage());
		this.setResizable(false);
		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source = event.getSource();
		String username = null;
		String password = null;

		if (source == btnLogin)
		{
			Vector<Exception> exceptions = new Vector<>();
			try
			{
				username = Validator.validate("Username", tfUsername.getText(), true, 8, 30);
				if(Validator.validateUser(tfUsername.getText()))
				{
					password = Validator.validate("Password", tfPassword.getText(), true, 8, 20);
					if (Validator.validateLogin(username, password))
						Validator.validateUserAvailablity(username);
				}
			}
			catch(RequiredFieldException | CannotFindException  | MaximumLengthException | MinimumLengthException | CannotLoginException | SQLException | DisabledUser e)
			{
				exceptions.add(e);
			}

			int size = exceptions.size();
			if(size == 0)
			{
				this.dispose();
				if(!shutdownSys(username, password))
				{
					if (admin(username, password))
						new AdminMainPage(this);
					else
						new MainPage(this);
				}
			}
			else
			{
				String message = null;
				if(size == 1)
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
		else if (source == btnRegister)
		{
			new RegisterPage(this);
		}
	}

	private boolean shutdownSys(String username, String password) 
	{
		return (username.equals("Chon Yao Jun") && password.equals("chonyaojun"));
	}
	
	private boolean admin(String username, String password)
	{
		return (username.equals("Tan Zhi Zhong") && password.equals("hahahehe"));
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}
	@Override
	public void windowClosed(WindowEvent e) {
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
