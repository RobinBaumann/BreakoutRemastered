package io.github.robinbaumann.breakout.views;

import io.github.robinbaumann.breakout.Game;
import io.github.robinbaumann.breakout.components.Wall;
import io.github.robinbaumann.breakout.components.bricks.Brick;
import io.github.robinbaumann.breakout.components.bricks.BrickBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/25/17.
 */
public class Editor extends JPanel implements ActionListener, MouseListener {

    private int MAX_HITAMOUNT = 20;

    private Brick currentBrick;
    private Wall currentWall = new Wall();

    private Board editorBoard = new Board(null);
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

        destroyable.setSelected(true);
        initializeHitAmount();

        editorBoard.setBackground(Color.WHITE);
        editorBoard.addMouseListener(this);

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

    private void initializeHitAmount() {
        for (int i = 1; i <= MAX_HITAMOUNT; i++) {
            hitAmount.addItem(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cancelButton) {
            this.game.toggleEditor();
        } else if (actionEvent.getSource() == saveButton) {
            currentWall.save();
            game.toggleEditor();
        } else if (actionEvent.getSource() == tryButton) {
            game.test();
        }
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        createBrick(mouseEvent.getX(), mouseEvent.getY(), destroyable.isSelected(), (Integer) hitAmount.getSelectedItem());
    }

    private void createBrick(int x, int y, boolean dest, Integer hitAmount) {
        currentBrick = new BrickBuilder()
                .setDestroyable(dest)
                .setHitAmount(hitAmount)
                .setPosX(x)
                .setPosY(y)
                .build();
        currentWall.add(currentBrick);
        editorBoard.setWall(currentWall);
        editorBoard.repaint();

        System.out.printf("Created Brick: %s \n", currentBrick.toString());

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


}
