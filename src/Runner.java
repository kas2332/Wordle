import java.awt.event.KeyEvent;

public class Runner {
    static WordleGUI wordleGUI;

    public static void main (String[] args) {
        wordleGUI = new WordleGUI();
        wordleGUI.wordleGUIMaker();
    }
    public void printChar (KeyEvent e, int row, int col) {
        if (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z) {
            wordleGUI.jLabels[row][col].setText(String.valueOf(e.getKeyChar()));
            wordleGUI.jLabels[row][col].setEditable(false);
            if (col == 4) {
                wordleGUI.jLabels[row][col].setEditable(true);
            }
            else {
                wordleGUI.jLabels[row][col + 1].setEditable(true);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (col == 0) {
                wordleGUI.jLabels[row][col].setEditable(true);
            }
            else {
                wordleGUI.jLabels[row][col - 1].setEditable(true);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
           if (col == 4) {
                wordleGUI.jLabels[row][col].setEditable(true);
            }
            else {
                wordleGUI.jLabels[row][col + 1].setEditable(true);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_BACKSPACE) {
            wordleGUI.jLabels[row][col].setText(null);
            wordleGUI.jLabels[row][col].setEditable(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER) {//ajfljasnfja
            
        }
    }
}
