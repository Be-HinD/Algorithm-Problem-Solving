import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_9328 열쇠
public class Main {
    static int T, N, M, initCnt, res;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static boolean[][] v, doors;
    static char[] keyArr;
    static char[][] map;
    static List<Character> list;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        //주어진 맵에서 테두리 탐색으로 '.', '$', 열쇠, 문들에 대해 사전처리 및 큐 추가
        //이 후 열쇠 리스트에 대해 갈 수 있는 곳들 문 방문배열(doors)확인 후 큐 추가
        //BFS
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) { //TestCase

            res = 0; //먹은 문서 개수 초기화
            initCnt = 0; //초기 테두리 문서 갯수


            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //너비
            M = Integer.parseInt(st.nextToken()); //높이

            map = new char[N][M];
            for(int i=0; i<N; i++) { //맵 입력
                String input = br.readLine();
                for(int j=0; j<M; j++) {
                    map[i][j] = input.charAt(j);
                }
            }

            //시작 열쇠 입력
            String key = br.readLine();
            if(!key.equals("irony")) {
                keyArr = key.toCharArray();
            }
            //열쇠 리스트 초기화
            list = new ArrayList<>();
            for(char idx : keyArr) {
                list.add(idx);
            }

            //방문 배열 2개 초기화
            v = new boolean[N][M];
            doors = new boolean[N][M];

            //테두리 갈 수 있는 곳들에 대해서 큐에 추가
            q = new ArrayDeque<>();
            int cnt = 0;
            for(int i=0; i<M; i++) {
                //*** 위쪽 테두리 ***
                if(map[0][i] == '.') { //빈 칸
                    v[0][i] = true;
                    q.offer(new int[]{0, i});
                }
                else if(Character.isLowerCase(map[0][i])) { //열쇠
                    v[0][i] = true;
                    list.add(map[0][i]);
                    map[0][i] = '.';
                    q.offer(new int[]{0,i});
                }
                else if(Character.isUpperCase(map[0][i])) {//문
                    char item = Character.toUpperCase(map[0][i]);
                    if(list.contains(item)) { //해당 문에 대한 열쇠가 존재한다면
                        v[0][i] = true;
                        map[0][i] = '.';
                        q.offer(new int[]{0,i});
                    } else {
                        doors[0][i] = true;
                    }
                }
                else if(map[0][i] == '$') { //문서
                    v[0][i] = true;
                    map[0][i] = '.';
                    initCnt++;
                    q.offer(new int[]{0,i});
                }

                //*** 아래쪽 테두리 ***
                if(map[N-1][i] == '.') { //빈 칸
                    v[N-1][i] = true;
                    q.offer(new int[]{N-1, i});
                }
                else if(Character.isLowerCase(map[N-1][i])) { //열쇠
                    v[N-1][i] = true;
                    list.add(map[N-1][i]);
                    map[N-1][i] = '.';
                    q.offer(new int[]{N-1,i});
                }
                else if(Character.isUpperCase(map[N-1][i])) {//문
                    char item = Character.toUpperCase(map[N-1][i]);
                    if(list.contains(item)) { //해당 문에 대한 열쇠가 존재한다면
                        v[N-1][i] = true;
                        map[N-1][i] = '.';
                        q.offer(new int[]{N-1,i});
                    } else {
                        doors[N-1][i] = true;
                    }
                }
                else if(map[N-1][i] == '$') { //문서
                    v[N-1][i] = true;
                    map[N-1][i] = '.';
                    initCnt++;
                    q.offer(new int[]{N-1,i});
                }


            }

            for(int i=0; i<N; i++) {
                //*** 왼쪽 테두리 ***
                if(map[i][0] == '.') { //빈 칸
                    v[i][0] = true;
                    q.offer(new int[]{i, 0});
                }
                else if(Character.isLowerCase(map[i][0])) { //열쇠
                    v[i][0] = true;
                    list.add(map[i][0]);
                    map[i][0] = '.';
                    q.offer(new int[]{i,0});
                }
                else if(Character.isUpperCase(map[i][0])) {//문
                    char item = Character.toUpperCase(map[i][0]);
                    if(list.contains(item)) { //해당 문에 대한 열쇠가 존재한다면
                        v[i][0] = true;
                        map[i][0] = '.';
                        q.offer(new int[]{i,0});
                    } else {
                        doors[i][0] = true;
                    }
                }
                else if(map[i][0] == '$') { //문서
                    v[i][0] = true;
                    map[i][0] = '.';
                    initCnt++;
                    q.offer(new int[]{i,0});
                }


                //*** 오른쪽 테두리 ***
                if(map[i][M-1] == '.') { //빈 칸
                    v[i][M-1] = true;
                    q.offer(new int[]{i, M-1});
                }
                else if(Character.isLowerCase(map[i][M-1])) { //열쇠
                    v[i][M-1] = true;
                    list.add(map[i][M-1]);
                    map[i][M-1] = '.';
                    q.offer(new int[]{i,M-1});
                }
                else if(Character.isUpperCase(map[i][M-1])) {//문
                    char item = Character.toUpperCase(map[i][M-1]);
                    if(list.contains(item)) { //해당 문에 대한 열쇠가 존재한다면
                        v[i][M-1] = true;
                        map[i][M-1] = '.';
                        q.offer(new int[]{i,M-1});
                    } else {
                        doors[i][M-1] = true;
                    }
                }
                else if(map[i][M-1] == '$') { //문서
                    v[i][M-1] = true;
                    map[i][M-1] = '.';
                    initCnt++;
                    q.offer(new int[]{i,M-1});
                }
            }

            //열쇠와 관련된 문들을 큐에 추가
            for(char idx : list) {
                idx = Character.toUpperCase(idx);
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < M; l++) {
                        if (map[k][l] == idx && doors[k][l]) {
                            v[k][l] = true;
                            q.offer(new int[]{k, l});
                        }
                    }
                }
            }


            sb.append(bfs() + initCnt).append("\n");


        }
        System.out.println(sb);
    }

    private static int bfs() {
        int cnt = 0;

        while(!q.isEmpty()) {
            int[] idx = q.poll();

            for(int i=0; i<4; i++) {
                int nx = idx[0] + dx[i];
                int ny = idx[1] + dy[i];
                //사방 탐색 후 대문자가 있으면 열쇠리스트 검색 후 해당 대문자와 관련된 열쇠지역들 모두 큐에 추가
                if(nx<0 || ny<0 || nx>=N || ny>= M || v[nx][ny] || map[nx][ny] == '*') continue;
                if(map[nx][ny] == '.') {
                    v[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                } else if(map[nx][ny] == '$') {
                    cnt++;
                    v[nx][ny] = true;
                    map[nx][ny] = '.';
                    q.offer(new int[]{nx,ny});
                } else if(Character.isLowerCase(map[nx][ny])){
                    //열쇠의 경우
                    list.add(map[nx][ny]); //열쇠추가
                    char door = Character.toUpperCase(map[nx][ny]);
                    map[nx][ny] = '.';

                    //열쇠와 관련된 문들을 큐에 추가
                    for(int k=0; k<N; k++) {
                        for(int l=0; l<M; l++) {
                            if(map[k][l] == door && doors[k][l]) {
                                v[k][l] = true;
                                q.offer(new int[]{k,l});
                            }
                        }
                    }
                    v[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                } else {
                    //문을 만날 경우
                    if(list.contains(Character.toLowerCase(map[nx][ny]))) {
                        q.offer(new int[]{nx,ny});
                        map[nx][ny] = '.';
                        v[nx][ny] = true;
                    } else {
                        //열쇠가 없다면
                        doors[nx][ny] = true;
                    }
                }
            }
        }
        return cnt;
    }
}