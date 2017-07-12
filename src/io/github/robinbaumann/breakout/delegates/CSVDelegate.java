package io.github.robinbaumann.breakout.delegates;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.github.robinbaumann.breakout.components.bricks.Brick;
import io.github.robinbaumann.breakout.components.bricks.BrickBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 7/7/17.
 */
public class CSVDelegate {

    private Set<Brick> bricks = new HashSet<>();
    private BufferedReader br = null;
    private String line = "";
    private static final String SEPARATOR = ",";

    public Set<Brick> readFromFile(File file) {
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                String[] brick = line.split(SEPARATOR);
                System.out.printf("Brick: X=%s, Y=%s, DESTROYABLE=%s, HIT_AMOUNT=%s \n",
                        brick[0], brick[1], brick[2], brick[3]);

                Brick br = new BrickBuilder()
                        .setPosX(Integer.valueOf(brick[0]))
                        .setPosY(Integer.valueOf(brick[1]))
                        .setDestroyable(Boolean.parseBoolean(brick[2]))
                        .setHitAmount(Integer.valueOf(brick[3]))
                        .build();

                bricks.add(br);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bricks;

    }

    public void saveToFile(File file, Set<Brick> bricks) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            for (Brick b:bricks) {
                writer.write(b.toString());
                writer.flush();
            }
            writer.close();
        } catch(IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
