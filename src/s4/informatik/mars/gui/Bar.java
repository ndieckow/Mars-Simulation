package s4.informatik.mars.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import s4.informatik.mars.util.Utils;

public abstract class Bar {
	
	protected int x, y;
	protected int width, height;
	protected float max;
	protected float fill;
	protected float changeRate;
	protected String name;
	
	private boolean changingMax = false;
	private int temp_newMax = 0;
	private int temp_maxDiff = 0;
	private int temp_changeTicks = 0;
	private int temp_maxTicks = 0;
	private double temp_barMaxChangeValue;
	
	private List<Bar> coupledBars = new ArrayList<Bar>();
	
	public Bar(int x, int y, int width, int height, float max, String name) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.max = max;
		this.name = name;
		fill = 0;
	}
	
	public float getFill() {
		return fill;
	}
	
	public void setFill(float fill) {
		if (fill > max) {
			this.fill = max;
			return;
		} else if (fill < 0) {
			this.fill = 0;
			return;
		}
		this.fill = fill;
	}
	
	public float getMax() {
		return max;
	}
	
	public float getChangeRate() {
		return changeRate;
	}
	
	public void setChangeRate(float changeRate) {
		this.changeRate = changeRate;
	}
	
	public String getName() {
		return name;
	}
	
	public void changeMaxSmoothly(int newMax, int ticks) {
		if (newMax < max || changingMax)
			return;
		
		changingMax = true;
		temp_newMax = newMax;
		temp_maxDiff = newMax - (int) max;
		temp_maxTicks = ticks;
		temp_barMaxChangeValue = Utils.getBarMaxChangeValue(ticks, temp_maxDiff);
	}
	
	public void update() {
		if (changingMax) {
			temp_changeTicks++;
			if (temp_changeTicks >= temp_maxTicks) {
				changingMax = false;
				temp_changeTicks = 0;
				temp_maxTicks = 0;
				max = temp_newMax; // Update max, so it has an integer value
				temp_newMax = 0;
				temp_maxDiff = 0;
			} else {
				max += Math.sin((Math.PI / temp_maxTicks) * temp_changeTicks) * temp_barMaxChangeValue;
			}
		}
		
		fill += changeRate;
		if (fill >= max && !changingMax) changeMaxSmoothly((int) max * 4, 60);
	}
	
	public abstract void render(Graphics g);
}