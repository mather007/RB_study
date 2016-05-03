package RB_main;

import java.util.ArrayList;
import java.util.Arrays;

import util.Util;

/**
 * 使用模拟退火算法
 * 
 * @author yzq
 *
 */
public class SA_main {
	private static double a = 0.95;
	private static int t0 = 97;
	private static int tf = 3;
	private static int Lk = 1000;
	private static int k = 2;
	private static double alfa = 0.8;
	private static double r = 3;
	private static double[] p = null;
	private static int M = 100;
	private static int[] Ns = { 20, 40, 60, 100 };

	public static void main(String[] args) {
		p = new double[21];
		double pi = 0;
		double[] ps = new double[21];
		for (int i = 0; i < ps.length; i++) {
			ps[i] = pi;
			pi += 0.01; 
		}
		//System.out.println(Arrays.toString(ps));
		p = ps;
		for (int i = 0; i < Ns.length; i++) {
			for (int ii = 0; ii < p.length; ii++) {
				for(int iii = 0;iii < M;iii++){
					RB rb = new RB(k,Ns[i],alfa,r,p[ii]);
					int[] sol_new = Util.randomCanRe(0, rb.getD()-1, Ns[i]);
					int E_current = rb.count_enable_num(sol_new);
					int E_best = E_current;

					int[] sol_current = Arrays.copyOf(sol_new, sol_new.length);
					int[] sol_best = Arrays.copyOf(sol_new, sol_current.length);
					double t = t0;
					while (t >= tf) {
						boolean is_SAT = false;
						for(int lk = 1;lk <= Lk;lk++){
							if(Math.random()<3/t){
								sol_new = Arrays.copyOf(sol_best,sol_best.length);
								int ci = rb.return_rand_c(sol_new);
								if(ci==rb.getT()){
									is_SAT =true;
									break;
								}else{
									//随机扰动
								}
							}
						}
						if (is_SAT) {
							break;
						}
						t *= a;
					}
				}
			}
		}
	}

}
