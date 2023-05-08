package Objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Fuego extends SuperObjeto{

	public OBJ_Fuego() {
		// TODO Auto-generated constructor stub
		nombre = "Fuego";
		try {
			imagen2 = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/Explosion.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
