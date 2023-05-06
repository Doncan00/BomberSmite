package Objetos;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.AdminitradorJuego;

public class SuperObjeto {

	public BufferedImage imagen;
	public String nombre;
	public boolean colision = false;
	public int MundoX, MundoY;
	public Rectangle hitBox = new Rectangle(0,0,25,25);
	
	
	public void draw (Graphics2D g2, AdminitradorJuego admJuego) {
		
		int PantallaX = MundoX - admJuego.jugador.hitBoxX;
		int PantallaY = MundoY - admJuego.jugador.hitBoxY;
		
		g2.drawImage(imagen, PantallaX, PantallaY, 25, 25, null);
	}
}
