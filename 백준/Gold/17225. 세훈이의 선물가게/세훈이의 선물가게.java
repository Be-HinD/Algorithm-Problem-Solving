import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int S, G, N;

	static class Pair implements Comparable<Pair> {
		int star;
		char color;

		public Pair(int star, char color) {
			// TODO Auto-generated constructor stub
			this.star = star;
			this.color = color;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.star == o.star) {
				return o.color-this.color;
			}
			return this.star - o.star;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		S = Integer.valueOf(st.nextToken());
		G = Integer.valueOf(st.nextToken());
		N = Integer.valueOf(st.nextToken());
		int t, c, num;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int bmax = 0;
		int amax = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			t = Integer.valueOf(st.nextToken());
			c = st.nextToken().charAt(0);
			num = Integer.valueOf(st.nextToken());
			for (int j = 0; j < num; j++) {
				if (c == 'B') {
					if (bmax >= t) {
						pq.add(new Pair(bmax, 'b'));
						bmax+=S;
					}else {
						pq.add(new Pair(t, 'b'));
						bmax = t+S;
					}
				}else {
					if(amax >= t) {
						pq.add(new Pair(amax, 'a'));
						amax+=G;
					}else {
						pq.add(new Pair(t, 'a'));
						amax = t+G;
					}
				}
			}
		}
		ArrayList<Integer> blue = new ArrayList<>();
		ArrayList<Integer> red = new ArrayList<>();
		int idx=1;
		while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			if(cur.color=='a') {
				red.add(idx);
			}else {
				blue.add(idx);
			}
			idx++;
		}
		System.out.println(blue.size());
		for(int k : blue) 
			System.out.print(k+" ");
		System.out.println();
		System.out.println(red.size());
		for(int k : red) 
			System.out.print(k+" ");
		System.out.println();
	}
}