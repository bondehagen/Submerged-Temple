package SubmergedTemple.player;

import SubmergedTemple.map.Map;
import SubmergedTemple.map.NodeType;

public abstract class BasePlayer {

    protected Map map;
    protected char lastDirection;
    protected NodeType type;
    protected int score;
    protected int airBubbles;
    private int airMax;
    private int coinValue;

    public BasePlayer() {
        lastDirection = 'K';
        score = 0;
        airMax = 15;
        airBubbles = airMax;
        coinValue = 1;
    }

    public int GetAirvalue() {
        return airBubbles;
    }

    public int GetScore() {
        return score;
    }

    public boolean IsAlive() {
        return (airBubbles > 0);
    }


    public char GetLastDirection() {
        return lastDirection;
    }

    public void SetLastDirection(char lastDirection) {
        this.lastDirection = lastDirection;
    }

    public void Update(Map map) {
        this.map = map;
        DecreaseAir();
        SetLastDirection('K'); //Invalid move
        this.Update();
    }

    public NodeType GetType() {
        return this.type;
    }

    public void EvalueteScore(NodeType nodeType) {
        if(nodeType.equals(NodeType.AirBubble)){
            IncreaseAir();
        } else if(nodeType.equals(NodeType.Coin)){
            IncreaseScore();
        }
    }

    protected abstract void Update();

    public void IncreaseAir() {
        airBubbles = airMax;
    }

    public void IncreaseScore() {
        score += coinValue;
    }

    public void DecreaseAir() {
        if(airBubbles > 0)
            airBubbles--;
    }
}
