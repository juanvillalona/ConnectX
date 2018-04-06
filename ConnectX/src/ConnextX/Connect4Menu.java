package ConnextX;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * This class is an extension of the Connect 4 game. It serves as a menu for the user to pick a version of the
 * game which is either: Connect 4, Connect 5 or ConnectX. After a selection has been made and the Connect4 GUI
 * has been called, the visibility of this class is set to false and is only called again once the game is over
 * and the user wishes to play again.
 * 
 * @author Mayuri
 *
 */
public class Connect4Menu extends JFrame implements ActionListener{

	JPanel jpMain, jpDisplay, jpButton;
	JTextField userInput;
	JLabel outputDisplay, jlBtn;
	JButton connect4, connect5, connectX;
	
	public Connect4Menu () {
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		
		jpDisplay = new JPanel();
		jpDisplay.setLayout(new GridLayout(1,1));
		
		outputDisplay = new JLabel();
		outputDisplay.setBorder(new TitledBorder(new EtchedBorder(), "Choose A Game!"));
		jpDisplay.add(outputDisplay, new BorderLayout());
		jpMain.add(jpDisplay, new BorderLayout().NORTH);
		
		jpButton = new JPanel();
		jpButton.setLayout(new BorderLayout());
		jlBtn = new JLabel();
		jlBtn.setLayout(new GridLayout(1,3));
		
		connect4 = new JButton();
		connect4.setText("CONNECT 4");
		connect4.addActionListener(this);
		jlBtn.add(connect4);
		
		connect5 = new JButton();
		connect5.setText("CONNECT 5");
		connect5.addActionListener(this);
		jlBtn.add(connect5);
		
		connectX = new JButton();
		connectX.setText("CONNECT X");
		connectX.addActionListener(this);
		jlBtn.add(connectX);
		
		jpButton.add(jlBtn);
		jpMain.add(jpButton, new BorderLayout().CENTER);
		
		add(jpMain);
		
		setSize(450, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	/**
	 * Method takes in an action command and calls the Connect4 GUI after a selection has been made.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actCmd = e.getActionCommand();
		
		switch (actCmd) {
		case "CONNECT 4":
			
			Connect4 c4 = new Connect4();
			c4.setConnectX(4);
			setVisible(false);
			break;
		case "CONNECT 5":
			
			Connect4 c5 = new Connect4();
			c5.setConnectX(5);
			setVisible(false);
			break;
		case "CONNECT X":
			int userNum = Integer.parseInt( JOptionPane.showInputDialog( null, "We're Playing CONNECTX!!\nEnter a number for X (Reccomended: 3-7)"));
			Connect4 c6 = new Connect4();
			c6.setConnectX(userNum);
			setVisible(false);
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		Connect4Menu c1 = new Connect4Menu();
	}
}




