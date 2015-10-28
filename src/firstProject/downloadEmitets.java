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

public class downloadEmitets{
	
	public ArrayList<emitets> Emitets = new ArrayList<emitets>();
	public Iterator<Date> DateIterator;
	public listEmitets listEm = new listEmitets();
	private Calendar calendar = Calendar.getInstance();
	
	public downloadEmitets() throws IOException{//��� �������� ������� ��������� ������� 
		start();
	}
	
	public int getDays(Date start, Date end){ // ���������� ��� ����
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	    
		long l = calendar.getTimeInMillis();
	    
		calendar.setTime(end);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	    
		l = calendar.getTimeInMillis() - l;
		return (int) (l / (24 * 60 * 60 * 1000));
	}
	public cortegDataLoad datesLoad(readDateFile rDF){//������������ ��� �������� � ������
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		if(rDF.dates!=null)
			cal1.setTime(rDF.dates);
		else
			cal1.set(2000, Calendar.JANUARY, 1);
		
		Date dates1 = cal1.getTime();
		Date dates2 = cal2.getTime();
		Iterator<Date> Idate = GenericDate(dates1);
		int days = getDays(dates1, dates2);
		System.out.println("days = "+days);
		return new cortegDataLoad(cal1, cal2, Idate, days);
	}
	
	public void start() throws IOException{//���� ������� ��������� ��������� ������ ��� �������� ���� �������� � ���
		mainDesktop mD = mainDesktop.getInstance();
		controlThread cTh = controlThread.getInstance();
		
		try {
			Emitets = listEm.getEmitets();
			readDateFile rDF = new readDateFile(Emitets);
			for(int i=rDF.poz;i<Emitets.size();i++){
				cortegDataLoad cDL = datesLoad(rDF);
				DateIterator = cDL.dates;
				mD.JPr.setMaximum(cDL.days);
				int j=0;

				while( DateIterator.hasNext()){
					Date date = DateIterator.next();
					cTh.closeThead("loadEmitets", Emitets.get(i).codes, date);
					new onlyDateEmitets(generateURL(Emitets.get(i), date),Emitets.get(i).codes);
					System.out.println(generateURL(Emitets.get(i), date));					
					mD.JPr.setValue(j++);
				}
			}
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
