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
		//er = new EscanerRed();
		cargarPantallaInicial();
		cargarImagenes();
		iniciarVariables();
	}

	public void iniciarVariables() {
		cs = new ComunicacionServidor(this);
		Thread t = new Thread(cs);
		t.start();
		//
		pezAzul = new PezAzul(this, app, 100, 100);
		Thread hiloPezAzul = new Thread(pezAzul);
		hiloPezAzul.start();
		//
		pezRojo = new PezRojo(this, app, 800, 600);
		Thread hiloPezRojo = new Thread(pezRojo);
		hiloPezRojo.start();
		//
		alimentos = new ArrayList<Alimento>();
		capsulas = new ArrayList<Thread>();
		alimentos.add(new AlimentoBueno(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));
		alimentos.add(new AlimentoMalo(this, app, (int) app.random(0, 900), (int) app.random(0, 700)));

		for (int i = 0; i < alimentos.size(); i++) {
			capsulas.add(new Thread(alimentos.get(i)));
			if (alimentos.get(i) != null) {
				capsulas.get(i).start();
			}
		}
		//
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
			//pezRojo.mover();
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
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		String mensaje = (String) arg;
		System.out.println("[notificación: " + mensaje + "]");
		if(mensaje.equals("arriba")) {
			pezRojo.moverArriba();
		}
		
		String mensajeDos = (String) arg;
		System.out.println("[notificación: " + mensajeDos + "]");
		if(mensajeDos.equals("derecha")) {
			pezRojo.moverDerecha();
		}
		
		String mensajeTres = (String) arg;
		System.out.println("[notificación: " + mensajeTres + "]");
		if(mensajeTres.equals("izquierda")) {
			pezRojo.moverIzquierda();
		}
		
		String mensajeCuatro = (String) arg;
		System.out.println("[notificación: " + mensajeCuatro + "]");
		if(mensajeCuatro.equals("abajo")) {
			pezRojo.moverAbajo();
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
