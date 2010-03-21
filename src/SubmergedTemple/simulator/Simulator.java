package SubmergedTemple.simulator;

import SubmergedTemple.Skeleton;
import SubmergedTemple.map.NodeType;
import SubmergedTemple.map.Map;
import SubmergedTemple.player.IPlayer;
import SubmergedTemple.player.AIPlayer;
import SubmergedTemple.player.HumanPlayer;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Simulator extends GameRunner {

    protected Map map;
    protected IPlayer player1;
    protected IPlayer player2;
    protected String mapName;
    private GameSettings gameSettings;
    private final String mapPath = "map/";
    private String player1Name = "Player 1";
    private String player2Name = "Player 2";

    public Simulator(Skeleton frame, GameSettings gameSettings) {
        super(frame);
        this.mapName = gameSettings.getMap();
        this.gameSettings = gameSettings;
        this.map = new Map(mapPath + mapName);
    }

    @Override
    protected void Init() {
        try {
            InitPlayer1();
            InitPlayer2();
        } catch (IOException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void InitPlayer1() throws IOException {
        if (gameSettings.getPlayer1Command() == null) {
            player1 = new HumanPlayer(NodeType.Player1);
        } else {
            player1 = new AIPlayer(NodeType.Player1, map, gameSettings.getPlayer1Command());
        }
    }

    private void InitPlayer2() throws IOException {
        if (gameSettings.getPlayer2Command() == null) {
            player2 = new HumanPlayer(NodeType.Player2);
        } else {
            player2 = new AIPlayer(NodeType.Player2, map, gameSettings.getPlayer2Command());
        }
    }

    @Override
    protected void Update(int deltaTime) {
        UpdatePlayer1();
        UpdatePlayer2();
    }

    private void UpdatePlayer2() {
        if (player2.IsAlive()) {
            player2.Update(map);
            map.MovePlayer(player2);
        }
    }

    private void UpdatePlayer1() {
        if (player1.IsAlive()) {
            player1.Update(map);
            map.MovePlayer(player1);
        }
    }

    protected String TryGetWinner() {
        if (!map.HasMoreCoins()) {
            if (IsScoreEqual()) {
                if (!player1.IsAlive())
                    return player2Name;
                else if (!player2.IsAlive())
                    return player1Name;
            } else
                return GetPlayerNameWithHighestScore(player1Name, player2Name);
        }
        if (IsBothPlayersDead()) 
            return IsScoreEqual() ? "Equal score" : GetPlayerNameWithHighestScore(player1Name, player2Name);
        
        return null;
    }

    protected String GetPlayerNameWithHighestScore(String player1Name, String player2Name) {
        return player1.GetScore() > player2.GetScore() ? player1Name : player2Name;
    }

    protected boolean IsScoreEqual() {
        return player1.GetScore() == player2.GetScore();
    }

    protected  boolean IsBothPlayersDead() {
        return !player1.IsAlive() && !player2.IsAlive();
    }

    @Override
    protected abstract void Render(Graphics2D g);
}
