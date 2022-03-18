package contactmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AddContact extends JDialog {
	
	private ContactsDB contacts;
	private ContactManagerApp contactManagerApp;
	private ContactsData prevContact = null;
	private boolean updateMode = false;
	private boolean viewMode = false;
	private final JPanel contentPanel = new JPanel();
	private JTextField NameTextField;
	private JTextField NumberTextField;
	private JTextField MailTextField;
	private JTextArea AddrTextField;
	private JTextField DOBTextField;
	private JTextArea NotesTextField;
	private JButton SaveButton, cancelButton;
	
	String id, uid;
	public AddContact(ContactManagerApp app, ContactsDB contactsdb,ContactsData data, boolean UpdateMode,boolean ViewMode) throws Exception
	{
		this();
		contactManagerApp = app;
		contacts = contactsdb;
		prevContact = data;
		updateMode = UpdateMode;
		viewMode = ViewMode;
		if(updateMode) setTitle("Modify Contact");
		if(viewMode) {
			setTitle("View Contact");
			NameTextField.setEditable(false);
			NumberTextField.setEditable(false);
			MailTextField.setEditable(false);
			AddrTextField.setEditable(false);
			DOBTextField.setEditable(false);
			NotesTextField.setEditable(false);
			SaveButton.setVisible(false);
			cancelButton.setText("Ok");
		}
        id = prevContact.getId();
        uid = ContactManagerApp.getUID();
        ContactsData cdata= contactsdb.viewContact(id, uid);
        populateGui(cdata);
	}
	
	private void populateGui(ContactsData cdata) {
		String[] data = cdata.getData();
		NameTextField.setText(data[0]);
		NumberTextField.setText(data[1]);
		MailTextField.setText(data[2]);
		AddrTextField.setText(data[3]);
		DOBTextField.setText(data[4]);
		NotesTextField.setText(data[5]);
	}
	
	public AddContact(ContactManagerApp app,ContactsDB contactsdata, String usid) {
		this();
		uid = usid;
		contacts = contactsdata;
		contactManagerApp = app;
	}
	
	public AddContact() {
		setTitle("Add New Contact");
		setBounds(300, 10, 450, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(Color.BLACK);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		Font font = new Font("Serif", Font.BOLD, 18);
		
		{
			JLabel lblName = new JLabel("Name");
			lblName.setFont(font);
			lblName.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			NameTextField = new JTextField();
			NameTextField.setFont(font);
			GridBagConstraints gbc_NametextField = new GridBagConstraints();
			gbc_NametextField.gridwidth = 5;
			gbc_NametextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_NametextField.insets = new Insets(0, 0, 5, 5);
			gbc_NametextField.gridx = 0;
			gbc_NametextField.gridy = 1;
			contentPanel.add(NameTextField, gbc_NametextField);
			NameTextField.setColumns(10);
		}
		{
			JLabel lblNumber = new JLabel("Phone Number");
			lblNumber.setFont(font);
			lblNumber.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblNumber = new GridBagConstraints();
			gbc_lblNumber.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumber.gridx = 0;
			gbc_lblNumber.gridy = 2;
			contentPanel.add(lblNumber, gbc_lblNumber);
		}
		{
			NumberTextField = new JTextField();
			NumberTextField.setFont(font);
			GridBagConstraints gbc_NumberTextField = new GridBagConstraints();
			gbc_NumberTextField.gridwidth = 5;
			gbc_NumberTextField.insets = new Insets(0, 0, 5, 5);
			gbc_NumberTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_NumberTextField.gridx = 0;
			gbc_NumberTextField.gridy = 3;
			contentPanel.add(NumberTextField, gbc_NumberTextField);
			NumberTextField.setColumns(10);
		}
		{
			JLabel lblMail = new JLabel("Email");
			lblMail.setForeground(Color.WHITE);
			lblMail.setFont(font);
			GridBagConstraints gbc_lblMail = new GridBagConstraints();
			gbc_lblMail.insets = new Insets(0, 0, 5, 5);
			gbc_lblMail.gridx = 0;
			gbc_lblMail.gridy = 4;
			contentPanel.add(lblMail, gbc_lblMail);
		}
		{
			MailTextField = new JTextField();
			MailTextField.setFont(font);
			GridBagConstraints gbc_MailTextField = new GridBagConstraints();
			gbc_MailTextField.gridwidth = 5;
			gbc_MailTextField.insets = new Insets(0, 0, 5, 5);
			gbc_MailTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_MailTextField.gridx = 0;
			gbc_MailTextField.gridy = 5;
			contentPanel.add(MailTextField, gbc_MailTextField);
			MailTextField.setColumns(10);
		}
		{
			JLabel lblAddr = new JLabel("Address");
			lblAddr.setForeground(Color.WHITE);
			lblAddr.setFont(font);
			GridBagConstraints gbc_lblAddr = new GridBagConstraints();
			gbc_lblAddr.insets = new Insets(0, 0, 5, 5);
			gbc_lblAddr.gridx = 0;
			gbc_lblAddr.gridy = 6;
			contentPanel.add(lblAddr, gbc_lblAddr);
		}
		{
			AddrTextField = new JTextArea(3,30);
			AddrTextField.setFont(font);
			GridBagConstraints gbc_AddrTextField = new GridBagConstraints();
			gbc_AddrTextField.gridwidth = 5;
			gbc_AddrTextField.insets = new Insets(0, 0, 5, 5);
			gbc_AddrTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_AddrTextField.gridx = 0;
			gbc_AddrTextField.gridy = 7;
			contentPanel.add(AddrTextField, gbc_AddrTextField);
			AddrTextField.setColumns(10);
		}		
		{
			JLabel lblDOB = new JLabel("Date of Birth");
			lblDOB.setForeground(Color.WHITE);
			lblDOB.setFont(font);
			GridBagConstraints gbc_lblDOB = new GridBagConstraints();
			gbc_lblDOB.insets = new Insets(0, 0, 5, 5);
			gbc_lblDOB.gridx = 0;
			gbc_lblDOB.gridy = 8;
			contentPanel.add(lblDOB, gbc_lblDOB);
		}
		{
			DOBTextField = new JTextField("DD MMM YYYY");
			DOBTextField.setFont(font);
			DOBTextField.addFocusListener(new FocusListener() {
	            public void focusGained(FocusEvent e) {
	            	if(!updateMode && !viewMode) DOBTextField.setText(null);
	            }public void focusLost(FocusEvent e) {}
			});
			GridBagConstraints gbc_DOBTextField = new GridBagConstraints();
			gbc_DOBTextField.gridwidth = 5;
			gbc_DOBTextField.insets = new Insets(0, 0, 5, 5);
			gbc_DOBTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_DOBTextField.gridx = 0;
			gbc_DOBTextField.gridy = 9;
			contentPanel.add(DOBTextField, gbc_DOBTextField);
			DOBTextField.setColumns(10);
		}
		{
			JLabel lblNotes = new JLabel("Notes");
			lblNotes.setForeground(Color.WHITE);
			lblNotes.setFont(font);
			GridBagConstraints gbc_lblNotes = new GridBagConstraints();
			gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
			gbc_lblNotes.gridx = 0;
			gbc_lblNotes.gridy = 10;
			contentPanel.add(lblNotes, gbc_lblNotes);
		}
		{
			NotesTextField = new JTextArea(6,20);
			NotesTextField.setFont(font);
			GridBagConstraints gbc_NotesTextField = new GridBagConstraints();
			gbc_NotesTextField.gridwidth = 5;
			gbc_NotesTextField.insets = new Insets(0, 0, 5, 5);
			gbc_NotesTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_NotesTextField.gridx = 0;
			gbc_NotesTextField.gridy = 11;
			contentPanel.add(NotesTextField, gbc_NotesTextField);
			NotesTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				SaveButton = new JButton("Save");
				SaveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
                            saveContact();
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(AddContact.this,  "Error while Saving the contact\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
                            e1.printStackTrace();
                        }finally {contactManagerApp.RefreshView();}
					}
				});
				SaveButton.setActionCommand("OK");
				buttonPane.add(SaveButton);
				getRootPane().setDefaultButton(SaveButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}	
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	//Save Contact Method
	protected void saveContact() {
		ContactsData cdata;
		uid = ContactManagerApp.getUID();
		String[] data = {NameTextField.getText(), NumberTextField.getText(), MailTextField.getText(), AddrTextField.getText(), DOBTextField.getText(), NotesTextField.getText(), uid};
		try {
			if(updateMode) {
				cdata = prevContact;
				cdata.setData(data);
			}	
			else cdata = new ContactsData(data);
			
			if(updateMode) {
				contacts.modifyContact(cdata);
				setVisible(false);
				dispose();
				JOptionPane.showMessageDialog(contactManagerApp,"Contact modified succesfully!","Contact Modified",JOptionPane.INFORMATION_MESSAGE);
			}
			else if(viewMode) {
				setVisible(false);
				dispose();
			}
			else {
				contacts.addContact(cdata, uid);
				setVisible(false);
				dispose();
				JOptionPane.showMessageDialog(contactManagerApp,"Contact added succesfully!","Contact Added",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(Exception exc) {
			JOptionPane.showMessageDialog(contactManagerApp, "Error: "+exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		contactManagerApp.RefreshView();
	}
	
	public static void main(String[] args) {
		new AddContact().setVisible(true);
	}
	
}
