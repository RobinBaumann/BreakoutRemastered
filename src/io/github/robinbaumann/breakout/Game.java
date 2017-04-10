package io.github.robinbaumann.breakout;

import io.github.robinbaumann.breakout.views.GamePanel;
import io.github.robinbaumann.breakout.views.LobbyPanel;

import javax.swing.*;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Game  extends JFrame {
    private static final int width = 1280;
    private static final int height = 720;

    public Game(){
        super("Breakout Remastered");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game gameFrame = new Game();
            LobbyPanel lobbyPanel = new LobbyPanel(gameFrame);
            gameFrame.setSize(width, height);
            gameFrame.add(lobbyPanel);
            gameFrame.pack();
            gameFrame.setVisible(true);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

    }

    public void playGame() {
        // Game Loop

        GamePanel gamePanel = new GamePanel();

        this.getContentPane().removeAll();
        this.getContentPane().invalidate();
        this.add(gamePanel);
        this.getContentPane().revalidate();

        while (true) {
            gamePanel.move();
            gamePanel.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException iEx) {
                iEx.printStackTrace();
            }
        }
    }
}
