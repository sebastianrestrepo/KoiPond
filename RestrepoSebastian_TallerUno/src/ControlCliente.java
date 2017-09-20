import java.net.*;
import java.util.Observable;

import envios.Mensaje;

import java.io.*;

public class ControlCliente extends Observable implements Runnable {

	private Socket s;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Object atraparMensaje;
	private boolean conectado;
	private Logica log;

	/*
	 * Esta clase se encarga de la Lógica del enlace de flujos, de recibir y enviar
	 * mensajes, por medio del Socket que le asigna el ControlServidor y que llega
	 * desde el CreadorClientes
	 */

	public ControlCliente(Socket s) {
		this.s = s;
		this.log = log;
		conectado = false;

		System.out.println("[Conectado Socket...]");
		System.out.println("[Enlazando flujos...");
		try {
			salida = new ObjectOutputStream(s.getOutputStream());
			System.out.println("LOL");
			entrada = new ObjectInputStream(s.getInputStream());
			System.out.println("...Enlace completo :)]");
			conectado = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(200);

				recibirMensaje();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void recibirMensaje() {
		if (conectado) {
			try {
				// System.out.println("[Esperando mensaje]");
				atraparMensaje = entrada.readObject();
				if (atraparMensaje instanceof Mensaje) {
					Mensaje mensaje = (Mensaje) atraparMensaje;

					System.out.println("Llegó un mensaje: " + mensaje.getIndice() + mensaje.getMensaje());
					/// if(mensaje.equals("arriba"));
					setChanged();
					notifyObservers(mensaje);
					clearChanged();
				}
		
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
			Mensaje m = (Mensaje) obj;
			salida.writeObject(m);
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

	@Override
	public String toString() {
		return super.toString();
	}
	// ----------FINAL DE LA CLASE COMUNICACIONSERVIDOR--------//
}
