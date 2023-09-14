import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int ans, Scnt; //결과값, 7명 이어져있는지 체크할 값
    static boolean flag;
    static char[][] map = new char[5][5]; //입력 맵
    static int[][] dfsMap; //탐색을 위한 맵
    static int[] arr; //조합 배열
    static ArrayList<Point> list; //조합을 위한 좌표값 리스트
    static int[] dx = new int[] {1,-1,0,0}; //방향벡터
    static int[] dy = new int[] {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        list = new ArrayList<>();
        for(int i=0; i<5; i++) { //맵 입력
            String input = br.readLine();
            for(int j=0; j<5; j++) {
                char who = input.charAt(j);
                map[i][j] = who;
                list.add(new Point(i,j,who));
            }
        }
        arr = new int[7]; //7공주를 뽑을 조합배열
        Combination(0, 0);
        System.out.println(ans);
    }
    private static void Combination(int cnt, int start) {
        if(cnt == 7) {
            //조합 완성
            dfsMap = new int[5][5];
            for(int item : arr) { //새로운 탐색맵에 조합으로 뽑은 좌표들의 위치를 1로 세팅
                Point idx = list.get(item);
                dfsMap[idx.x][idx.y] = 1;
            }

            Point idx = list.get(arr[0]); //조합배열의 첫번째 좌표값으로 탐색 시작
            int x = idx.x;
            int y = idx.y;
            flag = false;
            dfsMap[x][y] = 3; //임의값으로 방문체크
            Scnt = 1; //시작좌표 카운트 증가
            dfs(x, y); //탐색
            if(flag) { //7명이 이어져있다면
                if (isValidate()) {
                    ans++;
                }
            }
        } else {
            for(int i=start; i<list.size(); i++) {
                arr[cnt] = i;
                Combination(cnt + 1, i + 1);
            }
        }
    }

//    private static void bfs(int x, int y) { //맵 상에 7개의 1값들이 연결되어있는지 탐색하는 bfs
//        Queue<int[]> queue = new ArrayDeque<>();
//        queue.offer(new int[] {x, y});
//        while(!queue.isEmpty()) {
//            int[] idx = queue.poll();
//            for (int i = 0; i < 4; i++) {
//                int nx = idx[0] + dx[i];
//                int ny = idx[1] + dy[i];
//                if (nx < 0 || ny < 0 || nx > 4 || ny > 4) continue;
//                if (dfsMap[nx][ny] == 1) {
//                    dfsMap[nx][ny] = 3;
//                    Scnt++;
//                    queue.offer(new int[]{nx, ny});
//                }
//            }
//        }
//        if(Scnt == 7) flag = true;
//    }
    private static void dfs(int x, int y) { //맵 상에 7개의 1값들이 연결되어있는지 탐색하는 dfs
        if(Scnt == 7) {
            flag = true;
        }
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny<0 || nx>4 || ny>4) continue;
            if(dfsMap[nx][ny] == 1) {
                dfsMap[nx][ny] = 3; //방문체크
                Scnt++; //전역변수로 증가시켜야 함
                dfs(nx, ny);
            }
        }
    }

    private static boolean isValidate() { //이다솜파가 최소 4명 포함되어있는지 체크
        int sCnt = 0;
        for(int item : arr) {
            Point idx = list.get(item);
            if(idx.who == 'S') sCnt++;
        }
        if(sCnt > 3) return true;
        return false;
    }

    static class Point { //리스트 관리를 위한 클래스 객체
        int x;
        int y;
        char who; //S인지 Y인지
        Point(int x, int y, char who) {
            this.x = x;
            this.y = y;
            this.who = who;
        }
    }
}
