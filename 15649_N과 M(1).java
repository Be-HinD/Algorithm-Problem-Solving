import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static boolean[] visited;
	static int memory;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		visited = new boolean[N+1];
		recursion(0);
	}
	static void recursion(int cnt) { //cnt 자리수
		if(cnt == M) {
			for(int i=0; i<arr.length;i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
		else {
			for(int i=1; i<N+1; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				arr[cnt] = i;
				recursion(cnt + 1);
				visited[i] = false;
				
			}
		}
		
		
	}
}
