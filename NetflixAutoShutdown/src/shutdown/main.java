package shutdown;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import com.sun.glass.events.KeyEvent;


public class main {
	
	/* 
	 * 
	 * idle-Time is measured in seconds.
	 * 
	 * */

	

	public static void main(String[] args) throws AWTException {
		Scanner sc  = new Scanner(System.in);
		String s = "SHUTDOWN -S -T 30";  // Needed Variables
		int[][][] current = null;    
		int[][][] last = null;
		boolean changed = true;
		
		System.out.println("Hallo, nach welcher idle-Time(in Sekunden) soll der Computer heruntergefahren werden ?");
		int idleTime = sc.nextInt();
		
		while(changed){  // while the screen is changing do nothing	
			try {
				Thread.sleep(idleTime * 1000);
				current  = pixop.Farben(pixop.getScreen());
				if(last == null){
					last = current;  // just to avoid some null pointer exception when comparing
					continue;
				}
				changed  = !pixop.comparer(current,last);
				if(!changed) {
				pixop.eingabe(KeyEvent.VK_WINDOWS);  //if screen unchanged shutdown the computer
				for(int i = 0 ; i < s.length() ; i++){
					char c = s.charAt(i);
					System.out.println(c);           
					pixop.eingabe(c);
				}
				pixop.eingabe(KeyEvent.VK_ENTER);
				}else {
					last = current;
				}
				
			} catch (AWTException e) {
				System.err.println("Kein Screencapture möglich!");
				e.printStackTrace();
			} catch (InterruptedException e) {  //Gotta Catch' Em all
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
