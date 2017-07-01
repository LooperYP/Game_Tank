package Game;

import java.io.IOException;

import utils.CollsionUtils;
import utils.DrawUtils;

public class EnemyBullet extends Element implements EAttack {
	private Key key;
	private int tx;
	private int ty;
	private int speed = 8;
	private boolean rush;
	
	
	public EnemyBullet() {
		super();
	}
	
	public EnemyBullet(Enemy enemy) {
		this.x = enemy.getX();
		this.y = enemy.getY();
		tx = enemy.getWidth();
		ty = enemy.getHeight();
		key = enemy.getKey();
		
		int[] size = null;
		try {
			size = DrawUtils.getSize("res//img//bullet_u.gif");
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = size[0];
		height = size[1];
	}
	
	public int inOrder() {
		return 1;
	}

	public void draw() {
		switch (key) {
		case UP:
			try {
				DrawUtils.draw("res//img//bullet_u.gif", x+tx/2-width/2, y-height/2);
				y-=speed;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case DOWN:
			try {
				DrawUtils.draw("res//img//bullet_d.gif", x+tx/2-width/2, y-height/2+ty);
				y+=speed;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case LEFT:
			try {
				DrawUtils.draw("res//img//bullet_l.gif", x-width/2, y+ty/2-height/2);
				x-=speed;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case RIGHT:
			try {
				DrawUtils.draw("res//img//bullet_r.gif", x-width/2+tx, y+ty/2-height/2);
				x+=speed;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}
	public boolean isOverBound() {
		if (y<0||x<0||y>Config.HEIGHT||x>Config.WIDTH) {
			return true;
		}
		return false;
	}

	public boolean isDistroy() {
		if(rush||blood==0) {
			return true;
		}
		return false;
	}
	
	public Blast attacked() {
		Blast blast = new Blast(this,false);
		blood--;
		return blast;
	}

	public boolean isrush(EAttacked element2) {
		Element element = (Element)element2;
		int x1 = x;
		int y1 = y;
		int w1 = width;
		int h1 = height;
		int x2 = element.x;
		int y2 = element.y;
		int w2 = element.width;
		int h2 = element.height;
//		switch (key) {
//		case UP:
//			y1-=speed;
//			break;
//			
//		case DOWN:
//			y1+=speed;
//			break;
//			
//		case LEFT:
//			x1-=speed;
//			break;
//			
//		case RIGHT:
//			x1+=speed;
//			break;
//
//		default:
//			break;
//		}
		rush = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
		return rush;
	}

}
