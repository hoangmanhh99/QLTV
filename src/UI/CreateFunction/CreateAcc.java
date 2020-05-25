package UI.CreateFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.SQLSvConnection;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAcc extends JFrame {

	private JPanel contentPane;
	private JTextField usertxt;
	private JTextField passtxt;
	private JTextField repasstxt;
	private JTextField role;
	private JTextField nametxt;
	private JTextField dobtxt;
	private JTextField addresstxt;
	private JTextField phonetxt;
	private JLabel lblName;
	private JLabel lblDateOfBirth;
	private JLabel lblAddress;
	private JLabel lblTelephone;
	private JTextField enddate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAcc frame = new CreateAcc();
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
	public CreateAcc() {
		setTitle("Create Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 357, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usertxt = new JTextField();
		usertxt.setBounds(118, 11, 213, 20);
		contentPane.add(usertxt);
		usertxt.setColumns(10);
		
		passtxt = new JTextField();
		passtxt.setBounds(118, 42, 213, 20);
		contentPane.add(passtxt);
		passtxt.setColumns(10);
		
		repasstxt = new JTextField();
		repasstxt.setBounds(118, 73, 213, 20);
		contentPane.add(repasstxt);
		repasstxt.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 14, 98, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 45, 98, 14);
		contentPane.add(lblPassword);
		
		JLabel lblRetypePassword = new JLabel("Retype password");
		lblRetypePassword.setBounds(10, 76, 110, 14);
		contentPane.add(lblRetypePassword);
		
		role = new JTextField();
		role.setBounds(118, 104, 213, 20);
		contentPane.add(role);
		role.setColumns(10);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(10, 107, 98, 14);
		contentPane.add(lblRole);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!(check(usertxt.getText()) && check(passtxt.getText()) && check(repasstxt.getText()) && check(role.getText()) && check(nametxt.getText()) && check(dobtxt.getText()) && check(addresstxt.getText()) && check(phonetxt.getText()) && check(enddate.getText()))) 
					{ JOptionPane.showMessageDialog(null, "Please fill all the required infomation!"); return;} 
				if (!passtxt.getText().contentEquals(repasstxt.getText())) {
					JOptionPane.showMessageDialog(null, "Password mismatch!");
					return;
				}
				if (role.getText().contentEquals("Reader")) {
					try {
						Connection con = SQLSvConnection.Connect();
						Statement stm = con.createStatement();
						ResultSet rs = stm.executeQuery("SELECT AccID, username FROM Accounts");
						int accid=0;
						while (rs.next()) {
							if (rs.getString(2).contentEquals(usertxt.getText())) {
								JOptionPane.showMessageDialog(null, "Username already exist");
								return;
							}
							accid++;
						}
						
						String sql = "INSERT INTO Accounts "
									+"VALUES ("+ (accid+1) + ", '" + usertxt.getText() +"', '" + passtxt.getText() +"', 'reader');";
						int RowCount = stm.executeUpdate(sql);
						ResultSet rs2 = stm.executeQuery("SELECT * FROM Card");
						int cardid = 0;
						while (rs2.next()) {
							cardid++;
						}						
						sql = "INSERT INTO Card VALUES ("+ (cardid+1) + ", '" + java.time.LocalDate.now() + "', '" + enddate.getText() + "', null);";
						stm.executeUpdate(sql);
						ResultSet rs3 = stm.executeQuery("SELECT * FROM Reader");
						int readerid=0;
						while (rs3.next()) {
							readerid++;
						}
						sql = "INSERT INTO Reader "
							+ "VALUES (" + readerid+1 + ", '"+ nametxt.getText() + "', '" + dobtxt.getText() + "', '" + addresstxt.getText() + "', '" + phonetxt.getText() +"', " + (cardid+1) + ", " + (accid+1) + ");"
								;
						stm.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Create Success!");
						return;
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (role.getText().contentEquals("Admin")) {
					Connection con;
					try {
						con = SQLSvConnection.Connect();
					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery("SELECT AccID, username FROM Accounts");
					int accid=0;
					while (rs.next()) {
						if (rs.getString(2).contentEquals(usertxt.getText())) {
							JOptionPane.showMessageDialog(null, "Username already exist");
							return;
						}
						accid++;
					}
					
					String sql = "INSERT INTO Accounts "
								+"VALUES ("+ (accid+1) + ", '" + usertxt.getText() +"', '" + passtxt.getText() +"', 'admin');";
					stm.executeUpdate(sql);
					ResultSet rs2 = stm.executeQuery("SELECT * FROM Staff");
					int staffid=0;
					while (rs2.next()) {
						staffid++;
					}
					sql = "INSERT INTO Staff "
						+ "VALUES (" + staffid+1 + ", '"+ nametxt.getText() + "', '" + dobtxt.getText() + "', '" + addresstxt.getText() + "', '" + phonetxt.getText() +"', " + (accid+1) + ");"
							;
					stm.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Create Success!");
					return;
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else JOptionPane.showMessageDialog(null, "Role must be 'Admin' or 'Reader'!");
			}
		});
		btnCreate.setBounds(10, 313, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(242, 313, 89, 23);
		contentPane.add(btnCancel);
		
		nametxt = new JTextField();
		nametxt.setBounds(118, 135, 213, 20);
		contentPane.add(nametxt);
		nametxt.setColumns(10);
		
		dobtxt = new JTextField();
		dobtxt.setBounds(118, 166, 213, 20);
		contentPane.add(dobtxt);
		dobtxt.setColumns(10);
		
		addresstxt = new JTextField();
		addresstxt.setBounds(118, 197, 213, 20);
		contentPane.add(addresstxt);
		addresstxt.setColumns(10);
		
		phonetxt = new JTextField();
		phonetxt.setBounds(118, 228, 213, 20);
		contentPane.add(phonetxt);
		phonetxt.setColumns(10);
		
		lblName = new JLabel("Name");
		lblName.setBounds(10, 138, 48, 14);
		contentPane.add(lblName);
		
		lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setBounds(10, 169, 89, 14);
		contentPane.add(lblDateOfBirth);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 200, 98, 14);
		contentPane.add(lblAddress);
		
		lblTelephone = new JLabel("Telephone");
		lblTelephone.setBounds(10, 231, 98, 14);
		contentPane.add(lblTelephone);
		
		JLabel lblEndDate = new JLabel("End date");
		lblEndDate.setBounds(10, 259, 48, 14);
		contentPane.add(lblEndDate);
		
		enddate = new JTextField();
		enddate.setBounds(118, 256, 213, 20);
		contentPane.add(enddate);
		enddate.setColumns(10);
		
	}
	
	public static boolean check(String str) {
        if(str != null && !str.isEmpty())
            return true;
        return false;
    }
}
