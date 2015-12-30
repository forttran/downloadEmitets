package firstProject;

import java.io.IOException;

public class ThreadReloadEmitets implements Runnable {

	@Override
	public void run() {
		try {	
			mainDesktop.getInstance().CreateJListEmitetsReload();
			new reloadEmitets().start();
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

}
