package com.topcoder;

import java.util.Arrays;
import java.util.HashMap;

/*
    각각의 친구는 2개의 주제에만 관심이 있고 다른 주제로 이야기 하는 것을 싫어함
    파티를 즐기기 위해서는 친구 모두가 공통 관심 주제가 있어야 함
    String[] 두개 first,second
    first[i] / second[i] 는 i번째 사람이 흥미있는 주제
    >> 즐거운 파티가 되기 위해서 초대할 수 있는 사람은 최대 몇 명인지 리턴
 */
public class ch5_1_InterestingParty {
    /*
        예시 데이터

        0)  first = {"fishing","gardening","swimming","fishing"}
            second = {"hunting","fishing","fishing","biting"}
            Returns = 4

            >> 모두 fishing 에 관심이 많음

        1)  first = {"variety","diversity","loquacity","courtesy"}
            second = {"talking","speaking","discussion","meeting"}
            Returns = 1

            >> 아무도 중복되는 관심이 없음 -> 예외로 처리해야 할 부분

        2)  first = {"snakes","programming","cobra","monty"}
            second = {"python","python","anaconda","python"}
            Returns = 3
     */

    /*
        내가 생각해본 문제풀이 전략
        1. first배열의 keyword와 second 배열의 keyword가 일치하는 갯수 중 가장 많은 것
            -> 배열 요소의 개수세기 형태로 측정 가능할 듯

     */

    /*
        책 기본 풀이
        1. 화제를 순서대로 선택
        2. 해당 화제에 몇명이 흥미 있는지 조사
     */
    public static int bestInvitation1 (String[] first, String[] second){
        int max = 0;
        for(int i = 0; i<first.length; i++){
            int f = 0;
            int s = 0;

            for(int j = 0; j<first.length; j++){
                if(first[i].equals(first[j])) f++; // 첫 번째 관심사 대비 첫번째 관심사가 같을 때
                if(first[i].equals(second[j])) f++; // 첫 번째 관심사 대비 두번째 관심사가 같을 때
                if(second[i].equals(first[j])) s++; // 두 번째 관심사 대비 첫번째 관심사가 같을 때
                if(second[i].equals(second[j])) s++; // 두 번째 관심사 대비 두번째 관심사가 같을 때
            }
            max = Math.max(f,max);
            max = Math.max(s,max); // f 와 s 를 단순 max비교하면 앞에 누적된 것 중에서 누락이 발생하니까
        }
        return max;
    }

    /*
     연관배열을 사용하여 for문 중첩을 삭제해보자
     연관 배열을 통해 주제에 관심 있는지 여부만 Check

     각 화제를 0 으로 초기화, 각 사람들이 관심 가지고 있는 화제 +1
    */

    public static int bestInvitation2 (String[] first, String[] second){
        int max = 0;

        HashMap<String,Integer> dic = new HashMap<>();

        for(int i = 0 ; i<first.length; i++){
            dic.put(first[i],0);
            dic.put(second[i],0);
        }

        for(int i = 0 ; i<first.length; i++){
            dic.put(first[i], dic.get(first[i])+1);
            dic.put(second[i], dic.get(second[i])+1);
        }

        for(String key : dic.keySet()){
            max = Math.max(max, dic.get(key));
        }

        return max;
    }
    public static void main(String[] args){
        String[] first = {"snakes","programming","cobra","monty"};
        String[] second = {"python","python","anaconda","python"};
        int ans = bestInvitation1(first,second);
        System.out.println(ans);

        int ans2 = bestInvitation2(first,second);
        System.out.println(ans2);
    }
}
