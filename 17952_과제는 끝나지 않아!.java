import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main {
	static int N, sec, A, T, idx, ans;
	static work w;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<work> stack = new Stack<>();
		N = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			sec = Integer.parseInt(st.nextToken());
			if(sec == 0) { //일이 들어오지 않았을 때
				if(stack.isEmpty()) continue; //스택이 비어있다면 다음 반복으로
				else { //스택에 일이 남아있을 때
					w = stack.peek(); //제일 최근 일을 꺼내서 시간단축
					w.T -= 1;
					if(w.T == 0) { //일이 끝나면
						stack.pop(); //스택제거
						ans += w.A; //결과 합산
					}
					continue;
				}
			} else { //일이 들어왔을 때
				A = Integer.parseInt(st.nextToken());
				T = Integer.parseInt(st.nextToken());
				if((T-1) == 0) { //바로 끝나는 일
					ans += A;
				} else {
					stack.push(new work(sec, A, T-1));
				}
			}
		}
		System.out.println(ans); //결과 출력
	}
}
class work { //일이 들어왔을 경우 정보 저장할 수 있는 클래스
	int sec;
	int A;
	int T;
	public work(int sec, int a, int t) {
		super();
		this.sec = sec;
		A = a;
		T = t;
	}
}
