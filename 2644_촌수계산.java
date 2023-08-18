import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M;
	static int x, y, nx, ny;
	static boolean[][] Map;
	static int[] visited; //방문체크 겸 깊이(촌수값)저장 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()) + 1; //사람 수 N
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken()); //출력을 위한 관계 x
		y = Integer.parseInt(st.nextToken()); //출력을 위한 관계 x
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); //입력 M
		Map = new boolean[N][N]; //맵 초기화
		visited = new int[N]; //방문배열 초기화
		for(int i=0; i<M; i++) { //맵 입력
			st = new StringTokenizer(br.readLine());
			nx = Integer.parseInt(st.nextToken());
			ny = Integer.parseInt(st.nextToken());
			Map[nx][ny] = true;
			Map[ny][nx] = true; //무향그래프임으로 반대도 연결
		}
		
		bfs(x);
		
		if(visited[y] == 0) System.out.println(-1); //해당 촌수에 방문한적이 없다면 깊이 -1 출력
		else System.out.println(visited[y] - 1); //해당 촌수에 방문했을 경우에 깊이 값 출력 + 방문체크를 위해 초기에 1을 주었기 때문에 -1을 한값이 정답
		
	}
	
	private static void bfs(int x) { //x : 촌수 중 하나의 값
		Queue<Integer> q = new LinkedList<>(); //큐생성
		q.offer(x); //촌수 시작값 offer
		visited[x] = 1; //방문체크를 위해 1 삽입
		
		while(!q.isEmpty()) { //큐가 빌 때까지
			int id = q.poll(); //큐에서 하나의 노드값을 꺼내고
			for(int i =0; i<N; i++) { //해당 노드와 연결되어 있는 노드의 번호를 탐색
				if(Map[id][i] && visited[i] == 0) { //방문하지 않고 해당 노드가 연결되어있다면
					q.offer(i); //큐에 삽입
					visited[i] = visited[id] + 1; //해당 큐와 같은 visited배열에 현재 깊이 +1의 값을 추가
				}
			}
		}
	}
}
