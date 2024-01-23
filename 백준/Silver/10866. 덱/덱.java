import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ_10866 덱
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();

            if(oper.equals("push_back")) {
                int num = Integer.parseInt(st.nextToken());
                ad.addLast(num);
            }
            else if(oper.equals("push_front")) {
                int num = Integer.parseInt(st.nextToken());
                ad.addFirst(num);
            }
            else if(oper.equals("pop_front")) {
                if(ad.isEmpty()) {
                    sb.append(-1).append("\n");
                    continue;
                }
                sb.append(ad.pollFirst()).append("\n");
            }
            else if(oper.equals("pop_back")) {
                if(ad.isEmpty()) {
                    sb.append(-1).append("\n");
                    continue;
                }
                sb.append(ad.pollLast()).append("\n");
            }
            else if(oper.equals("size")) {
                sb.append(ad.size()).append("\n");
            }
            else if(oper.equals("empty")) {
                if(ad.isEmpty()) {
                    sb.append(1).append("\n");
                    continue;
                }
                sb.append(0).append("\n");
            }
            else if(oper.equals("front")) {
                if(ad.isEmpty()) {
                    sb.append(-1).append("\n");
                    continue;
                }
                sb.append(ad.peekFirst()).append("\n");
            }
            else if(oper.equals("back")) {
                if(ad.isEmpty()) {
                    sb.append(-1).append("\n");
                    continue;
                }
                sb.append(ad.peekLast()).append("\n");
            }
        }
        System.out.println(sb);
    }
}