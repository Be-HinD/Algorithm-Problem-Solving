import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N,start, Max, ans;
    static int visited[];
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc < 11; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()) + 1; //입력값
            start = Integer.parseInt(st.nextToken()); //시작정점
            ans = 0; //최종적으로 높은 친구의 번호(결과값)
            Max = 0; //최종적으로 제일 높은 차수 저장 변수

            list = new ArrayList<ArrayList<Integer>>(); //인접리스트 초기화
            for (int i = 1; i < 102; i++) list.add(new ArrayList<Integer>());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) { //from, to를 N/2만큼 입력 및 인접리스트 추가
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list.get(from).add(to);
            }

            visited = new int[101]; //차수 저장 및 방문배열

            bfs(start); //bfs 탐색

            for (int i = 1; i < visited.length; i++) { //visited배열을 돌면서 제일 높은 차수값을 저장하고 해당하는 정점번호를 저장
                if (visited[i] >= Max) {
                    Max = visited[i]; //최대 차수값
                    ans = i; //최대 차수값의 정점번호
                }
            }

            for (int i = 1; i < visited.length; i++) { //정점들의 차수를 순회하면서 최대차수와 같은 정점들의 번호를 비교갱신
                if (visited[i] == Max) {
                    ans = Math.max(ans, i);
                }
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = 1; //시작정점 true체크 및 차수증가

        while(!queue.isEmpty()) {
            int idx = queue.poll();

            for(int i=0; i<list.get(idx).size(); i++) {
                int item = list.get(idx).get(i); //인접정점 (친구 번호)
                if(visited[item] == 0) { //방문하지 않은곳
                    queue.offer(item);
                    visited[item] = visited[idx] + 1; //방문카운트 늘림.
                }
            }
        }
    }
}
