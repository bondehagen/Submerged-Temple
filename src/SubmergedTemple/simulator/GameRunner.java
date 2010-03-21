package SubmergedTemple.simulator;

import SubmergedTemple.Skeleton;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class GameRunner extends JPanel implements Runnable {

    public final float NANO = 1000000000.0f;
    protected int WIDTH = 0;
    protected int HEIGHT = 0;
    protected JFrame frame;
    protected JLabel statusbar;
    protected Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Thread animator;
    private long currentFPS;
    private long desiredFPS = 10;
    private long desiredDeltaLoop = (1000 * 1000 * 1000) / desiredFPS;
    private boolean running = true;

    protected boolean pause = false;

    public GameRunner(Skeleton frame) {
        statusbar = frame.getStatusBar();
        canvas = new Canvas();
        setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.setOpaque(false);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        animator = new Thread(this);

        Init();

        final Dimension dimension = new Dimension(WIDTH + 200, HEIGHT+24);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        canvas.setIgnoreRepaint(true);
        canvas.requestFocus();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        animator.start();
    }

    public void run() {
        long beginLoopTime;
        long endLoopTime;
        long currentUpdateTime = System.nanoTime();
        long lastUpdateTime;
        long totalTime = 0;
        long deltaLoop = 0;
        long frameCounter = 0;

        while (running) {
            beginLoopTime = System.nanoTime();

            Render();

            lastUpdateTime = currentUpdateTime;
            currentUpdateTime = System.nanoTime();

            Update((int) ((currentUpdateTime - lastUpdateTime) / (1000 * 1000)));

            endLoopTime = System.nanoTime();
            deltaLoop = endLoopTime - beginLoopTime;

            // Calculate FPS
            totalTime += currentUpdateTime - lastUpdateTime;
            if (totalTime > NANO) {
                totalTime -= NANO;
                currentFPS = frameCounter;
                frameCounter = 0;
            }
            ++frameCounter;

            // Set FPS in statusbar
            statusbar.setText("fps: " + currentFPS);

            while(pause){
                Thread.yield();
            }

            if (deltaLoop > desiredDeltaLoop) {
                //Do nothing. We are already late.
            } else {
                try {
                    Thread.sleep((desiredDeltaLoop - deltaLoop) / (1000 * 1000));
                } catch (InterruptedException e) {
                    //Do nothing
                }
            }
        }
    }

    private void Render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();

        g.fillRect( 0, 0, WIDTH, HEIGHT);
        g.clearRect(0, 0, WIDTH, HEIGHT);

        Render(g);
        g.dispose();

        // Blit
        if (!bufferStrategy.contentsLost()) {
            bufferStrategy.show();
        }
    }

    protected abstract void Init();

    protected abstract void Update(int deltaTime);

    public void actionPerformed(ActionEvent e) {
        frame.repaint();
    }

    protected abstract void Render(Graphics2D g);
}
