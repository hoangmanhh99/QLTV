package UI.UpdateFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateBook extends JFrame {

	private JPanel contentPane;
	private JTextField bookid;
	private JTextField bookname;
	private JTextField authid;
	private JTextField cateid;
	private JTextField pubid;
	private JTextField year;
	private JTextField price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBook frame = new UpdateBook();
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
	public UpdateBook() {
		setTitle("Update Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 506, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookid = new JLabel("BookID ");
		lblBookid.setBounds(10, 11, 83, 14);
		contentPane.add(lblBookid);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(10, 36, 83, 14);
		contentPane.add(lblBookName);
		
		JLabel lblAuthorId = new JLabel("Author ID");
		lblAuthorId.setBounds(10, 61, 83, 14);
		contentPane.add(lblAuthorId);
		
		JLabel lblCategoryid = new JLabel("CategoryID");
		lblCategoryid.setBounds(10, 86, 83, 14);
		contentPane.add(lblCategoryid);
		
		JLabel lblPublisherid = new JLabel("PublisherID");
		lblPublisherid.setBounds(10, 111, 83, 14);
		contentPane.add(lblPublisherid);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(10, 136, 83, 14);
		contentPane.add(lblYear);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 161, 83, 14);
		contentPane.add(lblPrice);
		
		bookid = new JTextField();
		bookid.setBounds(103, 8, 377, 20);
		contentPane.add(bookid);
		bookid.setColumns(10);
		
		bookname = new JTextField();
		bookname.setBounds(103, 33, 377, 20);
		contentPane.add(bookname);
		bookname.setColumns(10);
		
		authid = new JTextField();
		authid.setBounds(103, 58, 377, 20);
		contentPane.add(authid);
		authid.setColumns(10);
		
		cateid = new JTextField();
		cateid.setBounds(103, 83, 377, 20);
		contentPane.add(cateid);
		cateid.setColumns(10);
		
		pubid = new JTextField();
		pubid.setBounds(103, 108, 377, 20);
		contentPane.add(pubid);
		pubid.setColumns(10);
		
		year = new JTextField();
		year.setBounds(103, 133, 377, 20);
		contentPane.add(year);
		year.setColumns(10);
		
		price = new JTextField();
		price.setBounds(103, 158, 377, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!check(bookid.getText())) {
					JOptionPane.showMessageDialog(null, "Insert Book ID!");
					return;
				}
				
				Connection con;
				try {
					
					con = SQLSvConnection.Connect();
					Statement stm = con.createStatement();
					
					if (check(bookname.getText())) {
						stm.executeUpdate("UPDATE Book SET BookName = '" + bookname.getText() + "' WHERE BookID = " + bookid.getText() );
					}
					
					if (check(authid.getText())) {
						stm.executeUpdate("UPDATE Book SET AuthorID = " + authid.getText() + " WHERE BookID = " + bookid.getText() );
					}
					
					if (check(cateid.getText())) {
						stm.executeUpdate("UPDATE Book SET CategoryID = " + cateid.getText() + " WHERE BookID = " + bookid.getText() );
					}
					
					if (check(pubid.getText())) {
						stm.executeUpdate("UPDATE Book SET PublisherID = " + pubid.getText() + " WHERE BookID = " + bookid.getText() );
					}
					
					if (check(year.getText())) {
						stm.executeUpdate("UPDATE Book SET PublistYear = " + year.getText() + " WHERE BookID = " + bookid.getText() );
					}
					
					if (check(price.getText())) {
						stm.executeUpdate("UPDATE Book SET Price = " + price.getText() + " WHERE BookID = " + bookid.getText() );
					}
					
					JOptionPane.showMessageDialog(null, "Update success!");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(10, 195, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(391, 195, 89, 23);
		contentPane.add(btnCancel);
	}
	public static boolean check(String str) {
        if(str != null && !str.isEmpty())
            return true;
        return false;
    }
}
