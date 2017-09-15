import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class EscanerRed extends Observable implements Runnable {

	private Observer padre;
	
	public EscanerRed(Observer padre) {
		this.padre = padre;
	}
	
	public void run() {
		while(true) {
			/*
			try {
				
				System.out.println("Buscador Iniciado");
				
				InetAddress actual = InetAddress.getLocalHost();
				String base = actual.getHostAddress();
				String [] divisionIp = base.split("\\.");
				base = divisionIp[0] + "." + divisionIp[1] + "." + divisionIp[2];
				
				int espera = 3000;
				
				for (int i = 0; i < 255; i++) {
					String buscado = base + "." + i;
					System.out.println("IP ES: " + buscado);
					if(InetAddress.getByName(buscado).isReachable(espera)) {
						padre.update(this, buscado);
						System.out.println("Nueva IP: " + buscado);
					}
				}
			Thread.sleep(16);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println("Error: " +  e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
	}
	
}
