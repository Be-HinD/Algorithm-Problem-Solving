import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; //입력값
    static int[][] map; //입력 2차원배열
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N]; //크기 초기화
        for(int i=0; i<N; i++) { //입력
        	st = new StringTokenizer(br.readLine());
        	String s = st.nextToken();
        	for(int j=0; j<N; j++) {
        		map[i][j] = s.charAt(j) - '0'; //문자열이 붙어있으므로 charAt()메서드 사용
        	}
        }
        quad(0, 0, map.length); //분할정복
	}
	
	private static void quad(int r, int c, int size) { //매개변수로 시작 좌표 및 size
		if(check(r, c, size)) {
			if(map[r][c] == 1) {
				System.out.print(1);
			} else {
				System.out.print(0);
			}
			return;
		}
		//압축 불가 4분할 재귀호출
		System.out.print("("); //분할시점에 여는 괄호 추가
		size = size/2; //size 1/2
		quad(r, c, size);
		quad(r, c + size, size);
		quad(r+size, c, size);
		quad(r+size, c+size, size);
		System.out.print(")"); //분할이 끝난 후 닫는 괄호로 분할종료
	}
	
	private static boolean check(int r, int c, int size) { //1 또는 0 탐색
		//탐색 1과 0이 있는지
		int diff = map[r][c]; //비교할 첫 좌표의 값
		for(int i=r; i<r + size; i++) {
			for(int j=c; j<c + size; j++) {
				if(map[i][j] != diff) return false; //1과 0이 섞여있을 경우 분할 필요
			}
		}
		return true; //같을 경우 분할x
	}
}
