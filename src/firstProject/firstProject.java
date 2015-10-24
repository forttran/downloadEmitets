package firstProject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class firstProject {
	
	public static String IncrementDate(String dates){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date first_date = dateFormat.parse(dates);
			Calendar c = Calendar.getInstance();
			c.setTime(dateFormat.parse(dates));
			c.add(Calendar.DATE, 1);  // number of days to add
			dates = dateFormat.format(c.getTime());
			//System.out.println(dates);
			Date dd = new Date();
			//if(first_date.before(dd)){
				System.out.println(first_date+" ++++ ");
				//System.out.println(dd);
				IncrementDate(dates);
			//}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			Long a = System.currentTimeMillis();
			//тра та та
			//Три строки для создания структуры
			/*httpRequest date = new httpRequest("http://www.finam.ru/cache/icharts/icharts.js");
			MSSQLDATE Date = new MSSQLDATE();
			Date.createStructure(date.preloader(date.emitetsRead()));*/

			//http://195.128.78.52/GAZP_141212_141212.txt?
			//market=1
			
			//&em=16842
			//&code=GAZP
			//&df=12&mf=11&yf=2014&from=12.12.2014
			//&dt=12&mt=11&yt=2014&to=12.12.2014
			
			//&p=1
			//&f=GAZP_141212_141212
			//&e=.txt
			
			//&cn=GAZP
			
			//&dtf=1&tmf=1
			//&MSOR=0&
			//mstime=on&mstimever=1&
			//sep=1&sep2=1
			//&datf=9"
			
			
			String dates = "20110101";
			IncrementDate(dates);
			httpRequest date = new httpRequest("http://195.128.78.52/GAZP_141212_141212.txt?market=1&em=16842&code=GAZP&df=12&mf=11&yf=2014&from=12.12.2014&dt=12&mt=11&yt=2014&to=12.12.2014&p=1&f=GAZP_141212_141212&e=.txt&cn=GAZP&dtf=1&tmf=1&MSOR=0&mstime=on&mstimever=1&sep=1&sep2=1&datf=9");
			/*Long b = System.currentTimeMillis() - a;
			System.out.println("приконнектились " + b);
			List<String[]> dt = date.httpRead();
			 b = System.currentTimeMillis() - a;
			System.out.println("Получили данные " + b);
			MSSQLDATE Date = new MSSQLDATE();
			b = System.currentTimeMillis() - a;
			System.out.println("Законектились к базе " + b);
			Date.InsertDate("GAZP", dt);
			
			b = System.currentTimeMillis() - a;
			System.out.println("Закончили запись " + b);*/
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
