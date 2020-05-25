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

public class UpdatePublisher extends JFrame {

	private JPanel contentPane;
	private JTextField id;
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
					UpdatePublisher frame = new UpdatePublisher();
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
	public UpdatePublisher() {
		setTitle("Update Pubisher");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPublisherId = new JLabel("Publisher ID");
		lblPublisherId.setBounds(10, 11, 89, 14);
		contentPane.add(lblPublisherId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 36, 89, 14);
		contentPane.add(lblName);
		
		JLabel lblAdress = new JLabel("Address");
		lblAdress.setBounds(10, 61, 89, 14);
		contentPane.add(lblAdress);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 86, 89, 14);
		contentPane.add(lblEmail);
		
		id = new JTextField();
		id.setBounds(109, 8, 315, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(109, 33, 315, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		add = new JTextField();
		add.setBounds(109, 58, 315, 20);
		contentPane.add(add);
		add.setColumns(10);
		
		mail = new JTextField();
		mail.setBounds(109, 83, 315, 20);
		contentPane.add(mail);
		mail.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!check(id.getText())) {
					JOptionPane.showMessageDialog(null, "Insert Publisher ID!");
					return;
				}
				Connection con;
				try {
					con = SQLSvConnection.Connect();
					Statement stm = con.createStatement();
					
					if (check(name.getText())) {
						stm.executeUpdate("UPDATE Publisher SET PublisherName = '" + name.getText() + "' WHERE PublisherID = " + id.getText() );
					}
					
					if (check(add.getText())) {
						stm.executeUpdate("UPDATE Publisher SET PublisherAddress = '" + add.getText() + "' WHERE PublisherID = " + id.getText() );
					}
					
					if (check(mail.getText())) {
						stm.executeUpdate("UPDATE Publisher SET PublisherEmail = '" + mail.getText() + "' WHERE PublisherID = " + id.getText() );
					}
					
					JOptionPane.showMessageDialog(null, "Update success!");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(10, 116, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(335, 116, 89, 23);
		contentPane.add(btnCancel);
	}

	public static boolean check(String str) {
        if(str != null && !str.isEmpty())
            return true;
        return false;
    }
}
