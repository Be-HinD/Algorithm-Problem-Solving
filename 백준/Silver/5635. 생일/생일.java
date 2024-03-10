import java.io.*;
import java.util.*;

//BOJ_5635 생일
public class Main {
    static class Member {
        String name;
        int year;
        int month;
        int day;

        public Member(String name, int year, int month, int day) {
            this.name = name;
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Member> low = new PriorityQueue<>(new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                if(o1.year == o2.year) {
                    if(o1.month == o2.month) {
                        return o1.day - o2.day;
                    }
                    else {
                        return o1.month - o2.month;
                    }
                }
                return o1.year - o2.year;
            }
        });

        PriorityQueue<Member> high = new PriorityQueue<>(new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                if(o2.year == o1.year) {
                    if(o2.month == o1.month) {
                        return o2.day - o1.day;
                    }
                    else {
                        return o2.month - o1.month;
                    }
                }
                return o2.year - o1.year;
            }
        });

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String people = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            low.add(new Member(people, year, month, day));
            high.add(new Member(people, year, month, day));
        }

        Member higher = high.poll();
        Member lower = low.poll();

        System.out.println(higher.name);
        System.out.println(lower.name);
    }
}