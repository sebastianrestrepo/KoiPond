import processing.core.PApplet;
import processing.core.PImage;

public class Cargar {

	private PApplet app;
	//private PImage img;
	private PImage [] pezAzulQuieto, pezAzulAdelante, pezAzulDer, pezAzulIzq, pezAzulAtras, pezRojoQuieto, pezRojoAdelante, pezRojoDer, pezRojoIzq, pezRojoAtras;
	private PImage [] alimentoBueno, alimentoMalo;
	
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
		cargarAlimentoBueno();
		cargarAlimentoMalo();
		System.out.println("Imágenes cargadas");
	}
	
	//-----------Cargar Alimento------------//
	public void cargarAlimentoBueno() {
		
		alimentoBueno = new PImage[48];
		for (int i = 0; i < alimentoBueno.length; i++) {
			alimentoBueno[i] = app.loadImage("../data/Alimento/AlimentoBueno/AlimentoBueno_" + i + ".png");
		}
	
	}
	
	public void cargarAlimentoMalo() {
		
		alimentoMalo = new PImage[49];
		for (int i = 0; i < alimentoMalo.length; i++) {
			alimentoMalo[i] = app.loadImage("../data/Alimento/AlimentoMalo/AlimentoMalo_" + i + ".png");
		}
		
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
			System.out.println(pezAzulAdelante[i]);
		}
		System.out.println("Se cargó el Pez AzulAdelante ");
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

	
	//-----------GETTERS Y SETTERS----------//
	
	public PImage[] getPezAzulQuieto() {
		return pezAzulQuieto;
	}

	public void setPezAzulQuieto(PImage[] pezAzulQuieto) {
		this.pezAzulQuieto = pezAzulQuieto;
	}

	public PImage[] getPezAzulAdelante() {
		return pezAzulAdelante;
	}

	public void setPezAzulAdelante(PImage[] pezAzulAdelante) {
		this.pezAzulAdelante = pezAzulAdelante;
	}

	public PImage[] getPezAzulDer() {
		return pezAzulDer;
	}

	public void setPezAzulDer(PImage[] pezAzulDer) {
		this.pezAzulDer = pezAzulDer;
	}

	public PImage[] getPezAzulIzq() {
		return pezAzulIzq;
	}

	public void setPezAzulIzq(PImage[] pezAzulIzq) {
		this.pezAzulIzq = pezAzulIzq;
	}

	public PImage[] getPezAzulAtras() {
		return pezAzulAtras;
	}

	public void setPezAzulAtras(PImage[] pezAzulAtras) {
		this.pezAzulAtras = pezAzulAtras;
	}

	public PImage[] getPezRojoQuieto() {
		return pezRojoQuieto;
	}

	public void setPezRojoQuieto(PImage[] pezRojoQuieto) {
		this.pezRojoQuieto = pezRojoQuieto;
	}

	public PImage[] getPezRojoAdelante() {
		return pezRojoAdelante;
	}

	public void setPezRojoAdelante(PImage[] pezRojoAdelante) {
		this.pezRojoAdelante = pezRojoAdelante;
	}

	public PImage[] getPezRojoDer() {
		return pezRojoDer;
	}

	public void setPezRojoDer(PImage[] pezRojoDer) {
		this.pezRojoDer = pezRojoDer;
	}

	public PImage[] getPezRojoIzq() {
		return pezRojoIzq;
	}

	public void setPezRojoIzq(PImage[] pezRojoIzq) {
		this.pezRojoIzq = pezRojoIzq;
	}

	public PImage[] getPezRojoAtras() {
		return pezRojoAtras;
	}

	public void setPezRojoAtras(PImage[] pezRojoAtras) {
		this.pezRojoAtras = pezRojoAtras;
	}

	public PImage[] getAlimentoBueno() {
		return alimentoBueno;
	}

	public void setAlimentoBueno(PImage[] alimentoBueno) {
		this.alimentoBueno = alimentoBueno;
	}

	public PImage[] getAlimentoMalo() {
		return alimentoMalo;
	}

	public void setAlimentoMalo(PImage[] alimentoMalo) {
		this.alimentoMalo = alimentoMalo;
	}
	
	
	//-------------FINAL------------//
}
