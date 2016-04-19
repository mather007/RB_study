package RB_main;

import java.lang.reflect.Array;
import java.util.Set;
import java.util.Timer;

import util.Util;

public class RB {
	
	//约束长度
	private int k;
	//变量个数
	private int N;
	//控制参数
	private double alfa;
	private double r;
	private double p;
	
	//各个变量的定义域
	private int d;
	//约束的个数
	private int t;
	//不可兼容值的个数
	private int q;
	
	private int[][] C;
	private int[][][] Q;
	
	public RB(int k,int N,double alfa,double r,double p){
		this.alfa = alfa;
		this.k = k;
		this.N = N;
		this.r = r;
		this.p = p;
		this.d = (int)Math.round(Math.pow(N, alfa));
		this.t = (int)Math.round(r*N*Math.log(N));
		this.q = (int)Math.round(p*Math.pow(d, k));
		
		this.C = new int[t][k];
		this.Q = new int[t][q][k];
		
		for(int i = 0;i < t; i++){
			int[] temp = Util.randomCommon(1, N, k);
			C[i] = temp;//约束中的变量集合
			int j = 0;
			while(j < q){
				int[] temp1 = Util.randomCommon(0, d-1, k);
				if(!Util.is_inQ(Q[i], temp1, j)){
					Q[i][j] = temp1;
					j++;
				}
			}
		}
	}
	
	
	public int count_enable_num(int[] assi){
		int nums = 0;
		for (int i = 0;i < t;i++){
			int[] temp = new int[k];
			for (int j = 0;j < k;j++){
				temp[j] =  assi[C[i][j]];
			}
			if(Util.is_inQ(Q[i], temp, q)){
				nums++;
			}
		}
		return nums;
	}
	
	
	public int getK() {
		return k;
	}





	public void setK(int k) {
		this.k = k;
	}





	public int getN() {
		return N;
	}





	public void setN(int n) {
		N = n;
	}





	public double getAlfa() {
		return alfa;
	}





	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}





	public double getR() {
		return r;
	}





	public void setR(double r) {
		this.r = r;
	}





	public double getP() {
		return p;
	}





	public void setP(double p) {
		this.p = p;
	}





	public int getD() {
		return d;
	}





	public void setD(int d) {
		this.d = d;
	}





	public int getT() {
		return t;
	}





	public void setT(int t) {
		this.t = t;
	}





	public int getQ1() {
		return q;
	}





	public void setQ(int q) {
		this.q = q;
	}





	public int[][] getC() {
		return C;
	}





	public void setC(int[][] c) {
		C = c;
	}





	public int[][][] getQ() {
		return Q;
	}





	public void setQ(int[][][] q) {
		Q = q;
	}





	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int k = 2;
		int N = 100;
		double alfa = 0.8;
		double r = 3;
		double p =0.2;
		long time1 = System.currentTimeMillis();
		RB rb = new RB(k,N,alfa,r,p);
		long time2 = System.currentTimeMillis();
		int[][] C = rb.getC();
		int[][][] Q = rb.getQ();
		System.out.println((time2-time1));
		System.out.println(C.length);
		System.out.println(Q[1].length);
		System.out.println(rb.getT());
		System.out.println(rb.getQ1());
		System.out.println(rb.getD());
	}
}
