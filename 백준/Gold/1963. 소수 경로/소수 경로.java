import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_14728
public class Main {
    static class NUM {
        String num;
        int transCnt;
        public NUM(String num, int transCnt) {
            this.num = num;
            this.transCnt = transCnt;
        }
    }
    static int T;
    static boolean[] isPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        sieve(10000);

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            String in = st.nextToken();
            String out = st.nextToken();

            int res = bfs(in, out);

            sb.append(res == -1 ? "Impossible" : res).append("\n");
        }

        System.out.println(sb);

    }

    private static int bfs(String in, String out) {
        PriorityQueue<NUM> pq = new PriorityQueue<>((o1, o2) -> o1.transCnt - o2.transCnt);
        boolean[] v = new boolean[10000];
        pq.offer(new NUM(in, 0));
        v[Integer.parseInt(in)] = true;

        while(!pq.isEmpty()) {
            NUM cur = pq.poll();

            if(cur.num.equals(out)) {
                return cur.transCnt;
            }

            for(int j=0; j<4; j++) {
                int target = cur.num.charAt(j) - '0';
                for (int i = 0; i <= 9; i++) {
                    if(target == i) continue;
                    String next = "";
                    for(int k=0; k<4; k++) {
                        if(j==k) {
                            next += Integer.toString(i);
                            continue;
                        }
                        next += cur.num.charAt(k);
                    }
                    if(Integer.parseInt(next) < 1000) continue;
                    if(isPrime[Integer.parseInt(next)] && !v[Integer.parseInt(next)]) {
                        pq.offer(new NUM(next, cur.transCnt+1));
                        v[Integer.parseInt(next)] = true;
                    }
                }
            }
        }

        return -1;
    }

    private static void sieve(int n) {
        // 소수를 판별하는 배열 생성, true로 초기화
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        // 0과 1은 소수가 아님
        isPrime[0] = false;
        isPrime[1] = false;

        // 2부터 시작하여 제곱근 n까지의 배수를 제거
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false; // i의 배수들은 소수가 아님
                }
            }
        }

    }
}