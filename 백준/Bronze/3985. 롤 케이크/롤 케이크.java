import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		int[] wholeCake = new int[L];
		for (int i = 0; i < wholeCake.length; i++) {
			wholeCake[i] = 0;
		}
		int N = sc.nextInt();
		int[] getCake = new int[N];
		int guessLong = 0, longNum = 0;
		int getMost = 0, mostNum = 0;
		for (int i = 1; i <= N; i++) {
			int P = sc.nextInt();
			int K = sc.nextInt();
			if((K-P+1) > guessLong) {
				guessLong = K-P+1;
				longNum = i;
			}
			for (int j = P; j <= K; j++) {
				if(wholeCake[j-1] == 0) {
					wholeCake[j-1] = i;
					getCake[i-1]++;
				}
			}
			if(getCake[i-1] > getMost) {
				getMost = getCake[i-1];
				mostNum = i;
			}
		}
		System.out.printf("%d\n%d",longNum, mostNum);
	}
}