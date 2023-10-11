//중복순열 + 시뮬레이션
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T, N, res; //입력 및 결과값 담을 변수
    static ArrayList<int[]> mans, stairs; //사람, 계단에 대한 리스트
    static int[] arr; //중복순열 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) { //testCase
            N = Integer.parseInt(br.readLine());

            res = Integer.MAX_VALUE; //결과값 초기화
            //리스트 초기화
            mans = new ArrayList<>();
            stairs = new ArrayList<>();

            for(int i=0; i<N; i++) { //입력
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int idx = Integer.parseInt(st.nextToken());
                    if(idx == 1) mans.add(new int[]{i,j}); //사람 리스트 추가
                    else if(idx > 1) stairs.add(new int[]{i,j,idx}); //계단 리스트 추가
                }
            }

            arr = new int[mans.size()]; //배열 사람수만큼 초기화
            Perm(0); //중복순열
            System.out.println("#" + tc + " " + res);
        }
    }

    private static void Perm(int cnt) {
        if(cnt == mans.size()) {
            //중복 순열 완성
            logic(); //이동 로직
        } else {
            for(int i=0; i<2; i++) {
                arr[cnt] = i;
                Perm(cnt+1);
            }
        }
    }

    private static void logic() {
        int[] timeArr = new int[mans.size()]; //i번째 사람에 대한 걸리는 시간을 담는 배열
        int[] stairs1 = new int[3]; //첫번째 계단에 대해 포화상태인지 확인할 배열
        int[] stairs2 = new int[3]; //두번째 계단에 대해 포화상태인지 확인할 배열
        boolean[] v = new boolean[mans.size()]; //i번째 사람이 계단을 탔는지 확인할 배열 (이거 없으면 기저조건 못 세움)

        for (int i = 0; i < mans.size(); i++) { //각 사람마다의 계단까지의 거리를 배열에 저장
            int[] idx = mans.get(i); //idx = {좌표x, 좌표y}
            int[] des = stairs.get(arr[i]); //des = {계단좌표x, 계단좌표y, 계단에 대한 시간값}
            timeArr[i] = Math.abs(idx[0] - des[0]) + Math.abs(idx[1] - des[1]); //절대값으로 저장(맨헤튼 거리)
        }

        int cnt = 1; //1초부터 시작하거나 마지막 결과에 +1 (왜인지모름)

        while (true) { //이동 핵심 로직
            //각 사람이 남은 거리를 1씩 감소
            for (int i = 0; i < timeArr.length; i++) {
                if (timeArr[i] != 0) timeArr[i]--;
            }
            //1계단 포화 체크배열 1 감소
            for (int i = 0; i < stairs1.length; i++) {
                if (stairs1[i] != 0) stairs1[i]--;
            }
            //2계단 포화 체크배열 1 감소
            for (int i = 0; i < stairs2.length; i++) {
                if (stairs2[i] != 0) stairs2[i]--;
            }

            //계단에 도착한 사람에 대한 로직
            for (int i = 0; i < timeArr.length; i++) { //각 사람에 대해
                if (timeArr[i] == 0 && !v[i]) { //계단을 도착했고(남은시간이 0) && 계단을 탄 적이 없는 경우
                    int[] idx = stairs.get(arr[i]); //idx = {x,y,value} i번째 사람이 타야 할 계단에 대한 정보
                    if (arr[i] == 0) { //첫 번째 계단이라면
                        for (int j = 0; j < stairs1.length; j++) { //계단 포화상태 탐색
                            if (stairs1[j] == 0) { //계단이 비어있는 경우
                                stairs1[j] = idx[2]; //계단 배열 값 채우기
                                timeArr[i] += idx[2]; //계단 시간만큼 추가
                                v[i] = true; //계단 방문체크
                                break; //꼭 필요 (없으면 한 사람이 3개의 자리를 다 채움)
                            }
                        }
                    } else { //두번째 계단에 대해
                        for (int j = 0; j < stairs2.length; j++) { //계단 포화상태 탐색
                            if (stairs2[j] == 0) { //계단이 비어있는 경우
                                stairs2[j] = idx[2]; //계단 배열 값 채우기
                                timeArr[i] += idx[2]; //계단 시간만큼 추가
                                v[i] = true; //계단 방문체크
                                break;
                            }
                        }
                    }
                }
            }

            //이동 로직 수행 후 다 내려갔는지 기저조건 점검
            boolean flag = false;
            for(int i=0; i<mans.size(); i++) {
                if(!v[i]) { //한 사람이라도 계단을 방문한 적이 없다면
                    flag = true;
                    break;
                }
                if(timeArr[i] != 0) { //한 사람이라도 남은시간이 있다면
                    flag = true;
                    break;
                }
            }
            cnt++; //시간 증가
            if(cnt >= res) break; //백트래킹 : 결과값보다 걸리는 시간이 높다면 비교할 필요 없음
            if(!flag) { //기저조건 만족 시
                res = Math.min(res, cnt); //결과값 비교 갱신 후
                break; //로직 종료
            }
        }
    }
}
