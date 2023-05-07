package Main;

public class HiloBomba extends Thread{

	int aux = 0;
	AdminitradorJuego aj;
	AssetSetter as;
	public HiloBomba (int aux, AssetSetter as) {
		this.aux = aux;
		this.as = as;
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
