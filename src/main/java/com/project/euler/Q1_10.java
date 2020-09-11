package com.project.euler;

import com.project.euler.utils.Utility;

public class Q1_10 {
    public static void main(String[] args) {
        System.out.println("Q1: " + problem1());
        System.out.println("Q2: " + problem2());
        System.out.println("Q3: " + problem3());
        System.out.println("Q4: " + problem4());
        System.out.println("Q5: " + problem5());
        System.out.println("Q6: " + problem6());
        System.out.println("Q7: " + problem7());
        System.out.println("Q8: " + problem8());
        System.out.println("Q9: " + problem9());
        System.out.println("Q10: " + problem10());
    }

    private static int problem1() {
        int sum = 0;
        for(int i=1; i<1000; i++) {
            if(i%3==0 || i%5==0)
                sum += i;
        }
        return sum;
    }

    private static int problem2() {
        int sum = 0, max = 4000000, a = 1, b = 2;

        do {
            if(b%2==0) sum+=b;

            int temp = a+b;
            a = b;
            b = temp;
        } while (b<=max);

        return sum;
    }

    private static long problem3() {
        long num = 600851475143L, factor = 2;

        for(int i=3; i< Math.sqrt(num); i++) {
            while(num%i==0) {
                factor = Math.max(factor, i);
                num /= i;
            }
        }

        if(num > 2)
            factor = Math.max(factor, num);

        return factor;
    }

    private static int problem4() {
        int result = Integer.MIN_VALUE;

        for(int i=999; i>=100; i--) {
            for(int j=999; j>=100; j--) {
               int num = i*j;
               String str = String.valueOf(num);
               if(str.equals(new StringBuilder(str).reverse().toString()))
                   result = Math.max(result, num);
            }
        }

        return result;
    }

    private static int problem5() {
        int[] nums = new int[]{2,3,5,7,11,13,17,19};
        int target = 20, result = 1;
        for(int num: nums) {
            int temp = num;
            do {
                result *= num;
                temp = temp*num;
            } while (temp < target);
        }

        return result;
    }

    private static int problem6() {
        int result = 0, sum = 5050;
        for(int i=1; i<=100; i++) {
            sum -= i;
            result += sum * i;
        }

        return 2*result;
    }

    private static int problem7() {
        int count = 0, max_size = 105000;
        boolean[] primes = Utility.getPrimes(max_size);

        for(int i=2; i<max_size; i++) {
            if(primes[i]) count++;
            if(count==10001) return i;
        }

        return -1;
    }

    private static long problem8() {
        String s = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";
        long result = Integer.MIN_VALUE;

        for(int i=0; i<=s.length()-13; i++) {
            long currProduct = 1;
            for(int j=0; j<13; j++)
                currProduct *= Character.getNumericValue(s.charAt(i+j));
            result = Math.max(result, currProduct);
        }

        return result;
    }

    private static int problem9() {
        for(int a=1; a<333; a++) {
            for(int b=1; b<(1000-a)/2; b++) {
                int c = 1000-b-a;
                if(Math.pow(a,2) + Math.pow(b,2) == Math.pow(c,2))
                    return a * b * c;
            }
        }
        return -1;
    }

    private static long problem10() {
        int max_size = 2000000;
        boolean[] primes = Utility.getPrimes(max_size);

        long sum = 0;
        for(int i=2; i<max_size; i++) {
            if(primes[i]) sum+=i;
        }

        return sum;
    }
}
