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

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Rectangle getRect() {
        return new Rectangle(posX, posY, image.getWidth(null), image.getHeight(null));
    }
}
