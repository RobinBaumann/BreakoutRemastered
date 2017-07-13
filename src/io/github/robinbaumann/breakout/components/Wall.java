package io.github.robinbaumann.breakout.components;

import io.github.robinbaumann.breakout.components.bricks.Brick;
import io.github.robinbaumann.breakout.delegates.FileAccessDelegate;

import java.util.HashSet;
import java.util.Random;
import java.io.File;
import java.util.Set;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 4/25/17.
 */
public class Wall {
    private Set<Brick> bricks = new HashSet<>();
    private static final String FILE_LOCATION = "data/walls/";

    public Wall() {
    }

    public void add(Brick brick) {
        bricks.add(brick);
    }

    public Set<Brick> getBricks() {
        return bricks;
    }


    public void loadRandomWall() {

        File dir = new File(FILE_LOCATION);
        File[] files = dir.listFiles();
        Random rand = new Random();
        File wall = files[rand.nextInt(files.length)];

        FileAccessDelegate csvParser = new FileAccessDelegate();

        bricks = csvParser.readFromFile(wall);
    }

    public int countWalls() {
        return new File(FILE_LOCATION).listFiles().length;
    }

    public void save(){
        int num = countWalls() +1;
        File file = new File(FILE_LOCATION+"wall"+num+".csv");
        FileAccessDelegate fileAccessDelegate = new FileAccessDelegate();
        fileAccessDelegate.saveToFile(file, bricks);

    }

}
