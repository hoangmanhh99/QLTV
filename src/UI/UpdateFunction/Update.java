package UI.UpdateFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Update extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update frame = new Update();
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
	public Update() {
		setTitle("Update");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 250, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUpdateBook = new JButton("Update Book");
		btnUpdateBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateBook frame1 = new UpdateBook();
				frame1.setVisible(true);
				dispose();
			}
		});
		btnUpdateBook.setBounds(10, 11, 214, 23);
		contentPane.add(btnUpdateBook);
		
		JButton btnUpdateAuthor = new JButton("Update Author");
		btnUpdateAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateAuthor frame1 = new UpdateAuthor();
				frame1.setVisible(true);
				dispose();
			}
		});
		btnUpdateAuthor.setBounds(10, 45, 214, 23);
		contentPane.add(btnUpdateAuthor);
		
		JButton btnUpdateCategory = new JButton("Update Category");
		btnUpdateCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateCategory frame1 = new UpdateCategory();
				frame1.setVisible(true);
				dispose();
			}
		});
		btnUpdateCategory.setBounds(10, 79, 214, 23);
		contentPane.add(btnUpdateCategory);
		
		JButton btnUpdatePublisher = new JButton("Update Publisher");
		btnUpdatePublisher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdatePublisher frame1 = new UpdatePublisher();
				frame1.setVisible(true);
				dispose();
			}
		});
		btnUpdatePublisher.setBounds(10, 113, 214, 23);
		contentPane.add(btnUpdatePublisher);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(10, 182, 214, 23);
		contentPane.add(btnCancel);
	}

}
