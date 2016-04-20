package util;

import java.util.Arrays;

public class Util {
	
	/**
	 * 随机指定范围内n个可重复的数
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
	 * 随机指定范围内N个不重复的数 
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
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			System.out.println(Arrays.toString(randomCanRe(0, 10, 2)));
		}
	}
}
