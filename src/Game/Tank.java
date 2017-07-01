package Game;

import java.io.IOException;

import utils.CollsionUtils;
import utils.DrawUtils;

public class Tank extends Element implements IsRush,EAttacked {
	private Key key = Key.UP;
	private long time;
	private Key rushkey;
	private int speed = 32;
	private int seed;
	private int stop;
	
	public Tank() {
		super();
		x = Config.WIDTH/2 - 64*4;
		y = Config.HEIGHT - 64;
		blood = 10;
	}

	public Bullet shot() {
		Bullet bullet = new Bullet(this);
		return bullet;
	}


	public Key getKey() {
		return key;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void draw() {
		int[] size = null;
		try {
			size = DrawUtils.getSize("res//img//tank_u.gif");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = size[0];
		this.height = size[1];
		
		switch (key) {
		case UP:
			try {
				DrawUtils.draw("res//img//tank_u.gif", x, y);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case DOWN:
			try {
				DrawUtils.draw("res//img//tank_d.gif", x, y);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case LEFT:
			try {
				DrawUtils.draw("res//img//tank_l.gif", x, y);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case RIGHT:
			try {
				DrawUtils.draw("res//img//tank_r.gif", x, y);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

	public void move(Key key) {
		if (this.key!=key) {
			this.key = key;
			return;
		}
		if (rushkey!=null&&seed==0) {
			return;
		}
		if(rushkey!=null&&seed!=0){
			switch (key) {
			case UP:
				y-=seed;
				break;
				
			case DOWN:
				y+=seed;
				break;
				
			case LEFT:
				x-=seed;
				break;
				
			case RIGHT:
				x+=seed;
				break;

			default:
				break;
			}
			seed = 0 ;
			return;
		}
		switch (key) {
		case UP:
			y-=speed;
			break;
			
		case DOWN:
			y+=speed;
			break;
			
		case LEFT:
			x-=speed;
			break;
			
		case RIGHT:
			x+=speed;
			break;

		default:
			break;
		}
		if(x<=0){
			x=0;
		}
		if(x>=Config.WIDTH-width){
			x=Config.WIDTH-width;
		}
		if(y<=0){
			y=0;
		}
		if(y>=Config.HEIGHT-height){
			y=Config.HEIGHT-height;
		}
	}

	public boolean isrush(IsRushed element2) {
		Element element = (Element)element2;
		int x1 = x;
		int y1 = y;
		int w1 = width;
		int h1 = height;
		int x2 = element.x;
		int y2 = element.y;
		int w2 = element.width;
		int h2 = element.height;
		
		switch (key) {
		case UP:
			seed = y1 - y2 -h2;
			y1-=speed;
			break;
			
		case DOWN:
			seed = y2 - y1 -h1;
			y1+=speed;
			break;
			
		case LEFT:
			seed = x1 - x2 -w2;
			x1-=speed;
			break;
			
		case RIGHT:
			seed = x2 - x1 -h1;
			x1+=speed;
			break;

		default:
			break;
		}
		boolean rush = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
		if (rush) {
			rushkey = key;
		}else {
			rushkey = null;
		}
		return rush;
	}
	
	public int inOrder() {
		return 1;
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

	@Override
	public boolean isrush(IsRush element2) {
		Element element = (Element)element2;
		int x1 = x;
		int y1 = y;
		int w1 = width;
		int h1 = height;
		int x2 = element.x;
		int y2 = element.y;
		int w2 = element.width;
		int h2 = element.height;
		
		switch (key) {
		case UP:
			seed = y1 - y2 -h2;
			y1-=speed;
			break;
			
		case DOWN:
			seed = y2 - y1 -h1;
			y1+=speed;
			break;
			
		case LEFT:
			seed = x1 - x2 -w2;
			x1-=speed;
			break;
			
		case RIGHT:
			seed = x2 - x1 -h1;
			x1+=speed;
			break;

		default:
			break;
		}
		boolean rush = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
		if (rush) {
			rushkey = key;
		}else {
			rushkey = null;
		}
		return rush;
	}

	@Override
	public boolean stop() {
		stop++;
		if(stop%2==0){
			return true;
		}
		return false;
	}

}
