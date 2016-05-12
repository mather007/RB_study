package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Util {
	
	/**
	 * 随机指定范围内n个可重复的数,可取最大值与最小值
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param n 随机数个数 
	 */
	public static int[] randomCanRe(int min,int max,int n){
		if(max < min || n <= 0){
			return null;
		}
		int[] res = new int[n];
		for (int i = 0; i < res.length; i++) {
			int num = ((int) Math.round((Math.random() * (max - min)))) + min;
			res[i] = num;
		}
		return res;
	}
	
	
	/** 
	 * 随机指定范围内N个不重复的数 ，取不到最小值，能取到最大值
	 * 最简单最基本的方法 
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param n 随机数个数 
	 */  
	public static int[] randomCommon(int min, int max, int n){  
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num =  ((int)Math.round((Math.random() * (max - min)))) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;  
	}  

	
	/**
	 * 速度比randCommon快，最大值最小值都可以取到。
	 * 
	 * 
	 * 随机指定范围内N个不重复的数 在初始化的无重复待选数组中随机产生一个数放入结果中，
	 * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 然后从len-2里随机产生下一个随机数，如此类推
	 * 
	 * @param max 指定范围最大值
	 *            
	 * @param min 指定范围最小值
	 *            
	 * @param n 随机数个数
	 *            
	 * @return int[] 随机数结果集
	 */
	public static int[] randomArray(int min, int max, int n) {
		int len = max - min + 1;

		if (max < min || n > len) {
			return null;
		}

		// 初始化给定范围的待选数组
		int[] source = new int[len];
		for (int i = min; i < min + len; i++) {
			source[i - min] = i;
		}
		Random rd = new Random();
		int[] result = new int[n];
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			// 待选数组0到(len-2)随机一个下标
			index = Math.abs(rd.nextInt() % len--);
			// 将随机到的数放入结果集
			result[i] = source[index];
			// 将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
			source[index] = source[len];
		}
		return result;
	}
	
	
	/**
	 * @author zhiqiang
	 * @param Q 要检查的（数组）矩阵
	 * @param q 被检查的数组
	 * @param j 检查到Q的第几行
	 */
	public static boolean is_inQ(int[][] Q,int[] q,int j){
		if(j==0){
			return false;
		}else{
			for(int i = 0 ; i < j ; i++){
				int res = 0;
				for (int w = 0; w < q.length; w++){
					if (Q[i][w]==q[w]){
						res += 1;
					}
				}
				if(res == q.length){//如果全相等返回真
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 寻找给定参数的最大值和最小值
	 * @param array
	 * @return 返回一个HashMap，对应"max"，"min"，"max_index"，"min_index"四个键值分别对应最大值，
	 * 最小值，最大值下标，最小值下标
	 */
	public static HashMap<String, Integer> max_min(int[] array){
		if (array == null){
			return null;
		}
		if (array.length < 1){
			return null;
		}
		HashMap<String, Integer> hm = new HashMap<>();
		int min = array[0];
		int max = array[0];
		int min_index = 0;
		int max_index = 0;
		for (int i = 0; i < array.length; i++) {
			if (min > array[i]) {
				min = array[i];
				min_index = i;
			}
			if (max < array[i]) {
				max = array[i];
				max_index = i;
			}
		}
		hm.put("max", max);
		hm.put("min", min);
		hm.put("max_index", max_index);
		hm.put("min_index", min_index);
		return hm;
	}
	
	public static void main(String[] args) {
//		int[] aa = {1,2,4,4,12,1,3,4,2};
//		int[] bb = aa.clone();
//		int[] cc = {};
//		System.out.println(max_min(aa));
//		Arrays.sort(aa);
//		System.out.println(Arrays.toString(bb));
//		System.out.println(Arrays.toString(aa));
//		System.out.println((aa[aa.length-1]));
//		long time1 = System.currentTimeMillis();
//		int[] aaa = randomArray(0, 10-1, 10);
//		long time2 = System.currentTimeMillis();
//		System.out.println(time2-time1);
//		System.out.println(Arrays.toString(aaa));
//		
//		ArrayList<Integer> al = new ArrayList<>();
//		al.add(5);
//		al.add(8);
//		System.out.println(al.toString());
//		System.out.println(al.contains(5));
		System.out.println(7/3);
	}
}
