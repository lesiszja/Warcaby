package pl.edu.pw.fizyka.pojava.warcaby;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImportowanieObrazów {
	BufferedImage teksturaBialychPionkow1;
	BufferedImage teksturaCzarnychPionkow1;
	BufferedImage teksturaBialejDamki1;
	BufferedImage teksturaCzarnejDamki1;
	BufferedImage teksturaCiemneDrewno;
	BufferedImage teksturaJasneDrewno;
	BufferedImage teksturaVillager;
	BufferedImage teksturaCzarnyVillager;
	BufferedImage teksturaGolem;
	BufferedImage teksturaCzarnyGolem;
	BufferedImage teksturaMinecraftDrewno;
	BufferedImage teksturaTrawa;
	
	public ImportowanieObrazów() {
		
		  teksturaBialychPionkow1 = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String s = "obrazy/bialypionek.png";
			URL sciezka = getClass().getResource(s);      
	      try {
	            teksturaBialychPionkow1 = ImageIO.read(sciezka);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }	
	      
	      teksturaCzarnychPionkow1 = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String s1 = "obrazy/czarnypionek.png";
			URL sciezka1 = getClass().getResource(s1);      
	      try {
	            teksturaCzarnychPionkow1 = ImageIO.read(sciezka1);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }	
	      
	      teksturaBialejDamki1 = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String s4 = "obrazy/bialadamka.png";
			URL sciezka4 = getClass().getResource(s4);      
	      try {
	            teksturaBialejDamki1 = ImageIO.read(sciezka4);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }
	        
	        
	      
	      teksturaCzarnejDamki1 = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String damka2 = "obrazy/damkaczarna.png";
			URL dama2 = getClass().getResource(damka2);      
	      try {
	            teksturaCzarnejDamki1 = ImageIO.read(dama2);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }	        
	        
	        
	      
	      teksturaCiemneDrewno = new BufferedImage(62,62,BufferedImage.TYPE_INT_RGB);
	        String s2 = "obrazy/teksturaPlanszyciemna1.png";
			URL sciezka2 = getClass().getResource(s2);      
	      try {
	            teksturaCiemneDrewno = ImageIO.read(sciezka2);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }	
	      
	      teksturaJasneDrewno = new BufferedImage(62,62,BufferedImage.TYPE_INT_RGB);
	        String s3 = "obrazy/jasnedrewno.png";
			URL sciezka3 = getClass().getResource(s3);      
	      try {
	            teksturaJasneDrewno = ImageIO.read(sciezka3);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }	
	      
	      teksturaVillager = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String sV = "obrazy/villager.png";
			URL sciezkaV = getClass().getResource(sV);      
	      try {
	            teksturaVillager = ImageIO.read(sciezkaV);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }	
		
	      teksturaCzarnyVillager = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String sCV = "obrazy/zlyvillager.png";
			URL sciezkaCV = getClass().getResource(sCV);      
	      try {
	            teksturaCzarnyVillager = ImageIO.read(sciezkaCV);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }
		
	      
	      teksturaGolem = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String sG = "obrazy/golem.png";
			URL sciezkaG = getClass().getResource(sG);      
	      try {
	            teksturaGolem = ImageIO.read(sciezkaG);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }
	      
	      teksturaCzarnyGolem = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String sCG = "obrazy/zlygolem.png";
			URL sciezkaCG = getClass().getResource(sCG);      
	      try {
	            teksturaCzarnyGolem = ImageIO.read(sciezkaCG);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }
	      
	      teksturaMinecraftDrewno = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String sD = "obrazy/wood.png";
			URL sciezkaD = getClass().getResource(sD);      
	      try {
	            teksturaMinecraftDrewno = ImageIO.read(sciezkaD);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }
	      
	      teksturaTrawa = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	        String sT = "obrazy/grass.png";
			URL sciezkaT = getClass().getResource(sT);      
	      try {
	            teksturaTrawa = ImageIO.read(sciezkaT);
	        } catch (IOException e) {
	            System.err.println("Blad odczytu obrazka");
	            e.printStackTrace();
	        }
	    
	
	
	}
	public BufferedImage getTeksturaCzarnychPionkow1() {
		return teksturaCzarnychPionkow1;
	}
	public void setTeksturaCzarnychPionkow1(BufferedImage teksturaCzarnychPionkow1) {
		this.teksturaCzarnychPionkow1 = teksturaCzarnychPionkow1;
	}
	public BufferedImage getTeksturaBialejDamki1() {
		return teksturaBialejDamki1;
	}
	public void setTeksturaBialejDamki1(BufferedImage teksturaBialejDamki1) {
		this.teksturaBialejDamki1 = teksturaBialejDamki1;
	}
	public BufferedImage getTeksturaCzarnejDamki1() {
		return teksturaCzarnejDamki1;
	}
	public void setTeksturaCzarnejDamki1(BufferedImage teksturaCzarnejDamki1) {
		this.teksturaCzarnejDamki1 = teksturaCzarnejDamki1;
	}
	public BufferedImage getTeksturaJasneDrewno() {
		return teksturaJasneDrewno;
	}
	public void setTeksturaJasneDrewno(BufferedImage teksturaJasneDrewno) {
		this.teksturaJasneDrewno = teksturaJasneDrewno;
	}
	public BufferedImage getTeksturaCiemneDrewno() {
		return teksturaCiemneDrewno;
	}
	public void setTeksturaCiemneDrewno(BufferedImage teksturaCiemneDrewno) {
		this.teksturaCiemneDrewno = teksturaCiemneDrewno;
	}
	public BufferedImage getTeksturaBialychPionkow1() {
		return teksturaBialychPionkow1;
	}
	public void setTeksturaBialychPionkow1(BufferedImage teksturaBialychPionkow1) {
		this.teksturaBialychPionkow1 = teksturaBialychPionkow1;
	}
	public BufferedImage getTeksturaVillager() {
		return teksturaVillager;
	}
	public void setTeksturaVillager(BufferedImage teksturaVillager) {
		this.teksturaVillager = teksturaVillager;
	}
	public BufferedImage getTeksturaCzarnyVillager() {
		return teksturaCzarnyVillager;
	}
	public void setTeksturaCzarnyVillager(BufferedImage teksturaCzarnyVillager) {
		this.teksturaCzarnyVillager = teksturaCzarnyVillager;
	}
	public BufferedImage getTeksturaGolem() {
		return teksturaGolem;
	}
	public void setTeksturaGolem(BufferedImage teksturaGolem) {
		this.teksturaGolem = teksturaGolem;
	}
	public BufferedImage getTeksturaCzarnyGolem() {
		return teksturaCzarnyGolem;
	}
	public void setTeksturaCzarnyGolem(BufferedImage teksturaCzarnyGolem) {
		this.teksturaCzarnyGolem = teksturaCzarnyGolem;
	}
	public BufferedImage getTeksturaMinecraftDrewno() {
		return teksturaMinecraftDrewno;
	}
	public void setTeksturaMinecraftDrewno(BufferedImage teksturaMinecraftDrewno) {
		this.teksturaMinecraftDrewno = teksturaMinecraftDrewno;
	}
	public BufferedImage getTeksturaTrawa() {
		return teksturaTrawa;
	}
	public void setTeksturaTrawa(BufferedImage teksturaTrawa) {
		this.teksturaTrawa = teksturaTrawa;
	}
}
