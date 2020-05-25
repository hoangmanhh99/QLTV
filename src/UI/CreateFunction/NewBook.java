package UI.CreateFunction;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewBook extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Auth;
	private JTextField Cate;
	private JTextField Pub;
	private JTextField Year;
	private JTextField Price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBook frame = new NewBook();
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
	public NewBook() throws ClassNotFoundException, SQLException {
		setTitle("Stock new book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book ID");
		lblNewLabel.setBounds(10, 11, 88, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setBounds(10, 36, 88, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblAuthorId = new JLabel("Author");
		lblAuthorId.setBounds(10, 61, 88, 14);
		contentPane.add(lblAuthorId);
		
		JLabel lblCategoryId = new JLabel("Category");
		lblCategoryId.setBounds(10, 86, 88, 14);
		contentPane.add(lblCategoryId);
		
		JLabel lblPublisherId = new JLabel("Publisher");
		lblPublisherId.setBounds(10, 111, 88, 14);
		contentPane.add(lblPublisherId);
		
		JLabel lblPublishYear = new JLabel("Publish Year");
		lblPublishYear.setBounds(10, 136, 88, 14);
		contentPane.add(lblPublishYear);
		
		Name = new JTextField();
		Name.setBounds(108, 33, 278, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		Auth = new JTextField();
		Auth.setBounds(108, 58, 278, 20);
		contentPane.add(Auth);
		Auth.setColumns(10);
		
		Cate = new JTextField();
		Cate.setBounds(108, 83, 278, 20);
		contentPane.add(Cate);
		Cate.setColumns(10);
		
		Pub = new JTextField();
		Pub.setBounds(108, 108, 278, 20);
		contentPane.add(Pub);
		Pub.setColumns(10);
		
		Year = new JTextField();
		Year.setBounds(108, 133, 278, 20);
		contentPane.add(Year);
		Year.setColumns(10);
		
		JButton btnShowAuthorsList = new JButton("Create New Author");
		btnShowAuthorsList.setBounds(388, 57, 157, 23);
		contentPane.add(btnShowAuthorsList);
		
		JButton btnShowCategoriesList = new JButton("Create New Category");
		btnShowCategoriesList.setBounds(388, 82, 157, 23);
		contentPane.add(btnShowCategoriesList);
		
		JButton btnShowPublishersList = new JButton("Create New Publisher");
		btnShowPublishersList.setBounds(388, 107, 157, 23);
		contentPane.add(btnShowPublishersList);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 161, 48, 14);
		contentPane.add(lblPrice);
		
		Price = new JTextField();
		Price.setBounds(108, 158, 278, 20);
		contentPane.add(Price);
		Price.setColumns(10);
		
		int i = 1;
		ResultSet rs = SQLSvConnection.querry("SELECT BookID FROM Book");
		while (rs.next()) {
			i = rs.getInt(1);
		}
		int bookid = ++i;
		
		
		JButton btnStock = new JButton("Stock");
		btnStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (  (check(Name.getText())) && (check(Auth.getText())) && (check(Cate.getText())) && (check(Pub.getText())) && (check(Year.getText())) && (check(Price.getText())) ) {
					try {
						Connection con = SQLSvConnection.Connect();
						Statement stm = con.createStatement();
						ResultSet rs = stm.executeQuery("SELECT PublisherID FROM Publisher WHERE PublisherName like '" + Pub.getText() + "'; ");
						if (!rs.next()) {
							JOptionPane.showMessageDialog(null, "Publisher isn't in database, create first!");
							return;
						}
						
						String pub = rs.getString(1);
						
						ResultSet rs1 = stm.executeQuery("SELECT AuthorID FROM Author WHERE AuthorName like '" + Auth.getText() + "'; ");
						if (!rs1.next()) {
							JOptionPane.showMessageDialog(null, "Author isn't in database, create first!");
							return;
						}
						
						String auth = rs1.getString(1);
						
						ResultSet rs2 = stm.executeQuery("SELECT CategoryID FROM Category WHERE CategoryName like '" + Cate.getText() + "'; ");
						if (!rs2.next()) {
							JOptionPane.showMessageDialog(null, "Category isn't in database, create first!");
							return;
						}
						
						String cate = rs2.getString(1);
						
						String sql = "INSERT INTO Book ( BookID, BookName, AuthorID, CategoryID, PublisherID, PublistYear, Price, Avalable) "
								+ " VALUES (" + bookid + ", '" 
								+ Name.getText()+ "', " 
								+ auth + ", " 
								+ cate + ", " 
								+ pub + ", " 
								+ Year.getText()+ ", " 
								+ Price.getText()+ ", " 
								+"1 ) ";
						int RowCount = stm.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Stock Success!");
						Name.setText(null); 
						Auth.setText(null); 
						Cate.setText(null); 
						Pub.setText(null); 
						Year.setText(null); 
						Price.setText(null);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnStock.setBounds(9, 192, 89, 23);
		contentPane.add(btnStock);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(452, 192, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblBookid = new JLabel(Integer.toString(bookid));
		lblBookid.setBounds(109, 11, 48, 14);
		contentPane.add(lblBookid);
		
		
	}
	public static boolean check(String str) {
        if(str != null && !str.isEmpty())
            return true;
        return false;
    }
}
