package contactmanager;

	import java.sql.*;
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.FileNotFoundException;

	public class IntoDatabase {

//		String name, num, mail, addr, dob, notes;

		public static void main(String[] args) throws Exception {
			new LogIn().setVisible(true);
			ContactsDB c = new ContactsDB();
			PreparedStatement myStmt=null;
			Statement my=null;
			
			String csvFile="contacts.csv";
			BufferedReader br=null;
			String line="";
			
			try {
//				String name,num,mail,addr,dob,notes;
				br = new BufferedReader(new FileReader(csvFile));
				int k=0;
				while((line=br.readLine())!=null) {
					k++;
					if(k==1)
						continue;					
					
//					name=num=mail=addr=dob=notes = "";
					String[] contact = line.split(",");
					
					/* Adding a contact's  name to the contact table in our contacts database */
					ContactsData temp = new ContactsData(contact);
					c.addContact(temp);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
		}

	}
