package SubmergedTemple.map;

public class Node {

    public final int X;
    public final int Y;
    private NodeType Type;

    public Node(int x, int y, NodeType type) {
        this.X = x;
        this.Y = y;
        this.Type = type;
    }

    public Node(int x, int y, char asciiType) {
        this.X = x;
        this.Y = y;
        this.Type = ConvertFromASCIIToType(asciiType);
    }

    boolean IsBlocked() {
        if (Type.equals(NodeType.AirBubble) || Type.equals(NodeType.Coin) || Type.equals(NodeType.None)) {
            return false;
        }
        return true;
    }

    private char ConvertToASCIIFromType(NodeType type) {
        switch (type) {
            case None:
                return ' ';
            case AirBubble:
                return '.';
            case Coin:
                return 'o';
            case Block:
                return '#';
            case MoveableBlock:
                return 'M';
            case Player1:
                return '1';
            case Player2:
                return '2';
        }
        return ' ';
    }

    public char GetAsciiType() {
        return ConvertToASCIIFromType(Type);
    }

    public NodeType GetType() {
        return Type;
    }

    public void SetType(NodeType nodeType){
        Type = nodeType;
    }

    private NodeType ConvertFromASCIIToType(char type) {
        switch (type) {
            case ' ':
                return NodeType.None;
            case '.':
                return NodeType.AirBubble;
            case 'o':
                return NodeType.Coin;
            case '#':
                return NodeType.Block;
            case 'M':
                return NodeType.MoveableBlock;
            case '1':
                return NodeType.Player1;
            case '2':
                return NodeType.Player2;
        }
        return NodeType.None;
    }
}
