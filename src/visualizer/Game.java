package visualizer;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private final String title;
    private final int width;
    private final int height;

    private Display display;
    private Handler handler;

    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics2D g;

    private Visualizer visualizer;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    private void init() {
        display = new Display(title, width, height);
        handler = new Handler(this);
        visualizer = new Visualizer(handler);
    }

    @Override
    public void run() {
        init();

        double fps = 60d;
        double timePerUpdate = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long now = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate;
            lastTime = now;

            if (delta >= 1) {
                update();
                render();
                updates++;
                frames++;
                delta = 0;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                display.getFrame().setTitle("Sorting Visualizer | " + updates + " UPS, " + frames + " FPS");
                frames = 0;
                updates = 0;
                timer += 1000;
            }
        }

        stop();
    }

    private void update() {
        visualizer.update();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        visualizer.render(g);

        bs.show();
        g.dispose();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Display getDisplay() {
        return display;
    }
}
