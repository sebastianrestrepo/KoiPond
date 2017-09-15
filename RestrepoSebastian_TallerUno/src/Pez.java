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
	protected boolean arriba, abajo, derecha, izquierda;

	public Pez(Logica log, PApplet app, int posX, int posY) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		tam = 100;
		vivo = true;
		numFrame = 1;
		arriba = false;
		abajo = false;
		derecha = false;
		izquierda = false;
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

	
	//-------------GETTERS Y SETTERS----------//
	public boolean isArriba() {
		return arriba;
	}

	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public void setAbajo(boolean abajo) {
		this.abajo = abajo;
	}

	public boolean isDerecha() {
		return derecha;
	}

	public void setDerecha(boolean derecha) {
		this.derecha = derecha;
	}

	public boolean isIzquierda() {
		return izquierda;
	}

	public void setIzquierda(boolean izquierda) {
		this.izquierda = izquierda;
	}
	
	
	
//-----------FINAL DE LA CLASE PEZ--------//
}
