import processing.core.PApplet;
import processing.core.PImage;

public class Logica {

	private PApplet app;
	private Cargar cargar;
	private int pantallas;
	private PImage[] pantallaInicial;
	private PImage fondo, agua;
	private int numActualPantallaIni;
	private PezAzul pezAzul;

	public Logica(PApplet app) {
		this.app = app;
		cargar = new Cargar(app);
		cargarPantallaInicial();
		cargarImagenes();
		pezAzul = new PezAzul(this, app, 150, 150);
		Thread hiloPezAzul = new Thread(pezAzul);
		hiloPezAzul.start();
	}

	public void iniciarVariables() {

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
			app.image(fondo, app.width/2, app.height/2);
			pezAzul.pintar();
			//app.image(agua, app.width/2, app.height/2);
			break;
		}
	}

	public void pintarPantallaInicial() {
		app.image(pantallaInicial[numActualPantallaIni], app.width / 2, app.height / 2);
		if (app.frameCount % 5 == 0) {
			numActualPantallaIni++;
			if (numActualPantallaIni >= 78) {
				numActualPantallaIni = 32;
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
			//System.out.println("pantalla: " + pantallas);
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

	// -------------FINAL------------//
}
