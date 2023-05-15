import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
//        JLabel[][] jLabels = new JLabel[5][7];
//        for (int row = 0; row < 7; row++) {
//            for (int col = 0; col < 5; col++) {
//                jLabels[row][col] = new JLabel();
//            }
//        }

//        JFrame frame = new JFrame();
//        JTextField textField = new JTextField();
//        textField.addKeyListener(listener);
//        frame.add(textField);
//        frame.pack();
//        frame.setSize(500,500);
//        frame.setVisible(true);

        NewJPanel newJPanel = new NewJPanel();
        //newJPanel.jLabel1.addKeyListener(listener);
    }

    public static void printChar(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }
}