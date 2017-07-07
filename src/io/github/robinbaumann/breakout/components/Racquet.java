package io.github.robinbaumann.breakout.components;

import io.github.robinbaumann.breakout.views.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Racquet extends Sprite {
    private int dirX = 0;
    private Board board;

    public Racquet(Board board) {
        this.board = board;
        this.posY = 650;
        this.posX = 1280/2;

        ImageIcon ii = new ImageIcon("img/racquet/racquet.png");
        this.image = ii.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);

    }

    public void move() {
        if (this.posX + this.dirX > 0 && this.posX + this.dirX < board.getWidth() - this.width)
            this.posX = this.posX + this.dirX;
    }

    public void keyReleased(KeyEvent e) {
        this.dirX = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            this.dirX = -3;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            this.dirX = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, this.width, this.height);
    }

    public int getTopY() {
        return this.posY;
    }

}
