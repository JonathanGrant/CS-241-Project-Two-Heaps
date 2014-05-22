import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.awt.geom.RoundRectangle2D;

//this class is a Graphical User Interface of the menu standard for the project
//Using custom fonts, buttons, and check boxes, I made the user experience comfortable, intuitive, and a little playful
public class PaintMenu extends JFrame implements MouseListener {
	private BufferedImage buffered; //buffered image allows no lag between each repaint
	public boolean checkOne = false, checkTwo = false; //boolean variables decide if the check marks should be displayed 
	Font TrenchFinal = new Font("Analog", 1, 30), ConfettiFinal = new Font("Analog", 1, 30); //fail safe in case program cannot load my custom fonts
	public PaintMenu(){ //
		super("Jonathan Allen Grant the Great's Programme Deluxariarre");
		setSize(1200,800);
		addMouseListener(this);
		setUndecorated(true); //gets rid of the old GUI so user experience is seamless
		setVisible(true);
		setLocationRelativeTo(null); //centers frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try { //try to get the fonts
			Font Trench = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/Trench.otf"));
			TrenchFinal = Trench.deriveFont(1,50);
			Font Confetti = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/Confetti.ttf"));
			ConfettiFinal = Confetti.deriveFont(1,70);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//classic paint method draws whole interface using Java Graphics
	public void paint(Graphics windowFinal){
		if(buffered == null) //stops lag
			buffered = (BufferedImage)(createImage(getWidth(),getHeight()));
		Graphics window = buffered.createGraphics();
		
		//background
		window.setColor(Color.WHITE);
		window.fillRect(0, 0, 1200, 800);
		
		//draw checkboxes
		window.setColor(Color.RED);
		window.fillRect(100, 200, 100, 100);//1
		window.fillRect(100, 400, 100, 100);//2
		window.setColor(Color.WHITE);
		window.fillRect(110, 210, 80, 80);//1
		window.fillRect(110, 410, 80, 80);//2
		
		//draw submit button
		window.setColor(Color.BLUE);
		window.fillRoundRect(375,600,400,150,40,40);
		
		//set external font
		window.setColor(Color.BLACK);
		window.setFont(TrenchFinal);
		
		//draw strings
		window.drawString("Heaps With Random Numbers", 250, 260);
		window.drawString("Heaps With Sequential Numbers", 250, 460);
		window.setColor(Color.WHITE);
		window.setFont(ConfettiFinal);
		window.drawString("Submit", 470, 700);
		
		//draw exit button
		window.setColor(Color.RED);
		int[] x = {1150,1050,1000,1050,1150,1200,1150};
		int[] y = {0,0,100,200,200,100,0};
		window.fillPolygon(x,y,7);
		window.setColor(Color.BLACK);
		window.drawString("EXIT",1040,120);
				
		//draw checks if checked
		if(checkOne){
			//draw checkmark one
			//polygon int[]
			int[] checkmarkX = {150,75,75,150,275,250,150};
			int[] checkmarkY = {250,175,225,300,150,115,250};
			//fill polygon
			window.setColor(Color.decode("#b20000"));
			window.fillPolygon(checkmarkX,checkmarkY,7);
		}
		if(checkTwo){
			//draw checkmark two
			//polygon int[]
			int[] checkmarkX = {150,75,75,150,275,250,150};
			int[] checkmarkY = {450,375,425,500,350,315,450};
			//fill polygon
			window.setColor(Color.decode("#b20000"));
			window.fillPolygon(checkmarkX,checkmarkY,7);
		}
		
		windowFinal.drawImage(buffered,0,0,null);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		
	}
	
	public void mouseEntered(MouseEvent e)
	{
		
	}
	
	public void mouseExited(MouseEvent e)
	{
		
	}
	
	public void mousePressed(MouseEvent e)
	{
		
	}
	
	//sees where the user clicked, and pursues events if needed
	public void mouseReleased(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		
		if(x >= 100 && x<= 200){
			if(y >= 200 && y <= 300){
				if(!checkOne) checkOne = true;
				else checkOne = false;
				repaint();
			} else if(y >=400 && y <=500){
				if(!checkTwo) checkTwo = true;
				else checkTwo = false;
				repaint();
			}
		}
		
		if(x>=375 && x<775){
			if(y>=600 && y<750){
				String choice;
				if(checkOne && checkTwo){
					try{this.playSound();}catch(Exception e1){e1.printStackTrace();}
					JOptionPane.showMessageDialog(null, "I think you only deserve one function today.");
				} else if(checkOne){
					Project2 run = new Project2();
					Project2.run("1");
					this.dispose();
				} else if(checkTwo){
					Project2 run = new Project2();
					Project2.run("2");
					this.dispose();
				} else{
					try{this.playSound();}catch(Exception e1){e1.printStackTrace();}
					JOptionPane.showMessageDialog(null, "Come on, can't even choose?");
				}
			}
		}
		
		if(x >= 1000 && x <= 1200 && y >= 0 && y <= 200){ //exit
			setVisible(false);
			dispose();
		}
	}
	
	//this class plays an error sound if the user chooses both checkboxes or none
	public void playSound() throws Exception {
		AudioClip clip = Applet.newAudioClip(new File("src/Crash.wav").toURI().toURL());
		clip.play();
	}
}
