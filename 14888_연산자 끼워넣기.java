import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, idx, Min_ans, Max_ans; // N : 피연산자 개수, idx : hash 인덱스용, Min_ans/Max_ans : 결과값
    static int[] operand, arr, operand_copy; //operand : 피연산자 입력배열, arr : 조합배열, operand_copy : 연산을 위한 카피용배열
    static boolean[] visited; //조합을 위한 방문체크 배열
    static HashMap<Integer, Integer> hash; //<조합을 위한 순서, 연산자 프로토콜>
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        operand = new int[N];
        hash = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) { //피연산자 수열 입력
            operand[i] = Integer.parseInt(st.nextToken());
        }
        //연산자 해시 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num == 0) continue;
            for(int j=0; j<num; j++) {
                hash.put(idx++, i);
            }
        }
        //조합을 위한 arr배열 크기 초기화
        arr = new int[hash.size()];
        //방문배열 초기화
        visited = new boolean[hash.size()];
        Min_ans = Integer.MAX_VALUE; //최소값을 위한 초기화
        Max_ans = Integer.MIN_VALUE;
        Permutaion(0); //로직 시작
        System.out.println(Max_ans);
        System.out.println(Min_ans);
    }

    private static void Permutaion(int cnt) { //N-1개의 연산자 조합 구하는 메서드
        //기저조건
        if(cnt == hash.size()) {
            //구해지 연산자 조합에 따른 연산로직 호출
            Calculator();
            return;
        }
        //동작부
        for(int i=0; i<hash.size(); i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[cnt] = i;
            Permutaion(cnt + 1); //조합 재귀호출
            visited[i] = false;
        }
    }

    private static void Calculator() { //연산자 우선순위를 신경쓰지않고 해시에서 꺼내 온 연산에 따른 순차계산 로직
        operand_copy = Arrays.copyOf(operand, operand.length); //기본 수열을 건들지 않기 위해 copy 배열 선언
        for(int i=0; i<hash.size(); i++) { //해시 크기만큼 반복 즉 연산자 개수만큼..
            int oper = hash.get(arr[i]); //arr[]배열이 0~N-1까지의 조합 ==> 해시의 키값으로 arr배열의 i번째 값을 참조 후 value를 연산 프로토콜로 사용
            switch (oper) { //switch-case 문으로 연산자 우선순위 신경쓰지않고 계산한 값을 i+1에 저장
                case 0:
                    operand_copy[i+1] = operand_copy[i] + operand_copy[i+1];
                    break;
                case 1:
                    operand_copy[i+1] = operand_copy[i] - operand_copy[i+1];
                    break;
                case 2:
                    operand_copy[i+1] = operand_copy[i] * operand_copy[i+1];
                    break;
                case 3:
                    operand_copy[i+1] = operand_copy[i] / operand_copy[i+1];
                    break;
            }
        }
        Min_ans = Math.min(Min_ans, operand_copy[operand_copy.length -1]); //최소값 비교 후 갱신
        Max_ans = Math.max(Max_ans, operand_copy[operand_copy.length -1]); //최대값 비교 후 갱신
    }
}
