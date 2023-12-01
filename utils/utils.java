package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
 
public class utils {
    public static List<String> readInput(String day) {
        List<String> lines = new ArrayList<String>();
        try {
            Path filePath = Path.of(String.format("day%s/input.txt", day));
            lines = Files.readAllLines(filePath);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(-1);
        }
        return lines;
    }

    public static List<List<String>> splitInput(List<String> input) {
        List<List<String>> parts = new ArrayList<List<String>>();
        List<String> currentPart = new ArrayList<String>();
        for (String line : input) {
            if (line.equals("")) {
                parts.add(currentPart);
                currentPart = new ArrayList<String>();
            } else {
                currentPart.add(line);
            }
        }
        return parts;
    }
}