import processing.core.PApplet;
import processing.core.PImage;

public class PezAzul extends Pez {

	public PezAzul(Logica log, PApplet app, int posX, int posY) {
		super(log, app, posX, posY);
		//igualarImagenes();
		pezAdelante = log.getCargar().getPezAzulAdelante();	
		pezAtras = log.getCargar().getPezAzulAtras();
		pezDer = log.getCargar().getPezAzulDer();
		pezIzq = log.getCargar().getPezAzulIzq();
		pezQuieto = log.getCargar().getPezAzulQuieto();
		estado = 5;
	}
	
	
	public void pintar() {
		//System.out.println("pintando pez azul");
		//posY+=2;
		switch(estado) {
		case 1:
			app.image(pezQuieto[numFrame], posX, posY, pezQuieto[numFrame].width/3 + tam,
					pezQuieto[numFrame].height/3+ tam);
			break;
		case 2:
			app.image(pezAdelante[numFrame], posX, posY, pezAdelante[numFrame].width/3 + tam,
					pezAdelante[numFrame].height/3+ tam);
		//	System.out.println("pintando caso 2 pez azul");
			break;
		case 3:
			app.image(pezDer[numFrame], posX, posY, pezDer[numFrame].width/3 + tam,
					pezDer[numFrame].height/3+ tam);
			break;
		case 4:
			app.image(pezIzq[numFrame], posX, posY, pezIzq[numFrame].width/3 + tam,
					pezIzq[numFrame].height/3+ tam);
			break;
		case 5:
			app.image(pezAtras[numFrame], posX, posY, pezAtras[numFrame].width/3 + tam,
					pezAtras[numFrame].height/3+ tam);
			break;
		}
	}
	
	public void mover() {
		// Mover arriba
		if (arriba) {
			if (posY > 15) {
				estado = 2;
				posY -= 2;
				// arriba = false;
			}
		}

		// Mover a la derecha
		if (derecha) {
			if (posX < app.width - 15) {
				estado = 3;
				posX += 2;
			}
		}

		// Mover a la izquierda
		if (izquierda) {
			if (posX > 15) {
				estado = 4;
				posX -= 2;
			}
		}

		// Mover abajo
		if (abajo) {
			if (posY < app.height - 15) {
				estado = 5;
				posY += 2;
			}
		}

	}
	
	//----------FINAL DE LA CLASE PEZ AZUL-----------//
}