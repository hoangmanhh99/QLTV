package UI.CreateFunction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class New extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New frame = new New();
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
	public New() {
		setTitle("Create New");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 277, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewBook = new JButton("New Book");
		btnNewBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewBook frame1;
				try {
					frame1 = new NewBook();
					frame1.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		btnNewBook.setBounds(52, 11, 159, 23);
		contentPane.add(btnNewBook);
		
		JButton btnNewPublisher = new JButton("New Publisher");
		btnNewPublisher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					NewPubliser frame1 = new NewPubliser();
					frame1.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewPublisher.setBounds(52, 45, 159, 23);
		contentPane.add(btnNewPublisher);
		
		JButton btnNewCategory = new JButton("New Category");
		btnNewCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					NewCategory frame1 = new NewCategory();
					frame1.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewCategory.setBounds(52, 79, 159, 23);
		contentPane.add(btnNewCategory);
		
		JButton btnNewAuthor = new JButton("New Author");
		btnNewAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					NewAuthor frame1 = new NewAuthor();
					frame1.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewAuthor.setBounds(52, 113, 159, 23);
		contentPane.add(btnNewAuthor);
		
		JButton btnNewAccount = new JButton("New Account");
		btnNewAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateAcc frame1 = new CreateAcc();
				frame1.setVisible(true);
				dispose();
			}
		});
		btnNewAccount.setBounds(52, 147, 159, 23);
		contentPane.add(btnNewAccount);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(52, 225, 159, 23);
		contentPane.add(btnCancel);
	}

}
