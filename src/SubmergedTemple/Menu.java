package SubmergedTemple;

import SubmergedTemple.simulator.GameSettings;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class Menu extends JPanel implements ActionListener {

    private JComboBox comboMap;
    private JComboBox comboPlayer1,  comboPlayer2;
    private JLabel lblChooseLanguage,  lblChooseLanguage2,  lblHeader,  lblMap,  lblPlayer1,  lblPlayer2;
    private JTextField txtPlayer1,  txtPlayer2;
    private JButton btnNext;
    private String[] maps;
    private String[] languages = {"", "Human", "Java", "PHP", "C++", "Python", "C#"};
    private GameSettings gameSettings;
    private boolean isFinnished = false;

    public Menu() {

        // Weeee.... :D
        gameSettings = new GameSettings();
        maps = GetMaps();

        lblHeader = new JLabel();
        lblHeader.setForeground(Color.white);
        lblMap = new JLabel();
        lblMap.setForeground(Color.white);
        comboMap = new JComboBox();
        lblPlayer1 = new JLabel();
        lblPlayer1.setForeground(Color.white);
        lblChooseLanguage = new JLabel();
        lblChooseLanguage.setForeground(Color.white);
        comboPlayer1 = new JComboBox();
        txtPlayer1 = new JTextField();
        lblPlayer2 = new JLabel();
        lblPlayer2.setForeground(Color.white);
        lblChooseLanguage2 = new JLabel();
        lblChooseLanguage2.setForeground(Color.white);
        comboPlayer2 = new JComboBox();
        txtPlayer2 = new JTextField();
        btnNext = new JButton();

        lblPlayer1.setText("PLAYER 1");
        lblPlayer2.setText("PLAYER 2");
        lblChooseLanguage.setText("Choose language");
        lblChooseLanguage2.setText("Choose language");
        lblHeader.setText("Game settings");
        lblMap.setText("Choose map");
        txtPlayer1.setText("");
        txtPlayer2.setText("");
        btnNext.setText("Play");
        btnNext.setVisible(false);
        btnNext.addActionListener(this);
        comboMap.setModel(new DefaultComboBoxModel(maps));
        comboMap.addActionListener(this);
        comboPlayer1.setModel(new DefaultComboBoxModel(languages));
        comboPlayer1.addActionListener(this);
        comboPlayer2.setModel(new DefaultComboBoxModel(languages));
        comboPlayer2.addActionListener(this);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        // I'm sorry to let anyone see the following code, but java gui just suck.. so I used a tool
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(
                GroupLayout.Alignment.LEADING).addComponent(lblHeader).addComponent(
                lblPlayer1).addComponent(
                lblPlayer2).addGroup(
                layout.createSequentialGroup().addGroup(
                layout.createParallelGroup(
                GroupLayout.Alignment.LEADING).addComponent(
                lblMap).addComponent(
                lblChooseLanguage).addComponent(
                lblChooseLanguage2)).addGap(
                76,
                76,
                76).addGroup(
                layout.createParallelGroup(
                GroupLayout.Alignment.LEADING,
                false).addComponent(
                comboPlayer2,
                0,
                GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addComponent(
                comboPlayer1,
                0,
                GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addComponent(
                comboMap,
                0,
                GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addComponent(
                txtPlayer2).addComponent(
                txtPlayer1,
                GroupLayout.DEFAULT_SIZE,
                250,
                Short.MAX_VALUE).addComponent(
                btnNext)))).addContainerGap(88, Short.MAX_VALUE)));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(
                GroupLayout.Alignment.TRAILING).addGroup(
                layout.createSequentialGroup().addComponent(
                lblHeader).addPreferredGap(
                LayoutStyle.ComponentPlacement.UNRELATED).addComponent(
                lblMap)).addComponent(
                comboMap,
                GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(lblPlayer1).addPreferredGap(
                LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(
                GroupLayout.Alignment.BASELINE).addComponent(
                lblChooseLanguage).addComponent(
                comboPlayer1,
                GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                LayoutStyle.ComponentPlacement.RELATED).addComponent(txtPlayer1,
                GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(lblPlayer2).addPreferredGap(
                LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(
                GroupLayout.Alignment.BASELINE).addComponent(
                lblChooseLanguage2).addComponent(
                comboPlayer2,
                GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                LayoutStyle.ComponentPlacement.RELATED).addComponent(txtPlayer2,
                GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(
                btnNext).addContainerGap(33,
                Short.MAX_VALUE)));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboMap) {
            gameSettings.setMap((String) comboMap.getSelectedItem());
        } else if (e.getSource() == comboPlayer1) {
            final String selected = (String) comboPlayer1.getSelectedItem();
            if (!selected.equals("Human")) {
                txtPlayer1.setText(GetCommand(selected));
                if (comboPlayer1.getSelectedIndex() > 0 && comboPlayer2.getSelectedIndex() > 0) {
                    btnNext.setVisible(true);
                } else {
                    btnNext.setVisible(false);
                }
            }else {
                txtPlayer1.setText("Use Arrow keys");
                btnNext.setVisible(true);
            }
        } else if (e.getSource() == comboPlayer2) {
            final String selected = (String) comboPlayer2.getSelectedItem();
            if (!selected.equals("Human")) {
                txtPlayer2.setText(GetCommand(selected));
                if (comboPlayer1.getSelectedIndex() > 0 && comboPlayer2.getSelectedIndex() > 0) {
                    btnNext.setVisible(true);
                } else {
                    btnNext.setVisible(false);
                }
            } else {
                txtPlayer2.setText("Use (WASD) keys");
                btnNext.setVisible(true);
            }
        } else if (e.getSource() == btnNext) {
            gameSettings.setMap((String) comboMap.getSelectedItem());
            String[] command = txtPlayer1.getText().split(" ");
            if (command != null && !command[0].equals("") && (command.length == 1 || command.length > 1 && !command[1].equals("Arrow"))) {
                gameSettings.setPlayer1Command(command);
            }
            command = txtPlayer2.getText().split(" ");
            if (command != null && !command[0].equals("") && (command.length == 1 || command.length > 1 && !command[1].equals("(WASD)"))) {
                gameSettings.setPlayer2Command(command);
            }
            System.out.println("next");
            isFinnished = true;
        }
        repaint();
    }

    private String GetCommand(String cmd) {
        if (cmd.equals("Java")) {
            return "java -cp AI/Java AI_Java";
        }
        if (cmd.equals("PHP")) {
            return "bin/PHP/php.exe -q AI/PHP/AI_PHP.php";
        }
        if (cmd.equals("C++")) {
            return "AI/Cpp/Debug/AI_Cpp.exe";
        }
        if (cmd.equals("Python")) {
            return "python AI/Python/somefile.py";
        }
        if (cmd.equals("C#")) {
            return "AI/Csharp/AI_Csharp.exe";
        }
        return cmd;
    }

    public boolean IsReady() {
        return isFinnished;
    }

    public GameSettings GetSettings() {
        return gameSettings;
    }

    private String[] GetMaps() {
        File dir = new File("./map");

        FilenameFilter filter = new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return !name.startsWith(".");
            }
        };
        String[] children = dir.list(filter);

        return children;
    }
}
