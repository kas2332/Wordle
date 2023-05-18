import java.awt.event.KeyEvent;

public class Runner {
    static WordleGUI wordleGUI;

    public static void main(String[] args) {
        wordleGUI = new WordleGUI();
        wordleGUI.wordleGUIMaker();
    }

    public void printChar(KeyEvent e, int row, int col) {
        boolean backspace = (e.getKeyCode() == KeyEvent.VK_BACK_SPACE);
        boolean letter = (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z);
        boolean enter = (e.getKeyCode() == KeyEvent.VK_ENTER);

        if ((backspace || letter || enter) && row < 6) {
            wordleGUI.jLabels[row][col].setEditable(false);
            if (backspace) {
                doBackspace(row, col);
            } else if (letter) {
                doLetter(row, col);
            } else if (isValidGuess(row)){
                doEnter(row, col);
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
    
    public void doLetter (int row, int col) {
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

    public boolean isValidGuess (int row) {
        String s = "";
        for (int i; i < 5; i++) {
            s += wordleGUI.jLabels[row][i];
        }
        if ((s.lenght() == 5) && (binarySearch(s) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    static int binarySearch(String[] arr, String x)
    {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
 
            int res = x.compareTo(arr[m]);
 
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
}
