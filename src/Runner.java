import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Runner {
    static WordleGUI wordleGUI;
    static String[] words = new String[14855];
    char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    boolean frameVisible = true;
    static String hiddenWord;

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.readWordsList();
        wordleGUI = new WordleGUI();
        wordleGUI.wordleGUIMaker();
    }

    public void readWordsList() {
        int i = 0;
        for (; i < 14855; i++) {
            try {
                words[i] = Files.readAllLines(Paths.get("src/ValidWords.txt")).get(i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        hiddenWord = words[(int) (Math.random() * 14855)];
        System.out.println(hiddenWord);
    }
    
    public void findHiddenWord () {
        hiddenWord = words[(int) (Math.random() * 14855)];
        System.out.println(hiddenWord);
    }

    public void printChar(KeyEvent e, int row, int col) {
        boolean backspace = (e.getKeyCode() == KeyEvent.VK_BACK_SPACE);
        boolean letter = (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z);
        boolean enter = (e.getKeyCode() == KeyEvent.VK_ENTER);

        if ((backspace || letter || enter) && frameVisible) {
            wordleGUI.jLabels[row][col].setEditable(false);
            if (backspace) {
                doBackspace(row, col);
            } else if (letter && checkIfIsValidChar(e)) {
                doLetter(e, row, col);
            } else if (letter && !checkIfIsValidChar(e)) {
                wordleGUI.jLabels[row][col].setEditable(true);
            } else if (checkIfIsValidGuess(row)) {
                doEnter(row);
            } else if (!checkIfIsValidGuess(row)) {
                displayMessage("invalid guess");
                wordleGUI.jLabels[row][col].setEditable(true);
            } else {
                displayMessage("Error");
            }
        }
    }

    public void doBackspace(int row, int col) {
        if (wordleGUI.jLabels[row][col].getText().equals("") && col != 0) {
            wordleGUI.jLabels[row][col - 1].setText("");
            wordleGUI.jLabels[row][col - 1].setEditable(true);
        } else {
            wordleGUI.jLabels[row][col].setText("");
            wordleGUI.jLabels[row][col].setEditable(true);
        }
    }

    public void doLetter(KeyEvent e, int row, int col) {
        wordleGUI.jLabels[row][col].setText(String.valueOf(e.getKeyChar()).toUpperCase());
        if (col == 4) {
            wordleGUI.jLabels[row][col].setEditable(true);
        } else {
            wordleGUI.jLabels[row][col + 1].setEditable(true);
        }
    }

    public void doEnter(int row) {
        checkLetters(row);
        if (row < 5) {
            wordleGUI.jLabels[row + 1][0].setEditable(true);
        }
    }

    public boolean checkIfIsValidGuess(int row) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            s.append(wordleGUI.jLabels[row][i].getText().toLowerCase());
        }
        return (s.length() == 5) && (binarySearch(s.toString().toLowerCase()) >= 0);
    }

    public boolean checkIfIsValidChar(KeyEvent e) {
        boolean found = false;
        for (char c : alphabet) {
            if (c == e.getKeyChar()) {
                found = true;
            }
        }
        return found;
    }

    public int binarySearch(String x) {
        int lowerBound = 0, upperBound = words.length - 1;

        while (lowerBound <= upperBound) {
            int middle = lowerBound + (upperBound - lowerBound) / 2;
            int res = x.compareTo(words[middle]);

            // Check if x is present at mid
            if (res == 0)
                return middle;

            // If x greater, ignore left half
            if (res > 0)
                lowerBound = middle + 1;

                // If x is smaller, ignore right half
            else
                upperBound = middle - 1;
        }
        return -1;
    }

    public void checkLetters (int row) {
        StringBuilder guess = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            guess.append(wordleGUI.jLabels[row][i].getText().toLowerCase());
        }

        for (int i = 0; i < hiddenWord.length(); i++) {
            System.out.println(hiddenWord);
            System.out.println(guess);
            System.out.println(hiddenWord.charAt(i));
            System.out.println(guess.charAt(i));
            if (hiddenWord.charAt(i) == guess.charAt(i)) {
                System.out.println(1);
                wordleGUI.jLabels[row][i].setRight();
            } else if (hiddenWord.indexOf(guess.charAt(i)) >= 0) {
                System.out.println(2);
                wordleGUI.jLabels[row][i].setWrong();
            } else {
                System.out.println(3);
                wordleGUI.jLabels[row][i].setNone();
            }
        }
        checkWin(row);
    }

    public void checkWin (int row) {
        boolean correct = true;
        for (int i = 0; i < 5; i++) {
            if (!wordleGUI.jLabels[row][i].isRight) {
                correct = false;
                break;
            }
        }
        if (correct) {
            doWin(row);
        } else if (row == 5) {
            doLose();
        }
    }

    public void doWin (int row) {
        for (int i = 0; i < 5; i++) {
            wordleGUI.jLabels[6][i].setText(wordleGUI.jLabels[row][i].getText());
        }
        displayMessage("win");
    }

    public void doLose () {
        for (int i = 0; i < 5; i++) {
            wordleGUI.jLabels[6][i].setText(String.valueOf(hiddenWord.charAt(i)));
        }
        displayMessage("lose");
    }
    
    public void playAgain() {
        findHiddenWord();
        wordleGUI.wordleGUIMaker();
    }

    public void displayMessage(String message) {
        int option;
        switch (message) {
            case "invalid guess" -> {
                wordleGUI.frame.setVisible(false);
                frameVisible = false;
                JOptionPane.showMessageDialog(null, "Error, word is not valid.", "Error", JOptionPane.ERROR_MESSAGE);
                wordleGUI.frame.setVisible(true);
            }
            case "lose" -> {
                wordleGUI.frame.setVisible(false);
                frameVisible = false;
                option = JOptionPane.showConfirmDialog(null, "Sorry, you lost.\nDo you want to ", "Lost :(", JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    playAgain();
                } else {
                    JOptionPane.showMessageDialog(null, "GoodBye! :)", "Bye", JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
            }
            case "win" -> {
                wordleGUI.frame.setVisible(false);
                frameVisible = false;
                option = JOptionPane.showConfirmDialog(null, "Hooray, you win!\nDo you want to play again?", "Win!", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    playAgain();
                } else {
                    JOptionPane.showMessageDialog(null, "GoodBye! :)", "Bye", JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
                }
            }
            case "Error" -> {
                JOptionPane.showMessageDialog(null, "Error, something went wrong. Please try again later", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-99999);
            }
        }
    }
}
