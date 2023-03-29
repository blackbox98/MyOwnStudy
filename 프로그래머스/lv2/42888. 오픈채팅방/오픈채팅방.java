import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> user = new HashMap<>();
        Queue<Log> logs = new LinkedList<>();
        for (String log : record) {
            String[] logInfo = log.split(" ");
            String state = logInfo[0];
            String uid = logInfo[1];
            switch (state) {
                case "Enter":
                    user.put(uid, logInfo[2]);
                    logs.offer(new Log(uid, state));
                    break;
                case "Leave":
                    logs.offer(new Log(uid, state));
                    break;
                case "Change":
                    user.put(uid, logInfo[2]);
                    break;
            }
        }
        String[] answer = new String[logs.size()];
        for (int i = 0; i < answer.length; i++) {
            Log log = logs.poll();
            answer[i] = String.format("%s님이 %s", user.get(log.uid), log.state);
        }
        return answer;
    }
    
    private static class Log {
        String uid;
        String state;

        public Log(String uid, String state) {
            this.uid = uid;
            String translation = "";
            switch (state) {
                case "Enter":
                    translation = "들어왔습니다.";
                    break;
                case "Leave":
                    translation = "나갔습니다.";
                    break;
            }
            this.state = translation;
        }
    }
}