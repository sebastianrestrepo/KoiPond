import processing.core.PApplet;

public class AlimentoBueno extends Alimento {

	public AlimentoBueno(Logica log, PApplet app, int posX, int posY) {
		super(log, app, posX, posY);
		alimento = log.getCargar().getAlimentoBueno();
	}

	public void pintar() {

		app.image(alimento[numFrame], posX, posY);
		calcular();
	}

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
