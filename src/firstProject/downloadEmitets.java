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
		
		Calendar cal = Calendar.getInstance();
		cal.set(2011, Calendar.JANUARY, 1);
		Date dates = cal.getTime();
		GenericDate(dates);
		listEmitets listEm = new listEmitets();
		try {
			Emitets = listEm.getEmitets();
			for(String s:Emitets){
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Iterator<Date> GenericDate(Date... dates){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
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
