package firstProject;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class downloadEmitets {
	
	public downloadEmitets(){
		ArrayList<String> Emitets = new ArrayList<String>();
		Iterator<Date> DateIterator;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		Calendar cal = Calendar.getInstance();
		cal.set(2011, Calendar.JANUARY, 1);
		Date dates = cal.getTime();
		DateIterator = GenericDate(dates);
		listEmitets listEm = new listEmitets();
		//Prints.PrintInFile("2.txt");
		try {
			Emitets = listEm.getEmitets();
			int i=0;
			for(String s:Emitets){
				/*while( DateIterator.hasNext()){
					Date date = DateIterator.next();
					System.out.println(s+": "+dateFormat.format(date));
				}*/
				i++;
				System.out.println(s);
			}
			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Iterator<Date> GenericDate(Date... dates){
		
		Date StartDate = dates[0];
		Date EndDate = (dates.length > 1 && dates[1] != null)?dates[1]: new Date();
				
		Iterator<Date> DateIterator = new dateIterator(StartDate, EndDate);
		/*while( DateIterator.hasNext()){
			Date date = DateIterator.next();
			System.out.println(dateFormat.format(date));
		}*/		
		return DateIterator;
	}
}
