import java.net.*;
import java.util.Observable;
import java.io.*;

public class ComunicacionServidor extends Observable implements Runnable {

	private Socket s;
	private ServerSocket ss;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Object atraparMensaje;
	private boolean conectado;
	private Logica log;

	public ComunicacionServidor(Logica log) {
		this.log = log;
		conectado = false;
		/*
		 * try {
		 * 
		 * System.out.println("Iniciando Servidor"); ss = new ServerSocket(9090);
		 * 
		 * System.out.println("En espera..."); s = ss.accept();
		 * 
		 * System.out.println("[Conectado Socket...]");
		 * System.out.println("[Enlazando flujos...");
		 * 
		 * salida = new ObjectOutputStream(s.getOutputStream());
		 * System.out.println("LOL"); entrada = new
		 * ObjectInputStream(s.getInputStream());
		 * 
		 * System.out.println("...Enlace completo :)]");
		 * 
		 * conectado = true;
		 * 
		 * Thread t = new Thread(this); t.start();
		 * 
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	@Override
	public void run() {
		try {
			Thread.sleep(200);
			while (true) {

				if (s == null) {
					System.out.println("Iniciando Servidor"); 
					ss = new ServerSocket(9090);
					System.out.println("En espera...");
					s = ss.accept();
					System.out.println("[Conectado Socket...]");
					System.out.println("[Enlazando flujos...");
					entrada = new ObjectInputStream(s.getInputStream());
					System.out.println("LOL");
					salida = new ObjectOutputStream(s.getOutputStream());
					System.out.println("...Enlace completo :)]");
					conectado = true;
					// recibirMensaje();
				}
				recibirMensaje();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void recibirMensaje() {
		if (conectado) {
			try {
				System.out.println("[Esperando mensaje]");
				atraparMensaje = entrada.readObject();
				String mensaje = (String) atraparMensaje;
				System.out.println("Llegó un mensaje: " + mensaje);
				/// if(mensaje.equals("arriba"));
				setChanged();
				notifyObservers(mensaje);
				clearChanged();

				/*
				
				
				*/
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void enviarMensaje(Object obj) {
		try {
			salida.writeObject(obj);
			salida.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ----------GETTERS Y SETTERS--------//
	public Object getAtraparMensaje() {
		return atraparMensaje;
	}

	public void setAtraparMensaje(Object atraparMensaje) {
		this.atraparMensaje = atraparMensaje;
	}

	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	// ----------FINAL DE LA CLASE COMUNICACIONSERVIDOR--------//
}
