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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if (o instanceof CreadorClientes) {
			Socket s = (Socket) arg;
			ControlCliente nuevoCliente = new ControlCliente(s);
			clientes.add(nuevoCliente);
			System.out.println("nuevoCliente");
			// clientes.get(clientes.size()-1).enviarMensaje(new Mensaje(null,
			// clientes.size()-1));
			nuevoCliente.addObserver(this);
			new Thread(nuevoCliente).start();
		}

		if (o instanceof ControlCliente) {
			if (arg instanceof Mensaje) {
				Mensaje m = (Mensaje) arg;
				System.out.println("Nuevo mensaje con indice: " + m.getIndice());

				for (int i = 0; i < clientes.size(); i++) {
					if (m.getIndice() != i) {
						m.setIndice(i);
						clientes.get(i).enviarMensaje(m);
						System.out.println("Se reenvia a: " + i + " este indice: " + m.getIndice() + " y este mensaje: "
								+ m.getMensaje());
					} else {
						System.out.println("No se reenvia a: " + i);
					}
				}
				//

				System.out.println("[notificación: " + m.getMensaje() + " del cliente número: " + m.getIndice() + "]");
				if (m.getIndice() == 0) {
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
				
				if (m.getIndice() == 1) {
					if (m.getMensaje().equals("arriba")) {
						log.getPezAzul().setArriba(true);
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
			}
				//FINAL DE LA INSTANCIA DE MENSAJE
			}
			
	
			

		}
	// ---------------FINAL DE LA CLASE---------//
	}


