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
    private int hitAmount;
    private boolean destroyable;

    public Brick(BrickBuilder builder) {
        this.posX = builder.posX;
        this.posY = builder.posY;
        this.hitAmount = builder.hitAmount;
        this.destroyable = builder.destroyable;
    }

}
