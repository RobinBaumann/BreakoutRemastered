package io.github.robinbaumann.breakout.views;

import io.github.robinbaumann.breakout.Game;
import io.github.robinbaumann.breakout.components.Wall;
import io.github.robinbaumann.breakout.components.bricks.Brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/25/17.
 */
public class Editor extends JPanel implements ActionListener{

    private Brick currentBrick;
    private Wall currentWall;

    private JPanel editorBoard = new JPanel();
    private JPanel toolBar = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JCheckBox destroyable = new JCheckBox();
    private JComboBox<Integer> hitAmount = new JComboBox<>();
    private JLabel hitAmountLabel = new JLabel("Hit Amount");
    private JLabel destroyableLable = new JLabel("Destroyable");

    private JButton cancelButton = new JButton("Cancel");
    private JButton saveButton = new JButton("Save");
    private JButton tryButton = new JButton("Try it!");

    private Game game;


    public Editor(Game game) {

        cancelButton.addActionListener(this);
        saveButton.addActionListener(this);
        tryButton.addActionListener(this);

        this.game = game;
        setLayout(new BorderLayout());
        toolBar.add(destroyable);
        toolBar.add(destroyableLable);
        toolBar.add(hitAmount);
        toolBar.add(hitAmountLabel);

        this.add(toolBar, BorderLayout.NORTH);
        this.add(editorBoard, BorderLayout.CENTER);

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(tryButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cancelButton) {
            game.toggleEditor();
        }
    }
}
