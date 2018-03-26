package s4.informatik.mars.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class VerticalBar extends Bar {
	
	public VerticalBar(int x, int y, int width, int height, float max) {
		super(x, y, width, height, max);
	}
	
	@Override
	public void render(Graphics g) {
		float fillPart = fill / max;
		if (fillPart > 1) fillPart = 1;
		
		// Render the unfilled part
		g.setColor(new Color(255, 255, 255, 90));
		g.fillRect(x, y, width, (int) (height * (1.0f - fillPart)));
		
		// Render the filled part
		g.setColor(Color.WHITE);
		g.fillRect(x, y + (int) (height * (1.0f - fillPart)), width, (int) (height * fillPart));
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.BOLD, 15));
		g.drawString((int) fill + " / " + (int) max, x, y + height + 15);
	}
}