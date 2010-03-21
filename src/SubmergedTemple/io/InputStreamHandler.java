package SubmergedTemple.io;

import java.io.*;

public class InputStreamHandler extends Thread {

    private InputStream stream;
    private StringBuffer captureBuffer;

    public InputStreamHandler(StringBuffer captureBuffer, InputStream stream) {
        this.stream = stream;
        this.captureBuffer = captureBuffer;
        start();
    }

    @Override
    public void run() {
        try {
            System.out.print("-");
            InputStreamReader inn = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(inn);
            String line;
            while ((line = br.readLine()) != null) {
                captureBuffer.append(line);
                this.stop();
            }
            br.close();
            inn.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
