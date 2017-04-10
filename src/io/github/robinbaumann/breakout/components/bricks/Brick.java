package io.github.robinbaumann.breakout.components.bricks;

import java.util.Optional;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/10/17.
 */
public class Brick {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    private int posX;
    private int posY;
    private int dirX;
    private int dirY;
    private int hitAmount;
    private boolean destroyable;
    private char moveUpOrSide;

    public Brick(BrickBuilder builder) {
        this.posX = builder.posX;
        this.posY = builder.posY;
        this.dirX = builder.dirX;
        this.dirY = builder.dirY;
        this.hitAmount = builder.hitAmount;
        this.destroyable = builder.destroyable;
        this.moveUpOrSide = builder.moveUpOrSide;
    }

}
