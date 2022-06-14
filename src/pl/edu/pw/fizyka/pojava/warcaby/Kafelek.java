package pl.edu.pw.fizyka.pojava.warcaby;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Kafelek extends JComponent implements MouseListener {

	/**
	 * 0 - oznaczenie pola pustego 
	 * 1 - oznaczenie pionka czarnego 
	 * 2 - oznaczenie pionka bia³ego 
	 * 3 - oznaczenie wybranego pionka czarnego (wybrany, czyli klikniêty myszk¹ przez gracza) 
	 * 4 - oznaczenie wybranego pionka bia³ego (wybrany, czyli klikniêty myszk¹ przez gracza) 
	 * 5 - oznaczenie pola pustego, na które mo¿na wykonaæ ruch (przesun¹æ pionka)
	 * 6 - oznaczenie bia³ej królówki 
	 * 7 - oznaczenie czarnej królówki 
	 * 8 - oznaczenie wybranej bia³ej królówki 
	 * 9 - oznaczenie wybranej czarnej królówki
	 * Do dodania:
	 * -timery
	 * -skórki
	 * -instrukcja gry
	 * -zmiana jêzyka
	 * -dzwiêki
	 */
	private static final long serialVersionUID = 1L;
	protected Integer x,y;
	protected Integer width = 62;
	protected Integer height = 62;
	protected int _i;
	protected int _j;
	Board plansza;
	ImportowanieDzwiekow dzwiek;
	protected StringBuilder nazwa = new StringBuilder();
	protected static JFrame parent;
	protected static int iBicie1;
	protected static int jBicie1;
	protected static int iBicie2;
	protected static int jBicie2;
	protected static int iBicie3;
	protected static int jBicie3;
	protected static int iBicie4;
	protected static int jBicie4;
	
	public Kafelek(JFrame f, int i, int j) {
		parent = f;
		x = 8 + 63 * i;
		y = 15 + 63 * j;
		_i = i;
		_j = j;
		setBounds(x, y, width, height);
		addMouseListener(this);
		setLayout(null);
		setDoubleBuffered(false);
		setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (Board.pawnTable[_i][_j] != 5) {
			zamienNaNieklikniety();

			
			if (Board.pawnTable[_i][_j] == 1 || Board.pawnTable[_i][_j] == 2 || Board.pawnTable[_i][_j] == 6 || Board.pawnTable[_i][_j] == 7) 
			{
				iBicie1 = -2;
				jBicie1 = -2;
				iBicie2 = -2;
				jBicie2 = -2;
				iBicie3 = -2;
				jBicie3 = -2;
				iBicie4 = -2;
				jBicie4 = -2;
				Board.pawnTable[_i][_j] += 2; //zamiana na klikniêty
				if(Board.czyRuchBialych){
				ruchDamki();	
				ruchBialegoPionka();
				}
				else {
					ruchCzarnegoPionka();
					ruchDamki();
				}
				
				
			}
		}
		// *****************************************************
		else {
			przesunPionek();
			zdejmijZbityPionek();
			zamienNaDamke();
		}
		sprawdzKoniecGry();
		parent.repaint();
	}

	private void sprawdzKoniecGry() {
		if (Board.gameReady) {
			int b = 0;
			int c = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (Board.pawnTable[i][j] == 1 || Board.pawnTable[i][j] == 3 || Board.pawnTable[i][j] == 7 || Board.pawnTable[i][j] == 9)
						c++;
					if (Board.pawnTable[i][j] == 2 || Board.pawnTable[i][j] == 4 || Board.pawnTable[i][j] == 6 || Board.pawnTable[i][j] == 8)
						b++;
				}
			}
			Board.player1points=12-c;
			Board.player2Points=12-b;
			
			if (b == 0) {
				Board.odtworzZwyciestwo();
				JOptionPane.showMessageDialog(null, "Koniec gry! Zwyciê¿y³ gracz 2");
				Board.setBoard();
				
			}
			if (c == 0) {
				Board.odtworzZwyciestwo();
				JOptionPane.showMessageDialog(null, "Koniec gry! Zwyciê¿y³ gracz 1");
				Board.setBoard();
			}
		}
	}

	private void zamienNaDamke() {
		if (Board.czyObrocone) {
			if (_j == 7 && Board.pawnTable[_i][_j] == 2) {
				Board.pawnTable[_i][_j] = 6;
				Board.odtworzDamka();
			}
			if (_j == 0 && Board.pawnTable[_i][_j] == 1) {
				Board.pawnTable[_i][_j] = 7;
				Board.odtworzDamka();
			}
		}
		else {
			if (_j == 0 && Board.pawnTable[_i][_j] == 2) {
				Board.pawnTable[_i][_j] = 6;
				Board.odtworzDamka();
			}
			if (_j == 7 && Board.pawnTable[_i][_j] == 1) {
				Board.pawnTable[_i][_j] = 7;
				Board.odtworzDamka();
			}
			
		}
	}

	private void zdejmijZbityPionek() {
		if ((_i == iBicie1 + 1 || _i == iBicie1 - 1) && (_j == jBicie1 - 1 || _j == jBicie1 + 1)){
			Board.pawnTable[iBicie1][jBicie1] = 0;
			iBicie1 = -2;
			jBicie1 = -2;
			Board.odtworzZbicie();
		}
		if ((_i == iBicie2 + 1 || _i == iBicie2 - 1) && (_j == jBicie2 - 1 || _j == jBicie2 + 1)){
			Board.pawnTable[iBicie2][jBicie2] = 0;
			iBicie2= -2;
			jBicie2 = -2;
			Board.odtworzZbicie();
		}
		if ((_i == iBicie3 + 1 || _i == iBicie3 - 1) && (_j == jBicie3 - 1 || _j == jBicie3 + 1)){
			Board.pawnTable[iBicie3][jBicie3] = 0;
			iBicie3 = -2;
			jBicie3 = -2;
			Board.odtworzZbicie();
		}
		if ((_i == iBicie4 + 1 || _i == iBicie4 - 1) && (_j == jBicie4 - 1 || _j == jBicie4 + 1)){
			Board.pawnTable[iBicie4][jBicie4] = 0;
			iBicie4 = -2;
			jBicie4 = -2;
			Board.odtworzZbicie();
		}
		
	}

	private void przesunPionek() {
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if (Board.pawnTable[i][j] == 4) {
					Board.pawnTable[i][j] = 0;
					Board.pawnTable[_i][_j] = 2;
				}
				if (Board.pawnTable[i][j] == 3) {
					Board.pawnTable[i][j] = 0;
					Board.pawnTable[_i][_j] = 1;
				}
				if (Board.pawnTable[i][j] == 8) {
					Board.pawnTable[i][j] = 0;
					Board.pawnTable[_i][_j] = 6;
				}
				if (Board.pawnTable[i][j] == 9) {
					Board.pawnTable[i][j] = 0;
					Board.pawnTable[_i][_j] = 7;
				}
				if (Board.pawnTable[i][j] == 5)
					Board.pawnTable[i][j] = 0;
			}
		Board.odtworzPrzesuniecie();
	}

	private void ruchCzarnegoPionka() {
		if (Board.pawnTable[_i][_j] == 3) {
			
			if (Board.czyObrocone) {//przy obróconym czarnym i bia³ym bicie zamieniæ
				if (_i != 0 && _i != 7) {
					if (Board.pawnTable[_i - 1][_j - 1] == 0) {
						Board.pawnTable[_i - 1][_j - 1] = 5;
					}
					if (Board.pawnTable[_i + 1][_j - 1] == 0) {
						Board.pawnTable[_i + 1][_j - 1] = 5;
					}
	
				} else if (_i == 0) {
					if (Board.pawnTable[_i + 1][_j - 1] == 0)
						Board.pawnTable[_i + 1][_j - 1] = 5;
				} else if (_i == 7) {
					if (Board.pawnTable[_i - 1][_j - 1] == 0)
						Board.pawnTable[_i - 1][_j - 1] = 5;
				}
				// *****************************************************
				if (_j != 1 && _j!=6 && _j!=7) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) 
						{
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
					}
				}
				else if (_j==6) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) 
						{
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}						
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}						
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}						
					}
				}
				else if (_j==7) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) 
						{
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}						
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}						
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}						
					}
				}
				else if (_j==1) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 1 || _i == 0) 
					{
						
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 6 || _i == 7) {
						
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
					}
				}
			}
			else {
				if (_i != 0 && _i != 7) {
					if (Board.pawnTable[_i - 1][_j + 1] == 0) {
						Board.pawnTable[_i - 1][_j + 1] = 5;
					}
					if (Board.pawnTable[_i + 1][_j + 1] == 0) {
						Board.pawnTable[_i + 1][_j + 1] = 5;
					}
	
				} else if (_i == 0) {
					if (Board.pawnTable[_i + 1][_j + 1] == 0)
						Board.pawnTable[_i + 1][_j + 1] = 5;
				} else if (_i == 7) {
					if (Board.pawnTable[_i - 1][_j + 1] == 0)
						Board.pawnTable[_i - 1][_j + 1] = 5;
				}
				
				if (_j != 6 && _j!=0 && _j!=1) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) 
						{
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
					}
				}
				else if (_j==1) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) 
						{
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j + 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j + 1;
						}						
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j + 1;
						}						
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j + 1;
						}						
					}
				}
				else if (_j==0) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) 
						{
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j + 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j + 1;
						}						
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 2 || Board.pawnTable[_i + 1][_j + 1] == 6)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j + 1;
						}						
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 2 || Board.pawnTable[_i - 1][_j + 1] == 6)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j + 1;
						}						
					}
				}
				else if (_j==6) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j - 1;
						}
					} 
					else if (_i == 1 || _i == 0) 
					{
						
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 2 || Board.pawnTable[_i + 1][_j - 1] == 6)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j - 1;
						}
					} 
					else if (_i == 6 || _i == 7) {
						
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 2 || Board.pawnTable[_i - 1][_j - 1] == 6)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j - 1;
						}
					}
				}
				
			}
		}
	}

	private void ruchBialegoPionka() {
		
		if (Board.pawnTable[_i][_j] == 4) // KLIKNIETY PIONEK BIALY
		{
			if (!Board.czyObrocone) {
				if (_i != 0 && _i != 7) {
					if (Board.pawnTable[_i - 1][_j - 1] == 0) {
						Board.pawnTable[_i - 1][_j - 1] = 5;
					}
					if (Board.pawnTable[_i + 1][_j - 1] == 0) {
						Board.pawnTable[_i + 1][_j - 1] = 5;
					}
	
				} 
				else if (_i == 0) {
					if (Board.pawnTable[_i + 1][_j - 1] == 0)
						Board.pawnTable[_i + 1][_j - 1] = 5;
				} 
				else if (_i == 7) {
					if (Board.pawnTable[_i - 1][_j - 1] == 0)
						Board.pawnTable[_i - 1][_j - 1] = 5;
				}
				// *****************************************************
				if (_j != 1 && _j!=6 && _j!=7) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) 
						{
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 1 || Board.pawnTable[_i - 1][_j + 1] == 7)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 1 || Board.pawnTable[_i + 1][_j + 1] == 7)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 1 || Board.pawnTable[_i + 1][_j + 1] == 7)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 1 || Board.pawnTable[_i - 1][_j + 1] == 7)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
					}
				}
				else if (_j==6) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) 
						{
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}						
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}						
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}						
					}
				}
				else if (_j==7) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) 
						{
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}						
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}						
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}						
					}
				}
				else if (_j==1) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 1 || Board.pawnTable[_i - 1][_j + 1] == 7)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 1 || Board.pawnTable[_i + 1][_j + 1] == 7)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 1 || _i == 0) 
					{
						
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 1 || Board.pawnTable[_i + 1][_j + 1] == 7)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 6 || _i == 7) {
						
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 1 || Board.pawnTable[_i - 1][_j + 1] == 7)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
					}
				}
					
					
			}
			else {
				if (_i != 0 && _i != 7) {
					if (Board.pawnTable[_i - 1][_j + 1] == 0) {
						Board.pawnTable[_i - 1][_j + 1] = 5;
					}
					if (Board.pawnTable[_i + 1][_j + 1] == 0) {
						Board.pawnTable[_i + 1][_j + 1] = 5;
					}
				} else if (_i == 0) {
					if (Board.pawnTable[_i + 1][_j + 1] == 0)
						Board.pawnTable[_i + 1][_j + 1] = 5;
				} else if (_i == 7) {
					if (Board.pawnTable[_i - 1][_j + 1] == 0)
						Board.pawnTable[_i - 1][_j + 1] = 5;
				}
				// *****************************************************
				if (_j != 6 && _j!=0 && _j!=1) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) 
						{
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 1 || Board.pawnTable[_i - 1][_j + 1] == 7)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 1 || Board.pawnTable[_i + 1][_j + 1] == 7)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 1 || Board.pawnTable[_i + 1][_j + 1] == 7)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j + 1;
						}
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j - 1;
						}
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 1 || Board.pawnTable[_i - 1][_j + 1] == 7)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j + 1;
						}
					}
				}
				else if (_j==1 || _j==0) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 1 || Board.pawnTable[_i - 1][_j + 1] == 7)) 
						{
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j + 1;
						}
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 1 || Board.pawnTable[_i + 1][_j + 1] == 7)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j + 1;
						}						
					} 
					else if (_i == 1 || _i == 0) 
					{
						if (Board.pawnTable[_i + 2][_j + 2] == 0 && (Board.pawnTable[_i + 1][_j + 1] == 1 || Board.pawnTable[_i + 1][_j + 1] == 7)) {
							Board.pawnTable[_i + 2][_j + 2] = 5;
							iBicie2 = _i + 1;
							jBicie2 = _j + 1;
						}						
					} 
					else if (_i == 6 || _i == 7) {
						if (Board.pawnTable[_i - 2][_j + 2] == 0 && (Board.pawnTable[_i - 1][_j + 1] == 1 || Board.pawnTable[_i - 1][_j + 1] == 7)) {
							Board.pawnTable[_i - 2][_j + 2] = 5;
							iBicie1 = _i - 1;
							jBicie1 = _j + 1;
						}						
					}
				}
				if (_j==6) {
					if (_i != 0 && _i != 1 && _i != 6 && _i != 7) 
					{
						
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j - 1;
						}
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j - 1;
						}
					} 
					else if (_i == 1 || _i == 0) 
					{
						
						if (Board.pawnTable[_i + 2][_j - 2] == 0 && (Board.pawnTable[_i + 1][_j - 1] == 1 || Board.pawnTable[_i + 1][_j - 1] == 7)) {
							Board.pawnTable[_i + 2][_j - 2] = 5;
							iBicie3 = _i + 1;
							jBicie3 = _j - 1;
						}
					} 
					else if (_i == 6 || _i == 7) {
						
						if (Board.pawnTable[_i - 2][_j - 2] == 0 && (Board.pawnTable[_i - 1][_j - 1] == 1 || Board.pawnTable[_i - 1][_j - 1] == 7)) {
							Board.pawnTable[_i - 2][_j - 2] = 5;
							iBicie4 = _i - 1;
							jBicie4 = _j - 1;
						}
					}
				}
				
			}
		}
		
	}
	
	private void ruchDamki() {
		if(Board.czyRuchBialych) {
			if (Board.pawnTable[_i][_j] == 8) {
				for (int i = 0; i < 4; i++) {
					int tmpX = _i;
					int tmpY = _j;
					while (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8) {
						if (i == 0) {
							tmpX--;
							tmpY--;
						} else if (i == 1) {
							tmpX--;
							tmpY++;
						} else if (i == 2) {
							tmpX++;
							tmpY++;
						} else if (i == 3) {
							tmpX++;
							tmpY--;
						}
						if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
							if (Board.pawnTable[tmpX][tmpY] == 0) {
								Board.pawnTable[tmpX][tmpY] = 5;
							} else {
								if (Board.pawnTable[tmpX][tmpY] == 1 || Board.pawnTable[tmpX][tmpY] == 7) {
									if (i == 0) {
										tmpX--;
										tmpY--;
									} else if (i == 1) {
										tmpX--;
										tmpY++;
									} else if (i == 2) {
										tmpX++;
										tmpY++;
									} else if (i == 3) {
										tmpX++;
										tmpY--;
									}
									if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
										if (Board.pawnTable[tmpX][tmpY] == 0) {
											Board.pawnTable[tmpX][tmpY] = 5;
											if (i == 0) {
												tmpX++;
												tmpY++;
												iBicie1 = tmpX;
												jBicie1 = tmpY;
											} else if (i == 1) {
												tmpX++;
												tmpY--;
												iBicie4 = tmpX;
												jBicie4 = tmpY;
											} else if (i == 2) {
												tmpX--;
												tmpY--;
												iBicie3 = tmpX;
												jBicie3 = tmpY;
											} else if (i == 3) {
												tmpX--;
												tmpY++;
												iBicie2 = tmpX;
												jBicie2 = tmpY;
											}
											
										}
								}
								break;
							}
					}
				}
			}
		}
		else {
			if (Board.pawnTable[_i][_j] == 9) {
				for (int i = 0; i < 4; i++) {
					int tmpX = _i;
					int tmpY = _j;
					while (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8) {
						if (i == 0) {
							tmpX--;
							tmpY--;
						} else if (i == 1) {
							tmpX--;
							tmpY++;
						} else if (i == 2) {
							tmpX++;
							tmpY++;
						} else if (i == 3) {
							tmpX++;
							tmpY--;
						}
						if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
							if (Board.pawnTable[tmpX][tmpY] == 0) {
								Board.pawnTable[tmpX][tmpY] = 5;
							} else {
								if (((Board.pawnTable[tmpX][tmpY] == 1 || Board.pawnTable[tmpX][tmpY] == 7) && Board.pawnTable[_i][_j] == 8)
										|| (Board.pawnTable[tmpX][tmpY] == 2 || Board.pawnTable[tmpX][tmpY] == 6) && Board.pawnTable[_i][_j] == 9) {
									if (i == 0) {
										tmpX--;
										tmpY--;
									} else if (i == 1) {
										tmpX--;
										tmpY++;
									} else if (i == 2) {
										tmpX++;
										tmpY++;
									} else if (i == 3) {
										tmpX++;
										tmpY--;
									}
									if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
										if (Board.pawnTable[tmpX][tmpY] == 0) {
											Board.pawnTable[tmpX][tmpY] = 5;
											if (i == 0) {
												tmpX++;
												tmpY++;
												iBicie1 = tmpX;
												jBicie1 = tmpY;
											} else if (i == 1) {
												tmpX++;
												tmpY--;
												iBicie4 = tmpX;
												jBicie4 = tmpY;
											} else if (i == 2) {
												tmpX--;
												tmpY--;
												iBicie3 = tmpX;
												jBicie3 = tmpY;
											} else if (i == 3) {
												tmpX--;
												tmpY++;
												iBicie2 = tmpX;
												jBicie2 = tmpY;	
											}
										}
								}
								break;
							}
					}
				}
			}
		}
	}
	
	private void zamienNaNieklikniety() {
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if (Board.pawnTable[i][j] == 3 || Board.pawnTable[i][j] == 4 || Board.pawnTable[i][j] == 8 || Board.pawnTable[i][j] == 9) {
					Board.pawnTable[i][j] -= 2;
				}
				if (Board.pawnTable[i][j] == 5) {
					Board.pawnTable[i][j] = 0;
				}
			}
	}	
	
	@Override
	public void mouseExited(MouseEvent e) {
	}	
	@Override
	public void mousePressed(MouseEvent e) {
	}	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

	
