package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Core.Game;
import Core.ResourceManager;
import Level.Tile;

public class Player extends Rectangle {
	private Game game;
	private int xPos, yPos; // Positions on the screen itself
	private int worldXPos, worldYPos; // Position in pixels in the game-world
	private int worldTileX, worldTileY; // Position in tiles of the game-world
	private int width, height; // Width and Height of the player

	// Movement
	private int movementSpeed;
	private boolean goLeft, goRight, goUp, goDown;
	private boolean canLeft = true, canRight = true, canUp = true, canDown = true;

	public Player(Game game) {
		setGame(game);
		init();
	}

	public void init() {
		width = 32;
		height = 32;
		setxPos(getGame().getWidth() / 2 - width / 2);
		setyPos(getGame().getHeight() / 2 - height / 2);
		setWorldXPos(getGame().getxOffset() - getxPos());
		setWorldYPos(getGame().getyOffset() - getyPos());
		setMovementSpeed(5);
	}

	public void tick() {
		checkForInput();
		applyMovement();
		checkBoundaries();
		checkForCollisions();
		updatePos();
		setBounds(getxPos(), getyPos(), this.width, this.height);
	}

	private void checkForInput() {
		if (getGame().getInputHandler().left.down && getWorldXPos() > 0) {
			setGoLeft(true);
		} else {
			setGoLeft(false);
		}
		if (getGame().getInputHandler().right.down && getWorldXPos() < getGame().getWorld().getWorldPixelSize() - width) {
			setGoRight(true);
		} else {
			setGoRight(false);
		}
		if (getGame().getInputHandler().up.down && getWorldYPos() > 0) {
			setGoUp(true);
		} else {
			setGoUp(false);
		}
		if (getGame().getInputHandler().down.down && getWorldYPos() < getGame().getWorld().getWorldPixelSize() - height) {
			setGoDown(true);
		} else {
			setGoDown(false);
		}
	}

	private void applyMovement() {
		if (isGoLeft() && isCanLeft()) {
			getGame().setxOffset(getGame().getxOffset() + getMovementSpeed());
		}
		if (isGoRight() && isCanRight()) {
			getGame().setxOffset(getGame().getxOffset() - getMovementSpeed());
		}
		if (isGoUp() && isCanUp()) {
			getGame().setyOffset(getGame().getyOffset() + getMovementSpeed());
		}
		if (isGoDown() && isCanDown()) {
			getGame().setyOffset(getGame().getyOffset() - getMovementSpeed());
		}
	}

	private void checkBoundaries() {
		if (getWorldXPos() < 0) {
			getGame().setxOffset(getGame().getxOffset() - 1);
		}
		if (getWorldXPos() > getGame().getWorld().getWorldPixelSize() - height) {
			getGame().setxOffset(getGame().getxOffset() + 1);
		}
		if (getWorldYPos() < 0) {
			getGame().setyOffset(getGame().getyOffset() - 1);
		}
		if (getWorldYPos() > getGame().getWorld().getWorldPixelSize() - width) {
			getGame().setyOffset(getGame().getyOffset() + 1);
		}
	}

	private void checkForCollisions() {
	}

	private void updatePos() {
		setWorldXPos(getxPos() - getGame().getxOffset());
		setWorldYPos(getyPos() - getGame().getyOffset());
	}

	public void render(Graphics g) {
		g.drawImage(getGame().getResMan().getPlayerImage(), getxPos(), getyPos(), getGame());
	}

	// Getters and Setters

	public int getWorldTileY() {
		return worldTileY;
	}

	public void setWorldTileY(int worldTileY) {
		this.worldTileY = worldTileY;
	}

	public int getWorldTileX() {
		return worldTileX;
	}

	public void setWorldTileX(int worldTileX) {
		this.worldTileX = worldTileX;
	}

	public int getWorldYPos() {
		return worldYPos;
	}

	public void setWorldYPos(int worldYPos) {
		this.worldYPos = worldYPos;
	}

	public int getWorldXPos() {
		return worldXPos;
	}

	public void setWorldXPos(int worldXPos) {
		this.worldXPos = worldXPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public boolean isGoLeft() {
		return goLeft;
	}

	public void setGoLeft(boolean goLeft) {
		this.goLeft = goLeft;
	}

	public boolean isGoRight() {
		return goRight;
	}

	public void setGoRight(boolean goRight) {
		this.goRight = goRight;
	}

	public boolean isGoUp() {
		return goUp;
	}

	public void setGoUp(boolean goUp) {
		this.goUp = goUp;
	}

	public boolean isGoDown() {
		return goDown;
	}

	public void setGoDown(boolean goDown) {
		this.goDown = goDown;
	}

	public boolean isCanLeft() {
		return canLeft;
	}

	public void setCanLeft(boolean canLeft) {
		this.canLeft = canLeft;
	}

	public boolean isCanRight() {
		return canRight;
	}

	public void setCanRight(boolean canRight) {
		this.canRight = canRight;
	}

	public boolean isCanUp() {
		return canUp;
	}

	public void setCanUp(boolean canUp) {
		this.canUp = canUp;
	}

	public boolean isCanDown() {
		return canDown;
	}

	public void setCanDown(boolean canDown) {
		this.canDown = canDown;
	}
}