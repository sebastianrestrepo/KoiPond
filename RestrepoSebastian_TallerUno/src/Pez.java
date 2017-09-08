import processing.core.PApplet;
import processing.core.PImage;

public class Pez implements Runnable {

	protected PApplet app;
	protected int posX;
	protected int posY;
	protected PImage[] pezAdelante, pezDer, pezIzq, pezAtras, pezQuieto;
	protected Logica log;
	protected int estado, tam, numFrame;
	protected boolean vivo;

	public Pez(Logica log, PApplet app, int posX, int posY) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		tam = 100;
		vivo = true;
		estado = 5;
		numFrame = 1;
	}

	@Override
	public void run() {
		try {
			while (vivo) {
				calculo();
				Thread.sleep(66);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void calculo() {
		switch (estado) {
		case 1:
			numFrame = 17;
			if (numFrame >= 77) {
				numFrame = 17;
			}
			break;
		case 2:
			numFrame++;
			if (numFrame >= 31) {
				numFrame = 0;
			}
			break;
		case 3:
			numFrame++;
			if (numFrame >= 31) {
				numFrame = 0;
			}
			break;
		case 4:
			numFrame++;
			if (numFrame >= 31) {
				numFrame = 0;
			}
			break;
		case 5:
			numFrame++;
			if (numFrame >= 31) {
				numFrame = 0;
			}
			break;
		}
	}
	
//-----------FINAL DE LA CLASE PEZ--------//
}
