package SubmergedTemple.simulator;

import SubmergedTemple.Skeleton;
import SubmergedTemple.map.NodeType;
import SubmergedTemple.map.Node;
import SubmergedTemple.player.PlayerStatus;
import SubmergedTemple.player.HumanPlayer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SimulatorGui extends Simulator {

    private Resources resources;
    private int tileSize;
    private PlayerStatus playerStatus1;
    private PlayerStatus playerStatus2;
    private final int sidePanelWidth;

    public SimulatorGui(Skeleton frame, GameSettings gameSettings){
        super(frame, gameSettings);
        
        frame.addKeyListener(new TAdapter());
        canvas.addKeyListener(new TAdapter());

        tileSize = 15;
        sidePanelWidth = 100;

        AddTitle();
        AddPlayerStatus();
    }

    private void AddPlayerStatus() {
        playerStatus1 = new PlayerStatus(sidePanelWidth, HEIGHT);
        playerStatus2 = new PlayerStatus(sidePanelWidth, HEIGHT);
        this.add(playerStatus1, BorderLayout.LINE_START);
        this.add(playerStatus2, BorderLayout.LINE_END);
    }

    private void AddTitle() {
        Font font = new Font("Verdana", Font.BOLD, 20);
        JLabel title = new JLabel("Map: " + mapName, JLabel.CENTER);
        title.setFont(font);
        title.setForeground(Color.WHITE);
        this.add(title, BorderLayout.PAGE_START);
    }

    @Override
    protected void Init() {
        resources = new Resources();
        WIDTH = map.Width * tileSize;
        HEIGHT = map.Height * tileSize;
        super.Init();
    }

    @Override
    protected void Update(int deltaTime) {
        playerStatus1.setScore(player1.GetScore());
        playerStatus1.setAirBubbles(player1.GetAirvalue());

        playerStatus2.setScore(player2.GetScore());
        playerStatus2.setAirBubbles(player2.GetAirvalue());

        super.Update(deltaTime);
    }

    @Override
    protected void Render(Graphics2D g) {
        RenderBackground(g);
        RenderMap(g);
        RenderPlayer1(g);
        RenderPlayer2(g);

        String winner = TryGetWinner();
        if(winner != null){
            RenderScore(g, winner);
            pause = true;
        }
    }

    private void RenderBackground(Graphics2D g) {
        final Image bgImage = resources.bgImage;
        g.drawImage(bgImage, -canvas.getX(), -canvas.getY(), bgImage.getWidth(this), bgImage.getHeight(this), this);
    }

    private void RenderScore(Graphics2D g, String playerName) {
        Dimension theSize = canvas.getSize();
        final int x = theSize.width / 3;
        final int y = theSize.height / 2;

        FontRenderContext frc = g.getFontRenderContext();
        Font f = new Font("Helvetica", Font.BOLD, 24);
        Font f2 = new Font("Helvetica", Font.BOLD, 16);
        String s = playerName + " win!";
        String s2 =  "Press [ESC] to exit";
        TextLayout tl = new TextLayout(s, f, frc);
        TextLayout tl2 = new TextLayout(s2, f2, frc);

        g.setColor(Color.black);
        g.fillRect(x - 15, y - 25, 180, 70);
        g.setColor(Color.WHITE);

        tl.draw(g, x, y);
        tl2.draw(g , x + 0, y + 30);
    }

    private void RenderPlayer1(Graphics2D g) {
        Node n1 = map.FindNode(NodeType.Player1);
        Image image = (player1.IsAlive()) ? resources.player1 : resources.player1_dead;
        g.drawImage(image, n1.X * tileSize, n1.Y * tileSize, tileSize, tileSize, frame);
    }

    private void RenderPlayer2(Graphics2D g) {
        Node n2 = map.FindNode(NodeType.Player2);
        Image image = (player2.IsAlive()) ? resources.player2 : resources.player2_dead;
        g.drawImage(image, n2.X * tileSize, n2.Y * tileSize, tileSize, tileSize, frame);
    }

    private void RenderMap(Graphics2D g) {
        Node[][] Nodes = map.GetNodes();
        for (int y = 0; y < map.Height * tileSize; y = y + tileSize) {
            for (int x = 0; x < map.Width * tileSize; x = x + tileSize) {
                switch (Nodes[x / tileSize][y / tileSize].GetType()) {
                    case AirBubble:
                        g.drawImage(resources.airBubble, x, y, tileSize, tileSize, frame);
                        break;
                    case Coin:
                        g.drawImage(resources.coin, x, y, tileSize, tileSize, frame);
                        break;
                    case Block:
                        g.drawImage(resources.block, x, y, tileSize, tileSize, frame);
                        break;
                    case MoveableBlock:
                        g.drawImage(resources.moveableBlock, x, y, tileSize, tileSize, frame);
                        break;
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (player1 instanceof HumanPlayer) {
                HumanPlayer player = (HumanPlayer) player1;
                player.keyReleased(e);
            }
            if (player2 instanceof HumanPlayer) {
                HumanPlayer player = (HumanPlayer) player2;
                player.keyReleased(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }

            if (player1 instanceof HumanPlayer) {
                HumanPlayer player = (HumanPlayer) player1;
                player.keyPressed(e);
            }
            if (player2 instanceof HumanPlayer) {
                HumanPlayer player = (HumanPlayer) player2;
                player.keyPressed(e);
            }
        }
    }

    private static class Resources {
        public final Image coin = new ImageIcon(Skeleton.class.getResource("gfx/coin.png")).getImage();
        public final Image player1 = new ImageIcon(Skeleton.class.getResource("gfx/p1.png")).getImage();
        public final Image player2 = new ImageIcon(Skeleton.class.getResource("gfx/p2.png")).getImage();
        public final Image player1_dead = new ImageIcon(Skeleton.class.getResource("gfx/p1_dead.png")).getImage();
        public final Image player2_dead = new ImageIcon(Skeleton.class.getResource("gfx/p2_dead.png")).getImage();
        public final Image block = new ImageIcon(Skeleton.class.getResource("gfx/solid.png")).getImage();
        public final Image airBubble = new ImageIcon(Skeleton.class.getResource("gfx/air.png")).getImage();
        public final Image moveableBlock = new ImageIcon(Skeleton.class.getResource("gfx/moveable.png")).getImage();
        public final Image bgImage = new ImageIcon(Skeleton.class.getResource("gfx/bg.png")).getImage();
    }
}
