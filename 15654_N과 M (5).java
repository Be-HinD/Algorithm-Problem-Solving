import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();
    static boolean[] v; //방문배열
    static StringBuilder sb = new StringBuilder(); //안쓰면 시간초과
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken() );
        M = Integer.parseInt(st.nextToken() );
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) { //입력값 리스트 추가
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list); //오름차순 정렬

        arr = new int[M];
        v = new boolean[10055];
        
        Perm(0);
        System.out.println(sb);
    }

    private static void Perm(int cnt) {
        if(cnt == M) {
            //출력
            for(int i=0; i<arr.length; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
        } else {
            for(int i=0; i<list.size(); i++) {
                int idx = list.get(i);
                if(!v[idx]) { //방문 검사
                    v[idx] = true;
                    arr[cnt] = idx;
                    Perm(cnt + 1);
                    v[idx] = false;
                }
            }
        }
    }
}
