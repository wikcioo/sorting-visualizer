package visualizer.algorithms;

import java.awt.*;

public abstract class Algorithm {

    protected int[] array;
    protected int speed;
    protected int barWidth;

    public Algorithm(int[] array, int speed, int barWidth) {
        this.array = array;
        this.speed = speed;
        this.barWidth = barWidth;
    }

    public abstract void update();

    public abstract void render(Graphics2D g);

}
