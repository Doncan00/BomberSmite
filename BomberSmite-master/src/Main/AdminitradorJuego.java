package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.*;

import Objetos.SuperObjeto;

public class AdminitradorJuego extends JPanel implements Runnable {

	//CONFIGURACIONES DE PANTALLA
	final int escalaOriginal = 5;
	final int escala = 5;

	public int segundos=0;

	public int ms=0,ms2=0;

	public final int tamPantalla = escalaOriginal * escala; //25x25 CUADROS
	public final int maxColPantalla = 40;
	public final int maxFilPantalla = 20;
	public final int anchoPantalla = tamPantalla * maxColPantalla; // 1015 PIXELES
	public final int alturaPantalla = tamPantalla * maxFilPantalla; // 540 PIXELES


	int FPS = 60;


	ControladorTile ControladorT = new ControladorTile(this);
	Controles teclas = new Controles();
	Thread hiloJuego;
	public Colision cColision = new Colision(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Jugador jugador = new Jugador(this, teclas);

	public Enemigo enemigo1 = new Enemigo(this);

	public Enemigo enemigo2 = new Enemigo(this);

	public Clip clip,clipm;
	public AudioInputStream audioInputStream,maudioInputStream;

	public int muerto=0;

	public Enemigo enemigo3 = new Enemigo(this);

	public Vidas vidascantidad = new Vidas(3);
	public SuperObjeto obj[] = new SuperObjeto[4]; 
	public SuperObjeto obj2[] = new SuperObjeto[4]; 
	public boolean[] expl = new boolean[4];
	
	public AdminitradorJuego() {
		for (int i=0; i<expl.length; i++) {
			expl[i] = false;
		}
		ReproductorDeSonido();
		enemigo1.setEy(425);
		enemigo2.setEy(425);
		enemigo2.setEx(925);
		enemigo3.setEx(925);
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
					
					//1 ACTUALIZA: ACTUALIZA LA INFORMACION COMO LA POSICION DEL PERSONAJE
					actualizar();

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
						SonidoMuerte();
						try {

							Thread.sleep(3000);
							if (vidascantidad.vidasnum==0){
								vidascantidad.vidasnum=3;
								System.exit(0);
							}else {
								ReproductorDeSonido();
								segundos = 0;
								ms = 0;
								clipm.stop();
								clipm.close();
								maudioInputStream.close();
							}

							enemigo1.evelocidad=1;
							enemigo3.evelocidad=1;
							enemigo2.evelocidad=1;
							jugador.velocidad=2;
							muerto=0;
						} catch (Exception e) {
							throw new RuntimeException(e);
						}


					}


					
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
		jugador.actualizar();
		Rect jug = new Rect(jugador.x, jugador.y, 15, 15);
		Rect enem1 = new Rect(enemigo1.ex, enemigo1.ey, 15, 15);
		Rect enem2 = new Rect(enemigo2.ex, enemigo2.ey, 15, 15);
		Rect enem3 = new Rect(enemigo3.ex, enemigo3.ey, 15, 15);
		if(jug.colision(enem1) || jug.colision(enem2) || jug.colision(enem3)){
			muerto=1;
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
		vidascantidad.dibujar(g2);



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
	}

	public void SonidoMuerte () {

		try {
			maudioInputStream = AudioSystem.getAudioInputStream(
					AdminitradorJuego.class.getResourceAsStream("/Sonidos/bombermanmuere.wav"));

			AudioFormat format = maudioInputStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clipm = (Clip) AudioSystem.getLine(info);
			clipm.open(maudioInputStream);
			clipm.start();
			clip.close();
			enemigo1.evelocidad=0;
			enemigo3.evelocidad=0;
			enemigo2.evelocidad=0;
			jugador.velocidad=0;
			jugador.x=25;
			jugador.y=25;
			enemigo1.setEy(425);
			enemigo1.setEx(25);
			enemigo2.setEy(425);
			enemigo2.setEx(925);
			enemigo3.setEx(925);
			enemigo3.setEy(25);
			vidascantidad.vidasnum--;





		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
