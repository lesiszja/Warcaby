package pl.edu.pw.fizyka.pojava.warcaby;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ImportowanieDzwiekow  {

	AudioInputStream streamPrzesuniecia;
	Clip dzwiekPrzesuniecia;
	AudioInputStream streamZbicia;
	Clip dzwiekZbicia;
	AudioInputStream streamRezygnacji;
	Clip dzwiekRezygnacji;
	AudioInputStream streamStartu;
	Clip dzwiekStartu;
	AudioInputStream streamDamki;
	Clip dzwiekDamki;
	AudioInputStream streamSoundtrack;
	Clip dzwiekSoundtrack;
	AudioInputStream streamZwyciestwa;
	Clip dzwiekZwyciestwa;
	AudioInputStream streamPrzesunieciaMinecraft;
	Clip dzwiekPrzesunieciaMinecraft;
	AudioInputStream streamZbiciaMinecraft;
	Clip dzwiekZbiciaMinecraft;
	AudioInputStream streamRezygnacjiMinecraft;
	Clip dzwiekRezygnacjiMinecraft;
	AudioInputStream streamStartuMinecraft;
	Clip dzwiekStartuMinecraft;
	AudioInputStream streamDamkiMinecraft;
	Clip dzwiekDamkiMinecraft;
	AudioInputStream streamSoundtrackMinecraft;
	Clip dzwiekSoundtrackMinecraft;
	AudioInputStream streamZwyciestwaMinecraft;
	Clip dzwiekZwyciestwaMinecraft;
	static FloatControl gainControl;
	float newGain;
	static FloatControl gainControl2;
	float newGain2;
	public ImportowanieDzwiekow() {
			
         try {
			streamPrzesuniecia = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/bassattack.wav"));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
         try {
        	 dzwiekPrzesuniecia = (Clip) AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
         try {
        	 dzwiekPrzesuniecia.open(streamPrzesuniecia);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
         //-----------------------------------------------------------------------//
         
         try {
 			streamZbicia = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/bicie.wav"));
 		} catch (UnsupportedAudioFileException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
          try {
         	 dzwiekZbicia = (Clip) AudioSystem.getClip();
 		} catch (LineUnavailableException e) {
 			e.printStackTrace();
 		}
          try {
         	 dzwiekZbicia.open(streamZbicia);
 			} catch (LineUnavailableException e) {
 				e.printStackTrace();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
          
        //-----------------------------------------------------------------------//
          
          try {
  			streamRezygnacji = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/rezygnacja.wav"));
  		} catch (UnsupportedAudioFileException e) {
  			e.printStackTrace();
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
           try {
          	 dzwiekRezygnacji = (Clip) AudioSystem.getClip();
  		} catch (LineUnavailableException e) {
  			e.printStackTrace();
  		}
           try {
          	 dzwiekRezygnacji.open(streamRezygnacji);
  			} catch (LineUnavailableException e) {
  				e.printStackTrace();
  			} catch (IOException e) {
  				e.printStackTrace();
  			}
           
         //-----------------------------------------------------------------------//
           
           try {
   			streamStartu = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/start.wav"));
   		} catch (UnsupportedAudioFileException e) {
   			e.printStackTrace();
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
            try {
           	 dzwiekStartu = (Clip) AudioSystem.getClip();
   		} catch (LineUnavailableException e) {
   			e.printStackTrace();
   		}
            try {
           	 dzwiekStartu.open(streamStartu);
   			} catch (LineUnavailableException e) {
   				e.printStackTrace();
   			} catch (IOException e) {
   				e.printStackTrace();
   			}
            
          //-----------------------------------------------------------------------//
            
            try {
    			streamDamki = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/damka.wav"));
    		} catch (UnsupportedAudioFileException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
             try {
            	 dzwiekDamki = (Clip) AudioSystem.getClip();
    		} catch (LineUnavailableException e) {
    			e.printStackTrace();
    		}
             try {
            	 dzwiekDamki.open(streamDamki);
    			} catch (LineUnavailableException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
             
           //-----------------------------------------------------------------------//
             
             try {
     			streamSoundtrack = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/soundtrack.wav"));
     		} catch (UnsupportedAudioFileException e) {
     			e.printStackTrace();
     		} catch (IOException e) {
     			e.printStackTrace();
     		}
              try {
             	 dzwiekSoundtrack = (Clip) AudioSystem.getClip();
     		} catch (LineUnavailableException e) {
     			e.printStackTrace();
     		}
              try {
             	 dzwiekSoundtrack.open(streamSoundtrack);
     			} catch (LineUnavailableException e) {
     				e.printStackTrace();
     			} catch (IOException e) {
     				e.printStackTrace();
     			}
              
              
              //-----------------------------------------------------------------------//
                
                try {
        			streamZwyciestwa = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/victory.wav"));
        		} catch (UnsupportedAudioFileException e) {
        			e.printStackTrace();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
                 try {
                	 dzwiekZwyciestwa = (Clip) AudioSystem.getClip();
        		} catch (LineUnavailableException e) {
        			e.printStackTrace();
        		}
                 try {
                	 dzwiekZwyciestwa.open(streamZwyciestwa);
        			} catch (LineUnavailableException e) {
        				e.printStackTrace();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
                 //*************************************************************//
                 try {
         			streamPrzesunieciaMinecraft = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/wood1.wav"));
         		} catch (UnsupportedAudioFileException e) {
         			e.printStackTrace();
         		} catch (IOException e) {
         			e.printStackTrace();
         		}
                  try {
                 	 dzwiekPrzesunieciaMinecraft = (Clip) AudioSystem.getClip();
         		} catch (LineUnavailableException e) {
         			e.printStackTrace();
         		}
                  try {
                 	 dzwiekPrzesunieciaMinecraft.open(streamPrzesunieciaMinecraft);
         			} catch (LineUnavailableException e) {
         				e.printStackTrace();
         			} catch (IOException e) {
         				e.printStackTrace();
         			}
                  //-----------------------------------------------------------------------//
                  
                  try {
          			streamZbiciaMinecraft = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/classic_hurt.wav"));
          		} catch (UnsupportedAudioFileException e) {
          			e.printStackTrace();
          		} catch (IOException e) {
          			e.printStackTrace();
          		}
                   try {
                  	 dzwiekZbiciaMinecraft = (Clip) AudioSystem.getClip();
          		} catch (LineUnavailableException e) {
          			e.printStackTrace();
          		}
                   try {
                  	 dzwiekZbiciaMinecraft.open(streamZbiciaMinecraft);
          			} catch (LineUnavailableException e) {
          				e.printStackTrace();
          			} catch (IOException e) {
          				e.printStackTrace();
          			}
                   
                 //-----------------------------------------------------------------------//
                   
                   try {
           			streamRezygnacjiMinecraft = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/idle2.wav"));
           		} catch (UnsupportedAudioFileException e) {
           			e.printStackTrace();
           		} catch (IOException e) {
           			e.printStackTrace();
           		}
                    try {
                   	 dzwiekRezygnacjiMinecraft = (Clip) AudioSystem.getClip();
           		} catch (LineUnavailableException e) {
           			e.printStackTrace();
           		}
                    try {
                   	 dzwiekRezygnacjiMinecraft.open(streamRezygnacjiMinecraft);
           			} catch (LineUnavailableException e) {
           				e.printStackTrace();
           			} catch (IOException e) {
           				e.printStackTrace();
           			}
                    
                  //-----------------------------------------------------------------------//
                    
                    try {
            			streamStartuMinecraft = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/raidhorn_01.wav"));
            		} catch (UnsupportedAudioFileException e) {
            			e.printStackTrace();
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
                     try {
                    	 dzwiekStartuMinecraft = (Clip) AudioSystem.getClip();
            		} catch (LineUnavailableException e) {
            			e.printStackTrace();
            		}
                     try {
                    	 dzwiekStartuMinecraft.open(streamStartuMinecraft);
            			} catch (LineUnavailableException e) {
            				e.printStackTrace();
            			} catch (IOException e) {
            				e.printStackTrace();
            			}
                     
                   //-----------------------------------------------------------------------//
                     
                     try {
             			streamDamkiMinecraft = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/levelup.wav"));
             		} catch (UnsupportedAudioFileException e) {
             			e.printStackTrace();
             		} catch (IOException e) {
             			e.printStackTrace();
             		}
                      try {
                     	 dzwiekDamkiMinecraft = (Clip) AudioSystem.getClip();
             		} catch (LineUnavailableException e) {
             			e.printStackTrace();
             		}
                      try {
                     	 dzwiekDamkiMinecraft.open(streamDamkiMinecraft);
             			} catch (LineUnavailableException e) {
             				e.printStackTrace();
             			} catch (IOException e) {
             				e.printStackTrace();
             			}
                      
                    //-----------------------------------------------------------------------//
                      
                      try {
              			streamSoundtrackMinecraft = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/piano.wav"));
              		} catch (UnsupportedAudioFileException e) {
              			e.printStackTrace();
              		} catch (IOException e) {
              			e.printStackTrace();
              		}
                       try {
                      	 dzwiekSoundtrackMinecraft = (Clip) AudioSystem.getClip();
              		} catch (LineUnavailableException e) {
              			e.printStackTrace();
              		}
                       try {
                      	 dzwiekSoundtrackMinecraft.open(streamSoundtrackMinecraft);
              			} catch (LineUnavailableException e) {
              				e.printStackTrace();
              			} catch (IOException e) {
              				e.printStackTrace();
              			}
                       
                       //-----------------------------------------------------------------------//
                         
                         try {
                 			streamZwyciestwaMinecraft = AudioSystem.getAudioInputStream(getClass().getResource("dzwieki/fortnite.wav"));
                 		} catch (UnsupportedAudioFileException e) {
                 			e.printStackTrace();
                 		} catch (IOException e) {
                 			e.printStackTrace();
                 		}
                          try {
                         	 dzwiekZwyciestwaMinecraft = (Clip) AudioSystem.getClip();
                 		} catch (LineUnavailableException e) {
                 			e.printStackTrace();
                 		}
                          try {
                         	 dzwiekZwyciestwaMinecraft.open(streamZwyciestwaMinecraft);
                 			} catch (LineUnavailableException e) {
                 				e.printStackTrace();
                 			} catch (IOException e) {
                 				e.printStackTrace();
                 			}
                          gainControl = (FloatControl) dzwiekSoundtrack.getControl(FloatControl.Type.MASTER_GAIN);
                          gainControl2 = (FloatControl) dzwiekSoundtrackMinecraft.getControl(FloatControl.Type.MASTER_GAIN);
                          newGain = Math.min(Math.max(Board.glosnoscMuzyki, gainControl.getMinimum()), gainControl.getMaximum());
                          newGain2 = Math.min(Math.max(Board.glosnoscMuzyki, gainControl2.getMinimum()), gainControl2.getMaximum());
                          gainControl.setValue(newGain);
                          gainControl2.setValue(newGain2);
         
         
	}
	static void zmienGlosnosc() {
		gainControl.setValue(Board.glosnoscMuzyki);
		gainControl2.setValue(Board.glosnoscMuzyki);
	}
	
	
	 
}
