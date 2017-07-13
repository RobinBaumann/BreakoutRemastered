package io.github.robinbaumann.breakout.views;

import io.github.robinbaumann.breakout.Game;
import io.github.robinbaumann.breakout.components.Ball;
import io.github.robinbaumann.breakout.components.Racquet;
import io.github.robinbaumann.breakout.components.Wall;
import io.github.robinbaumann.breakout.components.bricks.Brick;

import javax.swing.*;
import java.awt.*;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class    Board extends JPanel {
    private Game game;
    private Ball ball = new Ball(this);
    private Racquet racquet = new Racquet(this);
    private Wall wall = new Wall();
    private static final int LIVES = 2;
    private int ramainingLives = 2;
    private int points = 0;
    private int currentLevelNr = 0;

    public Board(Game game) {
        this.game = game;
        setDoubleBuffered(true);
    }

    public void move() {
        ball.move();
        racquet.move();
    }

    public void reset() {
        ball.resetState();
        racquet.resetState();
        this.ramainingLives = LIVES;
        this.points = 0;
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


        for (Brick b : wall.getBricks()) {
            if (b.getHitAmount() != 0) {
                g2d.drawImage(b.getImage(), b.getPosX(), b.getPosY(),
                        b.getWidth(), b.getHeight(), this);
                b.drawHitAmount(g2d);


            }
        }
        paintScoreBoard(g2d);

    }

    private void paintScoreBoard(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 200, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, 200, 50);
        g2d.drawString("Scoreboard:", 12, 15);
        g2d.drawString("Reached Points: " + this.points, 12, 30);
        g2d.drawString("Remaining lives: " + this.ramainingLives, 12, 45);
    }

    public void gameOver() {
        if (this.ramainingLives == 0) {
            JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            this.reset();
            game.stop();
        } else {
            this.ramainingLives--;
            this.ball.resetState();
        }
    }

    public Racquet getRacquet() {
        return racquet;
    }

    public void checkCollision() {
        int destroyedBricks = 0;

        if (ball.getBounds().getMaxY() > game.getHeight()) {
            gameOver();
        }

        for (Brick b : wall.getBricks()) {
            if (b.getHitAmount() == 0) {
                destroyedBricks++;

            } else if (!b.isDestroyable()) {
                destroyedBricks++;

            }
        }


        if (wall.getBricks().size() == destroyedBricks) {
            levelFinished();
        }

        if ((ball.getBounds()).intersects(racquet.getBounds())) {

            int racquetLPos = (int) racquet.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = racquetLPos + 24;
            int second = racquetLPos + 48;
            int third = racquetLPos + 72;
            int fourth = racquetLPos + 96;

            if (ballLPos < first) {
                ball.setDirX(-2);
                ball.setDirY(-2);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setDirX(-2);
                ball.setDirY(-2 * ball.getDirY());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setDirX(0);
                ball.setDirY(-2);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setDirX(2);
                ball.setDirY(-2 * ball.getDirY());
            }

            if (ballLPos > fourth) {
                ball.setDirX(2);
                ball.setDirY(-2);
            }
        }

        for (Brick b : wall.getBricks()) {
            if (ball.getBounds().intersects(b.getBounds())) {
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (b.getHitAmount() != 0) {
                    if (b.getBounds().contains(pointRight)) {
                        ball.setDirX(-1);
                    } else if (b.getBounds().contains(pointLeft)) {
                        ball.setDirX(1);
                    }

                    if (b.getBounds().contains(pointTop)) {
                        ball.setDirY(1);
                    } else if (b.getBounds().contains(pointBottom)) {
                        ball.setDirY(-1);
                    }

                    if (b.isDestroyable()) {
                        b.decreaseHitAmount();
                        points++;
                        repaint();
                    }
                }

            }
        }

    }

    private void levelFinished() {
        currentLevelNr++;
        int continueOption = JOptionPane.showConfirmDialog(this, "Do you want to play another Level?", "Level Finished", JOptionPane.YES_NO_OPTION);
        if(continueOption == 0) {
            reset();
        } else {
            reset();
            game.stop();
            game.setHighscore(currentLevelNr);
        }
    }


    public Wall getWall() {
        return wall;
    }


    public void setWall(Wall wall) {
        this.wall = wall;
    }


}
