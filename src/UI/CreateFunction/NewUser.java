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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pass;
	private JPasswordField repass;
	private JTextField name;
	private JTextField address;
	private JTextField phone;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
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
	public NewUser() {
		setTitle("New User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 324, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 11, 87, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 36, 87, 14);
		contentPane.add(lblPassword);
		
		JLabel lblRetypePassword = new JLabel("Retype Password");
		lblRetypePassword.setBounds(10, 61, 87, 14);
		contentPane.add(lblRetypePassword);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 86, 87, 14);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 111, 87, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 136, 87, 14);
		contentPane.add(lblPhone);
		
		username = new JTextField();
		username.setBounds(107, 8, 181, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(107, 33, 181, 20);
		contentPane.add(pass);
		
		repass = new JPasswordField();
		repass.setBounds(107, 58, 181, 20);
		contentPane.add(repass);
		
		name = new JTextField();
		name.setBounds(107, 83, 181, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setBounds(107, 108, 181, 20);
		contentPane.add(address);
		address.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(107, 133, 181, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 161, 87, 14);
		contentPane.add(lblEmail);
		
		email = new JTextField();
		email.setBounds(107, 158, 181, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT Staffusername FROM Staff");
					while (rs.next()) {
						if (username.getText().contentEquals(rs.getString(1))) {
							JOptionPane.showMessageDialog(null, "Username already registered!");
						}
					}
					Connection con = SQLSvConnection.Connect();
					Statement stm = con.createStatement();
					String sql = "INSERT INTO Staff ( Staffusername, Staffpassword, StaffName, StaffAddress, StaffPhone, StaffEmail) VALUES ( '" + username.getText() + "', '" + pass.getText() + "', "
							   + name.getText() + "', " + address.getText() + "', " + phone.getText() + "', " + email.getText() + "' );";
					int RowCount = stm.executeUpdate(sql);
					stm.executeUpdate("INSERT INTO Staff (UserID) VALUES ("+ RowCount );
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCreate.setBounds(10, 194, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(209, 194, 89, 23);
		contentPane.add(btnCancel);
	}
}
