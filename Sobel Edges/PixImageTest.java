package EdgeDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PixImageTest extends JFrame {

	// Initialize JFrame window
	static JFrame window = new JFrame();
	
	public static void main(String[] args) {
		final String dir = System.getProperty("user.dir") + "\\EdgeDetection";
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		File file = new File(dir + "\\image.jpg");
		PixImage img = new PixImage(file);
		BufferedImage buffImg = img.sobelEdges();
		display(buffImg);
	}

	// Displays a BufferedImage to the JFrame
	static void display(BufferedImage img){
		window.setBounds(0, 0, img.getWidth(), img.getHeight());
		window.getContentPane().add(new JLabel(new ImageIcon(img)));
		window.setVisible(true);		
	}
}
