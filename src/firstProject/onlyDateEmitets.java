/*
 * ����� "�������" ������ ��� ���������� ����������� ������� �������� � ������ ��������� �� ������ URL
 */
package firstProject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class onlyDateEmitets {
	public onlyDateEmitets(String url,String code) throws IOException, SQLException, NumberFormatException{
		//Long a = System.currentTimeMillis();
		httpRequest date = new httpRequest(url);
		//Long b = System.currentTimeMillis() - a;
		//System.out.println("��������������� " + b);
		List<String[]> dt = date.httpRead();
		/*for(String[] s:dt){
			for(String str:s){
				System.out.print(str+" ");
			}
			System.out.println("");
		}*/
		// b = System.currentTimeMillis() - a;
		//System.out.println("�������� ������ " + b);
		msSqlDate Date = new msSqlDate();
		//b = System.currentTimeMillis() - a;
		//System.out.println("������������� � ���� " + b);
		Date.InsertDate(code, dt);
		
		//Long b = System.currentTimeMillis() - a;
		//System.out.println("��������� ������ " + b);
	}
}
