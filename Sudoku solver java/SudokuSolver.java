public class SudokuSolver {
	
	public static boolean ValidRow(int[][] board, int n, int r, int c) { //checks is given number can be placed in the row
		// every column in row must be checked to see if it matcheds the given number
		
		for (int col = 0; col < 9; col++) { // 0-8
			if (col != c) {
				if (board[r][col] == n) {
					//System.out.println(board[r][col]); // delete
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static boolean ValidCol (int[][] board,  int n, int r, int c) { //checks if the given number can be placed in the selected column
		
		for (int row = 0; row < 9; row++) { //0-8
			if (row != r) {
				if (board[row][c] == n ) {
					//System.out.println(board[row][c]); // delete
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public static boolean ValidPlace (int[][] board, int n, int r, int c) { // checks ValidRow, ValidCol and ValidBox methods in one combined function
		if (ValidRow(board,n,r,c) && ValidRow(board,n,r,c) && ValidCol(board,n,r,c) && ValidBox(board,n,r,c)) {
			return true;
		}
		return false;
	}
	
	public static boolean ValidBox (int[][] board, int n, int r, int c) {
		
		int sRow = (r/3) * 3; // Starting row index of the 3x3 grid which the selected cell is in
		int eRow = sRow + 2;
		
		int sCol = (c/3) * 3; //Starting column index of the 3x3 grid which the selected cell is in
		int eCol = sCol + 2;
		//System.out.println(sRow+" "+sCol); // delete
		
		for (sRow = (eRow-2); sRow <= eRow; sRow++) {
			for (sCol = (eCol - 2);sCol <= eCol; sCol++) {
				if (board [sRow][sCol] == n) {
					//System.out.println(board[sRow][sCol]); // delete
					return false;
				}
			}
			
		}
		
		
		return true;
	}
	
	public static void disBoard(int[][] board) { // method to display the board as a grid
		System.out.print("-------------------------");
		System.out.println();
		for (int r = 0; r < 9; r++) {
			System.out.print("| ");
			for (int c = 0; c < 9; c++) {
				System.out.print(board[r][c]+ " ");
				
				if ((c % 3 == 2)) { //After every 3 columns 
					System.out.print("| ");
				}
				
			}
			if ((r != 0) && (r % 3 == 2)) { //After every 3 rows
				System.out.println();
				System.out.print("-------------------------");
			}
			System.out.println();
		}
	}
	
	public static boolean sudokuSolver(int[][] board) {
		// traversing the 9x9 
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				// checking if the cell is empty
				if (board[r][c] == 0) {
					// loop coveing numbers 1 to 9
					for (int n = 1; n <= 9; n++) {
						
						
						// checking if placement of selected number in the cell is valid
						if  (ValidPlace(board,n,r,c)) {
							//places n from loop into empty cell
							board[r][c] = n;
							
							// back tracing
							if (sudokuSolver(board)) {
								return true;
							}
							else { //resetting position of board if solution could not be obtainted with current position
								board[r][c] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		//use 2d arrays to contain sudoku board
		int[][] ar = {
				{3, 0, 5, 4, 0, 2, 0, 6, 0},
				{4, 9, 0, 7, 6, 0, 1, 0, 8},
				{6, 0, 0, 1, 0, 3, 2, 4, 5},
				{0, 0, 3, 9, 0, 0, 5, 8, 0},
				{9, 6, 0, 0, 5, 8, 7, 0, 3},
				{0, 8, 1, 3, 0, 4, 0, 9, 2},
				{0, 5, 0, 6, 0, 1, 4, 0, 0},
				{2, 0, 0, 5, 4, 9, 0, 7, 0},
				{1, 4, 9, 0, 0, 7, 3, 0, 6}
		};
		
		disBoard(ar);
		System.out.println(sudokuSolver(ar));
		disBoard(ar);
		
		
		int[][] ar2 = {
				{0, 0, 6, 0, 1, 0, 0, 4, 0},
				{0, 0, 0, 4, 0, 0, 8, 0 ,0},
				{0, 0, 0, 2, 0, 0, 6, 9 ,5},
				
				{0, 1, 0, 0, 8, 0, 0, 0 ,4},
				{0, 6, 0, 0, 0, 0, 0, 1 ,0},
				{9, 0, 0, 0, 3, 0, 0, 5 ,0},
				
				{1, 2, 8, 0, 0, 5, 0, 0 ,0},
				{0, 0, 5, 0, 0, 8, 0, 0 ,0},
				{0, 4, 0, 0, 2, 0, 5, 0 ,0},	
		};
		
		disBoard(ar2);
		System.out.println(sudokuSolver(ar2));
		disBoard(ar2);
		
		int[][] ar3 = {
				{0, 0, 0, 0, 0, 0, 0, 4, 0},
				{0, 0, 0, 4, 0, 0, 8, 0 ,0},
				{0, 0, 0, 2, 0, 0, 6, 9 ,5},
				
				{0, 1, 0, 0, 8, 0, 0, 0 ,4},
				{0, 6, 0, 0, 0, 0, 0, 1 ,0},
				{9, 0, 0, 0, 3, 0, 0, 5 ,0},
				
				{1, 2, 8, 0, 0, 5, 0, 0 ,0},
				{0, 0, 5, 0, 0, 8, 0, 0 ,0},
				{0, 4, 0, 0, 2, 0, 5, 0 ,0},	
		};
		
		disBoard(ar3);
		System.out.println(sudokuSolver(ar3));
		disBoard(ar3);
		

	}

}




