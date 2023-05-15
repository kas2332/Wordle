import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {
    boolean isEditable = false;
    Font font = new Font("Comic Sans MS", Font.PLAIN, 12);
    public MyJLabel () {
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
    }
    public boolean getEditable () {
        return isEditable;
    }
}