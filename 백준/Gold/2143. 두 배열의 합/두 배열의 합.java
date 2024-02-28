    import java.io.*;
    import java.util.*;

    //BOJ_2143 두 배열의 합
    public class Main {
        static int N;
        static long res;
        static int[] a, b;
        static long[] na, nb;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            HashMap<Long, Integer> A_data = new HashMap<>();
            HashMap<Long, Integer> B_data = new HashMap<>();

            na = new long[500600];
            nb = new long[500600];
            N = Integer.parseInt(br.readLine());

            int lengA = Integer.parseInt(br.readLine());
            a = new int[lengA];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<lengA; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            int lengB = Integer.parseInt(br.readLine());
            b = new int[lengB];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<lengB; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }



            //A 누적합을 전부 계산
            int p = 0;
            int naCnt = 0;
            for(int i=0; i<lengA; i++) {
                for(int j=i; j<lengA; j++) {
                    if(i==j) {
                        //초기화
                        na[p++] = a[i];
                    }
                    else {
                        na[p++] = na[p-2] + a[j];
                    }
                    if(A_data.get(na[p-1]) != null) {
                        int idx = A_data.get(na[p-1]);
                        A_data.put(na[p-1], ++idx);
                    }
                    else A_data.put(na[p-1], 1);
                    naCnt++;
                }
            }

            //B 누적합을 전부 계산
            p = 0;
            for(int i=0; i<lengB; i++) {
                for(int j=i; j<lengB; j++) {
                    if(i==j) {
                        //초기화
                        nb[p++] = b[i];
                    }
                    else {
                        nb[p++] = nb[p-2] + b[j];
                    }
                    if(B_data.get(nb[p-1]) != null) {
                        int idx = B_data.get(nb[p-1]);
                        B_data.put(nb[p-1], ++idx);
                    }
                    else B_data.put(nb[p-1], 1);
                }
            }

            for(int i=0; i<naCnt; i++) {
                long idx = na[i];
                long key = N - idx;
                if(B_data.get(key) != null) {
                    res += B_data.get(key);
                }
            }

            System.out.println(res);

        }
    }