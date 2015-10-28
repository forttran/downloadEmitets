package firstProject;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class cortegDataLoad {
	public Iterator<Date> dates;
	
	public Calendar cal1;
	public Calendar cal2;
	public int days;
	
	public cortegDataLoad(Calendar cal1, Calendar cal2, Iterator<Date> dates, int days){
		this.cal1 = cal1;
		this.cal2 = cal2;
		this.dates = dates;
		this.dates = dates;
		this.days = days;
	}
}
