import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int no, ano, pushTime, coolTime, immune;
    static int[] erda, origin;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        erda = new int[N];
        origin = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) { //행동불가
            erda[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(erda);
        immune = erda[0];
        coolTime = erda[0];
        no++;
        for(int i=1; i<erda.length; i++) {
            pushTime = erda[i];
            //쿨타임이 있는지 체크
            if((pushTime-coolTime) >= 100) {
                coolTime = pushTime; //쿨타임 되면 바로 사용
                if((pushTime-immune)>= 90) //면역이아니라면
                no++; //사용횟수 증가
                immune = pushTime; //면역 갱신
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) { //행동불가
            origin[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(origin);
        immune = origin[0];
        coolTime = origin[0];
        ano++;
        for(int i=1; i<origin.length; i++) {
            pushTime = origin[i];
            //쿨타임이 있는지 체크
            if((pushTime-coolTime) >= 360) {
                coolTime = pushTime;
                if((pushTime-immune)>= 90) {
                    ano++;
                    immune = pushTime;
                }
            }
        }
        System.out.println(no + " " + ano);
        }
}
