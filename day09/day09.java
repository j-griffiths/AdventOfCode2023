package day09;

import utils.utils;

import java.util.List;

public class day09 {
    final static String day = "09";

    public static void main(String[] args) {
        List<String> lines = utils.readInput(day);

        System.out.println(partOne(lines));
        System.out.println(partTwo(lines));
    }

    public static long partOne(List<String> lines) {
        long total = 0L;
        for (String line : lines) {
            String[] numbersAsStrings = line.split(" ");
            long[] sequence = new long[numbersAsStrings.length];
            for (int i = 0; i < sequence.length; i++) {
                sequence[i] = Long.parseLong(numbersAsStrings[i]);
            }
           
            total += nextNumber(sequence);
        }
        return total;
    }

    public static long nextNumber(long[] sequence) {
        if (allZeros(sequence)) {
            return 0;
        }

        return sequence[sequence.length - 1] + nextNumber(getDifferenceArray(sequence));
    }

    public static boolean allZeros(long[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] != 0) return false;
        }
        return true;
    }

    public static long[] getDifferenceArray(long[] sequence) {
        long[] differenceArray = new long[sequence.length - 1];
        for (int i = 0; i < differenceArray.length; i++) {
            differenceArray[i] = sequence[i + 1] - sequence[i];
        }
        return differenceArray;
    }

    public static long partTwo(List<String> lines) {
        long total = 0L;
        for (String line : lines) {
            String[] numbersAsStrings = line.split(" ");
            long[] sequence = new long[numbersAsStrings.length];
            for (int i = 0; i < sequence.length; i++) {
                sequence[i] = Long.parseLong(numbersAsStrings[numbersAsStrings.length - 1 - i]);
            }
           
            total += nextNumber(sequence);
        }
        return total;
    }
}
