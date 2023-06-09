package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.*;
import javax.swing.*;

import Objetos.SuperObjeto;

public class AdminitradorJuego extends JPanel implements Runnable {

	//CONFIGURACIONES DE PANTALLA
	final int escalaOriginal = 5;
	final int escala = 5;

	public int segundos=0,s2=0;

	public int ms=0;

	public int[] ms2={0,0,0,0,0};

	public final int tamPantalla = escalaOriginal * escala; //25x25 CUADROS
	public final int maxColPantalla = 40;
	public final int maxFilPantalla = 20;
	public final int anchoPantalla = tamPantalla * maxColPantalla; // 1015 PIXELES
	public final int alturaPantalla = tamPantalla * maxFilPantalla; // 540 PIXELES


	int FPS = 60;

	public int numTileMap[][];

	ControladorTile ControladorT = new ControladorTile(this,numTileMap);
	Controles teclas = new Controles();
	Thread hiloJuego;
	public Buffo bff = new Buffo();
	public Colision cColision = new Colision(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Jugador jugador = new Jugador(this, teclas);

	public Enemigo enemigo1 = new Enemigo(this);

	public Enemigo enemigo2 = new Enemigo(this);

	public Enemigo enemigo4 = new Enemigo(this);
	public Enemigo enemigo5 = new Enemigo(this);
	public Enemigo enemigo6 = new Enemigo(this);

	public int nivelExpl = 3;

	public Clip clip,clipm,clipgo,clipex,clipgg;
	public AudioInputStream audioInputStream,maudioInputStream,goaudioInputStream,exaudioInputStream,ggaudioinputStream;

	public int muerto=0;

	public Enemigo enemigo3 = new Enemigo(this);

	public Vidas vidascantidad = new Vidas(3);
	public SuperObjeto obj[] = new SuperObjeto[5];
	public SuperObjeto obj2[] = new SuperObjeto[5];
	public SuperObjeto obj3[] = new SuperObjeto[5];
	
	
	public boolean[] expl = new boolean[5];
	
	public AdminitradorJuego() {
		for (int i=0; i<expl.length; i++) {
			expl[i] = false;
		}
		ReproductorDeSonido();
		enemigo1.setEx(25);
		enemigo1.setEy(425);
		enemigo2.setEx(925);
		enemigo2.setEy(425);
		enemigo3.setEx(925);
		enemigo3.setEy(25);
		enemigo4.setEx(400);
		enemigo4.setEy(300);
		enemigo5.setEx(700);
		enemigo5.setEy(125);
		enemigo6.setEx(275);
		enemigo6.setEy(325);
		this.setSize(anchoPantalla, alturaPantalla);
		this.setBackground(Color.LIGHT_GRAY);
		this.setDoubleBuffered(true);
		this.addKeyListener(teclas);
		this.setFocusable(true);
		

	}
	
	public void setUpGame () {
		int aux = aSetter.colocarObjeto();
		HiloBomba hiloBomba = new HiloBomba(aux,aSetter);
		if (aux != 0) {
			hiloBomba.start();
			for(int i=0;i<obj.length;i++){
				if(obj[i]!=null){
					ms2[i]=0;
				}
			}

		}

		
	}
	
	public void iniciarHiloJuego() {
		
		hiloJuego = new Thread(this);
		hiloJuego.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//CONTROL DE FPSSSSSSSSSSS 1
				double intervaloDibujado = 1000000000/FPS;
				double tiempoDibujado = System.nanoTime() + intervaloDibujado;
				
				while(hiloJuego !=null) {
					
					/*long currentTiempo = System.nanoTime();
					System.out.println("Tiempo: "+currentTiempo);*/
					



					ms++;
					if(ms==60){
						segundos++;
						ms=0;
					}
					if(segundos==175){

						try {
							clip.stop();
							clip.close();
							audioInputStream.close();
							ReproductorDeSonido();
						} catch (IOException e) {
							throw new RuntimeException(e);
						}


						segundos=0;
					}
					if(muerto==1){


						enemigo1.evelocidad=0;
						enemigo3.evelocidad=0;
						enemigo2.evelocidad=0;
						enemigo4.evelocidad=0;
						enemigo5.evelocidad=0;
						enemigo6.evelocidad=0;



						try {


							if (vidascantidad.vidasnum==0){
								SonidoGameOver();

							}else  {
								SonidoMuerte();

							}



						} catch (Exception e) {
							System.out.println(e.getMessage());
						}



					}

					//1 ACTUALIZA: ACTUALIZA LA INFORMACION COMO LA POSICION DEL PERSONAJE
					actualizar();

					
					//2 DRAW: DIBUJA EN PANTALLA LA ACTUALIZACION DE INFORMACION
					repaint();
					
				//CONTROL DE FPSSSSSSSSSS 2
				try {

					double tiempoRestante = tiempoDibujado - System.nanoTime();
					tiempoRestante = tiempoRestante/1000000;
					
					if(tiempoRestante < 0) {
						tiempoRestante = 0;
					}
					
					Thread.sleep((long) tiempoRestante);
					
					tiempoDibujado += intervaloDibujado;
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
	}
	
	public void actualizar() {

		enemigo1.actualizarenemigo();
		enemigo2.actualizarenemigo();
		enemigo3.actualizarenemigo();
		enemigo4.actualizarenemigo();
		enemigo5.actualizarenemigo();
		enemigo6.actualizarenemigo();
		jugador.actualizar();
		Rect jug = new Rect(jugador.x, jugador.y, 15, 15);
		Rect enem1 = new Rect(enemigo1.ex, enemigo1.ey, enemigo1.ew, enemigo1.eh);
		Rect enem2 = new Rect(enemigo2.ex, enemigo2.ey, enemigo2.ew, enemigo2.eh);
		Rect enem3 = new Rect(enemigo3.ex, enemigo3.ey, enemigo3.ew, enemigo3.eh);
		Rect enem4 = new Rect(enemigo4.ex, enemigo4.ey, enemigo4.ew, enemigo4.eh);
		Rect enem5 = new Rect(enemigo5.ex, enemigo5.ey, enemigo5.ew, enemigo5.eh);
		Rect enem6 = new Rect(enemigo6.ex, enemigo6.ey, enemigo6.ew, enemigo6.eh);
		if(jug.colision(enem1) || jug.colision(enem2) || jug.colision(enem3) || jug.colision(enem4) || jug.colision(enem5) || jug.colision(enem6)){
			muerto=1;
			vidascantidad.vidasnum--;
		}

		for (int i=1;i<obj.length;i++) {
			if (obj[i] != null) {

				Rect bomba = new Rect(obj[i].MundoX-25, obj[i].MundoY-25, 25, 25);


				if (enem1.colision(bomba)){
					if (enemigo1.eultimadireccion =="arriba") {
						enemigo1.ey += enemigo1.evelocidad;
						enemigo1.edireccion="abajo";

					}
					if (enemigo1.eultimadireccion =="abajo") {
						enemigo1.ey -= enemigo1.evelocidad;
						enemigo1.edireccion="arriba";
					}
					if (enemigo1.eultimadireccion =="izquierda") {
						enemigo1.ex += enemigo1.evelocidad;
						enemigo1.edireccion="derecha";
					}
					if (enemigo1.eultimadireccion =="derecha") {
						enemigo1.ex -= enemigo1.evelocidad;
						enemigo1.edireccion="izquierda";

					}

				}
				if (enem2.colision(bomba)){
					if (enemigo2.eultimadireccion =="arriba") {
						enemigo2.ey += enemigo2.evelocidad;
						enemigo2.edireccion="abajo";

					}
					if (enemigo2.eultimadireccion =="abajo") {
						enemigo2.ey -= enemigo2.evelocidad;
						enemigo2.edireccion="arriba";
					}
					if (enemigo2.eultimadireccion =="izquierda") {
						enemigo2.ex += enemigo2.evelocidad;
						enemigo2.edireccion="derecha";
					}
					if (enemigo2.eultimadireccion =="derecha") {
						enemigo2.ex -= enemigo2.evelocidad;
						enemigo2.edireccion="izquierda";

					}

				}
				if (enem3.colision(bomba)){
					if (enemigo3.eultimadireccion =="arriba") {
						enemigo3.ey += enemigo3.evelocidad;
						enemigo3.edireccion="abajo";

					}
					if (enemigo3.eultimadireccion =="abajo") {
						enemigo3.ey -= enemigo3.evelocidad;
						enemigo3.edireccion="arriba";
					}
					if (enemigo3.eultimadireccion =="izquierda") {
						enemigo3.ex += enemigo3.evelocidad;
						enemigo3.edireccion="derecha";
					}
					if (enemigo3.eultimadireccion =="derecha") {
						enemigo3.ex -= enemigo3.evelocidad;
						enemigo3.edireccion="izquierda";

					}

				}
				if (enem4.colision(bomba)){
					if (enemigo4.eultimadireccion =="arriba") {
						enemigo4.ey += enemigo4.evelocidad;
						enemigo4.edireccion="abajo";

					}
					if (enemigo4.eultimadireccion =="abajo") {
						enemigo4.ey -= enemigo4.evelocidad;
						enemigo4.edireccion="arriba";
					}
					if (enemigo4.eultimadireccion =="izquierda") {
						enemigo4.ex += enemigo4.evelocidad;
						enemigo4.edireccion="derecha";
					}
					if (enemigo4.eultimadireccion =="derecha") {
						enemigo4.ex -= enemigo4.evelocidad;
						enemigo4.edireccion="izquierda";

					}

				}
				if (enem5.colision(bomba)){
					if (enemigo5.eultimadireccion =="arriba") {
						enemigo5.ey += enemigo5.evelocidad;
						enemigo5.edireccion="abajo";

					}
					if (enemigo5.eultimadireccion =="abajo") {
						enemigo5.ey -= enemigo5.evelocidad;
						enemigo5.edireccion="arriba";
					}
					if (enemigo5.eultimadireccion =="izquierda") {
						enemigo5.ex += enemigo5.evelocidad;
						enemigo5.edireccion="derecha";
					}
					if (enemigo5.eultimadireccion =="derecha") {
						enemigo5.ex -= enemigo5.evelocidad;
						enemigo5.edireccion="izquierda";

					}

				}
				if (enem6.colision(bomba)){
					if (enemigo6.eultimadireccion =="arriba") {
						enemigo6.ey += enemigo6.evelocidad;
						enemigo6.edireccion="abajo";

					}
					if (enemigo6.eultimadireccion =="abajo") {
						enemigo6.ey -= enemigo6.evelocidad;
						enemigo6.edireccion="arriba";
					}
					if (enemigo6.eultimadireccion =="izquierda") {
						enemigo6.ex += enemigo6.evelocidad;
						enemigo6.edireccion="derecha";
					}
					if (enemigo6.eultimadireccion =="derecha") {
						enemigo6.ex -= enemigo6.evelocidad;
						enemigo6.edireccion="izquierda";

					}

				}
				repaint();
			}
		}
		for (int i=1;i<obj2.length;i++) {
			if (obj2[i] != null && obj3[i] != null) {
				Rect fuegoHor = new Rect(obj2[i].MundoX-(25*nivelExpl),obj2[i].MundoY-25,(25*nivelExpl+25),23);
				Rect fuegoVer = new Rect (obj3[i].MundoX-25,obj3[i].MundoY-25,23,(25*nivelExpl+25));


				if(jug.colision(fuegoVer) || jug.colision(fuegoHor)){
					vidascantidad.vidasnum--;
					muerto=1;
				}

				if(enem1.colision(fuegoVer) || enem1.colision(fuegoHor)){
					enemigo1.ex=0;
					enemigo1.ey=0;
					enemigo1.ew=0;
					enemigo1.eh=0;
					enemigo1.evelocidad=0;
					enemigo1.edown1=null;
				}
				if(enem2.colision(fuegoVer) || enem2.colision(fuegoHor)){
					enemigo2.ex=0;
					enemigo2.ey=0;
					enemigo2.ew=0;
					enemigo2.eh=0;
					enemigo2.evelocidad=0;
					enemigo2.edown1=null;
				}
				if(enem3.colision(fuegoVer) || enem3.colision(fuegoHor)){
					enemigo3.ex=0;
					enemigo3.ey=0;
					enemigo3.ew=0;
					enemigo3.eh=0;
					enemigo3.evelocidad=0;
					enemigo3.edown1=null;
				}
				if(enem4.colision(fuegoVer) || enem4.colision(fuegoHor)){
					enemigo4.ex=0;
					enemigo4.ey=0;
					enemigo4.ew=0;
					enemigo4.eh=0;
					enemigo4.evelocidad=0;
					enemigo4.edown1=null;
				}
				if(enem5.colision(fuegoVer) || enem5.colision(fuegoHor)){
					enemigo5.ex=0;
					enemigo5.ey=0;
					enemigo5.ew=0;
					enemigo5.eh=0;
					enemigo5.evelocidad=0;
					enemigo5.edown1=null;
				}
				if(enem6.colision(fuegoVer) || enem6.colision(fuegoHor)){
					enemigo6.ex=0;
					enemigo6.ey=0;
					enemigo6.ew=0;
					enemigo6.eh=0;
					enemigo6.evelocidad=0;
					enemigo6.edown1=null;
				}

			}
		}
		
		for (int i=1;i<obj2.length;i++) {

			if (obj2[i] != null && obj3[i] != null) {

				if(ms2[i]==0){
					SonidoExplosion();
					ms2[i]=1;
				}

				Rect fuegoHor = new Rect(obj2[i].MundoX,obj2[i].MundoY,(25*nivelExpl),25);
				Rect fuegoVer = new Rect (obj3[i].MundoX,obj3[i].MundoY,25,(25*nivelExpl));

//				for (int j=0;j<maxColPantalla;j++) {
//					for (int jj=0;jj<maxFilPantalla;jj++) {
						int cont = 0;
						
						for (int j=0;j<5;j++) {
							if (obj2[i] != null && obj3[i] != null) {
								int x=0,y=0;	
						x = (obj2[i].MundoX / tamPantalla)-3 + cont;
						y = (obj2[i].MundoY / tamPantalla)-1;
						
						while(x<0) {
							x=1;
							cont++;
						}
						
					//	System.out.println("x: "+x+" y: "+y);
						
						//Comprobar horizontal
						switch (numTileMap[x][y]){
						case 1:
							cont++;
							break;
						case 2:
							numTileMap[x][y] = 1;
							cont++;
							break;
						case 0:
							cont ++;

							break;
						}
						}
						}
						cont = 0;
						
						for (int j=0;j<5;j++) {
							if (obj2[i] != null && obj3[i] != null) {
								int x=0,y=0;	
						x = (obj2[i].MundoX / tamPantalla)-1 ;
						y = (obj2[i].MundoY / tamPantalla)-3 + cont;
						
						while(y<0 || x < 0) {
							y=1;
							x=1;
							cont++;
						}
						
					//	System.out.println("x: "+x+" y: "+y);
						
						//Comprobar horizontal
						switch (numTileMap[x][y]){
						case 1:
							cont++;
							break;
						case 2:
							numTileMap[x][y] = 1;
							cont++;
							break;
						case 0:
							cont ++;

							break;
						}
						}
						}
						
//					}
//				}
			}
		}
		if(enemigo1.edown1==null && enemigo2.edown1==null && enemigo3.edown1==null && enemigo4.edown1==null && enemigo5.edown1==null && enemigo6.edown1==null  ){
			SonidoVictoria();
		}
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		
		ControladorT.dibujar(g2);
		
		//Objeto
		for (int i=0; i<obj.length; i++) {
			if (obj[i]!= null) {
				obj[i].draw(g2,this);
			}
			if (expl[i]) {
				obj2[i].MundoX = obj[i].MundoX;
				obj2[i].MundoY = obj[i].MundoY;
				obj2[i].draw2(g2, this);
			}
		}


		jugador.dibujar(g2);
		enemigo1.dibujar(g2);
		enemigo2.dibujar(g2);
		enemigo3.dibujar(g2);
		enemigo4.dibujar(g2);
		enemigo5.dibujar(g2);
		enemigo6.dibujar(g2);
		vidascantidad.dibujar(g2);
		bff.draw(g2, this);



		g2.dispose();
	}
	public class Rect {

		int x=0;
		int y=0;
		int w=0;
		int h=0;


		Rect(int x,int y, int w, int h){
			this.x=x;
			this.y=y;
			this.w=w;
			this.h=h;


		}

		public Boolean colision(Rect target) {

			if(this.x<target.x+target.w &&
					this.x+w>target.x &&

					this.y<target.y+target.h &&
					this.y+this.h>target.y) {
				return true;
			}
			return false;
		}
	}

	public void ReproductorDeSonido () {

		try {
			audioInputStream = AudioSystem.getAudioInputStream(
					AdminitradorJuego.class.getResourceAsStream("/Sonidos/musicafondo.wav"));

			AudioFormat format = audioInputStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);
			clip.start();


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(muerto==1) {
			segundos = 0;
			ms = 0;
			jugador.x = 25;
			jugador.y = 25;
			if(enemigo1.edown1!=null) {
				enemigo1.setEx(25);
				enemigo1.setEy(425);
				enemigo1.evelocidad = 1;
			}
			if(enemigo2.edown1!=null) {
				enemigo2.setEx(925);
				enemigo2.setEy(425);
				enemigo2.evelocidad = 1;

			}
			if(enemigo3.edown1!=null) {
				enemigo3.setEx(925);
				enemigo3.setEy(25);
				enemigo3.evelocidad = 1;
			}
			if(enemigo4.edown1!=null) {
				enemigo4.setEx(400);
				enemigo4.setEy(300);
				enemigo4.evelocidad = 1;
			}
			if(enemigo5.edown1!=null) {
				enemigo5.setEx(700);
				enemigo5.setEy(125);
				enemigo5.evelocidad = 1;
			}
			if(enemigo6.edown1!=null) {
				enemigo6.setEx(275);
				enemigo6.setEy(325);
				enemigo6.evelocidad = 1;
			}






			muerto = 0;
		}
	}

	public void SonidoMuerte () {

		try {

			clip.close();

			maudioInputStream = AudioSystem.getAudioInputStream(
					AdminitradorJuego.class.getResourceAsStream("/Sonidos/bombermanmuere.wav"));

			AudioFormat format = maudioInputStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clipm = (Clip) AudioSystem.getLine(info);
			clipm.open(maudioInputStream);
			clipm.start();
			Thread.sleep(3000);


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ReproductorDeSonido();

	}

	public void SonidoGameOver () {

		try {
			clip.close();
			goaudioInputStream = AudioSystem.getAudioInputStream(
					AdminitradorJuego.class.getResourceAsStream("/Sonidos/go.wav"));

			AudioFormat format = goaudioInputStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clipgo = (Clip) AudioSystem.getLine(info);
			clipgo.open(goaudioInputStream);
			clipgo.start();
			Thread.sleep(6500);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ReproductorDeSonido();
		vidascantidad.vidasnum=3;

	}

	public void SonidoExplosion () {

		try {

			exaudioInputStream = AudioSystem.getAudioInputStream(
					AdminitradorJuego.class.getResourceAsStream("/Sonidos/explosion.wav"));

			AudioFormat format = exaudioInputStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clipex = (Clip) AudioSystem.getLine(info);
			clipex.open(exaudioInputStream);
			clipex.start();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}
	public void SonidoVictoria () {

		try {
			clip.close();

			ggaudioinputStream = AudioSystem.getAudioInputStream(
					AdminitradorJuego.class.getResourceAsStream("/Sonidos/ganar.wav"));

			AudioFormat format = ggaudioinputStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clipgg = (Clip) AudioSystem.getLine(info);
			clipgg.open(ggaudioinputStream);
			clipgg.start();
			Thread.sleep(4000);
			JOptionPane.showMessageDialog(null,"GANASTEEEEE!!!","GG",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}


}
