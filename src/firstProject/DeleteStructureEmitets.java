package firstProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DeleteStructureEmitets {
	public Connection con;
	public PreparedStatement pstmt;
	public Boolean rs;
	
	public DeleteStructureEmitets(){//�������� ������ ���� ��������	
		try {
			Object[] options = {"��, ������", "���, �� ������"};
			int n = JOptionPane.showOptionDialog(mainDesktop.getInstance().frame,
				"�� ������� ��� ������ ������� ���������?",
				"�������� ���������",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options, 
				options[0]);
			if(n==0){
				con = new connect().getConnector(); 
				pstmt = con.prepareStatement("{call DeleteEmitets()}");
				rs = pstmt.execute(); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
