import processing.core.PApplet;

public class MainAppKoiPond extends PApplet {

	private Logica log;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("MainAppKoiPond");
		System.setProperty("java.net.preferIPv4Stack", "true");
	}
	
	public void settings() {
		size(900, 700);
	}
	
	public void setup() {
		log = new Logica(this);
		log.cargarPantallaInicial();
		imageMode(CENTER);
	}
	
	public void draw() {
		log.pantallas();
	}

	public void keyPressed() {
		log.keyPressed();
	}
	
	public void mouseClicked() {
		println(mouseX, mouseY);
	}

	//-------------FINAL------------//
}
