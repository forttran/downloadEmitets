package firstProject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class writeDateFile {
	public String code;
	public String dates;
	public writeDateFile(String code, Date date){
		this.code = code;
		this.dates =  new SimpleDateFormat("dd.MM.yyyy").format(date);
		try {
			Writer coder = new FileWriter("code.txt", false);
			Writer dater = new FileWriter("date.txt", false);
			coder.write(code);
			dater.write(dates);
			coder.close();
			dater.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
