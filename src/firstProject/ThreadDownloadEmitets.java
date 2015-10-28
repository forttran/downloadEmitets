package firstProject;

import java.io.IOException;

public class ThreadDownloadEmitets implements Runnable{

	public void run() {
		try {	
			mainDesktop.getInstance().CreateJListEmitets();
			new downloadEmitets().start();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	

}
