package pl.edu.pw.fizyka.pojava.warcaby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ZasadyGry extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	protected static JFrame parent; 
	protected JFrame okno; 
	protected JLabel napisGlowny; 
	protected JButton wyjscie;
	protected JLabel instrukcja;
	private String oryginal = " ";
	 InputStreamReader isr;
	 String s3;
	 Font font;
		int i;
	//BufferedInputStream czcionka;
	//int Lista[];
	

	 
	
	
	public ZasadyGry(JFrame oknoF) {
		parent = oknoF;
		okno = new JFrame();
		okno.setTitle("Zasady Gry");
		okno.setSize(650,600);
		okno.setLocationRelativeTo(okno);
		okno.setLayout(new BorderLayout());
		//okno.setResizable(false);
		BufferedInputStream czcionka;
		
	
		napisGlowny = new JLabel("ZASADY GRY", SwingConstants.CENTER);
		wyjscie = new JButton ("ZAMKNIJ");
		
		
	
	try {
		String s = "obrazy/Lobster_1.3.otf";
		InputStream sciezka = getClass().getResourceAsStream(s);
        czcionka = new BufferedInputStream(sciezka);

         font = Font.createFont(Font.TRUETYPE_FONT, czcionka);

        napisGlowny.setFont(font.deriveFont(Font.BOLD, 15f));
        
    } 
	catch (FontFormatException | IOException e) {
        e.printStackTrace();
    }
    
	
	
	
	
	napisGlowny.setForeground(Color.ORANGE);
	okno.add(napisGlowny, BorderLayout.NORTH);
	
	instrukcja = new JLabel("ZASADY GRY", SwingConstants.CENTER);
	okno.add(instrukcja, BorderLayout.CENTER);
	
	wyjscie = new JButton("Zamknij");
	//wyjscie.setBounds(265, 290, 105, 20);
	okno.add(wyjscie, BorderLayout.PAGE_END);
	wyjscie.addActionListener(this);
	
	
    
    //JFileChooser jchooser = new JFileChooser(dirPath);
    
   // int returnVal = jchooser.showSaveDialog(null);

	
        if (Board.czyJezykPolski) {
        	 s3 = "teksty/polWarcaby.txt";
        }
        else {
        	 s3 = "teksty/angWarcaby.txt";
        }
        //	URL sciezka3 = getClass().getResource(s3);
        	File dirPath = new File(getClass().getResource(s3).getFile());
            //BufferedReader reader = new BufferedReader(new FileReader(dirPath))
           
			try {
				isr = new InputStreamReader(new FileInputStream(dirPath),Charset.forName("UTF-8").newDecoder());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            try (Scanner s = new Scanner(isr).useDelimiter(" ")) {
            
            	
            	while (s.hasNext()) {
				String result = s.next();
				
				
				
				oryginal= oryginal+result+" ";
				i++;
				if (i%7 == 0) {
					i=0;
					oryginal = oryginal+"\n";
				}
            	}
            	oryginal = "<html>"+oryginal+"</html>";
			}
           // System.out.println(oryginal);
            instrukcja.setText(oryginal);
            instrukcja.setFont(font.deriveFont(Font.HANGING_BASELINE, 17f));
        
        
    
	
	
	okno.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == wyjscie) //Rezygnuje
		{
			okno.dispose();				
		}

		
	}
}
