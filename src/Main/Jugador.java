package Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Jugador {
	
	public int x , y;
	public int velocidad;
	public String direccion;
	public Rectangle hitBox;
	public boolean ColisiOn = false;
	public int hitBoxX,hitBoxY;
	
	AdminitradorJuego aj;
	Controles teclas;
	
	public Jugador(AdminitradorJuego aj, Controles teclas) {
		
		this.aj = aj;
		this.teclas = teclas;
		
		hitBox = new Rectangle(0, 0, 15, 15);
		
		hitBoxX = hitBox.x;
		hitBoxY = hitBox.y;
		
		posicionBase();
	}
	public void posicionBase() {
		
		x = 27;
		y = 27;
		velocidad = 3;
		direccion = "abajo";
		
	}
	
	public void actualizar() {
		
		if(x >= 957 && y >= 459 ) {
			JOptionPane.showMessageDialog(null, "HAS COMPLETADO EL LABERINTO", "FELICIDADES", JOptionPane.PLAIN_MESSAGE);
			x = 27;
			y = 27;
		}
		
		if(teclas.arriba == true || teclas.abajo == true ||
				teclas.izqui == true || teclas.dere == true) {
			
			if(teclas.arriba == true) {
				direccion = "arriba";
			}
			else if(teclas.abajo == true) {
				direccion = "abajo";
			}
			else if(teclas.izqui == true) {
				direccion = "izquierda";
			}
			else if(teclas.dere == true) {
				direccion = "derecha";
			}
			
			ColisiOn = false;
			aj.cColision.revisarTile(this);
			
			if(ColisiOn == false) {
				
				switch(direccion) {
				case "arriba":
					y -= velocidad;
					break;
				case "abajo":
					y += velocidad;
					break;
				case "izquierda":
					x -= velocidad;
					break;
				case "derecha":
					x += velocidad;
					break;
				}
			}
			
		}
	}
	
	public void dibujar(Graphics2D g2) {
		
		g2.setColor(Color.white);
		g2.fillRect(x, y, aj.tamPantalla-10, aj.tamPantalla-10);
	}

}
