import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { //{1,2,3,4}집합의 모든 부분집합
    static int N, mid, Min, start_team, link_team; //N : 배열크기, mid : 나뉘는 팀 수, Min : 결과값, start_team/link_team : 각 팀의 능력치
    static int[][] map; //입력 2차원 맵
    static boolean[] visited; //재귀 조합을 위한 방문체크배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        map = new int[N][N];
        mid = N / 2;
        //2차원 배열 읿력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //최소값 비교를 위한 초기화
        Min = Integer.MAX_VALUE;
        combination(0, 0);
        System.out.println(Min); //결과 출력
    }

    private static void combination(int cnt, int start) { //N개의 팀 수에서 N/2개 팀의 조합
        if (cnt == mid) { //조합 생성 완료
            //동작부
            calculator();
        } else {
            for (int i = start; i < visited.length; i++) { //visited 배열로만 조합을 구하기 때문에 인덱스가 cnt가 아닌 현재 위치 i
                if (visited[i]) continue;
                visited[i] = true;
                combination(cnt + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    private static void calculator() { //두 팀의 능력치 합을 구하고 비교하는 메서드
        start_team = 0; //팀 능력치 초기화
        link_team = 0;
        for (int i = 0; i < visited.length; i++) { //2중 for문을 통해 true false를 비교하고 해당하는 각 팀의 능력치 합을 구해줌
            for (int j = i + 1; j < visited.length; j++) {
                if (visited[i] == true && visited[j] == true) {
                    start_team += map[i][j];
                    start_team += map[j][i];
                }
                if (visited[i] == false && visited[j] == false) {
                    link_team += map[i][j];
                    link_team += map[j][i];
                }
            }
        }

        int ans = Math.abs(start_team - link_team); //두 팀 능력치 차이
        if (ans == 0) { //0이라면 더이상 최소값이 없기 때문에 결과 출력 및 종료
            System.out.println(0);
            System.exit(0);
        }
        Min = Math.min(Min, ans); //기존 Min값과 비교 후 갱신
    }
}
