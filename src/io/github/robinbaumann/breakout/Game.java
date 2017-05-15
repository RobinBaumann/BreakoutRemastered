package io.github.robinbaumann.breakout;

import io.github.robinbaumann.breakout.views.Board;
import io.github.robinbaumann.breakout.views.Editor;
import io.github.robinbaumann.breakout.views.Lobby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/9/17.
 */
public class Game  extends JFrame implements Runnable {
    private static final int width = 1280;
    private static final int height = 720;
    private static final String LOBBY = "Lobby";
    private static final String GAME = "Game";
    private static final String EDITOR = "Editor";
    private boolean run = false;
    private CardLayout cardLayout;
    private JPanel cardPanel = new JPanel();
    private Lobby lobby;
    private Board board;
    private Editor editor;

    public Game(){
        super("Breakout Remastered");
        cardPanel.setLayout(new CardLayout());
        cardLayout = (CardLayout) cardPanel.getLayout();
        lobby = new Lobby(this);
        board = new Board(this);
        editor = new Editor(this);
        cardPanel.add(lobby, LOBBY);
        cardPanel.add(board, GAME);
        cardPanel.add(editor, EDITOR);
        this.add(cardPanel);
        this.pack();
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        cardLayout.show(cardPanel, LOBBY);

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                board.getRacquet().keyPressed(keyEvent);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                board.getRacquet().keyReleased(keyEvent);
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

    public void toggleEditor() {
        if(cardPanel.getComponent(0).equals(editor)){
            cardLayout.show(cardPanel, LOBBY);
        } else {
            cardLayout.show(cardPanel, EDITOR);
        }
    }

    public void playGame() {
        cardLayout.show(cardPanel, GAME);
        this.validate();
        this.repaint();
        this.run = true;
        Thread t = new Thread(this);
        t.start();

    }

    public void stopGame() {
        cardLayout.show(cardPanel, LOBBY);
        this.run = false;
    }

    @Override
    public void run() {
        while (run) {
            board.move();
            board.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException iEx) {
                iEx.printStackTrace();
            }
        }
    }
}
