package com.project.euler.utils;

import java.util.HashMap;
import java.util.Map;

public class Utility {

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
