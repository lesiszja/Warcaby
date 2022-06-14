package pl.edu.pw.fizyka.pojava.warcaby;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Opcje extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected static JFrame parent; 
	protected JFrame okno; 
	protected JLabel napisGlowny;
	protected JLabel napisStyl; 
	protected JLabel napisZmianaWygladu; 
	protected JLabel napisMuzyka; 
	protected JLabel napisGlosnosc;
	protected JButton tekstury; 
	protected JButton ustawieniaMuzyki; 
	protected JButton ustawieniaEfektow;
	protected JComboBox<Object> style; 
	BufferedInputStream czcionka;
	protected JButton wczytaj;
	protected JButton zapisz;
	protected JButton zmianaJezykaPrzycisk; 
	protected JLabel napisZmianaJezyka;
	int Lista[];
	protected JButton obrotPlanszy;
	static int SLIDER_MIN = -30;
    static int SLIDER_MAX = 6;
    static int SLIDER_INIT;
    static JSlider slider;
    protected JLabel napisEfekty;
    
    
static final Map<String, String> LF_MAP = createLfMap();
    
	static Map<String, String> createLfMap(){
        Map<String, String> map = new HashMap<String, String>();
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
        	if(info.getClassName() == "com.sun.java.swing.plaf.motif.MotifLookAndFeel") {
        	}
        	else {
            map.put(info.getName(), info.getClassName());
            //System.out.println(info.getClassName());
        	}
        }
        return Collections.unmodifiableMap(map);
    }
	
	public Opcje(JFrame oknoF) {
		
		parent = oknoF;
		okno = new JFrame();
		okno.setTitle("Opcje");
		okno.setSize(650,400);
		okno.setLocationRelativeTo(okno);
		okno.setLayout(null);
		okno.setResizable(false);
		Lista = new int[64];
		SLIDER_INIT = Board.glosnoscMuzyki;
	
		napisGlowny = new JLabel(Board.opcjegryTekst);
		napisStyl = new JLabel(Board.StylAplikacjiTekst);
		napisZmianaWygladu = new JLabel(Board.teksturynapisTekst);
		napisMuzyka = new JLabel(Board.muzykaTekst);
		napisGlosnosc = new JLabel(Board.glosnoscTekst);
		napisEfekty = new JLabel (Board.wlaczenieEfektowTekst);
		napisZmianaJezyka=new JLabel(Board.jezykTekst); 
		
	
		
		//Zmiana czcionki 
		
		try {
			String s = "obrazy/Lobster_1.3.otf";
			InputStream sciezka = getClass().getResourceAsStream(s);
            czcionka = new BufferedInputStream(sciezka);

            Font font = Font.createFont(Font.TRUETYPE_FONT, czcionka);

            napisGlowny.setFont(font.deriveFont(Font.BOLD, 22f));
            napisStyl.setFont(font.deriveFont(Font.BOLD, 14f));
            napisZmianaWygladu.setFont(font.deriveFont(Font.BOLD, 14f));
            napisMuzyka.setFont(font.deriveFont(Font.BOLD, 14f));
            napisGlosnosc.setFont(font.deriveFont(Font.BOLD, 14f));
            napisZmianaJezyka.setFont(font.deriveFont(Font.BOLD, 14f)); 
            
            napisEfekty.setFont(font.deriveFont(Font.BOLD, 14f));
        } 
		catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
		napisZmianaJezyka.setBounds(10, 35, 100, 20); 
		napisZmianaJezyka.setForeground(Color.ORANGE); 
		okno.add(napisZmianaJezyka);
		
		napisGlosnosc.setBounds(10, 230, 120, 20);
		napisGlosnosc.setForeground(Color.ORANGE);
		okno.add(napisGlosnosc);
		slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
	   // slider.setVisible(true);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Board.glosnoscMuzyki = slider.getValue();
				ImportowanieDzwiekow.zmienGlosnosc();
				
			}
		}) ;
        slider.setBounds(185, 230, 310, 50);
        okno.add(slider);
        slider.setVisible(Board.czyOdtwarzacMuzyke);
		
		napisGlowny.setBounds(265, 10, 120, 25);
		napisGlowny.setForeground(Color.ORANGE);
		okno.add(napisGlowny);

		napisEfekty.setBounds(10, 160, 140, 20);
		napisEfekty.setForeground(Color.ORANGE);
		okno.add(napisEfekty);
		
		napisStyl.setBounds(10, 70, 100, 20);
		napisStyl.setForeground(Color.ORANGE);
		okno.add(napisStyl);

		//style = new JComboBox<Object>(new String[] { "Clear", "Metal", "Motif", "Nimbus" });
		//style.setBounds(225, 90, 200, 20);
		//okno.add(style);
		//style.addActionListener(this);
		
		 style = new JComboBox<Object>();
        for (String name : LF_MAP.keySet()){
        	style.addItem(name);
        }
        style.setSelectedItem(UIManager.getLookAndFeel().getName());
        style.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    UIManager.setLookAndFeel(LF_MAP.get(style.getSelectedItem()));
                    SwingUtilities.updateComponentTreeUI(parent);
                    SwingUtilities.updateComponentTreeUI(okno);
                    //Opcje.this.pack();
                    //okno.setVisible(false);
                   // okno.setVisible(true);
                }catch (Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(Opcje.this, "Bï¿½ï¿½d podczas zmiany LF");
                }
            }
        });
        style.setBounds(225, 90, 200, 20);
		okno.add(style);
        

		napisZmianaWygladu.setBounds(10, 120, 120, 20);
		napisZmianaWygladu.setForeground(Color.ORANGE);
		okno.add(napisZmianaWygladu);
		
		

		tekstury = new JButton(Board.wyborTekstur);
		tekstury.setBounds(185, 120, 310, 20);
		okno.add(tekstury);
		tekstury.addActionListener(this);

		napisMuzyka.setBounds(10, 200, 120, 20);
		napisMuzyka.setForeground(Color.ORANGE);
		okno.add(napisMuzyka);

		ustawieniaMuzyki = new JButton(Board.wlaczenieMuzyki);
		ustawieniaMuzyki.setBounds(185, 200, 310, 20);
		okno.add(ustawieniaMuzyki);
		ustawieniaMuzyki.addActionListener(this);
		
		ustawieniaEfektow = new JButton(Board.wlaczenieEfektow);
		ustawieniaEfektow.setBounds(185, 160, 310, 20);
		okno.add(ustawieniaEfektow);
		ustawieniaEfektow.addActionListener(this);

				
		wczytaj = new JButton("Wczytaj planszê");
		wczytaj.setBounds(185, 320, 150, 20);
		okno.add(wczytaj);
		wczytaj.addActionListener(this);
		
		zapisz = new JButton("Zapisz do pliku");
		zapisz.setBounds(345, 320, 150, 20);
		okno.add(zapisz);
		zapisz.addActionListener(this);
		
		obrotPlanszy = new JButton(Board.obrotTekst);
		obrotPlanszy.setBounds(185, 290, 310, 20);
		okno.add(obrotPlanszy);
		obrotPlanszy.addActionListener(this);
		
		zmianaJezykaPrzycisk=new JButton(Board.zmianaJezykaTekst); 
		zmianaJezykaPrzycisk.setBounds(185, 40, 310, 20);
		okno.add(zmianaJezykaPrzycisk);
		zmianaJezykaPrzycisk.addActionListener(this);
		
		okno.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		

		
		if (source == wczytaj) {
			File dirPath = new File(System.getProperty("user.dir"));
			JFileChooser jchooser = new JFileChooser(dirPath);
			int returnVal = jchooser.showSaveDialog(null);
			
		    if (returnVal==JFileChooser.APPROVE_OPTION){
		    	try{
		    		File inputFile= jchooser.getSelectedFile();
			        InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile),
			        Charset.forName("UTF-8").newDecoder());
			        Scanner s = new Scanner(isr).useDelimiter("");
			       	for (int i = 0; i < 64; i++) 
			       		Lista[i] = Integer.parseInt(s.next());	
			       	int a=0;		
			   		for (int i = 0; i < 8; i++) {
			   			for (int j = 0; j < 8; j++) {
			   				Board.pawnTable[i][j]=Lista[a];
			                a++;
			                parent.repaint();
			            }
			   		}
			                		
			   		s.close();
			
			    }
		    	catch (FileNotFoundException e1) {
		    		e1.printStackTrace();
		    	}
		    }
		}
		if(source==zapisz) {
			int a=0;		
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					Lista[a]=Board.pawnTable[i][j];
					a++;
				}
			}
			 File dirPath2 = new File(System.getProperty("user.dir"));
	         JFileChooser jchooser = new JFileChooser(dirPath2);
	         int returnVal = jchooser.showSaveDialog(null);
	         if (returnVal==JFileChooser.APPROVE_OPTION) {
	        	 try {
	        		 File outputFile = new File(jchooser.getSelectedFile().getAbsolutePath() + ".txt");
	                 OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFile),Charset.forName("UTF-8").newEncoder());
	                 for (int i=0; i<64; i++) {
	                	 osw.write(String.valueOf(Lista[i]));
	                 }	                    
	                 osw.close();
	            } 
	            catch (FileNotFoundException e1) {
	            	e1.printStackTrace();
	            } 
	            catch (IOException e1) {
	            	e1.printStackTrace();
	            }
	        	
	         }
		}
		if(source==obrotPlanszy) {
			if(!Board.czyObracacPlansze) {
				Board.czyObracacPlansze = true;		
				if(Board.czyJezykPolski)
				{
					obrotPlanszy.setText("W³¹czony Obrót Planszy");
					Board.setObrotTekst("W³¹czony Obrót Planszy");
				}
				else
				{
					obrotPlanszy.setText("Board rotation enabled");
					Board.setObrotTekst("Board rotation enabled");
				}
			}
			else {
				Board.czyObracacPlansze = false;
				if(Board.czyJezykPolski)
				{
					obrotPlanszy.setText("Wy³¹czony Obrót Planszy");
					Board.setObrotTekst("Wy³¹czony Obrót Planszy");
				}
				else
				{
					obrotPlanszy.setText("Board rotation disabled");
					Board.setObrotTekst("Board rotation disabled");
				}
			}
		}
		if(source==zmianaJezykaPrzycisk) {
			if(!Board.czyJezykPolski) {
				Board.czyJezykPolski = true;		
				zmianaJezykaPrzycisk.setText("Switch to English");
				Board.setZmianaJezykaTekst("Switch to English");
				if(Board.czyNormalneTekstury)
				{
					tekstury.setText("Domyœlne");
					Board.setWyborTekstur("Domyœlne");
				}
				if(Board.czyOdtwarzacMuzyke)
				{
					ustawieniaMuzyki.setText("Muzyka t³a w³¹czona");
					Board.setWlaczenieMuzyki("Muzyka t³a w³¹czona");
				}
				else
				{
					ustawieniaMuzyki.setText("Muzyka t³a wy³¹czona");
					Board.setWlaczenieMuzyki("Muzyka t³a wy³¹czona");
				}
				if(Board.czyObracacPlansze)
				{
					obrotPlanszy.setText("W³¹czony Obrót Planszy");
					Board.setObrotTekst("W³¹czony Obrót Planszy");
				}
				else
				{
					obrotPlanszy.setText("Wy³¹czony Obrót Planszy");
					Board.setObrotTekst("Wy³¹czony Obrót Planszy");
				}
				if (Board.czyOdtwarzacEfekty) {
					ustawieniaEfektow.setText("Efekty dzwiêkowe w³¹czone");
					Board.setWlaczenieEfektow("Efekty dzwiêkowe w³¹czone");
				}
				else {
					ustawieniaEfektow.setText("Efekty dzwiêkowe wy³¹czone");
					Board.setWlaczenieEfektow("Efekty dzwiêkowe wy³¹czone");
				}
				
				napisEfekty.setText("Efekty dzwiekowe");
				Board.setWlaczenieEfektowTekst("Efekty dzwiekowe");
				
				wczytaj.setText("Wczytaj planszê");
				Board.setWczytajTekst("Wczytaj planszê");
				
				zapisz.setText("Zapisz planszê");
				Board.setZapiszTekst("Zapisz planszê");
				
				napisZmianaWygladu.setText("Tekstury");
				Board.setTeksturynapisTekst("Tekstury");
				
				napisGlowny.setText("OPCJE GRY");
				Board.setOpcjegryTekst("OPCJE GRY");
				
				napisGlosnosc.setText("Glosnosc[dB]");
				Board.setGlosnoscTekst("Glosnosc[dB]");
				
				napisZmianaJezyka.setText("Jezyk");
				Board.setJezykTekst("Jezyk");

				napisMuzyka.setText("Muzyka");
				Board.setMuzykaTekst("Muzyka");
				
				napisStyl.setText("Styl aplikacji");
				Board.setStylAplikacjiTekst("Styl aplikacji");
				
				Board.zmianaJezykaFunkcja();

			}
			else {
				Board.czyJezykPolski = false;
				zmianaJezykaPrzycisk.setText("Zmieñ na Polski");
				Board.setZmianaJezykaTekst("Zmieñ na Polski");
				if(Board.czyNormalneTekstury)
				{
					tekstury.setText("Normal");
					Board.setWyborTekstur("Normal");
				}
				if(Board.czyOdtwarzacMuzyke)
				{
					ustawieniaMuzyki.setText("Background music on");
					Board.setWlaczenieMuzyki("Background music on");
				}
				else
				{
					ustawieniaMuzyki.setText("Background music off");
					Board.setWlaczenieMuzyki("Background music off");
				}
				if(Board.czyObracacPlansze)
				{
					obrotPlanszy.setText("Board rotation enabled");
					Board.setObrotTekst("Board rotation enabled");
				}
				else
				{
					obrotPlanszy.setText("Board rotation disabled");
					Board.setObrotTekst("Board rotation disabled");
				}
				if (Board.czyOdtwarzacEfekty) {
					ustawieniaEfektow.setText("Sound effects on");
					Board.setWlaczenieEfektow("Sound effects on");
				}
				else {
					ustawieniaEfektow.setText("Sound effects off");
					Board.setWlaczenieEfektow("Sound effects off");
				}
				
				napisEfekty.setText("Sound effects");
				Board.setWlaczenieEfektowTekst("Sound effects");
				
				wczytaj.setText("Load a board");
				Board.setWczytajTekst("Load a board");
				
				zapisz.setText("Save board");
				Board.setZapiszTekst("Save board");
				
				napisZmianaWygladu.setText("Textures");
				Board.setTeksturynapisTekst("Textures");
				
				napisGlowny.setText("OPTIONS");
				Board.setOpcjegryTekst("OPTIONS");
				
				napisGlosnosc.setText("Volume[dB]");
				Board.setGlosnoscTekst("Volume[dB]");
				
				napisZmianaJezyka.setText("Language");
				Board.setJezykTekst("Language");
				
				napisMuzyka.setText("Music");
				Board.setMuzykaTekst("Music");
				
				napisStyl.setText("Application style");
				Board.setStylAplikacjiTekst("Application style");
				
				Board.zmianaJezykaFunkcja();
			}
		}
		if(source==ustawieniaMuzyki) {
			if(!Board.czyOdtwarzacMuzyke) {
				Board.czyOdtwarzacMuzyke = true;		
				if(Board.czyJezykPolski)
				{
					ustawieniaMuzyki.setText("Muzyka t³a w³¹czona");
					Board.setWlaczenieMuzyki("Muzyka t³a w³¹czona");
				}
				else
				{
					ustawieniaMuzyki.setText("Background music on");
					Board.setWlaczenieMuzyki("Background music on");
				}
				//Board.wyciszSoundtrack();
				ImportowanieDzwiekow.zmienGlosnosc();
				slider.setVisible(true);
				Board.odtworzSoundtrack();
				//slider.setValue(Board.glosnoscMuzyki);
			}
			else {
				Board.czyOdtwarzacMuzyke = false;
				if(Board.czyJezykPolski)
				{
					ustawieniaMuzyki.setText("Muzyka t³a wy³¹czona");
					Board.setWlaczenieMuzyki("Muzyka t³a wy³¹czona");
				}
				else
				{
					ustawieniaMuzyki.setText("Background music off");
					Board.setWlaczenieMuzyki("Background music off");
				}
				//Board.wyciszSoundtrack();
				ImportowanieDzwiekow.zmienGlosnosc();
				slider.setVisible(false);
				Board.zatrzymajSoundtrack();
				}
		}
		if(source==ustawieniaEfektow) {
			if(!Board.czyOdtwarzacEfekty) {
				Board.czyOdtwarzacEfekty = true;
				if (Board.czyJezykPolski) {
					ustawieniaEfektow.setText("Efekty dzwiêkowe w³¹czone");
					Board.setWlaczenieEfektow("Efekty dzwiêkowe w³¹czone");	
				}
				else {
						ustawieniaEfektow.setText("Sound effects on");
						Board.setWlaczenieEfektow("Sound effects on");
				}
			}
			else {
				Board.czyOdtwarzacEfekty = false;
				if (Board.czyJezykPolski) {
					ustawieniaEfektow.setText("Efekty dzwiêkowe wy³¹czone");
					Board.setWlaczenieEfektow("Efekty dzwiêkowe wy³¹czone");
				}
				else {
					ustawieniaEfektow.setText("Sound effects off");
					Board.setWlaczenieEfektow("Sound effects off");
				}
			}
		}
		if(source==tekstury) {
			if(!Board.czyNormalneTekstury) {
				Board.czyNormalneTekstury = true;		
				if(Board.czyJezykPolski)
				{
					tekstury.setText("Domyœlne");
					Board.setWyborTekstur("Domyœlne");
				}
				else
				{
					tekstury.setText("Normal");
					Board.setWyborTekstur("Normal");
				}
				Board.zatrzymajSoundtrack();
				Board.odtworzSoundtrack();
				parent.repaint();
			}
			else {
				Board.czyNormalneTekstury = false;
				tekstury.setText("Minecraft");
				Board.setWyborTekstur("Minecraft");
				Board.zatrzymajSoundtrack();
				Board.odtworzSoundtrack();
				parent.repaint();
			}
		}
	}

}



