package UI.MainFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.IOclasses.*;
import UI.IOclasses.Export;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowAllBook extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowAllBook frame = new ShowAllBook();
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
	public ShowAllBook() {
		setTitle("Book List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1096, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnShow = new JButton("Close");
		btnShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnShow.setBounds(981, 462, 89, 23);
		contentPane.add(btnShow);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1060, 440);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try {
			ResultSet rs = SQLSvConnection.querry("SELECT BookID, BookName, AuthorName, CategoryName, PublisherName, PublistYear, Price,\r\n" + 
					"case\r\n" + 
					"	when Avalable = 1 then 'In library'\r\n" + 
					"	when Avalable = 0 then 'Rent'\r\n" + 
					"end as Status\r\n" + 
					"from Book, Author, Category, Publisher\r\n" + 
					"where Book.AuthorID = Author.AuthorID AND Book.CategoryID = Category.CategoryID AND Book.PublisherID = Publisher.PublisherID;");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JButton btnExportToExcel = new JButton("Export to Excel file");
			btnExportToExcel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Export.Export(table.getModel());
				}
			});
			btnExportToExcel.setBounds(10, 462, 187, 23);
			contentPane.add(btnExportToExcel);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
