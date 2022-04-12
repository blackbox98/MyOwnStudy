package hw_20220412;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_9760 {

	static ArrayList<Card> cards;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("InOutFiles/SWEA_9760_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			cards = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 5; i++) {
				String s = st.nextToken();
				char suit = s.charAt(0);
				int rank = 0;
				switch (s.charAt(1)) {
				case 'A':
					rank = 1;
					break;
				case 'T':
					rank = 10;
					break;
				case 'J':
					rank = 11;
					break;
				case 'Q':
					rank = 12;
					break;
				case 'K':
					rank = 13;
					break;
				default:
					rank = s.charAt(1) - '0';
					break;
				}
				cards.add(new Card(suit, rank));
			}
			Collections.sort(cards);
			System.out.printf("#%d %s\n", tc, play());
		}
	}

	private static String play() {
		boolean flag1 = true;
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).suit != cards.get(i + 1).suit) {
				flag1 = false;
				break;
			}
		}
		
		boolean flag4 = false;
		if (flag1) {
			flag4 = true;
		}
		
		boolean flag5 = true;
		for (int i = cards.size() - 1; i >= 1; i--) {
			if (cards.get(i).rank - cards.get(i - 1).rank != 1) {
				if (cards.get(i).rank == 10 && cards.get(i - 1).rank == 1) {
					continue;
				}
				flag1 = false;
				flag5 = false;
				break;
			}
		}
		
		if (flag1) {
			return "Straight Flush";
		}
		
		int[] nums = new int[14];
		for (int i = 0; i < cards.size(); i++) {
			nums[cards.get(i).rank]++;
		}
		
		int cnt3 = 0;
		int cnt2 = 0;
		for (int i = 0; i < 14; i++) {
			if (nums[i] == 4) {
				return "Four of a Kind";
			}
			if (nums[i] == 3) {
				cnt3++;
			}
			if (nums[i] == 2) {
				cnt2++;
			}
		}
		if (cnt3 == 1 && cnt2 == 1) {
			return "Full House";
		}
		if (flag4) {
			return "Flush";
		}
		if (flag5) {
			return "Straight";
		}
		if (cnt3 == 1) {
			return "Three of a kind";
		}
		if (cnt2 == 2) {
			return "Two pair";
		}
		if (cnt2 == 1) {
			return "One pair";
		}
		return "High card";
	}

	static class Card implements Comparable<Card> {
		char suit;
		int rank;

		public Card(char suit, int rank) {
			this.suit = suit;
			this.rank = rank;
		}

		@Override
		public int compareTo(Card o) {
			return Integer.compare(this.rank, o.rank);
		}

		@Override
		public String toString() {
			return "Card [suit=" + suit + ", rank=" + rank + "]";
		}
	}
}