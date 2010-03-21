package SubmergedTemple.player;

import SubmergedTemple.map.Map;
import SubmergedTemple.map.NodeType;
import SubmergedTemple.io.InputStreamHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class AIPlayer extends BasePlayer implements IPlayer {

    private Process process;
    private OutputStream stdin = null;
    private InputStream stderr = null;
    private InputStream stdout = null;

    public AIPlayer(NodeType type, Map map, String[] command) throws IOException {
        this.map = map;
        this.type = type;

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        System.out.println("Execute " + type);
        process = processBuilder.start();

        // launch and grab stdin/stdout and stderr
        stdin = process.getOutputStream();
        stderr = process.getErrorStream();
        stdout = process.getInputStream();
    }

    public void Move() {
    }

    private String CreateOutput() {
        return String.format("%d\n%d\n%d\n%d\n%d\n%s;",
                (type.ordinal() + 1),
                GetAirvalue(),
                GetScore(),
                map.Width,
                map.Height,
                map.GetASCIIMap());
    }

    private void Move(char direction) {
        switch (direction) {
            case 'N':
                lastDirection = direction;
                break;
            case 'S':
                lastDirection = direction;
                break;
            case 'W':
                lastDirection = direction;
                break;
            case 'E':
                lastDirection = direction;
                break;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        stdin.close();
        stdout.close();
        stderr.close();
        process.destroy();
        try {
            int waitFor = process.waitFor();
        } catch (InterruptedException ex) {}
        super.finalize();
    }

    @Override
    public void Update() {
        String output = CreateOutput();

        PrintWriter ut = new PrintWriter(stdin, true);
        ut.println(output);

        StringBuffer sb = new StringBuffer();
        new InputStreamHandler(sb, stdout);

        //Only execute for one second
        long start = System.currentTimeMillis();
        while ((sb.length() <= 0)) {
            if ((System.currentTimeMillis() - start) > 1000) {
                break;
            }
        }

        if (sb.length() >= 1) {
            Move(sb.charAt(0));
            return;
        }
    }
}
