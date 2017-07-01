package Game;

public class GameTest {
	public static void main(String[] args) {
		GameWindow game = new GameWindow(Config.TITLE, Config.WIDTH, Config.HEIGHT, Config.FPS);
		game.start();
	}
} 
