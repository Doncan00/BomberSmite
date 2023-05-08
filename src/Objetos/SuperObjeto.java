package Objetos;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.AdminitradorJuego;

public class SuperObjeto {

	public BufferedImage imagen,imagen2;
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
		
				g2.drawImage(imagen2, PantallaX, PantallaY, 25, 25, null);
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
