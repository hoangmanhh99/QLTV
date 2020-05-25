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

public class UpdateAuthor extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAuthor frame = new UpdateAuthor();
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
	public UpdateAuthor() {
		setTitle("Update Author");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAuthorId = new JLabel("Author ID");
		lblAuthorId.setBounds(10, 11, 87, 14);
		contentPane.add(lblAuthorId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 36, 87, 14);
		contentPane.add(lblName);
		
		id = new JTextField();
		id.setBounds(107, 8, 317, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(107, 33, 317, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!check(id.getText())) {
					JOptionPane.showMessageDialog(null, "Insert Author ID!");
					return;
				}
				Connection con;
				try {
					con = SQLSvConnection.Connect();
					Statement stm = con.createStatement();
					
					if (check(name.getText())) {
						stm.executeUpdate("UPDATE Author SET AuthorName = '" + name.getText() + "' WHERE AuthorID = " + id.getText() );
					}
										
					JOptionPane.showMessageDialog(null, "Update success!");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(8, 76, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(335, 76, 89, 23);
		contentPane.add(btnCancel);
	}
	
	public static boolean check(String str) {
        if(str != null && !str.isEmpty())
            return true;
        return false;
    }

}
