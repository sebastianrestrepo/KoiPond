import processing.core.PApplet;
import processing.core.PImage;

public class PezRojo extends Pez {

	public PezRojo(Logica log, PApplet app, int posX, int posY) {
		super(log, app, posX, posY);
		// igualarImagenes();
		pezAdelante = log.getCargar().getPezRojoAdelante();
		pezAtras = log.getCargar().getPezRojoAtras();
		pezDer = log.getCargar().getPezRojoDer();
		pezIzq = log.getCargar().getPezRojoIzq();
		pezQuieto = log.getCargar().getPezRojoQuieto();
		estado = 2;
	}

	//Método que se encarga de pintar las imagenes del pez dependiendo de su estado
	public void pintar() {
		switch (estado) {
		case 1:
			app.image(pezQuieto[numFrame], posX, posY, pezQuieto[numFrame].width / 3 + tam,
					pezQuieto[numFrame].height / 3 + tam);
			break;
		case 2:
			app.image(pezAdelante[numFrame], posX, posY, pezAdelante[numFrame].width / 3 + tam,
					pezAdelante[numFrame].height / 3 + tam);
			// System.out.println("pintando caso 2 pez azul");
			break;
		case 3:
			app.image(pezDer[numFrame], posX, posY, pezDer[numFrame].width / 3 + tam,
					pezDer[numFrame].height / 3 + tam);
			break;
		case 4:
			app.image(pezIzq[numFrame], posX, posY, pezIzq[numFrame].width / 3 + tam,
					pezIzq[numFrame].height / 3 + tam);
			break;
		case 5:
			app.image(pezAtras[numFrame], posX, posY, pezAtras[numFrame].width / 3 + tam,
					pezAtras[numFrame].height / 3 + tam);
			break;
		}
	}

	//---------------GETTERS Y SETTERS----------------//
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

	// ----------FINAL---------//
}
