package com.project.euler;

public class Q51_60 {
    public static void main(String[] args) {
        System.out.println("Question 52: " + question52());
        System.out.println("Question 53: " + question53());
    }

    // check Q51_60.py
    private static int question52() {
        return 142857;
    }

    private static int question53() {
        int n = 100, count = 0;
        int limit = 1000000;

        int[][] pascalTriangle = new int[n+1][n+1];
        for (int i=0; i<=n; i++) {
            pascalTriangle[i][0] = 1;
            pascalTriangle[i][n] = 1;
        }

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                pascalTriangle[i][j] = Math.min(limit, pascalTriangle[i-1][j] + pascalTriangle[i-1][j-1]);
                if(pascalTriangle[i][j] == limit) count++;
            }
        }

        return count;
    }


}
