import processing.core.PApplet;
import processing.core.PImage;

public abstract class Alimento {

	protected Logica log;
	protected PApplet app;
	protected int posX, posY;
	protected PImage [] alimento;
	protected int numFrame;
	protected int tam;
	
	public Alimento(Logica log, PApplet app, int posX, int posY) {
		this.log = log;
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		tam = (int) app.random(10, 60);
	}


	public abstract void pintar();
	
	public abstract void calcular();

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
	//---------FINAL CLASE ALIMENTO-------//
}
