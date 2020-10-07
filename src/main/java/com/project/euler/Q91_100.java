package com.project.euler;

import com.project.euler.utils.Utility;

import java.io.IOException;

public class Q91_100 {
    public static void main(String[] args) throws IOException {
        Q91_100 q91_100 = new Q91_100();
        System.out.println("Problem 91: " + problem91());
        System.out.println("Problem 92: " + problem92());
        System.out.println("Problem 94: " + problem94());
        System.out.println("Problem 99: " + q91_100.problem99());
    }

    private static int problem91() {
        int size = 50;
        int count = 3*size*size;
        for(int i=1; i<=size; i++) {
            for(int j=1; j<=size; j++) {
                int gcd = Utility.gcd(i, j);
                count += Math.min(j * gcd / i, (size-i) * gcd / j)*2;
            }
        }

        return count;
    }

    private static int problem92() {
        int result = 0, target = 10000000, cachesize = 568;
        boolean[] cache = new boolean[cachesize+1];

        for (int i = 1; i < cachesize; i++) {
            int sequence = sumSquareDigits(i);
            while (sequence > i && sequence != 89) {
                sequence = sumSquareDigits(sequence);
            }

            if (cache[sequence] || sequence == 89) {
                result++;
                cache[i] = true;
            }
        }

        for (int i = cachesize; i <= target; i++) {
            if (cache[sumSquareDigits(i)]) {
                result++;
            }
        }

        return result;
    }

    private static long problem94() {
        long x = 2;
        long y = 1;
        long limit = 1000000000;
        long result = 0;

        while(true){
            // b = a+1
            long aTimes3 = 2 * x - 1;
            long areaTimes3 = y * (x - 2);
            if (aTimes3 > limit) break;

            if (aTimes3 > 0 && areaTimes3 > 0 && aTimes3 % 3 == 0 && areaTimes3 % 3 == 0) {
                long a = aTimes3 / 3;
                result += 3 * a + 1;
            }

            //b = a-1
            aTimes3 = 2 * x + 1;
            areaTimes3 = y * (x + 2);

            if (aTimes3 > 0 && areaTimes3 > 0 && aTimes3 % 3 == 0 && areaTimes3 % 3 == 0) {
                long a = aTimes3 / 3;
                result += 3 * a - 1;
            }

            long nextx = 2 * x + y * 3;
            long nexty = y * 2 + x;

            x = nextx;
            y = nexty;
        }

        return result;
    }

    private int problem99() throws IOException {
        String lines = new Utility().readFromInputStream("p099_base_exp.txt");
        int[][] nums = new int[1000][2];
        int i = 0;
        for(String str: lines.split("\n")) {
            nums[i][0] = Integer.parseInt(str.split(",")[0]);
            nums[i][1] = Integer.parseInt(str.split(",")[1]);
            i++;
        }

        int maxRow = 0;
        double maxPow = Double.MIN_VALUE;
        for(i=0; i<1000; i++) {
            double curr = nums[i][1] * Math.log10(nums[i][0]);
            if(curr > maxPow) {
                maxRow = i;
                maxPow = curr;
            }
        }
        return maxRow+1;
    }

    private static int sumSquareDigits(int n) {
        int sum = 0;
        while(n > 0) {
            sum += (n%10) * (n%10);
            n /= 10;
        }
        return sum;
    }
}
