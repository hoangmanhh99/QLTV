package UI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import UI.CreateFunction.New;
import UI.MainFunction.DeleteBook;
import UI.MainFunction.Rent;
import UI.MainFunction.Return;
import UI.MainFunction.Search;
import UI.MainFunction.ShowAllBook;
import UI.MainFunction.Statistic;
import UI.MainFunction.TestImport;
import UI.UpdateFunction.Update;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Color;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu (1);
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
	public MainMenu(int AccID) throws ClassNotFoundException, SQLException {
		int count=0;
		int price=0;
		int available = 0;
		int unavailable = 0;	
		ResultSet rs = SQLSvConnection.querry("Select * from Book");    
		while (rs.next()) {
			count++;
			price+=rs.getInt("Price");
			if (rs.getInt("Avalable") == 1) {
		    		available++;
			} else {unavailable++;}
		}

		
		setTitle("Library Managerment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 260, 177);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("All books: ");
		lblNewLabel.setBounds(10, 39, 90, 14);
		panel.add(lblNewLabel);
		
		JLabel lblLibraryInfomation = new JLabel("LIBRARY INFORMATION");
		lblLibraryInfomation.setBounds(54, 11, 161, 14);
		panel.add(lblLibraryInfomation);
		
		JLabel lblBooksInLib = new JLabel("Books in lib: ");
		lblBooksInLib.setBounds(10, 64, 90, 14);
		panel.add(lblBooksInLib);
		
		JLabel lblBooksRent = new JLabel("Books rent: ");
		lblBooksRent.setBounds(10, 89, 90, 14);
		panel.add(lblBooksRent);
		
		JLabel lblTotalValues = new JLabel("Total values: ");
		lblTotalValues.setBounds(10, 114, 90, 14);
		panel.add(lblTotalValues);
		
		JLabel Allbooks = new JLabel("New label");
		Allbooks.setBounds(107, 39, 121, 14);
		panel.add(Allbooks);
				
	    String numofbooks = Integer.toString(count);
	    Allbooks.setText(numofbooks);
		
		JLabel avail = new JLabel("New label");
		avail.setBounds(107, 64, 121, 14);
		panel.add(avail);
		
		String availb = Integer.toString(available);
		avail.setText(availb);
		
		JLabel unavail = new JLabel("New label");
		unavail.setBounds(107, 89, 121, 14);
		panel.add(unavail);
		
		String unavailb = Integer.toString(unavailable);
		unavail.setText(unavailb);
		
		JLabel totalprice = new JLabel("totalvalue");
		totalprice.setBounds(107, 114, 121, 14);
		panel.add(totalprice);
		
		String allprice = Integer.toString(price);
		totalprice.setText(allprice + " VND");
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					MainMenu frame2 = new MainMenu(AccID);
					frame2.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setBounds(141, 143, 109, 23);
		panel.add(btnRefresh);
		
		JButton booklist = new JButton("Books list");
		booklist.setBounds(10, 143, 109, 23);
		panel.add(booklist);
		booklist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowAllBook frame1 = new ShowAllBook();
				frame1.setVisible(true);
			}
		});
				
		JButton btnRentBook = new JButton("Rent Book");
		btnRentBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Rent frame1;
				try {
					frame1 = new Rent(AccID);
					frame1.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		btnRentBook.setBounds(317, 11, 136, 35);
		contentPane.add(btnRentBook);
		
		JButton btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeleteBook frame1 = new DeleteBook();
				frame1.setVisible(true);
			}
		});
		btnDeleteBook.setBounds(317, 103, 136, 39);
		contentPane.add(btnDeleteBook);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Return frame1 = new Return();
				frame1.setVisible(true);
			}
		});
		btnReturnBook.setBounds(317, 57, 136, 35);
		contentPane.add(btnReturnBook);
		
		JButton btnSearchBook = new JButton("Search book");
		btnSearchBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Search frame1 = new Search();
				frame1.setVisible(true);
			}
		});
		btnSearchBook.setBounds(317, 153, 136, 35);
		contentPane.add(btnSearchBook);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LogScreen frame1 = new LogScreen();
				frame1.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBounds(486, 203, 136, 43);
		contentPane.add(btnLogOut);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 203, 443, 43);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Loged in as");
		lblNewLabel_7.setBounds(10, 11, 69, 14);
		panel_1.add(lblNewLabel_7);
		ResultSet rs3 = SQLSvConnection.querry("SELECT StaffName FROM Staff, Accounts WHERE Accounts.AccID = Staff.AccID AND Accounts.AccID = " + AccID);
		rs3.next();
		JLabel lblNewLabel_8 = new JLabel(rs3.getString(1));
		lblNewLabel_8.setBounds(80, 11, 185, 14);
		panel_1.add(lblNewLabel_8);
		
		JLabel lblAppVersion = new JLabel("App version");
		lblAppVersion.setBounds(275, 11, 69, 14);
		panel_1.add(lblAppVersion);
		
		JLabel lblNewLabel_9 = new JLabel("Beta 1.0.0");
		lblNewLabel_9.setBounds(354, 11, 110, 14);
		panel_1.add(lblNewLabel_9);
		
		JButton btnCreateNew = new JButton("Create New");
		btnCreateNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				New neW = new New();
				neW.setVisible(true);
			}
		});
		btnCreateNew.setBounds(486, 11, 136, 35);
		contentPane.add(btnCreateNew);
		
		JButton btnImportexport = new JButton("Import book data");
		btnImportexport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TestImport frame1 = new TestImport();
				frame1.setVisible(true);
			}
		});
		btnImportexport.setBounds(486, 103, 136, 39);
		contentPane.add(btnImportexport);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Update frame1 = new Update();
				frame1.setVisible(true);
			}
		});
		btnUpdate.setBounds(486, 57, 136, 35);
		contentPane.add(btnUpdate);
		
		JLabel lblLibraryManagerment = new JLabel("Library Managerment - Project I - Nguyen Thieu Quang - 20173331 - 2019");
		lblLibraryManagerment.setBounds(203, 257, 431, 14);
		contentPane.add(lblLibraryManagerment);
		
		JButton btnAbout = new JButton("Statistic");
		btnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Statistic frame1 = new Statistic();
				frame1.setVisible(true);
			}
		});
		btnAbout.setBounds(486, 153, 136, 35);
		contentPane.add(btnAbout);
		
	}
}
