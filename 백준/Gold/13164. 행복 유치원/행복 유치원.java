import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n, k;
    static int[] arr;

    public static void solve() {
        // 각 조에는 적어도 한명의 원생이 있어야함, 서로 인접한 원생들끼리 같은 조여야함
        // 조별로 인원이 같을필요는 없음, 가장 큰 원생과 가장 작은 원생의 키 차이만큼 비용이 발생함
        // 합을 최소로 하고싶음

        int[] S = new int[n-1];
        for(int i = 0;i<n-1;i++) {
            S[i] = arr[i+1] - arr[i];
        }
        // 모든 차를 구하고, 어짜피 K개로 나누어진 각 조에서 차가 좁혀질라면
        // N/K + (N % K == 0 ? 1 : 0)
        Arrays.sort(S);
        long sum = 0;
//        int count = n/k + (n % k != 0 ? 1 : 0);
        int count = n-k;
        int idx = 0;
        while(count-- > 0 && idx < n-1) sum += S[idx++];
        System.out.print(sum);

    }
    public static void input() throws Exception {

        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0;i<n;i++) arr[i] = Integer.parseInt(stk.nextToken());
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }


}