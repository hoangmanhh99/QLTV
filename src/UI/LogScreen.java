package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;

public class LogScreen extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogScreen frame1 = new LogScreen();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogScreen() {
		setTitle("Library Managerment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogIn = new JLabel("LOGIN TO LIB MANAGERMENT");
		lblLogIn.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblLogIn.setBounds(34, 11, 277, 41);
		contentPane.add(lblLogIn);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 63, 91, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 91, 91, 14);
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setBounds(73, 60, 152, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton loginBTN = new JButton("Login");
		loginBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = username.getText();
				String pass = passwordField.getText();
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT AccID, username, password, Role FROM Accounts");
					while (rs.next()) {
						if (user.contentEquals(rs.getString(2)) && pass.contentEquals(rs.getString(3)) && rs.getString(4).contentEquals("admin")) {
							MainMenu frame1 = new MainMenu(rs.getInt(1));
							frame1.setVisible(true);
							dispose();
							return;
						} else if (user.contentEquals(rs.getString(2)) && pass.contentEquals(rs.getString(3)) && rs.getString(4).contentEquals("reader")) {
							ReaderMenu frame1 = new ReaderMenu(rs.getInt(1));
							frame1.setVisible(true);
							dispose();
							return;
						}
					}
					JOptionPane.showMessageDialog(null, "Wrong username or password!");
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		loginBTN.setBounds(253, 63, 100, 42);
		contentPane.add(loginBTN);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(73, 88, 152, 20);
		contentPane.add(passwordField);
	}
}
