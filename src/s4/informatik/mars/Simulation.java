package s4.informatik.mars;

import java.awt.Insets;

import javax.swing.JFrame;

public class Simulation implements Runnable {
	
	private Thread thread;
	private boolean running;
	
	private JFrame frame;
	private SimulationPanel sPanel;
	
	public Simulation(int people, int houses, int generators) {
		frame = new JFrame("Mars Simulation");
		Insets insets = frame.getInsets();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		sPanel = new SimulationPanel(people, houses, generators);
		frame.add(sPanel);
		
		frame.setSize(1280 + insets.left + insets.right, 720 + insets.top + insets.bottom);
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		start();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		sPanel.update();
	}
	
	public void render() {
		sPanel.repaint();
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double ns = 1000000000.0 / 60.0; // 1/60 einer Sekunde in Nanosekunden
		double delta = 0;
		long lastTimer = System.currentTimeMillis();
		int ticks = 0;
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {
				update();
				ticks++;
				delta--;
			}
			
			render();
			frames++;
			
			if (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				//System.out.println(ticks + " ticks, " + frames + " fps");
				ticks = 0;
				frames = 0;
			}
		}
	}
}