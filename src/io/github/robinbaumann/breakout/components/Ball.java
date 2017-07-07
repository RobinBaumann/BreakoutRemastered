package io.github.robinbaumann.breakout.components;

import io.github.robinbaumann.breakout.views.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Ball extends Sprite {
    private static final int INIT_X = 1280/2;
    private static final int INIT_Y = 720/2;
    private int dirX = 1; // value > 0 : move right, value < 0 : move left
    private int dirY = 1; // value > 0 : move down, value < 0 : move up
    private Board board;

    public Ball(Board board) {
        this.board = board;

        resetState();

        ImageIcon ii = new ImageIcon("img/ball/ball.png");
        this.image = ii.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void move() {
        if (this.posX + this.dirX < 0)
            this.dirX = 1;
        if (this.posX + this.dirX > board.getWidth() - this.width)
            this.dirX = -1;
        if (this.posY + this.dirY < 0)
            this.dirY = 1;
        if (this.posY + this.dirY > board.getHeight() - this.height)
            board.gameOver();
        if (board.getRacquet().collision()){
            this.dirY = -1;
            this.posY = board.getRacquet().getTopY() - this.height;
        }

        this.posX += this.dirX;
        this.posY += this.dirY;
    }

    private void resetState() {

        this.posX = INIT_X;
        this.posY = INIT_Y;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, this.width, this.height);
    }
}
