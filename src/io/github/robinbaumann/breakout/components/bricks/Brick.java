package io.github.robinbaumann.breakout.components.bricks;

import javax.swing.*;
import java.awt.*;
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
    private JLabel hitLabel = new JLabel();
    private Color color = Color.GREEN;


    public Brick(BrickBuilder builder) {
        this.posX = builder.posX;
        this.posY = builder.posY;
        this.hitAmount = builder.hitAmount;
        this.destroyable = builder.destroyable;

    }

    public void paint(Graphics2D g) {
        if(this.hitAmount > 1) {
            this.color = Color.YELLOW;
        }
        if(!this.destroyable){
            this.color = Color.RED;
        }
        g.fillRect(this.posX, this.posY, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, WIDTH, HEIGHT);
    }

    public void decreaseHitAmount() {
        this.hitAmount--;
    }

}
