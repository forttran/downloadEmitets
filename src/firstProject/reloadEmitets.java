package firstProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class reloadEmitets {
	public void start() throws IOException{
		try {
			ArrayList<emitets> AL = new listEmitets().getEmitets();
			for(emitets em:AL){
				System.out.println(em.names);
				isReload(em);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("������ �������� ��� ������� ���������)");
		
	}
	public String isReload(emitets em){//����� �� 4 ����� ������ �������� ��� ���������� ���������� � ����������
		int flag=0;
		int i=0;
		String last_date = null;
		while (flag==0) {
			if(i==4) break;//� ������ �������� �������������� ���������.
			Connection con = new connect("myFirst"+i).getConnector(); 
			PreparedStatement pstmt;
			try {
				pstmt = con.prepareStatement("{call is_table_exist(?)}");//������� ������ � �����
				pstmt.setString(1, "TEM_"+em.codes);
				ResultSet rs = pstmt.executeQuery();  
				while (rs.next()) flag=rs.getInt(1);
				
				if(flag==1){
					System.out.println("���� �������");
					pstmt = con.prepareStatement("{call select_last_date(?)}");//��������� ���� ���������� ������
					pstmt.setString(1, em.codes);
					ResultSet rs1 = pstmt.executeQuery();  
					while (rs1.next()) last_date = rs1.getString(1);
					
					System.out.println("������ ���� ="+last_date);
					SimpleDateFormat format = new SimpleDateFormat();
					format.applyPattern("yyyyMMdd");
					Date docDate;
					Date date;
					
					try {
						docDate = format.parse(last_date);
						System.out.println("���� = "+docDate);
						Iterator<Date> DateIterator = downloadEmitets.GenericDate(docDate);
						System.out.println("������_________");
						while( DateIterator.hasNext()){
							date = DateIterator.next();
							String URL = downloadEmitets.generateURL(em, date);
							new onlyDateEmitets(URL,em.codes,"myFirst"+i);
							System.out.println("��������� ����="+URL);
						}
						System.out.println("�����________");
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						System.err.println("���������� ����������� ��-�� ������ SQL:");
						System.err.println("� �������:" + em.codes);
						System.err.println("���� �������");
						e.printStackTrace();						
					}catch (NumberFormatException e) {
						System.err.println("���������� ����������� ��-�� ������ NumberFormatException:");
						System.err.println("� �������:" + em.codes);
						System.err.println("���� �������");
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		return "��������";
	}
}
