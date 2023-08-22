import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean visited[];
	static ArrayList<ArrayList<Integer>> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>(); //인접리스트 생성
		for(int i=0; i<N; i++) list.add(new ArrayList<>()); //인접리스트 초기화
		
		for(int i=0; i<M; i++) { //입력값 받고 무향그래프 초기화
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.get(x).add(y);
			list.get(y).add(x);
		}
		
		for(int i=0; i<N; i++) { //각 정점에서부터 출발
			visited = new boolean[N]; //방문배열 매번 초기화
			visited[i] = true;
			dfs(i, 0); //dfs로 i번째 정점에서부터 탐색
		}
		System.out.println(0); //종료가 되지않는다면 0
	}
	
	private static void dfs(int idx, int cnt) {
		if(cnt > 3) { //4개의 친구(정점)이 연결되어 있다면 조건만족
			System.out.println(1);
			System.exit(0);
		}
		
		for(int i=0; i<list.get(idx).size(); i++) { //i정점에 연결된 인접정점개수만큼
			int item = list.get(idx).get(i); //인접정점 한개씩 꺼내서
			if(!visited[item]) { //방문비교
				visited[item] = true;
				dfs(item, cnt + 1);
				visited[item] = false; //백트래킹
			}
		}
	}
}
