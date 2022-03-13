package contactmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsDB {
	
	int count = 0;
	String  uid = new LogIn().id;
	private String name, num, mail, addr, dob, notes;
	Connection conn;
	Statement s;
	
	//Connecting to Database
	public ContactsDB() throws Exception {
		String user="root";
		String password="mib00786";
		String dburl="jdbc:mysql://localhost:3306/contactsmanager";
		conn = DriverManager.getConnection(dburl,user,password);
	}

	//Retrieving Contacts from Database
	public List<ContactsData> getAllContacts() throws Exception {
		List<ContactsData> AllContacts = new ArrayList<>();
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery("select name, num, mail, addr, dob, notes from Contacts where uid='"+uid+"' ");
			while(res.next()) {
				ContactsData temp = RowtoContact(res);
				AllContacts.add(temp);
			}
			return AllContacts;
		}
		finally {
			close(stmt,res);
		}
	}
	
	private ContactsData RowtoContact(ResultSet res) throws SQLException {
		String data[] = {res.getString("name"), res.getString("num"), res.getString("mail"), res.getString("addr"), res.getString("dob"), res.getString("notes")};
		ContactsData temp = new ContactsData(data);
		return temp;
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
	public void addContact(ContactsData Cdata) throws Exception {
		PreparedStatement stmt=null;
		try {
			String[] data = Cdata.getData();
			stmt = conn.prepareStatement("insert into Contacts(name, num, mail, addr, dob, notes, uid) values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, data[1]);
			stmt.setString(2, data[2]);
			stmt.setString(3, data[3]);
			stmt.setString(4, data[4]);
			stmt.setString(5, data[5]);
			stmt.setString(6, data[6]);
			stmt.setString(7, uid);
			stmt.executeUpdate();
			count++;
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
			stmt.setString(1, data[1]);
			stmt.setString(2, data[2]);
			stmt.setString(3, data[3]);
			stmt.setString(4, data[4]);
			stmt.setString(5, data[5]);
			stmt.setString(6, data[6]);
			stmt.setInt(7, Cdata.getId());
			stmt.executeUpdate();
		}
		finally {
			close(stmt,null);
		}
	}
	
	//Delete Contact Method
	public void deleteContact(ContactsData Cdata) throws Exception {
		PreparedStatement stmt = conn.prepareStatement("delete from Contacts where id=?");
		stmt.setInt(1, Cdata.getId());
		stmt.executeUpdate();
		count--;
	}
	
	public List<ContactsData> searchContact(String key) throws Exception {
		List<ContactsData> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = conn.prepareStatement("select name, num, mail, addr, dob, notes from Contacts where name like ? or num like ? or mail like ? or addr like ? or dob like ? or notes like ?");
			stmt.setString(1, "%" + key +"%");
			stmt.setString(2, "%" + key +"%");
			stmt.setString(3, "%" + key +"%");
			stmt.setString(4, "%" + key +"%");
			stmt.setString(5, "%" + key +"%");
			stmt.setString(6, "%" + key +"%");
			stmt.setString(7, "%" + key +"%");
			stmt.setString(8, "%" + key +"%");
			stmt.setString(9, "%" + key +"%");
			stmt.setString(10, "%" + key +"%");
			res=stmt.executeQuery();
			while(res.next()) {
				ContactsData temp=RowtoContact(res);
				list.add(temp);
			}
			return list;
		}
		finally {
			close(stmt,res);
		}
	}
	
	//View Contact Method
	public ContactsData viewContact(int id) throws Exception {
		PreparedStatement stmt = null;
		ResultSet res = null;
		name = num = mail = addr = dob = notes = "";
		try {
			stmt = conn.prepareStatement("select name, num, mail, addr, dob, notes from Contacts where id=?");
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			while(res.next()) {
				name = res.getString("name");
				num = res.getString("num");
				mail = res.getString("mail");
				addr = res.getString("addr");
				dob = res.getString("dob");
				notes = res.getString("notes");
			}
			String data[] = {name, num, mail, addr, dob, notes};
			ContactsData temp = new ContactsData(data);
			return temp;
		}
		finally {
			close(stmt,null);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new ContactsDB();
	}
}
