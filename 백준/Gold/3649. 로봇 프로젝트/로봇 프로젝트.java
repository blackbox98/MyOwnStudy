import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String check;
        while ((check = br.readLine()) != null) {
            int x = Integer.parseInt(check) * 10000000;
            int n = Integer.parseInt(br.readLine());
            int[] lego = new int[n];
            for (int i = 0; i < n; i++) {
                lego[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(lego);
            boolean result = false;
            int left = 0;
            int right = n - 1;
            int sum;
            if (n < 2) left = n;
            while (left < right) {
                sum = lego[left] + lego[right];
                if (x == sum) {
                    result = true;
                    break;
                } else if (x > sum) left++;
                else right--;
            }
            if (result) {
                System.out.println("yes " + lego[left] + " " + lego[right]);
            } else System.out.println("danger");
        }
    }
}