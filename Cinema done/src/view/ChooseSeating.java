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
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Admin;
import model.BookingDetail;

public class ChooseSeating extends JDialog implements ActionListener, WindowListener 
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panelSeating = new JPanel();
	private Vector<JButton> vectorButton = new Vector<JButton>();
	private Vector<String> seats = new Vector<String>();
	private BookingDetail bod;
	private JButton btnCheckOut;
	private JLabel dpPrice;
	JButton btnCancel;
	private final JPanel panel = new JPanel();

	public ChooseSeating(MainPage frame, BookingDetail bod)
	{
		super(frame, "Tiang Cinema", true);
		this.bod = bod;
		
		setIconImage(new ImageIcon(getClass().getResource("/seat.png")).getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 796, 517);
		setSize(1600, 800);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.black);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		addWindowListener(this);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblChooseYourSeating = new JLabel("Choose your seating");
			lblChooseYourSeating.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblChooseYourSeating.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblChooseYourSeating, BorderLayout.NORTH);
		}
		{
			contentPanel.add(panelSeating, BorderLayout.CENTER);
			panelSeating.setLayout(new GridLayout(12, 20, 0, 0));
			{
				String[] rowName = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
				for(int i = 0, j = 0; i < 240; i++) 
				{
					String name = rowName[j]+ (i%20 + 1);
					vectorButton.add(Initialze(new JButton(), name));
					if( (i+1) % 20  == 0)
						j++;
				}
				chgBtns(vectorButton);
			}
		}

		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setBackground(Color.black);
		btnCheckOut = new JButton("Check out");
		btnCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCheckOut.setBackground(new Color(102,51,0));
		btnCheckOut.setForeground(new Color(222,212,87));
		btnCheckOut.setFocusPainted(false);
		btnCheckOut.addActionListener(this);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnCheckOut.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnCheckOut.setBackground(new Color(204,204,0));
		        btnCheckOut.setForeground(Color.black);
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnCheckOut.setBackground(new Color(102,51,0));
		        btnCheckOut.setForeground(new Color(222,212,87));
		    }
		});
		buttonPane.add(btnCheckOut);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		buttonPane.add(btnCancel);
		
		buttonPane.add(panel);
		JLabel space = new JLabel();
		JLabel tolPrice = new JLabel("Total Price:");
		JLabel RM = new JLabel();
		RM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		RM.setHorizontalAlignment(SwingConstants.RIGHT);
		RM.setText("RM	");
		RM.setForeground(new Color(222,212,87));
		tolPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tolPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dpPrice = new JLabel("0.00");
		dpPrice.setHorizontalAlignment(SwingConstants.CENTER);
		dpPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dpPrice.setForeground(new Color(222,212,87));
		panel.setLayout(new GridLayout(1, 3, 0, 0));
		panel.setBackground(Color.black);
		panel.add(space);
		panel.add(tolPrice);
		panel.add(RM);
		panel.add(dpPrice);

		this.setVisible(true);
	}

	private Vector<JButton> chgBtns(Vector<JButton> btns) 
	{
		ResultSet rs = Admin.linkDatabase("Select row, `Column` from seats where time = '"+ bod.getDate() +" "+ bod.getTime()+"' and moviename = '"+ bod.getMvName()+"'"); 
		Vector<String> sseats = new Vector<String>();
		try {
			while(rs.next())
			{
				String seat = new String();
				seat = rs.getString(1);
				seat += rs.getString(2);
				sseats.add(seat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String ss:  sseats) 
		{
			for(JButton btn: btns)
			{
				if(ss.equals(btn.getName()))
				{
					btn.setBackground(Color.RED);
					btn.setEnabled(false);
				}
			}
		}
		return btns;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == btnCheckOut)
		{
			if(seats.size() > 0)
			{
				int opt = JOptionPane.showConfirmDialog(this, "Are you sure to check out", "Tiang Cinema", JOptionPane.YES_OPTION);
				if(opt == 0) {
					bod.setSeats(seats);
					Receipt dialog;
					try {
						dialog = new Receipt(bod, this);
						dialog.setVisible(true);
						dispose();
					} catch (SQLException | ParseException | PrinterException e1) {
						e1.printStackTrace();
					}
				}
			}
			else 
				JOptionPane.showMessageDialog(this, "Please choose a seat(s) first", "Tiang Cinema", JOptionPane.WARNING_MESSAGE);
		}
		else if(src == btnCancel)
			dispose();
		else 
		{
			DecimalFormat df = new DecimalFormat("0.00");
			JButton btn = (JButton) e.getSource();
			if(btn.getBackground().getRGB() == new Color(0,255,127).getRGB())
			{
				removeVector(seats, btn.getName());
				btn.setBackground(Color.white);
				dpPrice.setText(df.format(seats.size() * bod.getPrice()));
			}
			else if(btn.getBackground() == Color.white)
			{
				seats.add(btn.getName());
				btn.setBackground(new Color(0,255,127));
				dpPrice.setText(df.format(seats.size() * bod.getPrice()));
			}
		}
	}

	private JButton Initialze(JButton button, String name)
	{
		button.setText(name);
		button.setName(name);
		button.setBackground(Color.white);
		button.addActionListener(this);
		panelSeating.add(button);
		return button;
	}

	private void removeVector(Vector<String> seats, String name) {
		for(int i = 0; i < seats.size(); i++) {
			if(seats.get(i).equals(name))
				seats.remove(i);
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		if(Receipt.finish)
			dispose();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}
