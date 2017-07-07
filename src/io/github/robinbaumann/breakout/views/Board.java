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
        setDoubleBuffered(true);
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

    public void checkCollision() {
        int destroyedBricks = 0;

        if(ball.getBounds().getMaxY() > game.getHeight()) {
            gameOver();
        }

        for (Brick b:wall.getBricks()) {
            if(b.isDestroyed()){
                destroyedBricks++;
            } else if(!b.isDestroyable()) {
                destroyedBricks++;
            }
        }


        if(wall.getBricks().size() == destroyedBricks) {
            levelFinished();
        }

        if ((ball.getBounds()).intersects(racquet.getBounds())) {

            int racquetLPos = (int) racquet.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = racquetLPos + 8;
            int second = racquetLPos + 16;
            int third = racquetLPos + 24;
            int fourth = racquetLPos + 32;

            if (ballLPos < first) {
                ball.setDirX(-1);
                ball.setDirY(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setDirX(-1);
                ball.setDirY(-1 * ball.getDirY());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setDirX(0);
                ball.setDirY(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setDirX(1);
                ball.setDirY(-1 * ball.getDirY());
            }

            if (ballLPos > fourth) {
                ball.setDirX(1);
                ball.setDirY(-1);
            }
        }

        for (Brick b:wall.getBricks()) {
            if(ball.getBounds().intersects(b.getBounds())) {
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!b.isDestroyed()) {
                    if(b.getBounds().contains(pointRight)) {
                        ball.setDirX(-1);
                    } else if (b.getBounds().contains(pointLeft)) {
                        ball.setDirX(1);
                    }

                    if(b.getBounds().contains(pointTop)) {
                        ball.setDirY(1);
                    } else if(b.getBounds().contains(pointBottom)) {
                        ball.setDirY(-1);
                    }

                    b.setDestroyed(true);
                }

            }
        }

    }

    private void levelFinished() {
        JOptionPane.showMessageDialog(this, "Level Finished", "Level Finished", JOptionPane.YES_NO_OPTION);
    }
}
