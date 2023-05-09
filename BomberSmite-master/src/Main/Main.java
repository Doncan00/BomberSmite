package Main;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stubdddd
		JFrame ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setTitle("BomberSmite");
		
		AdminitradorJuego aJ = new AdminitradorJuego();
		ventana.setSize(1015, 540);
		ventana.add(aJ);
		
//		aJ.setUpGame();
		
		aJ.iniciarHiloJuego();
		
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
	}
}
