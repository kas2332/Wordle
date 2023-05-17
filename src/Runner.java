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

        if (backspace || letter) {
            wordleGUI.jLabels[row][col].setEditable(false);
            if (backspace) {
                if (wordleGUI.jLabels[row][col].getText().equals("") && col != 0) {
                    wordleGUI.jLabels[row][col - 1].setText("");
                    wordleGUI.jLabels[row][col - 1].setEditable(true);
                } else {
                    wordleGUI.jLabels[row][col].setText("");
                    wordleGUI.jLabels[row][col].setEditable(true);
                }
            } else {
                wordleGUI.jLabels[row][col].setText(String.valueOf(e.getKeyChar()).toUpperCase());
                if (col == 4) {
                    wordleGUI.jLabels[row][col].setEditable(true);
                } else {
                    wordleGUI.jLabels[row][col + 1].setEditable(true);
                }
            }
        }
    }
}