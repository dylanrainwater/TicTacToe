import java.util.Scanner;


public class Game {
	
	public enum State {
		PLAYING, WIN, LOSE;
	}
	
	public enum Mode {
		SINGLEPLAYER, MULTIPLAYER;
	}
	
	private static State gameState;
	private static Mode mode;
	private static Scanner keyboard;
	private static int playerTurn;
	private static String[][] board = {
		{"_","_","_"},
		{"_","_","_"},
		{"_","_","_"}
	};
	private static int difficulty = 1;
	
	public static void main(String[] args) {
		keyboard = new Scanner(System.in);
		
		init();
		
		int row, column;
		int tmpPlayer = 1;
		
		if (mode.equals(Mode.MULTIPLAYER)) {
			String input;
			
			while (gameState.equals(State.PLAYING)) {
				System.out.println("\nPlayer " + playerTurn + "'s turn!");
				// Make sure the player makes a valid placement, they can't take someone else's spot
				tmpPlayer = playerTurn;
				while (playerTurn == tmpPlayer) {
					System.out.println("\nEnter the coordinate you want to take up as \"row,column\"");
					
					input = keyboard.nextLine();
					
					row = Integer.parseInt(input.split(",")[0]) - 1;
					column = Integer.parseInt(input.split(",")[1]) - 1;
					
					if (board[row][column].equals("_")) {
						if (playerTurn == 1) {
							board[row][column] = "X";
							playerTurn = 2;
						} else {
							board[row][column] = "O";
							playerTurn = 1;
						}
					}
				}

				print(board);
				checkForWin(board);
			}
			
		} else { // Singleplayer, needs AI
			String input;

			AI ai = new AI(difficulty);
			
			while (gameState.equals(State.PLAYING)) {
				System.out.println("\nPlayer " + playerTurn + "'s turn!");
				
				// Make sure the player makes a valid placement, they can't take up the AI's or their own spot
				while (playerTurn == 1) {
					System.out.println("\nEnter the coordinate you want to take up as \"row,column\"");
					
					input = keyboard.nextLine();
					
					row = Integer.parseInt(input.split(",")[0]) - 1;
					column = Integer.parseInt(input.split(",")[1]) - 1;
					
					if (board[row][column].equals("_")) {
						if (playerTurn == 1) {
							board[row][column] = "X";
							playerTurn = 2;
						} else {
							board[row][column] = "O";
							playerTurn = 1;
						}
					}
				}
				if (playerTurn == 2) {
					ai.makeMove(board);
					playerTurn = 1;
				}
				
				checkForWin(board);
				print(board);
			}
			
		}
	}
	
	public static void init() {
		System.out.println("Singleplayer or Multiplayer?");
		String input;

		input = keyboard.nextLine();
		
		if (input.toLowerCase().equals("singleplayer")) {
			mode = Mode.SINGLEPLAYER;
			System.out.println("SinglePlayer! Enter 1 for easy mode, or 2 for hard mode!");
			difficulty = Integer.parseInt(keyboard.nextLine());
		} else {
			mode = Mode.MULTIPLAYER;
		}
		
		gameState = State.PLAYING;
		playerTurn = 1;
		
		
	}
	
	public static void print(String[][] board) {
		for (int i = 0; i < 3; i++) {
			System.out.println();
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
		}
	}
	
	public static boolean checkForWin(String[][] board) {
		
		boolean win = false;
		/* Diagonal win */
		if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
			if (board[0][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1 wins!");
				win = true;
			} else if (board[0][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2 wins!");
				win = true;
			}
		} else if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) ) {
			if (board[0][2].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1 wins!");
				win = true;
			} else if (board[0][2].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2 wins!");
				win = true;
			}
		}
		/* Horizontal win */
		if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2])) {
			if (board[0][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1 wins!");
				win = true;
			} else if (board[0][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2 wins!");
				win = true;
			}
		} else if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2])) {
			if (board[1][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1 wins!");
				win = true;
			} else if (board[1][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2 wins!");
				win = true;
			}
		} else if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2])) {
			if (board[2][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1 wins!");
				win = true;
			} else if (board[2][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2 wins!");
				win = true;
			}
		}
		/* Vertical win */
		if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0])) {
			if (board[0][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1 wins!");
				win = true;
			} else if (board[0][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2 wins!");
				win = true;
			}
		} else if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1])) {
			if (board[0][1].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1 wins!");
				win = true;
			} else if (board[0][1].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2 wins!");
				win = true;
			}
		} else if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2])) {
			if (board[0][2].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1 wins!");
				win = true;
			} else if (board[0][2].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2 wins!");
				win = true;
			}
		}
		int count = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j].equals("_")) {
					count++;
				}
			}
		}
		if (count == 0) {
			gameState = State.WIN;
			System.out.println("Tie!");
			win = true;
		}
		return win;
	}
}
