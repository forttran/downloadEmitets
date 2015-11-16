package firstProject;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class readDateFile {
	public String code;
	public Date dates;
	public ArrayList<emitets> emitets;
	public int poz;
	public char [] a = new char[50];
	public char [] b = new char[50];
	
	public void readerDate() throws IOException{
		FileReader coder = new FileReader("code.txt");
		FileReader dater = new FileReader("date.txt");

		coder.read(a);
		dater.read(b);
		
		coder.close();
		dater.close();
	}
	public readDateFile(ArrayList<emitets> emitets){
		SimpleDateFormat DF = new SimpleDateFormat("dd.MM.yyyy");
		try {
			readerDate();
			code = String.valueOf(a);
			dates = DF.parse(String.valueOf(b));
			code=code.trim();
			System.out.println(code);
			System.out.println(dates);
			for(int i = 0; i < emitets.size(); i++){
				System.out.println("emitets.get(i).codes=" + emitets.get(i).codes+ "||");
				System.out.println("code=" + code + "||");
				if(emitets.get(i).codes.equals(code)){
					this.poz = i;
					break;
				}
			}
			System.out.println("poz = " + poz);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Не найдены файлы code.txt и date.txt...");
			//e.printStackTrace();
		}
	}
}
