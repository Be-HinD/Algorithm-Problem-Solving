import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_2251 물통
public class Main {
    static int A,B,C,res;
    static ArrayList<Integer> list;
    static boolean[][][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new boolean[A+1][B+1][C+1];

        list = new ArrayList<>();

        bfs();

        Collections.sort(list);

        for(int idx : list) {
            sb.append(idx).append(" ");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,C});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];

            if(arr[a][b][c]) continue;

            arr[a][b][c] = true;

            if(a == 0) {
                list.add(c);
                res++;
            }

            //한 물통이 비거나 다른 한 물통이 가득 찰 때까지
            if(a != 0) {
                if(b != B) {
                    if(B-b > a) { //b를 가득 못채울 경우
                        q.offer(new int[]{0,b+a,c});
                    }
                    else { //b를 가득 채울 경우
                        q.offer(new int[]{a-(B-b), B, c});
                    }
                }
                if(c != C) {
                    if(C-c > a) { //c를 가득 못채울 경우
                        q.offer(new int[]{0,b,c+a});
                    }
                    else { //c를 가득 채울 경우
                        q.offer(new int[]{a-(C-c), b, C});
                    }
                }
            }
            if(b != 0) {
                if(a != A) {
                    if(A-a > b) { //a를 가득 못채울 경우
                        q.offer(new int[]{a+b,0,c});
                    }
                    else { //a를 가득 채울 경우
                        q.offer(new int[]{A, b-(A-a), c});
                    }
                }
                if(c != C) {
                    if(C-c > b) { //c를 가득 못채울 경우
                        q.offer(new int[]{a,0,c+b});
                    }
                    else { //c를 가득 채울 경우
                        q.offer(new int[]{a, b-(C-c), C});
                    }
                }
            }
            if(c != 0) {
                if(a != A) {
                    if(A-a > c) { //a를 가득 못채울 경우
                        q.offer(new int[]{a+c,b,0});
                    }
                    else { //a를 가득 채울 경우
                        q.offer(new int[]{A, b, c-(A-a)});
                    }
                }
                if(b != B) {
                    if(B-b > c) { //b를 가득 못채울 경우
                        q.offer(new int[]{a,b+c,0});
                    }
                    else { //b를 가득 채울 경우
                        q.offer(new int[]{a, B, c-(B-b)});
                    }
                }
            }
        }
    }
}