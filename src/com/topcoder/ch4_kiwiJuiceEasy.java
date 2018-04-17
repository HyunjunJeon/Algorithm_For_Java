package com.topcoder;

/**
 * Created by jeonhj920@gmail.com on 2018. 4. 17.
 * Blog : http://HyunjunJeon.github.io
 * Github : http://github.com/HyunjunJeon
 */

/*
     키위주스를 미리 따라놓은
        병의 용량 : capacities[]
        키위주스의 양: bottles[]
        (용량 >= 키위주스의 양) 이 항상 성립해야함

     병 속 키위주스를 재분배 하기 위해서 M회 조작 시행
        i번째 조작 => 병 fromId[i] 에서 toId[i] 까지 키위 주스를 '추가로' 넣어주는 것

     fromId가 비어있거나 toId가 꽉차게되면 더이상 주스 넣기 X

     >> N개의 요소를 가진 정수 배열 int[]로 리턴
     >> 배열 i번째 요소는 모든 주스를 쏟는 작업이 완료되고 i번째 병에 남아있는 주스의 양

     - 제약 조건
        1. capacities
            2~50개 요소가 있는 배열, 각 요소는 1 ~ 1000000 사이의 값
        2. bottles
            capacities와 같은 수의 요소가 있는 배열, bottle[i]는 capacities[i]에 들어있는 주스
        3. fromId
            1~50개 요소가 있는 배열 ( capacities보다 -1임 )
        4. toId
            fromId와 같은 수의 요소가 있는 배열
 */
public class ch4_kiwiJuiceEasy {

    /*
        예시 데이터 참고해서 생각해보기
        0)
            capacities = {20,20}
            bottles = {5, 8}
            fromId = {0}
            toId = {1}
            Returns = {0,13}
            0번째 병의 주스를 1번째 병에 다 따르기 때문에 0번째는 0이되고 1번째는 13. capacities를 넘지 않기 때문에 OK
        1)
            capacities = {10,10}
            bottles = {5, 8}
            fromId = {0}
            toId = {1}
            Returns = {3,10}
            1번째 병이 가득 찰 때까지 따릅니다.
        2)
            capacities = {30,20,10}
            bottles = {10, 5, 5}
            fromId = {0,1,2}
            toId = {1,2,0}
            Returns = {10,10,0}
        3)
            capacities = {214,35,86,58,25,62}
            bottles = {6,34,27,38,9,60}
            fromId = {1,2,4,5,3,3,1,0}
            toId = {0,1,2,4,2,5,3,1}
            Returns = {}
        4)
            capacities = {700000,800000,900000,1000000}
            bottles = {478478,478478,478478,478478}
            fromId = {2,3,2,0,1}
            toId = {0,1,1,3,2}
            Returns = {0,13}

     */
    /*
        주스를 옮기는 작업
        1. 주스를 모두 옮겼는데 넘치지 않고 전부 들어간 경우
            옮길 주스의 양 <= 기존 병의 남은 용량
        2. 주스를 옮기는데 넘치는 경우
            옮길 주스의 양 > 기존 병의 남은 용량
     */
    public int[] thePouring1(int[] capacities, int[] bottles, int[] fromId, int[] toId){
        for(int i = 0; i < fromId.length; i++){
            int f = fromId[i];
            int t = toId[i];
            int space = capacities[i]-bottles[i];

            if(bottles[f] <= space){
                bottles[t] += bottles[f];
                bottles[f] = 0;
            }else {
                bottles[t] += space;
                bottles[f] -= space;
            }
        }
        return bottles;
    }
    public int[] thePouring2(int[] capacities, int[] bottles, int[] fromId, int[] toId){
        for(int i = 0; i < fromId.length; i++){
            int f = fromId[i];
            int t = toId[i];
            int space = capacities[i]-bottles[i];
            // 옮길 주스의 양 vs 기존 주스 병의 남은 용량
            // 둘 중 작은 것이 이동량
            int vol = Math.min(bottles[f], space);

            bottles[f] -= vol;
            bottles[t] += vol;
        }
        return bottles;
    }
    public int[] thePouring3(int[] capacities, int[] bottles, int[] fromId, int[] toId){
        // 1번에서 사용한 조건 분기에서 실수 발생 가능성
        // 옮길 주스와 기존 주스 양의 총합은 일정
        // 옮길 주스는 주스 총량과 기존 주스 병의 용량 중에 작은 값
        for(int i = 0; i < fromId.length; i++){
            int sum = bottles[fromId[i]] + bottles[toId[i]]; // 부어줄 곳 + 받을 곳 키위주스 총량
            bottles[toId[i]] = Math.min(sum, capacities[toId[i]]);
            bottles[fromId[i]] = sum - bottles[toId[i]];
        }
        return bottles;
    }
    public static void main(String[] args) {
        /*
            FeedBack
                1. 문제 이해 후 '손'으로 꼭 계산해볼 것
                2. 코딩이 오래 걸린다면 다시 한 번 '손'으로 계산해볼 것
                3. 조건문을 되도록 적게 사용할 것 ( 버그의 가능성이 높음 )
         */
    }
}
