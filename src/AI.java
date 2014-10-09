import java.util.Random;


public class AI {

	int difficulty; // 0 = EASY, 1 = HARD

	Random RNG = new Random();
	boolean myTurn = true;
	
	int row, column;

	public AI(int difficulty) {
		this.difficulty = difficulty;
	}

	public void makeMove(String[][] board) {
		myTurn = true;
		
		if (difficulty == 1) {
			/*
			 * Easy AI, just randomly pick an empty spot and place your marker
			 */
			
			placeRandomly(board);

		} else {
			/* 
			 * Hard AI, scan board and block the player's attempt at winning!
			 */
			
			if (myTurn) {
				/* Block Diagonal */
				if (board[0][0].equals(board[1][1])) {
					if (board[0][0].equals("X") && board[2][2].equals("_")) {
						board[2][2] = "O";
						myTurn = false;
						return;
					}
				} else if (board[1][1].equals(board[1][2])) {
					if (board[1][1].equals("X") && board[0][0].equals("_")) {
						board[0][0] = "O";
						myTurn = false;
						return;
					}
				} else if (board[0][2].equals(board[1][1])) {
					if (board[0][2].equals("X") && board[2][0].equals("_")) {
						board[2][0] = "O";
						myTurn = false;
						return;
					}
				} else if (board[1][1].equals(board[2][1])) {
					if (board[1][1].equals("X")) {
						board[0][2] = "O";
						myTurn = false;
						return;
					}
				} else if (board[0][0].equals(board[2][2])) {
					if (board[0][0].equals("X") && board[1][1].equals("_")) {
						board[1][1] = "O";
						myTurn = false;
						return;
					}
				} else if (board[0][2].equals(board[2][0])) {
					if (board[0][2].equals("X") && board[1][1].equals("_")) {
						board[1][1] = "O";
						myTurn = false;
						return;
					}
				} 
				/* Block horizontal */
				for (int i = 0; i < 3; i++) {
					if (board[i][0].equals(board[i][2])) {
						if (board[i][0].equals("X") && board[i][1].equals("_")) {
							board[i][1] = "O";
							myTurn = false;
							return;
						}
					} else if (board[i][0].equals(board[i][1])) {
						if (board[i][0].equals("X") && board[i][2].equals("_")) {
							board[i][2] = "O";
							myTurn = false;
							return;
						}
					} else if (board[i][1].equals(board[i][2])) {
						if (board[i][1].equals("X") && board[i][0].equals("_")) {
							board[i][0] = "O";
							myTurn = false;
							return;
						}
					}
				}
				/* Block vertical */
				for (int i = 0; i < 3; i++) {
					if (board[0][i].equals(board[1][i])) {
						if (board[0][i].equals("X") && board[2][i].equals("_")) {
							board[2][i] = "O";
							myTurn = false;
							return;
						}
					} else if (board[1][i].equals(board[2][i])) {
						if (board[1][i].equals("X") && board[0][i].equals("_")) {
							board[0][i] = "O";
							myTurn = false;
							return;
						}
					} else if (board[0][i].equals(board[2][i])) {
						if (board[0][i].equals("X") && board[1][i].equals("_")) {
							board[1][i] = "O";
							myTurn = false;
							return;
						}
					}
				}
				/* Catch all, if the player hasn't made much progress, just place randomly */
				placeRandomly(board);
			}
		}
	}
	
	public void placeRandomly(String[][] board) {
		int[][] spaces = new int[3][3];
		int count = 0;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				spaces[i][j] = ((board[i][j].equals("_") ? 0 : 1));
				if (spaces[i][j] == 0) {
					count++;
				}
			}
		}
		
		while (myTurn) {
			
			if (count <= 0) {
				return;
			}
			
			row = RNG.nextInt(3);
			column = RNG.nextInt(3);

			if (spaces[row][column] == 0) {
				board[row][column] = "O";
				myTurn = false;
			}
		}
	}
}
