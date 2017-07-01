package Game;

import java.io.IOException;

import utils.DrawUtils;

public class GameOver extends Element {

	public static void gamevoer() {
		try {
			DrawUtils.draw("res//img//symbol.gif", 0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw() {
		try {
			DrawUtils.draw("res//img//gameover.png", 0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int inOrder() {
		// TODO 自动生成的方法存根
		return 4;
	}

	@Override
	public boolean isDistroy() {
		// TODO 自动生成的方法存根
		return false;
	}
	
}
