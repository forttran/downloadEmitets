package firstProject;

import java.io.IOException;

public class firstProject {
	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		System.out.println("запустили");
		try {
			new mainDesktop();
			new сreateStructureEmitets();
			//new downloadEmitets();
			//String URL = "http://195.128.78.52/GAZP_141212_141212.txt?market=1&em=16842& code=GAZP&df=12&mf=11&yf=2014&from=12.12.2014&dt=12&mt=11&yt=2014&to=12.12.2014&p=1&f=GAZP_141212_141212&e=.txt&cn=GAZP&dtf=1&tmf=1&MSOR=0&mstime=on&mstimever=1&sep=1&sep2=1&datf=9";
			//String URL = "http://195.128.78.52/MSNG_121026_121026.txt?market=1&em=6&code=MSNG&df=26&mf=9&yf=2012&from=26.10.2012&dt=26&mt=9&yt=2012&to=26.10.2012&p=1&f=MSNG_121026_121026&e=.txt&cn=MSNG&dtf=1&tmf=1&MSOR=0&mstime=on&mstimever=1&sep=1&sep2=1&datf=9";

			//onlyDateEmitets only = new onlyDateEmitets(URL,"MSNG");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Long end = System.currentTimeMillis()-start;
		System.out.println("заняло времени: " + end);
	}
}
