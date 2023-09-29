import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Person> personPQ = new PriorityQueue<>(Comparator.comparingInt(o -> o.s));
        StringTokenizer st;
        int s, e;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            personPQ.offer(new Person(s, e));
        }
        PriorityQueue<int[]> comPQ = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> comNumPQ = new PriorityQueue<>();
        int[] computers = new int[N + 1];
        int computerNum = 0;
        L:
        while (!personPQ.isEmpty()) {
            Person person = personPQ.poll();
            while (!comPQ.isEmpty() && person.s > comPQ.peek()[0]) {
                comNumPQ.offer(comPQ.poll()[1]);
            }
            if (!comNumPQ.isEmpty()) {
                int n = comNumPQ.poll();
                comPQ.offer(new int[]{person.e, n});
                computers[n]++;
            } else {
                comPQ.offer(new int[]{person.e, ++computerNum});
                computers[computerNum]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(computerNum).append("\n");
        for (int i = 1; i <= computerNum; i++) {
            sb.append(computers[i]).append(" ");
        }
        System.out.println(sb);
    }
    
    private static class Person {
        int s;
        int e;
        
        private Person(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}