package firstProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {
	public String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=myFirst3;user=forttran;password=123;";
	public Connection con = null;
	public connect(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			//InsertDate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnector(){
		return con;
	}
}
