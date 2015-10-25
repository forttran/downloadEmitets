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

public class downloadEmitets {
	
	public downloadEmitets() throws IOException{//Пока функция реализует вложенный массив для перебора всех эмитетов и дат
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
	
	public Iterator<Date> GenericDate(Date... dates){//Создаем итератор дат. Если вторая не задана берем текущую дату
		Date StartDate = dates[0];
		Date EndDate = (dates.length > 1 && dates[1] != null)?dates[1]: new Date();
				
		Iterator<Date> DateIterator = new dateIterator(StartDate, EndDate);
		return DateIterator;
	}
	
	@SuppressWarnings("static-access")
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
