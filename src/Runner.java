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
            } else if (enter && !checkIfIsValidGuess(row)) {
                displayMessage("invalid guess");
                wordleGUI.jLabels[row][col].setEditable(true);
            } else {
                displayMessage("Error");
                System.exit(-99999);
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
        if (row < 5) {
            wordleGUI.jLabels[row + 1][0].setEditable(true);
        } else if (row == 5) {
            doLastGuess();
        }
    }

    public boolean checkIfIsValidGuess(int row) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            s.append(wordleGUI.jLabels[row][i].getText());
        }
        System.out.println(binarySearch(s.toString().toLowerCase()));
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

            System.out.println(lowerBound);
            System.out.println(upperBound);
            System.out.println(middle);
            System.out.println(words[middle]);
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

    public void doLastGuess() {
        System.out.println("IDK");
    }

    public void displayMessage(String message) {
        switch (message) {
            case "invalid guess" -> {
                wordleGUI.frame.setVisible(false);
                frameVisible = false;
                JOptionPane.showMessageDialog(null, "Error, word is not valid.", "Error", JOptionPane.ERROR_MESSAGE);
                wordleGUI.frame.setVisible(true);
            }
            case "lose" -> JOptionPane.showMessageDialog(null, "Sorry, you lost.", "Error", JOptionPane.ERROR_MESSAGE);
            case "win" -> JOptionPane.showMessageDialog(null, "Hooray, you win!", "Error", JOptionPane.ERROR_MESSAGE);
            case "Error" ->
                    JOptionPane.showMessageDialog(null, "Error, something went wrong. Please try again later", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
