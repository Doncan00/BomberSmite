package Main;

public class HiloBomba extends Thread{

	int aux = 0;
	AdminitradorJuego aj;
	AssetSetter as = new AssetSetter(aj);
	public HiloBomba (int aux) {
		this.aux = aux;
	}
	
	public void run() {
		try {
			System.out.println("Bomb planted");
			Thread.sleep(3000);
			System.out.println(aux);
			as.explotarBomba(aux);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
