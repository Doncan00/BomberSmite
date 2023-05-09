package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class Vidas {
    public int vidasnum;

    public BufferedImage vidasfoto;




    public Vidas(int vidasnum){
        this.vidasnum=vidasnum;
    }
    public void imagenvidas(){
        try {
            vidasfoto = ImageIO.read(getClass().getResourceAsStream("/Vidas/corazon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void dibujar(Graphics2D g2) {
        imagenvidas();

        if(vidasnum==3){
            g2.drawImage(vidasfoto,25,455,25,25,null);
            g2.drawImage(vidasfoto,51,455,25,25,null);
            g2.drawImage(vidasfoto,77,455,25,25,null);
        }
        if(vidasnum==2){
            g2.drawImage(vidasfoto,25,455,25,25,null);
            g2.drawImage(vidasfoto,51,455,25,25,null);

        }
        if(vidasnum==1){
            g2.drawImage(vidasfoto,25,455,25,25,null);

        }

    }
}
