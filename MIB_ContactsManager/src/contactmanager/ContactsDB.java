package contactmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsDB {
	
	private String name, num, mail, addr, dob, notes, uid;
	Connection conn;
	Statement stmt;

	String user, password;
	//Connecting to Database
	public ContactsDB() {
		user="root";
		password="mib00786";
		try {
			String dburl="jdbc:mysql://localhost:3306/contactsmanager";
			conn = DriverManager.getConnection(dburl,user,password);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Retrieving Contacts from Database
	public List<ContactsData> getAllContacts(String uid) throws Exception {
		List<ContactsData> contacts = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet res = null;
		uid = ContactManagerApp.getUID();
		try {
			stmt = conn.prepareStatement("select * from v"+uid);
			res = stmt.executeQuery();
			while (res.next()) {
				ContactsData data = RowToContact(res);
				contacts.add(data);
			}
			return contacts;
		}
		finally {
			close(stmt,res);
		}
	}
	
	private ContactsData RowToContact(ResultSet res) throws SQLException {
		String data[] = {res.getString("id"),res.getString("name"), res.getString("num"), res.getString("mail"), res.getString("addr"), res.getString("dob"), res.getString("notes"), res.getString("uid")};
		ContactsData cdata = new ContactsData(data);
		return cdata; 
	}
	
	private static void close(Connection conn, Statement stmt, ResultSet res) throws SQLException {
		if (res != null)
			res.close();
		
		if (stmt != null)
			stmt.close();

		if (conn != null)
			conn.close();
	}	
	void close(Statement stmt, ResultSet res) throws SQLException {
		close(null, stmt, res);
	}
	
	//Add Contact Method
	public void addContact(ContactsData Cdata, String uid) throws Exception {
		PreparedStatement stmt=null;
		try {
			String[] data = Cdata.getData();
			stmt = conn.prepareStatement("insert into Contacts(name, num, mail, addr, dob, notes, uid) values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, data[0]);
			stmt.setString(2, data[1]);
			stmt.setString(3, data[2]);
			stmt.setString(4, data[3]);
			stmt.setString(5, data[4]); 
			stmt.setString(6, data[5]);
			stmt.setString(7, uid);
			stmt.executeUpdate();
			System.out.println("Contact Stored");
		}
		finally {
			close(stmt,null);
		}
	}
	
	//Modify Contact Method
	public void modifyContact(ContactsData Cdata) throws Exception {
		String[] data = Cdata.getData();
		PreparedStatement stmt=null;
		try {
			stmt = conn.prepareStatement("update Contacts set name=?, num=?, mail=?, addr=?, dob=?, notes=? where id=?");
			stmt.setString(1, data[0]);
			stmt.setString(2, data[1]);
			stmt.setString(3, data[2]);
			stmt.setString(4, data[3]);
			stmt.setString(5, data[4]);
			stmt.setString(6, data[5]);
			stmt.setString(7, Cdata.getId());
			stmt.executeUpdate();
		}
		finally {
			close(stmt,null);
		}
	}
	
	//Delete Contact Method
	public void deleteContact(ContactsData Cdata) throws Exception {
		PreparedStatement stmt = conn.prepareStatement("delete from Contacts where id=?");
		stmt.setString(1, Cdata.getId());
		stmt.executeUpdate();
	}
	
	//Search Contact Method
	public List<ContactsData> searchContact(String key, String uid) throws Exception {
		List<ContactsData> cdata = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = conn.prepareStatement("select * from v"+uid+" where name like ? or num like ? or mail like ? or addr like ? or dob like ? or notes like ?");
			stmt.setString(1, "%" + key +"%");
			stmt.setString(2, "%" + key +"%");
			stmt.setString(3, "%" + key +"%");
			stmt.setString(4, "%" + key +"%");
			stmt.setString(5, "%" + key +"%");
			stmt.setString(6, "%" + key +"%");
			res = stmt.executeQuery();

			while(res.next()) {
				ContactsData data = RowToContact(res);
				cdata.add(data);
			}
			return cdata;
		}
		finally {
			close(stmt,res);
		}
	}
	
	//View Contact Method
	public ContactsData viewContact(String id, String usid) throws Exception {
		PreparedStatement stmt = null;
		ResultSet res = null;
		name = num = mail = addr = dob = notes = "";
		try {
			stmt = conn.prepareStatement("select * from v"+usid+" where id=?");
			stmt.setString(1, id);
			res = stmt.executeQuery();
			while(res.next()) {
				name = res.getString("name");
				num = res.getString("num");
				mail = res.getString("mail");
				addr = res.getString("addr");
				dob = res.getString("dob");
				notes = res.getString("notes");
				uid = res.getString("uid");
			}
			String data[] = {id, name, num, mail, addr, dob, notes, uid};
			ContactsData cdata = new ContactsData(data);
			return cdata;
		}
		finally {
			close(stmt,null);
		}
	}
	
	public static void createView(String em) throws Exception {
		String uid=null;
		ContactsDB c = new ContactsDB();
		ResultSet res = c.stmt.executeQuery("select uid from Users where loginid='"+em+"'");
    	if(res.next()) uid = res.getString("uid");
    	PreparedStatement stmt = new ContactsDB().conn.prepareStatement("create view v"+uid+" as select * from contacts where uid='"+uid+"'");
    	stmt.executeUpdate();
        stmt.close();
	}
	
	public static void main(String[] args) throws Exception {
		new ContactsDB();
	}
}
