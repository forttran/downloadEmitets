/*
 * � ������ �������������� ��������� ������ ���� ��������
 */
package firstProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class listEmitets {
	
	public Connection con;
	public Statement stmt = null;
	public PreparedStatement pstmt;
	public ResultSet rs;
	public ArrayList<String> arr = new ArrayList<String>();
	
	public listEmitets(){//����������� � ���� � ������������
		con = new connect().getConnector(); 
	}
	public ArrayList<String> getEmitets() throws SQLException{//��������� �������� ��������� �� ��������� ������ ���� ��������
		pstmt = con.prepareStatement("{call SelectTableEmitets()}");//�������� ������ ���� ��������
		rs = pstmt.executeQuery(); 
		while (rs.next()) 
			arr.add(rs.getNString("name"));
		return arr;
	}
}
