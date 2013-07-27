package Level;

import java.awt.Graphics;

import Core.Game;
import Core.ResourceManager;

public class BasicTile extends Tile {
	public BasicTile(Game game, int x, int y) {
		setGame(game);
		setInitialX(x);
		setInitialY(y);
		setTileID(ResourceManager.GRASS);
		setWorldXPos(getInitialX() + getGame().getxOffset());
		setWorldYPos(getInitialY() + getGame().getyOffset());
	}

	void tick() {
		setWorldXPos(getInitialX() + getGame().getxOffset());
		setWorldYPos(getInitialY() + getGame().getyOffset());
		checkVisibility();
		setBounds(getWorldXPos(), getWorldYPos(), getTileSize(), getTileSize());
	}

	void render(Graphics g) {
		g.drawImage(getGame().getResMan().getSpecificTile(getTileID()), getWorldXPos(), getWorldYPos(), getGame());
	}
}