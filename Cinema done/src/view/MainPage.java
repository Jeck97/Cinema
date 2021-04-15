package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.Admin;
import controller.validator.DateException;
import controller.validator.InvalidNumberException;
import controller.validator.MaximumLengthException;
import controller.validator.MaximumNumberException;
import controller.validator.MinDateException;
import controller.validator.MinimumLengthException;
import controller.validator.MinimumNumberException;
import controller.validator.RequiredFieldException;
import controller.validator.RequiredSelectedItem;
import controller.validator.Validator;
import model.BookingDetail;
import model.TableModelCustomer;

public class MainPage extends JDialog implements ActionListener, MouseListener, WindowListener
{
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JTable movieTable;
	private JButton btnFilter;
	private JButton btnReset;
	private JButton btnSelect;
	private JButton btnCancel;
	private JButton btnTime;
	private JTextField txtFilter;
	private JTextArea textArea; 
	private JComboBox<String> cmbFilter;
	private JLabel lblMoviePicture;
	private JDateChooser dac;
	private BookingDetail bod;

	private String[] category = {"NAME", "TYPE", "PRICE"};

	public MainPage(LoginPage frame)
	{
		super(frame, "Tiang Cinema", true);

		bod = new BookingDetail();

		JPanel panelHeader = new JPanel();
		JPanel panelFilter = new JPanel();
		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		JPanel buttonPane = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setHgap(80);

		JLabel lblHeaderTitle = new JLabel("Welcome to Golden Tiang Cinema");
		lblHeaderTitle.setForeground(new Color(222,212,87));
		lblHeaderTitle.setFont(new Font("Kristen ITC", Font.BOLD, 24));
		lblHeaderTitle.setHorizontalAlignment(SwingConstants.CENTER);

		cmbFilter = new JComboBox<String>(category);
		cmbFilter.setBackground(Color.black);
		cmbFilter.setForeground(new Color(222,212,87));
		cmbFilter.setFocusable(false);
		txtFilter = new JTextField();
		txtFilter.setColumns(40);
		btnFilter = new JButton("Filter");
		btnFilter.setToolTipText("Filter");
		btnFilter.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		btnFilter.setBackground(new Color(102,51,0));
		btnFilter.setForeground(new Color(222,212,87));
		btnFilter.setFocusPainted(false);
		btnFilter.addActionListener(this);
		btnFilter.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnFilter.setBackground(new Color(204,204,0));
				btnFilter.setForeground(Color.black);
			}
			public void mouseExited(MouseEvent evt) {
				btnFilter.setBackground(new Color(102,51,0));
				btnFilter.setForeground(new Color(222,212,87));
			}
		});
		btnFilter.addActionListener(this);	
		btnReset = new JButton("Reset");
		btnReset.setToolTipText("Reset the table");
		btnReset.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		btnReset.setBackground(new Color(102,51,0));
		btnReset.setForeground(new Color(222,212,87));
		btnReset.setFocusPainted(false);
		btnReset.addActionListener(this);
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
		btnReset.addActionListener(this);	

		movieTable = new JTable();
		movieTable.getTableHeader().setBackground(new Color(222,212,87));
		movieTable.setBackground(Color.yellow);
		movieTable.setForeground(Color.black);
		movieTable.setRowHeight(40);
		movieTable.setModel(new TableModelCustomer(false, null, null));
		movieTable.addMouseListener(this);	

		lblMoviePicture = new JLabel();
		lblMoviePicture.setBounds(0, 11, 150, 206);
		lblMoviePicture.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMoviePicture.setHorizontalAlignment(SwingConstants.LEFT);	
		lblMoviePicture.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Frozen 2.jpg")).getImage().getScaledInstance(150, 206, Image.SCALE_SMOOTH)));

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(162, 11, 512, 206);
		textArea.setBackground(Color.yellow);
		textArea.setFont(new Font("Century Gothic",Font.BOLD,20));
		textArea.setColumns(50);
		textArea.setRows(10);

		dac = new JDateChooser();
		dac.setBounds(686, 11, 265, 30);
		dac.addMouseListener(this);
		dac.getComponent(1).setEnabled(false);
		dac.getComponent(1).setForeground(Color.black);
		dac.getComponent(1).setBackground(Color.black);

		btnTime = new JButton("Time");
		btnTime.setBounds(858, 52, 93, 29);
		btnTime.setBackground(new Color(102,51,0));
		btnTime.setForeground(new Color(222,212,87));
		btnTime.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnTime.addActionListener(this);
		btnTime.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnTime.setBackground(new Color(204,204,0));
				btnTime.setForeground(Color.black);
			}
			public void mouseExited(MouseEvent evt) {
				btnTime.setBackground(new Color(102,51,0));
				btnTime.setForeground(new Color(222,212,87));
			}
		});

		btnSelect = new JButton("Select");
		btnSelect.setBackground(new Color(102,51,0));
		btnSelect.setForeground(new Color(222,212,87));
		btnSelect.setActionCommand("OK");
		btnSelect.setEnabled(false);
		btnSelect.setFocusPainted(false);
		btnSelect.addActionListener(this);
		btnSelect.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnSelect.setBackground(new Color(204,204,0));
				btnSelect.setForeground(Color.black);
			}
			public void mouseExited(MouseEvent evt) {
				btnSelect.setBackground(new Color(102,51,0));
				btnSelect.setForeground(new Color(222,212,87));
			}
		});
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(102,51,0));
		btnCancel.setForeground(new Color(222,212,87));
		btnCancel.setFocusPainted(false);
		btnCancel.setActionCommand("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnCancel.setBackground(new Color(204,204,0));
				btnCancel.setForeground(Color.black);
			}
			public void mouseExited(MouseEvent evt) {
				btnCancel.setBackground(new Color(102,51,0));
				btnCancel.setForeground(new Color(222,212,87));
			}
		});

		scrollPane.getViewport().setBackground(Color.black);
		scrollPane.setViewportView(movieTable);

		panelFilter.add(cmbFilter);
		panelFilter.add(txtFilter);
		panelFilter.add(btnFilter);
		panelFilter.add(btnReset);
		panelFilter.setBackground(Color.black);
		panelHeader.setLayout(new GridLayout(2, 0, 0, 0));
		panelHeader.setBackground(Color.black);
		panelHeader.add(lblHeaderTitle);
		panelHeader.add(panelFilter);
		panel_1.setBackground(Color.black);
		panel_1.setLayout(null);
		panel_1.add(lblMoviePicture);
		panel_1.add(textArea);
		panel_1.add(dac);
		panel_1.add(btnTime);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(scrollPane);
		panel.add(panel_1);		
		buttonPane.setBackground(Color.black);
		buttonPane.setLayout(fl_buttonPane);
		buttonPane.add(btnSelect);
		buttonPane.add(btnCancel);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.black);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(panelHeader, BorderLayout.NORTH);
		contentPanel.add(panel, BorderLayout.CENTER);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(btnSelect);
		this.setIconImage(new ImageIcon(getClass().getResource("/Movie.png")).getImage());
		this.setBounds(100, 100, 979, 620);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(this);

		showPosterAndDescription();
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object src = event.getSource();

		if (src == btnFilter)
		{
			String filterText = null;
			String currentCategory = cmbFilter.getItemAt(cmbFilter.getSelectedIndex()).toString();
			try 
			{
				if (currentCategory.equals("PRICE"))
					filterText = String.valueOf(Validator.validate("Filter key word", txtFilter.getText(), true, true, true, 0.0, 50.0));
				else
					filterText = Validator.validate("Filter key word", txtFilter.getText(), true, 0, 50);

				movieTable.setModel(new TableModelCustomer(true, currentCategory, filterText));

			}
			catch (RequiredFieldException | InvalidNumberException | MinimumNumberException | MaximumNumberException | MaximumLengthException | MinimumLengthException e) 
			{
				JOptionPane.showMessageDialog(this, e.getMessage(), "Unsuccessful", JOptionPane.WARNING_MESSAGE);
			}

		}
		else if (src == btnReset)
			movieTable.setModel(new TableModelCustomer(false, null, null));
		else if(src == btnSelect) 
		{
			bod.setMvName(movieTable.getValueAt(movieTable.getSelectedRow(), 0).toString());
			bod.setPrice(Double.parseDouble(movieTable.getValueAt(movieTable.getSelectedRow(), 2).toString()));
			new ChooseSeating(this, bod);
		}
		else if(src == btnCancel)
			dispose();
		else if(src == btnTime)
		{
			Vector<Exception> exceptions = new Vector<Exception>();
			try 
			{
				if (Validator.validateSelectedItem("Selected movie", movieTable.getSelectedRow()))
					Validator.validateSelDate(dac.getDate());
			} 
			catch (RequiredSelectedItem | DateException | MinDateException e) 
			{
				exceptions.add(e);
			}

			int size = exceptions.size();
			if(size == 0)
			{
				String msg = "Please choose a time";
				Vector<Object> vecRB = new Vector<Object>();
				vecRB.add(msg);
				ButtonGroup bg = new ButtonGroup();
				String mvN = movieTable.getValueAt(movieTable.getSelectedRow(), 0).toString();
				Vector<String> timeTable = Admin.getTimeTableOfMovie(mvN);
				for (String date : timeTable)
				{
					if(date.contains(new SimpleDateFormat("yyyy-MM-dd").format(dac.getDate())) && Admin.countSeatAtDate(mvN, new SimpleDateFormat("yyyy-MM-dd").format(dac.getDate())+" "+ date.toString().substring(11)) <= 240)
					{
						if(date.equals(new SimpleDateFormat("yyyy-MM-dd").format(dac.getDate())))
							if(Integer.parseInt(new SimpleDateFormat("HH").format(dac.getDate())) > Integer.parseInt(date.substring(11,13)))
							{
								bod.setDate(new SimpleDateFormat("yyyy-MM-dd").format(dac.getDate()));
								JRadioButton rb = new JRadioButton();
								rb.setText(String.valueOf(date.toString().substring(11)));
								bg.add(rb);
								vecRB.add(rb);
							}
						bod.setDate(new SimpleDateFormat("yyyy-MM-dd").format(dac.getDate()));
						JRadioButton rb = new JRadioButton();
						rb.setText(String.valueOf(date.toString().substring(11)));
						bg.add(rb);
						vecRB.add(rb);
					}
				}
				JOptionPane.showConfirmDialog(this, vecRB.toArray(), "Showing Time", JOptionPane.YES_OPTION);
				for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) 
				{
					AbstractButton button = buttons.nextElement();
					if(button.isSelected()) 
					{
						bod.setTime(button.getText().toString());
						btnSelect.setEnabled(true);
					}
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

	}

	public void showPosterAndDescription() 
	{
		try
		{
			String movieName = movieTable.getValueAt(movieTable.getSelectedRow(), 0).toString();
			Image myImage = new ImageIcon(getClass().getResource("/"+movieName+".jpg")).getImage();
			Image img2 = myImage.getScaledInstance(150, 206, Image.SCALE_SMOOTH);
			ImageIcon i = new ImageIcon(img2);
			lblMoviePicture.setIcon(i);
			textArea.setText(Admin.getMovieDescription(movieName));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
		}
		catch (Exception e)
		{
			lblMoviePicture.setIcon(null);
			lblMoviePicture.setText("Image not found");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource() == movieTable) 
		{
			showPosterAndDescription();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	@Override
	public void windowActivated(WindowEvent e) 
	{
		if(Receipt.finish)
			dispose();
		Receipt.finish = false;
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
