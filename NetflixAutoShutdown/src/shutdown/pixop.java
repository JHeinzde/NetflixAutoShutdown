/** 
 * Coding einer Pixelbibliothek zum Botten
 * Codierung der Paramter in der normalen Koordinatenform (x,y)
 * Arrylist ist in dieser Weise gecodet.
 * In Dimension drei folgenden codierung : 
 * [0] = Blau
 * [1] = Grün
 * [2] = Rot!
 * 
 * This is a collection of functions that do screencaptures, compare them and simulate mous and keyboard inputs 
 * It uses RGB Colors. 
 * Note that this implementation is very primitive and inefficent.
 * The Array mapping for the colors is the following : 
 * [0] = blue
 * [1] = green
 * [2] = red
 * 
 */

package shutdown;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class pixop {

	public static Rectangle getScreen() {
		Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit()
				.getScreenSize());
		return screenSize;
	}

	public static Rectangle getRectangle(int x, int y) {
		Rectangle myr = new Rectangle(x, y);
		return myr;
	}

	public static Rectangle getRectangle(int x, int y, int width, int height) {
		Rectangle myr = new Rectangle(x, y, width, height);
		return myr;
	}

	public static int getheight(Rectangle screensize) {
		int height;
		height = screensize.height;
		return height;
	}

	public static int getwidth(Rectangle screensize) {
		int width;
		width = screensize.width;
		return width;
	}

	public static BufferedImage getScreencapture(Rectangle myr)
			throws AWTException {
		Robot ro = new Robot();
		BufferedImage image = ro.createScreenCapture(myr);
		return image;
	}

	public static Color getFarbe(BufferedImage image, int x, int y) {
		Color farbe = new Color(image.getRGB(x, y));
		return farbe;
	}

	public static int[] Farben(Color farbe) {
		int[] farben = new int[3];
		farben[0] = farbe.getBlue();
		farben[1] = farbe.getGreen();
		farben[2] = farbe.getRed();
		return farben;
	}

	public static int[][][] Farben(Rectangle myr) throws AWTException,
			IOException {
		BufferedImage image = getScreencapture(myr);
		imagesaver(image);
		int colorr[][][] = new int[getwidth(myr)][getheight(myr)][3];
		for (int y = 0; y < getheight(myr); y++) {
			for (int x = 0; x < getwidth(myr); x++) {
				Color farbe = getFarbe(image, x, y);
				colorr[x][y][0] = farbe.getBlue();
				colorr[x][y][1] = farbe.getGreen();
				colorr[x][y][2] = farbe.getRed();
			}
		}
		return colorr;
	}

	public static int[][][] farbenreader(String filename) throws IOException, ClassNotFoundException {
		InputStream fis = new FileInputStream(filename);
		ObjectInputStream or = new ObjectInputStream(fis);
		int[][][] farben =  (int[][][]) or.readObject();
		 or.close();
		 return farben;
		}
	

	public static void farbensaver(int farben[][][], String Filename)
			throws IOException {
		File file = new File(Filename);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream o = new ObjectOutputStream(fos);
		o.writeObject(farben);
		o.flush();
		o.close();
		System.out.println("Farben wurden erfolgreich in : " + Filename + " gespeichert.");
	}
	


	public static boolean comparer(int farben[][][], int farben2[][][]
			) {
		for (int z = 0; z < farben[0].length; z++) {
			for (int f = 0; f < farben.length; f++) {
				if (farben[f][z][0] == farben2[f][z][0]) {
				} else {
					 return false;
				}
				if (farben[f][z][1] == farben2[f][z][1]) {

				} else {
					return false;
				}
				if (farben[f][z][2] == farben2[f][z][2]) {
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static void oneclick(int x, int y) throws AWTException {
		Robot ro = new Robot();
		ro.mouseMove(x, y);
		ro.mousePress(InputEvent.BUTTON1_MASK);
		ro.delay(1000);
		ro.mouseRelease(InputEvent.BUTTON1_MASK);
		ro.delay(5000);
	}

	public static void mousemove(int x, int y) throws AWTException {
		Robot ro = new Robot();
		ro.mouseMove(x, y);
		ro.delay(1000);
	}

	public static void doubleclick(int x, int y) throws AWTException {
		Robot ro = new Robot();
		ro.mouseMove(x, y);
		ro.mousePress(InputEvent.BUTTON1_MASK);
		ro.mouseRelease(InputEvent.BUTTON1_MASK);
		ro.delay(1000);
		ro.mousePress(InputEvent.BUTTON1_MASK);
		ro.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public static void imagesaver(BufferedImage image) throws IOException {
		File outputfile = new File("test.jpg");
		ImageIO.write(image, "jpg", outputfile);
	}

	public static int tminutes() {
		java.util.Date now = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"dd.MM.yyyy HH.mm.ss");
		String ausgabe = sdf.format(now);
		String ausgabeminuten = ausgabe.substring(ausgabe.length() - 5,
				ausgabe.length() - 3);
		int minutes = Integer.parseInt(ausgabeminuten);
		return minutes;
	}

	public static void eingabe(int key) throws AWTException {
		Robot ro = new Robot();
		ro.keyPress(key);
		ro.delay(10);
		ro.keyRelease(key);
		ro.delay(1000);
	}
	
	

	
	public static int tseconds() {
		java.util.Date now = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"dd.MM.yyyy HH.mm.ss");
		String ausgabe = sdf.format(now);
		String ausgabeminuten = ausgabe.substring(ausgabe.length() - 2,
				ausgabe.length());
		int minutes = Integer.parseInt(ausgabeminuten);
		return minutes;
	}
}
