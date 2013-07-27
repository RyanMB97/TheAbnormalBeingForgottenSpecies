package Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public class Key {

		public boolean down, clicked;
		private short absorbs, presses;

		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}

		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}

			if (pressed) {
				presses++;
			}
		}

		public Key() {
			keys.add(this);
		}
	}

	Game game;

	public List<Key> keys = new ArrayList<Key>();

	public Key left = new Key();
	public Key right = new Key();
	public Key up = new Key();
	public Key down = new Key();
	public Key I = new Key();

	public Key itemUp = new Key();
	public Key itemDown = new Key();

	public boolean leftButton = false;
	public boolean rightButton = false;

	public InputHandler(Game game) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
		game.addMouseWheelListener(this);
		game.addKeyListener(this);
		this.game = game;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) { // If left button pressed
			leftButton = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) { // If right button pressed
			rightButton = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) { // If left button pressed
			leftButton = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) { // If right button pressed
			rightButton = false;
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) { // If left button pressed
			leftButton = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) { // If right button pressed
			rightButton = true;
		}
	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseWheelMoved(MouseWheelEvent e) {
	}

	public void tick() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
	}

	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}

	public void toggle(KeyEvent e, boolean pressed) {
		// Movement
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
			left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
			right.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
			up.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
			down.toggle(pressed);
	}

	public void keyPressed(KeyEvent e) {
		toggle(e, true);

		// Misc
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

	public void keyTyped(KeyEvent e) {

	}
}