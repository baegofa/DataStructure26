package ds_lecture_26;

import java.util.Arrays;
import java.util.*;

public class SelectSort {
	
	public void sort(int[] A) {
		for (int i=0;i<A.length-1;i++) {     //마지막 하나는 검증할 필요가 없으니 -1 넣는 것
			int min_pos = i;
			for (int j=i+1;j<A.length;j++) 
				if(A[j]<A[min_pos])
					min_pos = j;
			int temp = A[i];
			A[i] = A[min_pos];
			A[min_pos] = temp;
		}
	}
	
	public boolean isSorted(int[] A) {
		for (int i=0; i< A.length - 1;i++) {
			if (A[i] > A[i+1])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		SelectSort driver = new SelectSort();
		int[] size = {1000,2000,5000,10000,20000,30000,40000,60000,80000,100000};
		
		Random rand = new Random();
		for (int k=0; k<size.length;k++) {
			int[] A = new int[size[k]];
			for (int i =0; i< size[k]; i++) {
				A[i] = rand.nextInt(100000);
			}
			long start = System.currentTimeMillis();
			driver.sort(A);
			long finish = System.currentTimeMillis();
			if(driver.isSorted(A)) {
				System.out.print("배열의 원소 수 = " + size[k]);
				System.out.println(", 실행 시간 = " + (finish - start)/1000.0 + "초");
			}
		}
		int[] A = new int[10];
		for (int i=0;i<10;i++)
			A[i] = rand.nextInt(100);

	}
	
}
