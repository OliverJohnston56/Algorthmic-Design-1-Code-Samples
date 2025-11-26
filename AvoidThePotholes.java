/*
 * Oliver Johnston
 */

import java.util.Random;
import java.util.Scanner;

public class AvoidThePotholes {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            playGame(input);

            System.out.print("\nWould you like to play again? (Y/N): ");
            String choice = input.nextLine().trim().toUpperCase();

            if (!choice.equals("Y")) {
                playAgain = false;
                System.out.println("Thanks for playing! Goodbye");
            }
        }

        input.close();
    }

    // Core Game Method 
    private static void playGame(Scanner input) {
        final int SIZE = 10;
        final int NUM_POTHOLES = 5;
        char[][] grid = new char[SIZE][SIZE];
        int[][] potholes = new int[NUM_POTHOLES][2];

        // Fill grid
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                grid[i][j] = '_';

        int playerRow = 0, playerCol = 0;
        grid[playerRow][playerCol] = 'x';
        grid[SIZE - 1][SIZE - 1] = '^';

        // Generate potholes
        Random rand = new Random();
        int count = 0;
        while (count < NUM_POTHOLES) {
            int r = rand.nextInt(SIZE);
            int c = rand.nextInt(SIZE);
            if ((r == 0 && c == 0) || (r == SIZE - 1 && c == SIZE - 1))
                continue;
            if (!isDuplicate(potholes, count, r, c)) {
                potholes[count][0] = r;
                potholes[count][1] = c;
                count++;
            }
        }

        // Direction mapping
        String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        int[] dRow = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dCol = {0, 1, 1, 1, 0, -1, -1, -1};

        System.out.println("\n--- NEW GAME ---");
        System.out.println("Reach home '^' without stepping on a pothole!");
        System.out.println("Move in directions: N, NE, E, SE, S, SW, W, NW\n");

        boolean gameOver = false;
        while (!gameOver) {
            displayGrid(grid);
            System.out.print("Enter your move: ");
            String move = input.nextLine();

            int directionIndex = -1;
            for (int i = 0; i < directions.length; i++) {
                if (move.equals(directions[i])) {
                    directionIndex = i;
                    break;
                }
            }

            if (directionIndex == -1) {
                System.out.println("Invalid direction! Try again.\n");
                continue;
            }

            int newRow = playerRow + dRow[directionIndex];
            int newCol = playerCol + dCol[directionIndex];

            // Check bounds
            if (newRow < 0 || newRow >= SIZE || newCol < 0 || newCol >= SIZE) {
                System.out.println("You can't move outside the grid!\n");
                continue;
            }

            // Check potholes
            if (isPothole(potholes, NUM_POTHOLES, newRow, newCol)) {
                System.out.println("You fell into a pothole at (" + newRow + "," + newCol + ")! Game over!");
                gameOver = true;
                break;
            }

            // Move player
            grid[playerRow][playerCol] = '_';
            playerRow = newRow;
            playerCol = newCol;
            grid[playerRow][playerCol] = 'x';

            // Check win
            if (playerRow == SIZE - 1 && playerCol == SIZE - 1) {
                displayGrid(grid);
                System.out.println("Congratulations! You reached home safely!");
                gameOver = true;
            }
        }
    }

    private static boolean isDuplicate(int[][] potholes, int count, int r, int c) {
        for (int i = 0; i < count; i++) {
            if (potholes[i][0] == r && potholes[i][1] == c)
                return true;
        }
        return false;
    }

    private static boolean isPothole(int[][] potholes, int count, int r, int c) {
        for (int i = 0; i < count; i++) {
            if (potholes[i][0] == r && potholes[i][1] == c)
                return true;
        }
        return false;
    }

    private static void displayGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
