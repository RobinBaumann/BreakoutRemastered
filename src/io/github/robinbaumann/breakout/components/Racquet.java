package io.github.robinbaumann.breakout.components;

import io.github.robinbaumann.breakout.views.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Racquet {
    private static final int POS_Y = 650;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    private int posX = 0;
    private int dirX = 0;
    private GamePanel gamePanel;

    public Racquet(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void move() {
        if (this.posX + this.dirX > 0 && this.posX + this.dirX < gamePanel.getWidth() - WIDTH)
            this.posX = this.posX + this.dirX;
    }

    public void paint(Graphics2D g) {
        g.fillRect(this.posX, POS_Y, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        this.dirX = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            this.dirX = -2;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            this.dirX = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.posX, POS_Y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return POS_Y;
    }
}
