import java.io.*;
import java.util.*;

//BOJ_21608 상어 초등학교
public class Main {
    static int N,M;
    static List<List<Integer>> student;
    static int[][] map;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        //학교에 다니는 학생의 수는 N2명
        // |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸이 (r1, c1)과 (r2, c2)를 인접

        /*
        * 접근법
        * 인접리스트로 i번째 학생의 좋아하는 학생을 입력받음
        * 1. 배열 탐색으로 좋아하는 학생수가 제일 많은 자리 탐색
        * 2. 후보군을 리스트에 담고 리스트 size가 2이상일 경우 리스트 탐색을 통해 비어있는 칸이 많은 칸 탐색
        * 3. 2번의 후보군도 리스트에 담고 마찬가지로 size가 2이상일 경우 행의 번호가 작은 칸
        * 4. 3번 많을 경우 재차 리스트 탐색 ㄱㄱ
        * */

        M = N*N;

        arr = new int[M];
        student = new ArrayList<>();
        for(int i=0; i<=N*N; i++) student.add(new ArrayList<>());

        for(int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            arr[i] = idx;
            for(int j=0; j<4; j++) {
                student.get(idx).add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=0; i<M; i++) {
            //1번 탐색 시작
            List<int[]> first = searchManyStudent(arr[i]);

            if(first.size() > 1) {
                //2번 탐색 시작
                List<int[]> second = searchManyEmpty(arr[i], first);

                if(second.size() > 1) {
                    //3번 탐색 시작
                    int[] third = searchLast(arr[i], second);

                    map[third[0]][third[1]] = arr[i];
                }
                else {
                    int[] cur = second.get(0);
                    map[cur[0]][cur[1]] = arr[i];
                }
            }
            else {
                //한 자리밖에 후보군이 없을 경우
                int[] cur = first.get(0);
                map[cur[0]][cur[1]] = arr[i];
            }
        }


//        for(int i=0; i<N; i++){
//            System.out.println(Arrays.toString(map[i]));
//        }

        int res = 0;
        int score = 10;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {

                int cnt = 0;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                    if(student.get(map[i][j]).contains(map[nx][ny])) cnt++;
                }

                res += (int) (Math.pow(score, cnt) / score);
            }
        }

        System.out.println(res);

    }

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    private static List<int[]> searchManyStudent(int currentStudent) {

        int[][] cnts = new int[N][N];
        int max = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {

                if(map[i][j] != 0) continue;    //이미 누가 앉아있는 경우
                //현재 좌표 기준으로 사방탐색으로 좋아하는 학생수의 리스트 탐색
                for(int k=0; k<4; k++) {
                    int nx = i+dx[k];
                    int ny = j+dy[k];
                    if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                    if(student.get(currentStudent).contains(map[nx][ny])) {
                        cnts[i][j]++;
                    }
                }
                max = Math.max(max, cnts[i][j]);
            }
        }

        List<int[]> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] != 0) continue;    //1% 틀렸습니다 반례
                if(cnts[i][j] == max) list.add(new int[]{i,j});
            }
        }

        return list;
    }

    //가장 많이 비어있는 칸 탐색 메서드
    private static List<int[]> searchManyEmpty(int currentStudent, List<int[]> first) {

        List<int[]> list = new ArrayList<>();

        int[][] cnts = new int[N][N];
        for(int i=0; i<N; i++) Arrays.fill(cnts[i], -1);

        int max = 0;
        for(int i=0; i<first.size(); i++) {
            int[] cur = first.get(i);

            cnts[cur[0]][cur[1]] = 0;

            for(int j=0; j<4; j++) {
                int nx = cur[0] + dx[j];
                int ny = cur[1] + dy[j];
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(map[nx][ny] == 0) {
                    //공석이라면
                    cnts[cur[0]][cur[1]]++;
                }
            }
            max = Math.max(max, cnts[cur[0]][cur[1]]);
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(cnts[i][j] == max) {
                    list.add(new int[]{i,j});
                }
            }
        }
        return list;
    }

    private static int[] searchLast(int currentStudent, List<int[]> second) {

        //제일 낮은 행 탐색 및 리스트 추가
        List<int[]> row = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int i=0; i<second.size(); i++) {
            int[] cur = second.get(i);
            min = Math.min(min, cur[0]);
        }

        for(int i=0; i<second.size(); i++) {
            int[] cur = second.get(i);
            if(cur[0] == min) row.add(new int[]{cur[0], cur[1]});
        }

        if(row.size() > 1) {

            //제일 낮은 열 탐색
            int col = Integer.MAX_VALUE;
            for(int j=0; j<row.size(); j++) {
                int[] cur = row.get(j);
                col = Math.min(col, cur[1]);
            }

            for(int j=0; j<row.size(); j++) {
                int[] cur = row.get(j);
                if(cur[1] == col) {
                    return new int[]{cur[0], cur[1]};
                }
            }
        }

        int[] cur = row.get(0);
        return new int[]{cur[0], cur[1]};
    }
}