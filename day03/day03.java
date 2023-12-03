package day03;

import utils.utils;
import java.util.List;

public class day03 {
    final static String day = "03";

    final static public List<Character> digits = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    public static void main(String[] args) {
        List<String> input = utils.readInput(day);

        System.out.println(partOne(input));
        System.out.println(partTwo(input));
    }

    public static int partOne(List<String> lines) {
        int[][] grid = new int[lines.size()][lines.getFirst().length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                grid[i][j] = 0;
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) != '.' && !digits.contains(line.charAt(j))) {
                    int up = Math.max(0, i - 1);
                    int left = Math.max(0, j - 1);
                    int down = Math.min(lines.size(), i + 1);
                    int right = Math.min(line.length(), j + 1);
                    grid[up][left] = 1;
                    grid[up][j] = 1;
                    grid[up][right] = 1;
                    grid[i][left] = 1;
                    grid[i][j] = 1;
                    grid[i][right] = 1;
                    grid[down][left] = 1;
                    grid[down][j] = 1;
                    grid[down][right] = 1;
                }
            }
        }

        int total = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int number = 0;
            for (int j = 0; j < line.length(); j++) {
                if (digits.contains(line.charAt(j))) {
                    int k = j;
                    for (; k < line.length() && digits.contains(line.charAt(k)); k++) {
                        number = number * 10 + (line.charAt(k) - '0');
                    }
                    for (; j < k; j++) {
                        if (grid[i][j] == 1) {
                            total += number;
                            break;
                        }
                    }
                    number = 0;
                    j = k - 1;
                }
            }
        }
        return total;
    }

    public static int partTwo(List<String> lines) {
        int[][] grid = new int[lines.size()][lines.getFirst().length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                grid[i][j] = 0;
            }
        }

        int total = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '*') {
                    int up = Math.max(0, i - 1);
                    int left = Math.max(0, j - 1);
                    int down = Math.min(lines.size(), i + 1);
                    int right = Math.min(line.length(), j + 1);
                    grid[up][left] = 1;
                    grid[up][j] = 1;
                    grid[up][right] = 1;
                    grid[i][left] = 1;
                    grid[i][j] = 1;
                    grid[i][right] = 1;
                    grid[down][left] = 1;
                    grid[down][j] = 1;
                    grid[down][right] = 1;
                    int power = 1;
                    int count = 0;
                    for (int x = 0; x < lines.size(); x++) {
                        String innerLine = lines.get(x);
                        int number = 0;  
                        for (int y = 0; y < innerLine.length(); y++) {
                            if (digits.contains(innerLine.charAt(y))) {
                                int k = y;
                                for (; k < innerLine.length() && digits.contains(innerLine.charAt(k)); k++) {
                                    number = number * 10 + (innerLine.charAt(k) - '0');
                                }
                                for (; y < k; y++) {
                                    if (grid[x][y] == 1) {
                                        power *= number;
                                        count++;
                                        break;
                                    }
                                }
                                number = 0;
                                y = k - 1;
                            }
                        }
                    }
                    if (count == 2) {
                        total += power;
                    }
                    grid[up][left] = 0;
                    grid[up][j] = 0;
                    grid[up][right] = 0;
                    grid[i][left] = 0;
                    grid[i][j] = 0;
                    grid[i][right] = 0;
                    grid[down][left] = 0;
                    grid[down][j] = 0;
                    grid[down][right] = 0;
                }
            }
        }

        return total;
    }
}
