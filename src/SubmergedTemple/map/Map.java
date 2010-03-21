package SubmergedTemple.map;

import SubmergedTemple.player.IPlayer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map {

    public int Height;
    public int Width;
    public Node[][] nodes;

    public Map(String mapName) {
        String map = ReadFile(new File(mapName));
        if (map != null && !map.isEmpty()) {
            CreateNodesFromMap(map);
        }
    }

    public boolean HasMoreCoins() {
        return (FindNode(NodeType.Coin) != null);
    }


    private void CreateNodesFromMap(String map) {
        String[] asciiMap = map.split(System.getProperty("line.separator"));

        Width = Integer.parseInt(asciiMap[0]);
        Height = Integer.parseInt(asciiMap[1]);

        nodes = new Node[Width][Height];

        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                nodes[x][y] = new Node(x, y, asciiMap[y + 2].toCharArray()[x]);
            }
        }
    }

    public String GetASCIIMap() {
        StringBuilder asciiMap = new StringBuilder();
        for (int y = 0; y < Height; y++) {
            for (int x = 0; x < Width; x++) {
                asciiMap.append(nodes[x][y].GetAsciiType());
            }
            asciiMap.append(System.getProperty("line.separator"));
        }
        return asciiMap.toString();
    }

    private Node GetNewNode(Node node, char direction) {
        int y = node.Y;
        int x = node.X;

        switch (direction) {
            case 'N':
                if (y - 1 >= 0) {
                    return nodes[x][y - 1];
                }
                break;
            case 'S':
                if (y + 1 < Height) {
                    return nodes[x][y + 1];
                }
                break;
            case 'E':
                if (x + 1 < Width) {
                    return nodes[x + 1][y];
                }
                break;
            case 'W':
                if (x - 1 >= 0) {
                    return nodes[x - 1][y];
                }
                break;
        }
        return null;
    }

    private String ReadFile(File file) {
        StringBuilder contents = new StringBuilder();
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return contents.toString();
    }

    public Node[][] GetNodes() {
        return nodes;
    }

    public boolean IsDirectionPossible(char direction) {
        char[] possibles = new char[]{'N', 'S', 'E', 'W'};
        for (char c : possibles)
            if (c == direction)
                return true;
        
        return false;
    }

    public void MovePlayer(IPlayer player) {
        char direction = player.GetLastDirection();
        Node playerNode = FindNode(player.GetType());
        try {
            if (IsDirectionPossible(direction)) {
                NodeType result = move(playerNode, direction);
                player.EvalueteScore(result);
            } else {
                System.out.printf("Direction '%c' is not valid\n", direction);
            }
        } catch (Exception e) {
            System.out.println(playerNode.X + " " + playerNode.Y + " " + direction);
            e.printStackTrace();
        }
    }

    public boolean TryToMove(Node moveable, char direction) {
        if (moveable != null && moveable.GetType().equals(NodeType.MoveableBlock)) {
            Node moveNodeTo = GetNewNode(moveable, direction);
            if (!moveNodeTo.IsBlocked()) {
                NodeType tempType = moveable.GetType();
                moveable.SetType(moveNodeTo.GetType());
                moveNodeTo.SetType(tempType);
                return true;
            }
        }
        return false;
    }

    public Node FindNode(NodeType nodeType) {
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                if (nodes[x][y].GetType().equals(nodeType)) {
                    return nodes[x][y];
                }
            }
        }
        return null;
    }

    private NodeType move(Node player, char direction) {
        NodeType result = NodeType.None;
        try {
            Node newNodePos = GetNewNode(player, direction);
            if (newNodePos != null) {
                if (newNodePos.IsBlocked()) {
                    if (TryToMove(newNodePos, direction)) {
                        result = newNodePos.GetType();
                        newNodePos.SetType(player.GetType());
                        player.SetType(NodeType.None);
                    }
                    System.out.println("Blocked" + direction);
                } else {
                    result = newNodePos.GetType();
                    newNodePos.SetType(player.GetType());
                    player.SetType(NodeType.None);
                }
            } else {
                System.out.println("Blocked: " + direction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
