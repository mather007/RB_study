package RB_main;

import java.util.ArrayList;
import java.util.Arrays;

import util.Util;
/**
 * 此类用于生产RB模型的随机实例。
 * 
 */
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
				int[] temp1 = Util.randomCanRe(0, d-1, k);
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
				temp[j] =  assi[C[i][j]-1];
			}
			if(Util.is_inQ(Q[i], temp, q)){
				nums++;
			}
		}
		return nums;
	}
	/**
	 * 随机检出一个不满足的约束
	 */
	public int return_rand_c(int[] assi){
		int[] jcsx = Util.randomArray(0, t-1, t);
		for (int i = 0; i < jcsx.length; i++) {
			int[] temp = new int[k];
			for (int j = 0;j < k;j++){
				temp[j] =  assi[C[i][j]-1];
			}
			if(Util.is_inQ(Q[i], temp, q)){
				return i;
			}
		}
		return t;
	} 
	/**
	 * 返回策略扰动后的赋值
	 * @param assi 当前赋值
	 * @param ci 所针对的约束的下标
	 * 
	 */
	public int[] excitationAssi(int[] assi,int ci){
		int[] temp = new int[k];
		for(int cii = 0;cii < k;cii++){
			temp[cii] = C[ci][cii];//ci对应的变量
		}
		int[] Qi = Util.randomArray(0, k-1, 1);//从0到k中随机一个整数
		int qi = Qi[0];
		ArrayList<Integer> al = canNotIn(assi,temp,qi,ci);
		int[] insAssi = Util.randomArray(0, d-1, d);
		int ass = d;
		for (int i = 0; i < insAssi.length; i++) {
			if (al.contains(insAssi[i])) {
				continue;
			}else{
				ass = insAssi[i];
				break;
			}
		}
		if (ass==d) {
			return null;
		}else {
			assi[temp[qi]-1] = ass;
			return assi;
		}
	} 
	/**
	 * 由其他的变量，得来另一个变量的不可兼容值
	 */
	public ArrayList<Integer> canNotIn(int[] assi,int[] temp,int qi,int ci){
		ArrayList<Integer> al = new ArrayList<Integer>();
		//取出assi中除去qi里存放的变量，剩下变量的当前赋值
		int[] res = new int[temp.length-1];
		int index = 0;
		for (int i = 0; i < temp.length; i++) {
			if (i==qi) {
				continue;
			}
			res[index] = assi[temp[i]-1];
			index++;
		}
		for (int i = 0; i < q; i++) {
			int equalsNum = 0;
			int index_0 = 0;
			for (int j = 0; j < k; j++) {
				if (j==qi) {
					continue;
				}

				if(Q[ci][i][j] == res[index_0]){
					equalsNum++;
				}
				index_0++;
				if (equalsNum==k-1) {
					al.add(Q[ci][i][qi]);
				}
			}
		}
		return al;
	}
	
	
	/**
	 * 返回C中指定的约束，所存放的变量
	 * @param ci 指定的C中的哪一个约束
	 * @return
	 */
	public int[] getCiVar(int ci){
		int[] temp = new int[k];
		for(int cii = 0;cii < k;cii++){
			temp[cii] = C[ci][cii];//ci对应的变量
		}
		return temp;
	}
	
	public int[][] getQici(int ci){
		int[][] temp = new int[q][k];
		for(int i=0; i < q;i++){
			for(int j=0;j < k;j++){
				temp[i][j] = Q[ci][i][j];
			}
		}
		return temp;
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
		int N = 20;
		double alfa = 0.8;
		double r = 3;
		double p =0.2;
				
		
		RB rb = new RB(k,N,alfa,r,p);
		int[][] C = rb.getC();
		int[][][] Q = rb.getQ();
		
		for (int i = 0; i < C.length; i++) {
			System.out.println(Arrays.toString(C[i]));
		}
		System.err.println("-----------------------------");
//		int[][] lnQ = new int[rb.getQ1()][k*rb.getT()];
//		for (int j = 0; j < rb.getQ1(); j++) {
//			for (int i = 0; i < Q.length; i++) {
//				for (int j2 = 0; j2 < k; j2++) {
//					lnQ[j][k*i + j2] = Q[i][j][j2];
//				}
//			}
//			System.out.println(Arrays.toString(lnQ[j]));
//		}
		int[] sol = Util.randomCanRe(0, rb.getD()-1, N);
		System.out.println(Arrays.toString(sol));
		int ci = rb.return_rand_c(sol);
		int[][] Qici = rb.getQici(ci);
		for (int i = 0; i < rb.getQ1(); i++) {
			System.out.println(Arrays.toString(Qici[i]));
		}
		System.out.println();
		System.out.println("ci"+ci);
		sol = rb.excitationAssi(sol, ci);
		System.out.println(Arrays.toString(sol));
		/*
		long times = 0;
		for (int i = 0; i < 100; i++) {
			int[] sol = Util.randomCanRe(0, rb.getD()-1, N);
			long time3 = System.currentTimeMillis();
			int res1 = rb.count_enable_num(sol);
			long time4 = System.currentTimeMillis();
			times  = times + (time4 - time3);
			System.out.println(res1);
		}
		
		System.out.println("计数的时间："+(times));
		
		int[][] C = rb.getC();
		int[][][] Q = rb.getQ();
		
		for (int i = 0; i < C.length; i++) {
			System.out.println(Arrays.toString(C[i]));
		}
		System.err.println("-----------------------------");
		int[][] lnQ = new int[rb.getQ1()][k*rb.getT()];
		for (int j = 0; j < rb.getQ1(); j++) {
			for (int i = 0; i < Q.length; i++) {
				for (int j2 = 0; j2 < k; j2++) {
					lnQ[j][k*i + j2] = Q[i][j][j2];
				}
			}
			System.out.println(Arrays.toString(lnQ[j]));
		}
	*/	
	}
}
