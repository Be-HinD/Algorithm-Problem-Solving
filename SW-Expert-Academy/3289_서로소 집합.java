import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int tc, N, M;
	static int parents[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		tc = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=tc; t++) { //TestCase
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //정점 개수 N
			M = Integer.parseInt(st.nextToken()); //Union 입력 개수 M
			
			parents = new int[N]; //대표자 배열 선언
			MakeSet(); //대표자 배열 초기화
			
			for(int i=0; i<M; i++) { //입력 M에 따른 Union 연산
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken()); //합집합 연산 or 포함여부 확인
				int x = Integer.parseInt(st.nextToken()) - 1; //0부터 시작하기 때문에 -1
				int y = Integer.parseInt(st.nextToken()) - 1; //0부터 시작하기 때문에 -1
				if(c == 0) { //합집합 연산
					Union(x, y);
				} else { //포함여부 확인
					int idx1 = FindSet(x);
					int idx2 = FindSet(y);
					if(idx1 == idx2) sb.append(1); //값 비교 후 같으면 1
					else sb.append(0); //다르면 0
				}
			}
			System.out.println("#" + t + " " + sb); //결과 출력
		}
	}
	
	private static void MakeSet() {
		parents = new int[N];
		for(int i=0; i<N; i++) {
			parents[i] = i; //모든 요소는 자기만 원소로 갖는 단위 서로소 집합이 되게 한다.(자신이 곧 대표자인 루트로 표현)
		}
	}
	
	private static int FindSet(int x) {
		if(x == parents[x]) return x; //기저조건
		return parents[x] = FindSet(parents[x]);
	}
	
	private static boolean Union(int x, int y) { //x가 속한 집합과 y가 속한집합을 합칠 수 있다면 합치고 true 반환, 아니면 false 반환
		int xRoot = FindSet(x);
		int yRoot = FindSet(y);
		
		if(xRoot == yRoot) return false; //서로의 대표자가 같은 즉, 같은 집합의 상황이므로 union하지 않음
		//두 집합의 대표자가 다를 경우 Union 처리(yRoot를 xRoot 아래로 붙이기)
		parents[yRoot] = xRoot;
		return true;
	}
}
