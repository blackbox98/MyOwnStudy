class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        m = m.replace("A#", "a")
                .replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g");
        int answerTime = 0;
        for (String song : musicinfos) {
            String[] songInfo = song.split(",");
            String[] start = songInfo[0].split(":");
            int startHour = Integer.parseInt(start[0]);
            int startMinute = Integer.parseInt(start[1]);
            String[] end = songInfo[1].split(":");
            int endHour = Integer.parseInt(end[0]);
            int endMinute = Integer.parseInt(end[1]);
            int playTime = ((endHour - startHour) * 60) + (endMinute - startMinute);
            String title = songInfo[2];
            String music = songInfo[3].replace("A#", "a")
                    .replace("C#", "c")
                    .replace("D#", "d")
                    .replace("F#", "f")
                    .replace("G#", "g");
            StringBuilder musicFullPlayed = new StringBuilder();
            for (int time = 0; time < playTime; time++) {
                musicFullPlayed.append(music.charAt(time % music.length()));
            }
            if (musicFullPlayed.toString().contains(m)) {
                if (answerTime < playTime) {
                    answer = title;
                    answerTime = playTime;
                }
            }
        }
        return answer;
    }
}