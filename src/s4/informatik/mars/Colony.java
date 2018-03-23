package s4.informatik.mars;

public class Colony {

	// constants
	public static int CALC_INTERVAL = 1; // beim wievielten Tick jeweils sollte calculate() ausgef�hrt werden?

	public static float POPULATION_GROWTH = 1; // m
	public static int AREA_CONSTANT = 1; // m // wieviele Personen pro Haus leben
	public static float GENERATOR_GROWTH = 1; // m // wieviele Generatoren pro Zeiteinheit hinzukommen
	public static float ENERGY_PROD_CONSTANT = 2; // m // wieviel Energie jeder Generator produziert
	public static float EFFICIENCY = 0.9; // m // wieviel Alu aus einem Erz gewonnen wird

	public int houses; // m
	public int area; // wieviel Personen in der Kolonie leben können
	public int population;
	public int generators; // m

	public float energyProduction;
	public float energyUsagePerPerson; // m
	public float energyUsage;

	public float ore;
	public float aluminium;
	public float miningMod; // m // Modifikator, der beeinflusst wieviel Erz erzeugt wird
	public float smeltingMod; // m // Modifikator, der beeinflusst wie schnell Erz zu Aluminium wird

	public float miningPercentage; // m // wieviel % der Restenergie in den Bergbau fließt
	public float miningPercentagePop; // m // welcher Anteil der Bevölkerung im Bergbau arbeitet

	public float housePrice; // m // wieviel Aluminium ein Haus benötigt

	public Colony(int people, int houses, int generators, float energyUsagePerPerson, float miningMod, float smeltingMod, float miningPercentage, float miningPercentagePop, float housePrice) {
		population = people; // m
		this.houses = houses;
		this.generators = generators;
		this.energyUsagePerPerson = energyUsagePerPerson;
		this.miningMod = miningMod;
		this.smeltingMod = smeltingMod;
		this.miningPercentage = miningPercentage;
		this.miningPercentagePop = miningPercentagePop;
		this.housePrice = housePrice;
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
		float energyMining = energyRest * miningPercentage;
		float energySmelting = energyRest * (1 - miningPercentage);
		float miningFactor = energyMining * (population * miningPercentagePop);
		float oreProd = miningFactor * miningMod;
		float smeltingFactor = energySmelting * (population * (1 - miningPercentagePop));
		float oreUsage = smeltingFactor * smeltingMod;

		aluminium = oreUsage * EFFICIENCY;
	}
}
