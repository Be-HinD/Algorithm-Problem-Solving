    import java.io.*;
    import java.util.*;

    //BOJ_19942 다이어트
    public class Main {
        static class INFO {
            int mp;
            int mf;
            int ms;
            int mv;
            int value;

            public INFO(int mp, int mf, int ms, int mv, int value) {
                this.mp = mp;
                this.mf = mf;
                this.ms = ms;
                this.mv = mv;
                this.value = value;
            }
        }
        static int N, res, resP;
        static int[] target, ans;
        static PriorityQueue<Integer> ress = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        static INFO[] arr;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(br.readLine());

            target = new int[4];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++) {
                target[i] = Integer.parseInt(st.nextToken());
            }

            arr = new INFO[N+1];

            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                int mp = Integer.parseInt(st.nextToken());
                int mf = Integer.parseInt(st.nextToken());
                int ms = Integer.parseInt(st.nextToken());
                int mv = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                INFO info = new INFO(mp, mf, ms, mv, value);
                arr[i] = info;
            }


            ans = new int[N+1];
            res = Integer.MAX_VALUE;

            dfs(0, 0, 0, 0, 0, 0, 0, 0);


            if(res == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
            System.out.println(res);

            while(!ress.isEmpty()) {
                System.out.print(ress.poll() + " ");
            }

        }

        private static void dfs(int idx, int p, int mp, int mf, int ms, int mv, int sum, int prev) {
            //기저
            ans[idx] = prev;
            if(idx != 0) {
                if (mp >= target[0] && mf >= target[1] && ms >= target[2] && mv >= target[3]) {
                    // 최종 가치 갱신 및 비교
                    if (res == sum) {
                        //갱신
                        PriorityQueue<Integer> ad = new PriorityQueue<>(new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return o1 - o2;
                            }
                        });

                        resP = p;
                        ad.offer(idx);
                        while(true) {
                            idx = ans[idx];
                            if(idx == 0) break;
                            ad.offer(idx);
                        }

                        boolean prevF = false;
                        boolean curF = false;

                        if(ress.isEmpty()) {
                            while(!ad.isEmpty()) {
                                ress.offer(ad.poll());
                            }
                            return;
                        }

                        for(int i=0; i<ress.size(); i++) {
                            int cur = ress.poll();
                            if(ad.isEmpty()) break;
                            int diff = ad.poll();

                            if(cur > diff) {
                                if(!prevF) curF = true;
                            }
                            else if(cur < diff) {
                                if(!curF) prevF = true;
                            }
                            ress.offer(cur);
                            ad.offer(diff);
                        }


                        if(curF) {
                            ress.clear();
                            while(!ad.isEmpty()) {
                                ress.offer(ad.poll());
                            }
                        }



                    }
                    else if(res > sum) {
                        res = sum;
                        PriorityQueue<Integer> ad = new PriorityQueue<>(new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return o1 - o2;
                            }
                        });

                        resP = p;
                        ad.offer(idx);
                        while(true) {
                            idx = ans[idx];
                            if(idx == 0) break;
                            ad.offer(idx);
                        }
                        ress.clear();
                        while(!ad.isEmpty()) {
                            ress.offer(ad.poll());
                        }
                    }
                    return;
                }
            }

            //동작부
            for(int i=idx; i<=N; i++) {
                if(idx == i) continue;
                dfs(i, p+1, mp + arr[i].mp, mf + arr[i].mf, ms + arr[i].ms, mv + arr[i].mv, sum + arr[i].value, idx);
            }

        }
    }