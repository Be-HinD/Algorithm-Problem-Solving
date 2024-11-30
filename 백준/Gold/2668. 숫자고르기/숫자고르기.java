import java.io.*;
import java.util.*;

//BOJ_2668
class Main {
    static int N, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 키워드
         * 첫째줄에서 임의의 집합을 뽑았을 때, 아래 줄 집합도 일치하는 경우의 수 탐색
         * 그러한 집합의 최대 크기 탐색
         * 접근법
         * 1초, N <= 100 -> O(N^3)까지 가능
         * 싸이클이 형성되는 집합 + 자기자신을 원소로 가지는 수 => Set
         * **/

        N = Integer.parseInt(br.readLine());    //수열

        Map<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            map.put(i, arr[i]);
        }

        Set<Integer> set = new TreeSet<>();

        List<Integer> list;
        boolean[] v;
        for(int i=1; i<=N; i++) {
            if(set.contains(i)) continue;
            list = new ArrayList<>();
            list.add(i);
            int origin = i;
            int next = map.get(i);

            if(i == next) {
                set.add(i);
                continue;
            }

            v = new boolean[N+1];
            while(next != origin) {
                if(v[next] || set.contains(next)) break;
                list.add(next);
                v[next] = true;
                next = map.get(next);
                if(next == origin) {    //싸이클 형성됨.
                    set.addAll(list);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(set.size()).append("\n");

        for(int idx : set) sb.append(idx).append("\n");

        System.out.println(sb);

    }
}