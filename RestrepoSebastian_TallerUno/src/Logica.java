import processing.core.PApplet;
import processing.core.PImage;

public class Logica {

	private PApplet app;
	private Cargar cargar;
	private int pantallas;
	private PImage [] pantallaInicial;
	private int numActualPantallaIni;
	
	public Logica(PApplet app) {
		this.app = app;
		cargar = new Cargar(app);
		cargarPantallaInicial();
	}
	
	public void cargarPantallaInicial() {
		pantallaInicial = new PImage[78];
		for (int i = 0; i < pantallaInicial.length; i++) {
			pantallaInicial[i] = app.loadImage("../data/PantallaInicial/PantallaInicial_" + i + ".png");
		}
	}
	
	public void pantallas() {
		switch(pantallas){
			case 0:
				pintarPantallaInicial();
				break;
			case 1:
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
		
	}
	
	//-------------FINAL------------//
}
