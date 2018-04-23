package com.topcoder;

import java.util.Arrays;

/*
    숫자 리스트 입력 ( 2^62를 넘지않는 중복 가능한 숫자들의 조합 ) int[] numbers
    입력 리스트에서 값 1개 선택 후 +1
    리스트의 내부 숫자의 곱이 가장 커져야 함
    리턴 : 곱의 최댓값
 */
public class ch5_2_Cryptography {
    /*
        전체 탐색
        1. +1 숫자를 선택
        2. 전체 곱 계산 후 최댓값 리턴
     */

    public static long encrypt1(int[] numbers){
        long ans = 0;

        for(int i = 0; i<numbers.length; i++){
            long temp = 1; // 곱셈에 영향을 주지 않음
            for(int j = 0; j<numbers.length; j++) {
                if (i == j) { // 특정 위치 숫자증가시키기 위해서
                    temp *= (numbers[j] + 1);
                } else { // 증가하지 않은 나머지들 곱해줌
                    temp *= numbers[j];
                }
            }
            ans = Math.max(ans, temp);
        }

        return ans;
    }

    /*
        Example 분석 및 수학적 방법에 의해
        >> 가장 작은 수에 +1하면 됌 ( 중복된 숫자들만 해결하면 될 것 )
     */
    public static long encrypt2(int[] numbers){
        long ans = 1; // 곱하기에 영향을 주지 않는 1로 초기화
        Arrays.sort(numbers); // 오름차순 정렬 -> 중복은 상관없음. 1,1,1,1,1,2,3,4,5 일경우 1 -> 2가 되도 결과는 같음
        numbers[0]++; // 가장 작은 요소 +1 시키고
        for(int a : numbers){ // 배열 요소들 곱해서 ans에 저장
            ans *= a;
        }
        return ans;
    }
}
