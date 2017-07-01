package Game;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;

import com.itheima.game.GameEngine;

import utils.DrawUtils;
import utils.SoundUtils;
import utils.Window;

public class GameWindow extends Window{
	private long time;
	private int number;
	private Tank tank = new Tank();
	private int step = new Random().nextInt(15)+10;
	ArrayList<Element> creatarr = new ArrayList<>();
	Random r = new Random();
	CopyOnWriteArrayList<Element> list = new CopyOnWriteArrayList<>();
	private boolean stop;
	private int exit = 1600;

	public GameWindow(String title, int width, int height, int fps) {
		super(title, width, height, fps);
	}

	@Override
	protected void onCreate() {
		
		try {
			SoundUtils.play("res//snd//start.wav");
		} catch (IOException e) {
			e.printStackTrace();
		}
		list.add(tank);
		Symbol symbol = new Symbol(64*8, 64*9);
		list.add(symbol);
		Enemy enemy1 = new Enemy(12*64,64*9);
		list.add(enemy1);
//		Enemy enemy2 = new Enemy(15*64,64*8);
//		list.add(enemy2);
		for (int i = 0; i < 4; i++) {
			Enemy enemy = new Enemy(i*5*64,0);
			list.add(enemy);
		}
		for (int i = 0; i < 16; i++) {
			if (i==3) {
				Grass grass = new Grass(i*64,5*64);
				list.add(grass);
				i++;
			}
			Wall wall = new Wall(i*64,5*64);
			list.add(wall);
		}
		for (int i = 1; i < 17; i++) {
			if (i==6) {
				i++;
			}
			Grass grass = new Grass(i*64,3*64);
			list.add(grass);
		}
		for (int i = 0; i < 16; i++) {
			if (i==9||i==3) {
				Grass grass = new Grass(3*64,1*64);
				Grass grass1 = new Grass(9*64,1*64);
				list.add(grass);
				list.add(grass1);
				i++;
			}
			Steel steel = new Steel(i*64,1*64);
			list.add(steel);
		}
		for (int i = 8; i <=9; i++) {
			for (int j = 7; j <=9; j++) {
				if(i==9&&j==8){
					j++;
				}
				Steel steel = new Steel(j*64,i*64);
				list.add(steel);
			}
		}
		for (int i = 1; i < 17; i++) {
			if (i==12||i==4) {
				Grass grass = new Grass(4*64,7*64);
				Grass grass1 = new Grass(12*64,7*64);
				list.add(grass);
				list.add(grass1);
				i++;
			}
			Water water = new Water(i*64,7*64);
			list.add(water);
		}
	}

	@Override
	protected void onMouseEvent(int key, int x, int y) {	
		int creatint = r.nextInt(2);
//		System.out.println(x);
//		System.out.println(y);
//		System.out.println(666666666);
		switch (key) {
		case 0:
			if(creatint==0){
				list.add(new Wall(x, Config.HEIGHT-y));
			}
			if(creatint==1){
				list.add(new Steel(x, Config.HEIGHT-y));
			}
//			if(creatint==2){
//				list.add(new Water(x, Config.HEIGHT-y));
//			}
			break;
		case 1:
			for (Element element1 : list) {
				if(element1 instanceof IsRushed){
					if(element1 instanceof Symbol){
					}else {
						list.remove(element1);
					}
				}
			}
			break;

		default:
			break;
		}
	}

	@Override
	protected void onKeyEvent(int key) {
	
		switch (key) {
		case Keyboard.KEY_UP:
			tank.move(Key.UP);
			break;

		case Keyboard.KEY_DOWN:
			tank.move(Key.DOWN);
			break;
			
		case Keyboard.KEY_LEFT:
			tank.move(Key.LEFT);
			break;
			
		case Keyboard.KEY_RIGHT:
			tank.move(Key.RIGHT);
			break;
			
		case Keyboard.KEY_ESCAPE:
			System.exit(0);
			break;
			
		case Keyboard.KEY_RETURN:
			for (Element element1 : list) {
				if(element1 instanceof IsRush){
					stop = element1.stop();
//					System.out.println("stop");
				}
			}
			break;
			
		case Keyboard.KEY_SPACE:
			long now = System.currentTimeMillis();
			if (now-time>200) {
				try {
					SoundUtils.play("res//snd//fire.wav");
				} catch (IOException e) {
					e.printStackTrace();
				}
				Bullet bullet = tank.shot();
				list.add(bullet);
				time = now;
			}
			break;
			
		default:
			break;
		}
		

	}

	protected void onDisplayUpdate() {
		list.sort(new Comparator<Element>() {
			public int compare(Element o1, Element o2) {
				return o1.inOrder() - o2.inOrder();
			}
		});
	
		for (Element element1 : list) {
			if (element1 instanceof Enemy) {
				if (step==0) {
					step = new Random().nextInt(15)+15;
					if(!stop){
						((Enemy)element1).randomMove();
						EnemyBullet enemyBullet = ((Enemy)element1).shot();
						list.add(enemyBullet);
					}
				}
				if(!stop){
					((Enemy)element1).move();
				}
				step--;
			}
		}
		
		for (Element element1 : list) {
			for (Element element2 : list) {
				if(element1!=element2){
					if(element1 instanceof IsRush && element2 instanceof IsRush){
						if (((IsRush)element1).isrush((IsRush)element2)) {
//							System.out.println("is rush");
							break;
						}
					}
				}
				if (element1 instanceof IsRush && element2 instanceof IsRushed ) {
					if (((IsRush)element1).isrush((IsRushed)element2)) {
//						System.out.println("is rush");
						break;
					}
				}
			}
		}

		for (Element element1 : list) {
			for (Element element2 : list) {
				if (element1 instanceof Attack && element2 instanceof Attacked ) {
					if (((Attack)element1).isrush((Attacked)element2)) {
						Blast blast = ((Attacked)element2).attacked();
						list.add(blast);
						break;
					}
				}
				if (element1 instanceof EAttack && element2 instanceof EAttacked ) {
					if (((EAttack)element1).isrush((EAttacked)element2)) {
						Blast blast = ((EAttacked)element2).attacked();
						list.add(blast);
						break;
					}
				}
				if (element1 instanceof Attack && element2 instanceof EAttack) {
					if (((Attack)element1).isrush((EAttack)element2)) {
						Blast blast = ((EnemyBullet)element2).attacked();
						list.add(blast);
						break;
					}
				}
			}
		}
		
		for (Element element : list) {
			element.draw();
			if (element instanceof Bullet && ((Bullet)element).isOverBound()) {
				list.remove(element);
			}
			if (element instanceof EnemyBullet && ((EnemyBullet)element).isOverBound()) {
				list.remove(element);
			}
			if (element instanceof Attacked && element.isDistroy()) {
				((Attacked)element).showDistroy();
				list.remove(element);
			}
			if (element.isDistroy() && (element instanceof Symbol || element instanceof Tank)) {
				list.remove(element);
				GameOver.gamevoer();
				list.add(new GameOver());
			}
			if (element.isDistroy()) {
				list.remove(element);
			}
			if(!list.contains(tank)){//添加symbol销毁,退出虚拟机方法!
				exit--;
				if(exit==0){
					System.exit(0);
				}
			}
		}
//		System.out.println(list.size());
	}
	
}
