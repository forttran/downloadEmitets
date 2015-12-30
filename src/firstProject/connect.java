package firstProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {
	public String DataBase = "myFirst3";
	public String connectionUrl;
	public Connection con = null;
	public connect(String...pDataBase){
		try {
			for(String s:pDataBase) this.DataBase=s;
			connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName="+DataBase+";user=forttran;password=123;";
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
