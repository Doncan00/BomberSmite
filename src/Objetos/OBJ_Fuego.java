package Objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Fuego extends SuperObjeto{

	public OBJ_Fuego() {
		// TODO Auto-generated constructor stub
		nombre = "Fuego";
		try {
			centro = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/Explosion.png"));
			izquierda = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/ExplosionIzq.png"));
			derecha = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/ExplosionDer.png"));
			abajo = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/ExplosionAba.png"));
			arriba = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/ExplosionArr.png"));
			horizontal = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/ExplosionHor.png"));
			vertical = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/ExplosionVer.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
