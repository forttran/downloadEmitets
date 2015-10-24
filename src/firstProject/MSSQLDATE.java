package firstProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSSQLDATE {
	public String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=myFirst;user=forttran;password=123;";
	public Connection con = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	public Boolean quation = false;
	public List<String[]> data;
	
	public MSSQLDATE(){
		connect();
	}
	public void connect() {//коннектимся
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
	
	public void createStructure(ArrayList<Emitets> date){//создание таблиц с названием эмитетов и их ID
		PreparedStatement pstmt;
		boolean rs;
		try {
			pstmt = con.prepareStatement("{call CreateEmitetsStructure()}");//Создание сводной таблицы
			rs = pstmt.execute(); 
			pstmt = con.prepareStatement("{call AddEmitetsStructure(?,?,?,?)}");//генерирование всех таблиц
			for(Emitets emitets:date){
				pstmt.setInt(1, 1);
				pstmt.setInt(2, new Integer(emitets.id));
				pstmt.setString(3, emitets.names);
				pstmt.setString(4, emitets.codes);
				rs = pstmt.execute();  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void InsertDate(String nameTable, List<String[]> date) {//функция добавления котировок в таблицы.
		PreparedStatement pstmt;
		boolean rs;
		try {
			pstmt = con.prepareStatement("{call AddEmitets(?,?,?,?,?)}");
			int i=0;
			for(String[] tick:date){
				pstmt.setString(1, nameTable);
				pstmt.setInt(2, new Integer(tick[0]));
				pstmt.setInt(3, new Integer(tick[1]));
				pstmt.setFloat(4, new Float(tick[2]));
				pstmt.setInt(5, new Integer(tick[3]));
				rs = pstmt.execute();  
				i++;
			}
			System.out.println("Добавлено: " + i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
