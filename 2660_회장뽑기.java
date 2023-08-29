import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N;
    static int Min = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> list;
    static ArrayList<Integer> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(st.nextToken()); //회원 수
        list = new ArrayList<>();
        ans = new ArrayList<>();
        int[] score = new int[N];
        for(int i=0; i<N; i++) list.add(new ArrayList<Integer>()); //인접리스트 초기화

        while(true) { //-1이 들어올 때 까지 인접리스트 입력받기
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            if(i == -2) break; //입력 중단점
            list.get(i).add(j);
            list.get(j).add(i);
        }

        for(int i=0; i<N; i++) {
            score[i] = bfs(new int[]{i, 0});
            Min = Math.min(Min, score[i]); //최소점수 찾기
        }

        for(int i=0; i<N; i++) { //최소점수를 가지는 후보 추가
            if(Min == score[i]) {
                ans.add(i);
            }
        }

        sb.append(Min + " " + ans.size() + "\n"); //후보 점수, 후보 수 추가
        for(int i=0; i<ans.size(); i++) { //후보 번호 추가
            sb.append(ans.get(i) + 1 + " ");
        }

        System.out.print(sb);
    }
    private static int bfs(int[] idx) {
        Queue<int[]> queue = new ArrayDeque(); //int[0] : 번호, int[1] : 깊이
        boolean v[] = new boolean[list.size()];
        queue.offer(idx);
        v[idx[0]] = true;
        int cnt = 0;
        while(!queue.isEmpty()) {
            int[] item = queue.poll();
            cnt = item[1]; //깊이관리
            for(int i =0; i<list.get(item[0]).size(); i++) { //큐에서 꺼낸 후보와 인접해있는 정점만 비교
                int index = list.get(item[0]).get(i);
                if(v[index]) continue; //방문체크
                queue.offer(new int[]{index,item[1] + 1}); //깊이 1만큼 올려서 큐에 추가
                v[index] = true;
            }
        }
        return cnt;
    }
}
