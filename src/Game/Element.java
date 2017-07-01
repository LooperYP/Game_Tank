package Game;

import java.awt.Color;
import java.io.IOException;

import com.itheima.game.GameEngine;
import com.itheima.game.HeimaAbstractWorld;

import utils.DrawUtils;

public abstract class Element{
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected int speed;
	protected int blood = 1;
	
	public Element() {
		super();
	}

	public Element(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw();
	
	public abstract int inOrder();
	
	public abstract boolean isDistroy();

	public void logic() {
	
	}

	public boolean stop() {
		
		return false;
	}

}
