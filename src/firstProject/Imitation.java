package firstProject;

import java.util.Random;

public class Imitation {
	public  int next;
	public  int start=10;
	public  int randomu(){
		Random r = new Random(47);
		next = r.nextInt(4)-2;
		return next;
	}
	public void generate(){
		start = start + randomu();
		System.out.println(toString());
		generate();
	}
	public String toString(){
		String s=null;
		for(int i=0;i<start;i++){
			s+="|";
		}
		return s;
	}
}
