package io.github.robinbaumann.breakout.components;

import java.awt.*;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 7/7/17.
 */
public class Sprite {

    protected int posX;
    protected int posY;
    protected int width;
    protected int height;
    protected Image image;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getRect() {
        return new Rectangle(posX, posY, image.getWidth(null), image.getHeight(null));
    }
}
