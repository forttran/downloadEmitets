/*
 *  ���������� ���������� ��� �������� ���. 
 */
package firstProject;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class dateIterator implements Iterator<Date>, Iterable<Date>{

	private Calendar end = Calendar.getInstance();
	private Calendar current = Calendar.getInstance();
	
	public dateIterator(Date start, Date end){
		this.end.setTime(end);
		this.end.add(Calendar.DATE, -1);
		this.current.setTime(start);
		this.current.add(Calendar.DATE, -1);
	}
	
	public Iterator<Date> iterator() {
		return this;
	}

	public boolean hasNext() {
		return !current.after(end);
	}

	public Date next() {
		current.add(Calendar.DATE, 1);
		return current.getTime();
	}
	
}
