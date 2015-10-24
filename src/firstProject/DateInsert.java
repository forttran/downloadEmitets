package firstProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateInsert {
	
	public String URL;
	
	public DateInsert(String URL){
		this.URL = URL;
	}
	
	public String Insert(){
		String s;
		String str=null;
		try {
            URL u = new URL(URL);           
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            
            while((s=buff.readLine())!=null){
            	if(s.length()>5000){
            		//return s;
            		//System.out.println(s);
            		//System.out.println(s.length());
            		str=s;
            	}
            }
            return str;
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException :" + e.getMessage());
        }
		return null;
	}
	
	public String[] RegSplit(String s, String reg){
		String[] str = {};
		str=s.split(reg);
		return str;
	}

	public String RegSearch(String st) {
		String[] str = {};
		String s=null;
		st = st.replaceAll("<span class=\"[a-z|\\-|\\s]*\"></span>", "");
		String reg="<div class='mfd-quote-text'>(\\W|\\d)*(</div>)";
		Pattern pattern = Pattern.compile(reg);
		Matcher mt = pattern.matcher(st);
		while(mt.find()){
			s=mt.group();
			s = s.replaceAll("<div class='mfd-quote-text'>", "");
			s = s.replaceAll("</div>", "");
		}
		return s;
	}
	
}
