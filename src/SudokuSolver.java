public class SudokuSolver {
    private static final int N = 9;

    public static boolean solve(int[][] grid) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= N; num++) {
                        if (isSafe(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solve(grid)) return true;
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < N; x++)
            if (grid[row][x] == num || grid[x][col] == num) return false;

        int startRow = (row/3)*3, startCol = (col/3)*3;
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (grid[startRow + r][startCol + c] == num) return false;

        return true;
    }

    public static void printGrid(int[][] grid) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++)
                System.out.print(grid[r][c] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        };
        if (solve(board)) {
            System.out.println("Sudoku solved:");
            printGrid(board);
        } else {
            System.out.println("No solution exists");
        }
    }
}
