package com.project.euler.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Utility {

    public String readFromInputStream(String filename) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    // generate boolean array of prime numbers until max_size valued element
    public static boolean[] getPrimes(int max_size) {
        boolean[] primes = new boolean[max_size];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i * i < max_size; i++) {
            if (primes[i]) {
                for (int j = i * i; j < max_size; j += i)
                    primes[j] = false;
            }
        }
        return primes;
    }

    // length of recurrence digits for num/den fraction
    public static int lengthOfRecurringCycle(int num, int den) {
        Map<Integer, Integer> remMap = new HashMap<>();
        int rem = num % den, i = 2;
        remMap.put(rem, 1);
        while(rem != 0) {
            num = rem*10;
            rem = num % den;

            if(remMap.containsKey(rem))
                return i - remMap.get(rem);
            else
                remMap.put(rem, i++);

            if(rem==0) return 0;
        }

        return 0;
    }

    // multiply a number(in form of stringbuilder) by another digit (integer)
    public static StringBuilder multiplyByDigit(StringBuilder sb, int i) {
        int carry = 0;
        StringBuilder sb2 = new StringBuilder();
        for(int j=0; j<sb.length(); j++) {
            int temp = carry + Character.getNumericValue(sb.charAt(j))*i;
            sb2.append(temp%10);
            carry = temp/10;
        }
        if(carry!=0) sb2.append(carry);

        return sb2;
    }

    // add two numbers represented as stringbuilder. used for huge entries
    public static StringBuilder sumNumbers(StringBuilder sb, StringBuilder sb2) {
        int a = sb.length();
        int b = sb2.length();

        StringBuilder sb3 = new StringBuilder();

        int i=0, j=0, carry = 0;
        while(i<a && j<b) {
            int temp = carry + Character.getNumericValue(sb.charAt(i++)) +
                    Character.getNumericValue(sb2.charAt(j++));
            carry = temp/10;
            sb3.append(temp%10);
        }

        if(i==a) {
            while(j<b) {
                int temp = carry + Character.getNumericValue(sb2.charAt(j++));
                carry = temp/10;
                sb3.append(temp%10);
            }
        }

        if(j==b) {
            while(i<a) {
                int temp = carry + Character.getNumericValue(sb.charAt(i++));
                carry = temp/10;
                sb3.append(temp%10);
            }
        }

        if(carry!=0) sb3.append(carry);

        return sb3;
    }

    // check if string is palindrome
    public static boolean isStringPalindrome(String s) {
        int start = 0, end = s.length()-1;
        while (start < end) {
            if(s.charAt(start++) != s.charAt(end--))
                return false;
        }

        return true;
    }

    // check if a integer is palindrome
    public static boolean isPalindrome(int num) {
        return isStringPalindrome(String.valueOf(num));
    }

    // get binary conversion of a integer
    public static String convertToBinary(int num) {
        StringBuilder sb = new StringBuilder();
        while(num > 0) {
            sb.append(num % 2);
            num /= 2;
        }

        return sb.reverse().toString();
    }

    // check if 1 to 9 are all present in string
    public static boolean isPanDigital(String s, int limit) {
        if(s.length() != 9)
            return false;

        int[] count = new int[limit+1];
        for(char ch: s.toCharArray()) {
            count[ch-'0']++;
        }

        for(int i=1; i<=limit; i++) {
            if (count[i] == 0) return false;
        }

        return true;
    }

    // check if 1 to limit are all present in long number ( 1 < limit <=9 )
    public static boolean isPanDigital(long l, int limit) {
        if(String.valueOf(l).contains("0"))
            return false;
        return isPanDigital(String.valueOf(l), limit);
    }

    // check if a number is prime
    public static boolean isPrime(int num) {
        if(num==1) return false;

        for(int i=2; i<=Math.sqrt(num); i++) {
            if(num%i ==0) return false;
        }
        return true;
    }

    // Generate all permutations of given entries in nums
    public static List<Integer> generatePermutations(int[] nums) {
        List<Integer> permutations = new ArrayList<>();
        generatePermutationsHelper(nums, permutations, new StringBuilder());
        return permutations;
    }

    private static void generatePermutationsHelper(int[] nums, List<Integer> permutations, StringBuilder sb) {
        if(sb.length()==nums.length) {
            permutations.add(Integer.parseInt(sb.toString()));
            return;
        }

        for (int num : nums) {
            if (sb.indexOf(String.valueOf(num)) == -1) {
                sb.append(num);
                generatePermutationsHelper(nums, permutations, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static boolean isPermutation(int a, int b) {
        return areNumbersPermutation(a, b);
    }

    public static int sumDigits(String n) {
        return n.chars()
                .map(c -> Character.getNumericValue((char) c))
                .sum();
    }

    public static int gcd(int a, int b) {
        int y, x;

        if (a > b) {
            x = a;
            y = b;
        } else {
            x = b;
            y = a;
        }

        while (x % y != 0) {
            int temp = x;
            x = y;
            y = temp % x;
        }

        return y;
    }

    public static boolean isPerfectSquare(int a) {
        double sqrt = Math.sqrt(a);
        return (int) sqrt == sqrt;
    }

    public static boolean areNumbersPermutation(long a, long b) {
        Map<Long, Long> count = new HashMap<>();
        while(a > 0) {
            count.put(a%10, count.getOrDefault(a%10, 0L)+1);
            a /= 10;
        }

        while(b > 0) {
            count.put(b%10, count.getOrDefault(b%10, 0L)-1);
            b /= 10;
        }

        for(long val: count.values()) {
            if(val != 0)
                return false;
        }

        return true;
    }
}
