package SubmergedTemple.player;

import SubmergedTemple.map.NodeType;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HumanPlayer extends BasePlayer implements IPlayer {

    public HumanPlayer(NodeType type) {
        this.type = type;
    }

    public void Move() {
    }

    public void keyPressed(KeyEvent e) {
        if (type == NodeType.Player1) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    this.lastDirection = 'N';
                    break;
                case KeyEvent.VK_DOWN:
                    this.lastDirection = 'S';
                    break;
                case KeyEvent.VK_LEFT:
                    this.lastDirection = 'W';
                    break;
                case KeyEvent.VK_RIGHT:
                    this.lastDirection = 'E';
                    break;
            }
        }
        if (type == NodeType.Player2) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    this.lastDirection = 'N';
                    break;
                case KeyEvent.VK_S:
                    this.lastDirection = 'S';
                    break;
                case KeyEvent.VK_A:
                    this.lastDirection = 'W';
                    break;
                case KeyEvent.VK_D:
                    this.lastDirection = 'E';
                    break;
            }
        }
    }

    @Override
    public void Update() {
        int timeoutCount = 0;

        // Wait one second for keyboard input before moving on
        while (this.lastDirection == 'K') {
            try {
                if (timeoutCount == 1000) {
                    break;
                }
                timeoutCount += 10;
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(HumanPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_RIGHT:
                break;
        }
    }
}
