/*����� "�������" ������ ��� ���������� ����������� ������� �������� � ������ ��������� �� ������ URL*/
package firstProject;

import java.io.IOException;
import java.util.List;

public class onlyDateEmitets {
	public onlyDateEmitets(String url) throws IOException{
		Long a = System.currentTimeMillis();
		httpRequest date = new httpRequest(url);
		Long b = System.currentTimeMillis() - a;
		System.out.println("��������������� " + b);
		List<String[]> dt = date.httpRead();
		 b = System.currentTimeMillis() - a;
		System.out.println("�������� ������ " + b);
		msSqlDate Date = new msSqlDate();
		b = System.currentTimeMillis() - a;
		System.out.println("������������� � ���� " + b);
		Date.InsertDate("GAZP", dt);
		
		b = System.currentTimeMillis() - a;
		System.out.println("��������� ������ " + b);
	}
}
