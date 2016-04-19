package RB_main;

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
			Util.randomCommon(1,N,k);
		}
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





	public static void main(String[] args) {
		Math.log(Math.E);
		System.out.println(Math.log(Math.E));
		System.out.println(Math.E);
	}
}
