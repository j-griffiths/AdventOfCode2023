package day13;

import utils.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day13 {
    final static String day = "13";

    public static void main(String[] args) {
        List<List<String>> input = utils.splitInput(utils.readInput(day));
        HashMap<List<String>, Map.Entry<Boolean, Integer>> cache = new HashMap<>();
        System.out.println(partOne(input, cache));
        System.out.println(partTwo(input, cache));
    }

    public static long partOne(List<List<String>> input, HashMap<List<String>, Map.Entry<Boolean, Integer>> cache) {
        long total = 0;
        for (List<String> pattern : input) {
            boolean reflectedHorizontally = false;
            for (int leftPointer = 0; leftPointer < pattern.size() - 1; leftPointer++) {
                boolean reflects = true;
                for (int l = leftPointer, r = leftPointer + 1; l >= 0 && r < pattern.size(); l--, r++) {
                    if (!pattern.get(l).equals(pattern.get(r))) {
                        reflects = false;
                        break;
                    }
                }
                if (reflects) {
                    reflectedHorizontally = true;
                    total += (leftPointer + 1) * 100;
                    cache.put(pattern, Map.entry(true, leftPointer));
                    break;
                }
            }

            if (reflectedHorizontally)
                continue;

            for (int leftPointer = 0; leftPointer < pattern.get(0).length() - 1; leftPointer++) {
                boolean reflects = true;
                for (int l = leftPointer, r = leftPointer + 1; l >= 0 && r < pattern.get(0).length(); l--, r++) {
                    if (!checkVerticalReflection(l, r, pattern)) {
                        reflects = false;
                        break;
                    }
                }
                if (reflects) {
                    total += leftPointer + 1;
                    cache.put(pattern, Map.entry(false, leftPointer));
                    break;
                }
            }
        }

        return total;
    }

    public static boolean checkVerticalReflection(int l, int r, List<String> pattern) {
        for (int i = 0; i < pattern.size(); i++) {
            if (pattern.get(i).charAt(l) != pattern.get(i).charAt(r)) {
                return false;
            }
        }
        return true;
    }

    public static long partTwo(List<List<String>> input, HashMap<List<String>, Map.Entry<Boolean, Integer>> cache) {
        long total = 0;
        for (List<String> pattern : input) {
            boolean reflectedHorizontally = false;
            boolean reflectedVertically = false;
            int smallestReflectionPoint = 999;
            for (int i = 0; i < pattern.size() * pattern.get(0).length(); i++) {
                List<String> patternCopy = new ArrayList<>(pattern);
                int rowToAlter = i / patternCopy.get(0).length();
                int columnToAlter = i % patternCopy.get(0).length();
                char characterToChange = patternCopy.get(rowToAlter).charAt(columnToAlter);
                char characterToSet = characterToChange == '.' ? '#' : '.';
                String alteredRow = patternCopy.get(rowToAlter).substring(0, columnToAlter) + characterToSet
                        + patternCopy.get(rowToAlter).substring(columnToAlter + 1);
                patternCopy.set(rowToAlter, alteredRow);
                for (int leftPointer = 0; leftPointer < patternCopy.size() - 1; leftPointer++) {
                    boolean reflects = true;
                    for (int l = leftPointer, r = leftPointer + 1; l >= 0 && r < patternCopy.size(); l--, r++) {
                        if (!patternCopy.get(l).equals(patternCopy.get(r))) {
                            reflects = false;
                            break;
                        }
                    }
                    if (reflects && leftPointer < smallestReflectionPoint && (cache.get(pattern).getKey() == false || cache.get(pattern).getKey() == true && leftPointer != cache.get(pattern).getValue())) {
                        reflectedHorizontally = true;
                        reflectedVertically = false;
                        smallestReflectionPoint = leftPointer;
                    }
                }

                for (int leftPointer = 0; leftPointer < patternCopy.get(0).length() - 1; leftPointer++) {
                    boolean reflects = true;
                    for (int l = leftPointer, r = leftPointer + 1; l >= 0 && r < patternCopy.get(0).length(); l--, r++) {
                        if (!checkVerticalReflection(l, r, patternCopy)) {
                            reflects = false;
                            break;
                        }
                    }
                    if (reflects && leftPointer < smallestReflectionPoint && (cache.get(pattern).getKey() == true || cache.get(pattern).getKey() == false && leftPointer != cache.get(pattern).getValue())) {
                        reflectedHorizontally = false;
                        reflectedVertically = true;
                        smallestReflectionPoint = leftPointer;
                    }
                }
            }
            if (reflectedHorizontally) {
                total += (smallestReflectionPoint + 1) * 100;
            } else if (reflectedVertically) {
                total += smallestReflectionPoint + 1;
            }
        }

        return total;
    }
}
