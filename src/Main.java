import java.util.Scanner;


public class Main {
	
	public enum State {
		PLAYING, WIN, LOSE;
	}
	
	public enum Mode {
		SINGLEPLAYER, MULTIPLAYER;
	}
	
	private static State gameState;
	private static Mode mode;
	private static String input;
	private static Scanner keyboard;
	private static int playerTurn;
	private static String[][] board = {
		{"_","_","_"},
		{"_","_","_"},
		{"_","_","_"}
	};
	
	
	public static void main(String[] args) {
		keyboard = new Scanner(System.in);
		
		init();
		
		int row, column;
		int tmpPlayer = 1;
		
		if (mode.equals(Mode.MULTIPLAYER)) {
			
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
		}
	}
	
	public static void init() {
		System.out.println("Singleplayer or Multiplayer?");
		
		input = keyboard.nextLine();
		
		if (input.toLowerCase().equals("singleplayer")) {
			mode = Mode.SINGLEPLAYER;
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
	
	public static void checkForWin(String[][] board) {
		/* Diagonal win */
		if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
			if (board[0][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1, wins!");
			} else if (board[0][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2, wins!");
			}
		} else if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) ) {
			if (board[0][2].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1, wins!");
			} else if (board[0][2].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2, wins!");
			}
		}
		/* Horizontal win */
		if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2])) {
			if (board[0][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1, wins!");
			} else if (board[0][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2, wins!");
			}
		} else if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2])) {
			System.out.println("Horizontal-2");
			if (board[1][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1, wins!");
			} else if (board[1][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2, wins!");
			}
		} else if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2])) {
			if (board[2][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1, wins!");
			} else if (board[2][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2, wins!");
			}
		}
		/* Vertical win */
		if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0])) {
			if (board[0][0].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1, wins!");
			} else if (board[0][0].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2, wins!");
			}
		} else if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1])) {
			if (board[0][1].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1, wins!");
			} else if (board[0][1].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2, wins!");
			}
		} else if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2])) {
			if (board[0][2].equals("X")) {
				gameState = State.WIN;
				System.out.println("Player 1, wins!");
			} else if (board[0][2].equals("O")) {
				gameState = State.WIN;
				System.out.println("Player 2, wins!");
			}
		}
	}
}
