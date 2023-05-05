package Main;
import Objetos.OBJ_Bomba;

public class AssetSetter {

	AdminitradorJuego admJuego;
	
	public AssetSetter(AdminitradorJuego admJuego) {
		this.admJuego = admJuego;
	}
	
	public void colocarObjeto() {
		admJuego.obj[0] = new OBJ_Bomba();
		admJuego.obj[0].MundoX = 3 * admJuego.tamPantalla;
		admJuego.obj[0].MundoY = 3 * admJuego.tamPantalla;
		
		admJuego.obj[1] = new OBJ_Bomba();
		admJuego.obj[1].MundoX = 4 * admJuego.tamPantalla;
		admJuego.obj[1].MundoY = 17 * admJuego.tamPantalla;
	}

}
