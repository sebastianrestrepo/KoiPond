import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Observable;

public class EscanerRed extends Thread {

	private ArrayList<Alimento> alimentos;
	private int conteo;
	
	public EscanerRed() {
		conteo = 0;
		alimentos = new ArrayList<Alimento>();
	}
	
	public void run() {
		while(true) {
		
			try {
				String base = "172.30.186."+conteo;
				InetAddress actual = InetAddress.getByName(base);
				if(actual.isReachable(500)) {
					System.out.println("nueva ip disponible: " + base);
					
				}
				
				if(conteo < 255)
				conteo++;
				
				//System.out.println("Host Local: " + hostLocal.toString());
				
				//System.out.println("El número de IP's conectadas es: " + numeroIPs.length);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
