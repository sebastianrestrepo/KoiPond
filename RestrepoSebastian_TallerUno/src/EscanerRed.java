import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class EscanerRed extends Observable implements Runnable {

	private boolean noError;

	public EscanerRed() {
		noError = true;
	}

	public void run() {
		//Solo se ejecutará mientras no exista error
		while (noError) {

			try {

				System.out.println("Buscador Iniciado");

				InetAddress actual = InetAddress.getLocalHost();
				String base = actual.getHostAddress();
				String[] divisionIp = base.split("\\.");
				base = divisionIp[0] + "." + divisionIp[1] + "." + divisionIp[2];

				int espera = 3000;

				for (int i = 0; i < 255; i++) {
					String buscado = base + "." + i;
					System.out.println("IP ES: " + buscado);

					//Si encuentra una IP disponible en esa dirección base, notifique al observador
					if (InetAddress.getByName(buscado).isReachable(espera)) {
						
						setChanged();
						notifyObservers(buscado);
						clearChanged();

						System.out.println("Nueva IP: " + buscado);
					}

				}
				Thread.sleep(16);

			} catch (IOException e) {
				//Si aparece una excepeción, deje de ejecutar el Hilo e informele al observador que...
				//para que adicione el alimento de otra manera y no se pare el juego
				noError = false;
				System.out.println("Error Escaner Red");
				setChanged();
				notifyObservers("error");
				clearChanged();
				// TODO Auto-generated catch block
				e.printStackTrace();
				// System.out.println("Error: " + e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
