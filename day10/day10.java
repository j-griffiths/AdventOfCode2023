package day10;

import utils.utils;

import java.util.List;

public class day10 {
    final static String day = "10";

    public static void main(String[] args) {
        List<String> lines = utils.readInput(day);

        System.out.println(partOne(lines));
        System.out.println(partTwo(lines));
    }

    public static int partOne(List<String> lines) {
        int rowPointer = 0;
        int columnPointer = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == 'S') {
                    rowPointer = i;
                    columnPointer = j;
                }
            }
        }

        int startingRow = rowPointer;
        int startingColumn = columnPointer;

        int upIndex = startingRow - 1;
        int downIndex = startingRow + 1;
        int leftIndex = startingColumn - 1;
        int rightIndex = startingColumn + 1;
        // list of possible directions: up, down, left, right
        boolean[] directions = new boolean[] { false, false, false, false };
        char upChar = '.';
        char downChar = '.';
        char leftChar = '.';
        char rightChar = '.';
        if (upIndex >= 0) {
            upChar = lines.get(startingRow - 1).charAt(startingColumn);
        }
        if (downIndex <= lines.size()) {
            downChar = lines.get(startingRow + 1).charAt(startingColumn);
        }
        if (leftIndex >= 0) {
            leftChar = lines.get(startingRow).charAt(startingColumn - 1);
        }
        if (rightIndex <= lines.get(0).length()) {
            rightChar = lines.get(startingRow).charAt(startingColumn + 1);
        }

        if (upChar == '7' || upChar == 'F' || upChar == '|') {
            directions[0] = true;
        }
        if (downChar == 'L' || downChar == 'J' || downChar == '|') {
            directions[1] = true;
        }
        if (leftChar == 'L' || leftChar == 'F' || leftChar == '-') {
            directions[2] = true;
        }
        if (rightChar == 'J' || rightChar == '7' || rightChar == '-') {
            directions[3] = true;
        }

        int prevRow = startingRow;
        int prevColumn = startingColumn;

        int stepCounter = 0;
        while (canMove(directions)) {
            for (int i = 0; i < directions.length; i++) {
                if (directions[i]) {
                    int direction = i % 2 == 0 ? -1 : 1;
                    boolean verticalChange = i / 2 == 0;
                    int nextRow = rowPointer + ((verticalChange ? 1 : 0) * direction);
                    int nextColumn = columnPointer + ((verticalChange ? 0 : 1) * direction);
                    if (nextRow != prevRow || nextColumn != prevColumn) {
                        prevRow = rowPointer;
                        prevColumn = columnPointer;
                        rowPointer = nextRow;
                        columnPointer = nextColumn;
                        directions = getDirections(lines.get(rowPointer).charAt(columnPointer));
                        stepCounter++;
                        break;
                    }
                }
            }
        }

        return stepCounter / 2;
    }

    public static boolean canMove(boolean[] directions) {
        boolean canMove = false;
        for (int i = 0; i < directions.length; i++) {
            if (directions[i]) {
                canMove = true;
            }
        }
        return canMove;
    }

    public static boolean[] getDirections(char character) {
        boolean[] directions = new boolean[] { false, false, false, false };
        switch (character) {
            case '|':
                directions[0] = true;
                directions[1] = true;
                break;
            case '-':
                directions[2] = true;
                directions[3] = true;
                break;
            case 'L':
                directions[0] = true;
                directions[3] = true;
                break;
            case 'J':
                directions[0] = true;
                directions[2] = true;
                break;
            case '7':
                directions[1] = true;
                directions[2] = true;
                break;
            case 'F':
                directions[1] = true;
                directions[3] = true;
                break;
            case '.':
                System.out.println("Travelled to . node");
                break;
        }
        return directions;
    }

    public static int partTwo(List<String> lines) {
        int rowPointer = 0;
        int columnPointer = 0;

        int[][] grid = new int[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = 0;
                if (lines.get(i).charAt(j) == 'S') {
                    rowPointer = i;
                    columnPointer = j;
                }
            }
        }

        int startingRow = rowPointer;
        int startingColumn = columnPointer;

        int upIndex = startingRow - 1;
        int downIndex = startingRow + 1;
        int leftIndex = startingColumn - 1;
        int rightIndex = startingColumn + 1;
        // list of possible directions: up, down, left, right
        boolean[] directions = new boolean[] { false, false, false, false };
        char upChar = '.';
        char downChar = '.';
        char leftChar = '.';
        char rightChar = '.';
        if (upIndex >= 0) {
            upChar = lines.get(startingRow - 1).charAt(startingColumn);
        }
        if (downIndex <= lines.size()) {
            downChar = lines.get(startingRow + 1).charAt(startingColumn);
        }
        if (leftIndex >= 0) {
            leftChar = lines.get(startingRow).charAt(startingColumn - 1);
        }
        if (rightIndex <= lines.get(0).length()) {
            rightChar = lines.get(startingRow).charAt(startingColumn + 1);
        }

        if (upChar == '7' || upChar == 'F' || upChar == '|') {
            directions[0] = true;
        }
        if (downChar == 'L' || downChar == 'J' || downChar == '|') {
            directions[1] = true;
        }
        if (leftChar == 'L' || leftChar == 'F' || leftChar == '-') {
            directions[2] = true;
        }
        if (rightChar == 'J' || rightChar == '7' || rightChar == '-') {
            directions[3] = true;
        }

        int prevRow = startingRow;
        int prevColumn = startingColumn;

        while (canMove(directions)) {
            for (int i = 0; i < directions.length; i++) {
                if (directions[i]) {
                    int direction = i % 2 == 0 ? -1 : 1;
                    boolean verticalChange = i / 2 == 0;
                    int nextRow = rowPointer + ((verticalChange ? 1 : 0) * direction);
                    int nextColumn = columnPointer + ((verticalChange ? 0 : 1) * direction);
                    if (nextRow != prevRow || nextColumn != prevColumn) {
                        prevRow = rowPointer;
                        prevColumn = columnPointer;
                        rowPointer = nextRow;
                        columnPointer = nextColumn;
                        grid[rowPointer][columnPointer] = 1;
                        directions = getDirections(lines.get(rowPointer).charAt(columnPointer));
                        break;
                    }
                }
            }
        }

        boolean insideLoop = false;
        int tileCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char currentChar = lines.get(i).charAt(j);
                if (grid[i][j] == 1 && (currentChar == '|' || currentChar == 'J' || currentChar == 'L')) {
                    insideLoop = !insideLoop;
                }
                if (insideLoop && grid[i][j] == 0) {
                    tileCount++;
                }
            }
        }
        return tileCount;
    }
}
