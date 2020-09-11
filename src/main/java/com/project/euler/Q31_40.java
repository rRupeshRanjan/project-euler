package com.project.euler;

public class Q31_40 {
    public static void main(String[] args) {
        System.out.println("Q31: " + question31());
        System.out.println("Q34: " + question34());
    }

    private static int question31() {
        int[] coins = new int[] {1,2,5,10,20,50,100,200};
        int amount = 200;

        int[] dp = new int[amount+1];
        dp[0] = 1;

        for(int coin : coins) {
            for(int j = 0; j <= amount; j++) {
                if(j >= coin)
                    dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }

    private static long question34() {
        int[] factorials = new int[10];
        factorials[0] = 1;
        for(int i=1; i<10; i++)
            factorials[i] = i * factorials[i-1];

        long sum = 0;
        for(int i=10; i<=1499999; i++) {
            if(i==factorialsSum(i, factorials))
                sum += i;
        }

        return sum;
    }

    private static int factorialsSum(int num, int[] factorials) {
        int sum = 0;
        while(num > 0) {
            sum += factorials[num%10];
            num /= 10;
        }

        return sum;
    }
}
