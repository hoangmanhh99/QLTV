package UI.TestFiles;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.EventQueue;
import com.microsoft.sqlserver.jdbc.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		JLabel ID = new JLabel("ID");
		ID.setBounds(10, 130, 109, 14);
		contentPane.add(ID);
		
		JLabel Name = new JLabel("Name");
		Name.setBounds(10, 155, 109, 14);
		contentPane.add(Name);
		
		JLabel year = new JLabel("Year");
		year.setBounds(10, 180, 109, 14);
		contentPane.add(year);
		
		JButton btnNewButton = new JButton("Test database");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url = "jdbc:sqlserver://localhost:1433;databaseName=LibraryManagerment;user=new;password=123";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection con = DriverManager.getConnection(url);
					String sql = "Select * from Book";
				    Statement stm = con.createStatement();
				    ResultSet rs = stm.executeQuery(sql);
				    while (rs.next()) {
				          String BookID = rs.getString(1);
				          String BookName = rs.getString(2);
				          String Year = rs.getString(6);
				          ID.setText(BookID);
				          Name.setText(BookName);
				          year.setText(Year);
				      }
				} catch (Exception e1) {}
			}
		});
		btnNewButton.setBounds(35, 11, 159, 94);
		contentPane.add(btnNewButton);
	}
}
