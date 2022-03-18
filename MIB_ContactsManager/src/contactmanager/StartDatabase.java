package contactmanager;

import java.io.*;
import java.sql.DriverManager;

import com.ibatis.common.jdbc.ScriptRunner;

public class StartDatabase {

	public StartDatabase() {
		try {
			ContactsDB c = new ContactsDB();
			c.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/",c.user,c.password);
			ScriptRunner scr = new ScriptRunner(c.conn, false, false);
		    Reader reader = new BufferedReader(new FileReader("SQL_Script.sql"));
		    scr.runScript(reader);
			System.out.println("Database created and ready to use.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		new StartDatabase();
	}

}
