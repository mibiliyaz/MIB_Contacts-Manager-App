package contactmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ContactManagerApp extends JFrame {
	
	private JPanel contentPane;
	private JTextField searchBar;
	private JTable table;

	private ContactsDB contactsdb;
	
	public static String uid;
	public static boolean status = false;
	//Launching application
	public static void main(String[] args) {
		new WelcomeScreen();
		if(!status) new LogIn().setVisible(true);
		else {
			EventQueue.invokeLater(new Runnable() {
				public void run() { 
					try	{
						ContactManagerApp frame = new ContactManagerApp();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	public ContactManagerApp(boolean stat, String usid) {
		status = stat;
		uid = usid;
    	main(null);
	}
	
	//Contacts Manager 
	public ContactManagerApp() throws Exception {
		contactsdb = new ContactsDB();
		
		//Designing Application Frame		
		setTitle("Contacts Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 10, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.getLayout();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.NORTH);

		searchBar = new JTextField();
		searchBar.setFont(new Font("Serif", Font.PLAIN, 17));
		searchBar.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(searchBar);
		searchBar.setColumns(16);
		
		JButton SearchButton = new JButton("Search");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					List<ContactsData> contacts=null;
					String key = searchBar.getText();
					if(key!=null && key.trim().length()>0) {
						contacts = contactsdb.searchContact(key, uid);
						ContactsTable model= new ContactsTable(contacts);
						table.setModel(model);
					}
					else {
						RefreshView();
					}
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(ContactManagerApp.this,  "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(SearchButton);

		JButton ViewContactButton = new JButton("View Contact");
		ViewContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(ContactManagerApp.this, "Select any contact to view\n", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				
					ContactsData cdata = (ContactsData) table.getValueAt(row, ContactsTable.OBJECT_COL);
					AddContact dialog = new AddContact(ContactManagerApp.this, contactsdb, cdata, false, true);
					dialog.setVisible(true);
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(ContactManagerApp.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		ViewContactButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(ViewContactButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("TimeNewRoman", Font.BOLD, 14));
		//table.setForeground(Color.WHITE);
		//table.setBackground(Color.BLACK);
		table.getParent().setBackground(Color.BLACK);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setForeground(Color.BLACK);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton AddContactButton = new JButton("Add New Contact");
		AddContactButton.setHorizontalAlignment(SwingConstants.LEFT);
		AddContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				AddContact dialog = new AddContact(ContactManagerApp.this,contactsdb,uid);
				dialog.setVisible(true);
			}
		});
		panel_1.add(AddContactButton);
		
		JButton UpdateContactButton = new JButton("Update Contact");
		UpdateContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(ContactManagerApp.this, "Select a contact to Edit\n", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}
					ContactsData cdata = (ContactsData) table.getValueAt(row, ContactsTable.OBJECT_COL);
					AddContact dialog = new AddContact(ContactManagerApp.this, contactsdb, cdata,true,false);
					dialog.setVisible(true);
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(ContactManagerApp.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_1.add(UpdateContactButton);
		
		JButton btnDeleteRow = new JButton("Delete Contact");
		btnDeleteRow.setHorizontalAlignment(SwingConstants.RIGHT);
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(ContactManagerApp.this, "You must select a contact", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}
					ContactsData temp = (ContactsData) table.getValueAt(row, ContactsTable.OBJECT_COL);
					contactsdb.deleteContact(temp);
					JOptionPane.showMessageDialog(ContactManagerApp.this, "Contact deleted successfully", "Contact dleted", JOptionPane.ERROR_MESSAGE);
					RefreshView();
				} catch(Exception exc) {
					JOptionPane.showMessageDialog(ContactManagerApp.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_1.add(btnDeleteRow);
		
		RefreshView();
	}
	
	public void RefreshView() {
		try {
			List<ContactsData> contacts=null;
			contacts = contactsdb.getAllContacts(uid);
			ContactsTable model = new ContactsTable(contacts);
			table.setModel(model);
		}
		catch(Exception exc) {
			JOptionPane.showMessageDialog(ContactManagerApp.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static String getUID() {
		return uid;
	}
}
