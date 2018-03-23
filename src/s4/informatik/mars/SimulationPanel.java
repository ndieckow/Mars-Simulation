package s4.informatik.mars;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import s4.informatik.mars.gui.Bar;
import s4.informatik.mars.gui.HorizontalBar;
import s4.informatik.mars.gui.VerticalBar;

public class SimulationPanel extends JPanel {

	private Colony colony; // die Kolonie, in der alle für die Simulation wichtigen Werte enthalten sind
	private List<Bar> bars = new ArrayList<>();

	private HorizontalBar energyProductionBar;
	private HorizontalBar energyUsageBar;

	private HorizontalBar areaBar;
	private HorizontalBar populationBar;

	private VerticalBar erzBar;
	private VerticalBar alumBar;

	int erzTicks = 0;
	boolean tick = true;

	public SimulationPanel() {
		setBackground(Color.BLACK);

		colony = new Colony(10, 1, 10, 1, 1, 1);
		// Horizontale Leisten
		int hbs = 450; // y-Koordinate des Startpunkts der horizontalen Leisten

		energyProductionBar = new HorizontalBar(50, hbs, 900, 20, 100);
		energyProductionBar.setFill((float) Math.random() * 100);
		bars.add(energyProductionBar);

		energyUsageBar = new HorizontalBar(50, hbs + 60, 900, 20, 100);
		energyUsageBar.setFill((float) Math.random() * 100);
		bars.add(energyUsageBar);

		areaBar = new HorizontalBar(50, hbs + 120, 900, 20, 100);
		areaBar.setFill((float) Math.random() * 100);
		bars.add(areaBar);

		populationBar = new HorizontalBar(50, hbs + 180, 900, 20, 100);
		populationBar.setFill((float) Math.random() * 100);
		bars.add(populationBar);

		// Vertikale Leisten
		erzBar = new VerticalBar(1050, 30, 30, 600, 100);
		erzBar.setFill(100);
		bars.add(erzBar);

		alumBar = new VerticalBar(1170, 30, 30, 600, 100);
		alumBar.setFill(50);
		bars.add(alumBar);
	}

	public void update() {
		// BERECHNUNGEN


		for (Bar b : bars) {
			b.update();
			b.setFill(b.getFill() + 0.2f);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Platz für Energie- & Populationsleisten
		g.setColor(Color.BLUE);
		g.fillRect(0, 720 - 300, 1000, 300);

		// Platz für Rohstoffleisten
		g.setColor(Color.RED);
		g.fillRect(1000, 0, 280, 720);

		// Elemente rendern
		for (Bar b : bars) {
			b.render(g);
		}
	}
}
