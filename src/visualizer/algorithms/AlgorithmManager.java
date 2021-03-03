package visualizer.algorithms;

public class AlgorithmManager {

    private static Algorithm currentAlgorithm = null;

    public static void setCurrentAlgorithm(Algorithm algorithm) {
        currentAlgorithm = algorithm;
    }

    public static Algorithm getCurrentAlgorithm() {
        return currentAlgorithm;
    }

}
