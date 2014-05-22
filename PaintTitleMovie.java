import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.applet.Applet;
import java.applet.AudioClip;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

//This class is the title screen, and plays an animation
//Was very hard to get this to work -_-
public class PaintTitleMovie extends JPanel implements MouseListener {

	ImageIcon image;
	Font ConfettiFinal = new Font("Analog", 1, 20); // fail safe
	static JFrame frame = new JFrame();

	//constructor loads my fonts and adds a mouse listener
	public PaintTitleMovie() {
		try {
			Font Confetti = Font.createFont(Font.TRUETYPE_FONT, new File(
					"src/Fonts/Confetti.ttf"));
			ConfettiFinal = Confetti.deriveFont(1, 50);
			image = new ImageIcon(getClass().getResource("src/Title2.gif"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMouseListener(this);
	}

	@Override
	//paints GIF and exit button
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			//g.drawImageIcon(image, 0, 0, this);
			image.paintIcon(this, g, 0, 0);
		}
		// draw exit button
		g.setColor(Color.BLUE);
		g.fillRect(990, 50, 210, 100);
		g.setColor(Color.BLACK);
		g.fillRect(1000, 60, 190, 80);
		g.setColor(Color.WHITE);
		g.setFont(ConfettiFinal);
		g.drawString("Continue", 1000, 120);
	}

	//Sets up frame and calls song to be played
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				frame.add(new PaintTitleMovie());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(1200, 800);
				frame.setUndecorated(true);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				SongTitle s = new SongTitle();
			}
		});
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
	//when user clicks continue
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x = arg0.getX();
		int y = arg0.getY();

		if (x >= 990 && y >= 50 && y <= 150) {
			this.setVisible(false);
			frame.dispose();
			
			PaintMenu load = new PaintMenu(); // load paint menu
		}
	}
}