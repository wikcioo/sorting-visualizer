package visualizer;

import visualizer.algorithms.AlgorithmManager;
import visualizer.algorithms.BubbleSort;
import visualizer.algorithms.QuickSort;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Visualizer {

    private final Handler handler;

    // Algorithms
    private BubbleSort bubbleSort;
    private QuickSort quickSort;

    // Buttons
    private JButton bubbleSortButton;
    private JButton quickSortButton;

    private int[] array;
    private int speed;
    private int barWidth;

    public Visualizer(Handler handler) {
        this.handler = handler;
        barWidth = 10;
        array = new int[handler.getScreenWidth() / barWidth];
        speed = 20;
        initializeArray();
//        initializeButtons();
        initializeAlgorithms();

        AlgorithmManager.setCurrentAlgorithm(bubbleSort);
    }

    private void initializeArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, handler.getScreenHeight() - 100);
        }
    }

    private void initializeButtons() {
        bubbleSortButton = new JButton("Bubble Sort");
        quickSortButton = new JButton("Quick Sort");

        bubbleSortButton.setBounds(100, 100, 100, 50);
        quickSortButton.setBounds(handler.getScreenWidth() - 40, 20, 40, 20);

        bubbleSortButton.addActionListener(actionEvent -> {
            AlgorithmManager.setCurrentAlgorithm(bubbleSort);
        });

        quickSortButton.addActionListener(actionEvent -> {
            AlgorithmManager.setCurrentAlgorithm(quickSort);
        });

        handler.getDisplay().getFrame().add(bubbleSortButton);
        handler.getDisplay().getFrame().add(quickSortButton);
    }

    private void initializeAlgorithms() {
        bubbleSort = new BubbleSort(handler, array, speed, barWidth);
        quickSort = new QuickSort(array, speed, handler.getScreenWidth());
    }

    public void update() {
        if (AlgorithmManager.getCurrentAlgorithm() != null) {
            AlgorithmManager.getCurrentAlgorithm().update();
        }
    }

    public void render(Graphics2D g) {
        if (AlgorithmManager.getCurrentAlgorithm() != null) {
            AlgorithmManager.getCurrentAlgorithm().render(g);
        }
    }

    // Getters and Setters
    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
