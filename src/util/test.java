package util;

import java.util.Arrays;

public class test {
	public static void main(String[] args) {
		double pi = 0;
		double[] ps = new double[21];
		for (int i = 0; i < ps.length; i++) {
			pi = ((double)i)/100;
			ps[i] = pi;
			 
		}
		System.out.println(Arrays.toString(ps));
	}
}
