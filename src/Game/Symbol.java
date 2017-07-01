package Game;

import java.io.IOException;

import utils.DrawUtils;

public class Symbol extends Element implements IsRushed,Attacked,EAttacked {
	int blood = 2;
	public Symbol(int x, int y) {
		super(x, y);
	}
	
	public Symbol() {
		// TODO 自动生成的构造函数存根
	}

	public void draw() {
		try {
			DrawUtils.draw("res//img//symbol.gif", x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] size = null;
		try {
			size = DrawUtils.getSize("res//img//symbol.gif");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = size[0];
		this.height = size[1];
	}
	
	@Override
	public int inOrder() {
		// TODO 自动生成的方法存根
		return 3;
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
