package Main;

import java.util.Random;

public class Colision {
	
	AdminitradorJuego aj;
	
	public Colision(AdminitradorJuego aj) {
		
		this.aj = aj;
		
	}
	
	public void revisarTile(Jugador jugador) {
		
		int jugadorIzquiX = jugador.x + jugador.hitBox.x;
		int jugadorDereX = jugador.x + jugador.hitBox.x + jugador.hitBox.width;
		
		int jugadorArribaY = jugador.y + jugador.hitBox.y;
		int jugadorAbajoY = jugador.y + jugador.hitBox.y + jugador.hitBox.height;
		
		int jugadorIzquiCol = jugadorIzquiX/aj.tamPantalla;
		int jugadorDereCol = jugadorDereX/aj.tamPantalla;
		int jugadorArribaFila = jugadorArribaY/aj.tamPantalla;
		int jugadorAbajoFila = jugadorAbajoY/aj.tamPantalla;
		
		int tileNum1, tileNum2;
		
		switch(jugador.direccion) {
		case "arriba":
			jugadorArribaFila = (jugadorArribaY - jugador.velocidad)/aj.tamPantalla;
			tileNum1 = aj.ControladorT.numTileMap[jugadorIzquiCol][jugadorArribaFila];
			tileNum2 = aj.ControladorT.numTileMap[jugadorDereCol][jugadorArribaFila];
			if(aj.ControladorT.tile[tileNum1].colision == true || aj.ControladorT.tile[tileNum2].colision == true) {
				jugador.ColisiOn = true;
			}
			break;
		case "abajo":
			jugadorAbajoFila = (jugadorAbajoY + jugador.velocidad)/aj.tamPantalla;
			tileNum1 = aj.ControladorT.numTileMap[jugadorIzquiCol][jugadorAbajoFila];
			tileNum2 = aj.ControladorT.numTileMap[jugadorDereCol][jugadorAbajoFila];
			if(aj.ControladorT.tile[tileNum1].colision == true || aj.ControladorT.tile[tileNum2].colision == true) {
				jugador.ColisiOn = true;
			}
			break;
		case "izquierda":
			jugadorIzquiCol = (jugadorIzquiX - jugador.velocidad)/aj.tamPantalla;
			tileNum1 = aj.ControladorT.numTileMap[jugadorIzquiCol][jugadorArribaFila];
			tileNum2 = aj.ControladorT.numTileMap[jugadorIzquiCol][jugadorAbajoFila];
			if(aj.ControladorT.tile[tileNum1].colision == true || aj.ControladorT.tile[tileNum2].colision == true) {
				jugador.ColisiOn = true;
			}
			break;
		case "derecha":
			jugadorDereCol = (jugadorDereX + jugador.velocidad)/aj.tamPantalla;
			tileNum1 = aj.ControladorT.numTileMap[jugadorDereCol][jugadorArribaFila];
			tileNum2 = aj.ControladorT.numTileMap[jugadorDereCol][jugadorAbajoFila];
			if(aj.ControladorT.tile[tileNum1].colision == true || aj.ControladorT.tile[tileNum2].colision == true) {
				jugador.ColisiOn = true;
			}
			break;
		}
		
	}

	public void revisarTile(Enemigo e1) {
		Random rand = new Random();
		int enemigoIzquiX = e1.ex + e1.ehitBox.x;
		int enemigoDereX = e1.ex + e1.ehitBox.x + e1.ehitBox.width;

		int enemigoArribaY = e1.ey + e1.ehitBox.y;
		int enemigoAbajoY = e1.ey + e1.ehitBox.y + e1.ehitBox.height;

		int enemigoIzquiCol = enemigoIzquiX/aj.tamPantalla;
		int enemigoDereCol = enemigoDereX/aj.tamPantalla;
		int enemigoArribaFila = enemigoArribaY/aj.tamPantalla;
		int enemigoAbajoFila = enemigoAbajoY/aj.tamPantalla;

		int tileNum1, tileNum2;

		switch(e1.edireccion) {
			case "arriba":
				enemigoArribaFila = (enemigoArribaY - e1.evelocidad)/aj.tamPantalla;
				tileNum1 = aj.ControladorT.numTileMap[enemigoIzquiCol][enemigoArribaFila];
				tileNum2 = aj.ControladorT.numTileMap[enemigoDereCol][enemigoArribaFila];
				if(aj.ControladorT.tile[tileNum1].colision == true || aj.ControladorT.tile[tileNum2].colision == true) {
					e1.eColisiOn = true;
					e1.enemigodireccion=rand.nextInt(4);
				}
				break;
			case "abajo":
				enemigoAbajoFila = (enemigoAbajoY + e1.evelocidad)/aj.tamPantalla;
				tileNum1 = aj.ControladorT.numTileMap[enemigoIzquiCol][enemigoAbajoFila];
				tileNum2 = aj.ControladorT.numTileMap[enemigoDereCol][enemigoAbajoFila];
				if(aj.ControladorT.tile[tileNum1].colision == true || aj.ControladorT.tile[tileNum2].colision == true) {
					e1.eColisiOn = true;
					e1.enemigodireccion=rand.nextInt(4);

				}
				break;
			case "izquierda":
				enemigoIzquiCol = (enemigoIzquiX - e1.evelocidad)/aj.tamPantalla;
				tileNum1 = aj.ControladorT.numTileMap[enemigoIzquiCol][enemigoArribaFila];
				tileNum2 = aj.ControladorT.numTileMap[enemigoIzquiCol][enemigoAbajoFila];
				if(aj.ControladorT.tile[tileNum1].colision == true || aj.ControladorT.tile[tileNum2].colision == true) {
					e1.eColisiOn = true;
					e1.enemigodireccion=rand.nextInt(4);

				}
				break;
			case "derecha":
				enemigoDereCol = (enemigoDereX + e1.evelocidad)/aj.tamPantalla;
				tileNum1 = aj.ControladorT.numTileMap[enemigoDereCol][enemigoArribaFila];
				tileNum2 = aj.ControladorT.numTileMap[enemigoDereCol][enemigoAbajoFila];
				if(aj.ControladorT.tile[tileNum1].colision == true || aj.ControladorT.tile[tileNum2].colision == true) {
					e1.eColisiOn = true;
					e1.enemigodireccion=rand.nextInt(4);

				}
				break;
		}

	}
}
