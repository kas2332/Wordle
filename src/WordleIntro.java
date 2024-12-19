import javax.swing.*;
import java.awt.*;

public class WordleIntro extends javax.swing.JPanel {
    final static Object LOCK = new Object();
    JProgressBar jProgressBar1;
    JButton playButton;
    boolean ready = false, play = false;

    public void updateProgressBar(int iterator) {
        int percentage = (int) Math.floor(((double) iterator / 14854) * 100);
        jProgressBar1.setValue(percentage);
        if (iterator < 7427) {
            jProgressBar1.setForeground(new Color(255, (int) ((iterator / 7427.0) * 255), 0));
        } else {
            jProgressBar1.setForeground(new Color(255 - (int) (((iterator / 7427.0) - 1) * 255), 255, 0));
        }
        jProgressBar1.setBackground(new Color(255 - jProgressBar1.getForeground().getRed(), 255 - jProgressBar1.getForeground().getGreen(), 255 - jProgressBar1.getForeground().getBlue()));
        if (percentage >= 100) {
            playButton.setEnabled(true);
            ready = true;
            jProgressBar1.setBackground(new Color(238, 238, 238));
        }
        while (ready) {
            for (int i = 255; i >= 64; i--) {
                if (!ready) {
                    break;
                }
                jProgressBar1.setForeground(new Color(0, 255, 0, i));
                synchronized (LOCK) {
                    try {
                        LOCK.wait(5);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
            for (int i = 64; i < 255; i++) {
                if (!ready) {
                    break;
                }
                jProgressBar1.setForeground(new Color(0, 255, 0, i));
                synchronized (LOCK) {
                    try {
                        LOCK.wait(5);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
    }

    public void wordleIntroGUIMaker() {
        //<editor-fold desc="Label and Panel Declaration">
        Font font = new Font("Comic Sans MS", Font.PLAIN, 13);
        Font fontTitle = new Font("Comic Sans MS", Font.PLAIN, 25);

        JFrame frame = new JFrame("Wordle");
        frame.setIconImage(new ImageIcon("src/WordleLogo.png").getImage());

        JPanel mainPanel = new JPanel();
        JPanel rulesPanel = new JPanel();

        JLabel titleLabel = new JLabel();
        titleLabel.setFont(fontTitle);
        JLabel sixGuessesLabel = new JLabel();
        sixGuessesLabel.setFont(font);
        JLabel validWordsLabel = new JLabel();
        validWordsLabel.setFont(font);
        JLabel greenLettersLabel = new JLabel();
        greenLettersLabel.setFont(font);
        JLabel YellowLettersLabel = new JLabel();
        YellowLettersLabel.setFont(font);
        JLabel DarkGreyLettersLabel = new JLabel();
        DarkGreyLettersLabel.setFont(font);

        jProgressBar1 = new JProgressBar();

        playButton = new JButton();
        playButton.setFont(font);
        playButton.setEnabled(false);
        playButton.addActionListener(_ -> {
            ready = false;
            play = true;
            synchronized (Runner.LOCK) {
                Runner.LOCK.notifyAll();
            }
            frame.dispose();
        });
        //</editor-fold>

        //<editor-fold desc="Setting Text">
        titleLabel.setText("Wordle");

        rulesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Instructions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", Font.PLAIN, 12))); // NOI18N

        sixGuessesLabel.setText("<html><body>You have 6 chances to guess the 5 letter Wordle</body></html>");

        validWordsLabel.setText("<html><body>You must only guess valid 5 letter words</body></html>");

        greenLettersLabel.setText("<html><body>Green boxes means that the letter is in the<br>Wordle word at the correct position</body></html>");

        YellowLettersLabel.setText("<html><body>Yellow boxes means that the letter is in the<br>Wordle word, but in an incorrect placement</body></html>");

        DarkGreyLettersLabel.setText("<html><body>Dark Grey boxes means that the letter is<br>not in the Wordle word</body></html>");

        playButton.setText("Play");
        //</editor-fold>

        //<editor-fold desc="Layout">
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(rulesPanel);
        rulesPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sixGuessesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(validWordsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(greenLettersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(YellowLettersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(DarkGreyLettersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(sixGuessesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(validWordsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(greenLettersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(YellowLettersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DarkGreyLettersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(titleLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(rulesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(playButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel)
                                .addGap(18, 18, 18)
                                .addComponent(rulesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(playButton)
                                .addGap(25, 25, 25))
        );

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        //</editor-fold>

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
