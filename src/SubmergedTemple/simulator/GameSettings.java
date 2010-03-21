package SubmergedTemple.simulator;

public class GameSettings {

    private String map;
    private String[] player1Cmd;
    private String[] player2Cmd;

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    // Command to excute
    public String[] getPlayer1Command() {
        return player1Cmd;
    }

    public void setPlayer1Command(String[] command) {
        this.player1Cmd = command;
    }

    // Command to excute
    public String[] getPlayer2Command() {
        return player2Cmd;
    }

    public void setPlayer2Command(String[] command) {
        this.player2Cmd = command;
    }
}
