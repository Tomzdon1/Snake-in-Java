
import java.applet.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;

public class snake extends JPanel implements ActionListener {

	private Image food;
	private Image head;
	private Image body;
	Random draw = new Random();
	private int size_frame = 900;
	private int[] x = new int[size_frame];
	private int[] y = new int[size_frame];
	private int Height = 490;
	private int Width = 390;
	private int food_x = 0;
	private int food_y = 0;
	private int size;
	private int image_size = 10;
	public int time = 250;
	private Timer timer;
	private boolean right = true;
	private boolean left;
	private boolean up;
	private boolean down;
	private boolean stGame = true;
	private Soound sound;
	private String zd="image/food.png"; 

	public snake() {

		setBackground(Color.black);

		loadImage();
		locateFood();
		StartGame();
	
		addKeyListener(new Keys());
		setFocusable(true);
		
		
	}

	public void loadImage() {
		///ImageIcon meal = new ImageIcon(ResourceLoader.load(zd));
		//food = meal.getImage();
		try{
		food= ImageIO.read(ResourceLoader.load(zd));
		head= ImageIO.read(ResourceLoader.load("image/head.png"));
		body= ImageIO.read(ResourceLoader.load("image/body.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	//	ImageIcon head_1 = new ImageIcon("image/head.png");
		//head = head_1.getImage();

		//ImageIcon body_1 = new ImageIcon("image/body.png");
		//body = body_1.getImage();
	}

	public void StartGame() {

		size = 3;

		for (int i = 0; i < size; i++) {
			x[i] = 100 - i * 10;
			y[i] = 100;
		}

		if (size == 3) {
			time = 400;

		}
		if (size == 4) {
			time = 140;
		}
		timer = new Timer(time, this);
		timer.start();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Drawning(g);

	}

	public void Drawning(Graphics g) {
		if (stGame) {
			g.drawImage(food, food_x, food_y, this);
			for (int i = 0; i < size; i++) {

				if (i == 0) {
					g.drawImage(head, x[i], y[i], this);
				} else {
					g.drawImage(body, x[i], y[i], this);
				}
			}
			Toolkit.getDefaultToolkit().sync();
		} else {

			gameOver(g);
			Soound.sound2.play();
		}
	}

	public void locateFood() {

		int lx = (int) (Math.random() * 29);
		food_x = ((lx * 10));
		int ly = (int) (Math.random() * 29);
		food_y = ((ly * 10));

	}

	public void EatFood() {
		if ((x[0] == food_x) && (y[0] == food_y)) {
			size++;

			locateFood();
		}
	}

	private void move() {
		for (int i = size; i > 0; i--) {
			x[i] = x[(i - 1)];
			y[i] = y[(i - 1)];
		}
		if (left) {
			x[0] -= image_size;
		}
		if (right) {
			x[0] += image_size;
		}
		if (up) {
			y[0] -= image_size;
		}
		if (down) {
			y[0] += image_size;
		}

	}

	public void sound() {
		if ((x[0] == food_x) && (y[0] == food_y)) {

			Soound.sound1.play();
		}

	}

	public void End() {
		for (int i = size; i > 0; i--) {
			if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
				stGame = false;
			}

		}

		if (y[0] >= Height) {
			stGame = false;
		}
		if (y[0] < 0) {
			stGame = false;
		}
		if (x[0] >= Width) {
			stGame = false;
		}
		if (x[0] < 0) {
			stGame = false;
		}
		if (!stGame) {

			timer.stop();

		}
	}

	public void speed() {

		if (size == 5) {

			time = 140;
			timer.stop();
			timer = new Timer(time, this);
			timer.start();

		}
		if (size == 25) {

			time = 70;
			timer.stop();
			timer = new Timer(time, this);
			timer.start();

		}

	}

	public void gameOver(Graphics g) {
		String msg = "Game Over";
		Font Small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(Small);
		g.setColor(Color.white);
		g.setFont(Small);
		g.drawString(msg, 170, 200);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (stGame) {
			End();
			speed();
			sound();
			EatFood();
			move();
		}

		repaint();

	}

	private class Keys extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && (!right)) {
				left = true;
				up = false;
				down = false;
			}

			if ((key == KeyEvent.VK_RIGHT) && (!left)) {
				right = true;
				up = false;
				down = false;
			}

			if ((key == KeyEvent.VK_UP) && (!down)) {
				up = true;
				right = false;
				left = false;
			}

			if ((key == KeyEvent.VK_DOWN) && (!up)) {
				down = true;
				right = false;
				left = false;
			}
		}
	}

}