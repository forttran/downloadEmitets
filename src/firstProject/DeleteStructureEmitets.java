package firstProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStructureEmitets {
	public Connection con;
	public PreparedStatement pstmt;
	public Boolean rs;
	
	public DeleteStructureEmitets() throws SQLException{//�������� ������ ���� ��������	
		con = new connect().getConnector(); 
		pstmt = con.prepareStatement("{call DeleteEmitets()}");
		rs = pstmt.execute(); 
	}
}
