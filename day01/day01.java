package day01;

import utils.utils;
import java.util.List;

public class day01 {
    final static String day = "01";

    final static List<String> numbers = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "one", "two", "three",
            "four", "five", "six", "seven", "eight", "nine");

    public static void main(String[] args) {
        List<String> inputLines = utils.readInput(day);

        System.out.println(problemOne(inputLines));
        System.out.println(problemTwo(inputLines));
    }

    public static int problemOne(List<String> input) {
        return input.stream()
                .map(String::chars).map(line -> line.filter(Character::isDigit)).map(line -> line
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())
                .mapToInt(string -> Integer.parseInt(
                        String.valueOf(string.charAt(0)).concat(String.valueOf(string.charAt(string.length() - 1)))))
                .sum();
    }

    public static int problemTwo(List<String> input) {
        return input.stream()
                .map(day01::getFirstAndLastDigit)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    public static String getFirstAndLastDigit(String str) {
        int indexOfFirstDigit = str.length();
        int indexOfLastDigit = -1;
        String firstDigit = "";
        String lastDigit = "";

        for (String s : numbers) {
            int newFirstIndex = str.indexOf(s);
            int newLastIndex = str.lastIndexOf(s);
            if (newFirstIndex == -1) {
                continue;
            }
            if (newFirstIndex < indexOfFirstDigit) {
                indexOfFirstDigit = newFirstIndex;
                firstDigit = s;
            }
            if (newLastIndex == -1) {
                continue;
            }
            if (newLastIndex > indexOfLastDigit) {
                indexOfLastDigit = newLastIndex;
                lastDigit = s;
            }
        }

        firstDigit = numbers.get(numbers.indexOf(firstDigit) % 9);
        lastDigit = numbers.get(numbers.indexOf(lastDigit) % 9);
        return firstDigit.concat(lastDigit);
    }
}
