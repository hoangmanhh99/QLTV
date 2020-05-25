package UI.IOclasses;

import java.sql.*;

public class SQLSvConnection {
	
	public static Connection Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://localhost:1433;databaseName=LibraryManagerment;user=new;password=123";
		Connection con = DriverManager.getConnection(url);
		return con;
	}
	public static ResultSet querry(String sql) throws SQLException, ClassNotFoundException {
		Connection con = Connect();
		Statement stm = con.createStatement();
	    ResultSet rs = stm.executeQuery(sql);
	    return rs;
	}
}
