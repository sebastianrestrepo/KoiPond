import processing.core.PApplet;

public class AlimentoBueno extends Alimento {

	public AlimentoBueno(Logica log, PApplet app, int posX, int posY) {
		super(log, app, posX, posY);
		alimento = log.getCargar().getAlimentoBueno();
	}

	//Método para pintar el alimento
	public void pintar() {
		app.image(alimento[numFrame], posX, posY);
		calcular();
	}

	//Método para calcular los frames de la animación del alimento
	public void calcular() {
		if (app.frameCount % 2 == 0) {
			numFrame++;
		}
		if (numFrame >= 48) {
			numFrame = 0;
		}
	}

	// ---------FINAL CLASE ALIMENTO--------//
}
