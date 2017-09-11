import java.net.*;

public class EscanerRed {

	private static InetAddress address;
	private static InetAddress [] numeroIPs;
	private byte [] ip;
	
	public EscanerRed() {
		
		try {
			address= InetAddress.getByAddress(ip);
			//System.out.println("Host Local: " + hostLocal.toString());
			numeroIPs = InetAddress.getAllByName("192.168.0.11");
			System.out.println("El número de IP's conectadas es: " + numeroIPs.length);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
