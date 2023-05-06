package Main;
import Objetos.OBJ_Bomba;

public class AssetSetter {

	AdminitradorJuego admJuego;
	
	public AssetSetter(AdminitradorJuego admJuego) {
		this.admJuego = admJuego;
	}
	
	public void colocarObjeto() {
		int cont = 0;
		for (int i=0;i<admJuego.obj.length;i++) {
			for (int j=0;j<admJuego.obj.length;j++) {
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
				break;
			}
		}
	}
}
