import processing.core.PApplet;

public class AlimentoBueno extends Alimento {

	
	public AlimentoBueno(Logica log, PApplet app, int posX, int posY) {
		super(log, app, posX, posY);
		alimento = log.getCargar().getAlimentoBueno();	
	}
	
	public void pintar() {
		app.image(alimento[numFrame], posX, posY);
	}
	
	public void calcular() {
		numFrame = 0;
		if (numFrame >= 48) {
			numFrame = 0;
		}
	}
	
	//---------FINAL CLASE ALIMENTO--------//
}
