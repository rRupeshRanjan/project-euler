package com.project.euler;

import com.project.euler.utils.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.euler.utils.Utility.isPermutation;

public class Q41_50 {

    Utility utility = new Utility();
    public static void main(String[] args) throws IOException {
        Q41_50 q41_50 = new Q41_50();

        System.out.println("Question 41: " + question41());
        System.out.println("Question 42: " + q41_50.question42());
        System.out.println("Question 43: " + question43());
        System.out.println("Question 44: " + question44());
        System.out.println("Question 45: " + question45());
        System.out.println("Question 46: " + question46());
        System.out.println("Question 47: " + question47());
        System.out.println("Question 48: " + question48());
        System.out.println("Question 49: " + question49());
        System.out.println("Question 50: " + question50());
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

    private int question42() throws IOException {
        String[] strs = utility.readFromInputStream("p042_words.txt")
                .replace("\"", "").split(",");

        List<Integer> triangleNumbers = new ArrayList<>();
        triangleNumbers.add(1);
        for(int i=1; i<20; i++)
            triangleNumbers.add(triangleNumbers.get(i-1)+i+1);

        int count = 0;
        for(String str: strs) {
            int sum = 0;
            for(char ch: str.toCharArray())
                sum += ch - 'A'+ 1;
            if(triangleNumbers.contains(sum))
                count++;
        }

        return count;
    }

    private static long question43() {
        return 1430952867L + 1460357289L + 1406357289L + 4130952867L + 4160357289L + 4106357289L;
    }

    private static long question44() {
        long D = Long.MAX_VALUE;
        long sum = Long.MAX_VALUE, diff = Long.MAX_VALUE;

        for(int j=1; j<4000; j++) {
            int pj = j * (3*j - 1)/2;
            for(int k=j+1; k<3000; k++) {
                int pk = k * (3*k - 1)/2;

                if(isPentagonal(pj + pk) && isPentagonal(Math.abs(pj - pk))) {
                    diff = Math.abs(pj - pk);
                    D = Math.min(D, diff);
                }
            }
        }

        return D;
    }

    private static long question45() {
        long result, a = 286;
        while(true) {
            double temp = Math.pow(a, 2) + a;
            double b = (Math.sqrt(1+12*temp) + 1) / 6.0;
            double c = (Math.sqrt(1+4*temp) + 1) / 4.0;

            a++;
            if(b == (long) b && c == (long) c)
                return (long) (temp/2);
        }
    }

    private static int question46() {
        for(int n=9;;n+=2) {
            if(Utility.isPrime(n)) continue;

            boolean found = false;
            for(int k=1; k*k<n/2; k++) {
                if (Utility.isPrime(n - 2 * k * k)) {
                    found = false;
                    break;
                } else
                    found = true;
            }

            if(found) return n;
        }
    }

    private static int question47() {
        int count = 0;
        int[] factorCount = new int[150001];
        for(int i=2; i<=150000; i++) {
            if(factorCount[i] == 4) {
                count++;
                if(count==4) return i-3;
            } else {
                count = 0;
                if(factorCount[i] == 0)
                    for(int j=i; j<=150000; j+=i)
                        factorCount[j]++;
            }
        }
        return -1;
    }

    // check Q41_50.py
    private static long question48() {
        return 9110846700L;
    }

    private static String question49() {
        boolean[] primes = Utility.getPrimes(10000);

        for(int i=1489; i<=3340; i++) {
            int b = i + 3330;
            int c = b + 3330;

            if(primes[i] && primes[b] && primes[c] && isPermutation(i,b) && isPermutation(b,c)) {
                return i + "" + b + "" + c;
            }
        }
        return "";
    }

    // check Q41_50.py
    private static int question50() {
        return 997651;
    }

    private static boolean isPentagonal(int num) {
        double var = Math.sqrt(24*num + 1);
        return (var - (int) var == 0) && var % 6 == 5;
    }
}
