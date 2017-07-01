package Game;

import java.io.IOException;

import utils.DrawUtils;

public class Blast extends Element {
	int i;
	int blood = 1;
	boolean dis;
	String[] arr = { "res//img//blast_1.gif", "res//img//blast_2.gif"};

	public Blast(Element element, boolean b) {
		int[] size = null;
		try {
			size = DrawUtils.getSize("res//img//blast_1.gif");
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = size[0];
		height = size[1];
		int x1 = element.x;
		int y1 = element.y;
		int w1 = element.width;
		int h1 = element.height;
		blood = element.blood;
		x = x1 - (width/2-w1/2);
		y = y1 - (height/2-h1/2);
		if(b){
			arr = new String[]{ "res//img//blast_1.gif", "res//img//blast_2.gif", "res//img//blast_3.gif", "res//img//blast_4.gif",
					"res//img//blast_5.gif", "res//img//blast_6.gif", "res//img//blast_7.gif", "res//img//blast_8.gif"};
			System.out.println("666");
		}
	}

	public void draw() {
		if (i>=arr.length) {
			dis = true;
			i = 0;
		}
		String string = arr[i];
		i++;
		try {
			DrawUtils.draw(string, x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int inOrder() {
		return 1;
	}

	public boolean isDistroy() {
		if (dis) {
			return true;
		}
		return false;
	}
	
}
