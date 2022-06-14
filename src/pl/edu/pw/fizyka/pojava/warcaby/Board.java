package pl.edu.pw.fizyka.pojava.warcaby;
import java.awt.BasicStroke;
/**
 * @author Jan Lesisz, Michalina Konopka, Karolina Wi¹cek
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Board extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	static int pawnTable[][];
	private JButton start;
	private static JButton options;
	private Board board;
	protected static Color boardColor1;
	protected static Color boardColor2;
	protected static Color pawnColor1;
	protected static Color pawnColor2;
	protected static boolean gameReady;
	private static JButton endGameButton;
	private static JButton endMoveButton;
	private static JButton rulesButton;
	private static JLabel roundTimeLabel;
	private static JLabel pointLabel;
	private static JLabel player1PointsLabel;
	private static JLabel player2PointsLabel;
	protected static int player1points;
	protected static int player2Points;
	protected static Double player1Time;
	protected static Double player2Time;
	private static JLabel player1TimeLabel;
	private static JLabel player2TimeLabel;
	private static JLabel gamer1Label;
	private static JLabel gamer2Label;
	private static JLabel FlagPL;
	private static JLabel FlagEN;
	private Kafelek[][] kafelki;
	static boolean czyObrocone;
	private ImportowanieObrazów tekstury;
	static ImportowanieDzwiekow dzwiek;
	static boolean czyRuchBialych;
	static boolean czyObracacPlansze;
	static String obrotTekst = "W³¹czony Obrót Planszy";
	static String wlaczenieMuzyki = "Muzyka t³a w³¹czona";
	static String wlaczenieEfektow = "Efekty dzwiêkowe w³¹czone";
	static String wlaczenieEfektowTekst = "Efekty dzwiekowe";
	static String wyborTekstur = "Domyœlne";
	static String zmianaJezykaTekst="Switch to English";
	static String wczytajTekst="Wczytaj planszê";
	static String zapiszTekst="Zapisz planszê";
	static String opcjegryTekst="OPCJE GRY";
	static String teksturynapisTekst="Tekstury";
	static String StylAplikacjiTekst="Styl Aplikacji";
	static String muzykaTekst="Muzyka";
	static String glosnoscTekst="Glosnosc[dB]";
	static String jezykTekst="Jezyk";
	static boolean czyNormalneTekstury;
	static boolean czyOdtwarzacMuzyke;
	static boolean czyOdtwarzacEfekty;
	static boolean czyJezykPolski;
	static int glosnoscMuzyki;
	Timer timer;
	static double czasStart;
	double czasKoniec;
	
	public Board() {
		
		super();
		czyObrocone=false;			//przypisanie pocz¹tkowych wartoœci
		czyRuchBialych=true;
		czyObracacPlansze = true;
		czyNormalneTekstury = true;
		czyOdtwarzacMuzyke = true;
		czyOdtwarzacEfekty = true;
		czyJezykPolski=true;
		glosnoscMuzyki = 0;
		pawnTable = new int[8][8]; //stworzenie nowej tablicy do zapisywania planszy
		kafelki = new Kafelek[8][8];
		gameReady = false;
		czasStart=System.currentTimeMillis();
		
		setSize(900, 570);				//ustawienia okna aplikacji
		setLocationRelativeTo(board); //Wyœrodkowanie okna aplikacji
		setLayout(null);				//Rêczne ustawianie layoutu
		setResizable(false);			//zablokowanie zmiany rozmiaru okna
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		start = new JButton("Start");			//przycisk rozpoczynaj¹cy grê
		start.setBounds(530, 430, 150, 30);
		start.setFont(new Font("Calibri", Font.BOLD, 13));
		add(start);
		start.addActionListener(this);
		
		options = new JButton("Opcje");							//przycisk otwieraj¹cy okno z opcjami
		options.setBounds(700, 430, 150, 30);
		options.setFont(new Font("Calibri", Font.BOLD, 13));
		add(options);
		options.addActionListener(this);
		
		boardColor1 = new Color(255, 120, 0);		//przypisanie bazowych kolorów planszy i pionków
		boardColor2 = new Color(61, 43, 31);
		pawnColor1 = Color.BLACK;
		pawnColor2 = Color.WHITE;
		
		endGameButton = new JButton("Rezygnujê");					//przycisk koñcz¹cy grê
		endGameButton.setBounds(530, 100, 150, 30);
		endGameButton.setFont(new Font("Calibri", Font.BOLD, 13));
		add(endGameButton);
		endGameButton.addActionListener(this);
		
		endMoveButton = new JButton("Koniec ruchu");			//przycisk koñcz¹cy ruch gracza
		endMoveButton.setBounds(700, 100, 150, 30);
		endMoveButton.setFont(new Font("Calibri", Font.BOLD, 13));
		add(endMoveButton);
		endMoveButton.addActionListener(this);
		
		rulesButton = new JButton("Zasady Gry");					//przycisk wyœwietlaj¹cy zasady gry
		rulesButton.setBounds(700, 50, 150, 30);
		rulesButton.setFont(new Font("Calibri", Font.BOLD, 13));
		add(rulesButton);
		rulesButton.addActionListener(this);
		
		roundTimeLabel = new JLabel("Czas trwania rundy");				//etykieta wyœwietlaj¹ca napis "czas trwania rundy"
		roundTimeLabel.setBounds(700, 210, 150, 30);
		roundTimeLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		add(roundTimeLabel);
		
		pointLabel = new JLabel("<html> Liczba zdobytych <br/> punktów</html>"); //etykieta wyœwietlaj¹ca napis Liczba zdobytych punktów
		pointLabel.setBounds(700, 300, 150, 45);
		pointLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		add(pointLabel);
		
		gamer1Label = new JLabel("Gracz 1");			//etykieta wyœwietlaj¹ca napis gracz 1
		gamer1Label.setBounds(530, 155, 150, 30);
		gamer1Label.setFont(new Font("Calibri", Font.BOLD, 18));
		add(gamer1Label);
		
		gamer2Label = new JLabel("Gracz 2");				//etykieta wyœwietlaj¹ca napis gracz 2
		gamer2Label.setBounds(630, 155, 150, 30);
		gamer2Label.setFont(new Font("Calibri", Font.BOLD, 18));
		add(gamer2Label);
		
	
		player1PointsLabel = new JLabel("");									//etykieta wyœwietlaj¹ca liczbê punktów gracza 1
		player1PointsLabel.setText(player1PointsLabel.getText()+player1points);
		player1PointsLabel.setBounds(540, 300, 150, 45);
		player1PointsLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		add(player1PointsLabel);
		
	
		player2PointsLabel = new JLabel("");									//etykieta wyœwietlaj¹ca liczbê punktów gracza 2
		player2PointsLabel.setText(player2PointsLabel.getText()+player2Points);
		player2PointsLabel.setBounds(640, 300, 150, 45);
		player2PointsLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		add(player2PointsLabel);
		
		player1Time = 0.0;
		player1TimeLabel = new JLabel("");									//etykieta wyœwietlaj¹ca czas rundy gracza 1
		player1TimeLabel.setText(player1TimeLabel.getText()+player1Time);
		player1TimeLabel.setBounds(535, 210, 150, 45);
		player1TimeLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		add(player1TimeLabel);
		
		player2Time = 0.0;													//etykieta wyœwietlaj¹ca czas rundy gracza 1
		player2TimeLabel = new JLabel("");
		player2TimeLabel.setText(player2TimeLabel.getText()+player2Time);
		player2TimeLabel.setBounds(635, 210, 150, 45);
		player2TimeLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		add(player2TimeLabel);
			
		FlagPL = new JLabel();										//wyœwietlenie obrazków z flagami
		FlagPL.setIcon(new ImageIcon(getClass().getResource("obrazy/PL.jpg")));
        FlagPL.setBounds(530, 50, 50, 30);
        add(FlagPL);
        
        FlagEN = new JLabel();
        FlagEN.setIcon(new ImageIcon(getClass().getResource("obrazy/EN.jpg")));
        FlagEN.setBounds(620, 50, 50, 30);
        add(FlagEN);
        
        for (int i = 0; i < 8; i++) {						//stworzenie jFrame dla ka¿dego pola
			for (int j = 0; j < 8; j++) {
				kafelki[i][j] = new Kafelek(this, i, j);
				add(kafelki[i][j]);
			}
		}
        tekstury=new ImportowanieObrazów();
        dzwiek = new ImportowanieDzwiekow();
        odtworzSoundtrack();
    	
		timer = new Timer(20, null);
		setVisible(true);			//ustawienie widocznoœci okna
		
		
	}
	//metoda ustawiaj¹ca pionki w pozycji pocz¹tkowej
	 static void setBoard() {
		for (int j = 0; j < 8; j++)			//przypisanie wartoœci wszystkich pól na 0 (puste pole)
			for (int i = 0; i < 8; i++) {
				pawnTable[i][j] = 0;
			}
		for (int j = 0; j < 3; j++)			//przypisanie wartoœci 1 polom startowym czarnych pionków 
			for (int i = 0; i < 8; i++) {
				if ((i + j) % 2 == 0)
					pawnTable[i][j] = 1;
			}

		for (int j = 5; j < 8; j++) {		//przypisanie wartoœci 2 polom startowym bia³ych pionków
			for (int i = 0; i < 8; i++) {
				if ((i + j) % 2 == 0)
					pawnTable[i][j] = 2;
			}
		}
		player1points=0;
		player2Points=0;
		gameReady = true;
		czyRuchBialych= true;
		czyObrocone = false;
		player1Time=0.0;			//ustawienie gotowoœci gry
		player2Time = 0.0;
		czasStart=System.currentTimeMillis();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g);
		g2d.setColor(Color.BLACK);	
		g2d.fillRect(10, 40, 505, 505);		//wype³nienie t³a planszy kolorem czarnym
		rysujPlanszê(g2d);
		rysujPionki(g2d);
		rysujMozliwyRuch(g2d);
		player2PointsLabel.setText(""+player2Points);		
		player1PointsLabel.setText(""+player1points);
		player1TimeLabel.setText(""+player1Time);
		player2TimeLabel.setText(""+player2Time);
	}
	
	private void rysujMozliwyRuch(Graphics2D g2d) {
		Rectangle2D rectangle = new Rectangle2D.Double();
		
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if (pawnTable[i][j] == 3 || pawnTable[i][j] == 4 || pawnTable[i][j] == 8 || pawnTable[i][j] == 9) {
					g2d.setColor(Color.BLUE);
					rectangle.setRect(kafelki[i][j].x + 3, kafelki[i][j].y + 25, kafelki[i][j].width, kafelki[i][j].height);
					g2d.setStroke(new BasicStroke(3));
					g2d.draw(rectangle);
				}
				if (pawnTable[i][j] == 5) {
					g2d.setColor(Color.GREEN);
					rectangle.setRect(kafelki[i][j].x + 3, kafelki[i][j].y + 25, kafelki[i][j].width, kafelki[i][j].height);
					g2d.setStroke(new BasicStroke(3));
					g2d.draw(rectangle);
					}
			}
	}
	
	private void rysujPionki(Graphics2D g2d) {
		if (czyNormalneTekstury) {
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) 
				{			
					if ( pawnTable[i][j] == 2 || pawnTable[i][j] == 4)									
				     	g2d.drawImage(tekstury.getTeksturaBialychPionkow1(), null, 17 + 63 * i,47 + 63 * j );				
					
					if (pawnTable[i][j] == 1  || pawnTable[i][j] == 3) 			
						g2d.drawImage(tekstury.getTeksturaCzarnychPionkow1(), null, 17 + 63 * i,47 + 63 * j );				
					
					if (pawnTable[i][j] == 6 || pawnTable[i][j] == 8)				
					    g2d.drawImage(tekstury.getTeksturaBialejDamki1(), null, 17 + 63 * i,47 + 63 * j );
						
					if(pawnTable[i][j] == 7 || pawnTable[i][j] == 9)
						g2d.drawImage(tekstury.getTeksturaCzarnejDamki1(), null, 17 + 63 * i,47 + 63 * j );
					
						
				}
			}
		}
		else {
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) 
				{			
					if ( pawnTable[i][j] == 2 || pawnTable[i][j] == 4)									
				     	g2d.drawImage(tekstury.getTeksturaVillager(), null, 17 + 63 * i,47 + 63 * j );				
					
					if (pawnTable[i][j] == 1  || pawnTable[i][j] == 3) 			
						g2d.drawImage(tekstury.getTeksturaCzarnyVillager(), null, 17 + 63 * i,47 + 63 * j );				
					
					if (pawnTable[i][j] == 6 || pawnTable[i][j] == 8)				
					    g2d.drawImage(tekstury.getTeksturaGolem(), null, 17 + 63 * i,47 + 63 * j );
						
					if(pawnTable[i][j] == 7 || pawnTable[i][j] == 9)
						g2d.drawImage(tekstury.getTeksturaCzarnyGolem(), null, 17 + 63 * i,47 + 63 * j );
					
						
				}
			}
		}
	}
	
	private void rysujPlanszê(Graphics2D g2d) 
	{
		if (czyNormalneTekstury) {
			for (int i = 0; i < 8; i++) {	
				for (int j = 0; j < 8; j++) {
					if ((i + j) % 2 == 0)					
				      g2d.drawImage(tekstury.getTeksturaJasneDrewno(), null, 11 + 63 * i,41 + 63 * j);				
					else							
				      g2d.drawImage(tekstury.getTeksturaCiemneDrewno(), null, 11 + 63 * i,41 + 63 * j);
					
	
				}
			}
		}
		else{
			for (int i = 0; i < 8; i++) {	
				for (int j = 0; j < 8; j++) {
					if ((i + j) % 2 == 0)					
				      g2d.drawImage(tekstury.getTeksturaTrawa(), null, 11 + 63 * i,41 + 63 * j);				
					else							
				      g2d.drawImage(tekstury.getTeksturaMinecraftDrewno(), null, 11 + 63 * i,41 + 63 * j);
					
	
				}
			}
			
		}
	}
	void obrocPlansze() {				//funkcja obracaj¹ca planszê
		int tymczasowaLista[];
		tymczasowaLista = new int[64];
		int a=0;		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tymczasowaLista[a]=pawnTable[i][j];
				a++;
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pawnTable[i][j] = tymczasowaLista[a-1];
				a--;
			}
		}		
	}
	
	public String getObrotTekst() {
		return obrotTekst;
	}
	public static void setObrotTekst(String obrotTekst) {
		Board.obrotTekst = obrotTekst;
	}
	public static String getWlaczenieMuzyki() {
		return wlaczenieMuzyki;
	}
	public static String getWlaczenieEfektowTekst() {
		return wlaczenieEfektowTekst;
	}
	public static void setWlaczenieEfektowTekst(String wlaczenieEfektowTekst) {
		Board.wlaczenieEfektowTekst = wlaczenieEfektowTekst;
	}
	public static void setWlaczenieMuzyki(String wlaczenieMuzyki) {
		Board.wlaczenieMuzyki = wlaczenieMuzyki;
	}
	public static String getWyborTekstur() {
		return wyborTekstur;
	}
	public static void setWyborTekstur(String wyborTekstur) {
		Board.wyborTekstur = wyborTekstur;
	}
	public float getGlosnoscMuzyki() {
		return glosnoscMuzyki;
	}
	public void setGlosnoscMuzyki(int glosnoscMuzyki) {
		Board.glosnoscMuzyki = glosnoscMuzyki;
		}
	
	public static String getWlaczenieEfektow() {
		return wlaczenieEfektow;
	}
	public static void setWlaczenieEfektow(String wlaczenieEfektow) {
		Board.wlaczenieEfektow = wlaczenieEfektow;
	}
	public static int[][] getPawnTable() {
		return pawnTable;
	}
	public static void setPawnTable(int[][] pawnTable) {
		Board.pawnTable = pawnTable;
	}
	public JButton getStart() {
		return start;
	}
	public void setStart(JButton start) {
		this.start = start;
	}
	public JButton getOptions() {
		return options;
	}
	public void setOptions(JButton options) {
		Board.options = options;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public static Color getBoardColor1() {
		return boardColor1;
	}
	public static void setBoardColor1(Color boardColor1) {
		Board.boardColor1 = boardColor1;
	}
	public static Color getBoardColor2() {
		return boardColor2;
	}
	public static void setBoardColor2(Color boardColor2) {
		Board.boardColor2 = boardColor2;
	}
	public static Color getPawnColor1() {
		return pawnColor1;
	}
	public static void setPawnColor1(Color pawnColor1) {
		Board.pawnColor1 = pawnColor1;
	}
	public static Color getPawnColor2() {
		return pawnColor2;
	}
	public static void setPawnColor2(Color pawnColor2) {
		Board.pawnColor2 = pawnColor2;
	}
	public static boolean isGameReady() {
		return gameReady;
	}
	public static void setGameReady(boolean gameReady) {
		Board.gameReady = gameReady;
	}
	public JButton getEndGameButton() {
		return endGameButton;
	}
	public void setEndGameButton(JButton endGameButton) {
		Board.endGameButton = endGameButton;
	}
	public JButton getEndMoveButton() {
		return endMoveButton;
	}
	public void setEndMoveButton(JButton endMoveButton) {
		Board.endMoveButton = endMoveButton;
	}
	public JButton getRulesButton() {
		return rulesButton;
	}
	public void setRulesButton(JButton rulesButton) {
		Board.rulesButton = rulesButton;
	}
	public JLabel getRoundTimeLabel() {
		return roundTimeLabel;
	}
	public void setRoundTimeLabel(JLabel roundTimeLabel) {
		Board.roundTimeLabel = roundTimeLabel;
	}
	public JLabel getPointLabel() {
		return pointLabel;
	}
	public void setPointLabel(JLabel pointLabel) {
		Board.pointLabel = pointLabel;
	}
	public JLabel getPlayer1PointsLabel() {
		return player1PointsLabel;
	}
	public void setPlayer1PointsLabel(JLabel player1PointsLabel) {
		Board.player1PointsLabel = player1PointsLabel;
	}
	public JLabel getPlayer2PointsLabel() {
		return player2PointsLabel;
	}
	public void setPlayer2PointsLabel(JLabel player2PointsLabel) {
		Board.player2PointsLabel = player2PointsLabel;
	}
	public static int getPlayer1points() {
		return player1points;
	}
	public static void setPlayer1points(int player1points) {
		Board.player1points = player1points;
	}
	public static int getPlayer2Points() {
		return player2Points;
	}
	public static void setPlayer2Points(int player2Points) {
		Board.player2Points = player2Points;
	}
	public static Double getPlayer1Time() {
		return player1Time;
	}
	public static void setPlayer1Time(Double player1Time) {
		Board.player1Time = player1Time;
	}
	public static Double getPlayer2Time() {
		return player2Time;
	}
	public static void setPlayer2Time(Double player2Time) {
		Board.player2Time = player2Time;
	}
	public JLabel getPlayer1TimeLabel() {
		return player1TimeLabel;
	}
	public void setPlayer1TimeLabel(JLabel player1TimeLabel) {
		Board.player1TimeLabel = player1TimeLabel;
	}
	public JLabel getPlayer2TimeLabel() {
		return player2TimeLabel;
	}
	public void setPlayer2TimeLabel(JLabel player2TimeLabel) {
		Board.player2TimeLabel = player2TimeLabel;
	}
	public JLabel getGamer1Label() {
		return gamer1Label;
	}
	public void setGamer1Label(JLabel gamer1Label) {
		Board.gamer1Label = gamer1Label;
	}
	public JLabel getGamer2Label() {
		return gamer2Label;
	}
	public void setGamer2Label(JLabel gamer2Label) {
		Board.gamer2Label = gamer2Label;
	}
	public JLabel getFlagPL() {
		return FlagPL;
	}
	public void setFlagPL(JLabel flagPL) {
		FlagPL = flagPL;
	}
	public JLabel getFlagEN() {
		return FlagEN;
	}
	public void setFlagEN(JLabel flagEN) {
		FlagEN = flagEN;
	}
	public Kafelek[][] getKafelki() {
		return kafelki;
	}
	public void setKafelki(Kafelek[][] kafelki) {
		this.kafelki = kafelki;
	}
	public static boolean isCzyObrocone() {
		return czyObrocone;
	}
	public static void setCzyObrocone(boolean czyObrocone) {
		Board.czyObrocone = czyObrocone;
	}
	public ImportowanieObrazów getTekstury() {
		return tekstury;
	}
	public void setTekstury(ImportowanieObrazów tekstury) {
		this.tekstury = tekstury;
	}
	public static ImportowanieDzwiekow getDzwiek() {
		return dzwiek;
	}
	public static void setDzwiek(ImportowanieDzwiekow dzwiek) {
		Board.dzwiek = dzwiek;
	}
	public static boolean isCzyRuchBialych() {
		return czyRuchBialych;
	}
	public static void setCzyRuchBialych(boolean czyRuchBialych) {
		Board.czyRuchBialych = czyRuchBialych;
	}
	public static boolean isCzyObracacPlansze() {
		return czyObracacPlansze;
	}
	public static void setCzyObracacPlansze(boolean czyObracacPlansze) {
		Board.czyObracacPlansze = czyObracacPlansze;
	}
	public static String getZmianaJezykaTekst() {
		return zmianaJezykaTekst;
	}
	public static void setZmianaJezykaTekst(String zmianaJezykaTekst) {
		Board.zmianaJezykaTekst = zmianaJezykaTekst;
	}
	public static String getWczytajTekst() {
		return wczytajTekst;
	}
	public static void setWczytajTekst(String wczytajTekst) {
		Board.wczytajTekst = wczytajTekst;
	}
	public static String getZapiszTekst() {
		return zapiszTekst;
	}
	public static void setZapiszTekst(String zapiszTekst) {
		Board.zapiszTekst = zapiszTekst;
	}
	public static String getOpcjegryTekst() {
		return opcjegryTekst;
	}
	public static void setOpcjegryTekst(String opcjegryTekst) {
		Board.opcjegryTekst = opcjegryTekst;
	}
	public static String getTeksturynapisTekst() {
		return teksturynapisTekst;
	}
	public static void setTeksturynapisTekst(String teksturynapisTekst) {
		Board.teksturynapisTekst = teksturynapisTekst;
	}
	public static String getStylAplikacjiTekst() {
		return StylAplikacjiTekst;
	}
	public static void setStylAplikacjiTekst(String stylAplikacjiTekst) {
		StylAplikacjiTekst = stylAplikacjiTekst;
	}
	public static String getMuzykaTekst() {
		return muzykaTekst;
	}
	public static void setMuzykaTekst(String muzykaTekst) {
		Board.muzykaTekst = muzykaTekst;
	}
	public static String getGlosnoscTekst() {
		return glosnoscTekst;
	}
	public static void setGlosnoscTekst(String glosnoscTekst) {
		Board.glosnoscTekst = glosnoscTekst;
	}
	public static String getJezykTekst() {
		return jezykTekst;
	}
	public static void setJezykTekst(String jezykTekst) {
		Board.jezykTekst = jezykTekst;
	}
	public static boolean isCzyNormalneTekstury() {
		return czyNormalneTekstury;
	}
	public static void setCzyNormalneTekstury(boolean czyNormalneTekstury) {
		Board.czyNormalneTekstury = czyNormalneTekstury;
	}
	public static boolean isCzyOdtwarzacMuzyke() {
		return czyOdtwarzacMuzyke;
	}
	public static void setCzyOdtwarzacMuzyke(boolean czyOdtwarzacMuzyke) {
		Board.czyOdtwarzacMuzyke = czyOdtwarzacMuzyke;
	}
	public static boolean isCzyOdtwarzacEfekty() {
		return czyOdtwarzacEfekty;
	}
	public static void setCzyOdtwarzacEfekty(boolean czyOdtwarzacEfekty) {
		Board.czyOdtwarzacEfekty = czyOdtwarzacEfekty;
	}
	public static boolean isCzyJezykPolski() {
		return czyJezykPolski;
	}
	public static void setCzyJezykPolski(boolean czyJezykPolski) {
		Board.czyJezykPolski = czyJezykPolski;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public static double getCzasStart() {
		return czasStart;
	}
	public static void setCzasStart(double czasStart) {
		Board.czasStart = czasStart;
	}
	public double getCzasKoniec() {
		return czasKoniec;
	}
	public void setCzasKoniec(double czasKoniec) {
		this.czasKoniec = czasKoniec;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	static void odtworzPrzesuniecie() {
		if(czyOdtwarzacEfekty) {
			if (czyNormalneTekstury) {
				dzwiek.dzwiekPrzesuniecia.loop(0);
				dzwiek.dzwiekPrzesuniecia.setMicrosecondPosition(0);
			}
			else {
				dzwiek.dzwiekPrzesunieciaMinecraft.loop(0);
				dzwiek.dzwiekPrzesunieciaMinecraft.setMicrosecondPosition(0);
			}
		}
 	}
	static void odtworzZbicie() {
		if(czyOdtwarzacEfekty) {
			if (czyNormalneTekstury) {
			dzwiek.dzwiekZbicia.start();
			dzwiek.dzwiekZbicia.setMicrosecondPosition(0);
			}
			else {
				dzwiek.dzwiekZbiciaMinecraft.start();
				dzwiek.dzwiekZbiciaMinecraft.setMicrosecondPosition(0);
			}
		}
	}
	static void odtworzRezygnacje() {
		if(czyOdtwarzacEfekty) {
			if(czyNormalneTekstury) {
			dzwiek.dzwiekRezygnacji.start();
			dzwiek.dzwiekRezygnacji.setMicrosecondPosition(0);
			}
			else {
				dzwiek.dzwiekRezygnacjiMinecraft.start();
				dzwiek.dzwiekRezygnacjiMinecraft.setMicrosecondPosition(0);			
			}
		}
	}
	static void odtworzStart() {
		if(czyOdtwarzacEfekty) {
			if(czyNormalneTekstury) {
			dzwiek.dzwiekStartu.start();
			dzwiek.dzwiekStartu.setMicrosecondPosition(0);
			}
			else {
				dzwiek.dzwiekStartuMinecraft.start();
				dzwiek.dzwiekStartuMinecraft.setMicrosecondPosition(0);
			}
		}
	}
	static void odtworzDamka() {
		if(czyOdtwarzacEfekty) {
			if(czyNormalneTekstury) {
			dzwiek.dzwiekDamki.start();
			dzwiek.dzwiekDamki.setMicrosecondPosition(0);
			}
			else {
				dzwiek.dzwiekDamkiMinecraft.start();
				dzwiek.dzwiekDamkiMinecraft.setMicrosecondPosition(0);			
			}
		}
	}
	static void odtworzZwyciestwo() {
		if(czyOdtwarzacEfekty) {
			if(czyNormalneTekstury) {
			dzwiek.dzwiekZwyciestwa.start();
			dzwiek.dzwiekZwyciestwa.setMicrosecondPosition(0);
			}
			else {
			dzwiek.dzwiekZwyciestwaMinecraft.start();
			dzwiek.dzwiekZwyciestwaMinecraft.setMicrosecondPosition(0);
			}
		}
	}
	static void odtworzSoundtrack() {
		if(czyOdtwarzacMuzyke) {
			if(czyNormalneTekstury) {
			dzwiek.dzwiekSoundtrack.loop(10);
			dzwiek.dzwiekSoundtrack.setMicrosecondPosition(0);
			}		
			else {
				dzwiek.dzwiekSoundtrackMinecraft.loop(10);
				dzwiek.dzwiekSoundtrackMinecraft.setMicrosecondPosition(0);				
			}
		}
	}
	static void zatrzymajSoundtrack() {		
			dzwiek.dzwiekSoundtrack.stop();
			dzwiek.dzwiekSoundtrackMinecraft.stop();			
	}
	static void zmianaJezykaFunkcja()
	{
		if(czyJezykPolski)
		{
			rulesButton.setText("Zasady gry");
			endGameButton.setText("Rezygnuje");
			endMoveButton.setText("Koniec ruchu");
			options.setText("Opcje");
			roundTimeLabel.setText("Czas trwania rundy");	
			gamer1Label.setText("Gracz 1");			
			gamer2Label.setText("Gracz 2");
			pointLabel.setText("<html> Liczba zdobytych <br/> punktów</html>"); 


		}
		else
		{
			rulesButton.setText("Game rules");
			endGameButton.setText("Resign");
			endMoveButton.setText("End of turn");
			options.setText("Options");
			roundTimeLabel.setText("Time of turn");	
			gamer1Label.setText("Player 1");			
			gamer2Label.setText("Player 2");
			pointLabel.setText("<html> List of <br/> points</html>"); 

		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); 				//jeden action preformer dla kilku przycisków
		if (source == start) {						//naciœniêcie przycisku start ustawia planszê i j¹ przemalowuje.
			odtworzStart();
			setBoard();
			repaint();
		}
		if (source == options) {					//wywo³anie opcji
			new Opcje(this);
		}
		if (source == endMoveButton) {				//obrót planszy
			if(czyObracacPlansze) {
				obrocPlansze();
				czyObrocone = !czyObrocone;
			}			
			czyRuchBialych = !czyRuchBialych;
			
			czasKoniec=System.currentTimeMillis();
			if(!czyRuchBialych) {
				player1Time = (player1Time+(czasKoniec-czasStart)/1000);
				player1Time = BigDecimal.valueOf(player1Time).setScale(3, RoundingMode.HALF_UP).doubleValue();
			}
			else {
				player2Time = (player2Time+(czasKoniec-czasStart)/1000);
				player2Time = BigDecimal.valueOf(player2Time).setScale(3, RoundingMode.HALF_UP).doubleValue();
			}
			czasStart=System.currentTimeMillis();
			repaint();
			
		}
		if (source ==endGameButton)
		{
			if(gameReady) {
				odtworzRezygnacje();
				if (czyRuchBialych) 
					JOptionPane.showMessageDialog(null, "Wygra³ gracz 2");
				else
					JOptionPane.showMessageDialog(null, "Wygra³ gracz 1");
				setBoard();
				repaint();				
			}		
		}
		if (source == rulesButton) {				//Zasady gry
			new ZasadyGry(this);
		}
	}	
}
