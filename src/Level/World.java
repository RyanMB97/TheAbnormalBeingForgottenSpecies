package Level;

import java.awt.Graphics;
import java.util.Random;

import Core.Game;

public class World {
	private Game game;
	private Tile[][] tiles = new Tile[25][25];
	private int worldPixelSize = 25 * 32;

	public World(Game game) {
		setGame(game);
		generateWorld();
	}

	public void generateWorld() {
		for (int x = 0; x < 25; x++) {
			for (int y = 0; y < 25; y++) {
				Random ran = new Random();
				int ranNum = ran.nextInt(5);
				switch (ranNum) {
				case 0:
					tiles[x][y] = new BasicTile(getGame(), x * 32, y * 32);
					break;
				case 1:
					tiles[x][y] = new WallTile(getGame(), x * 32, y * 32);
					break;
				default:
					tiles[x][y] = new BasicTile(getGame(), x * 32, y * 32);
					break;
				}
			}
		}
	}

	public void tick() {
		for (int x = 0; x < 25; x++) {
			for (int y = 0; y < 25; y++) {
				tiles[x][y].tick();
			}
		}
	}

	public void render(Graphics g) {
		for (int x = 0; x < 25; x++) {
			for (int y = 0; y < 25; y++) {
				if (tiles[x][y].isVisible())
					tiles[x][y].render(g);
			}
		}
	}

	// Getters and Setters

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getWorldPixelSize() {
		return worldPixelSize;
	}

	public void setWorldPixelSize(int worldPixelSize) {
		this.worldPixelSize = worldPixelSize;
	}

}