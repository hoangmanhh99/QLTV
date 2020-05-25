package UI.MainFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.*;
import UI.IOclasses.Export;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Statistic extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistic frame = new Statistic();
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
	public Statistic() {
		setTitle("Statistic");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 994, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAuthorBooks = new JButton("Author - Books");
		btnAuthorBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT AuthorName, count(BookID) as Books \r\n" + 
							"FROM Author, Book\r\n" + 
							"WHERE Book.AuthorID = Author.AuthorID\r\n" + 
							"GROUP BY AuthorName");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAuthorBooks.setBounds(10, 11, 167, 23);
		contentPane.add(btnAuthorBooks);
		
		JButton btnCategoryBooks = new JButton("Category - Books");
		btnCategoryBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT CategoryName, count(BookID) as Books \r\n" + 
							"FROM Category, Book\r\n" + 
							"WHERE Book.CategoryID = Category.CategoryID\r\n" + 
							"GROUP BY CategoryName");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCategoryBooks.setBounds(10, 45, 167, 23);
		contentPane.add(btnCategoryBooks);
		
		JButton btnPublisherBooks = new JButton("Publisher - Books");
		btnPublisherBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT PublisherName, count(BookID) as Books \r\n" + 
							"FROM Publisher, Book\r\n" + 
							"WHERE Book.PublisherID = Publisher.PublisherID\r\n" + 
							"GROUP BY PublisherName");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPublisherBooks.setBounds(10, 79, 167, 23);
		contentPane.add(btnPublisherBooks);
		
		JButton btnBooksRent = new JButton("Books rent");
		btnBooksRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT BookID, BookName FROM Book WHERE Avalable = 0");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBooksRent.setBounds(10, 113, 167, 23);
		contentPane.add(btnBooksRent);
		
		JButton btnBookAvailable = new JButton("Books available");
		btnBookAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT BookID, BookName FROM Book WHERE Avalable = 1");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBookAvailable.setBounds(10, 147, 167, 23);
		contentPane.add(btnBookAvailable);
		
		JButton btnReaderRent = new JButton("Reader - Rent Sessions");
		btnReaderRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT ReaderID, ReaderName, count(BorrowID) as [Rent Sessions] FROM Reader, BorrowDetail WHERE Reader.CardID = BorrowDetail.CardID GROUP BY ReaderID, ReaderName");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnReaderRent.setBounds(10, 181, 167, 23);
		contentPane.add(btnReaderRent);
		
		JButton btnBookRent = new JButton("Book - Rent time");
		btnBookRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT Book.BookID, BookName, count(BorrowID) as [Rent Times] \r\n" + 
							"FROM Book, BorrowBooks\r\n" + 
							"WHERE Book.BookID = BorrowBooks.BookID\r\n" + 
							"GROUP BY Book.BookID, BookName");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBookRent.setBounds(10, 215, 167, 23);
		contentPane.add(btnBookRent);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 11, 781, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(879, 266, 89, 23);
		contentPane.add(btnClose);
		
		JButton btnExportToExcel = new JButton("Export to Excel");
		btnExportToExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Export.Export(table.getModel());
			}
		});
		btnExportToExcel.setBounds(10, 266, 130, 23);
		contentPane.add(btnExportToExcel);
	}
}
