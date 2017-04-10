package io.github.robinbaumann.breakout.components.bricks;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/10/17.
 */
public class BrickBuilder {
    static final int WIDTH = 60;
    static final int HEIGHT = 10;
    int posX;
    int posY;
    int dirX;
    int dirY;
    int hitAmount;
    boolean destroyable;
    char moveUpOrSide;


    public BrickBuilder setPosX(int x) { this.posX = x; return this; }
    public BrickBuilder setPosY(int y) { this.posY = y; return this; }
    public BrickBuilder setDirX(int dirX) { this.dirX = dirX; return this; }
    public BrickBuilder setDirY(int dirY) { this.dirY = dirY; return this; }
    public BrickBuilder setHitAmount(int hitAmount) { this.hitAmount = hitAmount; return this; }
    public BrickBuilder setDestroyable(boolean destroyable) { this.destroyable = destroyable; return this; }
    public BrickBuilder setMoveUpOrSide(char moveUpOrSide) { this.moveUpOrSide = moveUpOrSide; return this; }

    public Brick build() {
        return new Brick(this);
    }

}
