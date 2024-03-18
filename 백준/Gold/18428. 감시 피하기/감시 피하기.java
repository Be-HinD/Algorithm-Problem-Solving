import java.io.*;
import java.util.*;

//BOJ_18428 감시 피하기
public class Main {
    static int N;
    static char[][] map;
    static int[] arr;
    static List<int[]> list, teacher;
    static boolean flag;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        list = new ArrayList<>();
        teacher = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'X') {
                    list.add(new int[]{i,j});
                }
                if(map[i][j] == 'T') {
                    teacher.add(new int[]{i,j});
                }
            }
        }


        //선생 == T
        //학생 == S
        //장애물 == O

        //존나 슈퍼 이지한 문제
        /*
        * 접근법
        * 장애물 무조건 3개 설치 == 조합으로 장애물 설치
        * N == 6이니까 3중 for문이 유리
        * 장애물 설치 이후 go() 메서드 실행
        * go() : 선생들의 위치로부터 학생들을 탐색
        *
        * */

        arr = new int[3];
        Comb(0, 0);

        System.out.println(flag ? "YES" : "NO");

    }

    private static void Comb(int start, int cnt) {
        if(cnt == 3) {
            //학생 탐색
            for(int i=0; i<arr.length; i++) {
                int[] wall = list.get(arr[i]);
                map[wall[0]][wall[1]] = 'O';
            }
            if(go()) {
                flag = true;
                return;
            }
            for(int i=0; i<arr.length; i++) {
                int[] wall = list.get(arr[i]);
                map[wall[0]][wall[1]] = 'X';
            }
            return;
        }

        for(int i=start; i<list.size(); i++) {
            arr[cnt] = i;
            Comb(i+1, cnt+1);
        }
    }

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    private static boolean go() {

        for(int i=0; i<teacher.size(); i++) {

            int[] cur = teacher.get(i);
            //4방 탐색 ㄱㄱ
            int nx = cur[0];
            int ny = cur[1];

            while(nx < N) {
                if(map[nx][ny] == 'S') {
                    return false;
                }
                else if(map[nx][ny] == 'O') break;
                nx += dx[0];
            }

            nx = cur[0];

            while(nx >= 0) {
                if(map[nx][ny] == 'S') {
                    return false;
                }
                else if(map[nx][ny] == 'O') break;
                nx += dx[1];
            }

            nx = cur[0];

            while(ny < N) {
                if(map[nx][ny] == 'S') {
                    return false;
                }
                else if(map[nx][ny] == 'O') break;
                ny += dy[2];
            }

            ny = cur[1];

            while(ny >= 0) {
                if(map[nx][ny] == 'S') {
                    return false;
                }
                else if(map[nx][ny] == 'O') break;
                ny += dy[3];
            }
        }

        return true;
    }
}