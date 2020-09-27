package com.project.euler;

import com.project.euler.utils.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q61_70 {
    public static void main(String[] args) throws IOException {
        Q61_70 q61_70 = new Q61_70();

        System.out.println("Problem 62: " + problem62());
        System.out.println("Problem 63: " + problem63());
        System.out.println("Problem 67: " + q61_70.problem67());
    }

    private static long problem62() {
        return 127035954683L;
    }

    /*
     * for n^k to be of k digits, k = log(10) to base (10/n)
     * */
    private static int problem63() {
        int counter = 0;
        for(int n=1; n<=9; n++) {
            counter += Math.floor(Math.log(10) / Math.log(10.0 / n));
        }
        return counter;
    }

    private int problem67() throws IOException {
        String list = new Utility().readFromInputStream("p067_triangle.txt");
        List<List<Integer>> nums = new ArrayList<>();
        for(String line: list.split("\n")) {
            nums.add(Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }

        for(int i=nums.size()-2; i>=0; i--) {
            List<Integer> integers = nums.get(i);
            for(int j=0; j<=i; j++) {
                int temp = integers.get(j) + Math.max(nums.get(i+1).get(j), nums.get(i+1).get(j+1));
                integers.set(j, temp);
            }
            nums.set(i, integers);
        }

        return nums.get(0).get(0);
    }
}
