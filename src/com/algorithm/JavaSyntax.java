package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by jeonhj920@gmail.com on 2018. 4. 10.
 * Blog : http://HyunjunJeon.github.io
 * Github : http://github.com/HyunjunJeon
 */
public class JavaSyntax {
//    public static void main(String[] args) {
//        hashSetTest();
//    }
//    // 최대힙 구현을 위한 compare
//    static class Compare implements Comparator<Integer>{
//        public int compare(Integer one, Integer two){
//            return two.compareTo(one);
//        }
//    }

    // 최소/최대힙
    public static void minAndMaxHeap(){
        Scanner sc = new Scanner(System.in);
        //Compare cmp = new Compare()
        //PriorityQueue<Integer> q = new PriorityQueue<>(1,cmp);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int n = sc.nextInt();
        while(n --> 0){
            int x = sc.nextInt();
            if(x == 0){
                if(q.isEmpty()){
                    System.out.println(0);
                }else{
                    System.out.println(q.poll());
                }
            }else{
                q.offer(x);
            }
        }
    }

    // 듣보잡
    public static void mapTest() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]); // 듣도못한
        int m = Integer.parseInt(line[1]); // 보도못한

        Map<String,Integer> a = new TreeMap<>();

        for(int i=0; i<n; i++){
            String name = br.readLine();
            a.put(name,1); // 듣도못한 사람에게는 1
        }
        for(int i=0; i<m; i++){
            String name = br.readLine();
            Integer v = a.get(name);
            if(v == null){
                v = 0;
            }
            v += 2; // 보도못한 사람에게는 2
            a.put(name,v);
        }

        List<String> b = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : a.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value == 3){ // 그래서 합이 3인 사람을 찾음
                b.add(key);
            }
        }
        System.out.println(b.size());
        b.forEach((c)->System.out.println(c));

    }


    // HashSet
    public static void hashSetTest(){
        Set<Integer> d = new HashSet<>();
        for(int i = 10; i >= 1; i--){
            d.add(i);
        }
        d.forEach((b)->System.out.println(b));
    }

    // 수 정렬하기
    public void numberSorting(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> a = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int temp = sc.nextInt();
            a.add(temp);
        }
        Collections.sort(a);
        a.forEach((b)->System.out.println(b));
    }
}
