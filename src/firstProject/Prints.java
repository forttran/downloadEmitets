package firstProject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Prints {
	public static void PrintInFile(String nameFile){//��������������� ������ ������ � ����
		PrintStream st;
		try {
			st = new PrintStream(new FileOutputStream(nameFile));
			System.setErr(st);
			System.setOut(st);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
