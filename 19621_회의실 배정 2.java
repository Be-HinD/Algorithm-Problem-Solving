import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<room> list;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        //입력
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        dfs(0, 0); //완전 탐색
        System.out.println(ans); //결과 출력
    }

    private static void dfs(int idx, int sum) {
        if(idx > (list.size() -1)) { //idx가 size-1을 초과할 때 결과값 갱신 및 재귀종료
            ans = ans >= sum ? ans : sum;
            return;
        }
        dfs(idx +2, sum + list.get(idx).p); //현재 회의를 선택한 경우 다음회의는 겹치므로 idx+2
        dfs(idx +1, sum); //현재 회의를 선택하지 않을 경우
    }
}

class room { //입력으로 들어오는 회의의 시작시간/종료시간 및 사람 수를 담을 클래스
    int s;
    int e;
    int p;
    room(int s, int e, int p) {
        this.s = s;
        this.e = e;
        this.p = p;
    }
}
