package day11;

import utils.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class day11 {
    final static String day = "11";

    public static void main(String[] args) {
        List<String> lines = utils.readInput(day);

        System.out.println(partOne(new ArrayList<>(lines)));
        System.out.println(partTwo(lines));
    }

    public static int partOne(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            boolean rowHasNoGalaxy = true;
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '#') {
                    rowHasNoGalaxy = false;
                    break;
                }
            }
            if (rowHasNoGalaxy) {
                lines.add(i, lines.get(i));
                i++;
            }
        }
        for (int i = 0; i < lines.get(0).length(); i++) {
            boolean columnHasNoGalaxy = true;
            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j).charAt(i) == '#') {
                    columnHasNoGalaxy = false;
                    break;
                }
            }
            if (columnHasNoGalaxy) {
                for (int j = 0; j < lines.size(); j++) {
                    lines.set(j, lines.get(j).substring(0, i) + lines.get(j).charAt(i) + lines.get(j).substring(i));
                }
                i++;
            }
        }

        List<Map.Entry<Integer, Integer>> galaxyLocations = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '#') {
                    galaxyLocations.add(Map.entry(i, j));
                }
            }
        }

        int total = 0;
        for (int i = 0; i < galaxyLocations.size(); i++) {
            for (int j = i + 1; j < galaxyLocations.size(); j++) {
                int diffX = Math.abs(galaxyLocations.get(i).getKey() - galaxyLocations.get(j).getKey());
                int diffY = Math.abs(galaxyLocations.get(i).getValue() - galaxyLocations.get(j).getValue());
                total += diffX + diffY;
            }
        }

        return total;
    }

    public static long partTwo(List<String> lines) {
        List<Map.Entry<Long, Long>> galaxyLocations = new ArrayList<>();
        long expansionScale = 1000000;
        long expansionIncrement = expansionScale - 1;
        List<Integer> emptyRows = new ArrayList<>();
        List<Integer> emptyColumns = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            boolean rowHasNoGalaxy = true;
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '#') {
                    rowHasNoGalaxy = false;
                    break;
                }
            }
            if (rowHasNoGalaxy) {
                emptyRows.add(i);
            }
        }
        for (int i = 0; i < lines.get(0).length(); i++) {
            boolean columnHasNoGalaxy = true;
            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j).charAt(i) == '#') {
                    columnHasNoGalaxy = false;
                    break;
                }
            }
            if (columnHasNoGalaxy) {
                emptyColumns.add(i);
            }
        }

        long emptyRowCount = 0;
        for (int i = 0; i < lines.size(); i++) {
            long emptyColumnCount = 0;
            if (emptyRows.contains(i)) {
                emptyRowCount++;
                continue;
            }
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (emptyColumns.contains(j)) {
                    emptyColumnCount++;
                    continue;
                }
                if (lines.get(i).charAt(j) == '#') {
                    galaxyLocations.add(
                            Map.entry(i + (emptyRowCount * expansionIncrement), j + (emptyColumnCount * expansionIncrement)));
                }
            }
        }
        long total = 0;
        for (int i = 0; i < galaxyLocations.size(); i++) {
            for (int j = i + 1; j < galaxyLocations.size(); j++) {
                long diffX = Math.abs(galaxyLocations.get(i).getKey() - galaxyLocations.get(j).getKey());
                long diffY = Math.abs(galaxyLocations.get(i).getValue() - galaxyLocations.get(j).getValue());
                total += diffX + diffY;
            }
        }

        return total;
    }
}
