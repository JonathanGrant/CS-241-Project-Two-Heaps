import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//this class plays an audio clip
//this class is separated from the PaintTitleMovie class because they did not work together as one congruent class :(
public class SongTitle extends JFrame { 
	public SongTitle(){ //constructor creates an invisible JFrame window that plays a sound
		setSize(0,0);
		setVisible(false); 
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{AudioClip clip = Applet.newAudioClip(new File("src/TitleSound.wav").toURI().toURL()); //I created this sound file myself using garageband <3
		clip.play();} catch (Exception e){e.printStackTrace();} //plays and doesn't loop
        JOptionPane.showMessageDialog(null,"Press OKAY to stop sound and continue."); //keeps thread alive so song doesn't end
	}
}
