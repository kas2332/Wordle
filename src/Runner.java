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
            if (col == 5) {
                System.out.println(1);
                wordleGUI.jLabels[row + 1][0].setEditable(true);
            }
            else if (row == 7) {
                System.out.println(2);
                wordleGUI.jLabels[row][col].setEditable(true);
            }
            else {
                System.out.println(3);
                System.out.println(col);
                System.out.println(row);
                wordleGUI.jLabels[row][col + 1].setEditable(true);
            }
        }
    }
}
