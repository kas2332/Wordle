import java.awt.event.KeyEvent;

public class Runner {
    static WordleGUI wordleGUI;

    public static void main(String[] args) {
        wordleGUI = new WordleGUI();
        wordleGUI.wordleGUIMaker();
    }

    public void printChar(KeyEvent e, int row, int col) {
        if (((e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z) || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) && e.getKeyCode() != KeyEvent){
            wordleGUI.jLabels[row][col].setEditable(false);
            if (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z) {
                wordleGUI.jLabels[row][col].setText(String.valueOf(e.getKeyChar()).toUpperCase());
                if (col == 4) {
                    wordleGUI.jLabels[row][col].setEditable(true);
                } else {
                    wordleGUI.jLabels[row][col + 1].setEditable(true);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (col == 0) {
                    wordleGUI.jLabels[row][col].setEditable(true);
                } else {
                    wordleGUI.jLabels[row][col - 1].setEditable(true);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (col == 4) {
                    wordleGUI.jLabels[row][col].setEditable(true);
                } else {
                    wordleGUI.jLabels[row][col + 1].setEditable(true);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                wordleGUI.jLabels[row][col].setText(null);
                wordleGUI.jLabels[row][col].setEditable(true);
            }
//        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//            if (row < 6) {
//                wordleGUI.jLabels[row + 1][col].setEditable(true);
//                //checkWord();
//            } else {
//               // checkFinalWord();
//            }
//        }
        }
    }
}