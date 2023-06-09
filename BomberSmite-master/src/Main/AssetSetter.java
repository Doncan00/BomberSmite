package Main;
import Objetos.OBJ_Bomba;
import Objetos.OBJ_Fuego;

public class AssetSetter {

	AdminitradorJuego admJuego;
	public boolean expl[] = new boolean[4];
	public Tile[] tile;
	
	public AssetSetter(AdminitradorJuego admJuego) {
		this.admJuego = admJuego;
		tile = new Tile[10];
	}
	
	public int colocarObjeto() {
		int jugadorX = admJuego.jugador.x +4;
		int jugadorY = admJuego.jugador.y +8;
		int cont = 0;
		for (int i=1;i<admJuego.obj.length;i++) {
			for (int j=1;j<admJuego.obj.length;j++) {
				if (admJuego.obj[j] != null) {
					if ((jugadorX / admJuego.tamPantalla+1) == (admJuego.obj[j].MundoX / admJuego.tamPantalla) &&
							(jugadorY / admJuego.tamPantalla+1) == (admJuego.obj[j].MundoY / admJuego.tamPantalla)) {
						cont++;
					}
				}
			}
			if (admJuego.obj[i] == null && cont == 0) {
				int x = (jugadorX)/ admJuego.tamPantalla +1;
				int y = (jugadorY)/ admJuego.tamPantalla +1;
				admJuego.obj[i] = new OBJ_Bomba();
				admJuego.obj[i].MundoX = (x * admJuego.tamPantalla);
				admJuego.obj[i].MundoY = (y * admJuego.tamPantalla);
				cont=0;
				return i;
			}
		}
		return 0;
	}
	
	public void explotarBomba(int aux) {
		
		int x; 
		int y; 
		
		System.out.println(admJuego.obj[aux].MundoX);
		x= admJuego.obj[aux].MundoX - ((admJuego.nivelExpl-1) * admJuego.tamPantalla);
		y= admJuego.obj[aux].MundoY;

		System.out.println("x: "+x+" y: "+y);
		admJuego.obj2[aux] = new OBJ_Fuego();
		admJuego.obj2[aux].MundoX = x;
		admJuego.obj2[aux].MundoY = y;
		
		x= admJuego.obj[aux].MundoX;
		y= admJuego.obj[aux].MundoY - ((admJuego.nivelExpl-1) * admJuego.tamPantalla);
		System.out.println("x: "+x+" y: "+y);
		admJuego.obj3[aux] = new OBJ_Fuego();
		admJuego.obj3[aux].MundoX = x;
		admJuego.obj3[aux].MundoY = y;
		HiloExplosion hiloExpl = new HiloExplosion(aux,x,y,admJuego);
		hiloExpl.start();
	}
	
	public void borrarBomba (int aux) {
		if (aux !=  999) {
			admJuego.obj[aux] = null;			
		}
	}
}
