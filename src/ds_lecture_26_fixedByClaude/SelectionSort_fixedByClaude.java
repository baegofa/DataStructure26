package ds_lecture_26_fixedByClaude;

import java.util.Random;

public class SelectionSort_fixedByClaude {

	public void sort(int[] A) {
		for (int i = 0; i < A.length - 1; i++) {	// 마지막 하나는 자동으로 제자리이므로 -1
			int minPos = i;
			for (int j = i + 1; j < A.length; j++)
				if (A[j] < A[minPos])
					minPos = j;
			if (minPos != i) {	// 이미 제자리면 교환하지 않는다
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
		SelectionSort_fixedByClaude driver = new SelectionSort_fixedByClaude();
		int[] size = {1000, 2000, 5000, 10000, 20000, 50000, 80000, 100000, 200000};
		Random rand = new Random();

		for (int n : size) {
			int[] A = new int[n];
			for (int i = 0; i < n; i++)
				A[i] = rand.nextInt(100000);
			long start = System.nanoTime();	// FIX: currentTimeMillis 보다 정밀한 nanoTime 사용
			driver.sort(A);
			long finish = System.nanoTime();
			System.out.print("배열의 원소 수 = " + n);
			if (driver.isSorted(A))
				System.out.println(", 실행 시간 = " + (finish - start) / 1e9 + "초");
			else
				System.out.println(", 정렬이 안되었습니다.");	// FIX: 원본은 앞 문장과 붙어 출력됐다
		}
	}
}
