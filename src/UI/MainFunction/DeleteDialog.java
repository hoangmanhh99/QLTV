package UI.MainFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteDialog extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteDialog frame = new DeleteDialog("0");
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
	public DeleteDialog(String BookID) {
		setTitle("WARNING");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 246, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAreYouSure = new JLabel("ARE YOU SURE?");
		lblAreYouSure.setBounds(69, 11, 103, 14);
		contentPane.add(lblAreYouSure);
		
		JButton btnSureVkl = new JButton("Sure vkl");
		btnSureVkl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection con = SQLSvConnection.Connect();
					Statement stm = con.createStatement();
					String sql = "DELETE from Book WHERE BookID = " + BookID;
					int RowCount = stm.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Delete Success!");
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSureVkl.setBounds(10, 53, 89, 23);
		contentPane.add(btnSureVkl);
		
		JButton btnNo = new JButton("No");
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNo.setBounds(131, 53, 89, 23);
		contentPane.add(btnNo);
	}

}
