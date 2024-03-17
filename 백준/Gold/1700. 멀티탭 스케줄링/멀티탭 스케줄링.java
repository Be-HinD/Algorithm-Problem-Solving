import java.io.*;
import java.util.*;

//BOJ_1700 멀티탭 스케줄링
public class Main {
    static int res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //멀티탭 구멍의 개수 ( 1 <= N <= 100 )
        int M = Integer.parseInt(st.nextToken());   //전기 용품의 총 사용횟수 ( 1 <= M <= 100 )

        /*
        * 접근법
        * 최대한 많이 사용하는 전기용품은 첫번째로
        * 입력은 사용 순서대로 들어옴. 자연수는 순서가 아닌 제품의 이름을 대신함.
        * 배열로 받고 들어오는 순서대로 일단 최대한의 플러그를 사용하고
        * 새롭게 추가해야할 전기용품의 차례가 되면, 누구를 뺄지 그리디하게 판단.
        * 요점 : 꼽을 때가 아닌 뺄 때 로직을 작성.
        * 로직 -----
        * 콘센트 자리가 비어있다면 추가
        * 콘센트 자리가 없고 새로운 전자제품의 차례일 때는
        * 현재 꽂혀있는 전자제품 중에서 이후에도 사용이 될 수 있는지 체크
        * 사용안되는 전자제품이 있다면 그자리 remove
        * 모두 재사용이 된다면 제일 마지막에 재사용 되는 전자제품 remove
        * */

        int[] arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) arr[i] = Integer.parseInt(st.nextToken());


        boolean[] used = new boolean[101];  //전자제품이 꽂혀있는지 알기위한 boolean 배열
        int[] plugs = new int[N];   //현재 꽂혀있는 전자제품 리스트 (콘센트 개수만큼의 길이)
        int pointer = 0;    //plugs 배열의 포인터

        for(int i=0; i<M; i++) {
            int cur = arr[i];   //현재 차례의 전자제품

            if(used[cur]) {
                //현재 꽂혀있는 전자제품이라면
                continue;
            }

            if(pointer < N) {
                //콘센트 자리가 남아있는 경우
                plugs[pointer++] = cur;
                used[cur] = true;
                continue;
            }

            //콘센트 자리가 부족할 경우
            List<Integer> list = new ArrayList<>();
            for(int j=i; j<M; j++) {
                if(used[arr[j]] && !list.contains(arr[j])) {
                    list.add(arr[j]);   //리스트에 추가되는 순서가 arr[j] 전자제품이 등장하는 순서인게 포인트!
                }
            }
            //위에서 구한 리스트는 현재 꽂혀있는 전자제품 중 다음에 쓰이는 것들.
            if(list.size() != N) {
                //추후에도 안쓰이는 전자제품이 현재 꽂혀있는 경우임.
                for(int j=0; j<M; j++) {
                    if(used[arr[j]] && !list.contains(arr[j])) {
                        used[arr[j]] = false;
                        break;
                    }
                }
            }
            else {
                //모두 추후에 쓰이는 경우이므로 제일 마지막에 사용되는 전자제품을 제거해야함.
                int removePlug = list.get(list.size()-1);
                used[removePlug] = false;
            }

            used[cur] = true;
            res++;

        }

        System.out.println(res);

    }
}