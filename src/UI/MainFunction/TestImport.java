package UI.MainFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import UI.IOclasses.*;
import UI.IOclasses.Import;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestImport extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblDataInFile;
	private JButton btnImportToDatabase;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestImport frame = new TestImport();
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
	public TestImport() {
		setTitle("Import");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 957, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 921, 395);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblDataInFile = new JLabel("Data in file");
		lblDataInFile.setBounds(10, 11, 102, 14);
		contentPane.add(lblDataInFile);
		
		
		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(842, 442, 89, 23);
		contentPane.add(btnCancel);
		
		table.setModel(Import.Import());
		
		btnImportToDatabase = new JButton("Import to database");
		btnImportToDatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel data = new DefaultTableModel();
				data = table.getModel();
				int bookid = 0;
				for (int i = 0; i < data.getRowCount(); i++) {
					Connection con; 
					try {
						con = SQLSvConnection.Connect();
						Statement stm = con.createStatement();
						ResultSet rs = stm.executeQuery("SELECT AuthorID FROM Author WHERE AuthorName like '" + data.getValueAt(i, 2) + "' ");
						rs.next();
						data.setValueAt(rs.getInt(1), i, 2);
						
						rs = stm.executeQuery("SELECT CategoryID FROM Category WHERE CategoryName like '" + data.getValueAt(i, 3) + "' ");
						rs.next();
						data.setValueAt(rs.getInt(1), i, 3);
						
						rs = stm.executeQuery("SELECT PublisherID FROM Publisher WHERE PublisherName like '" + data.getValueAt(i, 4) + "' ");
						rs.next();
						data.setValueAt(rs.getInt(1), i, 4);
						
						rs = stm.executeQuery("SELECT BookID FROM Book");
						while (rs.next()) {
							bookid = rs.getInt(1);
						}
						bookid++;
						
						String sql = "INSERT INTO Book ( BookID, BookName, AuthorID, CategoryID, PublisherID, PublistYear, Price, Avalable) "
								+ " VALUES (" + bookid + ", '" 
								+ data.getValueAt(i, 1)+ "', " 
								+ data.getValueAt(i, 2) + ", " 
								+ data.getValueAt(i, 3) + ", " 
								+ data.getValueAt(i, 4) + ", " 
								+ data.getValueAt(i, 5) + ", " 
								+ data.getValueAt(i, 6) + ", " 
								+ data.getValueAt(i, 7) + " ) ";
						JOptionPane.showMessageDialog(null, sql);
						stm.executeUpdate(sql);
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnImportToDatabase.setBounds(10, 442, 150, 23);
		contentPane.add(btnImportToDatabase);
	}
}
