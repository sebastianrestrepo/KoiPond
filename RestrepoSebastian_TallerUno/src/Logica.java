import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Logica implements Observer {

	private PApplet app;
	private Cargar cargar;
	private int pantallas;
	private int segundos, minutos;
	private PImage[] pantallaCarga, pantallaInicial;
	private PImage fondo, agua, ganadorAzul, ganadorRojo, empate, instrucciones, planta;
	private PFont inconsolata;
	private int numActualCarga, numActualPantallaIni;
	private PezAzul pezAzul;
	private PezRojo pezRojo;
	private boolean errorIPs;
	private ArrayList<Alimento> alimentos;
	private ArrayList<Thread> capsulas;
	private ControlCliente cs;
	private ControlServidor servidor;
	private EscanerRed er;
	private Minim minim;
	private AudioPlayer soundtrack;
	private AudioSample burbuja, burbujaMala;

	public Logica(PApplet app) {
		this.app = app;
		minim = new Minim(app);
		cargarPantallaCarga();
		cargar = new Cargar(app);
		cargar.start();
		cargarImagenes();
		iniciarVariables();
	}

	/*
	 * Método que se encarga de inicializar las variables
	 */
	public void iniciarVariables() {
		numActualPantallaIni = 20;

		errorIPs = false;

		// Se inicia el Servidor
		servidor = new ControlServidor(this);
		new Thread(servidor).start();

		inconsolata = app.createFont("Inconsolata.oft", 17);
		app.textFont(inconsolata);

		soundtrack = minim.loadFile("../data/Musica/koipondtrack.mp3");
		soundtrack.loop();

		burbuja = minim.loadSample("../data/Musica/burbuja.mp3");
		burbujaMala = minim.loadSample("../data/Musica/burbujaMala.mp3");

		minutos = 0;
		segundos = 59;
	}

	public void cargarPantallaInicial() {
		pantallaInicial = new PImage[68];
		for (int i = 19; i < pantallaInicial.length; i++) {
			pantallaInicial[i] = app.loadImage("../data/PantallaInicial/PantallaInicial_" + i + ".png");
			if (i == 68) {
				System.out.println("Pantalla Inicial Cargada");
			}
		}
	}

	public void iniciarMundo() {
		// Se inicia el Pez Azul
		pezAzul = new PezAzul(this, app, 100, 100);
		new Thread(pezAzul).start();

		// Se inicia el Pez Rojo
		pezRojo = new PezRojo(this, app, 800, 600);
		new Thread(pezRojo).start();

		// Se crean los alimentos
		alimentos = new ArrayList<Alimento>();
		capsulas = new ArrayList<Thread>();

		for (int i = 0; i < alimentos.size(); i++) {
			capsulas.add(new Thread(alimentos.get(i)));
			if (alimentos.get(i) != null) {
				capsulas.get(i).start();
			}
		}

		// Se inicia el escaner
		er = new EscanerRed();
		er.addObserver(this);
		new Thread(er).start();
	}

	public void cargarPantallaCarga() {
		pantallaCarga = new PImage[18];
		for (int i = 0; i < pantallaCarga.length; i++) {
			pantallaCarga[i] = app.loadImage("../data/PantallaCarga/cargando_" + i + ".png");
		}
	}

	public void cargarImagenes() {
		fondo = app.loadImage("../data/fondo/Fondo.png");
		agua = app.loadImage("../data/fondo/Agua.png");
		planta = app.loadImage("../data/fondo/Planta.png");
		instrucciones = app.loadImage("../data/Instrucciones/Instrucciones.png");
		ganadorAzul = app.loadImage("../data/PantallaFinal/AzulGanador.png");
		ganadorRojo = app.loadImage("../data/PantallaFinal/RojoGanador.png");
		empate = app.loadImage("../data/PantallaFinal/Empate.png");
	}

	public void pantallas() {
		switch (pantallas) {
		case 0:
			pintarPantallaCarga();
			if (cargar.isCargaFinalizada()/* &&numActualCarga==18 */) {
				pantallaInicial = cargar.getPantallaInicial();
				pantallas = 1;
			}
			break;
		case 1:
			pintarPantallaInicial();
			break;
		case 2:
			app.image(instrucciones, app.width / 2, app.height / 2);
			break;
		case 3:
			app.image(fondo, app.width / 2, app.height / 2);
			for (int i = 0; i < alimentos.size(); i++) {
				alimentos.get(i).pintar();
			}
			pezAzul.pintar();
			pezRojo.pintar();
			pezRojo.mover();
			restarTiempo();
			comer();
			choque();
			if (errorIPs) {
				adicionarAlimento();
			}
			app.image(agua, app.width / 2, app.height / 2);
			app.image(planta, app.width / 2, app.height / 2);
			app.text(minutos + ":" + segundos, 835, 29);
			break;
		case 4:
			app.image(ganadorAzul, app.width / 2, app.height / 2);
			break;
		case 5:
			app.image(ganadorRojo, app.width / 2, app.height / 2);
			break;
		case 6:
			app.image(empate, app.width / 2, app.height / 2);
			break;
		}
	}

	public void pintarPantallaInicial() {
		app.image(pantallaInicial[numActualPantallaIni], app.width / 2, app.height / 2);
		if (app.frameCount % 5 == 0) {
			numActualPantallaIni++;
			if (numActualPantallaIni >= 68) {
				numActualPantallaIni = 35;
			}
		}
	}

	public void pintarPantallaCarga() {
		app.image(pantallaCarga[numActualCarga], app.width / 2, app.height / 2);
		if (app.frameCount % 5 == 0) {
			numActualCarga++;
			if (numActualCarga >= 18) {
				numActualCarga = 0;
			}
		}
	}

	// --------------------------UPDATE--------------------------//
	@Override
	public synchronized void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

		if (o instanceof EscanerRed && arg instanceof String) {
			String ip = (String) arg;
			if (ip.contains(".")) {
				int random = (int) app.random(2);
				switch (random) {
				case 0:
					alimentos.add(new AlimentoBueno(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));
					break;
				case 1:
					alimentos.add(new AlimentoMalo(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));
					break;

				}
			}

			if (ip.equals("error")) {
				errorIPs = true;
			}

		}

	}

	public void comer() {

		// Pez Azul
		for (int i = 0; i < alimentos.size(); i++) {
			if (PApplet.dist(pezAzul.getPosX(), pezAzul.getPosY(), alimentos.get(i).getPosX(),
					alimentos.get(i).getPosY()) < 30) {
				if (alimentos.get(i) instanceof AlimentoBueno) {
					alimentos.remove(i);
					burbuja.trigger();
					pezAzul.setTam(pezAzul.getTam() + 10);
				}
				if (alimentos.get(i) instanceof AlimentoMalo) {
					alimentos.remove(i);
					burbujaMala.trigger();
					pezAzul.setTam(pezAzul.getTam() - 10);
				}
			}
		}
		// Pez Rojo
		for (int i = 0; i < alimentos.size() - 1; i++) {
			if (PApplet.dist(pezRojo.getPosX(), pezRojo.getPosY(), alimentos.get(i).getPosX(),
					alimentos.get(i).getPosY()) < 30) {
				if (alimentos.get(i) instanceof AlimentoBueno) {
					alimentos.remove(i);
					burbuja.trigger();
					pezRojo.setTam(pezRojo.getTam() + 10);
				}
				if (alimentos.get(i) instanceof AlimentoMalo) {
					alimentos.remove(i);
					burbujaMala.trigger();
					pezRojo.setTam(pezRojo.getTam() - 10);
				}
			}
		}

	}

	public void choque() {
		if (PApplet.dist(pezRojo.getPosX(), pezRojo.getPosY(), pezAzul.getPosX(), pezAzul.getPosY()) < 80) {
			if (pezRojo.getTam() < pezAzul.getTam()) {
				pezRojo.setTam(pezRojo.getTam() - 10);
			}
			if (pezAzul.getTam() < pezRojo.getTam()) {
				pezAzul.setTam(pezAzul.getTam() - 10);
			}
		}
	}

	public void adicionarAlimento() {
		if (app.frameCount % 30 == 0) {
			int random = (int) app.random(2);
			switch (random) {
			case 0:
				alimentos.add(new AlimentoBueno(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));
				break;
			case 1:
				alimentos.add(new AlimentoMalo(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));
				break;

			}
		}
	}

	public void restarTiempo() {
		if (app.frameCount % 60 == 0) {
			if (segundos >= 0) {
				segundos--;
			}
			if(segundos <= 0) {
				if (pezRojo.getTam() > pezAzul.getTam()) {
					pantallas = 5;
				}
				if (pezAzul.getTam() > pezRojo.getTam()) {
					pantallas = 4;
				}
				if (pezAzul.getTam() == pezRojo.getTam()) {
					pantallas = 6;
				}
			}
		}
	}

	public void keyPressed() {
		switch (pantallas) {
		case 1:
			if (app.key == app.ENTER) {
				pantallas = 2;
			}
			break;
		case 2:
			if (app.key == app.ENTER) {
				pantallas = 3;
				iniciarMundo();
			}
			break;
		case 3:
			// iniciarVariables();
			// pezAzul.keyPressed();
			// pezRojo.keyPressed();
			// System.out.println("pantalla: " + pantallas);
			//
			break;
		case 4:
			if (app.key == app.ENTER) {
				pantallas = 1;
			}
			break;
		case 5:
			if (app.key == app.ENTER) {
				pantallas = 1;
			}
			break;
		case 6:
			if (app.key == app.ENTER) {
				pantallas = 1;
			}
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

	public ControlCliente getCs() {
		return cs;
	}

	public void setCs(ControlCliente cs) {
		this.cs = cs;
	}

	public PezRojo getPezRojo() {
		return pezRojo;
	}

	public void setPezRojo(PezRojo pezRojo) {
		this.pezRojo = pezRojo;
	}

	public PezAzul getPezAzul() {
		return pezAzul;
	}

	public void setPezAzul(PezAzul pezAzul) {
		this.pezAzul = pezAzul;
	}

	// -------------FINAL------------//
}
