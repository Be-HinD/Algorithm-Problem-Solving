import java.io.*;
import java.util.*;

//BOJ_3019
public class Main {
    static int c, p, res;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        arr = new int[c];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<c; i++) arr[i] = Integer.parseInt(st.nextToken());

        switch (p) {
            case 1:
                shape1();
                break;
            case 2:
                shape2();
                break;
            case 3:
                shape3();
                break;
            case 4:
                shape4();
                break;
            case 5:
                shape5();
                break;
            case 6:
                shape6();
                break;
            case 7:
                shape7();
                break;
        }

        System.out.println(res);

    }

    static void shape1() {
        res = c;
        for(int i=0; i<c-3; i++) {
            if((arr[i] == arr[i+1]) && (arr[i+1] == arr[i+2]) && (arr[i+2] == arr[i+3])) {
                res++;
            }
        }
    }
    static void shape2() {
        for(int i=0; i<c-1; i++) {
            if(arr[i] == arr[i+1]) res++;
        }
    }

    static void shape3() {
        for(int i=0; i<c-2; i++) {
            if((arr[i] == arr[i+1]) && (arr[i+1] == arr[i+2]-1)) res++;
        }

        for(int i=0; i<c-1; i++) {  //90도
            if(arr[i]-1 == arr[i+1]) res++;
        }
    }

    static void shape4() {
        for(int i=0; i<c-2; i++) {
            if((arr[i]-1 == arr[i+1]) && (arr[i+1] == arr[i+2])) res++;
        }

        for(int i=0; i<c-1; i++) {  //90도
            if(arr[i] == arr[i+1]-1) res++;
        }
    }

    static void shape5() {
        for (int i = 0; i < c - 2; i++) {
            if ((arr[i] == arr[i + 1]) && (arr[i + 1] == arr[i + 2]))
                res++;
            if ((arr[i] == arr[i + 1]+1) && (arr[i + 1] == arr[i + 2]-1))
                res++;
        }

        for (int i = 0; i < c - 1; i++) {  //90도
            if ((arr[i] == arr[i + 1] - 1) || (arr[i] - 1 == arr[i + 1]))
                res++;
        }
    }

    static void shape6() {
        for (int i = 0; i < c - 2; i++) {
            if ((arr[i] == arr[i + 1]) && (arr[i + 1] == arr[i + 2]))
                res++;
            if ((arr[i] == arr[i + 1]-1) && (arr[i + 1] == arr[i + 2]))
                res++;
        }

        for(int i=0; i<c-1; i++) {
            if(arr[i] == arr[i+1]) res++;
            if(arr[i] == arr[i+1]+2) res++;
        }
    }

    static void shape7() {
        for (int i = 0; i < c - 2; i++) {
            if ((arr[i] == arr[i + 1]) && (arr[i + 1] == arr[i + 2]))
                res++;
            if ((arr[i] == arr[i + 1]) && (arr[i + 1] == arr[i + 2]+1))
                res++;
        }

        for(int i=0; i<c-1; i++) {
            if(arr[i] == arr[i+1]) res++;
            if(arr[i]+2 == arr[i+1]) res++;
        }
    }
}