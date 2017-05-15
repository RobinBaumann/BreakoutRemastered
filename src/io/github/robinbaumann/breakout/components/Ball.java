package io.github.robinbaumann.breakout.components;

import io.github.robinbaumann.breakout.views.Board;

import java.awt.*;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Ball {
    private static final int DIAMETER = 30;
    private int posX = 0;
    private int posY = 0;
    private int dirX = 1; // value > 0 : move right, value < 0 : move left
    private int dirY = 1; // value > 0 : move down, value < 0 : move up
    private Board board;

    public Ball(Board board) {
        this.board = board;
    }

    public void move() {
        if (this.posX + this.dirX < 0)
            this.dirX = 1;
        if (this.posX + this.dirX > board.getWidth() - DIAMETER)
            this.dirX = -1;
        if (this.posY + this.dirY < 0)
            this.dirY = 1;
        if (this.posY + this.dirY > board.getHeight() - DIAMETER)
            board.gameOver();
        if (collision()){
            this.dirY = -1;
            this.posY = board.getRacquet().getTopY() - DIAMETER;
        }

        this.posX += this.dirX;
        this.posY += this.dirY;
    }

    private boolean collision() {
        return board.getRacquet().getBounds().intersects(getBounds());
    }

    public void paint(Graphics2D g) {
        g.fillOval(this.posX, this.posY, 30, 30);
    }

    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, DIAMETER, DIAMETER);
    }
}
