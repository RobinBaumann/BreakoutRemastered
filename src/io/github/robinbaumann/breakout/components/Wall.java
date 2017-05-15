package io.github.robinbaumann.breakout.components;

import io.github.robinbaumann.breakout.components.bricks.Brick;

import java.util.HashSet;
import java.util.Set;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/25/17.
 */
public class Wall {
    Set<Brick> bricks = new HashSet<>();

    public void add(Brick brick) {
        bricks.add(brick);
    }

    public void remove(Brick brick) {
        bricks.remove(brick);
    }

    public void removeAll() {
        bricks.clear();
    }
}
