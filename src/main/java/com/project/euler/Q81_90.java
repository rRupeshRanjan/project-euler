package com.project.euler;

import com.project.euler.utils.Utility;

import java.io.IOException;

public class Q81_90 {
    public static void main(String[] args) throws IOException {
        Q81_90 q81_90 = new Q81_90();

        System.out.println("Question 81: " + q81_90.problem81());
    }

    private long problem81() throws IOException {
        int size = 80;
        String[] string = new Utility()
                .readFromInputStream("p081_matrix.txt")
                .split("\n");

        int[][] nums = new int[size][size];

        for(int i=0; i<size; i++) {
            String[] temp = string[i].split(",");
            for(int j=0; j<size; j++) {
                nums[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(i==0 && j==0) {
                    continue;
                } else if(i == 0) {
                    nums[i][j] += nums[i][j - 1];
                } else if(j == 0) {
                    nums[i][j] += nums[i - 1][j];
                } else {
                    nums[i][j] += Math.min(nums[i-1][j], nums[i][j-1]);
                }
            }
        }

        return nums[size-1][size-1];
    }
}
