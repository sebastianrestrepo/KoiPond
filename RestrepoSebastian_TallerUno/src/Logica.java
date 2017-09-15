import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica implements Observer {

	private PApplet app;
	private Cargar cargar;
	private int pantallas;
	private PImage[] pantallaInicial;
	private PImage fondo, agua;
	private int numActualPantallaIni;
	private PezAzul pezAzul;
	private PezRojo pezRojo;
	private ArrayList<Alimento> alimentos;
	private ArrayList<Thread> capsulas;
	private ComunicacionServidor cs;
	private EscanerRed er;

	public Logica(PApplet app) {
		this.app = app;
		cargar = new Cargar(app);
		cargarPantallaInicial();
		cargarImagenes();
		iniciarVariables();
	}

	public void iniciarVariables() {
		//Se inicia el Servidor
		cs = new ComunicacionServidor(this);
		cs.addObserver(this);
		
		Thread t = new Thread(cs); 
		t.start();

		//Se inicia el Pez Azul
		pezAzul = new PezAzul(this, app, 100, 100);
		new Thread(pezAzul).start();

		//Se inicia el Pez Rojo
		pezRojo = new PezRojo(this, app, 800, 600);
		new Thread(pezRojo).start();
		
		//Se crean los alimentos
		alimentos = new ArrayList<Alimento>();
		capsulas = new ArrayList<Thread>();
		/*
		alimentos.add(new AlimentoBueno(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));
		alimentos.add(new AlimentoMalo(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));
*/
		for (int i = 0; i < alimentos.size(); i++) {
			capsulas.add(new Thread(alimentos.get(i)));
			if (alimentos.get(i) != null) {
				capsulas.get(i).start();
			}
		}
		
		//Se inicia el escaner
		er = new EscanerRed(this);
		new Thread(er).start();
		
	}

	public void cargarPantallaInicial() {
		pantallaInicial = new PImage[78];
		for (int i = 0; i < pantallaInicial.length; i++) {
			pantallaInicial[i] = app.loadImage("../data/PantallaInicial/PantallaInicial_" + i + ".png");
		}
	}

	public void cargarImagenes() {
		fondo = app.loadImage("../data/fondo/Fondo.png");
		agua = app.loadImage("../data/fondo/Agua.png");

	}

	public void pantallas() {
		switch (pantallas) {
		case 0:
			pintarPantallaInicial();
			break;
		case 1:
			app.background(0);
			break;
		case 2:
			app.image(fondo, app.width / 2, app.height / 2);
			for (int i = 0; i < alimentos.size(); i++) {
				alimentos.get(i).pintar();
			}
			pezAzul.pintar();
			pezRojo.pintar();
			pezRojo.mover();
			app.image(agua, app.width / 2, app.height / 2);
			break;
		}
	}

	public void pintarPantallaInicial() {
		app.image(pantallaInicial[numActualPantallaIni], app.width / 2, app.height / 2);
		if (app.frameCount % 5 == 0) {
			numActualPantallaIni++;
			if (numActualPantallaIni >= 78) {
				numActualPantallaIni = 13;
			}
		}
	}

	//--------------------------UPDATE--------------------------//
	@Override
	public synchronized void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

		if (o instanceof ComunicacionServidor) {
			String mensaje = (String) arg;
			System.out.println("[notificación: " + mensaje + "]");
			if (mensaje.equals("arriba")) {
				pezRojo.setArriba(true);
			}
			if (mensaje.equals("abajo")) {
				pezRojo.setAbajo(true);
			}
			if (mensaje.equals("izquierda")) {
				pezRojo.setIzquierda(true);
			}
			if (mensaje.equals("derecha")) {
				pezRojo.setDerecha(true);
			}
			if (mensaje.equals("quieto")) {
				pezRojo.setArriba(false);
				pezRojo.setAbajo(false);
				pezRojo.setIzquierda(false);
				pezRojo.setDerecha(false);
			}
		}

		
		if(o instanceof EscanerRed && arg instanceof String) {
			String ip = (String) arg;
			if(ip.contains(".")) {
				int random = (int) app.random(2);
				switch(random) {
				case 0:
					alimentos.add(new AlimentoBueno(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));
					break;
				case 1:
					alimentos.add(new AlimentoMalo(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));
					break;
				
				}
			}
		}

	}

	public void keyPressed() {
		switch (pantallas) {
		case 0:
			if (app.key == app.ENTER) {
				pantallas = 1;
			}
			break;
		case 1:
			if (app.key == app.ENTER) {
				pantallas = 2;
			}
			break;
		case 2:
			// iniciarVariables();
			pezAzul.keyPressed();
			// pezRojo.keyPressed();
			// System.out.println("pantalla: " + pantallas);
			//
			break;
		}
	}

	// -----------GETTERS Y SETTERS-----------//
	public Cargar getCargar() {
		return cargar;
	}

	public void setCargar(Cargar cargar) {
		this.cargar = cargar;
	}

	public ComunicacionServidor getCs() {
		return cs;
	}

	public void setCs(ComunicacionServidor cs) {
		this.cs = cs;
	}

	public PezRojo getPezRojo() {
		return pezRojo;
	}

	public void setPezRojo(PezRojo pezRojo) {
		this.pezRojo = pezRojo;
	}

	// -------------FINAL------------//
}
