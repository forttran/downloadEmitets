package firstProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStructureEmitets {
	public Connection con;
	public PreparedStatement pstmt;
	public Boolean rs;
	
	public DeleteStructureEmitets() throws SQLException{//Получаем список всех эмитетов	
		con = new connect().getConnector(); 
		pstmt = con.prepareStatement("{call DeleteEmitets()}");
		rs = pstmt.execute(); 
	}
}
