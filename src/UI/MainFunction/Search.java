package UI.MainFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.*;
import UI.IOclasses.Export;
import net.proteanit.sql.DbUtils;

import javax.swing.JRadioButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JButton btnClose;
	private JButton btnExportToExcel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
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
	public Search() {
		setTitle("Search book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 942, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(79, 11, 168, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblKeyWords = new JLabel("Key words");
		lblKeyWords.setBounds(10, 14, 86, 14);
		contentPane.add(lblKeyWords);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ResultSet rs = SQLSvConnection.querry("SELECT BookID, BookName, AuthorName, CategoryName, PublisherName, PublistYear, Price,\r\n" + 
							"case\r\n" + 
							"	when Avalable = 1 then 'In library'\r\n" + 
							"	when Avalable = 0 then 'Rent'\r\n" + 
							"end as Status\r\n" + 
							"from Book, Author, Category, Publisher\r\n" + 
							"where Book.AuthorID = Author.AuthorID AND Book.CategoryID = Category.CategoryID AND Book.PublisherID = Publisher.PublisherID\r\n" + 
							"AND (\r\n" + 
							"BookID like '%" + textField.getText() + "%' OR \r\n" + 
							"BookName like '%" + textField.getText() + "%' OR \r\n" + 
							"AuthorName like '%" + textField.getText() + "%' OR \r\n" + 
							"CategoryName like '%" + textField.getText() + "%' OR \r\n" + 
							"PublisherName like '%" + textField.getText() + "%' OR \r\n" + 
							"PublistYear like '%" + textField.getText() + "%' OR \r\n" + 
							"Price like '%" + textField.getText() + "%');");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(257, 10, 89, 23);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 906, 235);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(827, 312, 89, 23);
		contentPane.add(btnClose);
		
		btnExportToExcel = new JButton("Export to Excel file");
		btnExportToExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Export.Export(table.getModel());
			}
		});
		btnExportToExcel.setBounds(10, 312, 190, 23);
		contentPane.add(btnExportToExcel);

	}
}
