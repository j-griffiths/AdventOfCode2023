package day12;

import utils.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class day12 {
    final static String day = "12";

    public static void main(String[] args) {
        List<String> lines = utils.readInput(day);

        System.out.println(partOne(lines));
        System.out.println(partTwo(lines));
    }

    public static long partOne(List<String> lines) {
        List<Map.Entry<String, List<Integer>>> inputs = lines.stream().map(line -> {
            String[] lineParts = line.split(" ");
            String[] numbers = lineParts[1].split(",");
            List<Integer> numberList = new ArrayList<>();
            for (int i = 0; i < numbers.length; i++) {
                numberList.add(Integer.parseInt(numbers[i]));
            }
            return Map.entry(lineParts[0], numberList);
        }).collect(Collectors.toList());

        Map<Map.Entry<String, List<Integer>>, Long> cache = new HashMap<>();

        long total = 0;
        for (Map.Entry<String, List<Integer>> input : inputs) {
            total += possibleConfigurations(input.getKey(), input.getValue(), cache);
        }

        return total;
    }

    public static long possibleConfigurations(String configuration, List<Integer> springGroups,
            Map<Map.Entry<String, List<Integer>>, Long> cache) {
        if (configuration.equals("")) {
            return springGroups.isEmpty() ? 1 : 0;
        }

        if (springGroups.isEmpty()) {
            return configuration.contains("#") ? 0 : 1;
        }

        Map.Entry<String, List<Integer>> cacheKey = Map.entry(configuration, springGroups);
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        long result = 0;

        if (configuration.charAt(0) == '.' || configuration.charAt(0) == '?') {
            result += possibleConfigurations(configuration.substring(1), springGroups, cache);
        }

        if (configuration.charAt(0) == '#' || configuration.charAt(0) == '?') {
            int contiguousSprings = springGroups.get(0);
            if (contiguousSprings <= configuration.length()
                    && !configuration.substring(0, contiguousSprings).contains(".")
                    && (contiguousSprings == configuration.length()
                            || configuration.charAt(contiguousSprings) != '#')) {
                if (configuration.length() != contiguousSprings) {
                    contiguousSprings++;
                }
                result += possibleConfigurations(configuration.substring(contiguousSprings),
                        springGroups.subList(1, springGroups.size()), cache);
            }
        }

        cache.put(cacheKey, result);

        return result;
    }

    public static long partTwo(List<String> lines) {
        int foldingAmount = 5;
        List<Map.Entry<String, List<Integer>>> inputs = lines.stream().map(line -> {
            String[] lineParts = line.split(" ");
            StringBuilder sb = new StringBuilder(lineParts[0]);
            sb.repeat("?" + lineParts[0], foldingAmount - 1);
            String[] numbers = lineParts[1].split(",");
            List<Integer> numberList = new ArrayList<>();
            for (int j = 0; j < foldingAmount; j++) {
                for (int i = 0; i < numbers.length; i++) {
                    numberList.add(Integer.parseInt(numbers[i]));
                }
            }
            return Map.entry(sb.toString(), numberList);
        }).collect(Collectors.toList());
        Map<Map.Entry<String, List<Integer>>, Long> cache = new HashMap<>();
        long total = 0;
        for (Map.Entry<String, List<Integer>> input : inputs) {
            total += possibleConfigurations(input.getKey(), input.getValue(), cache);
        }

        return total;
    }
}
