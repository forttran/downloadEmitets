package firstProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DeleteStructureEmitets {
	public Connection con;
	public PreparedStatement pstmt;
	public Boolean rs;
	
	public DeleteStructureEmitets(){//Получаем список всех эмитетов	
		try {
			Object[] options = {"Да, уверен", "Нет, не уверен"};
			int n = JOptionPane.showOptionDialog(mainDesktop.getInstance().frame,
				"Вы уверены что хотите удалить структуру?",
				"Удаление структуры",
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
