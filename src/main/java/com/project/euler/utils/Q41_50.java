package com.project.euler.utils;

import java.util.ArrayList;
import java.util.List;

public class Q41_50 {
    public static void main(String[] args) {
        System.out.println("Question 41: " + question41());
    }

    private static int question41() {
        List<Integer> nums = new ArrayList<>();
        nums.addAll(Utility.generatePermutations(new int[]{1,2,3,4}));
        nums.addAll(Utility.generatePermutations(new int[]{1,2,3,4,5,6,7}));
        int result = Integer.MIN_VALUE;

        for(int num: nums) {
            if(Utility.isPrime(num))
                result = Math.max(result, num);
        }

        return result;
    }
}
