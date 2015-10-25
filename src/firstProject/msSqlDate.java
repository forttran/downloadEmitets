/*
 * ����� ������������� �� ���������������� �������� ���������
 */
package firstProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class msSqlDate {
	public Connection con = null;
	public Statement stmt = null;
	public boolean rs;
	public Boolean quation = false;
	public List<String[]> data;
	
	public msSqlDate(){
		connect();
	}
	public void connect() {//�����������
		con = new connect().getConnector(); 
	}
	
	public void createStructure(ArrayList<emitets> date){//�������� ������ � ��������� �������� � �� ID
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("{call CreateEmitetsStructure()}");//�������� ������� �������
			rs = pstmt.execute(); 
			pstmt = con.prepareStatement("{call AddEmitetsStructure(?,?,?,?)}");//������������� ���� ������
			for(emitets emitets:date){
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
	
	public void InsertDate(String nameTable, List<String[]> date) {//������� ���������� ��������� � �������.
		PreparedStatement pstmt;
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
			System.out.println("���������: " + i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}