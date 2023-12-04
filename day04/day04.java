package day04;

import utils.utils;

import java.util.HashMap;
import java.util.List;

public class day04 {
    final static String day = "04";

    public static void main(String[] args) {
        List<String> inputParts = utils.readInput(day);

        System.out.println(partOne(inputParts));
        System.out.println(partTwo(inputParts));
    }

    public static int partOne(List<String> lines) {
        int total = 0;
        for (String line : lines) {
            int matches = getMatches(line);
            if (matches > 0) {
                total += Math.pow(2, matches - 1);
            }
        }
        return total;
    }

    public static int partTwo(List<String> lines) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        
        return process(cache, lines, 0, lines.size());
    }

    public static int getMatches(String line) {
            String[] cardAndNumbers = line.split(": ");
            String numbers = cardAndNumbers[1];
            String[] splitNumbers = numbers.split(" \\| ");
            List<String> winningNumbers = List.of(splitNumbers[0].split(" "));
            String[] actualNumbers = splitNumbers[1].split(" ");
            int count = 0;
            for (String currentNumber : actualNumbers) {
                if (!currentNumber.equals("") && winningNumbers.contains(currentNumber)) {
                    count++;
                }
            }
            return count;
    }

    public static int process(HashMap<Integer, Integer> cache, List<String> lines, int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            int matches = cache.computeIfAbsent(i, v -> getMatches(lines.get(v)));
            if (matches > 0) {
                count += process(cache, lines, i + 1, i + 1 + matches);
            }
            count++;
        }
        return count;
    }
}
