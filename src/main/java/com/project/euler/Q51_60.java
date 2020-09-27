package com.project.euler;

import com.project.euler.utils.Utility;

import java.io.IOException;

public class Q51_60 {
    public static void main(String[] args) throws IOException {
        Q51_60 q51_60 = new Q51_60();
        System.out.println("Question 52: " + question52());
        System.out.println("Question 53: " + question53());
        System.out.println("Question 55: " + question55());
        System.out.println("Question 56: " + question56());
        System.out.println("Question 57: " + question57());
        System.out.println("Question 58: " + question58());
        System.out.println("Question 59: " + q51_60.question59());
    }

    // check Q51_60.py
    private static int question52() {
        return 142857;
    }

    private static int question53() {
        int n = 100, count = 0;
        int limit = 1000000;

        int[][] pascalTriangle = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            pascalTriangle[i][0] = 1;
            pascalTriangle[i][n] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                pascalTriangle[i][j] = Math.min(limit, pascalTriangle[i - 1][j] + pascalTriangle[i - 1][j - 1]);
                if (pascalTriangle[i][j] == limit) count++;
            }
        }

        return count;
    }

    private static int question55() {
        int count = 0;
        for (int i = 1; i <= 10000; i++) {
            if (isLychrel(i)) count++;
        }

        return count;
    }

    private static boolean isLychrel(int n) {
        StringBuilder num = new StringBuilder(String.valueOf(n));
        for (int i = 0; i < 50; i++) {
            num = Utility.sumNumbers(num, new StringBuilder(num).reverse()).reverse();
            if (Utility.isStringPalindrome(num.toString()))
                return false;
        }

        return true;
    }

    // check Q51_60.py
    private static int question56() {
        return 972;
    }

    // check Q51_60.py
    private static int question57() {
        return 153;
    }

    private static int question58() {
        int rowInterval = 2;
        int num = 1, count = 0;
        for (int i = 3; ; i += 2) {
            for (int j = 0; j < 4; j++) {
                num += rowInterval;
                if (Utility.isPrime(num)) count++;
                if ((float) count / (i * 2 - 1) < 0.1)
                    return i;
            }
            rowInterval += 2;
        }
    }

    // todo:: solve
    private long question59() throws IOException {
        String[] splits = new Utility().readFromInputStream("p059_cipher.txt").split(",");

        for (int i = 97; i <= 122; i++) {
            for (int j = 97; j <= 122; j++) {
                for (int k = 97; k <= 122; k++) {
                    long sum = 0;
                    int[] t = new int[]{i, j, k};

                    for (int m = 0; m < splits.length-1; m++) {
                        int c = Integer.parseInt(splits[m]) ^ t[m % 3];
                        if (!((32 <= c && c <= 90) || (97 <= c && c <= 122)))
                            break;
                        else {
                            sum += c;
                            if(m==splits.length-1) return sum;
                        }
                    }
                }
            }
        }

        return 0;
    }
}
