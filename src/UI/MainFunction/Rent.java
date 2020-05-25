package UI.MainFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import UI.IOclasses.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rent extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rent frame = new Rent(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Rent(int userID) throws ClassNotFoundException, SQLException {
		setTitle("Rent Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRentSessionId = new JLabel("Rent Session ID:");
		lblRentSessionId.setBounds(10, 11, 116, 14);
		contentPane.add(lblRentSessionId);
		
		JLabel lblSessionid = new JLabel("rentsession");
		lblSessionid.setBounds(136, 11, 81, 14);
		contentPane.add(lblSessionid);
		
		ResultSet rs = SQLSvConnection.querry("SELECT BorrowID FROM BorrowDetail");
		int[] sessionID = {1};
		while (rs.next()) {
			sessionID[0]++;
		}
		lblSessionid.setText(Integer.toString(sessionID[0]));
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 36, 81, 14);
		contentPane.add(lblDate);
		
		JLabel lblTime = new JLabel("");
		lblTime.setBounds(136, 36, 116, 14);
		contentPane.add(lblTime);
		lblTime.setText(java.time.LocalDate.now().toString());
		
		JLabel lblBooksList = new JLabel("Book ID List");
		lblBooksList.setBounds(281, 11, 116, 14);
		contentPane.add(lblBooksList);
		
		JList list = new JList();
		list.setBounds(280, 36, 164, 301);
		contentPane.add(list);
		
		JLabel lblStaffInfo = new JLabel("Staff info");
		lblStaffInfo.setBounds(10, 61, 48, 14);
		contentPane.add(lblStaffInfo);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(43, 86, 48, 14);
		contentPane.add(lblName);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(43, 111, 48, 14);
		contentPane.add(lblId);
		
		JLabel lblStaffname = new JLabel("staffname");
		lblStaffname.setBounds(101, 86, 169, 14);
		contentPane.add(lblStaffname);
		ResultSet rs2 = SQLSvConnection.querry("SELECT StaffName, StaffID FROM Staff WHERE AccID = " + userID);
		rs2.next(); 
		lblStaffname.setText(rs2.getString(1));
		
		JLabel lblStaffid = new JLabel(Integer.toString(userID));
		lblStaffid.setBounds(101, 111, 81, 14);
		contentPane.add(lblStaffid);
		
		JLabel lblReaderInfo = new JLabel("Reader info");
		lblReaderInfo.setBounds(10, 136, 81, 14);
		contentPane.add(lblReaderInfo);
		
		JLabel lblId_1 = new JLabel("Card ID");
		lblId_1.setBounds(43, 161, 48, 14);
		contentPane.add(lblId_1);
		
		textField = new JTextField();
		textField.setBounds(101, 158, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(43, 186, 48, 14);
		contentPane.add(lblName_1);
		
		JLabel lblReadername = new JLabel("readername");
		lblReadername.setBounds(101, 186, 169, 14);
		contentPane.add(lblReadername);
		
		JButton btnShow = new JButton("Show");
		btnShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT ReaderName FROM Reader WHERE CardID = " + textField.getText());
					rs.next();
					lblReadername.setText(rs.getString(1));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShow.setBounds(101, 132, 96, 23);
		contentPane.add(btnShow);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 213, 259, 124);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRentBookDetail = new JLabel("Rent Book Detail");
		lblRentBookDetail.setBounds(10, 11, 152, 14);
		panel.add(lblRentBookDetail);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setBounds(10, 36, 81, 14);
		panel.add(lblBookId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(91, 33, 158, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setBounds(10, 61, 81, 14);
		panel.add(lblReturnDate);
		
		textField_2 = new JTextField();
		textField_2.setBounds(91, 58, 158, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCheckInfo = new JButton("Check Info");
		btnCheckInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					BookInfo frame1 = new BookInfo(textField_1.getText());
					frame1.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCheckInfo.setBounds(10, 86, 106, 23);
		panel.add(btnCheckInfo);
		
		String[] IDlist = new String[20];
		IDlist[0] = "Please select book";
		int[] index = {0};
		list.setListData(IDlist);
		list.setSelectedIndex(0);
		
		
		JButton btnRent = new JButton("Rent");
		btnRent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT Avalable FROM Book WHERE BookID = " + textField_1.getText());
					rs.next();
					if (rs.getInt(1)==0) {
						JOptionPane.showMessageDialog(null, "Book not available for rent!");
						return;
					}
					} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				IDlist[index[0]] = textField_1.getText() +", '"+ textField_2.getText();
				list.setListData(IDlist);
				list.setSelectedIndex(0);
				index[0] = index[0]+1;
			}
		});
		btnRent.setBounds(140, 86, 109, 23);
		panel.add(btnRent);
		
		JButton btnFinishSession = new JButton("Finish Session");
		btnFinishSession.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection con;
				try {
					con = SQLSvConnection.Connect();
					Statement stm = con.createStatement();
					String sql = "INSERT INTO BorrowDetail \r\n" + 
								 "VALUES ("+ sessionID[0] + ", " + textField.getText() + ", " + rs2.getString(2) + ", 0, '"+ java.time.LocalDate.now().toString() +"');";
					//JOptionPane.showMessageDialog(null, sql);
					stm.executeUpdate(sql);
					for (int i=0;i<index[0];i++) {
						stm.executeUpdate("INSERT INTO BorrowBooks VALUES (" + sessionID[0] +", " + IDlist[i] +"', 0);");
						stm.executeUpdate("UPDATE Book SET Avalable = 0 WHERE BookID = " + cutID(IDlist[i]));
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"Rent session succesfully updated to database!");
				ResultSet rs;
				try {
					rs = SQLSvConnection.querry("SELECT BorrowID, Price FROM BorrowDetail");
					int[] sessionID = {1};
					while (rs.next()) {
						sessionID[0]++;
					}
					lblSessionid.setText(Integer.toString(sessionID[0]));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFinishSession.setBounds(10, 382, 126, 23);
		contentPane.add(btnFinishSession);
		
		JButton btnExportBill = new JButton("Export Ticket");
		btnExportBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
try {
					
					FileOutputStream outStream = new FileOutputStream("E:\\DataProjectI\\Rent_Ticket_No_"+ Integer.toString(sessionID[0]) + ".docx");
					XWPFDocument doc = new XWPFDocument();
					XWPFParagraph paraTit = doc.createParagraph();
					paraTit.setAlignment(ParagraphAlignment.LEFT);
					XWPFRun paraTitRun = paraTit.createRun();
					paraTitRun.setBold(true);
					paraTitRun.setFontSize(16);
					paraTitRun.setText("================  LIBRARY RENT TICKET  =================");
					paraTitRun.addBreak();
					paraTitRun.addBreak();
					paraTitRun.setText("Rent Session ID: " + Integer.toString(sessionID[0]));
					paraTitRun.addBreak();
					paraTitRun.setText("Staff ID: " + Integer.toString(userID) + "  Staff Name: " + lblStaffname.getText());
					paraTitRun.addBreak();
					paraTitRun.setText("Reader ID: " + textField.getText() + "  Reader Name: " + lblReadername.getText());
					paraTitRun.addBreak();
					paraTitRun.setText("Book list(Name, ID, Return Date): ");
					paraTitRun.addBreak();
					paraTitRun.addBreak();
					for (int i = 0; i < index[0]; i++) {
						ResultSet rs = SQLSvConnection.querry("SELECT BookName FROM Book WHERE BookID = " + cutID(IDlist[i]));
						rs.next();
						paraTitRun.setText(rs.getString(1)+ ",  " + IDlist[i]);
						paraTitRun.addBreak();
					}
					doc.write(outStream);
					outStream.close();
					JOptionPane.showMessageDialog(null, "Success");
					try {
					     if (Desktop.isDesktopSupported()) {
					       Desktop.getDesktop().open(new File("E:\\DataProjectI\\Rent_Ticket_No_"+ Integer.toString(sessionID[0]) + ".docx"));
					     }
					   } catch (IOException ioe) {
					     ioe.printStackTrace();
					   }
					
					
					
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnExportBill.setBounds(166, 382, 126, 23);
		contentPane.add(btnExportBill);
		
		JButton btnDeleteBook = new JButton("Delete book");
		btnDeleteBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=0;
				for (i=list.getSelectedIndex();i<=index[0];i++) {
					IDlist[i]= IDlist[i+1];
				}
				IDlist[index[0]]=null;
				index[0]--;
				list.setListData(IDlist);
				list.setSelectedIndex(0);
			}
		});
		btnDeleteBook.setBounds(281, 348, 163, 23);
		contentPane.add(btnDeleteBook);
		
		JButton btnCancelSession = new JButton("Cancel session");
		btnCancelSession.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancelSession.setBounds(318, 382, 126, 23);
		contentPane.add(btnCancelSession);
		
		//==================================================================================================
		
	}
	
	public static String cutID(String string) 
	{ 
		return string.substring(0,string.indexOf(','));
	} 
}
