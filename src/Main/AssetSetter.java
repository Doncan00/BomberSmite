package Main;
import Objetos.OBJ_Bomba;

public class AssetSetter {

	AdminitradorJuego admJuego;
	
	public AssetSetter(AdminitradorJuego admJuego) {
		this.admJuego = admJuego;
	}
	
	public int colocarObjeto() {
		int cont = 0;
		for (int i=1;i<admJuego.obj.length;i++) {
			for (int j=1;j<admJuego.obj.length;j++) {
				if (admJuego.obj[j] != null) {
					if ((admJuego.jugador.x / admJuego.tamPantalla) == (admJuego.obj[j].MundoX / admJuego.tamPantalla) ||
							(admJuego.jugador.x / admJuego.tamPantalla) == (admJuego.obj[j].MundoY / admJuego.tamPantalla)) {
						cont++;
					}
				}
			}
			if (admJuego.obj[i] == null && cont == 0) {
				int x = admJuego.jugador.x / admJuego.tamPantalla;
				int y = admJuego.jugador.y / admJuego.tamPantalla;
				admJuego.obj[i] = new OBJ_Bomba();
				admJuego.obj[i].MundoX = x * admJuego.tamPantalla;
				admJuego.obj[i].MundoY = y * admJuego.tamPantalla;
				cont=0;
				return i;
			}
		}
		return 0;
	}
	
	public void explotarBomba(int aux) {
		if (aux !=  999) {
			admJuego.obj[aux] = null;			
		}
	}
}
