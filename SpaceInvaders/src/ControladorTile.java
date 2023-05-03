import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class ControladorTile {
	
	AdminitradorJuego aj;
	public Tile[] tile;
	public int numTileMap[][];

	
	public ControladorTile(AdminitradorJuego aj) {
		
		this.aj = aj;
		
		tile = new Tile[10];
		numTileMap = new int[aj.maxColPantalla][aj.maxFilPantalla];
		
		cargarTile();
		cargarMapa("/mapas/mapa1.txt");
	}
	
	public void cargarTile() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/Calle.png"));
			tile[0].colision = true;
			
			tile[1] = new Tile();
			tile[1].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/Tierra.png"));
			
			tile[2] = new Tile();
			tile[2].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/Meta.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void cargarMapa(String rutaArchivo) {
		try {
			//EXPORTA EL MAPA
			InputStream mapa1 = getClass().getResourceAsStream(rutaArchivo);
			//LEE EL MAPA
			BufferedReader br = new BufferedReader(new InputStreamReader(mapa1));
			
			int col = 0;
			int fil = 0;
			
			while(col < aj.maxColPantalla && fil < aj.maxFilPantalla) {
				
				String linea = br.readLine();
				
				while(col < aj.maxColPantalla) {
					
					String numeros[] = linea.split(" ");
					
					int num = Integer.parseInt(numeros[col]);
					
					numTileMap[col][fil] = num;
					col++;
				}
				if(col == aj.maxColPantalla) {
					col = 0;
					fil++;
				}
			}
			br.close();
		}
		catch(Exception e) {
			
		}
	}
	
	public void dibujar(Graphics2D g2) {
		
		int col = 0;
		int fila = 0;
		int x = 0;
		int y = 0;
		
		while(col < aj.maxColPantalla && fila < aj.maxFilPantalla) {
			
			int numTile = numTileMap[col][fila];
			
			g2.drawImage(tile[numTile].imagen, x, y, aj.tamPantalla, aj.tamPantalla, null);
			/*g2.setColor(Color.blue);
			g2.fillRect(x, y, 25, 25);*/
			
			col++;
			x+= aj.tamPantalla;
			
			if(col == aj.maxColPantalla) {
				col = 0;
				x = 0;
				fila++;
				y += aj.tamPantalla;
			}
		}
		
	}
}
