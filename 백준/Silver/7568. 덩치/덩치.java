import java.io.*;
import java.util.*;

//BOJ_7568
public class Main {
    static class Person {
        int x;
        int y;
        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Person[] arr = new Person[n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Person(x,y);
        }

        for(int i=0; i<n; i++) {
            int cnt = 0;
            for(int j=0; j<n; j++) {
                if(i == j) continue;
                if(arr[i].x < arr[j].x && arr[i].y < arr[j].y) cnt++;
            }
            System.out.print(cnt + 1 + " ");
        }
    }
}