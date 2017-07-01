package Game;

import java.io.IOException;

import utils.DrawUtils;

public class Steel extends Element implements IsRushed,Attacked,EAttacked {
	int blood = 5;
	public Steel(int x, int y) {
		super(x, y);
	}
	
	public void draw() {
		try {
			DrawUtils.draw("res//img//steel.gif", x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[] size = null;
		try {
			size = DrawUtils.getSize("res//img//steel.gif");
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
		if(blood == 0){
			
			return true;
		}
		return false;
	}
	
	public Blast attacked() {
		Blast blast = new Blast(this, false);
		blood--;
		return blast;
	}
	
	public Blast showDistroy() {
		Blast blast = new Blast(this,true);
		return blast;
	}
	
}
