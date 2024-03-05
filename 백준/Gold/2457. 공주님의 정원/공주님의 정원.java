import java.io.*;
import java.util.*;

//BOJ_2457 공주님의 정원
public class Main {
    static class TIME {
        int start;
        int end;

        public TIME(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static int N, res;
    static List<TIME> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())*100+Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken())*100+Integer.parseInt(st.nextToken());
            list.add(new TIME(start, end));
        }

        Collections.sort(list, new Comparator<TIME>() {
            @Override
            public int compare(TIME o1, TIME o2) {
                if(o1.start == o2.start) return o1.end - o2.end;
                return o1.start-o2.start;
            }
        });

        int startTime = 301;
        int max = 0;
        int idx = 0;

        while(startTime < 1201) {
            boolean flag = false;

            for(int i=idx; i<N; i++) {

                if(list.get(i).start > startTime) break;

                if(max < list.get(i).end) {
                    max = list.get(i).end;
                    idx = i+1;
                    flag = true;
                }

            }

            if(flag) {
                res++;
                startTime = max;
            }

            else break;
        }

        System.out.println(max<1201?0:res);
    }
}