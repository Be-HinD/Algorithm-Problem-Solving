import java.io.*;
import java.util.*;

//BOJ_1374
public class Main {
    static int N, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        //삽입 연산 N, 조회 연산 N

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new int[]{s,1});
            list.add(new int[]{e,-1});
        }

        Collections.sort(list, ((o1, o2) -> { return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]; }));

        ListIterator itr = list.listIterator();

        int sum = 0;
        while(itr.hasNext()) {
            int[] cur = (int[]) itr.next();
            sum += cur[1];
            res = Math.max(res, sum);
        }

        System.out.println(res);

    }
}