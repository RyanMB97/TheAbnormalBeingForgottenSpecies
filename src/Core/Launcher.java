package Core;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher {
	// JFrame related items
	private final String TITLE = "The Abnormal Being [Forgotten Species]";
	private final int WIDTH = 360;
	private final int HEIGHT = 240;
	private final Dimension gameDim = new Dimension(WIDTH, HEIGHT);
	private JFrame frame;

	// Other Items (Buttons, etc)
	private String[] buttonNames = { "New Game", "Load Game", "Options", "Special", "Quit" };
	private JButton[] buttons = new JButton[buttonNames.length];
	private final int buttonWidth = 120, buttonHeight = 30;

	public Launcher() {
		init();
		addButtons();
		addButtonActions();
	}

	public void init() {
		frame = new JFrame(TITLE);
		frame.setSize(gameDim);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
	}

	public void addButtons() {
		for (int i = 0; i < buttonNames.length; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			buttons[i].setBounds((WIDTH / 2) - (buttonWidth / 2), i * (buttonHeight + 5) + (buttonHeight - 5), buttonWidth, buttonHeight);
			frame.add(buttons[i]);
		}
	}

	public void addButtonActions() {
		buttons[0].addActionListener(new ActionListener() { // New Game
					public void actionPerformed(ActionEvent e) {
						new Game().start();
						frame.dispose();
					}
				});

		buttons[1].addActionListener(new ActionListener() { // Load Game
					public void actionPerformed(ActionEvent e) {
					}
				});

		buttons[2].addActionListener(new ActionListener() { // Options
					public void actionPerformed(ActionEvent e) {
					}
				});

		buttons[3].addActionListener(new ActionListener() { // Special
					public void actionPerformed(ActionEvent e) {
					}
				});

		buttons[4].addActionListener(new ActionListener() { // Quit
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		new Launcher();
	}
}