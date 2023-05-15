import java.awt.event.KeyEvent;

public class Runner {
    public static void main (String[] args) {
        WordleGUI wordleGUIObj = new WordleGUI();
        wordleGUIObj.wordleGUIMaker();
    }
    public void printChar (KeyEvent e) {

        System.out.println(e.getKeyChar());
    }
}
