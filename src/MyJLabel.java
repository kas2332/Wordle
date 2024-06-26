import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {
    boolean isEditable = false, isRight = false;
    final Font font = new Font("Comic Sans MS", Font.BOLD, 12);
    final Color base = Color.LIGHT_GRAY, highlighted = Color.CYAN, right = Color.GREEN, wrong = Color.YELLOW, none = Color.GRAY;

    public MyJLabel() {
        super();
        this.setFont(font);
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setText("");
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.setPreferredSize(new java.awt.Dimension(50, 50));
        this.setEditable(false);
        this.setBackground(base);
        this.setOpaque(true);
    }

    public boolean getEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
        if (isEditable) {
            this.setBackground(highlighted);
        } else {
            this.setBackground(base);
        }
    }

    public void setRight() {
        this.setBackground(right);
        this.isRight = true;
    }

    public void setWrong() {
        this.setBackground(wrong);
    }

    public void setNone() {
        this.setBackground(none);
    }
}
