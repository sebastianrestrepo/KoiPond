import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import envios.Mensaje;

public class ControlServidor implements Observer, Runnable {

	private CreadorClientes creadorClientes;
	private ArrayList<ControlCliente> clientes;
	private Logica log;

	public ControlServidor(Logica log) {
		this.log = log;
		clientes = new ArrayList<>();
		creadorClientes = new CreadorClientes();
		creadorClientes.addObserver(this);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// Se adicionan los nuevos clientes que el Creador de Clientes aceptó
		// previamente
		if (o instanceof CreadorClientes) {
			if (arg instanceof Socket) {
				Socket s = (Socket) arg;
				ControlCliente nuevoCliente = new ControlCliente(s);
				nuevoCliente.addObserver(this);
				clientes.add(nuevoCliente);
				clientes.get(clientes.size() - 1).enviarMensaje(new Mensaje(null, clientes.size()));
				Thread t = new Thread(nuevoCliente);
				t.start();
				System.out.println("nuevoCliente:" + nuevoCliente.toString());
			}
		}

		// Se capturan los mensaje que recibe cada cliente y se opera con ellos
		// dependiendo de las condiciones que se necesitan (mover personajes)
		if (o instanceof ControlCliente) {

			// ControlCliente controlador = (ControlCliente) o;
			if (arg instanceof Mensaje) {
				Mensaje m = (Mensaje) arg;
				System.out.println("Nuevo mensaje con indice: " + m.getIndice() + m.getMensaje());


				System.out.println("[notificación: " + m.getMensaje() + " del cliente número: " + m.getIndice() + "]");
				
				// Movimiento Pez Rojo
				if (m.getIndice() == 1) {
					if (m.getMensaje().equals("arriba")) {
						log.getPezRojo().setArriba(true);
					}
					if (m.getMensaje().equals("abajo")) {
						log.getPezRojo().setAbajo(true);
					}
					if (m.getMensaje().equals("izquierda")) {
						log.getPezRojo().setIzquierda(true);
					}
					if (m.getMensaje().equals("derecha")) {
						log.getPezRojo().setDerecha(true);
					}
					if (m.getMensaje().equals("quieto")) {
						log.getPezRojo().setArriba(false);
						log.getPezRojo().setAbajo(false);
						log.getPezRojo().setIzquierda(false);
						log.getPezRojo().setDerecha(false);
					}
				}

				// Movimiento Pez Azul
				if (m.getIndice() == 2) {
					System.out.println("Efectivamente el indice era 2 : " + m.getIndice());
					if (m.getMensaje().equals("arriba")) {
						log.getPezAzul().setArriba(true);
					}
					if (m.getMensaje().equals("abajo")) {
						log.getPezAzul().setAbajo(true);
					}
					if (m.getMensaje().equals("izquierda")) {
						log.getPezAzul().setIzquierda(true);
					}
					if (m.getMensaje().equals("derecha")) {
						log.getPezAzul().setDerecha(true);
					}
					if (m.getMensaje().equals("quieto")) {
						log.getPezAzul().setArriba(false);
						log.getPezAzul().setAbajo(false);
						log.getPezAzul().setIzquierda(false);
						log.getPezAzul().setDerecha(false);
					}
				}
			}
			// FINAL DE LA INSTANCIA DE MENSAJE
		}

	}

	// ---------------FINAL DE LA CLASE---------//
}
