import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {
    static int N, M; //정점개수 N, 입력개수 M
    static ArrayList<ArrayList<Integer>> list; //인접리스트
    static int[] degree; //정점 차수 저장배열
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); //출력값이 많기 때문에 빌더사용

        N = Integer.parseInt(st.nextToken()) + 1; //정점의 개수 + 1
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>(); //인접리스트 생성
        for(int i=0; i<N; i++) list.add(new ArrayList<>()); //정점개수 N만큼 리스트 초기화
        degree = new int[N]; //차수배열 초기화

        for(int i=0; i<M; i++) { //입력값 M개만큼 인접리스트 갱신
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken()); //진입차수
            list.get(x).add(y);
            degree[y]++; //진입차수에 해당하는 정점의 차수 증가
        }
        Queue<Integer> q = new ArrayDeque<>(); //방문을 위한 큐 생성
        
        for(int i=1; i<N; i++) { //정점 1번부터
            if(degree[i] == 0) { //차수가 0인 시작정점들을 큐에 추가
                q.offer(i); //노드번호를 추가
            }
        }

        while(!q.isEmpty()) { //큐가 빌 때까지
            int Node = q.poll();
            sb.append(Node + " "); //방문정점 빌더 추가
            
            for(int i=0; i<list.get(Node).size(); i++) { //방문한 정점으로부터 진출차수만큼 반복
                int idx = list.get(Node).get(i); //진출차수에 해당하는 정점의 번호를 get
                degree[idx]--; //차수감소
                if(degree[idx] == 0) q.offer(idx); //감소된 차수가 0이라면 방문
            }

        }
        System.out.println(sb); //결과출력
    }
}
