package Game;

import java.io.IOException;

import utils.DrawUtils;

public class Wall extends Element implements IsRushed,Attacked,EAttacked {
	int blood = 2;
	public Wall(int x, int y) {
		super(x, y);
	}
	
	public void draw() {
		try {
			DrawUtils.draw("res//img//wall.gif", x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] size = null;
		try {
			size = DrawUtils.getSize("res//img//wall.gif");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = size[0];
		this.height = size[1];
	}

	@Override
	public int inOrder() {
		// TODO 自动生成的方法存根
		return 0;
	}

	public boolean isDistroy() {
		if(blood==0){
			return true;
		}
		return false;
	}
	
	public Blast attacked() {
		Blast blast = new Blast(this,false);
		blood--;
		return blast;
	}
	
	public Blast showDistroy() {
		Blast blast = new Blast(this,true);
		return blast;
	}
	
}
