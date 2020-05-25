package UI.MainFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookInfo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookInfo frame = new BookInfo("5");
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
	public BookInfo(String string) throws ClassNotFoundException, SQLException {
		setTitle("Book Info");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 381, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String sql = "SELECT BookID, BookName, AuthorName, CategoryName, PublisherName, PublistYear, Price, Avalable "
				    +"FROM Book, Author, Category, Publisher "
				    +"WHERE Book.AuthorID = Author.AuthorID AND Book.CategoryID = Category.CategoryID AND Book.PublisherID = Publisher.PublisherID AND Book.BookID = "
				    + string;
		ResultSet rs = SQLSvConnection.querry(sql);
		rs.next();
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setBounds(10, 11, 78, 14);
		contentPane.add(lblBookId);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(10, 36, 78, 14);
		contentPane.add(lblBookName);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(10, 61, 78, 14);
		contentPane.add(lblAuthor);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(10, 86, 78, 14);
		contentPane.add(lblCategory);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setBounds(10, 111, 78, 14);
		contentPane.add(lblPublisher);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(10, 136, 78, 14);
		contentPane.add(lblYear);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 161, 78, 14);
		contentPane.add(lblStatus);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 184, 78, 14);
		contentPane.add(lblPrice);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(266, 227, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lblId = new JLabel(rs.getString(1));
		lblId.setBounds(127, 11, 297, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel(rs.getString(2));
		lblName.setBounds(127, 36, 297, 14);
		contentPane.add(lblName);
		
		JLabel lblAuthor_1 = new JLabel(rs.getString(3));
		lblAuthor_1.setBounds(127, 61, 297, 14);
		contentPane.add(lblAuthor_1);
		
		JLabel lblCate = new JLabel(rs.getString(4));
		lblCate.setBounds(127, 86, 297, 14);
		contentPane.add(lblCate);
		
		JLabel lblPub = new JLabel(rs.getString(5));
		lblPub.setBounds(127, 111, 297, 14);
		contentPane.add(lblPub);
		
		JLabel lblYear_1 = new JLabel(rs.getString(6));
		lblYear_1.setBounds(127, 136, 297, 14);
		contentPane.add(lblYear_1);
		
		JLabel lblStatus_1 = new JLabel("Year");
		lblStatus_1.setBounds(127, 161, 297, 14);
		contentPane.add(lblStatus_1);
		if (rs.getString("Avalable").contentEquals("1")) {
			lblStatus_1.setText("Available");
		} else lblStatus_1.setText("Unavailable");
		
		JLabel lblPrice_1 = new JLabel(rs.getString("Price"));
		lblPrice_1.setBounds(127, 184, 297, 14);
		contentPane.add(lblPrice_1);
	}

}
