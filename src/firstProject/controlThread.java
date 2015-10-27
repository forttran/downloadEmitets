package firstProject;

import java.util.HashMap;

public class controlThread {
	private static controlThread  instance;
	private HashMap<String, Thread> ThMap = new HashMap< String, Thread>(); 
	public static controlThread  getInstance() {
		if (instance == null) {
			instance = new  controlThread();
		}
		return instance;
	}
	public void getMap(String key, Thread Th){
		ThMap.put(key, Th);
	}
	public Thread setMap(String key){
		Thread Th = ThMap.get(key);
		return Th;
	}
}
