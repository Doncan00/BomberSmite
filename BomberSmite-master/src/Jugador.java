package Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Jugador {
	
	public int x , y;
	public int velocidad;
	public String direccion;
	public Rectangle hitBox;
	public boolean ColisiOn = false;
	public int hitBoxX,hitBoxY;
	public int DefaulthitBoxX,DefaulthitBoxY;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	

	
	public int contadorSprite = 0;
	public int numeroSprite = 1;
	
	AdminitradorJuego aj;
	Controles teclas;
	
	public Jugador(AdminitradorJuego aj, Controles teclas) {
		
		this.aj = aj;
		this.teclas = teclas;
		
		hitBox = new Rectangle(4, 8, 15, 15);
		
		DefaulthitBoxX = hitBox.x;
		DefaulthitBoxY = hitBox.y;
		
		hitBoxX = hitBox.x;
		hitBoxY = hitBox.y;
		
		posicionBase();
		getImagenesJugador();
	}
	public void posicionBase() {
		
		x = 25;
		y = 25;
		velocidad = 2;
		direccion = "abajo";
		
	}
	
	public void getImagenesJugador() {
		 try {
			 
			 up1 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_ARRIBA_1.png"));
			 up2 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_ARRIBA_2.png"));
			 down1 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_ABAJO_1.png"));
			 down2 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_ABAJO_2.png"));
			 left1 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_IZQUIERDA_1.png"));
			 left2 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_IZQUIERDA_2.png"));
			 right1 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_DERECHA_1.png"));
			 right2 = ImageIO.read(getClass().getResourceAsStream("/jugador/BM_DERECHA_2.png"));

			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
	}
	
	public void actualizar() {
		
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
			
			contadorSprite++;
			if(contadorSprite > 12) {
				if(numeroSprite == 1) {
					numeroSprite = 2;
				}
				else if(numeroSprite == 2) {
					numeroSprite = 1;
				}
				contadorSprite = 0;
			}
			
			ColisiOn = false;
			aj.cColision.revisarTile(this);
			
			int objIndex = aj.cColision.revisarObjeto(this, true);
			
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
		
		if (teclas.bomba) {
			aj.setUpGame(); 
			teclas.bomba =false;
		}
	}
	
	public void dibujar(Graphics2D g2) {
		

		/*g2.setColor(Color.white);
		g2.fillRect(x, y, aj.tamPantalla-10, aj.tamPantalla-10);*/
		
		BufferedImage imagen = null;
		
		switch(direccion) {
		case "arriba":
			if(numeroSprite == 1) {
				imagen = up1;
			}
			if(numeroSprite == 2) {
				imagen = up2;
			}
			break;
		case "abajo":
			if(numeroSprite == 1) {
				imagen = down1;
			}
			if(numeroSprite == 2) {
				imagen = down2;
			}
			break;
		case "izquierda":
			if(numeroSprite == 1) {
				imagen = left1;
			}
			if(numeroSprite == 2) {
				imagen = left2;
			}
			break;
		case "derecha":
			if(numeroSprite == 1) {
				imagen = right1;
			}
			if(numeroSprite == 2) {
				imagen = right2;
			}
			break;
		}
		g2.drawImage(imagen, x, y, aj.tamPantalla, aj.tamPantalla, null);
	}

}
