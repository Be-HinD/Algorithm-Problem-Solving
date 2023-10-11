import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static class Bug {
        int x;
        int y;
        int cnt;
        int dist;
        boolean isLife;
        int diffCnt;
        Bug(int x, int y, int cnt, int dist, boolean life, int diffCnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dist = dist;
            this.isLife = life;
            this.diffCnt = diffCnt;
        }
    }
    static int T, N, M, K;
    static int[] dx = new int[]{-1,1,0,0}; //상하좌우 방향벡터
    static int[] dy = new int[]{0,0,-1,1};
    static ArrayList<Bug> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            //셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            list = new ArrayList<>(); //미생물 담을 리스트

            for(int i=0; i<K; i++) { //미생물 입력
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken())-1;
                Bug bug = new Bug(x, y, cnt, dist,true, cnt);
                list.add(bug);
            }

            for(int i=0; i<M; i++) { //미생물 이동
                Move();
            }

            int sum = 0;
            for(int i=0; i<list.size(); i++) {
                Bug bug = list.get(i);
                if(!bug.isLife) continue;
                sum += bug.cnt;
            }
            System.out.println("#" + tc + " " + sum);
        }
    }
    private static void Move() {
        //미생물 한마리씩 꺼내서 이동
        for(int i=0; i<list.size(); i++) {
            Bug bug = list.get(i); //미생물 꺼내고
            if(!bug.isLife) continue; //죽어있으면 다음 미생물로
            int dist = bug.dist; //방향
            //좌표 갱신
            int nx = bug.x + dx[dist];
            int ny = bug.y + dy[dist];

            if(nx==0 || ny==0 || nx==N-1 || ny==N-1) { //약품이 칠해진 곳으로 갈 경우
                bug.cnt = bug.cnt/2; //미생물 감소
                bug.diffCnt = bug.cnt; //비교변수 갱신
                if(bug.cnt == 0) { //미생물 전멸 시 제거
                    bug.isLife = false;
                } else { //미생물이 남아있는 경우
                    //진행방향 반대
                    if (dist % 2 == 0) dist++;
                    else dist--;
                }
            }
            //값 갱신
            bug.x = nx;
            bug.y = ny;
            bug.dist = dist;
        }
        //이동 끝
        //겹친 셀 탐색
        for(int i=0; i<list.size(); i++) {
            Bug bug = list.get(i);
            if(!bug.isLife) continue;
            for(int j=0; j<list.size(); j++) {
                if(i==j) continue;
                Bug diffBug = list.get(j);
                if(!diffBug.isLife) continue;

                if(bug.x == diffBug.x && bug.y == diffBug.y) { //겹친다면
                    if(bug.diffCnt > diffBug.diffCnt) { //bug가 더 클 경우
                        bug.cnt += diffBug.cnt; //미생물 수 합치기
                        diffBug.isLife = false;
                    } else { // diff가 더 클 경우
                        bug.diffCnt = diffBug.cnt; //비교변수 갱신
                        bug.cnt += diffBug.cnt;
                        bug.dist = diffBug.dist; //방향갱신
                        diffBug.isLife = false;
                    }
                }
            }
        }

        //겹친 미생물 작업 끝난 후 다음 이동을 위해 diffCnt 초기화
        for(int i=0; i<list.size(); i++) {
            Bug bug = list.get(i);
            if(!bug.isLife) continue;
            bug.diffCnt = bug.cnt;
        }
    }
}
