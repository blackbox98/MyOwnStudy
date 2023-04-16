import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");
        int N = Integer.parseInt(strArr[0]);
        int M = Integer.parseInt(strArr[1]);
        int[][] A = new int[N][M];
        int[][] B = new int[N][M];
        initArr(A, N, M, br);
        initArr(B, N, M, br);
        int[][] answer = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer[i][j] = A[i][j] + B[i][j];
            }
        }
        printArr(answer, N, M);
    }

    private static void initArr(int[][] arr, int N, int M, BufferedReader br) throws IOException {
        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(tmp[j]);
            }
        }
    }
    
    private static void printArr(int[][] answer, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }
}
