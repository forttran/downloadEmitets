/*
 * класс "команда" создан для логической группировки классов создания структуры в базе даных
 */
package firstProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class сreateStructureEmitets {
	public сreateStructureEmitets(){
		//Три строки для создания структуры
		try {
			httpRequest date = new httpRequest("http://www.finam.ru/cache/icharts/icharts.js");
			msSqlDate Dates = new msSqlDate();
			Dates.createStructure(date.preloader(date.emitetsRead()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean isCreate(){
		boolean flag = false;
		try {
			Connection con = new connect().getConnector(); 
			PreparedStatement pstmt = con.prepareStatement("{call infoCount()}");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				flag = (rs.getInt("cnt")>2)?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
