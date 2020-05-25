package UI.TestFiles;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DBtest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBtest frame = new DBtest();
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
	public DBtest() {
		String z="hhh";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JLabel lblBookName = new JLabel("Book name");
		lblBookName.setBounds(76, 108, 73, 14);
		contentPane.add(lblBookName);
		
		JLabel lblBook = new JLabel("Book");
		lblBook.setBounds(159, 108, 171, 14);
		contentPane.add(lblBook);
		JButton btnTest = new JButton("Test");
		btnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String url = "jdbc:sqlserver://localhost:1433;databaseName=LibraryManagerment;user=new;password=123";
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection con = DriverManager.getConnection(url);	
					String sql = "Select * from Book";
				    Statement stm = con.createStatement();
				    ResultSet rs = stm.executeQuery(sql);
				    rs.next();
				    change(lblBook,rs);				    
				} catch (Exception e1) {};				
			}
		});
		btnTest.setBounds(79, 37, 89, 23);
		contentPane.add(btnTest);
		
	}
	private static void change(JLabel lb, ResultSet rs) throws SQLException {
		lb.setText(rs.getString(2));
	}

}
