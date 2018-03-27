package s4.informatik.mars;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import s4.informatik.mars.gui.Bar;
import s4.informatik.mars.gui.HorizontalBar;
import s4.informatik.mars.gui.VerticalBar;

public class SimulationPanel extends JPanel {

	private Colony colony; // die Kolonie, in der alle für die Simulation wichtigen Werte enthalten sind
	private List<Bar> bars = new ArrayList<>();

	private HorizontalBar energyProductionBar;
	private HorizontalBar energyUsageBar;

	private HorizontalBar capacityBar;
	private HorizontalBar populationBar;

	private VerticalBar oreBar;
	private VerticalBar alumBar;

	int calcTicks = 0; // zählt bis Colony.CALC_INTERVAL

	public SimulationPanel(int people, int houses, int generators) {
		setLayout(null);
		setBackground(Color.BLACK);
		
		ImageIcon icon = new ImageIcon(SimulationPanel.class.getResource("/mars.gif"));
		JLabel label = new JLabel(icon);
		label.setBounds(279, 15, 720, 404);
		add(label);

		colony = new Colony(people, houses, generators);
		// Horizontale Leisten
		int hbs = 450; // y-Koordinate des Startpunkts der horizontalen Leisten

		energyProductionBar = new HorizontalBar(50, hbs, 900, 20, 100, "Energieproduktion");
		bars.add(energyProductionBar);

		energyUsageBar = new HorizontalBar(50, hbs + 60, 900, 20, 100, "Energieverbrauch");
		bars.add(energyUsageBar);

		capacityBar = new HorizontalBar(50, hbs + 120, 900, 20, 100, "Wohnkapazität");
		capacityBar.setFill(colony.capacity);
		bars.add(capacityBar);

		populationBar = new HorizontalBar(50, hbs + 180, 900, 20, 100, "Population");
		populationBar.setFill(colony.population);
		bars.add(populationBar);

		// Vertikale Leisten
		oreBar = new VerticalBar(1050, 30, 30, 600, 100, "Erz");
		bars.add(oreBar);

		alumBar = new VerticalBar(1170, 30, 30, 600, Colony.HOUSE_PRICE, "Aluminium");
		bars.add(alumBar);
		
		doCalculations();
	}

	public void update() {
		calcTicks++;
		if (calcTicks >= Colony.CALC_INTERVAL) {
			calcTicks = 0;
			doCalculations();
		}

		for (Bar b : bars) {
			b.update();
		}
	}
	
	private void doCalculations() {
		colony.calculate();
		
		energyProductionBar.setChangeRate((colony.energyProduction - colony.OLD_energyProduction) / Colony.CALC_INTERVAL);
		energyUsageBar.setChangeRate((colony.energyUsage - colony.OLD_energyUsage) / Colony.CALC_INTERVAL);
		capacityBar.setChangeRate((float) (colony.capacity - colony.OLD_capacity) / Colony.CALC_INTERVAL);
		populationBar.setChangeRate((float) (colony.population - colony.OLD_population) / Colony.CALC_INTERVAL);
		oreBar.setChangeRate((colony.ore - colony.OLD_ore) / Colony.CALC_INTERVAL);
		alumBar.setChangeRate((colony.aluminium - colony.OLD_aluminium) / Colony.CALC_INTERVAL);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Platz für Energie- & Populationsleisten
		g.setColor(new Color(70, 128, 179));
		g.fillRect(0, 720 - 300, 1000, 300);

		// Platz für Rohstoffleisten
		g.setColor(new Color(128, 151, 171));
		g.fillRect(1000, 0, 280, 720);

		// Elemente rendern
		for (Bar b : bars) {
			b.render(g);
		}
	}
}
