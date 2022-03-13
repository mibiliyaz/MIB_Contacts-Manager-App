package contactmanager;

import java.awt.BorderLayout;
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

public class ContactManagerApp extends JFrame {
	
	private JPanel contentPane;
	private JTextField searchBar;
	private JTable table;
	
	private ContactsDB contactsdb;
	
	//Launching application
	public static void main(String[] args) {
		new LogIn().setVisible(true);
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try	{
//					ContactManagerApp frame = new ContactManagerApp();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	
	//Contacts Manager 
	public ContactManagerApp() {
		new LogIn().setVisible(false);
		//Connecting to database
		try {
			contactsdb = new ContactsDB();
		}
		catch(Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: "+exc,"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		//Designing Application Frame		
		setTitle("Contacts Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 10, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.getLayout();
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
					List<ContactsData> contactslist=null;
					String key = searchBar.getText();
					if(key!=null && key.trim().length()>0) {
						contactslist = contactsdb.searchContact(key);
					}
					else {
						contactslist = contactsdb.getAllContacts();
					}
					ContactsTable model = new ContactsTable(contactslist);
					
					table.setModel(model);
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
						JOptionPane.showMessageDialog(ContactManagerApp.this, "You must select a contact", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				
					ContactsData temp = (ContactsData) table.getValueAt(row, ContactsTable.ID);
					AddContact dialog = new AddContact(ContactManagerApp.this, contactsdb, temp,false,true);
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
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton AddContactButton = new JButton("Add New Contact");
		AddContactButton.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(AddContactButton);
		
		JButton UpdateContactButton = new JButton("Update Contact");
		UpdateContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(ContactManagerApp.this, "You must select a contact", "Error", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				ContactsData temp = (ContactsData) table.getValueAt(row, ContactsTable.ID);
				AddContact dialog = new AddContact(ContactManagerApp.this, contactsdb, temp,true,false);
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
		panel_1.add(btnDeleteRow);
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(ContactManagerApp.this, "You must select a contact", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}
					ContactsData temp = (ContactsData) table.getValueAt(row, ContactsTable.ID);
					contactsdb.deleteContact(temp);
					JOptionPane.showMessageDialog(ContactManagerApp.this, "Contact deleted successfully", "Contact dleted", JOptionPane.ERROR_MESSAGE);
					RefreshView();
				} catch(Exception exc) {
					JOptionPane.showMessageDialog(ContactManagerApp.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		AddContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				AddContact dialog = new AddContact(ContactManagerApp.this,contactsdb);
				dialog.setVisible(true);
			}
		});
	}
	
	public void RefreshView() {
		try {
			List<ContactsData> contacts=null;
			contacts = contactsdb.getAllContacts();
			ContactsTable model= new ContactsTable(contacts);
			table.setModel(model);
		}
		catch(Exception exc) {
			JOptionPane.showMessageDialog(ContactManagerApp.this, "Error "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
