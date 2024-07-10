import java.util.Scanner;

public class connect4 {
	
	public static void show_board(String[][] A) {
//		
//		System.out.print("   ");
//		for (int hor = 1; hor <= 57; hor++) 
//			System.out.print("-");
		System.out.print("\n");
		
		for (int i = 0;i< A.length; i++) { //rows
			System.out.print("R"+(i+1)+" ");
			for (int j = 0; j < A[i].length; j++) { //columns;
				System.out.print("|   "+A[i][j]+"   ");
			}
			System.out.print("|");
			System.out.print("\n");
			System.out.print("   ");
			
			
			for (int hor = 1; hor <= 57; hor++)
				System.out.print("-");
			System.out.print("\n");
		}
		
		//6 spaces before C1 then 6 after every column
		System.out.print("   ");
		System.out.print("   ");
		for (int col = 1; col <= 7; col++) {
			System.out.print("C"+col+"      ");
		}
		
		System.out.println("\n");
	}
	
	public static int check_win(String[][]ar) {
		int check = 0;
		
		//Checking vertical condition
		for(int c = 0 ; c<= 6; c++) { //0 1 2 3 4 5 6 (all columns)
			for (int r = 0; r<=2 ; r++) { // 0 1 2 (first 3 rows)
				//Picking an element to compare with the 3 elements below it
				String e1 = ar[r][c];
				String e2 = ar[r+1][c];
				String e3 = ar[r+2][c];
				String e4 = ar[r+3][c];
				//System.out.println((r)+" "+c + " ; "+(r+1)+" "+c + " ; "+(r+2)+" "+c + " ; "+(r+3)+" "+c + " ; ");
				if (    (e1.equals("O") && e2.equals("O") && e3.equals("O") && e4.equals("O") ) || 
						(e1.equals("X") && e2.equals("X") && e3.equals("X") && e4.equals("X") )) {
					//System.out.println(true);
					
					// Shows the 4 winning slots
					System.out.println("\n4 CONNECTED (VERTICAL): "+
							           "R"+(r+1)+"C"+(c+1)+", "+
							           "R"+(r+2)+"C"+(c+1)+", "+
							           "R"+(r+3)+"C"+(c+1)+", "+
							           "R"+(r+4)+"C"+(c+1)+", ");
					check++;
				}
				//else
					//System.out.println(false);
			}
		}
		//System.out.println(); //Delete when code works
		
		//Checking horizontal win condition
		for (int r = 0; r <= 5; r++) { // 0 1 2 3 4 5 (all rows)
			for (int c = 0; c <= 3; c++ ) { //0 1 2 3 (first 4 columns)
				String e1 = ar[r][c];
				String e2 = ar[r][c+1];
				String e3 = ar[r][c+2];
				String e4 = ar[r][c+3];
				//System.out.println((r)+" "+(c) + " ; "+(r)+" "+(c+1) + " ; "+(r)+" "+(c+2) + " ; "+(r)+" "+(c+3) + " ; "); //Delete
				if (    (e1.equals("O") && e2.equals("O") && e3.equals("O") && e4.equals("O") ) || 
						(e1.equals("X") && e2.equals("X") && e3.equals("X") && e4.equals("X") )) {
					//System.out.println(true);
					System.out.println("4 CONNECTED (HORIZONTAL): "+
					           "R"+(r+1)+"C"+(c+1)+", "+
					           "R"+(r+1)+"C"+(c+2)+", "+
					           "R"+(r+1)+"C"+(c+3)+", "+
					           "R"+(r+1)+"C"+(c+4)+", ");
					check++;
				}
				//else
					//System.out.println(false);
			}
		}
		//System.out.println(); //Delete when code works
		
		//Checking diagonal win (left to right)
		for (int r = 0; r <= 2; r++) { //0 1 2 (first 3 rows)
			for (int c = 0; c <= 3; c++) { //0 1 2 3 (first 4 columns)
				String e1 = ar[r][c];
				String e2 = ar[r+1][c+1];
				String e3 = ar[r+2][c+2];
				String e4 = ar[r+3][c+3];
				//System.out.println((r)+" "+(c) + " ; "+(r+1)+" "+(c+1) + " ; "+(r+2)+" "+(c+2) + " ; "+(r+3)+" "+(c+3) + " ; "); //Delete
				if (    (e1.equals("O") && e2.equals("O") && e3.equals("O") && e4.equals("O") ) || 
						(e1.equals("X") && e2.equals("X") && e3.equals("X") && e4.equals("X") )) {
					//System.out.println(true);
					System.out.println("4 CONNECTED (DIAGONAL): "+
					           "R"+(r+1)+"C"+(c+1)+", "+
					           "R"+(r+2)+"C"+(c+2)+", "+
					           "R"+(r+3)+"C"+(c+3)+", "+
					           "R"+(r+4)+"C"+(c+4)+", ");
					check++;
				}
				//else
					//System.out.println(false);
			}
		}
		//System.out.println(); //Delete when code works
		
		// Checking diagonal (right to left)
		for (int r = 0; r <= 2; r++) { //0 1 2 (first 3 rows)
			for (int c = 3; c <= 6; c++) { //3 4 5 6 (last 3 columns)
				String e1 = ar[r][c];
				String e2 = ar[r+1][c-1];
				String e3 = ar[r+2][c-2];
				String e4 = ar[r+3][c-3];
				
				//System.out.println((r)+" "+(c) + " ; "+(r+1)+" "+(c-1) + " ; "+(r+2)+" "+(c-2) + " ; "+(r+3)+" "+(c-3) + " ; "); //Delete
				
				if (    (e1.equals("O") && e2.equals("O") && e3.equals("O") && e4.equals("O") ) || 
						(e1.equals("X") && e2.equals("X") && e3.equals("X") && e4.equals("X") )) {
					//System.out.println(true);
					System.out.println("4 CONNECTED (DIAGONAL): "+
					           "R"+(r+1)+"C"+(c+1)+", "+
					           "R"+(r+2)+"C"+(c)+", "+
					           "R"+(r+3)+"C"+(c-1)+", "+
					           "R"+(r+4)+"C"+(c-2)+", ");
					check++;
				}
				//else
					//System.out.println(false);
			}
		}
		return check;
	}
	
	public static String[][] insert_element(String[][] ar, String el , int col) { //element to be inserted, column of insertion
		int ind_col = col - 1; //Converts column number to index
		// row of insertion will be last available row
		
		boolean full = true; //variable to check whether column is full (true by default)
		
		for (int r = 5; r >= 0 ; r--) {
			if (ar[r][ind_col].equals(" ")) {
				ar[r][ind_col] = el;
				full = false; //full changes to false if any free space is found
				System.out.println("Input successfully slotted");
				break;
			}
		}
		
		if (full == true) {
			System.out.println("This column has already been filled");
		}
		
		//show_board(ar); //Delete after code works
		
		return ar;
	}
	
	public static boolean check_full_board(String[][] ar) {
		for (int r = 0; r < ar.length; r++) {
			for (int c = 0; c < ar[r].length; c++) {
				if (ar[r][c].equals(" ")) { //if slot is empty
					return false; //returns value as false since board is not full
				}
			}
		}
		return true; // returns true if no empty space was found in all slots
	}
	
	public static void play_connect4(String[][] board) {
		Scanner in = new Scanner(System.in);
		int cont = 0;
		
		do {
			int check = 0; //There are no winners yet and it is not a stalemate

			int player = 1; //setting player to 1 by default
			
			String inp = "O";
			
			while (check == 0) {
				show_board(board);
				
				
				//Setting input according to turn
				if (player == 1) {
					inp = "O";
				}
				else 
					inp = "X";
				
				System.out.println("Player "+player+" (input - "+inp+") :" );
				
				System.out.println("Enter the column in which you wish to add slot (1 - 7) or 0 to clear board: ");
				int col = in.nextInt();
				
				if(col == 0) {
					board = clear_board(board);
					System.out.println("Board has been reset");
				}
				
				//invalid column value entered
				else if (col<1 || col>7) {
					System.out.println("Please enter a valid number ");
				}
				
				
				
				//valid value for column entered
				else {
					board = insert_element(board,inp,col);
					
					check = check_win(board);
					
					if (check > 0) {
						show_board(board);
						System.out.println("--------------------------------------------GAME OVER--------------------------------------------"
								+ "\nPlayer "+player+" is the winner");
					}
					
					// Changes player if no one has won
					if (player == 1) {
						player++;
					}
					else
						player--;
					
					// Calling draw if game is a stalemate
					boolean check_full = check_full_board(board);
					
					if (check_full == true) {
						System.out.println("The game is a draw");
					}	
				}	
			}
			System.out.println();
			System.out.println("Enter 1 to play another game: ");
			cont = in.nextInt();
			
			if (cont == 1) {
				board = clear_board(board);
				System.out.println("---------------------------------NEW GAME---------------------------------\n\n");
			}
		}
		while(cont == 1);
	}
	
	public static String[][] clear_board(String[][] ar) { // Clears board
		String [][] new_board = 
			   {{" "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," ",},
				{" "," "," "," "," "," "," ",},
				{" "," "," "," "," "," "," ",},
				{" "," "," "," "," "," "," ",}}; // Empty board for new game
		
		return new_board;
					
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("-------------------------------------------------------------WELCOME TO CONNECT 4-----------------------------------------------------\n\n");
		System.out.println("Player 1: O");
		System.out.println("Player 2: X");
		
		String[][] game = 
		   {{" "," "," "," "," "," "," "},
			{" "," "," "," "," "," "," "},
			{" "," "," "," "," "," "," ",},
			{" "," "," "," "," "," "," ",},
			{" "," "," "," "," "," "," ",},
			{" "," "," "," "," "," "," ",}}; // Empty board for the game
				
		
		//show_board(eg_game);
		
		//check_win(eg_game);
		
		//insert_element(eg_game,"O",2);
		
		play_connect4(game);
	}
}
