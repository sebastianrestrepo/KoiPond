import processing.core.PApplet;
import processing.core.PImage;

public class Cargar {

	private PApplet app;
	//private PImage img;
	private PImage [] pezAzulQuieto, pezAzulAdelante, pezAzulDer, pezAzulIzq, pezAzulAtras, pezRojoQuieto, pezRojoAdelante, pezRojoDer, pezRojoIzq, pezRojoAtras;
	
	public Cargar(PApplet app) {
		this.app = app;
		cargarPezAzulAdelante();
		cargarPezAzulDer();
		cargarPezAzulIzq();
		cargarPezAzulAtras();
		cargarPezRojoAdelante();
		cargarPezRojoDer();
		cargarPezRojoIzq();
		cargarPezRojoAtras();
		System.out.println("Imágenes cargadas");
	}
	
	//-----------Cargar Pez Azul-----------//
	public void cargarPezAzulQuieto() {
		pezAzulQuieto = new PImage[77];
		for (int i = 17; i < pezAzulQuieto.length; i++) {
			pezAzulQuieto[i] = app.loadImage("../data/PezAzul/PezRojoQuieto/PezAzulQuieto_" + i + ".png");
		}
	}
	
	public void cargarPezAzulAdelante() {
		pezAzulAdelante = new PImage[31];
		for (int i = 0; i < pezAzulAdelante.length; i++) {
			pezAzulAdelante[i] = app.loadImage("../data/PezAzul/PezAzulAdelante/PezAzulAdelante_" + i + ".png");
		}
	}
	
	public void cargarPezAzulDer() {
		pezAzulDer = new PImage[31];
		for (int i = 0; i < pezAzulDer.length; i++) {
			pezAzulDer[i] = app.loadImage("../data/PezAzul/PezAzulDer/PezAzulDer_" + i + ".png");
		}
	}
	
	public void cargarPezAzulIzq() {
		pezAzulIzq = new PImage[31];
		for (int i = 0; i < pezAzulIzq.length; i++) {
			pezAzulIzq[i] = app.loadImage("../data/PezAzul/PezAzulIzq/PezAzulIzq_" + i + ".png");
		}
	}
	
	public void cargarPezAzulAtras() {
		pezAzulAtras = new PImage[31];
		for (int i = 0; i < pezAzulAtras.length; i++) {
			pezAzulAtras[i] = app.loadImage("../data/PezAzul/PezAzulAtras/PezAzulAtras_" + i + ".png");
		}
	}
	
	//-----------Cargar Pez Rojo-----------//
	public void cargarPezRojoQuieto() {
		pezRojoQuieto = new PImage[77];
		for (int i = 7; i < pezRojoQuieto.length; i++) {
			pezRojoQuieto[i] = app.loadImage("../data/PezRojo/PezRojoQuieto/PezRojoQuieto_" + i + ".png");
		}
	}
	
	public void cargarPezRojoAdelante() {
		pezRojoAdelante = new PImage[31];
		for (int i = 0; i < pezRojoAdelante.length; i++) {
			pezRojoAdelante[i] = app.loadImage("../data/PezRojo/PezRojoAdelante/PezRojoAdelante_" + i + ".png");
		}
	}
	
	public void cargarPezRojoDer() {
		pezRojoDer = new PImage[31];
		for (int i = 0; i < pezRojoDer.length; i++) {
			pezRojoDer[i] = app.loadImage("../data/PezRojo/PezRojoDer/PezRojoDer_" + i + ".png");
		}
	}
	
	public void cargarPezRojoIzq() {
		pezRojoIzq = new PImage[31];
		for (int i = 0; i < pezRojoIzq.length; i++) {
			pezRojoIzq[i] = app.loadImage("../data/PezRojo/PezRojoIzq/PezRojoIzq_" + i + ".png");
		}
	}
	
	public void cargarPezRojoAtras() {
		pezRojoAtras = new PImage[31];
		for (int i = 0; i < pezRojoAtras.length; i++) {
			pezRojoAtras[i] = app.loadImage("../data/PezRojo/PezRojoAtras/PezRojoAtras_" + i + ".png");
		}
	}
	
	//-------------FINAL------------//
}
