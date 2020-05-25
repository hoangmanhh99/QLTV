package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.*;
import UI.MainFunction.ShowAllBook;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderMenu frame = new ReaderMenu(2);
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
	public ReaderMenu(int AccID) throws ClassNotFoundException, SQLException {
		setTitle("Reader Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReaderId = new JLabel("Reader ID");
		lblReaderId.setBounds(10, 53, 100, 14);
		contentPane.add(lblReaderId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 78, 100, 14);
		contentPane.add(lblName);
		
		JButton btnShowAllBook = new JButton("Show All Book");
		btnShowAllBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowAllBook frame1 = new ShowAllBook();
				frame1.setVisible(true);
			}
		});
		btnShowAllBook.setBounds(10, 103, 147, 23);
		contentPane.add(btnShowAllBook);
		
		JButton Search = new JButton("Search Book");
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UI.MainFunction.Search frame1 = new UI.MainFunction.Search();
				frame1.setVisible(true);
			}
		});
		Search.setBounds(167, 103, 147, 23);
		contentPane.add(Search);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LogScreen frame1 = new LogScreen();
				frame1.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBounds(324, 103, 147, 23);
		contentPane.add(btnLogOut);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(109, 53, 362, 14);
		contentPane.add(lblId);
		
		JLabel lblName_1 = new JLabel("name");
		lblName_1.setBounds(109, 78, 362, 14);
		contentPane.add(lblName_1);
		
		JLabel lblLibraryManagermentTool = new JLabel("LIBRARY MANAGERMENT TOOL FOR READER");
		lblLibraryManagermentTool.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLibraryManagermentTool.setBounds(95, 11, 352, 31);
		contentPane.add(lblLibraryManagermentTool);
		
		ResultSet rs = SQLSvConnection.querry("SELECT ReaderID, ReaderName FROM Reader WHERE AccID = " + AccID);
		rs.next();
		lblId.setText(rs.getString(1));
		lblName_1.setText(rs.getString(2));
	}
}
