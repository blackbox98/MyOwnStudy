import java.util.*;

class Solution {
    public class Genre {
        int totalPlay = 0;
        PriorityQueue<Song> pq = new PriorityQueue<>();
        
        public Genre() {
            super();
        }
        
        public void addSong(Song song) {
            this.totalPlay += song.play;
            this.pq.offer(song);
        }
    }
    
    public class Song implements Comparable<Song> {
        int idx;
        int play;
        
        public Song(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
        
        @Override
        public int compareTo(Song o) {
            if (this.play == o.play) return this.idx - o.idx;
            else return o.play - this.play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            Genre genre = map.getOrDefault(genres[i], new Genre());
            genre.addSong(new Song(i, plays[i]));
            map.put(genres[i], genre);
        }
        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2).totalPlay - map.get(o1).totalPlay);
        List<Integer> list = new ArrayList<>();
        for (String genre : keySet) {
            PriorityQueue<Song> pq = map.get(genre).pq;
            for (int i = 0; !pq.isEmpty() && i < 2; i++) {
                list.add(pq.poll().idx);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}