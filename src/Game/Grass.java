package Game;

import java.io.IOException;

import utils.DrawUtils;

public class Grass extends Element {
	private int width;
	private int height;
	
	public Grass(int x, int y) {
		super(x, y);
	}
	
	public void draw() {
		try {
			DrawUtils.draw("res//img//grass.gif", x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int inOrder() {
		// TODO 自动生成的方法存根
		return 2;
	}

	public boolean isDistroy() {
		return false;
	}
	
}
