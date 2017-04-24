package io.github.robinbaumann.breakout;

import io.github.robinbaumann.breakout.views.GamePanel;
import io.github.robinbaumann.breakout.views.LobbyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerAdapter;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Game  extends JFrame {
    private static final int width = 1280;
    private static final int height = 720;
    private static final String LOBBY_PANEL = "Lobby Panel";
    private static final String GAME_PANEL = "Game Panel";
    private boolean run = false;
    private CardLayout cardLayout;
    public JPanel cardPanel = new JPanel();
    public LobbyPanel lobbyPanel;
    public GamePanel gamePanel;

    public Game(){
        super("Breakout Remastered");
        cardPanel.setLayout(new CardLayout());
        cardLayout = (CardLayout) cardPanel.getLayout();
        lobbyPanel = new LobbyPanel(this);
        gamePanel = new GamePanel(this);
        cardPanel.add(lobbyPanel, LOBBY_PANEL);
        cardPanel.add(gamePanel, GAME_PANEL);
        this.add(cardPanel);
        this.pack();
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        cardLayout.show(cardPanel, LOBBY_PANEL);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game gameFrame = new Game();
            gameFrame.setVisible(true);
        });

    }

    public void playGame() {
        // Game Loop
        cardLayout.show(cardPanel, GAME_PANEL);
        this.validate();
        this.repaint();
        this.run = true;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    gamePanel.move();
                    gamePanel.repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException iEx) {
                        iEx.printStackTrace();
                    }
                }
            }
        });
        t.start();

    }

    public void stopGame() {
        cardLayout.show(cardPanel, LOBBY_PANEL);
        this.run = false;
    }
}
