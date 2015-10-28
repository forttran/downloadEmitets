/*
 * функция ответственная за определение структуры загрузки котировок
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
	
	public downloadEmitets() throws IOException{//при создании объекта зупускаем функцию 
		start();
	}
	
	public int getDays(Date start, Date end){ // Сравниваем две даты
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
	public cortegDataLoad datesLoad(readDateFile rDF){//осуществляем все операции с датами
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
	
	public void start() throws IOException{//Пока функция реализует вложенный массив для перебора всех эмитетов и дат
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
	
	public Iterator<Date> GenericDate(Date... dates){//Создаем итератор дат. Если вторая не задана берем текущую дату
		Date StartDate = dates[0];
		Date EndDate = (dates.length > 1 && dates[1] != null)?dates[1]: new Date();
				
		Iterator<Date> DateIterator = new dateIterator(StartDate, EndDate);
		return DateIterator;
	}
	

	public String generateURL(emitets Emitet, Date date){//генерируем ссылку
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String dt = dateFormat.format(date).toString();
		String[] dtr = dt.split("\\.");
		StringBuilder URL = new StringBuilder("http://195.128.78.52/date.txt?"); //установка IP и названия файла
		URL.append("market=1&");//тип эмитета. качаем только акции
		URL.append("em="+Emitet.id+"&");//числовой код эмитета
		URL.append("code="+Emitet.codes+"&");//код эмитета
		URL.append("df="+new Integer(dtr[0]).toString()+"&");//день от
		URL.append("mf="+(new Integer(dtr[1]).intValue()-1)+"&");//месяц от
		URL.append("yf="+new Integer(dtr[2]).toString()+"&");//год от
		URL.append("from="+dt+"&");//дата от
		URL.append("dt="+new Integer(dtr[0]).toString()+"&");//день до
		URL.append("mt="+(new Integer(dtr[1]).intValue()-1)+"&");//месяц до
		URL.append("yt="+new Integer(dtr[2]).toString()+"&");//год до
		URL.append("to="+dt+"&");//дата до
		URL.append("p=1&"); //периодичность. 1 - тики
		URL.append("f=date&");//название файла
		URL.append("e=.txt&");//тип файла
		URL.append("cn="+Emitet.codes+"&");//имя контракта. одинаково с кодом эмитета
		URL.append("dtf=1&");//формат даты
		URL.append("tmf=1&");//формат времени
		URL.append("MSOR=0&");//время начала или конца свечи
		URL.append("mstime=on&");//московское время
		URL.append("mstimever=1&");//московское время
		URL.append("sep=1&");//разделитель полей
		URL.append("sep2=1&");//разделитель разрядов
		URL.append("datf=9");//формат записи в фаил
		return URL.toString();
	}
}
