package com.project.euler.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Utility {

    // prime numbers until max_size valued element
    public static boolean[] getPrimes(int max_size) {
        boolean[] primes = new boolean[max_size];
        Arrays.fill(primes, true);

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
    public static StringBuilder sumDigits(StringBuilder sb, StringBuilder sb2) {
        int a = sb.length();
        int b = sb2.length();

        StringBuilder sb3 = new StringBuilder();

        int i=0, j=0, carry = 0;
        while(i<a && j<b) {
            int temp = carry +Character.getNumericValue(sb.charAt(i++)) +
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
}
