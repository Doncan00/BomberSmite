package Main;

import Objetos.SuperObjeto;

public class HiloExplosion extends Thread{

	int aux = 0;
	AdminitradorJuego aj;
	SuperObjeto hb;
	public HiloExplosion (int aux,int x, int y,AdminitradorJuego aj) {
		this.aux = aux;
		this.aj = aj;
		
	}
	
	public void run() {
		try {
			
			aj.expl[aux] = true;
			Thread.sleep(1000);
			aj.expl[aux] = false;
			aj.aSetter.borrarBomba(aux);
			aj.obj2[aux] = null;
			aj.obj3[aux] = null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
