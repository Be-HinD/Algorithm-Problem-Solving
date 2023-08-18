import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M;
	static int x, y, nx, ny;
	static boolean[][] Map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()) + 1; //노드 수 N+1
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken()); //촌수관계 x
		y = Integer.parseInt(st.nextToken()); //촌수관계 y
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); //입력 M
		Map = new boolean[N][N]; //맵 할당
		visited = new boolean[N]; //방문배열 할당
		
		for(int i=0; i<M; i++) { //양방향 그래프 생성
			st = new StringTokenizer(br.readLine());
			nx = Integer.parseInt(st.nextToken());
			ny = Integer.parseInt(st.nextToken());
			Map[nx][ny] = true;
			Map[ny][nx] = true;
		}
		
		bfs(x);
	}
	
	private static void bfs(int x) { //시작 노드
		Queue<Index> q = new LinkedList<>(); //큐 생성
		Index idx = new Index(x, 0); //객체 생성
		q.offer(idx); //객체를 큐에 삽입
		visited[x] = true; //방문체크
		
		while(!q.isEmpty()) {
			Index id = q.poll();
			if(id.idx == y) { //꺼낸 촌수관계가 입력과 일치하면
				System.out.println(id.cnt); //촌수값 출력
				System.exit(0); //종료
			}
			for(int i =0; i<N; i++) { //완전탐색
				if(Map[id.idx][i] && !visited[i]) { //연결되있는 노드 && 방문하지 않은 노드
					Index newid = new Index(i, id.cnt + 1); //노드 번호 및 촌수값 객체 생성
					q.offer(newid); //객체 삽입
					visited[i] = true; //방문체크
				}
			}
		}
		System.out.println(-1); //연결되지 않은 촌수관계일 경우
	}
}
class Index { //DFS와 같이 매개변수처럼 촌수값을 관리하기 위한 클래스
	int idx; //노드 번호
	int cnt; //촌수값
	public Index(int idx, int cnt) {
		super();
		this.idx = idx;
		this.cnt = cnt;
	}
}
