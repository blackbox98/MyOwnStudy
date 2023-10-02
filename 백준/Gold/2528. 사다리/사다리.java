import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, L, cur_stair, res;
	static boolean flag = false;

	static class Node {
		int be, fin, dir;

		Node(int be, int fin, int dir) {
			this.be = be;
			this.fin = fin;
			this.dir = dir;
		}
	}

	static Node node[];

	static void input() throws IOException {
		st = new StringTokenizer(in.readLine());
		N = Integer.valueOf(st.nextToken());
		L = Integer.valueOf(st.nextToken());
		node = new Node[N + 1];
		cur_stair = 1;
		res = -1;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			int l = Integer.valueOf(st.nextToken());
			int dir = Integer.valueOf(st.nextToken());
			if (dir == 0) {
				node[i] = new Node(0, l, dir);
			} else {
				node[i] = new Node(L - l, L, dir);
			}
		}
	}

	static void up() {
		while (true) {
			if (cur_stair == N) {
				flag = true;
				break;
			}
			Node cur = node[cur_stair];
			Node next = node[cur_stair + 1];
			if ((cur.fin >= next.be && cur.fin <= next.fin) || cur.be >= next.be && cur.be <= next.fin) {
				cur_stair++;
			} else if (cur.be >= next.be && cur.fin <= next.fin) {
				cur_stair++;
			} else if (cur.be < next.be && cur.fin > next.fin) {
				cur_stair++;
			} else
				break;
		}
	}

	static void move() {
		for (int i = 1; i <= N; i++) {
			Node cur = node[i];
			if (cur.dir == 0) {
				cur.be++;
				cur.fin++;
				if (cur.fin == L) {
					cur.dir = 1;
				}
			} else {
				cur.be--;
				cur.fin--;
				if (cur.be == 0) {
					cur.dir = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		while (true) {
			res++;
			up();
			move();
			if(flag) {
				System.out.print(res);
				break;
			}
		}
	}
}