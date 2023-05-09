package Objetos;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.AdminitradorJuego;

public class SuperObjeto {

	public BufferedImage imagen,centro,arriba,abajo,derecha,izquierda,horizontal,vertical;
	public String nombre;
	public boolean colision = false;
	public int MundoX, MundoY,ExplX,ExplY;
	public Rectangle hitBox = new Rectangle(0,0,25,25);
	int PantallaX;
	int PantallaY;
	
	public void draw (Graphics2D g2, AdminitradorJuego admJuego) {
		
		PantallaX = ((MundoX - admJuego.jugador.hitBoxX) / admJuego.tamPantalla) * admJuego.tamPantalla;
		PantallaY = ((MundoY - admJuego.jugador.hitBoxY) / admJuego.tamPantalla) * admJuego.tamPantalla;
		
		g2.drawImage(imagen, PantallaX, PantallaY, 25, 25, null);
	}
	
	public void draw2 (Graphics2D g2, AdminitradorJuego admJuego) {
		
		PantallaX = ((MundoX - admJuego.jugador.hitBoxX) / admJuego.tamPantalla) * admJuego.tamPantalla;
		PantallaY = ((MundoY - admJuego.jugador.hitBoxY) / admJuego.tamPantalla) * admJuego.tamPantalla;

		//Centro
		g2.drawImage(centro, PantallaX, PantallaY, 25, 25, null);
		
		//Explosion  nivel 2
		if  (admJuego.nivelExpl == 2) {
			g2.drawImage(derecha, PantallaX + admJuego.tamPantalla, PantallaY, 25, 25, null);
			g2.drawImage(izquierda, PantallaX - admJuego.tamPantalla, PantallaY, 25, 25, null);
			g2.drawImage(arriba, PantallaX, PantallaY - admJuego.tamPantalla , 25, 25, null);
			g2.drawImage(abajo, PantallaX, PantallaY + admJuego.tamPantalla, 25, 25, null);
		}
		//Explosion nivel 3
		if  (admJuego.nivelExpl == 3) {
			g2.drawImage(horizontal, PantallaX + admJuego.tamPantalla, PantallaY, 25, 25, null);
			g2.drawImage(horizontal, PantallaX - admJuego.tamPantalla, PantallaY, 25, 25, null);
			g2.drawImage(vertical, PantallaX, PantallaY - admJuego.tamPantalla , 25, 25, null);
			g2.drawImage(vertical, PantallaX, PantallaY + admJuego.tamPantalla, 25, 25, null);
			
			g2.drawImage(derecha, PantallaX + admJuego.tamPantalla*2, PantallaY, 25, 25, null);
			g2.drawImage(izquierda, PantallaX - admJuego.tamPantalla*2, PantallaY, 25, 25, null);
			g2.drawImage(arriba, PantallaX, PantallaY - admJuego.tamPantalla *2, 25, 25, null);
			g2.drawImage(abajo, PantallaX, PantallaY + admJuego.tamPantalla*2, 25, 25, null);
			
		}
		//				System.out.println(PantallaX+"         "+PantallaY);
		//				g2.drawImage(imagen2,)
	}


	public int getExplX() {
		return ExplX;
	}


	public void setExplX(int explX) {
		ExplX = explX;
	}


	public int getExplY() {
		return ExplY;
	}


	public void setExplY(int explY) {
		ExplY = explY;
	}
	
	
}
