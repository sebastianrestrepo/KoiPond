import processing.core.PApplet;

public class AlimentoMalo extends Alimento {

	public AlimentoMalo(Logica log, PApplet app, int posX, int posY) {
		super(log, app, posX, posY);
		alimento = log.getCargar().getAlimentoMalo();
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
		if (numFrame >= 49) {
			numFrame = 0;
		}
	}

	// ---------FINAL CLASE ALIMENTO--------//
}