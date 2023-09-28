import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int ans;
    static int[][] map;
    static int[] arr = new int[]{5,5,5,5,5,5}; //색종이 보유 현황을 관리할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[10][10];
        for(int i=0; i<10; i++) { //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][j] = idx;
            }
        }
        ans = Integer.MAX_VALUE; //최소값 비교를 위한 초기화
        dfs(0, 0, 0);
        if(ans == Integer.MAX_VALUE) System.out.println(-1); //전부 덮지 못할 경우
        else System.out.println(ans);
    }

    private static void dfs(int x, int y, int cnt) {
        if(cnt >= ans) return; //백트래킹 : 현재 최소값보다 커질 경우 탐색필요 없음.
        if(x==9 && y==9 && map[x][y] == 0) { //마지막 좌표에 도착 및 마지막 좌표가 0일 경우(반례 가능) : 1이 적힌 칸을 모두 덮은 상황
            ans = Math.min(ans, cnt); //최소값 갱신
            return;
        }
        if(map[x][y] == 0) { //현재좌표가 0일 경우 다음 좌표로 이동
            if((y+1) == 10) { //Null 예외처리 : 나누기 및 모듈러 연산으로 좌표값 참조가능(연습 필요)
                dfs(x+1, 0, cnt);
            } else {
                dfs(x, y+1, cnt);
            }
        } else { //현재좌표가 1일 경우
            for (int k = 5; k > 0; k--) { //그리디한 사고 : 큰 색종이부터 사용하는게 최소일 확률이 높음.
                if (check(x, y, k)) { //k*k 색종이를 사용할 수 있는지 체크
                    if (arr[k] > 0) { //k색종이가 남아있는지 체크
                        logic(x, y, k); //색종이 덮기
                        arr[k] -= 1; //색종이 1개 감소
                        dfs(x, y, cnt + 1);
                        reverseLogic(x, y, k); //백트래킹
                        arr[k] += 1; //백트래킹
                    }
                }
            }
        }
    }

    private static boolean check(int x, int y, int n) { //n*n 범위가 전부 1인지 체크
        if((x+n) > 10) return false;
        if((y+n) > 10) return false;
        for(int i=x; i<(x+n); i++) {
            for(int j=y; j<(y+n); j++) {
                if(map[i][j] == 0) return false;
            }
        }
        return true;
    }
    private static void logic(int x, int y, int n) { //색종이 덮는 로직
        for(int i=x; i<(x+n); i++) {
            for(int j=y; j<(y+n); j++) {
                map[i][j] = 0;
            }
        }
    }

    private static void reverseLogic(int x, int y, int n) { //색종이 덮은 곳 백트래킹
        for(int i=x; i<(x+n); i++) {
            for(int j=y; j<(y+n); j++) {
                map[i][j] = 1;
            }
        }
    }
}
