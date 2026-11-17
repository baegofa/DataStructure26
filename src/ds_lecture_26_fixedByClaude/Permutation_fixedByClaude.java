package ds_lecture_26_fixedByClaude;

public class Permutation_fixedByClaude {
	private long count;	// 만들어진 순열의 수 (시간 측정 시 결과 확인용)

	/** A[k..] 의 모든 순열을 만든다. print 가 true 면 각 순열을 출력한다. */
	public void perm(char[] A, int k, boolean print) {
		if (k >= A.length - 1) {	// FIX: 빈 배열(length 0)에서도 바로 끝나도록 >= 사용
			count++;
			if (print)
				System.out.println(new String(A));
			return;
		}
		for (int i = k; i < A.length; i++) {
			swap(A, i, k);
			perm(A, k + 1, print);
			swap(A, i, k);
		}
	}

	private void swap(char[] A, int i, int k) {
		char temp = A[k];
		A[k] = A[i];
		A[i] = temp;
	}

	public static void main(String[] args) {
		Permutation_fixedByClaude driver = new Permutation_fixedByClaude();
		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// FIX: 원본은 size 를 25 까지 늘려 25! 번 재귀하므로 사실상 끝나지 않는다.
		// n! 은 n 이 1 늘 때마다 n 배가 되므로 12 에서 멈춘다 (12! = 약 4.8억).
		final int MAX_SIZE = 12;
		for (int size = 4; size <= MAX_SIZE; size++) {
			char[] A = text.substring(0, size).toCharArray();
			driver.count = 0;
			long start = System.nanoTime();	// FIX: 작은 크기도 잴 수 있게 nanoTime 사용
			driver.perm(A, 0, false);
			long finish = System.nanoTime();
			System.out.println("size = " + size + ", 순열 수 = " + driver.count
					+ ", 실행 시간 = " + (finish - start) / 1e9 + "초");
		}

		// FIX: 원본은 출력이 주석 처리되어 아래 호출이 아무것도 보여주지 않았다.
		char[] A = {'a', 'b', 'c', 'd'};
		driver.perm(A, 0, true);
	}
}
