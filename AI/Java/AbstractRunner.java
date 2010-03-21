
import java.io.*;

public abstract class AbstractRunner {

    protected char[][] map;
    private static BufferedReader stdin;
    protected char player;
    protected int air;
    protected int score;
    protected int width;
    protected int height;

    public AbstractRunner() throws IOException {
        Start();
    }

    // Try to read and parse data from stdin
    private boolean ReadInput() throws IOException {
        // Player 1 or player 2
        player = stdin.readLine().toCharArray()[0];
        // Number of airbubbels left
        air = Integer.parseInt(stdin.readLine());
        //Your current score
        score = Integer.parseInt(stdin.readLine());
        // Map width and height
        width = Integer.parseInt(stdin.readLine());
        height = Integer.parseInt(stdin.readLine());

        // Read map into map
        if (air > 0 && width > 0 && height > 0) {
            map = new char[height][width];
            for (int y = 0; y < height; y++) {
                String line = stdin.readLine();
                map[y] = line.toCharArray();
            }
            return IsEOS();
        }
        return false;
    }

    // Is end of stream?
    private boolean IsEOS() throws IOException {
        String tile = stdin.readLine();
        return (((char) tile.toCharArray()[0]) == ';');
    }

    private void Start() throws IOException {
        stdin = new BufferedReader(new InputStreamReader(System.in));
        while (ReadInput()) {
            Run();
            Thread.yield(); // Give the CPU a break
        }
    }

    protected abstract void Run();
}
