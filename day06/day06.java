package day06;

import utils.utils;
import java.util.List;

public class day06 {
    final static String day = "06";

    public static void main(String[] args) {
        List<String> lines = utils.readInput(day);

        System.out.println(partOne(lines));
        System.out.println(partTwo(lines));
    }

    public static int partOne(List<String> lines) {
        String[] times = lines.get(0).substring(5).trim().replaceAll(" +", " ").split(" ");
        String[] distances = lines.get(1).substring(9).trim().replaceAll(" +", " ").split(" ");

        int totalWins = 1;
        for (int i = 0; i < times.length; i++) {
            int t = Integer.parseInt(times[i]);
            int d = Integer.parseInt(distances[i]);

            totalWins *= countWins(t, d);
        }

        return totalWins;
    }

    public static int countWins(long t, long d) {
        int winCounter = 0;

        for (int i = 1; i <= t; i++) {
            if (i * (t - i) > d) {
                winCounter++;
            }
        }
        
        return winCounter;
    }

    public static int partTwo(List<String> lines) {
        String time = lines.get(0).replaceAll("[^\\d]", "");
        String distance = lines.get(1).replaceAll("[^\\d]", "");

        long t = Long.parseLong(time);
        long d = Long.parseLong(distance);

        return countWins(t, d);
    }
}
