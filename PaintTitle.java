import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
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
import java.util.TimerTask;

public class PaintTitle extends JFrame {
	public int time = 0, time2 = 0, color = 0;
	public boolean flag = false;
	private BufferedImage buffered;
	private AudioClip clip = Applet.newAudioClip(new File("src/TitleSound_converted.wav").toURI().toURL());
	
	public PaintTitle() throws Exception{
		super("Welcome!");
		setSize(1200,800);
		this.playSound();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void playSound() throws Exception {
		clip.play();
	}
	
	public void paint(Graphics windowFinal){
		if(buffered == null)
			buffered = (BufferedImage)(createImage(getWidth(),getHeight()));
		Graphics window = buffered.createGraphics();
		
		if(time < 60)		window.setColor(Color.BLACK); //background animation
		if(time > 60 && time < 100  && time%2==0) window.setColor(Color.WHITE);
		else window.setColor(Color.BLACK);
		if(time > 130 && time < 180 && time % 4 == 0) window.setColor(Color.RED);
		else if (time > 130 && time < 180 && time % 4 == 1) window.setColor(Color.GREEN);
		else if (time > 130 && time < 180 && time % 4 == 2) window.setColor(Color.BLUE);
		else if (time > 130 && time < 180 && time % 4 == 3) window.setColor(Color.WHITE);
		if (time > 210 && time < 330 && time %10 ==0) window.setColor(Color.BLACK);
		else if (time > 210 && time < 330 && time %10 ==1) window.setColor(Color.GREEN);
		else if (time > 210 && time < 330 && time %10 ==2) window.setColor(Color.BLUE);
		else if (time > 210 && time < 330 && time %10 ==3) window.setColor(Color.RED);
		else if (time > 210 && time < 330 && time %10 ==4) window.setColor(Color.ORANGE);
		else if (time > 210 && time < 330 && time %10 ==5) window.setColor(Color.YELLOW);
		else if (time > 210 && time < 330 && time %10 ==6) window.setColor(Color.CYAN);
		else if (time > 210 && time < 330 && time %10 ==7) window.setColor(Color.PINK);
		else if (time > 210 && time < 330 && time %10 ==8) window.setColor(Color.WHITE);
		else if (time > 210 && time < 330 && time %10 ==9) window.setColor(Color.MAGENTA);
		if (time > 360 && time < 765) {time2++; window.setColor(new Color(255-time2,255-time2,255-time2));}
		window.fillRect(0, 0, 1200, 800); //background animation
		if(time == 510){
			time2=0;
		}
		if(time >510){
			window.setColor(Color.WHITE);
			window.setFont(new Font("Analog",1,100));
			window.drawString("Welcome!", 400, 400); //Text
		}
		if(time<1450){
			try{Thread.sleep(10);}catch(Exception e){e.printStackTrace();}
		}
		time++;
		if(time >= 1420){
			flag = true;
			//load main menu
			PaintMenu run = new PaintMenu();
			this.dispose();
		}
		windowFinal.drawImage(buffered,0,0,null);
		repaint();
		
	}
}
