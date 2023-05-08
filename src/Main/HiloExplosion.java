package Main;

import Objetos.SuperObjeto;

public class HiloExplosion extends Thread{

	int aux = 0,x,y;
	AdminitradorJuego aj;
	SuperObjeto hb;
	public HiloExplosion (int aux,int x, int y,AdminitradorJuego aj) {
		this.aux = aux;
		this.x = x;
		this.y = y;
		this.aj = aj;
		
	}
	
	public void run() {
		try {
			
			aj.expl[aux] = true;
			System.out.println(aj.expl[aux]);
			System.out.println(aux);
			Thread.sleep(1000);
			aj.expl[aux] = false;
			System.out.println(aj.expl[aux]);
			aj.aSetter.borrarBomba(aux);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
