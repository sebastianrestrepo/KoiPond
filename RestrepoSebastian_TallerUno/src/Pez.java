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
				mover();
				Thread.sleep(66);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método que se encarga de calcular los frames de la animación de los peces
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
	
	//Método que se encarga de realizar el movimiento del pez
	public void mover() {
		// Mover arriba
		if (arriba) {
			if (posY > 15) {
				estado = 2;
				posY -= 3;
				// arriba = false;
			}
		}

		// Mover a la derecha
		if (derecha) {
			if (posX < app.width - 15) {
				estado = 3;
				posX += 3;
			}
		}

		// Mover a la izquierda
		if (izquierda) {
			if (posX > 15) {
				estado = 4;
				posX -= 3;
			}
		}

		// Mover abajo
		if (abajo) {
			if (posY < app.height - 15) {
				estado = 5;
				posY += 3;
			}
		}

	}

	// -------------GETTERS Y SETTERS----------//
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

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	// -----------FINAL DE LA CLASE PEZ--------//
}
