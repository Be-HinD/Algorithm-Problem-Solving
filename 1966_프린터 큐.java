import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int tc, N, M, cnt, ans; //cnt : 중요도 비교 배열 arr의 인덱스 참조용, ans : 몇번째 출력인지 확인하기 위한 변수
    static Integer[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        tc = Integer.parseInt(st.nextToken());
        for(int t=0; t<tc; t++) { //Test Case~
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new Integer[N]; //중요도 배열 초기화
            ans = 0; //테케마다 초기화
            cnt = 0; //테케마다 초기화

            Deque<word> dq = new ArrayDeque<>(); //동작과정을 위한 덱 선언
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) { //덱의 입력값 추가 및 arr 값초기화
                int item = Integer.parseInt(st.nextToken());
                word w = new word(item, i); //인덱스번호와 중요도를 담은 해시생성
                dq.addLast(w); //덱의 끝에 word 객체를 추가
                arr[i] = item; // 문서의 중요도를 담는 배열
            }
            Arrays.sort(arr, Collections.reverseOrder()); //중요도 내림차순 정렬
            while(true) {
                if (dq.peekFirst().priority >= arr[cnt]) { //덱의 맨앞에 있는 문서의 중요도가 제일 높은 우선순위를 가지면
                    ans++;
                    cnt++;
                    if(dq.pop().idx == M) { //현재 꺼낸 문서가 찾고싶은 문서일 경우
                        System.out.println(ans); //결과 출력
                        break; //반복문 탈출
                    }
                } else { //덱의 맨앞에 있는 문서의 중요도가 낮을 경우
                    dq.addLast(dq.pop()); //꺼낸 후 덱의 맨 뒤로 옮겨줌
                }
            }
        }
    }
}
class word { //문서의 중요도 및 탐색을위한 인덱스번호를 가지는 클래스
    int priority;
    int idx;
    word(int priority, int idx) {
        this.priority = priority;
        this.idx = idx;
    }
}
