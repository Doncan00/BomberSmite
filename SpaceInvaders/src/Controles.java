import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controles implements KeyListener{
	
	public boolean arriba, abajo, izqui, dere;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		
		if(code ==  KeyEvent.VK_W) {
			arriba = true;
		}
		if(code == KeyEvent.VK_S) {
			abajo = true;

		}
		if(code == KeyEvent.VK_A) {
			izqui = true;

		}
		if(code == KeyEvent.VK_D) {
			dere = true;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			arriba = false;
		}
		if(code == KeyEvent.VK_S) {
			abajo = false;

		}
		if(code == KeyEvent.VK_A) {
			izqui = false;

		}
		if(code == KeyEvent.VK_D) {
			dere = false;

		}
	}
	

}
