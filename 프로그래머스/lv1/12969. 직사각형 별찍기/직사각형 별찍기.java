import java.io.*;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
}