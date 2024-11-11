import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_14889
public class Main {
    static int N, res;
    static int[] start, link;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        start = new int[N/2];
        link = new int[N/2];

        res = Integer.MAX_VALUE;
        Combination(0, 0);

        System.out.println(res);
        //N == 20 ? 웬만하면 완전탐색
        //N/2 명의 2개의 팀 -> 모든 조합을 다 찾아야함. 같은 집합에 순서가 바뀌는 경우는 어차피 동일한 값이기 때문에 고려 X
        //N == 짝수
    }

    private static void Combination(int next, int cnt) {
        if(cnt == N/2) {
            //해당 팀 조합에 대해서 차이값 탐색
            searchDiff();
            return;
        }
        for(int i=next; i<N; i++) {
            start[cnt] = i;
            Combination(i+1, cnt+1);
        }
    }

    private static void searchDiff() {
        int startTeam = 0, linkTeam = 0;
        Set<Integer> startSet = new HashSet<>();
        for(int i=0; i<N/2; i++) {
            startSet.add(start[i]);
            for(int j=0; j<N/2; j++) {
                if(i == j) continue;
                startTeam += arr[start[i]][start[j]];
            }
        }

        for(int i=0; i<N; i++) {
            if(startSet.contains(i)) continue;
            for(int j=0; j<N; j++) {
                if(startSet.contains(j)) continue;
                if(i == j) continue;
                linkTeam += arr[i][j];
            }
        }

        res = Math.min(res, Math.abs(startTeam - linkTeam));
    }
}