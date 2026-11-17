package ds_lecture_26_fixedByClaude;

import java.util.Random;
// FIX: 쓰지 않는 import (java.util.Arrays, java.util.*) 를 정리했다.

// 참고: SelectionSort_fixedByClaude 와 같은 알고리즘이다. 하나만 남겨도 된다.
public class SelectSort_fixedByClaude {

	public void sort(int[] A) {
		for (int i = 0; i < A.length - 1; i++) {	// 마지막 하나는 검증할 필요가 없으니 -1
			int minPos = i;
			for (int j = i + 1; j < A.length; j++)
				if (A[j] < A[minPos])
					minPos = j;
			if (minPos != i) {
				int temp = A[i];
				A[i] = A[minPos];
				A[minPos] = temp;
			}
		}
	}

	public boolean isSorted(int[] A) {
		for (int i = 0; i < A.length - 1; i++)
			if (A[i] > A[i + 1])
				return false;
		return true;
	}

	public static void main(String[] args) {
		SelectSort_fixedByClaude driver = new SelectSort_fixedByClaude();
		int[] size = {1000, 2000, 5000, 10000, 20000, 30000, 40000, 60000, 80000, 100000};
		Random rand = new Random();

		for (int n : size) {
			int[] A = new int[n];
			for (int i = 0; i < n; i++)
				A[i] = rand.nextInt(100000);
			long start = System.nanoTime();
			driver.sort(A);
			long finish = System.nanoTime();
			System.out.print("배열의 원소 수 = " + n);
			if (driver.isSorted(A))
				System.out.println(", 실행 시간 = " + (finish - start) / 1e9 + "초");
			else	// FIX: 원본은 정렬에 실패하면 아무것도 출력하지 않았다
				System.out.println(", 정렬이 안되었습니다.");
		}
		// FIX: 원본 끝의 쓰이지 않는 배열 생성 코드를 제거했다.
	}
}
