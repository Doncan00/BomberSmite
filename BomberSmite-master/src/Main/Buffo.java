package Main;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Buffo {
	
	
	public BufferedImage imagen;
	public boolean speed1 = false,speed2 = false;
	

	public Buffo() {
		// TODO Auto-generated constructor stub
		try {
			imagen = ImageIO.read(getClass().getResourceAsStream("/ObjetosImg/Velocidad.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void draw (Graphics2D g2, AdminitradorJuego admJuego) {


		if (!speed1) {
			g2.drawImage(imagen, 430, 305, 15, 15,null);
			if ((admJuego.jugador.x > 420 &&  admJuego.jugador.x < 450)&& (admJuego.jugador.y > 295 && admJuego.jugador.y < 320)) {
				admJuego.jugador.velocidad++;
				speed1 = true;
			}
		}

		if (!speed2) {

			g2.drawImage(imagen, 55, 180, 15, 15,null);
			if ((admJuego.jugador.x > 45 && admJuego.jugador.x < 70) && (admJuego.jugador.y > 170 && admJuego.jugador.y < 190)){
				admJuego.jugador.velocidad++;
				speed2 = true;
			}
		}
	}

}
