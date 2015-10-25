/*
 * ������� ������������� �� ����������� ��������� �������� ���������
 */
package firstProject;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class downloadEmitets {
	
	public downloadEmitets() throws IOException{//���� ������� ��������� ��������� ������ ��� �������� ���� �������� � ���
		ArrayList<emitets> Emitets = new ArrayList<emitets>();
		Iterator<Date> DateIterator;
		
		listEmitets listEm = new listEmitets();
		//Prints.PrintInFile("2.txt");
		try {
			Emitets = listEm.getEmitets();
			int i=0;
			for(emitets Emitet:Emitets){
				Calendar cal = Calendar.getInstance();
				cal.set(2011, Calendar.JANUARY, 1);
				Date dates = cal.getTime();
				DateIterator = GenericDate(dates);
				while( DateIterator.hasNext()){
					Date date = DateIterator.next();
					//generateURL(Emitet, date);
					new onlyDateEmitets(generateURL(Emitet, date),Emitet.codes);
					System.out.println(generateURL(Emitet, date));
					i++;
				}
			}
			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Iterator<Date> GenericDate(Date... dates){//������� �������� ���. ���� ������ �� ������ ����� ������� ����
		Date StartDate = dates[0];
		Date EndDate = (dates.length > 1 && dates[1] != null)?dates[1]: new Date();
				
		Iterator<Date> DateIterator = new dateIterator(StartDate, EndDate);
		return DateIterator;
	}
	
	@SuppressWarnings("static-access")
	public String generateURL(emitets Emitet, Date date){//���������� ������
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String dt = dateFormat.format(date).toString();
		String[] dtr = dt.split("\\.");
		StringBuilder URL = new StringBuilder("http://195.128.78.52/date.txt?"); //��������� IP � �������� �����
		URL.append("market=1&");//��� �������. ������ ������ �����
		URL.append("em="+Emitet.id+"&");//�������� ��� �������
		URL.append("code="+Emitet.codes+"&");//��� �������
		URL.append("df="+new Integer(dtr[0]).toString()+"&");//���� ��
		URL.append("mf="+(new Integer(dtr[1]).intValue()-1)+"&");//����� ��
		URL.append("yf="+new Integer(dtr[2]).toString()+"&");//��� ��
		URL.append("from="+dt+"&");//���� ��
		URL.append("dt="+new Integer(dtr[0]).toString()+"&");//���� ��
		URL.append("mt="+(new Integer(dtr[1]).intValue()-1)+"&");//����� ��
		URL.append("yt="+new Integer(dtr[2]).toString()+"&");//��� ��
		URL.append("to="+dt+"&");//���� ��
		URL.append("p=1&"); //�������������. 1 - ����
		URL.append("f=date&");//�������� �����
		URL.append("e=.txt&");//��� �����
		URL.append("cn="+Emitet.codes+"&");//��� ���������. ��������� � ����� �������
		URL.append("dtf=1&");//������ ����
		URL.append("tmf=1&");//������ �������
		URL.append("MSOR=0&");//����� ������ ��� ����� �����
		URL.append("mstime=on&");//���������� �����
		URL.append("mstimever=1&");//���������� �����
		URL.append("sep=1&");//����������� �����
		URL.append("sep2=1&");//����������� ��������
		URL.append("datf=9");//������ ������ � ����
		return URL.toString();
	}
}
