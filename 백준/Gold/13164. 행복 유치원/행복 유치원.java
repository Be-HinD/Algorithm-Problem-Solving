    import java.io.*;
    import java.util.*;

    //BOJ_13164
    public class Main {
        static int N, K, res;
        static int[] arr;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            /**
             * 적어도 한 명
             * 같은 조에 속한 원생들은 서로 인접
             * 가장 키가 큰 원생과 가장 키가 작은 원생의 키 차이
             * K개의 조에 대해 티셔츠 만드는 비용의 합을 최소
             * **/

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            if(N == 1) {
                System.out.println(0);
                return;
            }

            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer> list = new ArrayList<>();

            for(int i=1; i<N; i++) {
                list.add(arr[i]-arr[i-1]);
            }

            Collections.sort(list);

            for(int i=0; i<N-K; i++) {
                res += list.get(i);
            }

            System.out.println(res);

        }
    }