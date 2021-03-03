package visualizer.algorithms;

import visualizer.Handler;

import java.awt.*;

public class BubbleSort extends Algorithm {

    private final Handler handler;
    private int i = 0, j = 0;

    public BubbleSort(Handler handler, int[] array, int speed, int barWidth) {
        super(array, speed, barWidth);
        this.handler = handler;
    }

    @Override
    public void update() {
        for (int s = 0; s < speed; s++) {
            j++;
            if (j >= array.length - 1 - i) {
                j = 0;
                i++;
            }
            if (array[j] > array[j + 1]) swap(j, j + 1);
        }
    }

    private void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        for (int n = 0; n < array.length; n++) {
            g.fillRect(n * barWidth, handler.getScreenHeight() - array[n], barWidth - 1, array[n]);
        }
    }
}
