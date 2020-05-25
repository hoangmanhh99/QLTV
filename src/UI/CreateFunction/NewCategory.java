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

public class NewCategory extends JFrame {

	private JPanel contentPane;
	private JTextField name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCategory frame = new NewCategory();
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
	public NewCategory() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAuthorId = new JLabel("Category ID");
		lblAuthorId.setBounds(10, 11, 68, 14);
		contentPane.add(lblAuthorId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 36, 68, 14);
		contentPane.add(lblName);
		
		name = new JTextField();
		name.setBounds(88, 33, 336, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		int i = 0;
		ResultSet rs = SQLSvConnection.querry("SELECT CategoryID FROM Category");
		while (rs.next()) {
			i = rs.getInt(1);
		}
		int cateid = ++i;
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (check(name.getText())) {
					Connection con;
					try {
						con = SQLSvConnection.Connect();
						Statement stm = con.createStatement();
						stm.executeUpdate("INSERT INTO Category VALUES ( " + cateid + ", '" + name.getText() + "');");
						JOptionPane.showMessageDialog(null, "Create Complete!");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnCreate.setBounds(10, 90, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(335, 90, 89, 23);
		contentPane.add(btnCancel);
				
		
		JLabel lblAuthid = new JLabel(Integer.toString(cateid));
		lblAuthid.setBounds(88, 11, 48, 14);
		contentPane.add(lblAuthid);
		
	}
	
	public static boolean check(String str) {
        if(str != null && !str.isEmpty())
            return true;
        return false;
    }
}

