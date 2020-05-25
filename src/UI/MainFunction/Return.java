package UI.MainFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import UI.IOclasses.*;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Return extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Return frame = new Return();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Return() {
		setTitle("Return Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 578, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSessionId = new JLabel("Session ID");
		lblSessionId.setBounds(10, 11, 87, 14);
		contentPane.add(lblSessionId);
		
		JLabel lblName = new JLabel("");
		lblName.setBounds(135, 36, 218, 14);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(82, 8, 318, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(135, 57, 218, 14);
		contentPane.add(lblStatus);
		
		int late[] = {0};

		JButton btnFind = new JButton("Find");
		btnFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT Book.BookID, BookName, BorrowBooks.ReturnDate,\r\n" + 
							"CASE ReturnStat\r\n" + 
							"	WHEN  0 THEN 'Not returned yet'\r\n" + 
							"	WHEN  1 THEN 'Returned'\r\n" + 
							"END as Status\r\n" + 
							"FROM BorrowBooks, Book\r\n" + 
							"WHERE Book.BookID = BorrowBooks.BookID AND BorrowID = " + textField.getText());
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs = SQLSvConnection.querry("SELECT Book.BookID, BookName, BorrowBooks.ReturnDate,\r\n" + 
							"CASE ReturnStat\r\n" + 
							"	WHEN  0 THEN 'Not returned yet'\r\n" + 
							"	WHEN  1 THEN 'Returned'\r\n" + 
							"END as Status\r\n" + 
							"FROM BorrowBooks, Book\r\n" + 
							"WHERE Book.BookID = BorrowBooks.BookID AND BorrowID = " + textField.getText());
					
					while (rs.next()) {
						SimpleDateFormat sdfomat = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date d1;
						try {
							d1 = sdfomat.parse(cutDate(rs.getString(3)));
							if ( (d1.compareTo( sdfomat.parse(java.time.LocalDate.now().toString() )) < 0) && rs.getString(4).contentEquals("Not returned yet") ) {
								late[0] = late[0]+1;
							}
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
					}
					
					ResultSet rs3 = SQLSvConnection.querry("SELECT ReaderName FROM Reader, BorrowDetail, Card WHERE BorrowDetail.CardID = Card.CardID AND Reader.CardID = Card.CardID AND BorrowID = " + textField.getText());
					rs3.next();
					lblName.setText(rs3.getString(1));
					
					ResultSet rs4 = SQLSvConnection.querry("SELECT ReturnStat FROM BorrowDetail WHERE BorrowID = " + textField.getText());
					rs4.next();
					if (rs4.getInt(1)==1) {
						lblStatus.setText("Fully returned");
					} else lblStatus.setText("Not fully returned yet");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFind.setBounds(410, 7, 142, 23);
		contentPane.add(btnFind);
		
		JLabel lblBooksBorrowedIn = new JLabel("Books borrowed");
		lblBooksBorrowedIn.setBounds(10, 82, 119, 14);
		contentPane.add(lblBooksBorrowedIn);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel model = new DefaultTableModel();
				model = table.getModel();
				String bookID = model.getValueAt(table.getSelectedRow(), 0).toString();
				Connection con;
				try {
					con = SQLSvConnection.Connect();
					Statement stm = con.createStatement();
					String sql = "UPDATE Book SET Avalable = 1 WHERE BookID = " + bookID;
					stm.executeUpdate(sql);
					sql = "UPDATE BorrowBooks SET ReturnStat = 1 FROM Book, BorrowBooks WHERE Book.BookID = BorrowBooks.BookID AND Book.BookID = " + bookID + " AND BorrowBooks.BorrowID = " + textField.getText();
					stm.executeUpdate(sql);
					sql = "SELECT ReturnStat FROM BorrowBooks WHERE ReturnStat = 0 AND BorrowID = " + textField.getText();
					ResultSet rs2 = stm.executeQuery(sql);
					int count = 0;
					while (rs2.next()) {
						count++;
					}
					if (count==0) {
						stm.executeUpdate("UPDATE BorrowDetail SET ReturnStat = 1 WHERE BorrowID = " + textField.getText());
					}
					ResultSet rs = SQLSvConnection.querry("SELECT Book.BookID, BookName, BorrowBooks.ReturnDate, \r\n" + 
							"CASE ReturnStat\r\n" + 
							"	WHEN  0 THEN 'Not returned yet'\r\n" + 
							"	WHEN  1 THEN 'Returned'\r\n" + 
							"END as Status\r\n" + 
							"FROM BorrowBooks, Book\r\n" + 
							"WHERE Book.BookID = BorrowBooks.BookID AND BorrowID = " + textField.getText());
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					ResultSet rs4 = SQLSvConnection.querry("SELECT ReturnStat FROM BorrowDetail WHERE BorrowID = " + textField.getText());
					rs4.next();
					if (rs4.getInt(1)==1) {
						lblStatus.setText("Fully returned");
					} else lblStatus.setText("Not fully returned yet");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnReturn.setBounds(10, 425, 106, 23);
		contentPane.add(btnReturn);
		
		JButton btnReturnAll = new JButton("Return All");
		btnReturnAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection con;
				try {
					con = SQLSvConnection.Connect();
					Statement stm = con.createStatement();
					String sql = "UPDATE BorrowDetail SET ReturnStat = 1 WHERE BorrowID = " + textField.getText() ;
					stm.executeUpdate(sql);
					sql = "UPDATE BorrowBooks SET ReturnStat = 1 WHERE BorrowID = " + textField.getText();
					stm.executeUpdate(sql);
					sql = "UPDATE Book SET Avalable = 1 FROM Book, BorrowBooks WHERE BorrowBooks.BookID = Book.BookID AND BorrowID = " + textField.getText();
					stm.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Return Success!");
					ResultSet rs = SQLSvConnection.querry("SELECT Book.BookID, BookName, BorrowBooks.ReturnDate, \r\n" + 
							"CASE ReturnStat\r\n" + 
							"	WHEN  0 THEN 'Not returned yet'\r\n" + 
							"	WHEN  1 THEN 'Returned'\r\n" + 
							"END as Status\r\n" + 
							"FROM BorrowBooks, Book\r\n" + 
							"WHERE Book.BookID = BorrowBooks.BookID AND BorrowID = " + textField.getText());
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					ResultSet rs4 = SQLSvConnection.querry("SELECT ReturnStat FROM BorrowDetail WHERE BorrowID = " + textField.getText());
					rs4.next();
					if (rs4.getInt(1)==1) {
						lblStatus.setText("Fully returned");
					} else lblStatus.setText("Not fully returned yet");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnReturnAll.setBounds(240, 425, 113, 23);
		contentPane.add(btnReturnAll);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(446, 425, 106, 23);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 107, 542, 307);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblReturnStatus = new JLabel("Return Status");
		lblReturnStatus.setBounds(10, 57, 89, 14);
		contentPane.add(lblReturnStatus);
		
		JLabel lblReaderName = new JLabel("Reader name");
		lblReaderName.setBounds(10, 36, 95, 14);
		contentPane.add(lblReaderName);
		
		JLabel lblLate = new JLabel(Integer.toString(late[0])+ " books");
		lblLate.setBounds(417, 82, 48, 14);
		contentPane.add(lblLate);
		
		
		
	}
	
	public static String cutDate(String string) 
	{ 
		return string.substring(0,string.indexOf(' '));
	} 
}
