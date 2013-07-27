package Core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

public class ResourceManager {
	private BufferedImage playerImage;

	private BufferedImage tileMap;
	private BufferedImage[] tiles;

	public static final int GRASS = 0;
	public static final int WALL = 1;

	Music backgroundMusic;

	public ResourceManager() {
		loadImages();
		loadMedia();
	}

	private void loadImages() {
		setTiles(new BufferedImage[2]);
		try {
			// Load tiles
			tileMap = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Images/Tiles.png")); // Grab the tilemap

			for (byte i = 0; i < getTiles().length; i++) { // For every tile that there should be
				getTiles()[i] = tileMap.getSubimage(i * 32, 0, 32, 32); // Create a subimage from tile map, and store it as a separate image
			}

			setPlayerImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Images/Player.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadMedia() {
		TinySound.init();

		backgroundMusic = TinySound.loadMusic("Music/Background.wav");
	}

	// Getters and Setters

	public BufferedImage getPlayerImage() {
		return playerImage;
	}

	public void setPlayerImage(BufferedImage playerImage) {
		this.playerImage = playerImage;
	}

	public BufferedImage getSpecificTile(int i) {
		return tiles[i];
	}

	public BufferedImage[] getTiles() {
		return tiles;
	}

	public void setTiles(BufferedImage[] tiles) {
		this.tiles = tiles;
	}
}