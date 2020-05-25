package UI.CreateFunction;

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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewPubliser extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField add;
	private JTextField mail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPubliser frame = new NewPubliser();
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
	public NewPubliser() throws ClassNotFoundException, SQLException {
		setTitle("New Publisher");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPublisherId = new JLabel("Publisher ID");
		lblPublisherId.setBounds(10, 11, 114, 14);
		contentPane.add(lblPublisherId);
		
		JLabel lblPublisherName = new JLabel("Publisher Name");
		lblPublisherName.setBounds(10, 36, 114, 14);
		contentPane.add(lblPublisherName);
		
		JLabel lblPublisherAddress = new JLabel("Publisher Address");
		lblPublisherAddress.setBounds(10, 61, 114, 14);
		contentPane.add(lblPublisherAddress);
		
		JLabel lblPublisherEmail = new JLabel("Publisher Email");
		lblPublisherEmail.setBounds(10, 86, 114, 14);
		contentPane.add(lblPublisherEmail);
		
		name = new JTextField();
		name.setBounds(134, 33, 290, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		add = new JTextField();
		add.setBounds(134, 58, 290, 20);
		contentPane.add(add);
		add.setColumns(10);
		
		mail = new JTextField();
		mail.setBounds(134, 83, 290, 20);
		contentPane.add(mail);
		mail.setColumns(10);
		
		int i = 0;
		ResultSet rs = SQLSvConnection.querry("SELECT PublisherID FROM Publisher");
		while (rs.next()) {
			i = rs.getInt(1);
		}
		int pubid = ++i;
		
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (check(name.getText()) && check(add.getText()) && check(mail.getText())) {
					Connection con;
					try {
						con = SQLSvConnection.Connect();
						Statement stm = con.createStatement();
						stm.executeUpdate("INSERT INTO Publisher VALUES ( " + pubid + ", '" + name.getText() + "', '" + add.getText() + "', '" + mail.getText() + "');");
						JOptionPane.showMessageDialog(null, "Create Complete!");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnCreate.setBounds(10, 123, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(335, 123, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblPubid = new JLabel(Integer.toString(pubid));
		lblPubid.setBounds(134, 11, 48, 14);
		contentPane.add(lblPubid);
	}
	
	public static boolean check(String str) {
        if(str != null && !str.isEmpty())
            return true;
        return false;
    }

}
