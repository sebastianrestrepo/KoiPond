import java.io.IOException;
import java.net.*;
import java.util.Observable;

public class CreadorClientes extends Observable implements Runnable {

	private ServerSocket ss;
	private int puerto;
	
	public CreadorClientes() {
		try {
			ss = new ServerSocket(8080);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("Esperando nuevo cliente...");
				Socket nuevoSocket = ss.accept();
				setChanged();
				notifyObservers(nuevoSocket);
				clearChanged();
				Thread.sleep(1000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
