package SubmergedTemple.player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerStatus extends JPanel {

    private int airBubbles;
    private int score;
    private final JLabel scoreField;
    private final JLabel airField;

    public PlayerStatus(int width, int height) {
        this.score = 0;
        this.airBubbles = 0;
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(2, 0));
        this.setOpaque(false);
        Font font = new Font("Verdana", Font.BOLD, 16);

        scoreField = new JLabel();
        airField = new JLabel();

        scoreField.setForeground(Color.WHITE);
        airField.setForeground(Color.WHITE);

        scoreField.setFont(font);
        airField.setFont(font);

        this.add(scoreField, BorderLayout.CENTER);
        this.add(airField, BorderLayout.CENTER);
    }

    public void setAirBubbles(int airBubbles) {
        airField.setText("Air: " + airBubbles);
        this.airBubbles = airBubbles;
    }

    public void setScore(int score) {
        scoreField.setText("Score: " + score);
        this.score = score;
    }
}
