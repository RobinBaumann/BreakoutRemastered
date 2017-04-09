package io.github.robinbaumann.breakout;

import io.github.robinbaumann.breakout.views.GamePanel;

import javax.swing.*;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Game {
    private static final int width = 1280;
    private static final int height = 720;

    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("Breakout Remastered");
        GamePanel gamePanel = new GamePanel();
        gameFrame.setSize(width, height);
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Game Loop

        while (true) {
            gamePanel.move();
            gamePanel.repaint();
            try {
                Thread.sleep(10);
            } catch(InterruptedException iEx) {
                iEx.printStackTrace();
            }
        }
    }
}
