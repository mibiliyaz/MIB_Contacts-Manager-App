package contactmanager;

import java.sql.*;

class DBConnect {
    Connection c;
    Statement s;
    public DBConnect() {
        try {
            String user="root";
            String password="mib00786";
            String dburl="jdbc:mysql://localhost:3306/contactsmanager";
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(dburl,user,password);
            s = c.createStatement();	
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
		new DBConnect();
	}
}