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

//This class is called when the user chooses option 2
//It paints in an animated sequence the four heaps, as well as the swaps.
public class Paint extends JFrame implements MouseListener {
	int[] opt1, opt2, seq1, seq2;
	int time = 255, optSwap, seqSwap, time2 = 255, time3 = 255, time4 = 255;
	BufferedImage buffered;
	Font TrenchFinal = new Font("Analog",1,30), ConfettiFinal = new Font("Analog",1,30), Back = new Font("Analog",1,10); //fail safe
	AudioClip clip;
    
	//constructor takes in heaps and amount of swaps: 
	//optimal heap pre-deletion, optimal heap post-deletion, sequential heap pre-deletion, sequential heap post-deletion
	public Paint(int[] array, int[] array2, int[] array3, int[] arrayFOUR, int swap1, int swap2){
		opt1 = array;
		opt2 = array2;
		seq1 = array3;
		seq2 = arrayFOUR;
		optSwap = swap1;
		seqSwap = swap2;
		setSize(1300,800);
		setUndecorated(true);
		addMouseListener(this);
		try{this.playSound();} catch(Exception e) {System.out.println(e);}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
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
	
	//Plays the funeral song, in memory of the nodes deleted :'(
	public void playSound() throws Exception
	{
		clip = Applet.newAudioClip(new File("src/PaintSound.wav").toURI().toURL());
		clip.play();
	}
	
	//paints whole interface
	public void paint(Graphics windowFinal){
		if(buffered == null) //make buffer so no lag
			buffered = (BufferedImage)(createImage(getWidth(),getHeight()));
		Graphics window = buffered.createGraphics();
		
		window.setColor(Color.WHITE);  //background
		window.fillRect(0,0,1300,800);
		window.setColor(new Color(time,time,time));
		Font font1 = new Font("Dialog",1,30);  //set fonts
		Font font2 = new Font("Dialog",0,20);
		
		window.setFont(font1); //paints opt pre-deletion
		window.drawString("Optimal Method Pre Deletion: ", 90, 300);
		window.setFont(font2);
		window.setColor(new Color(time2,time2,time2));
		for(int a = 0; a < 10; a++){
			window.drawString(opt1[a]+"", 120 + a * 50, 350);
		}
		window.setFont(font1); //opt post-deletion
		window.setColor(new Color(time,time,time2));
		window.drawString("Optimal Method Post Deletion: ", 90, 400);
		window.setFont(font2);
		window.setColor(new Color(time2,time2,time2));
		for(int a = 0; a < 10; a++){
			window.drawString(opt2[a]+"", 120 + a * 50, 450);
		}
		
		window.setFont(font1); //seq pre-deletion
		window.setColor(new Color(time,time2,time));
		window.drawString("Sequential Method Pre Deletion: ", 690, 300);
		window.setFont(font2);
		window.setColor(new Color(time2,time2,time2));
		for(int a = 0; a < 10; a++){
			window.drawString(seq1[a]+"", 720 + a * 50, 350);
		}
		window.setFont(font1); //seq post-deletion
		window.setColor(new Color(time2,time,time));
		window.drawString("Sequential Method Post Deletion: ", 690, 400);
		window.setFont(font2);
		window.setColor(new Color(time2,time2,time2));
		for(int a = 0; a < 10; a++){
			window.drawString(seq2[a]+"", 720 + a * 50, 450);
		}
		
		//display swaps
		window.setFont(font1);
		window.setColor(new Color(time4,time4,time3));
		window.drawString("Optimal Method Swaps: ", 90, 600);
		window.setColor(new Color(time3,time4,time4));
		window.drawString("Sequential Insertion Swaps: ", 690, 600);
		window.setColor(new Color(time4,time4,time4));
		window.drawString(optSwap+"",463,600);
		window.drawString(seqSwap+"",1125,600);
		
		//draw exit button
		window.setColor(Color.RED);
		int[] x = {1250,1150,1100,1150,1250,1300,1250};
		int[] y = {0,0,100,200,200,100,0};
		window.fillPolygon(x,y,7);
		window.setColor(Color.BLACK);
		window.setFont(ConfettiFinal);
		window.drawString("EXIT",1140,120);
		
		//draw back button
		window.setColor(Color.GREEN);
		int[] x1 = {0,25,25,100,100,25,25,0};
		int[] y1 = {50,15,30,30,70,70,95,50};
		window.fillPolygon(x1,y1,8);
		
		window.setColor(Color.WHITE); //back text
		window.fillRect(25,40,65,20);
		window.setColor(Color.BLACK);
		window.setFont(Back);
		window.drawString("Back", 23, 60);
		
		windowFinal.drawImage(buffered,0,0,null);
		if(time > 0){ //animate
			try{
				Thread.sleep(20);
				time--;
				if(time<122) time2-=2;
				if(time2<122) time3-=4;
				if(time3<122) time4 -= 8;
				repaint();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//checks if user clicked on the exit button or the back button, and takes appropriate actions
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		if(x >= 1100 && x <= 1300 && y >= 0 && y <= 200){
			clip.stop();
			setVisible(false);
			dispose();
		} else if(x <= 100 && y >= 15 && y <= 95){
			setVisible(false);
			dispose();
			clip.stop();
			PaintMenu back = new PaintMenu();
		}
	}
}
