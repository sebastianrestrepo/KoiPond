import processing.core.PApplet;
import processing.core.PImage;

public abstract class Alimento implements Runnable {

	protected Logica log;
	protected PApplet app;
	protected int posX, posY;
	protected PImage [] alimento;
	protected int numFrame;
	
	public Alimento(Logica log, PApplet app, int posX, int posY) {
		this.log = log;
		this.app = app;
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(66);
			calcular();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public abstract void pintar();
	
	public abstract void calcular();
	
	
	//---------FINAL CLASE ALIMENTO-------//
}
