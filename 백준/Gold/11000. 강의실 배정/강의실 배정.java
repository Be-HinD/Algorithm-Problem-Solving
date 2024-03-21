import java.util.*;
import java.io.*;

public class Main {
   static int N;
    static Comparator<int[]> Comp;
    static PriorityQueue<int[]> ClassList;
    
    public static void main(String[] args) throws IOException{
        init();
        solv();
    }
    
    private static void init()throws IOException{
        N = readInt();
        Comp = new Comparator<int[]>(){
            @Override
            public int compare(int[] x1, int[] x2){
                return x1[0] - x2[0];   //끝시간은 후의 PQ에서 정렬할 거니까까
            }
        };
        ClassList = new PriorityQueue<>(Comp);
        while(N-- > 0){
            ClassList.add(new int [] {readInt(), readInt()});
        }
    }
    
    private static void solv() throws IOException{
        getClassRoomCount();
    }
    
    private static void getClassRoomCount(){
        PriorityQueue<Integer> endTimeList = new PriorityQueue<>();
        endTimeList.add(0);
        //=> 가장 빨리 끝나는 하나의 강의실로도 커버가 안되면 뒤에 강의실도 다 안되는 거니까
        while(!ClassList.isEmpty()){
            int [] nextClass = ClassList.poll();    //다음 강의의
            if(endTimeList.peek() <= nextClass[0]){ //가장 빨리 끝나는 강의 다음으로 붙일 수 있음
                endTimeList.poll(); //해당 강의실 시간 없애기
            }
            endTimeList.add(nextClass[1]);//새로운 강의 끝시간 추가
        }
        System.out.println(endTimeList.size());
    }
    
    private static int readInt()throws IOException{
        int c, n = System.in.read() & 15;   //n은 숫자 한 자리
        while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c&15);
        return n;
    }
}