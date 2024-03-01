import java.io.*;
import java.util.*;

class Solution {
    int[][] dice;
    static int n, max;
    static int[] answer;
    static List<Integer> listA, listB;
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        n = dice.length;
        max = 0;
        answer = new int[n / 2];
        combination(new int[n / 2], new boolean[n], 0, 0);
        return answer;
    }
    
    public void combination(int[] A, boolean[] v, int idx, int depth) {
        if (depth == n / 2) {
            int[] B = new int[n / 2];
            int idxA = 0;
            int idxB = 0;
            for (int i = 0; i < n; i++) {
                if (idxA < n / 2 && A[idxA] == i) {
                    idxA++;
                    continue;
                }
                B[idxB++] = i;
            }
            compareDice(A, B);
            return;
        }

        for (int i = idx; i < n; i++) {
            if (v[i]) continue;
            v[i] = true;
            A[depth] = i;
            combination(A, v, i + 1, depth + 1);
            v[i] = false;
        }
    }

    public void compareDice(int[] A, int[] B) {
        listA = new ArrayList<>();
        listB = new ArrayList<>();
        permutation(listA, A, 0, 0);
        permutation(listB, B, 0, 0);
        Collections.sort(listA);
        Collections.sort(listB);
        int win = 0;
        for (int sumA : listA) {
            int left = 0;
            int right = listA.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (sumA > listB.get(mid)) left = mid + 1;
                else right = mid;
            }
            win += left;
        }
        if (max < win) {
            max = win;
            for (int i = 0; i < n / 2; i++) {
                answer[i] = A[i] + 1;
            }
        }
    }

    public void permutation(List<Integer> list, int[] arr, int idx, int sum) {
        if (idx == n / 2) {
            list.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            permutation(list, arr, idx + 1, sum + dice[arr[idx]][i]);
        }
    }
}