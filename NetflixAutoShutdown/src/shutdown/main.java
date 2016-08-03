package shutdown;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class main {
	
	/* 
	 * This will is where the whole logic of the programm lies
	 * idle-Time is measured in seconds.
	 * */

	

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		System.out.println("Hallo, nach welcher idle-Time(in Sekunden) soll der Computer heruntergefahren werden ?");
		int idleTime = sc.nextInt();
		int[][][] current = null;
		int[][][] last = null;
		boolean changed = true;
		while(changed){	
			try {
				Thread.sleep(idleTime * 1000);
				current  = pixop.Farben(pixop.getScreen());
				if(last == null){
					last = current;
					continue;
				}
				pixop.comparer(current,last);
			} catch (AWTException e) {
				System.err.println("Kein Screencapture möglich!");
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
