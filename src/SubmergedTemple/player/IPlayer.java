package SubmergedTemple.player;

import SubmergedTemple.map.Map;
import SubmergedTemple.map.NodeType;

public interface IPlayer {

    void Move();

    char GetLastDirection();

    int GetAirvalue();

    int GetScore();

    NodeType GetType();

    void SetLastDirection(char lastDirection);

    void EvalueteScore(NodeType nodeType);

    void Update(Map map);

    boolean IsAlive();
}
