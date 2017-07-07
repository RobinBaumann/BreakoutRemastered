package io.github.robinbaumann.breakout.components;

import io.github.robinbaumann.breakout.components.bricks.Brick;
import io.github.robinbaumann.breakout.delegates.CSVDelegate;

import java.util.Random;
import java.io.File;
import java.util.Set;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/25/17.
 */
public class Wall {
    private Set<Brick> bricks;
    private static final String FILE_LOCATION = "walls/";

    public Wall() {
        bricks = loadRandomWall();
    }

    public void add(Brick brick) {
        bricks.add(brick);
    }

    public void remove(Brick brick) {
        bricks.remove(brick);
    }

    public void removeAll() {
        bricks.clear();
    }


    public Set<Brick> getBricks() {
        return bricks;
    }


    public Set<Brick> loadRandomWall() {


        File dir = new File(FILE_LOCATION);
        File[] files = dir.listFiles();
        Random rand = new Random();
        File wall = files[rand.nextInt(files.length)];

        CSVDelegate csvParser = new CSVDelegate();

        bricks = csvParser.readFromFile(wall);

        return bricks;
    }

}
