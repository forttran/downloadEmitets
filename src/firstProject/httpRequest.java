/*
 * Класс ответственный за непосредственнное получение данных с сервера
*/
package firstProject;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class httpRequest {
	public URLConnection conn;
	
	public httpRequest(String URL)  throws IOException{//формируем заголовки и отправляем запрос серверу
		conn = new URL(URL).openConnection();
		conn.setRequestProperty("Host", "195.128.78.52");
		conn.setRequestProperty("Accept", "image/webp,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
		conn.setRequestProperty("Referer", "http://www.finam.ru/analysis/profile041CA00007/");
		conn.connect();
	}
	
	public List<String[]> httpRead() throws IOException{//получаем ответ от сервера
		//PrintInFile("1.txt");
		List<String[]> res = new ArrayList<String[]>();
		if (((HttpURLConnection) conn).getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				res.add(line.split(","));
			}
			rd.close(); 
			return res;
		}else{
			System.out.println("Error");
			System.exit(0);
		}
		return null;
	}
	
	public ArrayList<String[]> emitetsRead() throws IOException{//получаем список эмитетов
		ArrayList<String[]> res = new ArrayList<String[]>();
		if (((HttpURLConnection) conn).getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			Pattern pt = Pattern.compile("\\[.+\\]");
			int i = 0;
			String[] ArrPattern = {",","','","','",",",",",",",",",",",",",","};
			while ((line = rd.readLine()) != null) {
				line = line.replaceAll("\\{", "[").replaceAll("\\}", "]");
				Matcher mt = pt.matcher(line);
				if (mt.find())
					res.add(mt.group(0).replaceAll("\\[", "").replaceAll("\\]", "").split(ArrPattern[i]));
				i++;
			}
			rd.close(); 
			return res;
		}else{
			System.out.println("Error");
			System.exit(0);
		}
		return null;
	}
		
	public ArrayList<emitets> preloader(ArrayList<String[]> date){//"Транспонируем" матрицу данных
		Prints.PrintInFile("1.txt");
		ArrayList<emitets> emitet = new ArrayList<emitets>();
		for(int i=0;i<date.get(3).length;i++){
			if(date.get(3)[i].equals("1")){
				emitets emitets = new emitets();
				//System.out.println(date.get(3)[i]+" "+date.get(0)[i] + " " + date.get(1)[i]+" "+date.get(2)[i]);
				emitets.id = date.get(0)[i];
				emitets.names = date.get(1)[i];
				emitets.codes = date.get(2)[i];
				emitet.add(emitets);
			}
		}
		return emitet;
	}
}
