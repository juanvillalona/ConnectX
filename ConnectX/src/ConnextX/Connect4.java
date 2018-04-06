package ConnextX;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * This class is dedicated to the GUI and logic behind the Connect 4 game. Players take turns trying to connect their chips
 * in either row, column, or diagonal combinations. If the board is full and both players fail to connect four of their chips
 * then the game will result in a draw. In addition, players can also choose how many chips they wish to connect before the game
 * starts. If a winner is chosen and players wish to play again, then the new game will start with the winning player going first.
 * 
 * @author Mayuri
 *
 */

public class Connect4 extends JFrame implements ActionListener {
	
	private JButton [][] board;
	private JPanel boardPanel;
	private JPanel mainPanel;
	private JPanel userIOPanel;
	private JLabel displayLbl;
	private String currPlayer = "X";
	private ImageIcon imgX = new ImageIcon("C:\\Users\\Mayuri\\Desktop\\Java Workspace\\cmp326\\Images\\smaller circle.jpg");
	private ImageIcon imgO = new ImageIcon("C:\\Users\\Mayuri\\Desktop\\Java Workspace\\cmp326\\Images\\blue circle.png");
	private int connectX;
	
	public Connect4 (){
		this.setTitle("Connect Four");
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		userIOPanel = new JPanel();
		userIOPanel.setBorder(new EtchedBorder());
		displayLbl = new JLabel();
		userIOPanel.add(displayLbl);
		mainPanel.add(userIOPanel, BorderLayout.NORTH); //this will throw the IOpanel to the top of the screen and keep it there
		
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(6,7));
		displayBoard();
		
		mainPanel.add(boardPanel, BorderLayout.CENTER); //this will keep the main board in the center
		add(mainPanel);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700,500);
		this.setVisible(true);
	
	}
	
	public void updateDisplay(String msg) {
		if (currPlayer == "X") {
		displayLbl.setText("<HTML><H1 color = red>" + msg + "</H1></HTML>");
	}
		else if(currPlayer == "O") {
			displayLbl.setText("<HTML><H1 color = blue>" + msg + "</H1></HTML>");
		}
		else {
			displayLbl.setText("<HTML><H1 color = purple>" + msg + "</H1></HTML>");
		}
	}

	public void updateAPlayer(JButton btnClicked) {
		if(currPlayer.equalsIgnoreCase("X")) {
			currPlayer = "O";
			updateDisplay("BLUE GOES");
		}
		else {
			currPlayer = "X";
			updateDisplay("RED GOES");
		}
		
	}
	
	/**
	 * Checks the board after a player has placed a chip onto the board. All rows, columns, and diagonals are checked 
	 * to see if there are four chips of the same type in a row.
	 * @param player
	 * @return
	 */
	public boolean checkForWinner(String player) {
		
		// check rows
		for (int row = 0; row < board.length; row++) {
			int markCounter = 0;
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col].getText().contains(player)) {
					markCounter++;
				}
				else if(! (board[row][col].getText().contains(player)) ) {
					markCounter = 0;
				}
				if (markCounter == connectX) {
					return true;
				}
			}
		}
		
		
		// check columns
		for (int col = 0; col < 7; col++) {
			int markCounter = 0;
			for (int row = board.length - 1; row >= 0; row--) {
				if(board[row][col].getText().contains(player)) {
					markCounter++;
				}
				else if(! (board[row][col].getText().contains(player)) ) {
					markCounter = 0;
				}
				if (markCounter == connectX) {
					return true;
				}
			}
		}
		
		/*
		 * This algorithm checks the right side of the second diagonal on the board. The while loop iterates four times from bottom 
		 * to top, right to left. 
		 * The reason it only iterates four times is because after a certain point, it is impossible for a player to connect four
		 * so the code does'nt have to check the corners.
		 * 		O X X X O O O
		 * 		O O X X X O O
		 * 		O O O X X X O
		 *		O O O O X X X
		 * 		O O O O O X X
		 * 		O O O O O O X
		 * 
		 */
		int diag2Count = 0;
		int row = board.length - 1;
		int col = 6;
		int iteration = 0;
		int rowPlace = row;
		while (iteration < 4) {
			row = rowPlace;
			col = 6;
			while (row >= 0 && col >= 1) {
				if (board[row][col].getText().contains(currPlayer)) {
					diag2Count++;
				}
				if (!(board[row][col].getText().contains(player))) {
					diag2Count = 0; //If there is a chip which does not belong to a player in the sequence, the counter is reset.
				} else {
					if (diag2Count == connectX) {
						return true;
					}
				}
				row--;
				col--;
			}
			iteration++;
			rowPlace--;
		}

		/*
		 * Checks the left hand side of the secondary diagonal. Iterates only 3 times.
		 * of the board.
		 * 			X O O O O O O
		 * 			X X O O O O O
		 * 			X X X O O O O
		 * 			O X X X O O O
		 * 			O O X X X O O
		 * 			O O O X X X O
		 */
		row = board.length - 1;
		col = 3;
		iteration = 0;
		int colPlace = col;
		while (iteration < 4) {
			col = colPlace;
			row = board.length -1;
			while (row >= 0 && col >= 0) {
				if (board[row][col].getText().contains(currPlayer)) {
					diag2Count++;
				}
				if (!(board[row][col].getText().contains(player))) {
					diag2Count = 0; //If there is a chip which does not belong to a player in the sequence, the counter is reset.
				} else {
					if (diag2Count == connectX) {
						return true;
					}
				}
				row--;
				col--;
			}
			iteration++;
			colPlace++;
		}
		
		/*
		 * Checks the left hand main Diagonals and iterates 3 times.
		 * 			O O O X X X O
		 * 			O O X X X O O
		 * 			O X X X O O O 
		 * 			X X X O O O O
		 * 			X X O O O O O
		 * 			X O O O O O O
		 * 
		 */
		row = 3;
		col = 0;
		rowPlace = row;
		iteration = 0;
		int diagMainCounter = 0;
		while (iteration < 3) {
			row = rowPlace;
			col = 0;
			while (row >= 0 && col <= 6) {
				if (board[row][col].getText().contains(currPlayer)) {
					diagMainCounter++;
				}
				if (!(board[row][col].getText().contains(player))) {
					diagMainCounter = 0; //If there is a chip which does not belong to a player in the sequence, the counter is reset.
				} else {
					if (diagMainCounter == connectX) {
						return true;
					}
				}
				row--;
				col++;
			}
			iteration++;
			rowPlace++;
		}
		
		/*
		 * Checks the right hand side of the main diagonal and iterates 4 times.
		 * 
		 *			O O O O O O X
		 *			O O O O O X X
		 *			O O O O X X X
		 *			O O O X X X O
		 *			O O X X X O O
		 *			O X X X O O O
		 */
		row = board.length -1;
		col = 0;
		colPlace = col;
		iteration = 0;
		diagMainCounter = 0;
		while (iteration < 4) {
			col = colPlace;
			row = board.length -1;
			while (row >= 0 && col <= 6) {
				if (board[row][col].getText().contains(currPlayer)) {
					diagMainCounter++;
				}
				if (!(board[row][col].getText().contains(player))) {
					diagMainCounter = 0; //If there is a chip which does not belong to a player in the sequence, the counter is reset.
				} else {
					if (diagMainCounter == connectX) {
						return true;
					}
				}
				row--;
				col++;
			}
			iteration++;
			colPlace++;
		}
		return false;
	}

	public boolean isFull() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				String text = board[row][col].getText();
				if (!(text.contains("X")) && !(text.contains("O"))) {
					return false;
				}
			}
		}
		return true;
	}

	public void displayWinner(String player) {
		if (player.equals("X")) {
			updateDisplay("RED IS THE WINNER");
		} else if (this.checkForWinner("O")) {
			updateDisplay("BLUE IS THE WINNER");
		} else {
			updateDisplay("DRAW");
		}

	}

	public void clearBoard() {

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col].setText("");
				board[row][col].setIcon(null);
				board[row][col].setEnabled(true);
			}
		}
	}

	public void playAnotherGame() {
		int yesNo = JOptionPane.showConfirmDialog(null, "Do you want to play another game?");
		if (yesNo == 0) {
			clearBoard();
			if (currPlayer.equalsIgnoreCase("X")) {
				updateDisplay("<HTML><H1 color = red>RED GOES FIRST </H1></HTML>");
			} else if (currPlayer.equalsIgnoreCase("O")) {
				updateDisplay("<HTML><H1 color = blue>BLUE GOES FIRST </H1></HTML");
			}
		}

		else {
			displayLbl.setText("Thanks for playing");
			JOptionPane.showMessageDialog(null, "Thanks for Playing!");
			System.exit(EXIT_ON_CLOSE);
		}
		System.out.println(yesNo);
	}

	public void setConnectX(int userInput) {
		connectX = userInput;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		setButtonOnMap(btnClicked);

		if (checkForWinner(currPlayer) || isFull()) {
			displayWinner(currPlayer);
			playAnotherGame();
		} else {

			updateAPlayer(btnClicked);
		}
	}
	
	private void setButtonIcon(JButton btnClicked) {
		
		if (btnClicked.getText().contains("X")) {
			btnClicked.setIcon(imgX);
			
			
		}
		if (btnClicked.getText().contains("O")) {
			btnClicked.setIcon(imgO);
			
		}
	}
	
	
	/**
	 * This method sets the button on the board according to the column that was
	 * clicked. According to the rules of Connect Four (and gravity) when a
	 * player places a chip onto the board the chip occupies the lower region of
	 * the column it was placed in. This method sets the chip onto the lowest
	 * available slot and disables the button it was placed in.
	 * 
	 * @param btnClicked
	 * @return
	 */
	public boolean setButtonOnMap(JButton btnClicked) {
		int buttonCol = 0;

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == btnClicked) {
					buttonCol = col;
				}
			}
		}

		boolean okToPlace = false;
		int row = board.length - 1;
		while (!okToPlace) {
			if (board[row][buttonCol].getText().equals("")) {
				if (currPlayer.equalsIgnoreCase("X")) {
					board[row][buttonCol].setText(currPlayer);
					setButtonIcon(board[row][buttonCol]);
					board[row][buttonCol].setDisabledIcon(imgX);

					okToPlace = true;
				} else if (currPlayer.equalsIgnoreCase("O")) {
					board[row][buttonCol].setText(currPlayer);
					setButtonIcon(board[row][buttonCol]);
					board[row][buttonCol].setDisabledIcon(imgO);

					okToPlace = true;
				}
			} else {
				okToPlace = false;
				row--;
			}
		}
		return false;
	}
	
	/**
	 * A method to display the board onto the GUI. We use a loop in order to
	 * iterate along every slot on the 2D array and create new JButtons along
	 * the way.
	 */
	public void displayBoard() {
		board = new JButton[6][7];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = new JButton();
				board[row][col].addActionListener(this);
				board[row][col].setBorder(new EtchedBorder());
				board[row][col].setEnabled(true);
				boardPanel.add(board[row][col]);
			}
		}
	}
}


