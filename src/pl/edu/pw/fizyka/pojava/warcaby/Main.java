package pl.edu.pw.fizyka.pojava.warcaby;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Jan Lesisz, Michalina Konopka, Karolina Wi�cek
 * 
 */
//test
public class Main {
	static JFrame f;
	public Main() {
		//new Board();
		f = new Board();
		f.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
				
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(
                        null, "Dzi�kujemy za gr� \n \n Tw�rcy:\n Michalina Konopka \n Karolina Wi�cek \n Jan Lesisz",
                        "Podzi�kowania",
                        JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
	}

	public static void main(String[] args) {
		new Main();
	}
}
