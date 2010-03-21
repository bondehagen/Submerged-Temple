
import java.io.*;

public class AI_Java extends AbstractRunner {

    public AI_Java() throws IOException {
        super();
    }

    /*
     * Your AI goes here
     */
    @Override
    public void Run() {
        System.out.println(GetRandomDirection());
    }

    private char GetRandomDirection() {
        int direction = (int) (4 * Math.random());
        switch (direction) {
            case 0:
                return 'N';
            case 1:
                return 'S';
            case 2:
                return 'E';
            case 3:
                return 'W';
        }
        return 'N';
    }

    public static void main(String argv[]) throws IOException {
        new AI_Java();
    }
}
