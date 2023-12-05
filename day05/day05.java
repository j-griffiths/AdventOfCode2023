package day05;

import utils.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

public class day05 {
    final static String day = "05";

    public static void main(String[] args) {
        List<List<String>> inputParts = utils.splitInput(utils.readInput(day));

        System.out.println(partOne(inputParts));
        System.out.println(partTwo(inputParts));
    }

    public static Long partOne(List<List<String>> inputParts) {
        ArrayList<HashMap<Long, Map.Entry<Long, Long>>> maps = new ArrayList<>();

        for (int i = 1; i < inputParts.size(); i++) {
            maps.add(mapKeyToDestinationWithRange(inputParts.get(i)));
        }

        String seedLine = inputParts.get(0).get(0);
        String[] seedNumbers = seedLine.substring(7).split(" ");

        Long[] locationNumbers = new Long[seedNumbers.length];
        for (int i = 0; i < seedNumbers.length; i++) {
            Long key = Long.parseLong(seedNumbers[i]);
            for (int j = 0; j < maps.size(); j++) {
                for (Map.Entry<Long, Map.Entry<Long, Long>> m : maps.get(j).entrySet()) {
                    Long k = m.getKey();
                    Map.Entry<Long, Long> v = m.getValue();
                    if (k <= key && key <= k + v.getValue() - 1) {
                        key = v.getKey() + (key - k);
                        break;
                    }
                }
            }
            locationNumbers[i] = key;
        }

        Long nearestLocation = 1000000000000000000L;
        for (int i = 0; i < locationNumbers.length; i++) {
            nearestLocation = Math.min(nearestLocation, locationNumbers[i]);
        }

        return nearestLocation;
    }

    public static Long partTwo(List<List<String>> inputParts) {
        String seedLine = inputParts.get(0).get(0);
        String[] seedNumbers = seedLine.substring(7).split(" ");
        ArrayList<Map.Entry<Long, Long>> range = new ArrayList<>();
        for (int i = 0; i < seedNumbers.length; i += 2) {
            range.add(new SimpleEntry<>(Long.parseLong(seedNumbers[i]), Long.parseLong(seedNumbers[i + 1])));
        }

        for (int i = 1; i < inputParts.size(); i++) {
            range = createRange(range, i, inputParts);
        }

        Long nearestLocation = 1000000000000000000L;
        for (Map.Entry<Long, Long> locationRange : range) {
            Long location = locationRange.getKey();
            nearestLocation = Math.min(nearestLocation, location);
        }

        return nearestLocation;
    }

    public static ArrayList<Map.Entry<Long, Long>> createRange(ArrayList<Map.Entry<Long, Long>> range, int partIndex,
            List<List<String>> inputParts) {
        List<String> mappingFunctions = inputParts.get(partIndex);
        ArrayList<Map.Entry<Long, Long>> newRanges = new ArrayList<>();

        while (!range.isEmpty()) {
            Map.Entry<Long, Long> pair = range.removeLast();
            Long lowerBound = pair.getKey();
            Long upperBound = lowerBound + pair.getValue();

            for (int i = 1; i < mappingFunctions.size(); i++) {
                String[] functionParts = mappingFunctions.get(i).split(" ");
                Long inputLowerBound = Long.parseLong(functionParts[1]);
                Long outputLowerBound = Long.parseLong(functionParts[0]);
                Long functionRange = Long.parseLong(functionParts[2]);
                Long inputUpperBound = inputLowerBound + functionRange;
                if (upperBound <= inputLowerBound
                        || lowerBound >= inputUpperBound && i == mappingFunctions.size() - 1) {
                    newRanges.add(new SimpleEntry<>(pair.getKey(), pair.getValue()));
                    break;
                } else if (upperBound <= inputUpperBound && lowerBound >= inputLowerBound
                        && lowerBound < inputUpperBound) {
                    Long difference = lowerBound - inputLowerBound;
                    Long newLowerBound = outputLowerBound + difference;
                    newRanges.add(new SimpleEntry<>(newLowerBound, pair.getValue()));
                    break;
                } else if (upperBound > inputUpperBound && lowerBound >= inputLowerBound) {
                    Long difference = lowerBound - inputLowerBound;
                    Long newLowerBound = outputLowerBound + difference;
                    Long newRange = inputUpperBound - lowerBound;
                    newRanges.add(new SimpleEntry<>(newLowerBound, newRange));
                    range.add(new SimpleEntry<>(inputUpperBound, upperBound - inputUpperBound));
                    break;
                } else if (upperBound <= inputUpperBound && lowerBound < inputLowerBound) {
                    Long difference = upperBound - inputLowerBound;
                    newRanges.add(new SimpleEntry<>(outputLowerBound, difference));
                    range.add(new SimpleEntry<>(lowerBound, inputLowerBound - lowerBound));
                    break;
                } else if (upperBound > inputUpperBound && lowerBound < inputLowerBound) {
                    Long lowerRange = inputLowerBound - lowerBound;
                    Long upperRange = upperBound - inputUpperBound;
                    newRanges.add(new SimpleEntry<>(outputLowerBound, functionRange));
                    range.add(new SimpleEntry<>(lowerBound, lowerRange));
                    range.add(new SimpleEntry<>(inputUpperBound, upperRange));
                    break;
                }
            }
        }

        return newRanges;
    }

    public static HashMap<Long, Map.Entry<Long, Long>> mapKeyToDestinationWithRange(List<String> lines) {
        HashMap<Long, Map.Entry<Long, Long>> map = new HashMap<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] destinationSourceRange = lines.get(i).split(" ");
            Long destination = Long.parseLong(destinationSourceRange[0]);
            Long source = Long.parseLong(destinationSourceRange[1]);
            Long range = Long.parseLong(destinationSourceRange[2]);
            map.putIfAbsent(source, Map.entry(destination, range));
        }

        return map;
    }
}
