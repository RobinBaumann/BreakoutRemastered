package io.github.robinbaumann.breakout.components.bricks;

import io.github.robinbaumann.breakout.components.Sprite;

import javax.swing.*;
import java.awt.*;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/10/17.
 */
public class Brick extends Sprite {
    private int hitAmount;
    private boolean destroyable;


    public Brick(BrickBuilder builder) {
        this.posX = builder.posX;
        this.posY = builder.posY;
        this.hitAmount = builder.hitAmount;
        this.destroyable = builder.destroyable;
        ImageIcon ii = new ImageIcon("img/bricks/undestroyable.png");
        if(destroyable) {
            ii = new ImageIcon("img/bricks/brick.png");
        }
        if(hitAmount > 1) {
            ii = new ImageIcon("img/bricks/strong.png");
        }

        this.image = ii.getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);

    }

    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, width, height);
    }

    public void decreaseHitAmount() {

        if(hitAmount>0) {
            this.hitAmount--;
        }

    }


    public boolean isDestroyable() {
        return destroyable;
    }

    public int getHitAmount() {
        return hitAmount;
    }
}
