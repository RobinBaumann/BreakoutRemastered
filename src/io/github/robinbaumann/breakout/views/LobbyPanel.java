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
public class LobbyPanel extends JPanel implements ActionListener {

    private static final JLabel labelForUserTextField = new JLabel("Username");
    private JTextField userName = new JTextField("", 20);
    private JButton startButton = new JButton("Play!");
    private JButton highScoreButton = new JButton("View Best");
    private JButton cancelButton = new JButton("Cancel");
    private Game game;

    public LobbyPanel(Game game) {

        startButton.addActionListener(this);

        this.game = game;
        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints labelConst = new GridBagConstraints();
        labelConst.insets = new Insets(3, 3, 3, 3);

        labelConst.gridx = 0;
        labelConst.gridy = 0;
        this.add(labelForUserTextField, labelConst);
        labelConst.gridx = 1;
        this.add(userName, labelConst);
        labelConst.gridx = 0;
        labelConst.gridy = 1;
        this.add(cancelButton, labelConst);
        labelConst.gridx = 1;
        this.add(highScoreButton, labelConst);
        labelConst.gridx = 2;
        this.add(startButton, labelConst);

    }

    //TODO: Resize window for play
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if(actionEvent.getSource() == this.startButton) {
                this.game.playGame();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
