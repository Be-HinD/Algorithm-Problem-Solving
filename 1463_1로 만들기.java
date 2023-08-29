import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int ans = Integer.MAX_VALUE;
    static int N;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //정수 N

        dfs(0, N);
        System.out.println(ans); //결과 출력
    }

    private static void dfs(int cnt, int n) {
        if(ans<=cnt) return; //현재 cnt가 이전 값과 비교하여 높으면 더 이상 진행 불필요
        if(n < 2) { //기저조건
            ans = Math.min(ans, cnt); //최소값 비교 갱신
        } else {
            if(n%3==0) { //3으로 딱 나누어 떨어지면
                dfs(cnt +1, n/3); //3으로 나눈 값을 매개변수로 전달
            }
            if(n%2==0) {
                dfs(cnt +1, n/2);
            }
            dfs(cnt+1, n-1);
        }
    }
}
