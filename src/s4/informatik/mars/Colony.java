package s4.informatik.mars;

public class Colony {

	// constants
	public static int CALC_INTERVAL = 120; // beim wievielten Tick jeweils sollte calculate() ausgeführt werden?

	public static float POPULATION_GROWTH = 0.2f; // m
	public static int AREA_CONSTANT = 10; // m // wieviele Personen pro Haus leben
	public static float GENERATOR_GROWTH = 0.2f; // m // wieviele Generatoren pro Zeiteinheit hinzukommen
	public static float ENERGY_PROD_CONSTANT = 2; // m // wieviel Energie jeder Generator produziert
	public static float EFFICIENCY = 0.9f; // m // wieviel Alu aus einem Erz gewonnen wird
	public static float EPP; // m // energy usage per person
	public static float MINING_MOD; // m // Modifikator, der beeinflusst wieviel Erz erzeugt wird
	public static float SMELTING_MOD; // m // Modifikator, der beeinflusst wie schnell Erz zu Aluminium wird
	public static float MINING_PERCENTAGE; // m // wieviel % der Restenergie in den Bergbau fließt
	public static float MINING_PERCENTAGE_POP; // m // welcher Anteil der Bevölkerung im Bergbau arbeitet
	public static float HOUSE_PRICE; // m // wieviel Aluminium ein Haus benötigt

	public int houses; // m
	public int capacity; // wieviel Personen in der Kolonie leben können
	private int hiddenCapacity;
	public int OLD_capacity;
	public int population; // m
	public int OLD_population;
	public int generators; // m

	public float energyProduction;
	public float OLD_energyProduction;
	public float energyUsage;
	public float OLD_energyUsage;

	public float ore;
	public float OLD_ore;
	public float aluminium;
	public float OLD_aluminium;
	
	// Hilfsvariablen
	private boolean modificatorSave;
	private float miningPercentage_save;
	private float miningPercentagePop_save;

	public Colony(int people, int houses, int generators) {
		population = people;
		this.houses = houses;
		this.generators = generators;
	}

	/**
	 * Berechnet alle für die Simulation relevanten Werte der Kolonie.
	 * Entspricht einer Zeitspanne von einem Monat.
	 */
	public void calculate() {
		// set OLD variables
		OLD_population = population;
		OLD_capacity = capacity;
		OLD_energyProduction = energyProduction;
		OLD_energyUsage = energyUsage;
		OLD_ore = ore;
		OLD_aluminium = aluminium;
		
		generators += generators * GENERATOR_GROWTH;
		capacity = houses * AREA_CONSTANT;
		energyProduction = generators * ENERGY_PROD_CONSTANT;
		energyUsage = population * EPP;
		
		if ((energyProduction - capacity * EPP) < 0) {
			hiddenCapacity = (int) (energyProduction / EPP);
		} else {
			hiddenCapacity = capacity;
		}
		
		population += population * POPULATION_GROWTH;
		
		if (population > hiddenCapacity) {
			population = hiddenCapacity;
		}

		float energyRest = energyProduction - energyUsage;
		float energyMining = energyRest * MINING_PERCENTAGE;
		float energySmelting = energyRest * (1 - MINING_PERCENTAGE);
		float miningFactor = energyMining * (population * MINING_PERCENTAGE_POP);
		float oreProd = miningFactor * MINING_MOD;
		float smeltingFactor = energySmelting * (population * (1 - MINING_PERCENTAGE_POP));
		float oreUsage = smeltingFactor * SMELTING_MOD;
		
		ore += oreProd;
		if (oreUsage <= ore) ore -= oreUsage;
		else oreUsage = 0;
		
		if (ore * EFFICIENCY >= 10 * HOUSE_PRICE) {
			if (!modificatorSave) {
				modificatorSave = true;
				miningPercentage_save = MINING_PERCENTAGE;
				miningPercentagePop_save = MINING_PERCENTAGE_POP;
				MINING_PERCENTAGE = 0;
				MINING_PERCENTAGE_POP = 0;
			}
		} else {
			if (modificatorSave) {
				modificatorSave = false;
				MINING_PERCENTAGE = miningPercentage_save;
				MINING_PERCENTAGE_POP = miningPercentagePop_save;
			}
		}
		
		aluminium += oreUsage * EFFICIENCY;
		
		// evtl. Häuserzahl erhöhen
		while (aluminium >= HOUSE_PRICE) {
			houses++;
			aluminium -= HOUSE_PRICE;
		}
		
		System.out.println("=========================");
		System.out.println("Population: " + population);
		System.out.println("Haeuser: " + houses);
		System.out.println("Bevoelkerungskap.: " + capacity);
		System.out.println("Energieproduktion: " + energyProduction);
		System.out.println("Energienutzung: " + energyUsage);
		System.out.println("Energierest: " + energyRest);
		System.out.println("Aluminium: " + aluminium);
	}
}
