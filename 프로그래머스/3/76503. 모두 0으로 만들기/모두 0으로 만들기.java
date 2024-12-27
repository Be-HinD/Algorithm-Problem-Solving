/**
키워드
가중치가 부여된 트리
모든 점들의 가중치를 0으로

접근법
정점 <= 300,000 //완전탐색으로는 불가능
음수 가중치 가능
간선 = 정점-1개
전처리 : a의 모든 가중치가 0인지 확인 후 판별
리프(혹은 루트)에서부터 0으로 만들기 <<
**/

import java.util.*;
class Solution {
    static List<Integer>[] list;
    static long[] value;
    static long res;
    static int[] v;
    public long solution(int[] a, int[][] edges) {
        
        //제로섬 전처리
        value = new long[a.length];
        
        int sum = 0;
        list = new ArrayList[a.length];
        
        for(int i=0; i<a.length; i++) {
            value[i] = a[i];
            sum += a[i];
            list[i] = new ArrayList<Integer>();
        }
        if(sum != 0) return -1;
        
        for(int i=0; i<edges.length; i++) {
            list[edges[i][0]].add(edges[i][1]);
	    	list[edges[i][1]].add(edges[i][0]);
        }
        
        v = new int[a.length];
        
        makeZero(0);

        if(value[0] != 0) return -1;
        // System.out.println(Arrays.toString(value));
        return res;
    }
    
    static long makeZero(int node) {
        v[node] = 1;
        for(int i=0; i<list[node].size(); i++){
            int next = list[node].get(i);
            if(v[next]==1) continue;
            value[node] += makeZero(next);
            
        }
        long num = value[node];
        res += Math.abs(num);
        return num;
    }
}