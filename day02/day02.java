package day02;

import utils.utils;
import java.util.List;

public class day02 {
    final static String day = "02";

    public static void main(String[] args) {
        List<String> input = utils.readInput(day);

        System.out.println(partOne(input));
        System.out.println(partTwo(input));
    }

    public static int partOne(List<String> lines) {
        int total = 0;
        int gameCounter = 0;
        for (String line : lines) {
            String[] gameAndSets = line.split(": ");
            String game = gameAndSets[0];
            int gameId = Integer.parseInt(game.substring(5));
            gameCounter += gameId;
            String allSets = gameAndSets[1];
            String[] sets = allSets.split("; ");
            boolean invalidGame = false;
            for (String set : sets) {
                String[] cubeList = set.split(", ");
                for (String cubeCount : cubeList) {
                    String[] countAndColour = cubeCount.split(" ");
                    int count = Integer.parseInt(countAndColour[0]);
                    String colour = countAndColour[1];
                    if (colour.equals("red") && count > 12) {
                        invalidGame = true;
                        break;
                    } else if (colour.equals("green") && count > 13) {
                        invalidGame = true;
                        break;
                    } else if (colour.equals("blue") && count > 14) {
                        invalidGame = true;
                        break;
                    }
                }
                if (invalidGame) {
                    total += gameId;
                    break;
                }
            }
        }
        return gameCounter - total;
    }

    public static int partTwo(List<String> lines) {
        int total = 0;
        for (String line : lines) {
            String[] gameAndSets = line.split(": ");
            String allSets = gameAndSets[1];
            String[] sets = allSets.split("; ");
            int redMax = 0;
            int greenMax = 0;
            int blueMax = 0;
            for (String set : sets) {
                String[] cubeList = set.split(", ");
                for (String cubeCount : cubeList) {
                    String[] countAndColour = cubeCount.split(" ");
                    int count = Integer.parseInt(countAndColour[0]);
                    String colour = countAndColour[1];
                    if (colour.equals("red")) {
                        if (count > redMax) {
                            redMax = count;
                        }
                    } else if (colour.equals("green")) {
                        if (count > greenMax) {
                            greenMax = count;
                        }
                    } else if (colour.equals("blue")) {
                        if (count > blueMax) {
                            blueMax = count;
                        }
                    }
                }
            }
            int power = redMax * greenMax * blueMax;
            total += power;
        }
        return total;
    }
}
