import java.io.*;
import java.util.StringTokenizer;

//BOJ_13305
public class Main {
    static int cityCnt;
    static int[] dist, store;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cityCnt = Integer.parseInt(br.readLine());
        dist = new int[cityCnt-1];
        store = new int[cityCnt];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<dist.length; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<store.length; i++) {
            store[i] = Integer.parseInt(st.nextToken());
        }

        long res = 0;

        for(int i=0; i<store.length-1; i++) {
            int curCost = store[i];
            res += (long) dist[i] * curCost;
            //현재 지점보다 저렴한지점까지 탐색
            for(int j=i+1; j<store.length-1; j++) {
                if(j == store.length-1) break;

                if(curCost <= store[j]) {
                    res += (long) dist[j] * curCost;
                    i++;
                }
                else break;
            }
        }

        System.out.println(res);

    }
}