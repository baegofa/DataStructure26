package ds_lecture_26;

public class Permutation {
    public void perm(char[] A, int k) {
        if (k == A.length - 1) {
//            for (int i = 0; i < A.length; i++)
//               System.out.print(A[i] + " ");
//            System.out.println();
        	return;
        }
        else {
            for (int i = k; i < A.length; i++) {
                swap(A, i, k);
                perm(A, k+1);
                swap(A, i, k);
            }
        }
    }

    private void swap(char[] A, int i, int k) {
        char temp = A[k];
        A[k] = A[i];
        A[i] = temp;
    }

    public static void main(String[] args) {
        Permutation driver = new Permutation();
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for (int size = 4; size < text.length(); size++) {
        	char[] A = new char[size];
        	for(int i =0; i<size; i++) 
        		A[i] = text.charAt(i);
        	long start = System.currentTimeMillis();
        	driver.perm(A,0);
        	long finish = System.currentTimeMillis();
			System.out.println("size = "+ size +", 실행 시간 = " + (finish - start)/1000.0 + "초");
        }
        char[] A = {'a', 'b', 'c', 'd'};
        driver.perm(A, 0);
    }
}
