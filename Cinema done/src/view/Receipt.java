package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.Admin;
import model.BookingDetail;
import javax.swing.JButton;

public class Receipt extends JDialog implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private BookingDetail bod;
	private JTextArea textArea;
	private JButton btnPrint;
	public static boolean finish;

	public Receipt(BookingDetail bod, ChooseSeating frame) throws SQLException, ParseException, PrinterException 
	{
		super(frame, "Tiang Cinema", true);
		this.bod = bod;
		setIconImage(new ImageIcon(getClass().getResource("/receipt.png")).getImage());
		setBounds(100, 100, 500, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.black);
		addWindowListener(this);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.black);
			contentPanel.add(panel, BorderLayout.WEST);
			{
				JLabel label = new JLabel("");
				Image myImage = new ImageIcon(getClass().getResource("/"+ bod.getMvName() +".jpg")).getImage();
				Image img2 = myImage.getScaledInstance(150, 206, Image.SCALE_SMOOTH);
				ImageIcon i = new ImageIcon(img2);
				label.setIcon(i);
				panel.add(label);
			}
		}
		{
			textArea = new JTextArea();
			textArea.setFont(new Font("Courier New", Font.PLAIN, 20));
			textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
			textArea.setColumns(20);
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			contentPanel.add(textArea);
			textArea.setText("Movie:	" + bod.getMvName());
			DecimalFormat df = new DecimalFormat(".00");
			textArea.setText(textArea.getText() + "\nRM:	" + df.format(bod.getPrice() * bod.getSeats().size()));
			textArea.setText(textArea.getText() + "\nTime:	" + bod.getDate()+ " " + bod.getTime().substring(1, 5));
			textArea.setText(textArea.getText() + "\nSeats:	\n\n");
			int seatCount = 0;
			for(String s: this.bod.getSeats())
			{
				textArea.setText(textArea.getText() + s + " ");
				if((seatCount+1)%6 == 0)
					textArea.setText(textArea.getText() + "\n");
				seatCount++;
			}
			textArea.setText(textArea.getText() + "\n");
			for(int i = 0; i < 25; i++)
				textArea.setText(textArea.getText() + "=");
		}
		{
			JPanel panelSouth = new JPanel();
			contentPanel.add(panelSouth, BorderLayout.SOUTH);
			panelSouth.setBackground(Color.black);
			btnPrint = new JButton("Print");
			btnPrint.setFont(new Font("Strika Text", Font.PLAIN, 20));
			btnPrint.setBackground(new Color(102,51,0));
			btnPrint.setForeground(new Color(222,212,87));
			btnPrint.setFocusPainted(false);
			btnPrint.addActionListener(this);	
			btnPrint.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent evt) {
			        btnPrint.setBackground(new Color(204,204,0));
			        btnPrint.setForeground(Color.black);
			    }
			    public void mouseExited(MouseEvent evt) {
			        btnPrint.setBackground(new Color(102,51,0));
			        btnPrint.setForeground(new Color(222,212,87));
			    }
			});
			panelSouth.add(btnPrint);
		}
		uptDB();
	}
	private boolean uptDB() throws SQLException, ParseException 
	{
		for(int i = 0; i < this.bod.getSeats().size(); i++)
		{	
			int upt = Admin.linkDatabaseUpdate("Insert into seats (row, `Column`, time, moviename) "
					+ "values (substring('"+bod.getSeats().get(i).toString()+"',1 ,1),"
					+ "substring('"+ bod.getSeats().get(i).toString() +"', 2, 3),"
					+ "'"+ bod.getDate() + " " + bod.getTime() +"',"
					+ "'"+ bod.getMvName() +"') ");
			if(upt <= 0) 
			{
				JOptionPane.showMessageDialog(this, "Booking failed");
				return true;
			}
		}
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try {
			textArea.print();
		} 
		catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		finish = true;
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}

