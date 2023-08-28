import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, M, range;
    static int ans = Integer.MAX_VALUE;
    static int num[], map[][], arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //정점개수

        num = new int[N]; //인구수
        map = new int[N][N]; //인접행렬

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) { //정점 별 인구 수 입력
            num[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<N; i++) { //인접행렬 입력
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            for(int j=0; j<M; j++) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                map[i][idx] = 1;
                map[idx][i] = 1;
            }
        }
        range = N/2; //조합을 위한 변수
        for(int i=1; i<=range; i++) {
            arr = new int[i]; //조합크기선언
            Combination(0, 0); //조합호출
        }
        if(ans == Integer.MAX_VALUE) System.out.println(-1); //인접된 곳이 없을 경우 -1
        else System.out.println(ans);
    }
    private static void Combination(int cnt, int start) { //조합메서드
        if(cnt == arr.length) {
            //조합완성
            //나머지 팀
            int team1[] = arr; //첫번째 선거구
            int team2[] = new int[N-arr.length]; //남은 두번째 선거구
            int idx = 0;
            int check = 0;
            for(int i=0; i<N; i++) { //team2 초기화
                for(int j=0; j<arr.length; j++) {
                    if(arr[j] == i) {
                        check = 1;
                        break;
                    }
                }
                if(check == 1) {
                    check = 0;
                    continue;
                }
                team2[idx++] = i;
                if(idx == N-arr.length) break;
            }
            //두 개의 선거구가 인접해있는지 확인
            if(!isValiable(team1)) return;
            if(!isValiable(team2)) return;
            
            //두 선거구의 인구를 합산
            int sum1 = 0;
            int sum2 = 0;
            for(int i=0; i<team1.length; i++) {
                sum1 += num[team1[i]];
            }
            for(int i=0; i<team2.length; i++) {
                sum2 += num[team2[i]];
            }
            ans = Math.min(ans, Math.abs(sum1 - sum2)); //최소값 갱신
        } else {
            for(int i=start; i<N; i++) {
                arr[cnt] = i;
                Combination(cnt + 1, i + 1);
            }
        }
    }
    //인접해있는지 확인해주는 메서드
    private static boolean isValiable(int[] arr) {
        if(arr.length == 1) return true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(arr[0]);
        boolean[] v = new boolean[N];
        v[arr[0]] = true;
        while(!queue.isEmpty()) { //bfs로 선거구의 각 구역이 인접해있는지 확인
            int idx = queue.poll();

            for(int item : arr) {
                if(map[idx][item] == 1 && !v[item]) {
                    queue.offer(item);
                    v[item] = true;
                }
            }
        }
        for(int item : arr) {
            if(!v[item]) return false; //선거구의 구역들 중 하나라도 방문이 안되있을 경우
        }
        return true;
    }
}
