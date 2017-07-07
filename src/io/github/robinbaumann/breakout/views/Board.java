package io.github.robinbaumann.breakout.views;

import io.github.robinbaumann.breakout.Game;
import io.github.robinbaumann.breakout.components.Ball;
import io.github.robinbaumann.breakout.components.Racquet;
import io.github.robinbaumann.breakout.components.Wall;
import io.github.robinbaumann.breakout.components.bricks.Brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Board extends JPanel {
    private Game game;
    private Ball ball = new Ball(this);
    private Racquet racquet = new Racquet(this);
    private Wall wall = new Wall();

    public Board(Game game) {
        this.game = game;
    }

    public void move() {
        ball.move();
        racquet.move();
    }

    public void reset() {

    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.BLACK);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(ball.getImage(), ball.getPosX(), ball.getPosY(),
                ball.getWidth(), ball.getHeight(), this);
        g2d.drawImage(racquet.getImage(), racquet.getPosX(), racquet.getPosY(),
                racquet.getWidth(), racquet.getHeight(), this);

        for (Brick b: wall.getBricks()) {
            g2d.drawImage(b.getImage(), b.getPosX(), b.getPosY(),
                    b.getWidth(), b.getHeight(), this);
        }

    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        game.stopGame();
    }


    public Ball getBall() {
        return ball;
    }

    public Racquet getRacquet() {
        return racquet;
    }

    public void setRacquet(Racquet racquet) {
        this.racquet = racquet;
    }
}