import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel implements Runnable {
    boolean isEditable = false;
    Font font = new Font("Comic Sans MS", Font.PLAIN, 12);
    Color color = new Color(Color.DARK_GREY);
    
    @Override
    public void run() {
        changeBackground();
    }

    public MyJLabel() {
        super();
        this.setFont(font);
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setText("");
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.setPreferredSize(new java.awt.Dimension(50, 50));
        this.setEditable(false);
        this.setBackground(color);
        this.setOpaque(false);
    }

    public void setEditable(boolean editable) {
        this.isEditable = editable;
        if (isEditable) {
            this.setOpaque(true);
        }
        else {
            this.setOpaque(false);
        }
    }

    public boolean getEditable() {
        return isEditable;
    }
}
