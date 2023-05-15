import java.awt.event.KeyEvent;

public class Runner {
    static WordleGUI wordleGUI;

    public static void main(String[] args) {
        wordleGUI = new WordleGUI();
        wordleGUI.wordleGUIMaker();
    }

    public void printChar(KeyEvent e, int row, int col) {
        wordleGUI.jLabels[row][col].setEditable(false);
        if (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z) {
            wordleGUI.jLabels[row][col].setText(String.valueOf(e.getKeyChar()));
            if (col == 4) {
                wordleGUI.jLabels[row][col].setEditable(true);
            } else {
                wordleGUI.jLabels[row][col + 1].setEditable(true);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println(1);
            if (col == 0) {
                System.out.println(2);
                wordleGUI.jLabels[row][col].setEditable(true);
                System.out.println(3);
            } else {
                System.out.println(4);
                wordleGUI.jLabels[row][col - 1].setEditable(true);
                System.out.println(5);
            }
            System.out.println(6);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (col == 4) {
                wordleGUI.jLabels[row][col].setEditable(true);
            } else {
                wordleGUI.jLabels[row][col + 1].setEditable(true);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            wordleGUI.jLabels[row][col].setText(null);
            wordleGUI.jLabels[row][col].setEditable(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (row < 6) {
                wordleGUI.jLabels[row + 1][col].setEditable(true);
                checkWord();
            } else {
                checkFinalWord();
            }
        }
    }
}
