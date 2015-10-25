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
	public ArrayList<emitets> arr = new ArrayList<emitets>();
	
	public listEmitets(){//����������� � ���� � ������������
		con = new connect().getConnector(); 
	}
	public ArrayList<emitets> getEmitets() throws SQLException{//��������� �������� ��������� �� ��������� ������ ���� ��������
		pstmt = con.prepareStatement("{call SelectTableEmitets()}");//�������� ������ ���� ��������
		rs = pstmt.executeQuery(); 
		while (rs.next()){
			emitets em = new emitets();
			em.names=rs.getString("NameID").replaceAll(" ", "");
			em.codes=rs.getString("CodeID").replaceAll(" ", "");
			em.id=new Integer(rs.getInt("ID")).toString();
			arr.add(em);
			
		}
		return arr;
	}
}
