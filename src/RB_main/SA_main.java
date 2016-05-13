package RB_main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
	private static int[] Ns = { 20, 40, 60, 80, 100 };

	
	public static HashMap<String, Object> SA(RB rb,int var_nums){
		HashMap<String, Object> hmRes = new HashMap<String, Object>(); 
		int[] sol_new = Util.randomCanRe(0, rb.getD()-1, var_nums);
		int E_current = rb.count_enable_num(sol_new);
		int E_best = E_current;
		int E_new = E_current;
		int[] sol_current = Arrays.copyOf(sol_new, sol_new.length);
		int[] sol_best = Arrays.copyOf(sol_new, sol_current.length);
		double t = t0;
		while (t >= tf) {
			for(int lk = 1;lk <= Lk;lk++){
				if(E_best==0){
					hmRes.put("E_best", E_best);
					hmRes.put("sol_best", sol_best);
					return hmRes; 
				}
				if(Math.random()<3/t){
					sol_new = Arrays.copyOf(sol_best,sol_best.length);
					int ci = rb.return_rand_c(sol_new);
					sol_new = rb.excitationAssi(sol_new, ci);
				}else{
					int[] randV= Util.randomArray(0, var_nums-1, 1);//随机取从0到N-1的一个位置
					if(Math.random()<0.5){//将从第一个变量开始到randV这个变量值从新随机赋值
						int vi = randV[0];
						int[] rands = Util.randomCanRe(0,rb.getD()-1 , vi+1);
						for (int j = 0; j < rands.length; j++) {
							sol_new[j] = rands[j];
						}
					}else{
						int vi = randV[0];
						int[] rands = Util.randomCanRe(0,rb.getD()-1 , var_nums-1-vi);
						int indexs = 0;
						for(int j = vi+1;j < var_nums;j++){
							sol_new[j] = rands[indexs];
							indexs++;
						}
					}
					E_new = rb.count_enable_num(sol_new);
					if(E_new < E_current){
						E_current = E_new;
						sol_current = Arrays.copyOf(sol_new, sol_new.length);
						if(E_new < E_best){
							E_best = E_new;
							sol_best = Arrays.copyOf(sol_new, sol_new.length);
						}
					}else{
						if(Math.random()<Math.exp(((double)E_new - (double)E_current)/t)){
							E_current = E_new;
							sol_current = Arrays.copyOf(sol_new, sol_new.length);
						}else{
							sol_new = Arrays.copyOf(sol_current,sol_current.length);
						}
					}
				}
			}
			t *= a;
		}
		hmRes.put("E_best", E_best);
		hmRes.put("sol_best", sol_best);
		return hmRes;
	}
	
	
	
	public static void main(String[] args) {
		try {
			FileWriter out1 = new FileWriter("C:\\Users\\CloudCross\\Desktop\\SAres.txt", true);
			FileWriter out2 = new FileWriter("C:\\Users\\CloudCross\\Desktop\\SAres_time.txt", true);
//			out.write("abc");
//		        //换行
//		    out.write("\r\n");
//		        //继续追加
//		    out.write("def");
//		        //刷新IO内存流
//		    out.flush();
//		        //关闭
//		    out.close();
	
        //往文件写入
     
		
		p = new double[21];
		double pi = 0;
		double[] ps = new double[21];
		for (int i = 0; i < ps.length; i++) {
			pi = ((double)i)/100;
			ps[i] = pi;
		}
		p = ps;
		for (int i = 0; i < Ns.length; i++) {
			for (int ii = 0; ii < p.length; ii++) {
				for(int iii = 0;iii < M;iii++){
					RB rb = new RB(k,Ns[i],alfa,r,p[ii]);
					System.out.println("计算到N="+Ns[i]);
					System.out.println("计算到p="+p[ii]);
					System.out.println("计算到第"+(iii+1)+"组");
					long time1 = System.currentTimeMillis();
					HashMap<String, Object> hmres = SA(rb,Ns[i]);
					long time2 = System.currentTimeMillis();
					if(iii==0){
						out1.write(String.valueOf(hmres.get("E_best")));
						out2.write(String.valueOf(time2-time1));
					}else{
						out1.write(","+String.valueOf(hmres.get("E_best")));
						out2.write(","+String.valueOf(time2-time1));
					}

					System.out.println("最少不满足约束个数："+hmres.get("E_best"));
					
					System.out.println("最佳赋值："+Arrays.toString((int[])hmres.get("sol_best")));
//					int[] sol_new = Util.randomCanRe(0, rb.getD()-1, Ns[i]);
//					int E_current = rb.count_enable_num(sol_new);
//					int E_best = E_current;
//					int E_new = E_current;
//					int[] sol_current = Arrays.copyOf(sol_new, sol_new.length);
//					int[] sol_best = Arrays.copyOf(sol_new, sol_current.length);
//					double t = t0;
//					while (t >= tf) {
//						boolean is_SAT = false;
//						for(int lk = 1;lk <= Lk;lk++){
//							if(Math.random()<3/t){
//								sol_new = Arrays.copyOf(sol_best,sol_best.length);
//								int ci = rb.return_rand_c(sol_new);
//								if(ci==rb.getT()){
//									is_SAT =true;
//									break;
//								}else{
//									sol_new = rb.excitationAssi(sol_new, ci);
//								}
//							}else{
//								int[] randV= Util.randomArray(0, Ns[i]-1, 1);//随机取从0到N-1的一个位置
//								if(Math.random()<0.5){//将从第一个变量开始到randV这个变量值从新随机赋值
//									int vi = randV[0];
//									int[] rands = Util.randomCanRe(0,rb.getD()-1 , vi+1);
//									for (int j = 0; j < rands.length; j++) {
//										sol_new[j] = rands[j];
//									}
//								}else{
//									int vi = randV[0];
//									int[] rands = Util.randomCanRe(0,rb.getD()-1 , Ns[i]-1-vi);
//									int indexs = 0;
//									for(int j = vi+1;j < Ns[i];j++){
//										sol_new[j] = rands[indexs];
//										indexs++;
//									}
//								}
//								E_new = rb.count_enable_num(sol_new);
//								if(E_new < E_current){
//									E_current = E_new;
//									sol_current = Arrays.copyOf(sol_new, sol_new.length);
//									if(E_new < E_best){
//										E_best = E_new;
//										sol_best = Arrays.copyOf(sol_new, sol_new.length);
//									}
//								}else{
//									if(Math.random()<Math.exp(((double)E_new - (double)E_current)/t)){
//										E_current = E_new;
//										sol_current = Arrays.copyOf(sol_new, sol_new.length);
//									}else{
//										sol_new = Arrays.copyOf(sol_current,sol_current.length);
//									}
//								}
//							}
//						}
//						if (is_SAT) {
//							System.out.println("找到解："+Arrays.toString(sol_best));
//							break;
//						}
//						t *= a;
//					}
				}
				out1.write("\r\n");
				out2.write("\r\n");
			}
			out1.write("\r\n");
			out2.write("\r\n");
		}
		out1.flush();
		out2.flush();
		out1.close();
		out2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
