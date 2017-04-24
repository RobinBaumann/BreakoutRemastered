package io.github.robinbaumann.breakout.views;

import io.github.robinbaumann.breakout.Game;
import io.github.robinbaumann.breakout.components.Ball;
import io.github.robinbaumann.breakout.components.Racquet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class GamePanel extends JPanel {
    private Game game;
    private Ball ball = new Ball(this);
    private Racquet racquet = new Racquet(this);

    public GamePanel(Game game) {
        this.game = game;
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                racquet.keyPressed(keyEvent);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                racquet.keyReleased(keyEvent);
            }
        };
        addKeyListener(keyListener);
        setFocusable(true);
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
        ball.paint(g2d);
        racquet.paint(g2d);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        game.stopGame();
    }


    public Racquet getRacquet() {
        return racquet;
    }

    public void setRacquet(Racquet racquet) {
        this.racquet = racquet;
    }
}
