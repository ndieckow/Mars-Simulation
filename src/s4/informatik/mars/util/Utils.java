package s4.informatik.mars.util;

// Static util class
public class Utils {
	
	public static double getBarMaxChangeValue(int ticks, int maxDiff) {
		double value = 0;
		for (int i = 0; i < ticks; i++) {
			value += Math.sin((Math.PI / ticks) * i);
		}
		return maxDiff / value;
	}
}