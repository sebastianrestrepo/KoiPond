import processing.core.PApplet;
import processing.core.PImage;

public class PezRojo extends Pez {

	public PezRojo(Logica log, PApplet app, int posX, int posY) {
		super(log, app, posX, posY);
		//igualarImagenes();
		pezAdelante = log.getCargar().getPezRojoAdelante();	
		pezAtras = log.getCargar().getPezRojoAtras();
		pezDer = log.getCargar().getPezRojoDer();
		pezIzq = log.getCargar().getPezRojoIzq();
		pezQuieto = log.getCargar().getPezRojoQuieto();
	}
	
	public void pintar() {
		//System.out.println("pintando pez azul");
		switch(estado) {
		case 1:
			app.image(pezQuieto[numFrame], posX, posY, pezQuieto[numFrame].width/3 + tam,
					pezQuieto[numFrame].height/3+ tam);
			break;
		case 2:
			app.image(pezAdelante[numFrame], posX, posY, pezAdelante[numFrame].width/3 + tam,
					pezAdelante[numFrame].height/3+ tam);
			System.out.println("pintando caso 2 pez azul");
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
	
	public void keyPressed() {
		if (app.keyCode == app.UP) {
			estado = 2;
		//	if(app.frameCount%2==0)
			posY-=10;
		}
		if (app.keyCode == app.RIGHT) {
			estado = 3;
		//	if(app.frameCount%2==0)
			posX+=10;
		}
		
		if (app.keyCode == app.LEFT) {
			estado = 4;
		//	if(app.frameCount%2==0)
			posX-=10;
		}
		if (app.keyCode == app.DOWN) {
			estado = 5;
		//	if(app.frameCount%2==0)
			posY+=10;
			System.out.println("keyPressed DOWN");
		}
	}
	
	
	//----------FINAL---------//
}
