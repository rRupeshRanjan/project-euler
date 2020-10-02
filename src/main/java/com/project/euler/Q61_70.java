package com.project.euler;

import com.project.euler.utils.ContinuedFractionBig;
import com.project.euler.utils.Convergents;
import com.project.euler.utils.Utility;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q61_70 {
    public static void main(String[] args) throws IOException {
        Q61_70 q61_70 = new Q61_70();

        System.out.println("Problem 62: " + problem62());
        System.out.println("Problem 63: " + problem63());
        System.out.println("Problem 64: " + problem64());
        System.out.println("Problem 65: " + problem65());
        System.out.println("Problem 67: " + q61_70.problem67());
        System.out.println("Problem 69: " + problem69());
        System.out.println("Problem 70: " + problem70());
    }

    // check Q61_70.py
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

    public static int problem64() {
        int oddConv = 0;
        for(int j=2; j<=10000; j++) {
            List<Integer> cf;
            try {
                cf = ContinuedFractionBig.continuedFractionSqrtBig(j);
                if(cf.size()%2==0) oddConv++;
            } catch(IllegalArgumentException ignored) { }
        }

        return oddConv;
    }

    private static int problem65() {
        int EXTRA = 5;
        int NTERMS = 100;

        // Returns an array list of size NTERMS+1
        List<Integer> cf = Convergents.continuedFractionE(NTERMS+EXTRA);
        BigInteger[] conv = Convergents.convergents(cf,NTERMS);

        return Utility.sumDigits(conv[0].toString());
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

    private static int problem69() {
        int n = 1, k = 0;
        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 21, 23, 29, 31};
        while (primes[k] * n <= 1000000)
            n*= primes[k++];
        return n;
    }

    private static long problem70() {
        int limit = 10000000-1;
        long best = 1;
        double bestRatio = Double.MAX_VALUE;

        int[] primes = new int[]{2003, 2011, 2017, 2027, 2029, 2039, 2053, 2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111,
                2113, 2129, 2131, 2137, 2141, 2143, 2153, 2161, 2179, 2203, 2207, 2213, 2221, 2237, 2239, 2243, 2251, 2267,
                2269, 2273, 2281, 2287, 2293, 2297, 2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357, 2371, 2377, 2381, 2383,
                2389, 2393, 2399, 2411, 2417, 2423, 2437, 2441, 2447, 2459, 2467, 2473, 2477, 2503, 2521, 2531, 2539, 2543,
                2549, 2551, 2557, 2579, 2591, 2593, 2609, 2617, 2621, 2633, 2647, 2657, 2659, 2663, 2671, 2677, 2683, 2687,
                2689, 2693, 2699, 2707, 2711, 2713, 2719, 2729, 2731, 2741, 2749, 2753, 2767, 2777, 2789, 2791, 2797, 2801,
                2803, 2819, 2833, 2837, 2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903, 2909, 2917, 2927, 2939, 2953, 2957,
                2963, 2969, 2971, 2999, 3001, 3011, 3019, 3023, 3037, 3041, 3049, 3061, 3067, 3079, 3083, 3089, 3109, 3119,
                3121, 3137, 3163, 3167, 3169, 3181, 3187, 3191, 3203, 3209, 3217, 3221, 3229, 3251, 3253, 3257, 3259, 3271,
                3299, 3301, 3307, 3313, 3319, 3323, 3329, 3331, 3343, 3347, 3359, 3361, 3371, 3373, 3389, 3391, 3407, 3413,
                3433, 3449, 3457, 3461, 3463, 3467, 3469, 3491, 3499, 3511, 3517, 3527, 3529, 3533, 3539, 3541, 3547, 3557,
                3559, 3571, 3581, 3583, 3593, 3607, 3613, 3617, 3623, 3631, 3637, 3643, 3659, 3671, 3673, 3677, 3691, 3697,
                3701, 3709, 3719, 3727, 3733, 3739, 3761, 3767, 3769, 3779, 3793, 3797, 3803, 3821, 3823, 3833, 3847, 3851,
                3853, 3863, 3877, 3881, 3889, 3907, 3911, 3917, 3919, 3923, 3929, 3931, 3943, 3947, 3967, 3989, 4001, 4003,
                4007, 4013, 4019, 4021, 4027, 4049, 4051, 4057, 4073, 4079, 4091, 4093, 4099, 4111, 4127, 4129, 4133, 4139,
                4153, 4157, 4159, 4177, 4201, 4211, 4217, 4219, 4229, 4231, 4241, 4243, 4253, 4259, 4261, 4271, 4273, 4283,
                4289, 4297, 4327, 4337, 4339, 4349, 4357, 4363, 4373, 4391, 4397, 4409, 4421, 4423, 4441, 4447, 4451, 4457,
                4463, 4481, 4483, 4493, 4507, 4513, 4517, 4519, 4523, 4547, 4549, 4561, 4567, 4583, 4591, 4597, 4603, 4621,
                4637, 4639, 4643, 4649, 4651, 4657, 4663, 4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751, 4759, 4783,
                4787, 4789, 4793, 4799, 4801, 4813, 4817, 4831, 4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937,
                4943, 4951, 4957, 4967, 4969, 4973, 4987, 4993, 4999};

        for (int i = 0; i < primes.length; i++) {
            for (int j = i+1; j < primes.length; j++) {

                long n = primes[i] * primes[j];
                if (n > limit) break;

                long phi = (primes[i] - 1) * (primes[j] - 1);
                double ratio = ((double) n) / phi;

                if (Utility.areNumbersPermutation(n, phi) && bestRatio > ratio) {
                    best = n;
                    bestRatio = ratio;
                }
            }
        }

        return best;
    }
}
