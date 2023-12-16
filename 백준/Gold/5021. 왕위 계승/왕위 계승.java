import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_5021 왕위 계승
public class Main {
    static int N, M;
    static String king;
    static double[] blood;
    static String[] list;
    static HashMap<String, List<String>> candiList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        king = br.readLine(); //유토피아를 세운 사람의 이름
        candiList = new HashMap<>();

        String[] arr = new String[3];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int w=0; w<3; w++) {
                arr[w] = st.nextToken();
            }

            for(int w=0; w<3; w++) {
                if(!candiList.containsKey(arr[w])) {
                    candiList.put(arr[w], new ArrayList<>());
                }
            }

            candiList.get(arr[1]).add(arr[0]);
            candiList.get(arr[2]).add(arr[0]);
        }

        blood = new double[M];
        list = new String[M];
        for(int i=0; i<M; i++) {
            list[i] = br.readLine();
        }

        bfs();

        String nextKing = "";
        double maxBlood = 0;

        for(int i=0; i<M; i++) {
            double b = blood[i];
            if(maxBlood < b) {
                maxBlood = b;
                nextKing = list[i];
            }
        }

        System.out.println(nextKing);
    }

    private static void bfs() {
        Queue<String> q = new ArrayDeque<>();
        q.offer(king);

        double depth = 1;
        int step = 0;
        while(!q.isEmpty()) {
            depth /= 2;
            step = q.size();
            for(int i=0; i<step; i++) {
                for(String idx : candiList.get(q.poll())) {
                    for(int w=0; w<M; w++) {
                        if(idx.equals(list[w])) {
                            blood[w] +=  depth;
                        }
                    }
                    q.offer(idx);
                }
            }
        }
    }
}