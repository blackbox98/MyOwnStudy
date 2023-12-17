import java.util.*;

class Solution {
    public class Job implements Comparable<Job> {
        int request;
        int required;

        public Job(int[] job) {
            this.request = job[0];
            this.required = job[1];
        }

        @Override
        public int compareTo(Job o) {
            return this.required - o.required;
        }
    }
    
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));
        int answer = 0;
        int cnt = 0;
        int idx = 0;
        int curTime = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>();
        while (cnt < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= curTime) pq.offer(new Job(jobs[idx++]));
            if (pq.isEmpty()) curTime = jobs[idx][0];
            else {
                Job job = pq.poll();
                curTime += job.required;
                answer += curTime - job.request;
                cnt++;
            }
        }
        return answer / jobs.length;
    }
}