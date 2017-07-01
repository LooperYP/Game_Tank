package Game;

import java.io.IOException;

import utils.DrawUtils;

public class Water extends Element implements IsRushed{
	public Water(int x, int y) {
		super(x, y);
	}
	
	public void draw() {
		try {
			DrawUtils.draw("res//img//water.gif", x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] size = null;
		try {
			size = DrawUtils.getSize("res//img//water.gif");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = size[0];
		this.height = size[1];
	}

	public int inOrder() {
		return 0;
	}

	public boolean isDistroy() {
		
		return false;
	}
	
}
