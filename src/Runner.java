import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Runner{
    static WordleGUI wordleGUI;
    String[] words = new String[14855];
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.readWordsList();
        wordleGUI = new WordleGUI();
        wordleGUI.wordleGUIMaker();
    }

    public void readWordsList () {
        int i = 0;
        for (; i < 14855; i++) {
            try {
                words[i] = Files.readAllLines(Paths.get("C:\\Users\\ks4292\\IdeaProjects\\Wordle\\src\\ValidWords.txt")).get(i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void printChar(KeyEvent e, int row, int col) {
        boolean backspace = (e.getKeyCode() == KeyEvent.VK_BACK_SPACE);
        boolean letter = (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z);
        boolean enter = (e.getKeyCode() == KeyEvent.VK_ENTER);

        if (backspace || letter || enter) {
            wordleGUI.jLabels[row][col].setEditable(false);
            if (backspace) {
                doBackspace(row, col);
            } else if (letter) {
                doLetter(e, row, col);
            } else if (checkIfIsValidGuess(row)){
                doEnter(row, col);
            } else {
                dispMessage("invalid guess");
                wordleGUI.jLabels[row][col].setEditable(true);
            }
        }
    }
    
    public void doBackspace (int row, int col) {
        if (wordleGUI.jLabels[row][col].getText().equals("") && col != 0) {
            wordleGUI.jLabels[row][col - 1].setText("");
            wordleGUI.jLabels[row][col - 1].setEditable(true);
        } else {
            wordleGUI.jLabels[row][col].setText("");
            wordleGUI.jLabels[row][col].setEditable(true);
        }
    }
    
    public void doLetter (KeyEvent e, int row, int col) {
        wordleGUI.jLabels[row][col].setText(String.valueOf(e.getKeyChar()).toUpperCase());
        if (col == 4) {
            wordleGUI.jLabels[row][col].setEditable(true);
        } else {
            wordleGUI.jLabels[row][col + 1].setEditable(true);
        }
    }
    
    public void doEnter (int row, int col) {
        wordleGUI.jLabels[row][col].setEditable(false);
        wordleGUI.jLabels[row + 1][0].setEditable(true);   
    }

    public boolean checkIfIsValidGuess(int row) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            s.append(wordleGUI.jLabels[row][i].getText());
        }
        System.out.println(binarySearch(s.toString().toLowerCase()));
        return (s.length() == 5) && (binarySearch(s.toString().toLowerCase()) >= 0);
    }
    
    public int binarySearch(String x)
    {
        int l = 0, r = words.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            System.out.println(l);
            System.out.println(r);
            System.out.println(m);
            System.out.println(words[m]);
            int res = x.compareTo(words[m]);
 
            // Check if x is present at mid
            if (res == 0)
                return m;
 
            // If x greater, ignore left half
            if (res > 0)
                l = m + 1;
 
            // If x is smaller, ignore right half
            else
                r = m - 1;
        }
        return -1;
    }
    public void dispMessage (String message) {
        switch (message) {
            case "invalid guess" ->
                    JOptionPane.showMessageDialog(null, "Error, word is not valid.", "Error", JOptionPane.ERROR_MESSAGE);
            case "lose" ->
                    JOptionPane.showMessageDialog(null, "Sorry, you lost.", "Error", JOptionPane.ERROR_MESSAGE);
            case "win" ->
                    JOptionPane.showMessageDialog(null, "Hooray, you win!", "Error", JOptionPane.ERROR_MESSAGE);
        }            }
}
