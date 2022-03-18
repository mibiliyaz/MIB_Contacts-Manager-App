package contactmanager;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

import com.ibatis.common.jdbc.ScriptRunner;

public class StartDatabase {

	public StartDatabase() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/",ContactsDB.user,ContactsDB.password);
			ScriptRunner scr = new ScriptRunner(conn, false, false);
		    Reader reader = new BufferedReader(new FileReader("SQL_Script.sql"));
		    scr.runScript(reader);
			System.out.println("\nDatabase created and ready to use.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		new StartDatabase();
	}

}
