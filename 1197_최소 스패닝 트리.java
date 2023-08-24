import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Edge implements Comparable<Edge>{
		int from, to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int V, E;
	static int[] parents;
	
	static void make() { //초기화 메서드
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) { //root노드 찾아주는 메서드
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) { //b의 root 변경 메서드
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		V = Integer.parseInt(st.nextToken()) + 1; //정점 1부터 시작
		E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(); //우선순위 큐 사용
		
		for(int i=0; i<E; i++) { //간선 및 가중치 입력
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.add(new Edge(from, to, weight));
		}
		
		//V개의 정점으로 make set 작업
		make();
		
		int result = 0; //MST 비용
		int cnt = 0; //연결된 간선 개수
		
		for(int i=0; i<pq.size(); i++) {
			Edge e = pq.poll();
			if(union(e.from, e.to)) { //사이클 형성 확인
				result += e.weight; //값 합산
				if(++cnt == V-1) break; //스패닝 트리 완성조건
			}
			i--;
		}
		System.out.println(result);
	}
}
