package firstProject;

import java.io.IOException;

public class firstProject {
	public static void main(String[] args) {
		try {
			ñreateStructureEmitets create = new ñreateStructureEmitets();
			downloadEmitets download = new downloadEmitets();
			String URL = "http://195.128.78.52/GAZP_141212_141212.txt?market=1&em=16842&code=GAZP&df=12&mf=11&yf=2014&from=12.12.2014&dt=12&mt=11&yt=2014&to=12.12.2014&p=1&f=GAZP_141212_141212&e=.txt&cn=GAZP&dtf=1&tmf=1&MSOR=0&mstime=on&mstimever=1&sep=1&sep2=1&datf=9";
			onlyDateEmitets only = new onlyDateEmitets(URL);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
