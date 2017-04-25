package io.github.robinbaumann.breakout;

import io.github.robinbaumann.breakout.views.GamePanel;
import io.github.robinbaumann.breakout.views.LobbyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Game  extends JFrame implements Runnable {
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

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                gamePanel.getRacquet().keyPressed(keyEvent);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                gamePanel.getRacquet().keyReleased(keyEvent);
            }
        };
        addKeyListener(keyListener);
        setFocusable(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game gameFrame = new Game();
            gameFrame.setVisible(true);
        });

    }

    public void playGame() {
        cardLayout.show(cardPanel, GAME_PANEL);
        this.validate();
        this.repaint();
        this.run = true;
        Thread t = new Thread(this);
        t.start();

    }

    public void stopGame() {
        cardLayout.show(cardPanel, LOBBY_PANEL);
        this.run = false;
    }

    public void keyPressed(KeyEvent e) {

    }

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
}
