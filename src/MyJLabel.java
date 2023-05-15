import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel implements Runnable {
    boolean isEditable = false;
    Font font = new Font("Comic Sans MS", Font.PLAIN, 12);

    public MyJLabel() {
        super();
        this.setFont(font);
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setText("");
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.setPreferredSize(new java.awt.Dimension(50, 50));
        this.setEditable(false);
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
        changeBackground();
    }

    public boolean getEditable() {
        return isEditable;
    }

    public void changeBackground () {
        if (this.getEditable()) {

        }
    }

    Thread t = new Thread(new MyJLabel());
    t.start();
    @Override
    public void run() {

    }
}