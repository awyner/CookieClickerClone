import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class Load extends JPanel implements MouseListener, KeyListener{

	int x=740, y1=0, y2=50, y3=100, y4=150, y5=200, y6=250, y7=300, y8=350, l=260;
	int clickMulti = 0;
	int lastClicked = 0;
	double count = 0;
	long clickPass = 0;
	boolean paused = false;
	boolean clicked = false;
	boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false, b6 = false, b7 = false, b8 = false;
	boolean muted = false;
	boolean debug = true;
	private static final long serialVersionUID = 5509155261502497671L;
	private double starcounter = 0;
	private double k = 0;
	private BufferedImage image;
	private BufferedImage imageJump;
	private BufferedImage fire;
	private BufferedImage fireJump;
	private BufferedImage feather;
	private BufferedImage featherJump;
	private BufferedImage[] invinc = new BufferedImage[3];
	private BufferedImage invincJump;
	private BufferedImage[] back = new BufferedImage[4];
	private BufferedImage block;
	private BufferedImage coin;
	private static byte data1[];
	private static byte data2[];
	private static byte data3[];
	private static byte data4[];
	private static byte data5[];
	private static byte data6[];
	private static byte data7[];
	private static byte data8[];
	private static byte data9[];
	private static byte data10[];
	private static AudioFormat form;
	private Clip c;

	public Load() {
		addMouseListener(this);
		try{
			image = ImageIO.read(Load.class.getResource("/mario.png"));
			imageJump = ImageIO.read(Load.class.getResource("/mariojump.png"));
			fire = ImageIO.read(Load.class.getResource("/fireflower.PNG"));
			fireJump = ImageIO.read(Load.class.getResource("/firejump.PNG"));
			feather = ImageIO.read(Load.class.getResource("/feather.PNG"));
			featherJump = ImageIO.read(Load.class.getResource("/featherjump.PNG"));
			invinc[0] = ImageIO.read(Load.class.getResource("/invincibilitystar.PNG"));
			invinc[1] = ImageIO.read(Load.class.getResource("/invincibilitystar1.PNG"));
			invinc[2] = ImageIO.read(Load.class.getResource("/invincibilitystar2.PNG"));
			invincJump = ImageIO.read(Load.class.getResource("/invincibilitystarjump.PNG"));
			block = ImageIO.read(Load.class.getResource("/coinblock.png"));
			coin = ImageIO.read(Load.class.getResource("/coin.png"));
			back[0] = ImageIO.read(Load.class.getResource("/Background 1.png"));
			back[1] = ImageIO.read(Load.class.getResource("/Background 2.png"));
			back[2] = ImageIO.read(Load.class.getResource("/Background 3.png"));
			back[3] = ImageIO.read(Load.class.getResource("/Background 4.png"));
			AudioInputStream redMush;
			AudioInputStream fire;
			AudioInputStream feather;
			AudioInputStream star;
			AudioInputStream luigi;
			AudioInputStream toad; 
			AudioInputStream yoshi;
			AudioInputStream peach;
			AudioInputStream main;
			AudioInputStream debug;

			try {
				redMush = AudioSystem.getAudioInputStream(Load.class.getResource("/RedMushroomSound.wav"));
				byte[] bytes = new byte[redMush.available()];
				redMush.read(bytes);
				data1 = bytes;
				form = redMush.getFormat();

				fire = AudioSystem.getAudioInputStream(Load.class.getResource("/FireSound.wav"));
				byte[] bytes2 = new byte[fire.available()];
				fire.read(bytes2);
				data2 = bytes2;
				form = fire.getFormat();

				feather = AudioSystem.getAudioInputStream(Load.class.getResource("/FeatherSound.wav"));
				byte[] bytes3 = new byte[feather.available()];
				feather.read(bytes3);
				data3 = bytes3;
				form = feather.getFormat();

				star = AudioSystem.getAudioInputStream(Load.class.getResource("/InvincibleSound.wav"));
				byte[] bytes4 = new byte[star.available()];
				star.read(bytes4);
				data4 = bytes4;
				form = star.getFormat();

				luigi = AudioSystem.getAudioInputStream(Load.class.getResource("/LuigiSound.wav"));
				byte[] bytes5 = new byte[luigi.available()];
				luigi.read(bytes5);
				data5 = bytes5;
				form = luigi.getFormat();

				toad = AudioSystem.getAudioInputStream(Load.class.getResource("/ToadSound.wav"));
				byte[] bytes6 = new byte[toad.available()];
				toad.read(bytes6);
				data6 = bytes6;
				form = toad.getFormat();

				yoshi = AudioSystem.getAudioInputStream(Load.class.getResource("/YoshiSound.wav"));
				byte[] bytes7 = new byte[yoshi.available()];
				yoshi.read(bytes7);
				data7 = bytes7;
				form = yoshi.getFormat();

				peach = AudioSystem.getAudioInputStream(Load.class.getResource("/PeachSound.wav"));
				byte[] bytes8 = new byte[peach.available()];
				peach.read(bytes8);
				data8 = bytes8;
				form = peach.getFormat();

				main = AudioSystem.getAudioInputStream(Load.class.getResource("/CoinSound.wav"));
				byte[] bytes9 = new byte[main.available()];
				main.read(bytes9);
				data9 = bytes9;
				form = main.getFormat();

				debug = AudioSystem.getAudioInputStream(Load.class.getResource("/OneUpSound.wav"));
				byte[] bytes10 = new byte[debug.available()];
				debug.read(bytes10);
				data10 = bytes10;
				form = debug.getFormat();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}

		}
		catch(IOException ex){

		}
	}

	@Override
	public void paint(Graphics g1){
		//Draws all of the upgrade boxes and hit box for clicker in the center
		Graphics2D g = (Graphics2D) g1;
		//background
		drawBack(g);
		g.setColor(Color.black);
		g.drawRect(x, y1, l, 40);
		g.drawRect(x, y2, l, 40);
		g.drawRect(x, y3, l, 40);
		g.drawRect(x, y4, l, 40);
		g.drawRect(x, y5, l, 40);
		g.drawRect(x, y6, l, 40);
		g.drawRect(x, y7, l, 40);
		g.drawRect(x, y8, l, 40);
		g.drawString(NumberFormat.getNumberInstance(Locale.US).format((long)count), 500, 30);
		g.drawString("Red Mushroom +50 (40)", x+5, 20);
		g.drawString("Fire Flower +500 (10,000)", x+5, 70);
		g.drawString("Feather +5,000 (100,000)", x+5, 120);
		g.drawString("Invincibility Star +50,000 (1,000,000)", x+5, 170);
		g.drawString("Luigi +50 (500,000)", x+5, 220);
		g.drawString("Toad +500 (5,000,000)", x+5, 270);
		g.drawString("Yoshi +5,000 (50,000,000)", x+5, 320);
		g.drawString("Princess Peach +50,000 (500,000,000)", x+5, 370);
		g.drawImage(block, 400, 150, 100, 110, null);

		//mushroom/default
		if(clicked == false && lastClicked == 0)
			g.drawImage(image, 400, 470, 100, 200, null);
		if (clicked == true && lastClicked == 0)
		{
			g.drawImage(imageJump, 375, 250, 150, 200, null);
			g.drawImage(coin, 420, 65, 60, 80, null);
		}
		//fire flower
		if(clicked == false && lastClicked == 1)
			g.drawImage(fire, 355, 425, 180, 280, null);
		if (clicked == true && lastClicked == 1)
		{
			g.drawImage(fireJump, 345, 235, 200, 300, null);
			g.drawImage(coin, 420, 65, 60, 80, null);
		}
		//feather
		if(clicked == false && lastClicked == 2)
			g.drawImage(feather, 300, 420, 240, 280, null);
		if (clicked == true && lastClicked == 2)
		{
			g.drawImage(featherJump, 370, 240, 150, 225, null);
			g.drawImage(coin, 420, 65, 60, 80, null);
		}
		//invincibility
		if(clicked == false && lastClicked == 3)
		{
			drawInvinc(g);
		}

		if (clicked == true && lastClicked == 3)
		{
			g.drawImage(invincJump, 390, 247, 140, 320, null);
			g.drawImage(coin, 420, 65, 60, 80, null);
		}
		if(clicked == false && lastClicked == 4)
			drawInvinc(g);
		if (clicked == true && lastClicked == 4)
		{
			g.drawImage(invincJump, 390, 247, 140, 320, null);
			g.drawImage(coin, 420, 65, 60, 80, null);
		}
		if(clicked == false && lastClicked == 5)
			drawInvinc(g);
		if (clicked == true && lastClicked == 5)
		{
			g.drawImage(invincJump, 390, 247, 140, 320, null);
			g.drawImage(coin, 420, 65, 60, 80, null);
		}
	}

	public void drawInvinc(Graphics2D g)
	{
		if(!isPaused()){
			starcounter += 0.1;
			if(starcounter>=3)
			{
				starcounter = 0;
			}
			g.drawImage(invinc[(int)starcounter], 380, 450, 150, 230, null);
		}
	}

	public void drawBack(Graphics2D g){
		if(!isPaused())
		{
			k += .05;
			if(k >= 4)
			{
				k = 0;
			}
			g.drawImage(back[(int) k], 0, 0, 1000, 800, null);
		}
	}

	public void playMusic(byte[] data){
		if(!muted){
			try {
				c = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			try {
				c.open(form, data, 0, data.length);
				c.start();
			} catch (LineUnavailableException e1) {

				e1.printStackTrace();
			}
		}

	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println(e.getX());
		//System.out.println(e.getY());
		//Click registration for upgrade boxes
		clicked = false;
		//red mushroom
		if(e.getX() > x && e.getX() < 1000 && e.getY() > y1 && e.getY() < y1+40){
			if(count>=40 || debug)
			{
				clickMulti+=50;
				lastClicked = 0;
				if(!debug)
					count-=40;
				playMusic(data1);
			}
		}
		//fire flower
		if(e.getX() > x && e.getX() < 1000 && e.getY() > y2 && e.getY() < y2+40){
			if(count>=10000 || debug){
				clickMulti+=500;
				lastClicked = 1;
				if(!debug)
					count-=10000;
				playMusic(data2);
			}
		}
		//feather
		if(e.getX() > x && e.getX() < 1000 && e.getY() > y3 && e.getY() < y3+40){
			if(count>=100000 || debug)
			{
				clickMulti+=5000;
				lastClicked = 2;
				if(!debug)
					count-=100000;
				playMusic(data3);
			}
		}
		//invincibility
		if(e.getX() > x && e.getX() < 1000 && e.getY() > y4 && e.getY() < y4+40){
			if(count>=1000000 || debug)
			{
				clickMulti+=50000;
				lastClicked = 3;
				if(!debug)
					count-=1000000;
				playMusic(data4);
			}
		}
		//luigi
		if(e.getX() > x && e.getX() < 1000 && e.getY() > y5 && e.getY() < y5+40){
			if(count>=500000 || debug)
			{
				clickPass+=50;
				if(!debug)
					count-=500000;
				playMusic(data5);
			}
		}
		//toad
		if(e.getX() > x && e.getX() < 1000 && e.getY() > y6 && e.getY() < y6+40){
			if(count>=5000000 || debug)
			{
				clickPass+=500;
				if(!debug)
					count-=5000000;
				playMusic(data6);
			}
		}
		//yoshi
		if(e.getX() > x && e.getX() < 1000 && e.getY() > y7 && e.getY() < y7+40){
			if(count>=50000000 || debug)
			{
				clickPass+=5000;
				if(!debug)
					count-=50000000;
				playMusic(data7);
			}
		}
		//peach
		if(e.getX() > x && e.getX() < 1000 && e.getY() > y8 && e.getY() < y8+40){
			if(count>=500000000 || debug)
			{
				clickPass+=50000;
				if(!debug)
					count-=500000000;
				playMusic(data8);
			}
		}

		//mario
		if(e.getX() > 400 && e.getX() < 600 && e.getY() > 250 && e.getY() < 450){
			count+=clickMulti+1;
			playMusic(data9);
		}
	}

	public void passive(long counter)
	{
		//Counter for passive upgrades, displays 30 times per second
		if(counter%2==0)
			count+=clickPass/30.0;
	}

	public boolean isPaused() {
		return paused;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Pauses the game
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			paused = !paused;
		//Closes the game
		if(e.getKeyCode() == KeyEvent.VK_Q)
			System.exit(0);
		if(e.getKeyCode() == KeyEvent.VK_M)
			muted = !muted;
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			debug = !debug;
			try {
				c = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				c.open(form, data10, 0, data10.length);
				c.start();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX() > 350 && e.getX() < 600 && e.getY() > 450 && e.getY() < 700)
		{
			clicked = true;
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

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}