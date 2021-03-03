package visualizer;

public class Handler {

    public Game game;

    public Handler(Game game) {
        this.game = game;
    }

    public int getScreenWidth() {
        return game.getWidth();
    }

    public int getScreenHeight() {
        return game.getHeight();
    }

    public Display getDisplay() {
        return game.getDisplay();
    }

}
