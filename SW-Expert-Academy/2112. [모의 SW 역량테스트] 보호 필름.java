import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T, D, W, K, Min;
    static int[] ans, arr;
    static int[][] map, copyMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) { //TestCase
            Min = 0; //결과값 초기화
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[D][W];
            for(int i=0; i<D; i++) { //맵 입력
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            arr = new int[D]; //조합뽑을 배열 초기화
            copyMap = new int[D][W];
            for(int i=0; i<D; i++) { //깊은복사
                copyMap[i] = map[i].clone();
            }
            if(logic()) { //원본맵에서 필름 테스트 통과할 경우
                System.out.println("#" + (tc+1) + " " + 0);
            }
            else {
                for (int i = 1; i < D; i++) { //행의 개수를 증가시키면서 조합시도
                    ans = new int[i];
                    Combination(0, 0);
                }
                System.out.println("#" + (tc+1) + " " + Min);
            }

        }

    }
    private static void Combination(int cnt, int start) {
        if(cnt == ans.length) {
            //약품 행 조합 완성
            //카피배열 생성
            copyMap = new int[D][W];
            for(int i=0; i<D; i++) { //깊은복사
                copyMap[i] = map[i].clone();
            }
            //약품 스프레드
            dfs(0, 0 );
        } else {
            for(int i=start; i<arr.length; i++) {
                ans[cnt] = i;
                Combination(cnt+1, i+1);
            }
        }

    }
    private static void dfs(int cnt, int idx) {
        if(Min != 0) return; //Min이 갱신된 경우 백트래킹
        //기저조건
        if(cnt == ans.length) {
            if(logic()) { //약품테스트 통과 시
                Min = ans.length;
            }
        } else {
            spreadA(ans[idx]); //한번은 A로
            dfs(cnt+1, idx+1);

            spreadB(ans[idx]); //한번은 B로
            dfs(cnt+1, idx+1);
        }
    }

    private static void spreadA(int row) {
        for(int i=0; i<W; i++) {
            copyMap[row][i] = 0;
        }
    }
    private static void spreadB(int row) {
        for(int i=0; i<W; i++) {
            copyMap[row][i] = 1;
        }
    }
    private static boolean logic() { //보호필름 테스트 로직
        for(int i=0; i<W; i++) {
            int cnt = 0;
            boolean flag = false;
            int diff = copyMap[0][i];
            cnt++;
            if(cnt >= K) return true; //K가 1일경우 체크안해주면 49개에서 fail
            for(int j=1; j<D; j++) { //각 행마다 K개 통과하는지 체크
                if(diff == copyMap[j][i]) { //같은 특성이라면
                    cnt++;
                    if(cnt >= K) {
                        flag = true;
                        break;
                    }
                }
                else { //다른 특성일 경우 shift
                    diff = copyMap[j][i];
                    cnt = 1;
                }
            }
            if(!flag) return false;
        }
        return true;
    }
}
