package firstProject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class firstProject {
	
	public static Iterator<Date> GenericDate(Date... dates){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date StartDate = dates[0];
		Date EndDate = (dates.length > 1 && dates[1] != null)?dates[1]: new Date();
				
		Iterator<Date> DateIterator = new DateIterator(StartDate, EndDate);
		/*while( DateIterator.hasNext()){
			Date date = DateIterator.next();
			System.out.println(dateFormat.format(date));
		}*/		
		return DateIterator;
	}
	
	public static void main(String[] args) {
		try {
			CreateStructureEmitets create = new CreateStructureEmitets();

			
			
			Calendar cal = Calendar.getInstance();
			cal.set(2011, Calendar.JANUARY, 1);
			Date dates = cal.getTime();
			GenericDate(dates);
			
			
			
			String URL = "http://195.128.78.52/GAZP_141212_141212.txt?market=1&em=16842&code=GAZP&df=12&mf=11&yf=2014&from=12.12.2014&dt=12&mt=11&yt=2014&to=12.12.2014&p=1&f=GAZP_141212_141212&e=.txt&cn=GAZP&dtf=1&tmf=1&MSOR=0&mstime=on&mstimever=1&sep=1&sep2=1&datf=9";
			OnlyDateEmitets only = new OnlyDateEmitets(URL);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
