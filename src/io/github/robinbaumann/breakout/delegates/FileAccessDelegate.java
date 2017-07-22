package io.github.robinbaumann.breakout.delegates;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.github.robinbaumann.breakout.components.bricks.Brick;
import io.github.robinbaumann.breakout.components.bricks.BrickBuilder;

import javax.print.DocFlavor;
import java.io.*;
import java.lang.management.BufferPoolMXBean;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * Project: BreakoutRemastered
 * Created by Robin Baumann on 7/7/17.
 */
public class FileAccessDelegate {

    private static Set<Brick> bricks = new HashSet<>();
    private static BufferedReader br = null;
    private static String LINE = "";
    private static final String SEPARATOR = ",";
    private static final String HIGHSCORE_FILE = "data/highscore/highscore";

    public static Set<Brick> readFromFile(File file) {
        try {
            br = new BufferedReader(new FileReader(file));
            while ((LINE = br.readLine()) != null) {

                String[] brick = LINE.split(SEPARATOR);
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

    public static void saveToFile(File file, Set<Brick> bricks) {
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

    public static int readHighscore() {
        int highscore = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(HIGHSCORE_FILE));
            while ((LINE = br.readLine()) != null) {
                highscore = Integer.valueOf(LINE);
            }

        } catch(IOException ioex) {
            ioex.printStackTrace();
        }

        return highscore;
    }

    public static void writeHighscore(int highscore) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(HIGHSCORE_FILE, false));
            writer.write(String.valueOf(highscore));
            writer.close();
        } catch(IOException ioex) {
            ioex.printStackTrace();
        }
    }


}
