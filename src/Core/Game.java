package Core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import kuusisto.tinysound.TinySound;
import Entities.Player;
import Level.World;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	// Other classes
	private Player player;
	private InputHandler inputHandler;
	private ResourceManager resMan;
	private World world;

	// Frame related variables
	JFrame frame;
	private final String TITLE = "The Abnormal Being [Forgotten Species]";
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private final Dimension gameDim = new Dimension(WIDTH, HEIGHT);

	// Game Related
	Thread gameThread;
	private boolean running = false;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	// Offsets for scrolling during movement
	private int xOffset = 0, yOffset = 0;

	// FPS and UPS
	int frames = 0;
	int ticks = 0;
	int FPS = 0;
	int UPS = 0;
	double delta = 0D;

	// Used in the "run" method to limit the frame rate to the UPS
	boolean limitFrameRate = false;
	boolean shouldRender;

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		long lastTimer = System.currentTimeMillis();
		delta = 0D;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			// If you want to limit frame rate, shouldRender = false
			shouldRender = false;

			// If the time between ticks = 1, then various things (shouldRender = true, keeps FPS locked at UPS)
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			if (!limitFrameRate && ticks > 0)
				shouldRender = true;

			// If you should render, render!
			if (shouldRender) {
				frames++;
				render();
			}

			// Reset stuff every second for the new "FPS" and "UPS"
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				FPS = frames;
				UPS = ticks;
				frames = 0;
				ticks = 0;
				frame.setTitle(TITLE + " FPS: " + FPS + " UPS: " + UPS);
			}
		}
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		running = false;
		TinySound.shutdown();
		System.exit(0);
	}

	public Game() {
		init();
		initClasses();
	}

	public void init() {
		frame = new JFrame(TITLE + " FPS: " + FPS);
		setMinimumSize(gameDim);
		setMaximumSize(gameDim);
		setPreferredSize(gameDim);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		requestFocus();
	}

	public void initClasses() {
		setPlayer(new Player(this));
		setInputHandler(new InputHandler(this));
		setResMan(new ResourceManager());
		setWorld(new World(this));

		getResMan().backgroundMusic.play(true);
	}

	public void tick() {
		frame.setTitle(TITLE + " FPS: " + FPS + " UPS: " + UPS);

		getWorld().tick();
		getPlayer().tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(4);
			requestFocus();
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		getWorld().render(g);
		getPlayer().render(g);

		g.dispose();
		bs.show();
	}

	// Getters and Setters

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public ResourceManager getResMan() {
		return resMan;
	}

	public void setResMan(ResourceManager resMan) {
		this.resMan = resMan;
	}
}