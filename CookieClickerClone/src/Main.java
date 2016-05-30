import java.awt.Color;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Main {

	private static JFrame frame;

	public static void main(String[] args) {
		frame = new JFrame("Mario Clicker");
		final Load panel = new Load();
		frame.add(panel);
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.addKeyListener(panel);
		frame.setVisible(true);
		frame.setBackground(Color.WHITE);

		Thread update = new Thread(new Runnable() {
			public void run() {
				long counter = 0;
				while(true) {
					try {
						Thread.sleep(1000/60);
					} catch (Exception e) {
					}
					if(!panel.isPaused()){
						frame.repaint();
						counter++;
						panel.passive(counter);
					}
				}
			}
		});
		update.setDaemon(true); update.start();
	}
}
