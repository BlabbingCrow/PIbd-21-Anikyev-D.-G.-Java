public class Random {
	
	public int Next(int minValue, int maxValue) {
		return (int)(minValue + Math.random() * (maxValue - minValue));
	}
}
