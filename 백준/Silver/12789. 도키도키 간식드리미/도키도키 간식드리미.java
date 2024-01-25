import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_12789 도키도키 간식드리미
public class Main {
    static int N, M, res;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> mainPipe = new ArrayDeque<>();
        ArrayDeque<Integer> sidePipe = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            mainPipe.offer(Integer.parseInt(st.nextToken()));
        }

        for(int i=1; i<=N; i++) {
            boolean flag = false;
            if(!mainPipe.isEmpty() && mainPipe.peekFirst() == i) {
                mainPipe.pollFirst();
                flag = true;
                continue;
            }
            if(!sidePipe.isEmpty() && sidePipe.peekLast() == i) {
                sidePipe.pollLast();
                flag = true;
                continue;
            }
            //두 군데 다 없다면
            while(!mainPipe.isEmpty()) {
                sidePipe.offer(mainPipe.pollFirst());

                if(mainPipe.isEmpty()) {
                    break;
                }

                if(mainPipe.peek() == i) {
                    mainPipe.poll();
                    flag = true;
                    break;
                }
            }
            if(!flag) break;
        }

        if(mainPipe.isEmpty() && sidePipe.isEmpty()) {
            System.out.println("Nice");
        }
        else {
            System.out.println("Sad");
        }
    }
}