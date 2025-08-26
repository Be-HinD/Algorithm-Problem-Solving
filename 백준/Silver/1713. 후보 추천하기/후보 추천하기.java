import java.io.*;
import java.util.*;

//BOJ_1713
public class Main {
    static class INFO {
        int cnt;
        int when;

        public INFO(int cnt, int when) {
            this.cnt = cnt;
            this.when = when;
        }
    }
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 조건
         * 1. 추천받으면 무조건 게시
         * 2. 비어있는 틀이 없다면 : 추천횟수 -> 같다면 기간
         * 3. 게시된 학생들의 카운트 관리(틀에서 내려가면 초기화)
         * 4. cmd <= 1,000 (N^3 가능)
         * **/

        int n = Integer.parseInt(br.readLine());
        int recommandCnt = Integer.parseInt(br.readLine());

        INFO[] student = new INFO[101];
        for(int i=1; i<101; i++) {
            student[i] = new INFO(0,  -1);
        }
        int uploadCnt = 0;

        st = new StringTokenizer(br.readLine());
        while(recommandCnt-->0) {
            int who = Integer.parseInt(st.nextToken());

            if(student[who].cnt != 0) { //이미 게시된 학생
                student[who].cnt++;
                continue;
            }

            if(uploadCnt == n) {
                //한명을 내려야 할 경우
                int target = 0; int min = 1001;
                for(int i=1; i<101; i++) {
                    if(student[i].cnt == 0) continue;
                    if(student[i].cnt == min) {
                        if(student[i].when > student[target].when) {
                            target = i;
                            min = student[i].cnt;
                        }
                    }
                    else if(student[i].cnt < min) {
                        target = i;
                        min = student[i].cnt;
                    }
                }
                student[target] = new INFO(0, -1);
                uploadCnt--;
            }

            student[who].cnt++;
            student[who].when = recommandCnt;
            uploadCnt++;
        }

        int max = 0;
        for(int i=1; i<101; i++) {
            max = Math.max(max, student[i].cnt);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=1; i<101; i++) {
            if(student[i].cnt == 0) continue;
            pq.offer(i);
        }
    
        // 추천수 기준 정렬했다가 틀림
        while(!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }

    }
}