package io.github.robinbaumann.breakout.views;

import io.github.robinbaumann.breakout.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/10/17.
 */
public class Lobby extends JPanel implements ActionListener {

    private JButton startButton = new JButton("Play!");
    private JButton highScoreButton = new JButton("Highscore");
    private JButton editorButton = new JButton("Create Level");
    private static final Dimension BUTTON_SIZE = new Dimension(250,50);
    private Game game;

    public Lobby(Game game) {

        startButton.setPreferredSize(BUTTON_SIZE);
        startButton.setBackground(Color.GREEN);
        editorButton.setPreferredSize(BUTTON_SIZE);
        editorButton.setBackground(Color.ORANGE);
        highScoreButton.setPreferredSize(BUTTON_SIZE);
        highScoreButton.setBackground(new Color(170,0,255));

        startButton.addActionListener(this);
        editorButton.addActionListener(this);
        highScoreButton.addActionListener(this);

        this.game = game;
        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints labelConst = new GridBagConstraints();
        labelConst.insets = new Insets(3, 3, 3, 3);

        labelConst.gridx = 0;
        labelConst.gridy = 0;
        this.add(startButton, labelConst);
        labelConst.gridy = 1;
        this.add(editorButton, labelConst);
        labelConst.gridy = 2;
        this.add(highScoreButton, labelConst);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if(actionEvent.getSource() == this.startButton) {
                this.game.play();
            } else if(actionEvent.getSource() == this.editorButton) {
                this.game.toggleEditor();
            } else if(actionEvent.getSource() == this.highScoreButton) {
                JOptionPane.showMessageDialog(this,
                        "Current Highscore: "+ game.getHighscore() + " Levels finished!",
                        "Highscore",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
