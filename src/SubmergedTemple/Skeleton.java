package SubmergedTemple;

import SubmergedTemple.simulator.GameSettings;
import SubmergedTemple.simulator.SimulatorGui;
import SubmergedTemple.simulator.Simulator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Skeleton extends JFrame {

    private JLabel statusbar;
    private static final GridBagConstraints gbc;
    
    public Skeleton() {
        setTitle("Submerged Temple");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(695, 539);

        setLocationRelativeTo(null);
        setResizable(false);

        statusbar = new JLabel("fps: 0");
        add(statusbar, BorderLayout.SOUTH);

        final ImageIcon imageIcon = new ImageIcon(Skeleton.class.getResource("gfx/bg.png"));
        setVisible(true);
        GameSettings gameSettings = RunMenu(imageIcon);

        RunGame(imageIcon, gameSettings);
    }

    

    public static JPanel wrapInBackgroundImage(JComponent component,
            Icon backgroundIcon,
            int verticalAlignment,
            int horizontalAlignment) {
        component.setOpaque(false);
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.add(component, gbc);
        JLabel backgroundImage = new JLabel(backgroundIcon);
        backgroundImage.setPreferredSize(new Dimension(1, 1));
        backgroundImage.setMinimumSize(new Dimension(1, 1));
        backgroundImage.setVerticalAlignment(verticalAlignment);
        backgroundImage.setHorizontalAlignment(horizontalAlignment);
        backgroundPanel.add(backgroundImage, gbc);
        return backgroundPanel;
    }


    static {
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
    }

    public JLabel getStatusBar() {
        return statusbar;
    }

    private GameSettings RunMenu(final ImageIcon imageIcon) {
        final Menu menu = new Menu();
        final JPanel wrap = wrapInBackgroundImage(menu, imageIcon, JLabel.TOP, JLabel.LEADING);
        setContentPane(wrap);
        setVisible(true);
        try {
            while (!menu.IsReady()) {
                Thread.sleep(50);
                repaint();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Skeleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        menu.setVisible(false);
        getContentPane().remove(wrap);
        return menu.GetSettings();
    }

    private void RunGame(final ImageIcon imageIcon, GameSettings gameSettings) {
        final Simulator simulator = new SimulatorGui(this, gameSettings);
        setContentPane(wrapInBackgroundImage(simulator, imageIcon, JLabel.TOP, JLabel.LEADING));
    }
}
