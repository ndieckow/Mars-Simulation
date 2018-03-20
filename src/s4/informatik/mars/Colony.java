package s4.informatik.mars;

public class Colony {

	// constants
	public static int CALC_INTERVAL = 1; // beim wievielten Tick jeweils sollte calculate() ausgeführt werden?
	
	public static float POPULATION_GROWTH = 1;
	public static float AREA_CONSTANT = 1;
	public static float GENERATOR_GROWTH = 1;
	public static float ENERGY_PROD_CONSTANT = 1;
	
	public int houses;
	public float area;
	public int population;
	public int generators;
	
	public float energyProduction;
	public float energyUsagePerPerson;
	public float energyUsage;
	
	public float ore;
	public float aluminium;
	public float bergbaumodifikator;
	
	public Colony(int people, int houses, int generators, float energyUsagePerPerson, float bergbaumodifikator) {
		population = people;
		this.houses = houses;
		this.generators = generators;
		this.energyUsagePerPerson = energyUsagePerPerson;
		this.bergbaumodifikator = bergbaumodifikator;
	}
	
	/**
	 * Berechnet alle für die Simulation relevanten Werte der Kolonie.
	 * Entspricht einer Zeitspanne von einem Monat.
	 */
	public void calculate() {
		population += population * POPULATION_GROWTH;
		area = houses * AREA_CONSTANT;
		energyProduction = generators * ENERGY_PROD_CONSTANT;
		energyUsage = population * energyUsagePerPerson;
		
		float energyRest = energyProduction - energyUsage;
		float energyBergbau = energyRest / 2;
		float energyVerhuetung = energyRest / 2;
		float bergbaufaktor = energyBergbau * (population / 2);
		float oreProd = bergbaufaktor * bergbaumodifikator;
		// float verhuetungsfaktor...
	}
}