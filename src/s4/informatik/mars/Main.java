package s4.informatik.mars;

public class Main {
	
	public static boolean READY = false;
	
	public static void main(String[] args) {
		new ConfigWindow();
	}
	
	public static void startSimulation(int people, int houses, int generators) {
		new Simulation(people, houses, generators);
	}
}