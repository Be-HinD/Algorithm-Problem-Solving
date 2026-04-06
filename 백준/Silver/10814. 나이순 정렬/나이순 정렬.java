import java.io.*;
import java.util.*;

public class Main {
    static class Member implements Comparable<Member>{
        int age;
        int idx;
        String name;
        public Member(int age, int idx, String name) {
            this.age = age;
            this.idx = idx;
            this.name = name;
        }

        @Override
        public int compareTo(Member o) {
            if(this.age == o.age) {
                return this.idx - o.idx;
            }
            return this.age - o.age;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        List<Member> list = new ArrayList<>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());

            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Member(age, i, name));
        }

        Collections.sort(list);

        for(Member cur : list) {
            System.out.println(cur.age + " " + cur.name);
        }
    }
}