package day08;

import utils.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class day08 {
    final static String day = "08";

    public static void main(String[] args) {
        List<String> lines = utils.readInput(day);

        System.out.println(partOne(lines));
        System.out.println(partTwo(lines));
    }

    public static int partOne(List<String> lines) {
        String directions = lines.get(0);
        HashMap<String, Map.Entry<String, String>> nodes = new HashMap<>();

        for (int i = 2; i < lines.size(); i++) {
            String[] nodeTuple = lines.get(i).replaceAll("[^a-zA-Z]", " ").replaceAll(" +", " ").trim().split(" ");
            nodes.put(nodeTuple[0], Map.entry(nodeTuple[1], nodeTuple[2]));
        }

        String location = "AAA";
        int directionCounter = 0;
        int stepCounter = 0;
        while (!location.equals("ZZZ")) {
            char direction = directions.charAt(directionCounter);
            directionCounter = (directionCounter + 1) % directions.length();

            Map.Entry<String, String> locationNode = nodes.get(location);
            switch (direction) {
                case 'L':
                    location = locationNode.getKey();
                    break;
                case 'R':
                    location = locationNode.getValue();
                    break;
                default:
                    System.out.println("Not L or R?");
                    break;
            }
            stepCounter++;
        }

        return stepCounter;
    }

    public static long partTwo(List<String> lines) {
        String directions = lines.get(0);
        HashMap<String, Map.Entry<String, String>> nodes = new HashMap<>();

        for (int i = 2; i < lines.size(); i++) {
            String[] nodeTuple = lines.get(i).replaceAll("[^a-zA-Z]", " ").replaceAll(" +", " ").trim().split(" ");
            nodes.put(nodeTuple[0], Map.entry(nodeTuple[1], nodeTuple[2]));
        }

        List<String> locations = nodes.keySet().stream().filter((location) -> location.endsWith("A"))
                .collect(Collectors.toList());
        List<Long> stepCounts = new ArrayList<>();

        int stepCounter = 0;
        while (!locations.isEmpty()) {
            int counter = stepCounter;
            locations = locations.stream().map((location) -> {
                Map.Entry<String, String> locationNode = nodes.get(location);
                char direction = directions.charAt(counter % directions.length());
                if (direction == 'L') {
                    location = locationNode.getKey();
                } else {
                    location = locationNode.getValue();
                }
                return location;
            }).collect(Collectors.toList());
            stepCounter++;
            if (locations.removeIf((location) -> location.endsWith("Z"))) {
                stepCounts.add(Long.valueOf(stepCounter));
            }
        }

        return stepCounts.stream().reduce(stepCounts.get(0), (acc, i) -> lcm(acc, i));
    }

    private static long gcd(long a, long b) {
        return a == 0 ? b : gcd(b % a, a);
    }

    public static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
