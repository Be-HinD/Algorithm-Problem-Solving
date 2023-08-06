import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayDeque<Integer> q = new ArrayDeque<Integer>(); //ArrayDeque 선언
        if(N % 2 == 0) {
	        for(int i=2; i<=N; i += 2) { //공식 (2,4,6,8, ... , N 형식으로 정리 됨)
	        	q.add(i); //짝수일 경우에
	        }
        } else {
        	q.add(N);
        	for(int i=2; i<=N; i += 2) { //공식 (N, 2,4,6,8, N-1 형식으로 정리 됨)
	        	q.add(i); //짝수일 경우에
	        }
        }
        while(q.size() != 1) { //마지막 숫자 한개가 남을 때까지
        	q.pollFirst(); //앞부분 숫자 버리고
        	q.addLast(q.pollFirst()); //바로 뒤 숫자를 맨뒤에 넣음
        }
        System.out.println(q.poll()); //최종 남은 한개의 숫자가 결과
    }
}
