package visualizer;

public class Launcher {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "True");
        Game game = new Game("Sorting Visualizer", 1280, 720);
        game.start();
    }
}
