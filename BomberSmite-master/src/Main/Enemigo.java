package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemigo {

	public int ex , ey,eh,ew;
	public int evelocidad;
	public String edireccion;

	public int enemigodireccion;
	public Rectangle ehitBox;
	public boolean eColisiOn = false;

	public String eultimadireccion;

	public int ehitBoxX,ehitBoxY;
	public int eDefaulthitBoxX,eDefaulthitBoxY;
	public BufferedImage up1, up2, edown1, down2, left1, left2, right1, right2;

	public int econtadorSprite = 0;
	public int enumeroSprite = 1;

	AdminitradorJuego aj;
	Controles eteclas;


		public void setEx(int ex){
			this.ex=ex;
		}
		public void setEy(int ey){
			this.ey=ey;
		}
	public Enemigo(AdminitradorJuego aj) {
		
		this.aj = aj;

		ehitBox = new Rectangle(0, 0, 24, 24);
		
		eDefaulthitBoxX = ehitBox.x;
		eDefaulthitBoxY = ehitBox.y;
		
		ehitBoxX = ehitBox.x;
		ehitBoxY = ehitBox.y;
		
		posicionBaseEnemigo();
		getImagenesEnemigo();
	}
	public void posicionBaseEnemigo() {
		
		ex = 25;
		ey = 25;
		ew=24;
		eh=24;
		evelocidad = 1;
		edireccion = "abajo";
		
	}
	
	public void getImagenesEnemigo() {
		 try {

			 edown1 = ImageIO.read(getClass().getResourceAsStream("/enemigos/frente.png"));
			 /*up1 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_ARRIBA_1.png"));
			 left1 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_IZQUIERDA_1.png"));
			 right1 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_DERECHA_1.png"));
*/
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
	}
	
	public void actualizarenemigo() {
		


			eColisiOn = false;
			aj.cColision.revisarTile(this);


		if(eColisiOn) {

			if (enemigodireccion == 0) {
				edireccion = "arriba";
			} else if (enemigodireccion == 1) {
				edireccion = "abajo";
			} else if (enemigodireccion == 2) {
				edireccion = "izquierda";
			} else if (enemigodireccion == 3) {
				edireccion = "derecha";
			}
		}
			
			if(!eColisiOn) {
				
				switch(edireccion) {
				case "arriba":
					ey -= evelocidad;
					break;
				case "abajo":
					ey += evelocidad;
					break;
				case "izquierda":
					ex -= evelocidad;
					break;
				case "derecha":
					ex += evelocidad;
					break;
				}
				eultimadireccion=edireccion;
			}
			
		}
		


	
	public void dibujar(Graphics2D g2) {
		

		/*g2.setColor(Color.white);
		g2.fillRect(x, y, aj.tamPantalla-10, aj.tamPantalla-10);*/
		
		BufferedImage imagen = null;
		
		switch(edireccion) {
		case "arriba":

			imagen = edown1;

			break;
		case "abajo":
				imagen = edown1;

			break;
		case "izquierda":
				imagen = edown1;

			break;
		case "derecha":
				imagen = edown1;

			break;
		}

		g2.drawImage(imagen, ex, ey, 25, 25, null);
	//	g2.fillRect(ex,ey,25,25);
	}



}
