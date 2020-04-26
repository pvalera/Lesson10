package exercises;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import examples.FileHelper;

public class Hangman extends KeyAdapter {

	Stack<String> puzzles = new Stack<String>();
	ArrayList<JLabel> boxes = new ArrayList<JLabel>();
	int lives = 9;
	JLabel livesLabel = new JLabel("" + lives);

	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		hangman.addPuzzles();
		hangman.createUI();
	}

	public List<String> loadWords() {
		return FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	}
	
	private void addPuzzles() {
		puzzles.push("defenestrate");
		puzzles.push("fancypants");
		puzzles.push("elements");

//		// use word list
//		puzzles.addAll(loadWords());
		
//		remove special characters and spaces and convert to lower case characters
//		UnaryOperator<String> lowcase = (x) -> x.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
//      puzzles.replaceAll(lowcase);
	}

	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
		playDeathKnell();
		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		loadNextPuzzle();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
	}

	private void loadNextPuzzle() {
		removeBoxes();
		lives = 9;
		livesLabel.setText("" + lives);		

		// repeat process of loading puzzle until word does not contain special characters 
		boolean isNotValid = false;
		do {
			isNotValid = false;
			try {				
				// generate random word from the list
				Random generator = new Random();
				int randomIndex = generator.nextInt(puzzles.size());
				puzzle = puzzles.push(puzzles.get(randomIndex).toLowerCase());
				System.out.println("Puzzle is now " + puzzle + ".");

				// check if word contains special characters
			    Pattern p = Pattern.compile("^[a-zA-Z]+$");
			    Matcher m = p.matcher(puzzle);
			    if (!m.find()) {
			    	throw new Exception("Puzzle has special characters.");
			    }
			} catch (Exception e) {
				System.out.println("*** Puzzle " + puzzle + " has special characters. Please wait for new puzzle to load.");
				isNotValid = true;
			}
		} while (isNotValid);

		createBoxes();
	}	
	
	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		updateBoxesWithUserInput(arg0.getKeyChar());

		// check if puzzle is solved
		// output guessed word as string
		String guess = "";
		for (int j = 0; j < boxes.size(); j++) {
			guess += boxes.get(j).getText();
		}

		if (guess.contains(puzzle)) {
			System.out.println("Congratulations! You solved the puzzle. Please wait for a new puzzle to load.");
			playDeathKnell();
			loadNextPuzzle();
		}
		// did not solve puzzle - ran out of lives
		else if (lives == 0) {
			System.out.println("Game Over. You have ran out of lives.");
			System.exit(0);
		}
	}

	private void updateBoxesWithUserInput(char keyChar) {
		boolean gotOne = false;
		for (int i = 0; i < puzzle.length(); i++) {
			if (puzzle.charAt(i) == keyChar) {
				boxes.get(i).setText("" + keyChar);
				gotOne = true;
			}
		}
		if (!gotOne) {
			livesLabel.setText("" + --lives);
			System.out.println("There are no \"" + keyChar + "\". Try again.");
		}
	}

	void createBoxes() {
		for (int i = 0; i < puzzle.length(); i++) {
			JLabel textField = new JLabel("_");
			boxes.add(textField);
			panel.add(textField);
		}
	}

	void removeBoxes() {
		for (JLabel box : boxes) {
			panel.remove(box);
		}
		boxes.clear();
	}
	
	public void playDeathKnell() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resource/funeral-march.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			Thread.sleep(8400);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

