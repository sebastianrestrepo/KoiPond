import processing.core.PApplet;

public class AlimentoMalo extends Alimento {

	
	public AlimentoMalo(Logica log, PApplet app, int posX, int posY) {
		super(log, app, posX, posY);
		alimento = log.getCargar().getAlimentoMalo();	
	}
	
	public void pintar() {
		app.image(alimento[numFrame], posX, posY);
	}
	
	public void calcular() {
		numFrame = 0;
		if (numFrame >= 49) {
			numFrame = 0;
		}
	}
	
	
	//---------FINAL CLASE ALIMENTO--------//
}