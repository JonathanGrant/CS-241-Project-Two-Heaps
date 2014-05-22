import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

//This class shows the amount of swaps for the first option of heap implementation.
public class PaintOptionOne extends JFrame implements MouseListener { //uses mouselistener so user can exit or go back
	int swaps1 = 0, swaps2 = 0, time = 0;
	int string1x = -2000, string2x = 2100, string1y = -1500, string2y = 1150;
	int string1Vx = 0, string2Vx = 0, string1Vy = 0, string2Vy = 0;
	AudioClip clip; //prepares song clip
	Font TrenchFinal = new Font("Analog",1,30), ConfettiFinal = new Font("Analog",1,30), Back = new Font("Analog",1,10); //fail safe
	//I use only my own custom fonts to make the user experience more friendly
	BufferedImage buffered; //buffered image allows no lag between each repaint
	
	public PaintOptionOne(int swaps1, int swaps2){ //takes in number of swaps for both methods; 1.optimal, 2.sequential.
		this.swaps1=swaps1/20; //average swaps
		this.swaps2=swaps2/20; //for 20 user takes
		addMouseListener(this); //adds mouse listener
		setSize(1200,800);
		setUndecorated(true); //makes the window screen disappear, making my application self relient for functionality
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //makes the screen centered
		
		//set new font
		try {
			Font Trench = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/Trench.otf"));
			TrenchFinal = Trench.deriveFont(1,50);
			Font Confetti = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/Confetti.ttf"));
			ConfettiFinal = Confetti.deriveFont(1,70);
			Font Ubuntu = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/Sofia.otf"));
			Back = Ubuntu.deriveFont(1,30);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//this method plays the whoosh sound to make it seem like the words really fly in
	private void playSound() throws MalformedURLException { 
		clip = Applet.newAudioClip(new File("src/whoosh.wav").toURI().toURL()); //I did not make this sound myself :( but it is only half a second!
		clip.play();
	}

	//this method changes the numbers to make the words fly in more smoothly and not robotic-like
	public void doMath(){ 
		string1x += string1Vx;  //basic physics equations
		string1y += string1Vy;
		string2x += string2Vx;
		string2y += string2Vy;
		
		if(time == 2){ //plays sound at a delayed time to make whole scene more realistic
			try {
				playSound();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		string1Vx = (125-string1x)/5; //locks onto target locations
		string1Vy = (260-string1y)/5;
		string2Vx = (125-string2x)/5;
		string2Vy = (460-string2y)/5;
	}
	
	//this method paints the whole scene and is standard for JFrame
	public void paint(Graphics windowFinal){
		if(buffered == null) //make buffer so no lag
			buffered = (BufferedImage)(createImage(getWidth(),getHeight()));
		Graphics window = buffered.createGraphics();
		
		//Background
		window.setColor(Color.WHITE);
		window.fillRect(0, 0, 1200, 800);
		
		//set external font
		window.setColor(Color.BLACK);
		window.setFont(TrenchFinal);
		
		//draw Strings
		window.drawString("Optimal Method Swap: " + swaps1,string1x,string1y);
		window.drawString("Sequential Method Swap Count Average: " + swaps2,string2x,string2y);
		
		//draw exit button
		window.setColor(Color.RED);
		int[] x = {1150,1050,1000,1050,1150,1200,1150};
		int[] y = {0,0,100,200,200,100,0};
		window.fillPolygon(x,y,7);
		window.setColor(Color.BLACK);
		window.setFont(ConfettiFinal);
		window.drawString("EXIT",1040,120);
		
		//draw back button
		window.setColor(Color.GREEN);
		int[] x1 = {0,25,25,100,100,25,25,0};
		int[] y1 = {50,15,30,30,70,70,95,50};
		window.fillPolygon(x1,y1,8);
		
		window.setColor(Color.WHITE);
		window.fillRect(25,40,65,20);
		window.setColor(Color.BLACK);
		window.setFont(Back);
		window.drawString("Back", 23, 60);
		
		windowFinal.drawImage(buffered,0,0,null);
		
		//Animate
		if(time < 30){
			try{Thread.sleep(50);}catch(Exception e){e.printStackTrace();}
			time++;
			doMath();
			repaint();
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//when mouse is released, checks where it is released, and what actions to take. To exit, or to go back.
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x = arg0.getX();
		int y = arg0.getY();

		if(x >= 1000 && x <= 1200 && y >= 0 && y <= 200){
			setVisible(false); //this makes JFrame invisible
			dispose(); //This closes JFrame
		} else if(x <= 100 && y >= 15 && y <= 95){
			setVisible(false);
			dispose();
			PaintMenu back = new PaintMenu(); //opens back the menu
		}
	}
}
