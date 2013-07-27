package Level;

import java.awt.Graphics;

import Core.Game;
import Core.ResourceManager;

public class WallTile extends Tile {
	private static final long serialVersionUID = -3141492952501858642L;

	public WallTile(Game game, int x, int y) {
		setGame(game);
		setInitialX(x);
		setInitialY(y);
		setTileID(ResourceManager.WALL);
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