package Objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Bomba extends SuperObjeto{

	public OBJ_Bomba() {
		// TODO Auto-generated constructor stub
		nombre = "Bomba";
		System.out.println("hola");
		try {
			imagen = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/Bomba.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
